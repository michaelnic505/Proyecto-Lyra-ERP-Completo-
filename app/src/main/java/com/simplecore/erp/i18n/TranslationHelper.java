package com.simplecore.erp.i18n;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class TranslationHelper {

    private final Properties properties;

    // Constructor que carga las propiedades de un archivo específico según el idioma y país
    public TranslationHelper(LanguageManager languageManager, String baseFileName) {
        // Obtén el idioma y el país del Locale
        Locale locale = languageManager.getLocale();
        String language = locale.getLanguage(); // Ejemplo: "en"
        String country = locale.getCountry(); // Ejemplo: "US"
        
        // Genera el nombre del archivo basado en el idioma y país (messages_en_US.properties)
        String propertiesFile = baseFileName + "_" + language + "_" + country + ".properties";  
        this.properties = loadProperties(propertiesFile);
    }

    // Método para cargar las propiedades desde un archivo
    private Properties loadProperties(String propertiesFile) {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream(propertiesFile)) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    // Método para obtener la traducción para una clave dada
    public String getTranslation(String key) {
        String translation = properties.getProperty(key);
        if (translation == null) {
            return "Missing translation for: " + key;
        }
        return translation;
    }

    // Método estático para obtener la traducción sin crear un objeto
    public static String getTranslationStatic(LanguageManager languageManager, String baseFileName, String key) {
        String language = languageManager.getLocale().getLanguage();  // Obtén el idioma del Locale
        String propertiesFile = baseFileName + "_" + language + ".properties";  // Genera el nombre del archivo basado en el idioma
        Properties properties = loadPropertiesStatic(propertiesFile);
        String translation = properties.getProperty(key);
        if (translation == null) {
            return "Missing translation for: " + key;
        }
        return translation;
    }

    // Método estático para cargar propiedades desde un archivo
    private static Properties loadPropertiesStatic(String propertiesFile) {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream(propertiesFile)) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
