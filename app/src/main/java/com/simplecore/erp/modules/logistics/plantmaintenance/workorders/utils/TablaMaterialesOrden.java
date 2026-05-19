
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.utils;


public class TablaMaterialesOrden {

    public String getPRECIO_UNITARIO() {
        return PRECIO_UNITARIO;
    }

    public void setPRECIO_UNITARIO(String PRECIO_UNITARIO) {
        this.PRECIO_UNITARIO = PRECIO_UNITARIO;
    }

    public String getCODIGO_MATERIAL() {
        return CODIGO_MATERIAL;
    }

    public String getDESCRIPCION_MATERIAL() {
        return DESCRIPCION_MATERIAL;
    }

    public String getNUMERO_OPERACION() {
        return NUMERO_OPERACION;
    }

    public String getCANTIDAD_MATERIAL() {
        return CANTIDAD_MATERIAL;
    }

    public String getMONTO_TOTAL() {
        return MONTO_TOTAL;
    }

    public String getALMACEN() {
        return ALMACEN;
    }

    public void setCODIGO_MATERIAL(String CODIGO_MATERIAL) {
        this.CODIGO_MATERIAL = CODIGO_MATERIAL;
    }

    public void setDESCRIPCION_MATERIAL(String DESCRIPCION_MATERIAL) {
        this.DESCRIPCION_MATERIAL = DESCRIPCION_MATERIAL;
    }

    public void setNUMERO_OPERACION(String NUMERO_OPERACION) {
        this.NUMERO_OPERACION = NUMERO_OPERACION;
    }

    public void setCANTIDAD_MATERIAL(String CANTIDAD_MATERIAL) {
        this.CANTIDAD_MATERIAL = CANTIDAD_MATERIAL;
    }

    public void setMONTO_TOTAL(String MONTO_TOTAL) {
        this.MONTO_TOTAL = MONTO_TOTAL;
    }

    public void setALMACEN(String ALMACEN) {
        this.ALMACEN = ALMACEN;
    }
    
    
    private String CODIGO_MATERIAL;
    private String DESCRIPCION_MATERIAL;
    private String NUMERO_OPERACION;
    private String CANTIDAD_MATERIAL;
    private String PRECIO_UNITARIO;    
    private String MONTO_TOTAL;
    private String ALMACEN;
    
    
}
