
package com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.visual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.materiales;



public class M03_Ver_Material {

    public String getFECHA_FABRICACION() {
        return FECHA_FABRICACION;
    }

    public void setFECHA_FABRICACION(String FECHA_FABRICACION) {
        this.FECHA_FABRICACION = FECHA_FABRICACION;
    }

    public String getRESULTADOS() {
        return RESULTADOS;
    }

    public void setRESULTADOS(String RESULTADOS) {
        this.RESULTADOS = RESULTADOS;
    }

    public String getCODIGO_MATERIAL() {
        return CODIGO_MATERIAL;
    }

    public String getDESCRIPCION_MATERIAL() {
        return DESCRIPCION_MATERIAL;
    }

    public String getID_TIPO_MATERIAL() {
        return ID_TIPO_MATERIAL;
    }

    public String getDESCRIPCION_TIPO_MATERIAL() {
        return DESCRIPCION_TIPO_MATERIAL;
    }

    public String getID_CLASE_MATERIAL() {
        return ID_CLASE_MATERIAL;
    }

    public String getDESCRIPCION_CLASE_MATERIAL() {
        return DESCRIPCION_CLASE_MATERIAL;
    }

    public String getMARCA() {
        return MARCA;
    }

    public String getMODELO() {
        return MODELO;
    }

    public String getSERIE() {
        return SERIE;
    }

    public String getID_ESTATUS_MATERIAL() {
        return ID_ESTATUS_MATERIAL;
    }

    public String getDESCRIPCION_ESTATUS() {
        return DESCRIPCION_ESTATUS;
    }

    public String getID_AREA_MATERIAL() {
        return ID_AREA_MATERIAL;
    }

    public String getDESCRIPCION_AREA_MATERIAL() {
        return DESCRIPCION_AREA_MATERIAL;
    }

    public void setCODIGO_MATERIAL(String CODIGO_MATERIAL) {
        this.CODIGO_MATERIAL = CODIGO_MATERIAL;
    }

    public void setDESCRIPCION_MATERIAL(String DESCRIPCION_MATERIAL) {
        this.DESCRIPCION_MATERIAL = DESCRIPCION_MATERIAL;
    }

    public void setID_TIPO_MATERIAL(String ID_TIPO_MATERIAL) {
        this.ID_TIPO_MATERIAL = ID_TIPO_MATERIAL;
    }

    public void setDESCRIPCION_TIPO_MATERIAL(String DESCRIPCION_TIPO_MATERIAL) {
        this.DESCRIPCION_TIPO_MATERIAL = DESCRIPCION_TIPO_MATERIAL;
    }

    public void setID_CLASE_MATERIAL(String ID_CLASE_MATERIAL) {
        this.ID_CLASE_MATERIAL = ID_CLASE_MATERIAL;
    }

    public void setDESCRIPCION_CLASE_MATERIAL(String DESCRIPCION_CLASE_MATERIAL) {
        this.DESCRIPCION_CLASE_MATERIAL = DESCRIPCION_CLASE_MATERIAL;
    }

    public void setMARCA(String MARCA) {
        this.MARCA = MARCA;
    }

    public void setMODELO(String MODELO) {
        this.MODELO = MODELO;
    }

    public void setSERIE(String SERIE) {
        this.SERIE = SERIE;
    }

    public void setID_ESTATUS_MATERIAL(String ID_ESTATUS_MATERIAL) {
        this.ID_ESTATUS_MATERIAL = ID_ESTATUS_MATERIAL;
    }

    public void setDESCRIPCION_ESTATUS(String DESCRIPCION_ESTATUS) {
        this.DESCRIPCION_ESTATUS = DESCRIPCION_ESTATUS;
    }

    public void setID_AREA_MATERIAL(String ID_AREA_MATERIAL) {
        this.ID_AREA_MATERIAL = ID_AREA_MATERIAL;
    }

    public void setDESCRIPCION_AREA_MATERIAL(String DESCRIPCION_AREA_MATERIAL) {
        this.DESCRIPCION_AREA_MATERIAL = DESCRIPCION_AREA_MATERIAL;
    }
    
private String  CODIGO_MATERIAL;
private String  DESCRIPCION_MATERIAL;
private String  ID_TIPO_MATERIAL;
private String  DESCRIPCION_TIPO_MATERIAL;
private String  ID_CLASE_MATERIAL;
private String  DESCRIPCION_CLASE_MATERIAL;
private String  MARCA;
private String  MODELO;
private String  SERIE;
private String  ID_ESTATUS_MATERIAL;
private String  DESCRIPCION_ESTATUS;
private String  ID_AREA_MATERIAL;
private String  DESCRIPCION_AREA_MATERIAL;
private String FECHA_FABRICACION;
private String RESULTADOS;

 public void ver_Material(){
     
        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;

        String query = SQLKeywords.SELECT_ALL.toSQL()
                     + DatabaseTables.Materiales.tableName()
                     + SQLKeywords.WHERE.toSQL()
                     + materiales.CODIGO_MATERIAL.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + getCODIGO_MATERIAL();
        
        try {

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet Datos = pSt.getResultSet();
            
            while(Datos.next()){
              
              setRESULTADOS(Datos.getString(1));
              setDESCRIPCION_MATERIAL(Datos.getString(2));
              setID_TIPO_MATERIAL(Datos.getString(3));
              setDESCRIPCION_TIPO_MATERIAL(Datos.getString(4));
              setID_CLASE_MATERIAL(Datos.getString(5));
              setDESCRIPCION_CLASE_MATERIAL(Datos.getString(6));
              setMARCA(Datos.getString(7));
              setMODELO(Datos.getString(8));
              setSERIE(Datos.getString(9));
              setID_ESTATUS_MATERIAL(Datos.getString(10));
              setDESCRIPCION_ESTATUS(Datos.getString(11));
              setID_AREA_MATERIAL(Datos.getString(12));
              setDESCRIPCION_AREA_MATERIAL(Datos.getString(13));
              setFECHA_FABRICACION(Datos.getString(14));
              
            }
            
            pSt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(M03_Ver_Material.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     

 }

}
