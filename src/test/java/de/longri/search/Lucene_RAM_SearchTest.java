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

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by brad on 6/7/15.
 */

public class Lucene_RAM_SearchTest {


    @Test
    public void testSearchIndex() throws IOException {

        String doc1 = "Shall I compare thee to a summer's day? \n" +
                "Thou art more lovely and more temperate:\n" +
                "Rough winds do shake the darling buds of May,\n" +
                "And summer's lease hath all too short a date: \n" +
                "Sometime too hot the eye of heaven shines,\n" +
                "And often is his gold complexion dimm'd; \n" +
                "And every fair from fair sometime declines,\n" +
                "By chance or nature's changing course untrimm'd;\n" +
                "But thy eternal summer shall not fade\n" +
                "Nor lose possession  possession of that fair thou owest;\n" +
                "Nor shall Death brag thou wander'st in his shade,\n" +
                "When in eternal lines to time thou growest: \n" +
                "So long as men can breathe or eyes can see,\n" +
                "So long lives this and this gives life to thee.\n";

        String doc2 = "Let me not to the marriage of true minds\n" +
                "Admit impediments. Love is not love\n" +
                "Which alters when it alteration finds,\n" +
                "Or bends with the remover to remove:\n" +
                "O no! it is an ever-fixed mark \n" +
                "That looks on tempests and is never shaken;\n" +
                "It is the star to every wandering bark,\n" +
                "Whose worth's unknown, possession although his height be taken.\n" +
                "Love's not Time's fool, though rosy lips and cheeks \n" +
                "Within his bending sickle's compass come: \n" +
                "Love alters not with his brief hours and weeks, \n" +
                "But bears it out even to the edge of doom.\n" +
                "If this be error and upon me proved,\n" +
                "I never writ, nor no man ever loved. ";

        String doc3 = "My mistress' eyes are nothing like the sun;\n" +
                "Coral is far more red than her lips' red;\n" +
                "If snow be white, why then her breasts are dun;\n" +
                "If hairs be wires, black wires grow on her head.\n" +
                "I have seen roses damask'd, red and white,\n" +
                "But no such roses see I possession in her cheeks; \n" +
                "And in some perfumes is there more delight\n" +
                "Than in the breath that from my mistress reeks.\n" +
                "I love to hear her speak, yet well I know\n" +
                "That music hath a far more pleasing sound;\n" +
                "I grant I never saw a goddess go;\n" +
                "My mistress, when she walks, treads on the ground:\n" +
                "And yet, by heaven, I think my love as rare\n" +
                "As any she belied with false compare. \n";

        String doc4 = "The expense of spirit in a waste of shame\n" +
                "Is lust in action; and till action, lust\n" +
                "Is perjured, murderous, bloody, full of blame,\n" +
                "Savage, extreme, rude, cruel, not to trust,\n" +
                "Enjoy'd no sooner but despised straight,\n" +
                "Past reason hunted, and no sooner had\n" +
                "Past reason hated, as a swallow'd bait\n" +
                "On purpose laid to make the taker mad;\n" +
                "Mad in pursuit and in possession so;\n" +
                "Had, having, and in quest to have, extreme;\n" +
                "A bliss in proof, and proved, a very woe;\n" +
                "Before, a joy proposed; behind, a dream.\n" +
                "All this the world well knows; yet none knows well\n" +
                "To shun the heaven that leads men to this hell.";

        List<SearchDokument> documents = new ArrayList<>();
        documents.add(new SearchDokument(1, doc1));
        documents.add(new SearchDokument(2, doc2));
        documents.add(new SearchDokument(3, doc3));
        documents.add(new SearchDokument(4, doc4));


        Indexer indexer = new RamIndexer();
        indexer.indexDocuments(documents);

        IndexSearcher SEARCHER = new IndexSearcher(indexer);

        String searchTerm = "\"Mad in pursuit and in possession so\"";

//        SEARCHER.search(searchTerm);
//        assertEquals(1, SEARCHER.indexes.size());

        searchTerm = "every";
        SEARCHER.search(searchTerm);
        assertEquals(2, SEARCHER.indexes.size());
        assertEquals(2, SEARCHER.indexes.get(0));
        assertEquals(1, SEARCHER.indexes.get(1));

        searchTerm= "reason";
        SEARCHER.search(searchTerm);
        assertEquals(1, SEARCHER.indexes.size());
        assertEquals(4, SEARCHER.indexes.get(0));

        searchTerm= "possess";
        SEARCHER.search(searchTerm);
        assertEquals(4, SEARCHER.indexes.size());
        assertEquals(1, SEARCHER.indexes.get(0));
        assertEquals(2, SEARCHER.indexes.get(1));
        assertEquals(3, SEARCHER.indexes.get(2));
        assertEquals(4, SEARCHER.indexes.get(3));

        searchTerm= "possession";
        SEARCHER.search(searchTerm);
        assertEquals(4, SEARCHER.indexes.size());
        assertEquals(1, SEARCHER.indexes.get(0));

        searchTerm= "possession reason";
        SEARCHER.search(searchTerm);
        assertEquals(4, SEARCHER.indexes.size());
        assertEquals(1, SEARCHER.indexes.get(0));

    }
}
