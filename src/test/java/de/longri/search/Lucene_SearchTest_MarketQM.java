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

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Created by brad on 6/7/15.
 */

public class Lucene_SearchTest_MarketQM {


    @Test
    public void testSearchIndex() throws IOException {

        List<SearchDokument> documents = getTestDokuments();

        Indexer indexer = new RamIndexer();
        indexer.indexDocuments(documents);
        indexer.indexDocuments(documents);

        IndexSearcher SEARCHER = new IndexSearcher(indexer);


        SEARCHER.search("Straumann");
        assertArrayEquals(new Integer[]{32911,32912,32803,32804}, SEARCHER.indexes.toArray());

        SEARCHER.search("ME-Fix");
        assertArrayEquals(new Integer[]{32883}, SEARCHER.indexes.toArray());

        SEARCHER.search("permamem");
        assertArrayEquals(new Integer[]{32899, 32900, 32901, 32902, 32903, 32904}, SEARCHER.indexes.toArray());

        SEARCHER.search("pro");
        assertArrayEquals(new Integer[]{32883, 32803, 32804}, SEARCHER.indexes.toArray());
    }

    private List<SearchDokument> getTestDokuments() {
        List<SearchDokument> documents = new ArrayList<>();

        documents.add(new SearchDokument(32, " "));
        documents.add(new SearchDokument(32808, "511112 collacone® 23V11-15 Quarantäne(6) collacone   "));
        documents.add(new SearchDokument(32913, " 511112 collacone® 24V01-16 Quarantäne(6) collacone  "));
        documents.add(new SearchDokument(32914, " 511112 collacone® 24V01-22 Quarantäne(6) collacone  "));
        documents.add(new SearchDokument(32528, " 681520 Jason® membrane 15 x 20 mm 23FB50200 Quarantäne(6) Jason membrane  "));
        documents.add(new SearchDokument(32529, " 682030 Jason® membrane 20 x 30 mm 23FB50200 Quarantäne(6) Jason membrane  "));
        documents.add(new SearchDokument(32530, " 683040 Jason® membrane 30 x 40 mm 23FB50200 Quarantäne(6) Jason membrane  "));
        documents.add(new SearchDokument(32908, " BO-701520 mucoderm® 15 x 20 mm 23X11-21 Quarantäne(6) BO-mucoderm  "));
        documents.add(new SearchDokument(32909, " BO-702030 mucoderm® 20 x 30 mm 23X11-21 Quarantäne(6) BO-mucoderm  "));
        documents.add(new SearchDokument(32909, " BO-702030 mucoderm® 20 x 30 mm 23X11-21 Quarantäne(6) BO-mucoderm  "));
        documents.add(new SearchDokument(32910, " BO-703040 mucoderm® 30 x 40 mm 23X11-21 Quarantäne(6) BO-mucoderm  "));
        documents.add(new SearchDokument(32915, " BO-511112 collacone® 24V01-11 Quarantäne(6) BO-collacone  "));
        documents.add(new SearchDokument(32916, " BO-511112 collacone® 24V01-22 Quarantäne(6) BO-collacone  "));
        documents.add(new SearchDokument(32917, " BO-511112 collacone® 24V01-30 Quarantäne(6) BO-collacone  "));
        documents.add(new SearchDokument(15726, " 34000 Cortico Trimmer 02000617 Quarantäne(6) "));
        documents.add(new SearchDokument(18663, " 34000 Cortico Trimmer 02000682 Quarantäne(6) "));
        documents.add(new SearchDokument(32899, " 801520 permamem® 15 x 20 mm 24BA30150 Quarantäne(6) permamem  "));
        documents.add(new SearchDokument(32900, " 802030 permamem® 20 x 30 mm 24BA30150 Quarantäne(6) permamem  "));
        documents.add(new SearchDokument(32901, " 803040 permamem® 30 x 40 mm 24BA30150 Quarantäne(6) permamem  "));
        documents.add(new SearchDokument(32911, " AW-701520 Straumann® mucoderm 15 x 20 mm 24X01-16 Quarantäne(6) AW-Straumann mucoderm  "));
        documents.add(new SearchDokument(32912, " AW-703040 Straumann® mucoderm 30 x 40 mm 24X01-16 Quarantäne(6) AW-Straumann mucoderm  "));
        documents.add(new SearchDokument(32803, " AW-601520 Straumann® collprotect® membrane 15 x 20 mm 23X12-05 Quarantäne(6) AW-collprotect membrane  "));
        documents.add(new SearchDokument(32804, " AW-602030 Straumann® collprotect® membrane 20 x 30 mm 23X12-05 Quarantäne(6) AW-collprotect membrane  "));
        documents.add(new SearchDokument(32902, " BO-801520 permamem® 15 x 20 mm 24BA30160 Quarantäne(6) BO-permamem  "));
        documents.add(new SearchDokument(32903, " BO-802030 permamem® 20 x 30 mm 24BA30160 Quarantäne(6) BO-permamem  "));
        documents.add(new SearchDokument(32904, " BO-803040 permamem® 30 x 40 mm 24BA30160 Quarantäne(6) BO-permamem  "));
        documents.add(new SearchDokument(32783, " 1811 cerabone® plus 0,5-1,0 mm 23KA60070 Quarantäne(6) cerabone plus  "));
        documents.add(new SearchDokument(32784, " 1811 cerabone® plus 0,5-1,0 mm 23KA60080 Quarantäne(6) cerabone plus  "));
        documents.add(new SearchDokument(32883, " ME-FIX-SCREW1.5 Pro-Fix Membr.Fix Screw 1,5 x 3 AD03020A Quarantäne(6) "));

        return documents;
    }

    public static void main(String[] args) {
        String input = "suche suche2 & und & \"auch und\" - minus -MINUS ME-Fix";

        // Definieren Sie das gewünschte Muster
        Pattern pattern = Pattern.compile("([&\\-]?)\\s*((?:\\w+|\"[^\"]*\"|\\w+(?:-\\w+)*)+)");

        // Erstellen Sie einen Matcher mit dem Muster und dem Eingabestring
        Matcher matcher = pattern.matcher(input);

        // Iterieren Sie über Übereinstimmungen und geben Sie das Ergebnis aus
        while (matcher.find()) {
            String delimiter = matcher.group(1);
            String words = matcher.group(2);

            // Überprüfen Sie das Vorzeichen und fügen Sie es vor die Wörter hinzu
            if (!delimiter.isEmpty()) {
                System.out.print(delimiter);
            }
            System.out.println(words);
        }
    }
}
