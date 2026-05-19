package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar;

public enum OperationContexts {
    
    IN_OPERATION(1, 
            "En funcionamiento",
            "In Operation",
            "En fonctionnement",
            "Em operação"),
    
    OUT_OF_SERVICE(0,
            "Fuera de servicio",
            "Out of Service",
            "Hors service",
            "Fora de serviço");

    private final int value;
    private final String descriptionEs; // Spanish description
    private final String descriptionEn; // English description
    private final String descriptionFr; // French description
    private final String descriptionPt; // Portuguese description

    // Constructor to initialize all fields
    OperationContexts(int value, String descriptionEs, String descriptionEn, String descriptionFr, String descriptionPt) {
        this.value = value;
        this.descriptionEs = descriptionEs;
        this.descriptionEn = descriptionEn;
        this.descriptionFr = descriptionFr;
        this.descriptionPt = descriptionPt;
    }

    // Methods to get the values and descriptions
    public int getValue() {
        return value;
    }

    public String getDescriptionEs() {
        return descriptionEs;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public String getDescriptionFr() {
        return descriptionFr;
    }

    public String getDescriptionPt() {
        return descriptionPt;
    }

    // Method to convert a numeric value to the corresponding enum constant
    public static OperationContexts fromValue(int value) {
        for (OperationContexts state : OperationContexts.values()) {
            if (state.value == value) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

    public String getDescription(String language) {
        switch (language.toLowerCase()) {
            case "es" -> {
                return getDescriptionEs();
            }
            case "en" -> {
                return getDescriptionEn();
            }
            case "fr" -> {
                return getDescriptionFr();
            }
            case "pt" -> {
                return getDescriptionPt();
            }
            default -> {
                return getDescriptionEn();
            }
        }
    }

    public static String[] getColumnNames(String language){
        String[] columns = switch (language) {
            case "es" ->
                new String[]{"Clave", "Contexto"};
            case "en" ->
                new String[]{"Key", "Context"};
            case "fr" ->
                new String[]{"Clé", "Contexte"};
            case "pt" ->
                new String[]{"Chave", "Contexto"};
            default ->
                new String[]{"Key", "Context"};
        };
        return columns;
    }
    
    public static String getDescriptionByValue(int value, String language) {
        for (OperationContexts state : OperationContexts.values()) {
            if (state.value == value) {
                return state.getDescription(language);
            }
        }
        return null;
    }

}
