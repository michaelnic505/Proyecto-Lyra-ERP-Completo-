package com.simplecore.erp.client.i18n;

import java.util.Locale;

public class LanguageManager {

    private Locale locale;

    public LanguageManager(String defaultLanguage) {
        setLocaleForLanguage(defaultLanguage);
    }

    // Método para establecer el idioma a partir del código proporcionado
    public void setLocaleForLanguage(String language) {
        switch (language.toLowerCase()) {
            case "es" -> {
                locale = Locale.forLanguageTag("es-ES");  // Español (España)
            }
            case "en" -> {
                locale = Locale.forLanguageTag("en-US");  // Inglés (Estados Unidos)
            }
            case "fr" -> {
                locale = Locale.forLanguageTag("fr-FR");  // Francés (Francia)
            }
            case "pt" -> {
                locale = Locale.forLanguageTag("pt-PT");  // Portugués (Portugal)
            }
            case "de" -> {
                locale = Locale.forLanguageTag("de-DE");  // Alemán (Alemania)
            }
            case "it" -> {
                locale = Locale.forLanguageTag("it-IT");  // Italiano (Italia)
            }
            case "ko" -> {
                locale = Locale.forLanguageTag("ko-KR");  // Coreano (Corea del Sur)
            }
            case "nl" -> {
                locale = Locale.forLanguageTag("nl-NL");  // Neerlandés (Países Bajos)
            }
            case "sv" -> {
                locale = Locale.forLanguageTag("sv-SE");  // Sueco (Suecia)
            }
            case "pl" -> {
                locale = Locale.forLanguageTag("pl-PL");  // Polaco (Polonia)
            }
            case "tr" -> {
                locale = Locale.forLanguageTag("tr-TR");  // Turco (Turquía)
            }
            case "id" -> {
                locale = Locale.forLanguageTag("id-ID");  // Indonesio (Indonesia)
            }
            case "vi" -> {
                locale = Locale.forLanguageTag("vi-VN");  // Vietnamita (Vietnam)
            }
            case "tl" -> {
                locale = Locale.forLanguageTag("tl-PH");  // Filipino (Filipinas)
            }
            case "th" -> {
                locale = Locale.forLanguageTag("th-TH");  // Tailandés (Tailandia)
            }
            case "el" -> {
                locale = Locale.forLanguageTag("el-GR");  // Griego (Grecia)
            }
            case "uk" -> {
                locale = Locale.forLanguageTag("uk-UA");  // Ucraniano (Ucrania)
            }
            case "sw" -> {
                locale = Locale.forLanguageTag("sw-KE");  // Kiswahili (Kenia)
            }
            case "ru" -> {
                locale = Locale.forLanguageTag("ru-RU");  // Ruso (Rusia)
            }
            case "ja" -> {
                locale = Locale.forLanguageTag("ja-JP");  // Japonés (Japón)
            }
            case "zh" -> {
                locale = Locale.forLanguageTag("zh-CN");  // Chino (China)
            }
            case "ar" -> {
                locale = Locale.forLanguageTag("ar-SA");  // Árabe (Arabia Saudita)
            }
            case "hi" -> {
                locale = Locale.forLanguageTag("hi-IN");  // Hindi (India)
            }
            case "bn" -> {
                locale = Locale.forLanguageTag("bn-BD");  // Bengalí (Bangladesh)
            }
            case "he" -> {
                locale = Locale.forLanguageTag("he-IL");  // Hebreo (Israel)
            }
            case "fa" -> {
                locale = Locale.forLanguageTag("fa-IR");  // Persa (Irán)
            }
            case "ro" -> {
                locale = Locale.forLanguageTag("ro-RO");  // Rumano (Rumanía)
            }
            case "hu" -> {
                locale = Locale.forLanguageTag("hu-HU");  // Húngaro (Hungría)
            }
            case "cs" -> {
                locale = Locale.forLanguageTag("cs-CZ");  // Checo (República Checa)
            }
            case "fi" -> {
                locale = Locale.forLanguageTag("fi-FI");  // Finés (Finlandia)
            }
            case "da" -> {
                locale = Locale.forLanguageTag("da-DK");  // Danés (Dinamarca)
            }
            case "no" -> {
                locale = Locale.forLanguageTag("no-NO");  // Noruego (Noruega)
            }
            case "sk" -> {
                locale = Locale.forLanguageTag("sk-SK");  // Eslovaco (Eslovaquia)
            }
            case "sr" -> {
                locale = Locale.forLanguageTag("sr-RS");  // Serbio (Serbia)
            }
            case "bg" -> {
                locale = Locale.forLanguageTag("bg-BG");  // Búlgaro (Bulgaria)
            }
            case "mt" -> {
                locale = Locale.forLanguageTag("mt-MT");  // Maltés (Malta)
            }
            case "lt" -> {
                locale = Locale.forLanguageTag("lt-LT");  // Lituano (Lituania)
            }
            case "lv" -> {
                locale = Locale.forLanguageTag("lv-LV");  // Letón (Letonia)
            }
            case "et" -> {
                locale = Locale.forLanguageTag("et-EE");  // Estonio (Estonia)
            }
            case "hr" -> {
                locale = Locale.forLanguageTag("hr-HR");  // Croata (Croacia)
            }
            case "mk" -> {
                locale = Locale.forLanguageTag("mk-MK");  // Macedonio (Macedonia del Norte)
            }
            case "sq" -> {
                locale = Locale.forLanguageTag("sq-AL");  // Albanés (Albania)
            }
            case "ca" -> {
                locale = Locale.forLanguageTag("ca-ES");  // Catalán (España)
            }
            case "is" -> {
                locale = Locale.forLanguageTag("is-IS");  // Islandés (Islandia)
            }
            case "ga" -> {
                locale = Locale.forLanguageTag("ga-IE");  // Irlandés (Irlanda)
            }
            case "cy" -> {
                locale = Locale.forLanguageTag("cy-GB");  // Galés (Reino Unido)
            }
            case "gl" -> {
                locale = Locale.forLanguageTag("gl-ES");  // Gallego (España)
            }
            case "bs" -> {
                locale = Locale.forLanguageTag("bs-BA");  // Bosnio (Bosnia y Herzegovina)
            }
            case "tlh" -> {
                locale = Locale.forLanguageTag("tlh");  // Klingon
            }
            case "pa" -> {
                locale = Locale.forLanguageTag("pa-IN");  // Punjabi (India)
            }
            case "gu" -> {
                locale = Locale.forLanguageTag("gu-IN");  // Gujarati (India)
            }
            case "mr" -> {
                locale = Locale.forLanguageTag("mr-IN");  // Marathi (India)
            }
            case "te" -> {
                locale = Locale.forLanguageTag("te-IN");  // Telugu (India)
            }
            case "kn" -> {
                locale = Locale.forLanguageTag("kn-IN");  // Kannada (India)
            }
            case "ml" -> {
                locale = Locale.forLanguageTag("ml-IN");  // Malayalam (India)
            }
            case "am" -> {
                locale = Locale.forLanguageTag("am-ET");  // Amárico (Etiopía)
            }
            case "ne" -> {
                locale = Locale.forLanguageTag("ne-NP");  // Nepali (Nepal)
            }
            case "si" -> {
                locale = Locale.forLanguageTag("si-LK");  // Sinhala (Sri Lanka)
            }
            case "my" -> {
                locale = Locale.forLanguageTag("my-MM");  // Birmano (Myanmar)
            }
            case "km" -> {
                locale = Locale.forLanguageTag("km-KH");  // Khmer (Camboya)
            }
            case "lo" -> {
                locale = Locale.forLanguageTag("lo-LA");  // Lao (Laos)
            }
            case "ha" -> {
                locale = Locale.forLanguageTag("ha-NG");  // Hausa (Nigeria)
            }
            case "yo" -> {
                locale = Locale.forLanguageTag("yo-NG");  // Yoruba (Nigeria)
            }
            case "zu" -> {
                locale = Locale.forLanguageTag("zu-ZA");  // Zulu (Sudáfrica)
            }
            default -> {
                locale = Locale.forLanguageTag("en-US");  // Predeterminado a Inglés (EE. UU.)
            }
        }
    }

    // Método para obtener el Locale actual
    public Locale getLocale() {
        return locale;
    }
}
