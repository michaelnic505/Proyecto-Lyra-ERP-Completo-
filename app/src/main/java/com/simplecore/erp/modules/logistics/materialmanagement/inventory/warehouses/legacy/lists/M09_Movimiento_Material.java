
package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.lists;

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


public class M09_Movimiento_Material {

    public String getCENTRO_COSTO() {
        return CENTRO_COSTO;
    }

    public String getDESCRIPCION_CENTRO_COSTO() {
        return DESCRIPCION_CENTRO_COSTO;
    }

    public void setCENTRO_COSTO(String CENTRO_COSTO) {
        this.CENTRO_COSTO = CENTRO_COSTO;
    }

    public void setDESCRIPCION_CENTRO_COSTO(String DESCRIPCION_CENTRO_COSTO) {
        this.DESCRIPCION_CENTRO_COSTO = DESCRIPCION_CENTRO_COSTO;
    }

    public String getMARCA() {
        return MARCA;
    }

    public void setMARCA(String MARCA) {
        this.MARCA = MARCA;
    }

    public String getID_TIPO_DOCUMENTO() {
        return ID_TIPO_DOCUMENTO;
    }

    public void setID_TIPO_DOCUMENTO(String ID_TIPO_DOCUMENTO) {
        this.ID_TIPO_DOCUMENTO = ID_TIPO_DOCUMENTO;
    }

    public double getPRECIO_UNITARIO() {
        return PRECIO_UNITARIO;
    }

    public void setPRECIO_UNITARIO(double PRECIO_UNITARIO) {
        this.PRECIO_UNITARIO = PRECIO_UNITARIO;
    }

    public double getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(double CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public String getNo_DOCUMENTO() {
        return No_DOCUMENTO;
    }

    public String getTIPO_DOCUMENTO() {
        return TIPO_DOCUMENTO;
    }

    public String getCODIGO_ALMACEN() {
        return CODIGO_ALMACEN;
    }

    public String getDESCRIPCION_ALMACEN() {
        return DESCRIPCION_ALMACEN;
    }

    public String getOBSERVACIONES() {
        return OBSERVACIONES;
    }

    public String getCODIGO_MATERIAL() {
        return CODIGO_MATERIAL;
    }

    public String getDESCRIPCION_MATERIAL() {
        return DESCRIPCION_MATERIAL;
    }

    public String getID_UM() {
        return ID_UM;
    }

    public String getDESCRIPCION_UM() {
        return DESCRIPCION_UM;
    }

    public String getMODELO() {
        return MODELO;
    }

    public String getSERIE() {
        return SERIE;
    }

    public void setNo_DOCUMENTO(String No_DOCUMENTO) {
        this.No_DOCUMENTO = No_DOCUMENTO;
    }

    public void setTIPO_DOCUMENTO(String TIPO_DOCUMENTO) {
        this.TIPO_DOCUMENTO = TIPO_DOCUMENTO;
    }

    public void setCODIGO_ALMACEN(String CODIGO_ALMACEN) {
        this.CODIGO_ALMACEN = CODIGO_ALMACEN;
    }

    public void setDESCRIPCION_ALMACEN(String DESCRIPCION_ALMACEN) {
        this.DESCRIPCION_ALMACEN = DESCRIPCION_ALMACEN;
    }

    public void setOBSERVACIONES(String OBSERVACIONES) {
        this.OBSERVACIONES = OBSERVACIONES;
    }

    public void setCODIGO_MATERIAL(String CODIGO_MATERIAL) {
        this.CODIGO_MATERIAL = CODIGO_MATERIAL;
    }

    public void setDESCRIPCION_MATERIAL(String DESCRIPCION_MATERIAL) {
        this.DESCRIPCION_MATERIAL = DESCRIPCION_MATERIAL;
    }

    public void setID_UM(String ID_UM) {
        this.ID_UM = ID_UM;
    }

    public void setDESCRIPCION_UM(String DESCRIPCION_UM) {
        this.DESCRIPCION_UM = DESCRIPCION_UM;
    }

    public void setMODELO(String MODELO) {
        this.MODELO = MODELO;
    }

    public void setSERIE(String SERIE) {
        this.SERIE = SERIE;
    }
 
    private String No_DOCUMENTO;
    private String ID_TIPO_DOCUMENTO;
    private String TIPO_DOCUMENTO;
    private double CANTIDAD;
    private double PRECIO_UNITARIO;
    private String CODIGO_ALMACEN;
    private String DESCRIPCION_ALMACEN;
    private String OBSERVACIONES;
    private String CODIGO_MATERIAL;
    private String DESCRIPCION_MATERIAL;
    private String ID_UM;
    private String DESCRIPCION_UM;
    private String MODELO;
    private String SERIE;
    private String MARCA;
    private String CENTRO_COSTO;
    private String DESCRIPCION_CENTRO_COSTO;

    public void crearMovimiento(){
        
        try {
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;

            String query = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.Movimientos_material.tableName()
                    + SentenceValues.setValues(17);
            
            pSt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pSt.setString(1, "0");
            pSt.setString(2, getID_TIPO_DOCUMENTO());
            pSt.setString(3, getTIPO_DOCUMENTO());
            pSt.setDouble(4, getCANTIDAD());
            pSt.setDouble(5, getPRECIO_UNITARIO());
            pSt.setString(6, getCODIGO_ALMACEN());
            pSt.setString(7, getDESCRIPCION_ALMACEN());
            pSt.setString(8, getOBSERVACIONES());
            pSt.setString(9, getCODIGO_MATERIAL());
            pSt.setString(10, getDESCRIPCION_MATERIAL());
            pSt.setString(11, getID_UM());
            pSt.setString(12, getDESCRIPCION_UM());
            pSt.setString(13, getMODELO());
            pSt.setString(14, getSERIE());
            pSt.setString(15, getMARCA());
            pSt.setString(16, getCENTRO_COSTO());
            pSt.setString(17, getDESCRIPCION_CENTRO_COSTO());

            pSt.executeUpdate();

            ResultSet Datos = pSt.getGeneratedKeys();
            
            if(Datos.next()){             
                setNo_DOCUMENTO(Datos.getString(1));
            }
            
            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(M09_Movimiento_Material.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
}
