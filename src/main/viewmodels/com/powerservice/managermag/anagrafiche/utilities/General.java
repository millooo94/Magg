package com.powerservice.managermag.anagrafiche.utilities;

import it.powerservice.managermag.customClass.CodDesc;
import it.powerservice.managermag.customClass.GridColumn;
import it.powerservice.managermag.customClass.TabRef;

import java.util.ArrayList;
import java.util.List;

public class General {
    public static ArrayList<TabRef> getMainTabs() {
        ArrayList<TabRef> mainTabs = new ArrayList<>();
        mainTabs.add(new TabRef("Scheda", "scheda"));
        mainTabs.add(new TabRef("Storico", "storico"));
        mainTabs.add(new TabRef("Documento", "documento"));
        mainTabs.add(new TabRef("Scadenze", "scadenze"));
        mainTabs.add(new TabRef("Note", "note"));
        mainTabs.add(new TabRef("Allegati", "allegati"));
        return mainTabs;
    }

    public static ArrayList<TabRef> getSecondaryTabs() {
        ArrayList<TabRef> secondaryTabs = new ArrayList<>();
        secondaryTabs.add(new TabRef("Dati Principali", "datiPrincipali"));
        secondaryTabs.add(new TabRef("Indirizzi/Sedi", "indirizziSedi"));
        secondaryTabs.add(new TabRef("Altri Dati", "altriDati"));
        secondaryTabs.add(new TabRef("Agente", "agente"));
        secondaryTabs.add(new TabRef("Personale", "personale"));
        secondaryTabs.add(new TabRef("Trasportatore", "trasportatore"));
        return secondaryTabs;
    }

    public static ArrayList<CodDesc> getSoggetti() {
        ArrayList<CodDesc> soggetti = new ArrayList<>();
        soggetti.add(new CodDesc("P", "Privato"));
        soggetti.add(new CodDesc("A", "Azienda"));
        soggetti.add(new CodDesc("EP", "Ente Pubblico"));
        return soggetti;
    }

    public static ArrayList<CodDesc> getSessi() {
        ArrayList<CodDesc> sessi = new ArrayList<>();
        sessi.add(new CodDesc("M", "Maschio"));
        sessi.add(new CodDesc("F", "Femmina"));
        return sessi;
    }

