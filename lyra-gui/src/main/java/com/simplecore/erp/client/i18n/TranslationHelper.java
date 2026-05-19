package com.simplecore.erp.client.i18n;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class TranslationHelper implements Translator {

    private final Properties properties;
    private final TranslatorType translatorType;
    
    private static final Map<TranslatorType, String> DEFAULT_TRANSLATOR = new HashMap<>();

    static {
        DEFAULT_TRANSLATOR.put(TranslatorType.MESSAGES, "i18n.messages.messages");
        DEFAULT_TRANSLATOR.put(TranslatorType.MAIN_MENU, "i18n.mainmenu.mainmenus");
        DEFAULT_TRANSLATOR.put(TranslatorType.NOTIFICATIONS, "i18n.notifications.notifications");
        DEFAULT_TRANSLATOR.put(TranslatorType.TABLES, "i18n.tables.tables");
        DEFAULT_TRANSLATOR.put(TranslatorType.TREE, "i18n.maintree.maintree");
        DEFAULT_TRANSLATOR.put(TranslatorType.COUNTRIES, "i18n.countries.countries");
    }
    
    private static final Map<TranslatorType, String> DYNAMIC_LANGUAGES = new HashMap<>();
    static{
        DYNAMIC_LANGUAGES.put(TranslatorType.MESSAGES, "config/i18n/messages/messages");
        DYNAMIC_LANGUAGES.put(TranslatorType.MAIN_MENU, "config/i18n/mainmenu/mainmenus");
        DYNAMIC_LANGUAGES.put(TranslatorType.NOTIFICATIONS, "config/i18n/notifications/notifications");
        DYNAMIC_LANGUAGES.put(TranslatorType.TABLES, "config/i18n/tables/tables");
        DYNAMIC_LANGUAGES.put(TranslatorType.TREE, "config/i18n/maintree/maintree");
        DYNAMIC_LANGUAGES.put(TranslatorType.COUNTRIES, "config/i18n/countries/countries");
    }
    
    private String getBaseFileName(TranslatorType type){
        return DYNAMIC_LANGUAGES.get(type);
    }
    
    // Constructor que carga las propiedades de un archivo específico según el idioma y país
    public TranslationHelper(LanguageManager languageManager,TranslatorType translatorType) {
        this.translatorType = translatorType;
        this.properties = new Properties();
        // Obtén el idioma y el país del Locale
        Locale locale = languageManager.getLocale();
        String language = locale.getLanguage(); // Ejemplo: "en"
        String country = locale.getCountry(); // Ejemplo: "US"
        // Genera el nombre del archivo basado en el idioma y país (messages_en_US.properties)
        String baseFileName = getBaseFileName(translatorType);
        String propertiesFile = baseFileName + "_" + language + "_" + country + ".properties";
        loadProperties(propertiesFile);
        // Si no se encontró el archivo, intenta con solo el idioma
        if (this.properties.isEmpty()) {
            propertiesFile = baseFileName + "_" + language + ".properties";
            loadProperties(propertiesFile);
        }
        // Si aún no hay propiedades, usa el idioma por defecto con ResourceBundle
        if (this.properties.isEmpty()) {
            System.out.println("[WARNING] Using default language: " + locale);
            loadDefaultResourceBundle(locale);
        }
    }

    // Método para cargar las propiedades desde un archivo externo
    private void loadProperties(String propertiesFile) {
        try (FileInputStream input = new FileInputStream(propertiesFile)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("[WARNING] Properties file not found: " + propertiesFile);
        }
    }

// Método para cargar propiedades desde ResourceBundle
    private void loadDefaultResourceBundle(Locale locale) {
        String baseName = DEFAULT_TRANSLATOR.get(translatorType);
        if (baseName != null) {
            try {
                ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
                bundle.keySet().forEach(key -> properties.setProperty(key, bundle.getString(key)));
            } catch (MissingResourceException e) {
                System.out.println("[ERROR] Missing default resource bundle: " + baseName);
            }
        }
    }

    // Método para obtener la traducción para una clave dada
    public String getTranslation(String key) {
        return properties.getProperty(key, "Missing translation for: " + key);
    }

    @Override
    public TranslatorType getType() {
        return translatorType;
    }
}
