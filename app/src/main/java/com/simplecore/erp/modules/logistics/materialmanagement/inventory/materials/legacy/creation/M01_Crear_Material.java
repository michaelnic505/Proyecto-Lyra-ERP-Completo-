package com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.creation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;


public class M01_Crear_Material {

    public String getID_UM() {
        return ID_UM;
    }

    public String getDESCRIPCION_UM() {
        return DESCRIPCION_UM;
    }

    public void setID_UM(String ID_UM) {
        this.ID_UM = ID_UM;
    }

    public void setDESCRIPCION_UM(String DESCRIPCION_UM) {
        this.DESCRIPCION_UM = DESCRIPCION_UM;
    }

    public String getFECHA_FAB() {
        return FECHA_FAB;
    }

    public void setFECHA_FAB(String FECHA_FAB) {
        this.FECHA_FAB = FECHA_FAB;
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
private String  ID_UM;
private String  DESCRIPCION_UM;
private String  MARCA;
private String  MODELO;
private String  SERIE;
private String  ID_ESTATUS_MATERIAL;
private String  DESCRIPCION_ESTATUS;
private String  ID_AREA_MATERIAL;
private String  DESCRIPCION_AREA_MATERIAL;
private String FECHA_FAB;

    
    
public void Crear_Material(){
    
        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;

        String query = SQLKeywords.INSERT.toSQL()
                + DatabaseTables.Materiales.tableName()
                + SentenceValues.setValues(16);
        
        try {
            pSt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pSt.setString(1, "0");
            pSt.setString(2, getDESCRIPCION_MATERIAL());
            pSt.setString(3, getID_TIPO_MATERIAL());
            pSt.setString(4, getDESCRIPCION_TIPO_MATERIAL());
            pSt.setString(5, getID_CLASE_MATERIAL());
            pSt.setString(6, getDESCRIPCION_CLASE_MATERIAL());
            pSt.setString(7, getID_UM());
            pSt.setString(8, getDESCRIPCION_UM());
            pSt.setString(9, getMARCA());
            pSt.setString(10, getMODELO());
            pSt.setString(11, getSERIE());
            pSt.setString(12, getID_ESTATUS_MATERIAL());
            pSt.setString(13, getDESCRIPCION_ESTATUS());
            pSt.setString(14, getID_AREA_MATERIAL());
            pSt.setString(15, getDESCRIPCION_AREA_MATERIAL());
            pSt.setString(16, getFECHA_FAB());
            
            pSt.executeUpdate();            
         
            
            ResultSet Datos = pSt.getGeneratedKeys();
            
            if (Datos.next()) {

                setCODIGO_MATERIAL(Datos.getString(1));

            }
            
            pSt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(M01_Crear_Material.class.getName()).log(Level.SEVERE, null, ex);
        }


}

    
}