        public static ArrayList<CodDesc> getStati() {
            ArrayList<CodDesc> statiDelMondo = new ArrayList<>();
            statiDelMondo.add(new CodDesc("NONE", "(Nessuno)"));
            statiDelMondo.add(new CodDesc("AFG", "Afghanistan"));
            statiDelMondo.add(new CodDesc("ALB", "Albania"));
            statiDelMondo.add(new CodDesc("DZA", "Algeria"));
            statiDelMondo.add(new CodDesc("AND", "Andorra"));
            statiDelMondo.add(new CodDesc("AGO", "Angola"));
            statiDelMondo.add(new CodDesc("ATG", "Antigua e Barbuda"));
            statiDelMondo.add(new CodDesc("SAU", "Arabia Saudita"));
            statiDelMondo.add(new CodDesc("ARG", "Argentina"));
            statiDelMondo.add(new CodDesc("ARM", "Armenia"));
            statiDelMondo.add(new CodDesc("AUS", "Australia"));
            statiDelMondo.add(new CodDesc("AUT", "Austria"));
            statiDelMondo.add(new CodDesc("AZE", "Azerbaigian"));
            statiDelMondo.add(new CodDesc("BHS", "Bahamas"));
            statiDelMondo.add(new CodDesc("BHR", "Bahrein"));
            statiDelMondo.add(new CodDesc("BGD", "Bangladesh"));
            statiDelMondo.add(new CodDesc("BRB", "Barbados"));
            statiDelMondo.add(new CodDesc("BEL", "Belgio"));
            statiDelMondo.add(new CodDesc("BLZ", "Belize"));
            statiDelMondo.add(new CodDesc("BEN", "Benin"));
            statiDelMondo.add(new CodDesc("BTN", "Bhutan"));
            statiDelMondo.add(new CodDesc("BLR", "Bielorussia"));
            statiDelMondo.add(new CodDesc("MMR", "Birmania"));
            statiDelMondo.add(new CodDesc("BOL", "Bolivia"));
            statiDelMondo.add(new CodDesc("BIH", "Bosnia ed Erzegovina"));
            statiDelMondo.add(new CodDesc("BWA", "Botswana"));
            statiDelMondo.add(new CodDesc("BRA", "Brasile"));
            statiDelMondo.add(new CodDesc("BRN", "Brunei"));
            statiDelMondo.add(new CodDesc("BGR", "Bulgaria"));
            statiDelMondo.add(new CodDesc("BFA", "Burkina Faso"));
            statiDelMondo.add(new CodDesc("BDI", "Burundi"));
            statiDelMondo.add(new CodDesc("KHM", "Cambogia"));
            statiDelMondo.add(new CodDesc("CMR", "Camerun"));
            statiDelMondo.add(new CodDesc("CAN", "Canada"));
            statiDelMondo.add(new CodDesc("CPV", "Capo Verde"));
            statiDelMondo.add(new CodDesc("TCD", "Ciad"));
            statiDelMondo.add(new CodDesc("CHL", "Cile"));
            statiDelMondo.add(new CodDesc("CHN", "Cina"));
            statiDelMondo.add(new CodDesc("CYP", "Cipro"));
            statiDelMondo.add(new CodDesc("COL", "Colombia"));
            statiDelMondo.add(new CodDesc("COM", "Comore"));
            statiDelMondo.add(new CodDesc("PRK", "Corea del Nord"));
            statiDelMondo.add(new CodDesc("KOR", "Corea del Sud"));
            statiDelMondo.add(new CodDesc("CIV", "Costa d'Avorio"));
            statiDelMondo.add(new CodDesc("CRI", "Costa Rica"));
            statiDelMondo.add(new CodDesc("HRV", "Croazia"));
            statiDelMondo.add(new CodDesc("CUB", "Cuba"));
            statiDelMondo.add(new CodDesc("DNK", "Danimarca"));
            statiDelMondo.add(new CodDesc("DMA", "Dominica"));
            statiDelMondo.add(new CodDesc("ECU", "Ecuador"));
            statiDelMondo.add(new CodDesc("EGY", "Egitto"));
            statiDelMondo.add(new CodDesc("SLV", "El Salvador"));
            statiDelMondo.add(new CodDesc("ARE", "Emirati Arabi Uniti"));
            statiDelMondo.add(new CodDesc("ERI", "Eritrea"));
            statiDelMondo.add(new CodDesc("EST", "Estonia"));
            statiDelMondo.add(new CodDesc("SWZ", "Eswatini"));
            statiDelMondo.add(new CodDesc("ETH", "Etiopia"));
            statiDelMondo.add(new CodDesc("FJI", "Fiji"));
            statiDelMondo.add(new CodDesc("PHL", "Filippine"));
            statiDelMondo.add(new CodDesc("FIN", "Finlandia"));
            statiDelMondo.add(new CodDesc("FRA", "Francia"));
            statiDelMondo.add(new CodDesc("GAB", "Gabon"));
            statiDelMondo.add(new CodDesc("GMB", "Gambia"));
            statiDelMondo.add(new CodDesc("GEO", "Georgia"));
            statiDelMondo.add(new CodDesc("DEU", "Germania"));
            statiDelMondo.add(new CodDesc("GHA", "Ghana"));
            statiDelMondo.add(new CodDesc("JAM", "Giamaica"));
            statiDelMondo.add(new CodDesc("JPN", "Giappone"));
            statiDelMondo.add(new CodDesc("DJI", "Gibuti"));
            statiDelMondo.add(new CodDesc("JOR", "Giordania"));
            statiDelMondo.add(new CodDesc("GRC", "Grecia"));
            statiDelMondo.add(new CodDesc("GRD", "Grenada"));
            statiDelMondo.add(new CodDesc("GTM", "Guatemala"));
            statiDelMondo.add(new CodDesc("GIN", "Guinea"));
            statiDelMondo.add(new CodDesc("GNQ", "Guinea Equatoriale"));
            statiDelMondo.add(new CodDesc("GNB", "Guinea-Bissau"));
            statiDelMondo.add(new CodDesc("GUY", "Guyana"));
            statiDelMondo.add(new CodDesc("HTI", "Haiti"));
            statiDelMondo.add(new CodDesc("HND", "Honduras"));
            statiDelMondo.add(new CodDesc("IND", "India"));
            statiDelMondo.add(new CodDesc("IDN", "Indonesia"));
            statiDelMondo.add(new CodDesc("IRN", "Iran"));
            statiDelMondo.add(new CodDesc("IRQ", "Iraq"));
            statiDelMondo.add(new CodDesc("IRL", "Irlanda"));
            statiDelMondo.add(new CodDesc("ISL", "Islanda"));
            statiDelMondo.add(new CodDesc("ISR", "Israele"));
            statiDelMondo.add(new CodDesc("ITA", "Italia"));
            statiDelMondo.add(new CodDesc("KAZ", "Kazakistan"));
            statiDelMondo.add(new CodDesc("KEN", "Kenya"));
            statiDelMondo.add(new CodDesc("KGZ", "Kirghizistan"));
            statiDelMondo.add(new CodDesc("KIR", "Kiribati"));
            statiDelMondo.add(new CodDesc("XKX", "Kosovo"));
            statiDelMondo.add(new CodDesc("KWT", "Kuwait"));
            statiDelMondo.add(new CodDesc("LAO", "Laos"));
            statiDelMondo.add(new CodDesc("LSO", "Lesotho"));
            statiDelMondo.add(new CodDesc("LVA", "Lettonia"));
            statiDelMondo.add(new CodDesc("LBN", "Libano"));
            statiDelMondo.add(new CodDesc("LBR", "Liberia"));
            statiDelMondo.add(new CodDesc("LBY", "Libia"));
            statiDelMondo.add(new CodDesc("LIE", "Liechtenstein"));
            statiDelMondo.add(new CodDesc("LTU", "Lituania"));
            statiDelMondo.add(new CodDesc("LUX", "Lussemburgo"));
            statiDelMondo.add(new CodDesc("MKD", "Macedonia del Nord"));
            statiDelMondo.add(new CodDesc("MDG", "Madagascar"));
            statiDelMondo.add(new CodDesc("MWI", "Malawi"));
            statiDelMondo.add(new CodDesc("MYS", "Malaysia"));
            statiDelMondo.add(new CodDesc("MDV", "Maldive"));
            statiDelMondo.add(new CodDesc("MLI", "Mali"));
            statiDelMondo.add(new CodDesc("MLT", "Malta"));
            statiDelMondo.add(new CodDesc("MAR", "Marocco"));
            statiDelMondo.add(new CodDesc("MHL", "Marshall"));
            statiDelMondo.add(new CodDesc("MRT", "Mauritania"));
            statiDelMondo.add(new CodDesc("MUS", "Mauritius"));
            statiDelMondo.add(new CodDesc("MEX", "Messico"));
            statiDelMondo.add(new CodDesc("FSM", "Micronesia"));
            statiDelMondo.add(new CodDesc("MDA", "Moldavia"));
            statiDelMondo.add(new CodDesc("MCO", "Monaco"));
            statiDelMondo.add(new CodDesc("MNG", "Mongolia"));
            statiDelMondo.add(new CodDesc("MNE", "Montenegro"));
            statiDelMondo.add(new CodDesc("MOZ", "Mozambico"));
            statiDelMondo.add(new CodDesc("NAM", "Namibia"));
            statiDelMondo.add(new CodDesc("NRU", "Nauru"));
            statiDelMondo.add(new CodDesc("NPL", "Nepal"));
            statiDelMondo.add(new CodDesc("NIC", "Nicaragua"));
            statiDelMondo.add(new CodDesc("NER", "Niger"));
            statiDelMondo.add(new CodDesc("NGA", "Nigeria"));
            statiDelMondo.add(new CodDesc("NOR", "Norvegia"));
            statiDelMondo.add(new CodDesc("NZL", "Nuova Zelanda"));
            statiDelMondo.add(new CodDesc("OMN", "Oman"));
            statiDelMondo.add(new CodDesc("NLD", "Paesi Bassi"));
            statiDelMondo.add(new CodDesc("PAK", "Pakistan"));
            statiDelMondo.add(new CodDesc("PLW", "Palau"));
            statiDelMondo.add(new CodDesc("PSE", "Palestina"));
            statiDelMondo.add(new CodDesc("PAN", "Panama"));
            statiDelMondo.add(new CodDesc("PNG", "Papua Nuova Guinea"));
            statiDelMondo.add(new CodDesc("PRY", "Paraguay"));
            statiDelMondo.add(new CodDesc("PER", "Perù"));
            statiDelMondo.add(new CodDesc("POL", "Polonia"));
            statiDelMondo.add(new CodDesc("PRT", "Portogallo"));
            statiDelMondo.add(new CodDesc("QAT", "Qatar"));
            statiDelMondo.add(new CodDesc("GBR", "Regno Unito"));
            statiDelMondo.add(new CodDesc("CAF", "Repubblica Centrafricana"));
            statiDelMondo.add(new CodDesc("CZE", "Repubblica Ceca"));
            statiDelMondo.add(new CodDesc("COD", "Repubblica Democratica del Congo"));
            statiDelMondo.add(new CodDesc("DOM", "Repubblica Dominicana"));
            statiDelMondo.add(new CodDesc("ROU", "Romania"));
            statiDelMondo.add(new CodDesc("RWA", "Ruanda"));
            statiDelMondo.add(new CodDesc("RUS", "Russia"));
            statiDelMondo.add(new CodDesc("KNA", "Saint Kitts e Nevis"));
            statiDelMondo.add(new CodDesc("VCT", "Saint Vincent e Grenadine"));
            statiDelMondo.add(new CodDesc("SLB", "Salomone"));
            statiDelMondo.add(new CodDesc("WSM", "Samoa"));
            statiDelMondo.add(new CodDesc("SMR", "San Marino"));
            statiDelMondo.add(new CodDesc("LCA", "Santa Lucia"));
            statiDelMondo.add(new CodDesc("STP", "São Tomé e Príncipe"));
            statiDelMondo.add(new CodDesc("SEN", "Senegal"));
            statiDelMondo.add(new CodDesc("SRB", "Serbia"));
            statiDelMondo.add(new CodDesc("SYC", "Seychelles"));
            statiDelMondo.add(new CodDesc("SLE", "Sierra Leone"));
            statiDelMondo.add(new CodDesc("SGP", "Singapore"));
            statiDelMondo.add(new CodDesc("SYR", "Siria"));
            statiDelMondo.add(new CodDesc("SVK", "Slovacchia"));
            statiDelMondo.add(new CodDesc("SVN", "Slovenia"));
            statiDelMondo.add(new CodDesc("SOM", "Somalia"));
            statiDelMondo.add(new CodDesc("ESP", "Spagna"));
            statiDelMondo.add(new CodDesc("LKA", "Sri Lanka"));
            statiDelMondo.add(new CodDesc("USA", "Stati Uniti d'America"));
            statiDelMondo.add(new CodDesc("ZAF", "Sudafrica"));
            statiDelMondo.add(new CodDesc("SDN", "Sudan"));
            statiDelMondo.add(new CodDesc("SSD", "Sudan del Sud"));
            statiDelMondo.add(new CodDesc("SUR", "Suriname"));
            statiDelMondo.add(new CodDesc("SWE", "Svezia"));
            statiDelMondo.add(new CodDesc("CHE", "Svizzera"));
            statiDelMondo.add(new CodDesc("SWZ", "Swaziland"));
            statiDelMondo.add(new CodDesc("TJK", "Tagikistan"));
            statiDelMondo.add(new CodDesc("TZA", "Tanzania"));
            statiDelMondo.add(new CodDesc("THA", "Thailandia"));
            statiDelMondo.add(new CodDesc("TLS", "Timor Est"));
            statiDelMondo.add(new CodDesc("TGO", "Togo"));
            statiDelMondo.add(new CodDesc("TON", "Tonga"));
            statiDelMondo.add(new CodDesc("TTO", "Trinidad e Tobago"));
            statiDelMondo.add(new CodDesc("TUN", "Tunisia"));
            statiDelMondo.add(new CodDesc("TUR", "Turchia"));
            statiDelMondo.add(new CodDesc("TKM", "Turkmenistan"));
            statiDelMondo.add(new CodDesc("TUV", "Tuvalu"));
            statiDelMondo.add(new CodDesc("UKR", "Ucraina"));
            statiDelMondo.add(new CodDesc("UGA", "Uganda"));
            statiDelMondo.add(new CodDesc("HUN", "Ungheria"));
            statiDelMondo.add(new CodDesc("URY", "Uruguay"));
            statiDelMondo.add(new CodDesc("UZB", "Uzbekistan"));
            statiDelMondo.add(new CodDesc("VUT", "Vanuatu"));
            statiDelMondo.add(new CodDesc("VEN", "Venezuela"));
            statiDelMondo.add(new CodDesc("VNM", "Vietnam"));
            statiDelMondo.add(new CodDesc("YEM", "Yemen"));
            statiDelMondo.add(new CodDesc("ZMB", "Zambia"));
            statiDelMondo.add(new CodDesc("ZWE", "Zimbabwe"));

            return statiDelMondo;
        }

