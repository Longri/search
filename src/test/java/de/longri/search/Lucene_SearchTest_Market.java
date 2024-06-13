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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by brad on 6/7/15.
 */

public class Lucene_SearchTest_Market {


    @Test
    public void testSearchIndex() throws IOException {

        List<SearchDokument> documents = getTestDokuments();

        Indexer indexer = new RamIndexer();
        indexer.indexDocuments(documents);

        IndexSearcher SEARCHER = new IndexSearcher(indexer);

        SEARCHER.search("Stra");
        assertArrayEquals(new Integer[]{29, 30, 33, 34, 45, 48, 49, 39, 40, 42}, SEARCHER.indexes.toArray());

        SEARCHER.search("\"Straumann\"");
        assertArrayEquals(new Integer[]{45, 49, 48, 34}, SEARCHER.indexes.toArray());

        SEARCHER.search("Viev");
        assertArrayEquals(new Integer[]{}, SEARCHER.indexes.toArray());

        SEARCHER.search("Vivi");
        assertArrayEquals(new Integer[]{28, 45, 46}, SEARCHER.indexes.toArray());

        SEARCHER.search("Vivie");
        assertArrayEquals(new Integer[]{28, 45 ,46}, SEARCHER.indexes.toArray());

        SEARCHER.search("plus");
        assertArrayEquals(new Integer[]{50, 36, 38, 28, 41, 48}, SEARCHER.indexes.toArray());

        SEARCHER.search("plus  nz");
        assertArrayEquals(new Integer[]{50, 36, 38, 28, 41, 48, 43, 47, 39, 40, 33}, SEARCHER.indexes.toArray());
    }

