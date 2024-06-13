/*
 * Copyright (C) 2024 Longri
 *
 * This file is part of fxutils.
 *
 * fxutils is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * fxutils is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with fxutils. If not, see <https://www.gnu.org/licenses/>.
 */
package de.longri.search;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.IndexSearcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class Searcher {

    private final static int DEFAULT_MAX_RESULTS = 10;
    private final static Pattern pattern = Pattern.compile("([&\\-]?)\\s*((?:\\w+|\"[^\"]*\"|\\w+-\\w+)+)");
    private final static Pattern hyphenCharacterPattern = Pattern.compile("([&\\-]?)(\\b\\w+-\\w+\\b)");

    private final Indexer indexer;

    public Searcher(Indexer indexer) {
        this.indexer = indexer;
    }

    public void search(String searchString) {
        search(searchString, DEFAULT_MAX_RESULTS);
    }


    static class PatternResult {
        String NonProcessedString;

        ArrayList<String> OrSearchString = new ArrayList<>();
        ArrayList<String> AndSearchStrings = new ArrayList<>();
        ArrayList<String> NotSearchStrings = new ArrayList<>();

        public PatternResult() {
        }

        public PatternResult(PatternResult hyphenResult, PatternResult splitResult) {
            OrSearchString.addAll(hyphenResult.OrSearchString);
            OrSearchString.addAll(splitResult.OrSearchString);
            AndSearchStrings.addAll(hyphenResult.AndSearchStrings);
            AndSearchStrings.addAll(splitResult.AndSearchStrings);
            NotSearchStrings.addAll(hyphenResult.NotSearchStrings);
            NotSearchStrings.addAll(splitResult.NotSearchStrings);
            NonProcessedString = hyphenResult.NonProcessedString != null ? hyphenResult.NonProcessedString : ""
                    + splitResult.NonProcessedString != null ? splitResult.NonProcessedString : "";
        }
    }

    private Query getQuery(String queryString) throws ParseException {
        QueryParser qp = new QueryParser(SearchDokument.FIELD_NAME_CONTENT, indexer.getAnalyzer());
        qp.setAllowLeadingWildcard(true);
        return qp.parse(queryString);
    }

    public void search(String search, int maxResults) {

        PatternResult hyphenResult = splitHyphenCharacterWorts(search);

        PatternResult splitResult = splitSearchPatern(hyphenResult.NonProcessedString);

        PatternResult patternResult = new PatternResult(hyphenResult, splitResult);

        ArrayList<Integer> orScoreDocs = new ArrayList();
        ArrayList<ArrayList<Integer>> andScoreDocs = new ArrayList<>();
        ArrayList<ArrayList<Integer>> notScoreDocs = new ArrayList<>();

        IndexReader reader = null;
        IndexSearcher searcher = null;
        try {

            //Create Reader
            reader = DirectoryReader.open(indexer.getIndexDirectory());

            //Create index searcher
            searcher = new IndexSearcher(reader);

            TopDocs foundDocs;

            for (String andSearch : patternResult.OrSearchString) {
                foundDocs = searcher.search(getQuery(getOrSearchString(andSearch)), maxResults);

                for (ScoreDoc doc : foundDocs.scoreDocs) {
                    if (!orScoreDocs.contains(doc.doc))
                        orScoreDocs.add(doc.doc);
                }
            }


            // search for AND results
            for (String andSearch : patternResult.AndSearchStrings) {
                foundDocs = searcher.search(getQuery(andSearch), maxResults);
                ArrayList<Integer> intArray = new ArrayList<Integer>();
                for (ScoreDoc doc : foundDocs.scoreDocs) {
                    if (!intArray.contains(doc.doc))
                        intArray.add(doc.doc);
                }
                andScoreDocs.add(intArray);
            }

            // search for NOT results
            for (String notSearch : patternResult.NotSearchStrings) {
                foundDocs = searcher.search(getQuery(notSearch), maxResults);
                ArrayList<Integer> intArray = new ArrayList<Integer>();
                for (ScoreDoc doc : foundDocs.scoreDocs) {
                    if (!intArray.contains(doc.doc))
                        intArray.add(doc.doc);
                }
                notScoreDocs.add(intArray);
            }


            //combine all results
            for (ArrayList<Integer> notIndxs : notScoreDocs) {
                for (int not : notIndxs) {
                    if (orScoreDocs.contains(not))
                        orScoreDocs.remove(orScoreDocs.indexOf(not));
                }

            }

            ArrayList<Integer> resultScoreDocs = new ArrayList<Integer>();
            if (andScoreDocs.isEmpty()) {
                resultScoreDocs = new ArrayList<Integer>();
                for (int doc : orScoreDocs) {
                    resultScoreDocs.add(doc);
                }
            } else {
                for (ArrayList<Integer> andIndxs : andScoreDocs) {
                    for (int and : andIndxs) {
                        if (orScoreDocs.contains(and))
                            resultScoreDocs.add(and);
                    }
                }
            }

            setFoundDocs(searcher, resultScoreDocs);


            //don't forget to close the reader
            reader.close();
        } catch (IOException e) {
            //Any error goes here
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    static String getOrSearchString(String search) {
        if (search.startsWith("\"")) return search;
        StringBuilder sb = new StringBuilder(search);
        sb.append(" *").append(search).append("*");
        sb.append(" ").append(search).append("*");
        sb.append(" *").append(search);
        sb.append(" \"").append(search).append("\"");
        return sb.toString();
    }

    static PatternResult splitSearchPatern(String search) {

        PatternResult patternResult = new PatternResult();

        // Erstellen Sie einen Matcher mit dem Muster und dem Eingabestring
        Matcher matcher = pattern.matcher(search);

        // Iterieren Sie über Übereinstimmungen und geben Sie das Ergebnis aus
        while (matcher.find()) {
            String delimiter = matcher.group(1);
            String word = matcher.group(2);

            // Überprüfen Sie das Vorzeichen und fügen Sie es vor das Wort hinzu
            if (!delimiter.isEmpty()) {
                if (delimiter.equals("&")) {
                    patternResult.AndSearchStrings.add(word.startsWith("\"") ? word : word + "~");
                } else {
                    patternResult.NotSearchStrings.add(word.startsWith("\"") ? word : word + "~");
                }
            } else {
                if (!word.startsWith("\""))
                    patternResult.OrSearchString.add(word);
//                    patternResult.OrSearchString.add(word );
                else
                    patternResult.OrSearchString.add(word);
            }
        }
        return patternResult;
    }

    static PatternResult splitHyphenCharacterWorts(String search) {
        PatternResult patternResult = new PatternResult();
        patternResult.NonProcessedString = search;

        Matcher matcher = hyphenCharacterPattern.matcher(search);

        while (matcher.find()) {

            if (matcher.group(1) != null && !matcher.group(1).isEmpty()) {
                if (matcher.group(1).equals("-")) {
                    patternResult.NotSearchStrings.add(matcher.group(2));
                } else {
                    patternResult.AndSearchStrings.add(matcher.group(2));
                }
            } else {
                String word = matcher.group(2);
                patternResult.OrSearchString.add(word);
            }

            //remove word from string
            patternResult.NonProcessedString = patternResult.NonProcessedString.replaceAll(matcher.group(), "");
        }

        return patternResult;
    }

    protected abstract void setFoundDocs(IndexSearcher searcher, ArrayList<Integer> foundDocs) throws IOException;
}
