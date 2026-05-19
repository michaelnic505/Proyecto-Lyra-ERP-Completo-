package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

public enum U01_Ubicaciones_Enum {

    NIVEL_1("1"),
    NIVEL_2("2"),
    NIVEL_3("3"),
    NIVEL_4("4"),
    NIVEL_5("5"),
    NIVEL_6("6");
    
    private String nivel;

    private U01_Ubicaciones_Enum(String nivel) {    
        this.nivel = nivel;
    }

    public String getNivel() {
        return nivel;
    }
    
}
