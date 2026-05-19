package com.simplecore.erp.client.i18n;

import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LanguageSelector {

    private JComboBox<String> languageSelector;

    public LanguageSelector(JComboBox<String> languageSelector) {
        this.languageSelector = languageSelector;
        setLanguageManager();
    }

    private void setLanguageManager() {
        // Collect available languages from the directory
        List<String> availableLanguages = getAvailableLanguages();
        
        // If no languages are found, add the default language from resources
        if (availableLanguages.isEmpty()) {
            availableLanguages.add(LanguageUtil.getLanguageName(getDefaultLanguage()));
        }

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
                        //languages.add(getLanguageName(languageCode));
                        languages.add(LanguageUtil.getLanguageName(languageCode));
                    }
                }
            }
        }

        // Return the list of available languages
        return languages;
    }

    private String getDefaultLanguage() {
        // Load default language from resources
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("i18/messages/messages_en_US.properties")) {
            if (input != null) {
                Properties properties = new Properties();
                properties.load(input);
                return properties.getProperty("language.default", "en_US"); // Default to English if not specified
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "en"; // Fallback default
    }

}
