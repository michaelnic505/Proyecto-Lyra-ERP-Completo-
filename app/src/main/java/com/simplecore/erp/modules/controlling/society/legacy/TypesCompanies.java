
package com.simplecore.erp.modules.controlling.society.legacy;


public enum TypesCompanies {
    
    CO("Corporative"),
    FI("Financial");
    
    String type;

    private TypesCompanies(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
    
    
}
