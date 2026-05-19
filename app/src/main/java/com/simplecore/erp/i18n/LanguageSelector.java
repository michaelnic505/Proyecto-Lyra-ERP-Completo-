package com.simplecore.erp.i18n;

import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class LanguageSelector {

    private JComboBox<String> languageSelector;

    public LanguageSelector(JComboBox<String> languageSelector) {
        this.languageSelector = languageSelector;
        setLanguageManager();
    }

    private void setLanguageManager() {
        // Collect available languages from the directory
        List<String> availableLanguages = getAvailableLanguages();

        // Convert the language list to an array to set it in the ComboBox
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(availableLanguages.toArray(new String[0]));
        languageSelector.setModel(model);
        
    }


    private List<String> getAvailableLanguages() {
        List<String> languages = new ArrayList<>();

        // Get the directory where the language files are stored
      //  String projectPath = System.getProperty("user.dir");  // Get the project directory
        File directory = new File("config/i18n/messages");

        // Check if the directory exists
        if (directory.exists() && directory.isDirectory()) {
            // Filter for .properties files
            File[] files = directory.listFiles((FilenameFilter) (dir, name) -> name.endsWith(".properties"));

            // Process each file and extract the language code
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName();
                    if (fileName.startsWith("messages_")) {
                        // Extract the language code (e.g., "en", "es", etc.)
                        String languageCode = fileName.split("_")[1].split("\\.")[0];
                        // Add the language to the list
                        languages.add(getLanguageName(languageCode));
                    }
                }
            }
        }

        // Return the list of available languages
        return languages;
    }

    private String getLanguageName(String languageCode) {
        switch (languageCode) {
            case "en":
                return "English";
            case "es":
                return "Español";
            case "fr":
                return "Français";
            case "pt":
                return "Português";
            case "de":
                return "Deutsch";
            case "it":
                return "Italiano";
            case "ko":
                return "한국어";
            case "nl":
                return "Nederlands";
            case "sv":
                return "Svenska";
            case "pl":
                return "Polski";
            case "tr":
                return "Türkçe";
            case "id":
                return "Bahasa Indonesia";
            case "vi":
                return "Tiếng Việt";
            case "tl":
                return "Filipino";
            case "th":
                return "ไทย";
            case "el":
                return "Ελληνικά";
            case "uk":
                return "Українська";
            case "sw":
                return "Kiswahili";
            case "ru":
                return "Русский";
            case "ja":
                return "日本語";
            case "zh":
                return "中文";
            case "ar":
                return "العربية";
            case "hi":
                return "हिन्दी";
            case "bn":
                return "বাংলা";
            case "he":
                return "עברית";
            case "fa":
                return "فارسی";
            case "ro":
                return "Română";
            case "hu":
                return "Magyar";
            case "cs":
                return "Čeština";
            case "fi":
                return "Suomi";
            case "da":
                return "Dansk";
            case "no":
                return "Norsk";
            case "sk":
                return "Slovenčina";
            case "sr":
                return "Srpski";
            case "bg":
                return "Български";
            case "mt":
                return "Malti";
            case "lt":
                return "Lietuvių";
            case "lv":
                return "Latviešu";
            case "et":
                return "Eesti";
            case "hr":
                return "Hrvatski";
            case "mk":
                return "Македонски";
            case "sq":
                return "Shqip";
            case "ca":
                return "Català";
            case "is":
                return "Íslenska";
            case "ga":
                return "Gaeilge";
            case "cy":
                return "Cymraeg";
            case "gl":
                return "Galego";
            case "bs":
                return "Bosanski";
            case "tlh":
                return "tlhIngan Hol"; // Klingon
            case "pa":
                return "ਪੰਜਾਬੀ"; // Punjabi
            case "gu":
                return "ગુજરાતી"; // Gujarati
            case "mr":
                return "मराठी"; // Marathi
            case "te":
                return "తెలుగు"; // Telugu
            case "kn":
                return "ಕನ್ನಡ"; // Kannada
            case "ml":
                return "മലയാളം"; // Malayalam
            case "am":
                return "አማርኛ"; // Amharic
            case "ne":
                return "नेपाली"; // Nepali
            case "si":
                return "සිංහල"; // Sinhala
            case "my":
                return "မြန်မာဘာသာ"; // Burmese
            case "km":
                return "ភាសាខ្មែរ"; // Khmer
            case "lo":
                return "ລາວ"; // Lao
            case "ha":
                return "Hausa"; // Hausa
            case "yo":
                return "Yoruba"; // Yoruba
            case "zu":
                return "Zulu"; // Zulu
            default:
                return "Unknown";
        }
    }

    public String getLanguageCode(String languageName) {
        switch (languageName) {
            case "English":
                return "en";  // English
            case "Español":
                return "es";  // Spanish
            case "Français":
                return "fr";  // French
            case "Português":
                return "pt";  // Portuguese
            case "Deutsch":
                return "de";  // German
            case "Italiano":
                return "it";  // Italian
            case "한국어":
                return "ko";  // Korean
            case "Nederlands":
                return "nl";  // Dutch
            case "Svenska":
                return "sv";  // Swedish
            case "Polski":
                return "pl";  // Polish
            case "Türkçe":
                return "tr";  // Turkish
            case "Bahasa Indonesia":
                return "id";  // Indonesian
            case "Tiếng Việt":
                return "vi";  // Vietnamese
            case "Filipino":
                return "tl";  // Filipino
            case "ไทย":
                return "th";  // Thai
            case "Ελληνικά":
                return "el";  // Greek
            case "Українська":
                return "uk";  // Ukrainian
            case "Kiswahili":
                return "sw";  // Swahili
            case "Русский":
                return "ru";  // Russian
            case "日本語":
                return "ja";  // Japanese
            case "中文":
                return "zh";  // Chinese
            case "العربية":
                return "ar";  // Arabic
            case "हिन्दी":
                return "hi";  // Hindi
            case "বাংলা":
                return "bn";  // Bengali
            case "עברית":
                return "he";  // Hebrew
            case "فارسی":
                return "fa";  // Persian
            case "Română":
                return "ro";  // Romanian
            case "Magyar":
                return "hu";  // Hungarian
            case "Čeština":
                return "cs";  // Czech
            case "Suomi":
                return "fi";  // Finnish
            case "Dansk":
                return "da";  // Danish
            case "Norsk":
                return "no";  // Norwegian
            case "Slovenčina":
                return "sk";  // Slovak
            case "Srpski":
                return "sr";  // Serbian
            case "Български":
                return "bg";  // Bulgarian
            case "Maltese":
                return "mt";  // Maltese
            case "Lietuvių":
                return "lt";  // Lithuanian
            case "Latviešu":
                return "lv";  // Latvian
            case "Eesti":
                return "et";  // Estonian
            case "Hrvatski":
                return "hr";  // Croatian
            case "Македонски":
                return "mk";  // Macedonian
            case "Shqip":
                return "sq";  // Albanian
            case "Català":
                return "ca";  // Catalan
            case "Íslenska":
                return "is";  // Icelandic
            case "Gaeilge":
                return "ga";  // Irish
            case "Cymraeg":
                return "cy";  // Welsh
            case "Galego":
                return "gl";  // Galician
            case "Bosanski":
                return "bs";  // Bosnian
            case "tlh":
                return "tlh";  // Klingon
            case "ਪੰਜਾਬੀ":
                return "pa";  // Punjabi
            case "ગુજરાતી":
                return "gu";  // Gujarati
            case "मराठी":
                return "mr";  // Marathi
            case "తెలుగు":
                return "te";  // Telugu
            case "ಕನ್ನಡ":
                return "kn";  // Kannada
            case "മലയാളം":
                return "ml";  // Malayalam
            case "አማርኛ":
                return "am";  // Amharic
            case "नेपाली":
                return "ne";  // Nepali
            case "සිංහල":
                return "si";  // Sinhala
            case "မြန်မာ":
                return "my";  // Burmese
            case "ខ្មែរ":
                return "km";  // Khmer
            case "ລາວ":
                return "lo";  // Lao
            case "Hausa":
                return "ha";  // Hausa
            case "Yoruba":
                return "yo";  // Yoruba
            case "Zulu":
                return "zu";  // Zulu
            default:
                return "unknown";  // Unknown
        }
    }

}
