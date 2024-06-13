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

import static org.junit.jupiter.api.Assertions.*;

class PatternTest {

    @Test
    void splitHyphenCharacterWortsTest() {
        String input = "suche ME-Fix suche2 & und & \"auch und\" - minus -MINUS-Plus  &neues-wort";

        Searcher.PatternResult result = Searcher.splitHyphenCharacterWorts(input);

        assertEquals("suche  suche2 & und & \"auch und\" - minus   ", result.NonProcessedString);
        assertEquals("[ME-Fix]", result.OrSearchString.toString());
        assertEquals("[MINUS-Plus]", result.NotSearchStrings.toString());
        assertEquals("[neues-wort]", result.AndSearchStrings.toString());
    }


    public static void main(String[] args) {
//        // Der zu durchsuchende String
//
//
//        // Das Muster für Wörter mit einem Bindestrich
//        String patternString = "([&\\-]?)(\\b\\w+-\\w+\\b)";
//
//        // Ein Pattern-Objekt erstellen
//        Pattern pattern = Pattern.compile(patternString);
//
//        // Einen Matcher erstellen und den Input-String durchsuchen
//        Matcher matcher = pattern.matcher(input);
//
//        // Alle gefundenen Wörter mit einem Bindestrich ausgeben
//        while (matcher.find()) {
//            System.out.println("Gefundenes Wort  : " + matcher.group());
//
//            System.out.println("Gefundenes Wort 1: " + matcher.group(1));
//            System.out.println("Gefundenes Wort 2: " + matcher.group(2));
//
//            System.out.println(" ");
//            System.out.println("----------------------------------------- ");
//            System.out.println(" ");
//        }
    }


}