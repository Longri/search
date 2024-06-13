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

import org.apache.lucene.document.Document;

import java.io.IOException;
import java.util.ArrayList;

public class IndexSearcher extends Searcher {

    public ArrayList<Integer> indexes = new ArrayList<>();

    public IndexSearcher(Indexer indexer) {
        super(indexer);
    }

    @Override
    protected void setFoundDocs(org.apache.lucene.search.IndexSearcher searcher, ArrayList<Integer> foundDocs) throws IOException {
        indexes.clear();
        for (int sd : foundDocs) {
            Document d = searcher.doc(sd);
            indexes.add(Integer.valueOf(d.get(SearchDokument.FIELD_NAME_INDEX)));
        }
    }
}
