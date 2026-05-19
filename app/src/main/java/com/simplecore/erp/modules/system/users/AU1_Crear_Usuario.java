
package com.simplecore.erp.modules.system.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;


public class AU1_Crear_Usuario {

    public int getRESULTADO() {
        return RESULTADO;
    }

    public void setRESULTADO(int RESULTADO) {
        this.RESULTADO = RESULTADO;
    }


    public String getHOST() {
        return HOST;
    }

    public void setHOST(String HOST) {
        this.HOST = HOST;
    }


    public String getTABLA_SQL() {
        return TABLA_SQL;
    }


    public void setTABLA_SQL(String TABLA_SQL) {
        this.TABLA_SQL = TABLA_SQL;
    }


    public String getNOMBRE_USUARIO() {
        return NOMBRE_USUARIO;
    }


    public void setNOMBRE_USUARIO(String NOMBRE_USUARIO) {
        this.NOMBRE_USUARIO = NOMBRE_USUARIO;
    }

    public String getPRIMER_NOMBRE() {
        return PRIMER_NOMBRE;
    }


    public void setPRIMER_NOMBRE(String PRIMER_NOMBRE) {
        this.PRIMER_NOMBRE = PRIMER_NOMBRE;
    }


    public String getSEGUNDO_NOMBRE() {
        return SEGUNDO_NOMBRE;
    }


    public void setSEGUNDO_NOMBRE(String SEGUNDO_NOMBRE) {
        this.SEGUNDO_NOMBRE = SEGUNDO_NOMBRE;
    }


    public String getPRIMER_APELLIDO() {
        return PRIMER_APELLIDO;
    }


    public void setPRIMER_APELLIDO(String PRIMER_APELLIDO) {
        this.PRIMER_APELLIDO = PRIMER_APELLIDO;
    }


    public String getSEGUNDO_APELLIDO() {
        return SEGUNDO_APELLIDO;
    }


    public void setSEGUNDO_APELLIDO(String SEGUNDO_APELLIDO) {
        this.SEGUNDO_APELLIDO = SEGUNDO_APELLIDO;
    }


    public String getCORREO() {
        return CORREO;
    }


    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }


    public String getCARGO() {
        return CARGO;
    }


    public void setCARGO(String CARGO) {
        this.CARGO = CARGO;
    }


    public String getORGANIZACIÓN() {
        return ORGANIZACIÓN;
    }


    public void setORGANIZACIÓN(String ORGANIZACIÓN) {
        this.ORGANIZACIÓN = ORGANIZACIÓN;
    }


    public String getTIPO_USUARIO() {
        return TIPO_USUARIO;
    }


    public void setTIPO_USUARIO(String TIPO_USUARIO) {
        this.TIPO_USUARIO = TIPO_USUARIO;
    }


    public String getCONTRASENA() {
        return CONTRASENA;
    }


    public void setCONTRASENA(String CONTRASENA) {
        this.CONTRASENA = CONTRASENA;
    }
    
    private String NOMBRE_USUARIO;
    private String PRIMER_NOMBRE;
    private String SEGUNDO_NOMBRE;
    private String PRIMER_APELLIDO;
    private String SEGUNDO_APELLIDO;
    private String CORREO;
    private String CARGO;
    private String ORGANIZACIÓN;
    private String TIPO_USUARIO;
    private String CONTRASENA;
    private String TABLA_SQL;
    private String HOST;
    private int RESULTADO;
    
    public void crear_Usuario(){
                
        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;
        String query = SQLKeywords.INSERT.toSQL()
                + getTABLA_SQL()
                + SentenceValues.setValues(11);
        
        try {
            pSt = conexion.prepareStatement(query);
            pSt.setString(1, getNOMBRE_USUARIO() );
            pSt.setString(2, getPRIMER_NOMBRE());
            pSt.setString(3, getSEGUNDO_NOMBRE());
            pSt.setString(4, getPRIMER_APELLIDO());
            pSt.setString(5, getSEGUNDO_APELLIDO());
            pSt.setString(6, getCORREO());
            pSt.setString(7, getCARGO());
            pSt.setString(8, getORGANIZACIÓN());
            pSt.setString(9, getTIPO_USUARIO());
            pSt.setString(10,getCONTRASENA());
            pSt.setString(11, getHOST());
            
            pSt.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AU1_Crear_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            setRESULTADO(ex.getErrorCode());
        }
        
    }
    
    
    
    
}
