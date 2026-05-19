
package com.simplecore.erp.modules.controlling.society.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;


public class C01_Crear_Compania {

    public String getMatriz() {
        return matriz;
    }

    public String getDenominacionMatriz() {
        return denominacionMatriz;
    }

    public void setMatriz(String matriz) {
        this.matriz = matriz;
    }

    public void setDenominacionMatriz(String denominacionMatriz) {
        this.denominacionMatriz = denominacionMatriz;
    }

    public String getTIPO() {
        return TIPO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }


    public int getCODIGO_ERROR() {
        return CODIGO_ERROR;
    }

    public void setCODIGO_ERROR(int CODIGO_ERROR) {
        this.CODIGO_ERROR = CODIGO_ERROR;
    }


    public String getID_EMPRESA() {
        return ID_EMPRESA;
    }


    public void setID_EMPRESA(String ID_EMPRESA) {
        this.ID_EMPRESA = ID_EMPRESA;
    }


    public String getDESCRIPCION_EMPRESA() {
        return DESCRIPCION_EMPRESA;
    }


    public void setDESCRIPCION_EMPRESA(String DESCRIPCION_EMPRESA) {
        this.DESCRIPCION_EMPRESA = DESCRIPCION_EMPRESA;
    }


    public String getID_PAIS() {
        return ID_PAIS;
    }


    public void setID_PAIS(String ID_PAIS) {
        this.ID_PAIS = ID_PAIS;
    }


    public String getDESCRIPCION_PAIS() {
        return DESCRIPCION_PAIS;
    }


    public void setDESCRIPCION_PAIS(String DESCRIPCION_PAIS) {
        this.DESCRIPCION_PAIS = DESCRIPCION_PAIS;
    }


    public String getTABLA_SQL() {
        return TABLA_SQL;
    }


    public void setTABLA_SQL(String TABLA_SQL) {
        this.TABLA_SQL = TABLA_SQL;
    }
    
    private String TABLA_SQL;
    private String ID_EMPRESA;
    private String DESCRIPCION_EMPRESA;
    private String ID_PAIS;
    private String DESCRIPCION_PAIS;
    private String TIPO;
    private String ESTADO;
    private String matriz;
    private String denominacionMatriz;
    
    private int CODIGO_ERROR;
    
    public void crear_Empresa(){
        
        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;
        String query = SQLKeywords.INSERT.toSQL()
                     + getTABLA_SQL()
                     + SentenceValues.setValues(8);
        
        try {
            pSt = conexion.prepareStatement(query);
            pSt.setString(1, getID_EMPRESA());
            pSt.setString(2, getDESCRIPCION_EMPRESA());
            pSt.setString(3, getID_PAIS());
            pSt.setString(4, getDESCRIPCION_PAIS());
            pSt.setString(5, getTIPO());
            pSt.setString(6, getESTADO());
            pSt.setString(7, getMatriz());
            pSt.setString(8, getDenominacionMatriz());
            pSt.executeUpdate();
            
            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(C01_Crear_Compania.class.getName()).log(Level.SEVERE, null, ex);
          //codigo de duplicado en BD envia este trozo de codigo
            if(ex.getErrorCode()==1062){
               
               setCODIGO_ERROR(ex.getErrorCode()); 
                
            }
            
        }
        
        
    }
    
    
}
