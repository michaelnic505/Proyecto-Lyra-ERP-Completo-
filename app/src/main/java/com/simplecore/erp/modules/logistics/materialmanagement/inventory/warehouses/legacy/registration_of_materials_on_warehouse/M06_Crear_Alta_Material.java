package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.registration_of_materials_on_warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.Entre_Comillas;
import com.simplecore.erp.config.database.tables.alta_materiales_almacen;


public class M06_Crear_Alta_Material {

    public String getMARCA() {
        return MARCA;
    }

    public String getSERIE() {
        return SERIE;
    }

    public String getMODELO() {
        return MODELO;
    }

    public void setMARCA(String MARCA) {
        this.MARCA = MARCA;
    }

    public void setSERIE(String SERIE) {
        this.SERIE = SERIE;
    }

    public void setMODELO(String MODELO) {
        this.MODELO = MODELO;
    }

    public int getRESULTADO() {
        return RESULTADO;
    }

    public void setRESULTADO(int RESULTADO) {
        this.RESULTADO = RESULTADO;
    }

    public String getDESCRIPCION_UM() {
        return DESCRIPCION_UM;
    }

    public void setDESCRIPCION_UM(String DESCRIPCION_UM) {
        this.DESCRIPCION_UM = DESCRIPCION_UM;
    }

    public String getID_UM() {
        return ID_UM;
    }

    public void setID_UM(String ID_UM) {
        this.ID_UM = ID_UM;
    }

    public String getNUMERO_ALTA() {
        return NUMERO_ALTA;
    }

    public String getCODIGO_MATERIAL() {
        return CODIGO_MATERIAL;
    }

    public String getDESCRIPCION_MATERIAL() {
        return DESCRIPCION_MATERIAL;
    }

    public String getCODIGO_ALMACEN() {
        return CODIGO_ALMACEN;
    }

    public String getDESCRIPCION_ALMACEN() {
        return DESCRIPCION_ALMACEN;
    }

    public String getPRECIO_UNITARIO() {
        return PRECIO_UNITARIO;
    }

    public void setNUMERO_ALTA(String NUMERO_ALTA) {
        this.NUMERO_ALTA = NUMERO_ALTA;
    }

    public void setCODIGO_MATERIAL(String CODIGO_MATERIAL) {
        this.CODIGO_MATERIAL = CODIGO_MATERIAL;
    }

    public void setDESCRIPCION_MATERIAL(String DESCRIPCION_MATERIAL) {
        this.DESCRIPCION_MATERIAL = DESCRIPCION_MATERIAL;
    }

    public void setCODIGO_ALMACEN(String CODIGO_ALMACEN) {
        this.CODIGO_ALMACEN = CODIGO_ALMACEN;
    }

    public void setDESCRIPCION_ALMACEN(String DESCRIPCION_ALMACEN) {
        this.DESCRIPCION_ALMACEN = DESCRIPCION_ALMACEN;
    }

    public void setPRECIO_UNITARIO(String PRECIO_UNITARIO) {
        this.PRECIO_UNITARIO = PRECIO_UNITARIO;
    }


    private String NUMERO_ALTA;
    private String CODIGO_MATERIAL;
    private String DESCRIPCION_MATERIAL;
    private String CODIGO_ALMACEN;
    private String DESCRIPCION_ALMACEN;
    private String PRECIO_UNITARIO;
    private String ID_UM;
    private String DESCRIPCION_UM;
    private String MARCA;
    private String SERIE;
    private String MODELO;
    private int RESULTADO;
    

    public void crear_Alta_Material() {

        try {
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;

            String query = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.MATERIAL_WAREHOUSE_REGISTRATION.tableName()
                    + SQLKeywords.OPEN_PARENTHESIS.toSQL()
                    + alta_materiales_almacen.CODIGO_MATERIAL.toString()
                    + alta_materiales_almacen.DESCRIPCION_MATERIAL.toString()
                    + alta_materiales_almacen.CODIGO_ALMACEN.toString()
                    + alta_materiales_almacen.DESCRIPCION_ALMACEN.toString()
                    + alta_materiales_almacen.PRECIO_UNITARIO.toString()
                    + alta_materiales_almacen.ID_UM.toString()
                    + alta_materiales_almacen.DESCRIPCION_UM.toString()
                    + alta_materiales_almacen.SERIE.toString()
                    + alta_materiales_almacen.MODELO.toString()
                    + alta_materiales_almacen.MARCA.toString()
                    + SQLKeywords.CLOSE_PARENTHESIS.toSQL()
                    + SQLKeywords.SELECT.toSQL()
                    + Entre_Comillas.entreComillas(getCODIGO_MATERIAL())
                    + Entre_Comillas.entreComillas(getDESCRIPCION_MATERIAL())
                    + Entre_Comillas.entreComillas(getCODIGO_ALMACEN())
                    + Entre_Comillas.entreComillas(getDESCRIPCION_ALMACEN())
                    + Entre_Comillas.entreComillas(getPRECIO_UNITARIO())
                    + Entre_Comillas.entreComillas(getID_UM())
                    + Entre_Comillas.entreComillas(getDESCRIPCION_UM())
                    + Entre_Comillas.entreComillas(getSERIE())
                    + Entre_Comillas.entreComillas(getMODELO())
                    + Entre_Comillas.entreComillas(getMARCA())
                    + SQLKeywords.WHERE.toSQL()
                    + SQLKeywords.NOT_EXISTS.toSQL()
                    + SQLKeywords.OPEN_PARENTHESIS.toSQL()
                    + SQLKeywords.SELECT_ALL.toSQL()
                    + DatabaseTables.MATERIAL_WAREHOUSE_REGISTRATION.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + alta_materiales_almacen.CODIGO_MATERIAL.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + getCODIGO_MATERIAL()
                    + SQLKeywords.AND.toSQL()
                    + alta_materiales_almacen.CODIGO_ALMACEN.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + getCODIGO_ALMACEN()
                    + SQLKeywords.CLOSE_PARENTHESIS.toSQL();

            pSt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);            
            pSt.executeUpdate();

            ResultSet Datos = pSt.getGeneratedKeys();
            
            if (Datos.next()) {
                setNUMERO_ALTA(Datos.getString(1));
            }
           
            pSt.close();

        } catch (SQLException ex) {
            Logger.getLogger(M06_Crear_Alta_Material.class.getName()).log(Level.SEVERE, null, ex);
            setRESULTADO(ex.getErrorCode());
        }

    }

}
