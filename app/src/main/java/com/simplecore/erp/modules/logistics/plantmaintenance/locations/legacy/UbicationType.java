package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

public enum UbicationType {

    L("L"),
    E("E");

    String type;

    private UbicationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
    

}