        public static List<GridColumn> getIndirizziColumns() {
            List<GridColumn> indirizziColumns = new ArrayList<>();
            indirizziColumns.add(new GridColumn("Referente", "referente", "140px"));
            indirizziColumns.add(new GridColumn("Nazione", "nazione", "80px"));
            indirizziColumns.add(new GridColumn("Regione", "regione", "160px"));
            indirizziColumns.add(new GridColumn("Provincia", "provincia", "80px"));
            indirizziColumns.add(new GridColumn("Comune", "comune", "80px"));
            indirizziColumns.add(new GridColumn("Telefono 1", "telefono1", "100px"));
            indirizziColumns.add(new GridColumn("Email", "email", "180px"));

            return indirizziColumns;
        }

    public static String setTipoAnagrafica(String tipoAnagrafica) {

        String anagraficaExtendedType = "";

        switch (tipoAnagrafica) {
            case "C":
                anagraficaExtendedType = "Cliente";
                break;
            case "F":
                anagraficaExtendedType = "Fornitore";
                break;
            case "T":
                anagraficaExtendedType = "Trasportatore";
                break;
            case "A":
                anagraficaExtendedType = "Agente";
                break;
            case "P":
                anagraficaExtendedType = "Personal";
                break;
        }

        return anagraficaExtendedType;
    }

    public static List<CodDesc> getTipiIndirizzo() {
        ArrayList<CodDesc> tipiIndirizzo = new ArrayList<>();
        tipiIndirizzo.add(new CodDesc("S", "Sede"));
        tipiIndirizzo.add(new CodDesc("D", "Destinazione Principale"));
        tipiIndirizzo.add(new CodDesc("DS", "Destinazione Secondaria"));
        tipiIndirizzo.add(new CodDesc("A", "Altro"));
        return tipiIndirizzo;
    }
}
