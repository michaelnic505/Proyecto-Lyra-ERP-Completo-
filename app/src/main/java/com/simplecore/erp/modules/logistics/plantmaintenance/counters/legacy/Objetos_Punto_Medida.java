
package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;



public enum Objetos_Punto_Medida {
    
    IBD("IBD","RE: Edificio","RE: Building","RE: Construção"),
    IEQ("IEQ","Equipo","Equipment","Equipamento"),
    IFL("IFL","Puesto técnico","Technical position","Posição técnica"),
    IME("IME","RE: Unidad de alquiler","RE: Rental unit","RE: Unidade de aluguel");
    
    String code;
    private String objetoEs;
    private String objetoEn;
    private String objetoPt;

    
    
    private Objetos_Punto_Medida(String code, String objetoEs, String objetoEn, String objetoPt) {
        this.code = code;
        this.objetoEs = objetoEs;
        this.objetoEn = objetoEn;
        this.objetoPt = objetoPt;
    }

    public String getCode() {
        return code;
    }

    public String getDescOPM() {

        String des = "";
            des = getObjetoEn();
   
        
        return des;
    }

    public static String getTitulo() {
        
        String titulo = "";
        
            titulo = "Measurement Point Objects";

        return titulo;
    }

    
    
    
    
    
    
    
    
    public String getObjetoEs() {
        return objetoEs;
    }

    public String getObjetoEn() {
        return objetoEn;
    }

    public String getObjetoPt() {
        return objetoPt;
    }

    public void setObjetoEs(String objetoEs) {
        this.objetoEs = objetoEs;
    }

    public void setObjetoEn(String objetoEn) {
        this.objetoEn = objetoEn;
    }

    public void setObjetoPt(String objetoPt) {
        this.objetoPt = objetoPt;
    }



    
    
    
}
