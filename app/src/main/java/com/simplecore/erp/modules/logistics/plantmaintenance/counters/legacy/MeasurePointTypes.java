
package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;


public enum MeasurePointTypes {
    
    K("K","Lectura de medidor de flujo","flow metere reading","leitura do medidor de vazão"),
    L("L","Punto Medida Lineal","MeasPoint Linear","Ponto Medição Linear"),
    M("M","Punto Medida General","MeasPoint General","Ponto de Medição Geral"),
    U("U","Posición de Medición Única","Unique Measuring Position","Posição de Medição Única");
    
    
    private String codigo;
    private String descES;
    private String descEN;
    private String descPT;

    private MeasurePointTypes(String codigo, String descES, String descEN, String descPT) {
        this.codigo = codigo;
        this.descES = descES;
        this.descEN = descEN;
        this.descPT = descPT;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescriptionCatPM() {

        String des = "";
            des = getDescEN();

        return des;
    }
    
        public static String getTitulo() {
        
        String titulo = "";
            titulo = "Measurement Point Category";
        

        return titulo;
    }


    
    
    public String getDescES() {
        return descES;
    }

    public String getDescEN() {
        return descEN;
    }

    public String getDescPT() {
        return descPT;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescES(String descES) {
        this.descES = descES;
    }

    public void setDescEN(String descEN) {
        this.descEN = descEN;
    }

    public void setDescPT(String descPT) {
        this.descPT = descPT;
    }
    
    
    
    
}
