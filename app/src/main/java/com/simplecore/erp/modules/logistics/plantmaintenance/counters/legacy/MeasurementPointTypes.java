package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;


public class MeasurementPointTypes {

    private static String[] typeCodes = {
        "K",
        "L",
        "M",
        "U"
    };

    private static String[] codeDescription_ES = {
        "Lectura de medidor de flujo",
        "Punto Medida Lineal",
        "Punto Medida General",
        "Posición de Medición Única"};

    private static String[] codeDescription_EN = {
        "Flow metere reading",
        "MeasPoint Linear",
        "MeasPoint General",
        "Unique Measuring Position"};

    public static String getTypeCodeByIndex(int index) {
        return typeCodes[index];
    }

    public static String getDescriptionCodeByIndex(int index) {

        String description = "";
        description = codeDescription_EN[index];

        return description;
    }

    public static String getDescriptionCodeByCode(String code) {

        int index = -1;

        for (int i = 0; i < typeCodes.length; i++) {

            if (typeCodes[i].equals(code)) {
                index = i;
                break;
            }

        }

        String description = "";
        description = codeDescription_EN[index];

        return description;

    }

}
