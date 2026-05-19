
package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils;



public enum StatusEquipment {
    
A("Active","Activo","Actif","Ativo","Объект"),
I("Inactive","Inactivo","Inactif","Parado","Праздный"),
S("Selled","Vendido","Vendu","Vendido","Продал"),
B("Deregistered","Baja","Déchargé","Dado de baixa","Уволен");

    public String getDescribe() {
        return describe;
    }

    public void setDescribe() {
        
            this.describe = getEn();
        
    }

    public String getEn() {
        return en;
    }

    public String getEs() {
        return es;
    }

    public String getFr() {
        return fr;
    }

    public String getPt() {
        return pt;
    }

    public String getRu() {
        return ru;
    }

private final String en;
private final String es;
private final String fr;
private final String pt;
private final String ru;
private String describe;

    private StatusEquipment(String en, String es, String fr, String pt, String ru) {
        this.en = en;
        this.es = es;
        this.fr = fr;
        this.pt = pt;
        this.ru = ru;
        
        setDescribe();
        
    }



}
