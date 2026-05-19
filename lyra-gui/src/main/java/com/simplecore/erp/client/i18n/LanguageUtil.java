
package com.simplecore.erp.client.i18n;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import java.util.HashMap;
import java.util.Map;

public class LanguageUtil {
    
    private static final Map<String, String> languageMap = new HashMap<>();
    private static final Map<String, String> reverseLanguageMap = new HashMap<>();

    static {
        languageMap.put("en", "English");
        languageMap.put("es", "Español");
        languageMap.put("fr", "Français");
        languageMap.put("pt", "Português");
        languageMap.put("de", "Deutsch");
        languageMap.put("it", "Italiano");
        languageMap.put("ko", "한국어");
        languageMap.put("nl", "Nederlands");
        languageMap.put("sv", "Svenska");
        languageMap.put("pl", "Polski");
        languageMap.put("tr", "Türkçe");
        languageMap.put("id", "Bahasa Indonesia");
        languageMap.put("vi", "Tiếng Việt");
        languageMap.put("tl", "Filipino");
        languageMap.put("th", "ไทย");
        languageMap.put("el", "Ελληνικά");
        languageMap.put("uk", "Українська");
        languageMap.put("sw", "Kiswahili");
        languageMap.put("ru", "Русский");
        languageMap.put("ja", "日本語");
        languageMap.put("zh", "中文");
        languageMap.put("ar", "العربية");
        languageMap.put("hi", "हिन्दी");
        languageMap.put("bn", "বাংলা");
        languageMap.put("he", "עברית");
        languageMap.put("fa", "فارسی");
        languageMap.put("ro", "Română");
        languageMap.put("hu", "Magyar");
        languageMap.put("cs", "Čeština");
        languageMap.put("fi", "Suomi");
        languageMap.put("da", "Dansk");
        languageMap.put("no", "Norsk");
        languageMap.put("sk", "Slovenčina");
        languageMap.put("sr", "Srpski");
        languageMap.put("bg", "Български");
        languageMap.put("mt", "Malti");
        languageMap.put("lt", "Lietuvių");
        languageMap.put("lv", "Latviešu");
        languageMap.put("et", "Eesti");
        languageMap.put("hr", "Hrvatski");
        languageMap.put("mk", "Македонски");
        languageMap.put("sq", "Shqip");
        languageMap.put("ca", "Català");
        languageMap.put("is", "Íslenska");
        languageMap.put("ga", "Gaeilge");
        languageMap.put("cy", "Cymraeg");
        languageMap.put("gl", "Galego");
        languageMap.put("bs", "Bosanski");
        languageMap.put("tlh", "tlhIngan Hol"); // Klingon
        languageMap.put("pa", "ਪੰਜਾਬੀ"); // Punjabi
        languageMap.put("gu", "ગુજરાતી"); // Gujarati
        languageMap.put("mr", "मराठी"); // Marathi
        languageMap.put("te", "తెలుగు"); // Telugu
        languageMap.put("kn", "ಕನ್ನಡ"); // Kannada
        languageMap.put("ml", "മലയാളം"); // Malayalam
        languageMap.put("am", "አማርኛ"); // Amharic
        languageMap.put("ne", "नेपाली"); // Nepali
        languageMap.put("si", "සිංහල"); // Sinhala
        languageMap.put("my", "မြန်မာဘာသာ"); // Burmese
        languageMap.put("km", "ភាសាខ្មែរ"); // Khmer
        languageMap.put("lo", "ລາວ"); // Lao
        languageMap.put("ha", "Hausa"); // Hausa
        languageMap.put("yo", "Yoruba"); // Yoruba
        languageMap.put("zu", "Zulu"); // Zulu
        
           
        // Construir el mapa inverso
        for (Map.Entry<String, String> entry : languageMap.entrySet()) {
            reverseLanguageMap.put(entry.getValue(), entry.getKey());
        }
    }

    public static String getLanguageName(String languageCode) {
        return languageMap.getOrDefault(languageCode, "Unknown");
    }

    public static String getLanguageCode(String languageName) {
        return reverseLanguageMap.getOrDefault(languageName, "unknown");
    }
}
