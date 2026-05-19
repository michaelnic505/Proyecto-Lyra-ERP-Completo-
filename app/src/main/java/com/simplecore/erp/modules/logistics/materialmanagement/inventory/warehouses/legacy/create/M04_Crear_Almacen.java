package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.create;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;


public class M04_Crear_Almacen {

    public String getID_COMPANIA() {
        return ID_COMPANIA;
    }

    public String getDESCRIPCION_COMPANIA() {
        return DESCRIPCION_COMPANIA;
    }

    public void setID_COMPANIA(String ID_COMPANIA) {
        this.ID_COMPANIA = ID_COMPANIA;
    }

    public void setDESCRIPCION_COMPANIA(String DESCRIPCION_COMPANIA) {
        this.DESCRIPCION_COMPANIA = DESCRIPCION_COMPANIA;
    }

    public int getCODIGO_ERROR() {
        return CODIGO_ERROR;
    }

    public void setCODIGO_ERROR(int CODIGO_ERROR) {
        this.CODIGO_ERROR = CODIGO_ERROR;
    }

    public String getID_AREA_ALMACEN() {
        return ID_AREA_ALMACEN;
    }

    public void setID_AREA_ALMACEN(String ID_AREA_ALMACEN) {
        this.ID_AREA_ALMACEN = ID_AREA_ALMACEN;
    }

    public String getCODIGO_ALMACEN() {
        return CODIGO_ALMACEN;
    }

    public String getDESCRIPCION_ALMACEN() {
        return DESCRIPCION_ALMACEN;
    }

    public String getID_TIPO_ALMACEN() {
        return ID_TIPO_ALMACEN;
    }

    public String getDESCRIPCION_TIPO_ALMACEN() {
        return DESCRIPCION_TIPO_ALMACEN;
    }

    public String getID_CLASE_ALMACEN() {
        return ID_CLASE_ALMACEN;
    }

    public String getDESCRIPCION_CLASE_ALMACEN() {
        return DESCRIPCION_CLASE_ALMACEN;
    }

    public String getID_ESTATUS_ALMACEN() {
        return ID_ESTATUS_ALMACEN;
    }

    public String getDESCRIPCION_ESTATUS() {
        return DESCRIPCION_ESTATUS;
    }

    public String getDESCRIPCION_AREA_ALMACEN() {
        return DESCRIPCION_AREA_ALMACEN;
    }

    public void setCODIGO_ALMACEN(String CODIGO_ALMACEN) {
        this.CODIGO_ALMACEN = CODIGO_ALMACEN;
    }

    public void setDESCRIPCION_ALMACEN(String DESCRIPCION_ALMACEN) {
        this.DESCRIPCION_ALMACEN = DESCRIPCION_ALMACEN;
    }

    public void setID_TIPO_ALMACEN(String ID_TIPO_ALMACEN) {
        this.ID_TIPO_ALMACEN = ID_TIPO_ALMACEN;
    }

    public void setDESCRIPCION_TIPO_ALMACEN(String DESCRIPCION_TIPO_ALMACEN) {
        this.DESCRIPCION_TIPO_ALMACEN = DESCRIPCION_TIPO_ALMACEN;
    }

    public void setID_CLASE_ALMACEN(String ID_CLASE_ALMACEN) {
        this.ID_CLASE_ALMACEN = ID_CLASE_ALMACEN;
    }

    public void setDESCRIPCION_CLASE_ALMACEN(String DESCRIPCION_CLASE_ALMACEN) {
        this.DESCRIPCION_CLASE_ALMACEN = DESCRIPCION_CLASE_ALMACEN;
    }

    public void setID_ESTATUS_ALMACEN(String ID_ESTATUS_ALMACEN) {
        this.ID_ESTATUS_ALMACEN = ID_ESTATUS_ALMACEN;
    }

    public void setDESCRIPCION_ESTATUS(String DESCRIPCION_ESTATUS) {
        this.DESCRIPCION_ESTATUS = DESCRIPCION_ESTATUS;
    }



    public void setDESCRIPCION_AREA_ALMACEN(String DESCRIPCION_AREA_ALMACEN) {
        this.DESCRIPCION_AREA_ALMACEN = DESCRIPCION_AREA_ALMACEN;
    }


    
private String CODIGO_ALMACEN;
private String DESCRIPCION_ALMACEN;
private String ID_TIPO_ALMACEN;
private String DESCRIPCION_TIPO_ALMACEN;
private String ID_CLASE_ALMACEN;
private String DESCRIPCION_CLASE_ALMACEN;
private String ID_ESTATUS_ALMACEN;
private String DESCRIPCION_ESTATUS;
private String ID_AREA_ALMACEN;
private String DESCRIPCION_AREA_ALMACEN;
private String ID_COMPANIA;
private String DESCRIPCION_COMPANIA;
private int CODIGO_ERROR;
    
    
public void crear_Almacen(){

    Connection conexion = PooledConnectionService.getConnection();
    PreparedStatement pSt = null;

    String query = SQLKeywords.INSERT.toSQL()
            + DatabaseTables.Almacenes.tableName()
            + SentenceValues.setValues(12);

    try {

        pSt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pSt.setString(1, getCODIGO_ALMACEN());
        pSt.setString(2, getDESCRIPCION_ALMACEN());
        pSt.setString(3, getID_TIPO_ALMACEN());
        pSt.setString(4, getDESCRIPCION_TIPO_ALMACEN());
        pSt.setString(5, getID_CLASE_ALMACEN());
        pSt.setString(6, getDESCRIPCION_CLASE_ALMACEN());
        pSt.setString(7, getID_ESTATUS_ALMACEN());
        pSt.setString(8, getDESCRIPCION_ESTATUS());
        pSt.setString(9, getID_AREA_ALMACEN());
        pSt.setString(10, getDESCRIPCION_AREA_ALMACEN());
        pSt.setString(11, getID_COMPANIA());
        pSt.setString(12, getDESCRIPCION_COMPANIA());

        pSt.executeUpdate();
        pSt.close();

        
    } catch (SQLException ex) {
        Logger.getLogger(M04_Crear_Almacen.class.getName()).log(Level.SEVERE, null, ex);
        setCODIGO_ERROR(ex.getErrorCode());
    }


    
    
}
    
    
}
