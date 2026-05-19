package com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.modification;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.materiales;


public class M02_Modificar_Material {

    public String getFECHA_FABRICACION() {
        return FECHA_FABRICACION;
    }

    public void setFECHA_FABRICACION(String FECHA_FABRICACION) {
        this.FECHA_FABRICACION = FECHA_FABRICACION;
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

    
    
public void modificar_Material(){
    
        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;

        String query = SQLKeywords.UPDATE.toSQL()
                     + DatabaseTables.Materiales.tableName()
                     + SQLKeywords.SET.toSQL()
                     + materiales.DESCRIPCION_MATERIAL.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.QUESTION_MARK.toSQL()
                     + materiales.ID_TIPO_MATERIAL.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.QUESTION_MARK.toSQL()
                     + materiales.DESCRIPCION_TIPO_MATERIAL.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.QUESTION_MARK.toSQL()
                     + materiales.ID_CLASE_MATERIAL.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.QUESTION_MARK.toSQL()
                     + materiales.DESCRIPCION_CLASE_MATERIAL.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.QUESTION_MARK.toSQL()
                     + materiales.MARCA.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.QUESTION_MARK.toSQL()
                     + materiales.MODELO.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.QUESTION_MARK.toSQL()
                     + materiales.SERIE.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.QUESTION_MARK.toSQL()
                     + materiales.ID_ESTATUS_MATERIAL.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.QUESTION_MARK.toSQL()
                     + materiales.DESCRIPCION_ESTATUS.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.QUESTION_MARK.toSQL()
                     + materiales.ID_AREA_MATERIAL.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.QUESTION_MARK.toSQL()
                     + materiales.DESCRIPCION_AREA_MATERIAL.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.QUESTION_MARK.toSQL()
                     + materiales.FECHA_FAB.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.QUESTION_MARK.toSQL()
                     + SQLKeywords.WHERE.toSQL()
                     + materiales.CODIGO_MATERIAL.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + getCODIGO_MATERIAL()
                ;

        try {
            pSt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            pSt.setString(1, getDESCRIPCION_MATERIAL());
            pSt.setString(2, getID_TIPO_MATERIAL());
            pSt.setString(3, getDESCRIPCION_TIPO_MATERIAL());
            pSt.setString(4, getID_CLASE_MATERIAL());
            pSt.setString(5, getDESCRIPCION_CLASE_MATERIAL());
            pSt.setString(6, getMARCA());
            pSt.setString(7, getMODELO());
            pSt.setString(8, getSERIE());
            pSt.setString(9, getID_ESTATUS_MATERIAL());
            pSt.setString(10, getDESCRIPCION_ESTATUS());
            pSt.setString(11, getID_AREA_MATERIAL());
            pSt.setString(12, getDESCRIPCION_AREA_MATERIAL());
            pSt.setString(13,getFECHA_FABRICACION());
            
            pSt.executeUpdate();            
            pSt.close();
                           
            
        } catch (SQLException ex) {
            Logger.getLogger(M02_Modificar_Material.class.getName()).log(Level.SEVERE, null, ex);
        }


}

    
}