    private List<SearchDokument> getTestDokuments() {
        List<SearchDokument> documents = new ArrayList<>();

        documents.add(new SearchDokument(28, "Dominikanische Republik (DO) Roama Labs SRL weil keine Registrierung benötigt wird cerabone® plus 1,0-2,0 mm 1821 cerabone® plus 1,0-2,0 mm 1820 cerabone® plus 0,5-1,0 mm 1810 cerabone® plus 0,5-1,0 mm 1811 Sonstige dieter GmbH GER (Vion 20) NZ (Finegand 15) NZ (Hawera 10) Vivien Ernst (VE) "));
        documents.add(new SearchDokument(29, "Dänemark (DK) Puredent APS NULL Jason® membrane 15 x 20 mm 681520 Jason® membrane 20 x 30 mm 682030 Jason® membrane 30 x 40 mm 683040 Lieferanten Osartis GmbH Retail biota bioimplants AG GER (Mühlhausen 51) HRV (Sablic 50)  SVN (Celjske 48) testRA (T-RA)"));
        documents.add(new SearchDokument(30, " Kroatien (HR) Novodent d.o.o. NULL mucoderm® 15 x 20 mm 701520 mucoderm® 20 x 30 mm 702030 mucoderm® 30 x 40 mm 703040 mucoderm® Weichgewebe-Punch 710210 Retail Medical Biomaterial Products GmbH Retail biota bioimplants AG GER (Vorwerk Podemus)  HRV (Sablic 50)  testRA (T-RA)"));
        documents.add(new SearchDokument(31, "Aserbaidschan (AZ) FAL Dental LLC weil keine Registrierung benötigt wird collprotect® membrane 15 x 20 mm 601520 collprotect® membrane 20 x 30 mm 602030 collprotect® membrane 30 x 40 mm 603040 Retail Medical Biomaterial Products GmbH Retail biota bioimplants AG GER (Vorwerk Podemus)  HRV (Sablic 50)  Torsten Ulm (TU) "));
        documents.add(new SearchDokument(33, "Griechenland (GR) Denco Dental SA NULL cerabone® Granula 0,5-1,0 mm 1510 cerabone® Granula 0,5-1,0 mm 1511 cerabone® Granula 0,5-1,0 mm 1512 cerabone® Granula 0,5-1,0 mm 1515 cerabone® Granula 1,0-2,0 mm 1520 cerabone® Granula 1,0-2,0 mm 1521 cerabone® Granula 1,0-2,0 mm 1522 cerabone® Granula 1,0-2,0 mm 1525 cerabone® Block 20 x 20 x 10 mm 1722 Sonstige dieter GmbH NZ (Finegand 15) NZ (Hawera 10) GER (Vion 20) testRA (T-RA) "));
        documents.add(new SearchDokument(34, "Deutschland (DE) Straumann GmbH NULL mucoderm® 15 x 20 mm 701520 mucoderm® 20 x 30 mm 702030 mucoderm® 30 x 40 mm 703040 mucoderm® Weichgewebe-Punch 710210 Sonstige dieter GmbH GER (Mühlhausen 51) Torsten Ulm (TU)"));
        documents.add(new SearchDokument(35, "Kasachstan (KZ) TOO DentalCom NULL cerabone® Granula 0,5-1,0 mm 1510 cerabone® Granula 0,5-1,0 mm 1511 cerabone® Granula 0,5-1,0 mm 1512 cerabone® Granula 0,5-1,0 mm 1515 cerabone® Granula 1,0-2,0 mm 1520 cerabone® Granula 1,0-2,0 mm 1521 cerabone® Granula 1,0-2,0 mm 1522 cerabone® Granula 1,0-2,0 mm 1525 cerabone® Block 20 x 20 x 10 mm 1722 Sonstige dieter GmbH GER (Vion 20) NZ (Finegand 15) NZ (Hawera 10) Torsten Ulm (TU) "));
        documents.add(new SearchDokument(36, "Kasachstan (KZ) TOO DentalCom No expiry date stated on certificate cerabone® plus 1,0-2,0 mm 1821 cerabone® plus 1,0-2,0 mm 1820 cerabone® plus 0,5-1,0 mm 1810 cerabone® plus 0,5-1,0 mm 1811 Sonstige dieter GmbH GER (Vion 20) NZ (Finegand 15) NZ (Hawera 10) Torsten Ulm (TU)"));
        documents.add(new SearchDokument(37, "Kasachstan (KZ) TOO DentalCom No expiry date stated on certificate Jason® membrane 15 x 20 mm 681520 Jason® membrane 20 x 30 mm 682030 Jason® membrane 30 x 40 mm 683040 Retail biota bioimplants AG GER (Mühlhausen 51) HRV (Sablic 50)  SVN (Celjske 48) Torsten Ulm (TU)"));
        documents.add(new SearchDokument(38, "Libanon (LB) DMS - Dental Medical Supplies NULL cerabone® plus 1,0-2,0 mm 1821 cerabone® plus 1,0-2,0 mm 1820 cerabone® plus 0,5-1,0 mm 1810 cerabone® plus 0,5-1,0 mm 1811 Sonstige dieter GmbH GER (Vion 20) NZ (Finegand 15) NZ (Hawera 10) Torsten Ulm (TU)"));
        documents.add(new SearchDokument(39, "Albanien (AL) New Dental shpk NULL cerabone® Granula 0,5-1,0 mm 1510 cerabone® Granula 0,5-1,0 mm 1511 cerabone® Granula 0,5-1,0 mm 1512 cerabone® Granula 0,5-1,0 mm 1515 cerabone® Granula 1,0-2,0 mm 1520 cerabone® Granula 1,0-2,0 mm 1521 cerabone® Granula 1,0-2,0 mm 1522 cerabone® Granula 1,0-2,0 mm 1525 cerabone® Block 20 x 20 x 10 mm 1722 Sonstige dieter GmbH GER (Vion 20) NZ (Finegand 15) NZ (Hawera 10) Christian Münzhardt (ChM) Torsten Ulm (TU) New Dental shpk / Active registration GER (Vion)" +
                "NZ (Finegand) " +
                "NZ (Hawera) dieter 4756.0 NO 30.08.17 werner ChM, TU / 4756.0 cerabone Altes und neues Layout registration number of medical device (within national register): 2476; no expiry date spevified National Agency for Drugs and Medical Devices (AKBPM) werner Albania 4756.0 see remarks"));
        documents.add(new SearchDokument(40, "Algerien (DZ) IMPLANTOMED EURL NULL cerabone® Granula 0,5-1,0 mm 1510 cerabone® Granula 0,5-1,0 mm 1511 cerabone® Granula 0,5-1,0 mm 1512 cerabone® Granula 0,5-1,0 mm 1515 cerabone® Granula 1,0-2,0 mm 1520 cerabone® Granula 1,0-2,0 mm 1521 cerabone® Granula 1,0-2,0 mm 1522 cerabone® Granula 1,0-2,0 mm 1525 cerabone® Block 20 x 20 x 10 mm 1722 Sonstige dieter GmbH GER (Vion 20) NZ (Finegand 15) NZ (Hawera 10) Christian Münzhardt (ChM) Torsten Ulm (TU) Implantomed / Active registration GER (Vion) " +
                "NZ (Finegand) " +
                "NZ (Hawera) dieter import licence, see remarks 16.03.23 werner ChM, TU / import licence, see remarks cerabone Altes und neues Layout Import licence (No. 838) dated 16.03.2023 available (renewed anually); product is subject to an ongoing import programme MINISTERE DE L`INDUSTRIE PHARMACEUTIQUE werner Algeria import licence, see remarks see remarks"))
        ;
        documents.add(new SearchDokument(41, "Armenien (AM) NULL cerabone® plus 1,0-2,0 mm 1821 cerabone® plus 1,0-2,0 mm 1820 cerabone® plus 0,5-1,0 mm 1810 cerabone® plus 0,5-1,0 mm 1811 Sonstige dieter GmbH GER (Vion 20) NZ (Finegand 15) NZ (Hawera 10) Torsten Ulm (TU) MhefGacia LLC / Not neccessary GER (Vion) " +
                "NZ (Finegand) " +
                "NZ (Hawera) dieter - NO - werner ok HB, TU / - cerabone plus Altes und neues Layout Armenia is part of the EAEU, new requirements need to be assessed at least annually, 07.05.2021 - werner Armenia - /"));
        documents.add(new SearchDokument(42, "Bahrain (BH) Hamad S. Al Hawas Est NULL permamem® 15 x 20 mm 801520 permamem® 20 x 30 mm 802030 permamem® 30 x 40 mm 803040 Sonstige dieter GmbH Christian Münzhardt (ChM) Torsten Ulm (TU) AvoStrata Medical Care / Ongoing certification N/A dieter werner ChM, TU / permamem Altes und neues Layout werner Bahrain /"));
        documents.add(new SearchDokument(43, "NULL cerabone® Granula 0,5-1,0 mm 1510 cerabone® Granula 0,5-1,0 mm 1511 cerabone® Granula 0,5-1,0 mm 1512 cerabone® Granula 0,5-1,0 mm 1515 cerabone® Granula 1,0-2,0 mm 1520 cerabone® Granula 1,0-2,0 mm 1521 cerabone® Granula 1,0-2,0 mm 1522 cerabone® Granula 1,0-2,0 mm 1525 cerabone® Block 20 x 20 x 10 mm 1722 Sonstige dieter GmbH GER (Vion 20) NZ (Finegand 15) NZ (Hawera 10) Christian Münzhardt (ChM) Torsten Ulm (TU) JSC (UAB Gravitonas/ Med Dental Gruppa) / Implantrade 1722.0 Active registration GER (Vion) NZ (Finegand) " +
                "NZ (Hawera) dieter NM-7.109473 NO 28.01.21 werner ChM, TU / NM-7.109473 cerabone Neues Layout werner Belarus NM-7.109473 28.01.26"));
        documents.add(new SearchDokument(44, "Bosnien und Herzegowina (BA) Krajinagroup d.o.o. NULL Jason® membrane 15 x 20 mm 681520 Jason® membrane 20 x 30 mm 682030 Jason® membrane 30 x 40 mm 683040 Lieferanten Osartis GmbH Retail biota bioimplants AG GER (Mühlhausen 51) HRV (Sablic 50)  SVN (Celjske 48) Christian Münzhardt (ChM) Torsten Ulm (TU) Krajinagroup  / Active registration GER (Mühlhausen) " +
                "HRV (Sablic) " +
                "SVN (Celjske) Osartis und biota 06-07.4-1-6206-4/18 NO 04.12.18 werner ChM, TU / 06-07.4-1-6206-4/18 Jason membrane Altes und neues Layout werner Bosnia & Herzegovina OK 06-07.4-1-6206-4/18 03.12.23"));
        documents.add(new SearchDokument(45, "Chile (CL) All Biomed Chile SpA NULL Straumann®  cerabone® 0,5-1,0 mm BS-1510 Straumann®  cerabone® 0,5-1,0 mm BS-1511 Straumann®  cerabone® 0,5-1,0 mm BS-1512 Straumann®  cerabone® 0,5-1,0 mm BS-1515 Straumann®  cerabone® 1,0-2,0 mm BS-1520 Straumann®  cerabone® 1,0-2,0 mm BS-1521 Straumann®  cerabone® 1,0-2,0 mm BS-1522 Straumann®  cerabone® 1,0-2,0 mm BS-1525 Retail Medical Biomaterial Products GmbH GER (Vorwerk Podemus)  Vivien Ernst (VE) Torsten Ulm (TU) All Biomed Chile SpA / Not neccessary GER (Vorwerk Podemus) MBP werner VE, TU / mucoderm punch Neues Layout werner Chile voluntary registration "));
        documents.add(new SearchDokument(46, "NULL Jason® membrane 15 x 20 mm 681520 Jason® membrane 20 x 30 mm 682030 Jason® membrane 30 x 40 mm 683040 Lieferanten Osartis GmbH Retail biota bioimplants AG HRV (Sablic 50)  Vivien Ernst (VE) Torsten Ulm (TU) Master Dental / Ongoing certification GER (Mühlhäuser) " +
                "HRV (Sablic) Osartis und biota werner VE, TU / Jason membrane Neues Layout Costa Rica"));

        documents.add(new SearchDokument(47, "Kroatien (HR) Novodent d.o.o. NULL cerabone® Granula 0,5-1,0 mm 1510 cerabone® Granula 0,5-1,0 mm 1511 cerabone® Granula 0,5-1,0 mm 1512 cerabone® Granula 0,5-1,0 mm 1515 cerabone® Granula 1,0-2,0 mm 1520 cerabone® Granula 1,0-2,0 mm 1521 cerabone® Granula 1,0-2,0 mm 1522 cerabone® Granula 1,0-2,0 mm 1525 cerabone® Block 20 x 20 x 10 mm 1722 Sonstige dieter GmbH GER (Vion 20) NZ (Finegand 15) NZ (Hawera 10) Benjamin Mucic (BeM) Katja Pratsch (KaP) Novodent 1720, 1722 Active registration GER (Vion) " +
                "NZ (Finegand) " +
                "NZ (Hawera) dieter D1323300040 NO 19.08.20 werner BeM, KaP / D1323300040 cerabone Neues Layout notification to HALMED necessary  Agency for Medicinal Products and Medical Devices of Croatia (HALMED) werner Croatia D1323300040 26.05.24"));
        documents.add(new SearchDokument(48, "Deutschland (DE) Straumann GmbH NULL cerabone® plus 1,0-2,0 mm 1821 cerabone® plus 1,0-2,0 mm 1820 cerabone® plus 0,5-1,0 mm 1810 cerabone® plus 0,5-1,0 mm 1811 Sonstige dieter GmbH GER (Vion 20) NZ (Finegand 15) NZ (Hawera 10) Torsten Ulm (TU) Thea Heinemeyer (TH) Straumann  / Active registration GER (Vion)" +
                "NZ (Finegand)" +
                "NZ (Hawera) dieter D1323300040 NO 19.08.20 werner TU,TH / D1323300040 cerabone plus Altes und neues Layout mdc werner Germany D1323300040 26.05.24"));
        documents.add(new SearchDokument(49, "Singapur (SG) Straumann Group & Clear Correct NULL Nathalie Dantan (ND) yes Straumann 30…S / 30…L " +
                "31…S / 31…L " +
                "35…S / 35…L " +
                "37…S Active registration N/A - 04.11.22 ND, PM / direct delivery from werner to STRM Singapore; cash and paper flow via werner CTGT0045N maxgraft / HSA CTBA Singapore - / Human Tissue Class I 14.11.22 "));
        documents.add(new SearchDokument(50, "Vietnam (VN) NULL cerabone® plus 1,0-2,0 mm 1821 cerabone® plus 1,0-2,0 mm 1820 cerabone® plus 0,5-1,0 mm 1810 cerabone® plus 0,5-1,0 mm 1811 Elena Pies (ElP) Torsten Ulm (TU) Pdent Trading and Service Ltd., Co In preparation werner ElP, TU cerabone plus Neues Layout Vietnam"));

        return documents;
    }
}
