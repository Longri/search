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
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;

/**
 * Holds the searchable Document info
 * (int) index
 * (String) name
 * (String) content
 * optional (String) path
 */
public class SearchDokument {

    public static final String FIELD_NAME_INDEX = "idx";
    public static final String FIELD_NAME_NAME = "name";
    public static final String FIELD_NAME_CONTENT = "content";

    public final Document doc;

    /**
     * Creates a empty SearchDokument
     */
    public SearchDokument() {
        doc = new Document();
    }

    /**
     * Creates a SearchDokument with indexed string content
     *
     * @param idx
     * @param content
     */
    public SearchDokument(int idx, String content) {
        this(idx, null, content);
    }

    /**
     * Creates a SearchDokument with indexed string content and name
     *
     * @param idx
     * @param name
     * @param content
     */
    public SearchDokument(int idx, String name, String content) {
        doc = new Document();
        doc.add(new StoredField("idx", idx));
        if (name != null) doc.add(new TextField("name", name, Field.Store.YES));
        doc.add(new TextField("content", content, Field.Store.YES));
    }
}
