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

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Indexer {

    protected final Analyzer analyzer;
    protected Directory directory;

    public Indexer() {
        analyzer = getAnalyzer();
        directory = getIndexDirectory();
    }

    protected abstract Directory getIndexDirectory();

    protected abstract Analyzer getAnalyzer();


    public void indexDocument(SearchDokument dokument) throws IOException {
        indexDocuments(dokument, null);
    }

    public void indexDocuments(SearchDokument... dokuments) throws IOException {
        // IndexWriter Configuration
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        //IndexWriter writes new index files to the directory
        IndexWriter writer = new IndexWriter(directory, iwc);

        for (SearchDokument doc : dokuments) {
            if (doc != null) writer.addDocument(doc.doc);
        }

        //don't forget to close the writer
        writer.close();
    }

    public void indexDocuments(List<? extends SearchDokument> dokuments) throws IOException {
        // IndexWriter Configuration
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        //IndexWriter writes new index files to the directory
        IndexWriter writer = new IndexWriter(directory, iwc);

        for (SearchDokument doc : dokuments) {
            if (doc != null) writer.addDocument(doc.doc);
        }

        //don't forget to close the writer
        writer.close();
    }

    public void indexDocuments(ArrayList<? extends SearchDokument> dokuments) throws IOException {
        // IndexWriter Configuration
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        //IndexWriter writes new index files to the directory
        IndexWriter writer = new IndexWriter(directory, iwc);

        for (SearchDokument doc : dokuments) {
            if (doc != null) writer.addDocument(doc.doc);
        }

        //don't forget to close the writer
        writer.close();
    }

}
