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
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

public class FileSystemIndexer extends Indexer {

    private final File FILE_HANDLE;

    public FileSystemIndexer(File file) {
        FILE_HANDLE = file;
        directory = getIndexDirectory();
    }

    @Override
    protected Directory getIndexDirectory() {
        if (FILE_HANDLE == null) return null;
        if (directory != null) return directory;
        Directory fsDirectory = null;
        try {
            fsDirectory = FSDirectory.open(FILE_HANDLE.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fsDirectory;
    }

    @Override
    protected Analyzer getAnalyzer() {
        if (analyzer != null) return analyzer;
        return new StandardAnalyzer();
    }
}
