
package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.modification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.tables.almacenes;


public class M05_Abrir_Almacen {

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

    public String getID_AREA_ALMACEN() {
        return ID_AREA_ALMACEN;
    }

    public String getDESCRIPCION_AREA_ALMACEN() {
        return DESCRIPCION_AREA_ALMACEN;
    }

    public String getID_COMPANIA() {
        return ID_COMPANIA;
    }

    public String getDESCRIPCION_COMPANIA() {
        return DESCRIPCION_COMPANIA;
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

    public void setID_AREA_ALMACEN(String ID_AREA_ALMACEN) {
        this.ID_AREA_ALMACEN = ID_AREA_ALMACEN;
    }

    public void setDESCRIPCION_AREA_ALMACEN(String DESCRIPCION_AREA_ALMACEN) {
        this.DESCRIPCION_AREA_ALMACEN = DESCRIPCION_AREA_ALMACEN;
    }

    public void setID_COMPANIA(String ID_COMPANIA) {
        this.ID_COMPANIA = ID_COMPANIA;
    }

    public void setDESCRIPCION_COMPANIA(String DESCRIPCION_COMPANIA) {
        this.DESCRIPCION_COMPANIA = DESCRIPCION_COMPANIA;
    }

    public String getCODIGO_ALMACEN() {
        return CODIGO_ALMACEN;
    }

    public String getTABLA_SQL() {
        return TABLA_SQL;
    }

    public void setCODIGO_ALMACEN(String CODIGO_ALMACEN) {
        this.CODIGO_ALMACEN = CODIGO_ALMACEN;
    }

    public void setTABLA_SQL(String TABLA_SQL) {
        this.TABLA_SQL = TABLA_SQL;
    }
    
    
    private String CODIGO_ALMACEN;
    private String TABLA_SQL;
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

    
    
    public void abrirAlmacen(){
        
        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;

        String query = SQLKeywords.SELECT_ALL.toSQL()
                     + getTABLA_SQL()
                     + SQLKeywords.WHERE.toSQL()
                     + almacenes.CODIGO_ALMACEN.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + getCODIGO_ALMACEN();
        
        try {

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet Datos = pSt.getResultSet();
            
            while(Datos.next()){
                
                setDESCRIPCION_ALMACEN(Datos.getString(2));
                setID_TIPO_ALMACEN(Datos.getString(3));
                setDESCRIPCION_TIPO_ALMACEN(Datos.getString(4));
                setID_CLASE_ALMACEN(Datos.getString(5));
                setDESCRIPCION_CLASE_ALMACEN(Datos.getString(6));
                setID_ESTATUS_ALMACEN(Datos.getString(7));
                setDESCRIPCION_ESTATUS(Datos.getString(8));
                setID_AREA_ALMACEN(Datos.getString(9));
                setDESCRIPCION_AREA_ALMACEN(Datos.getString(10));
                setID_COMPANIA(Datos.getString(11));
                setDESCRIPCION_COMPANIA(Datos.getString(12));
 
            }
            
            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(M05_Abrir_Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
}
