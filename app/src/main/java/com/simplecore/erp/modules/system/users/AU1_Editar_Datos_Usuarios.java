
package com.simplecore.erp.modules.system.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.tables.users;
import com.simplecore.erp.config.database.utils.SQLKeywords;


public class AU1_Editar_Datos_Usuarios {

    public String getHOST() {
        return HOST;
    }

    public void setHOST(String HOST) {
        this.HOST = HOST;
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


    public String getTABLA_SQL() {
        return TABLA_SQL;
    }


    public void setTABLA_SQL(String TABLA_SQL) {
        this.TABLA_SQL = TABLA_SQL;
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
    
    public void editar_Datos_Usuarios(){
        
        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;
        String query = SQLKeywords.UPDATE.toSQL()
                + getTABLA_SQL()
                + SQLKeywords.SET.toSQL()
                + users.PRIMER_NOMBRE.toString()
                + SQLKeywords.EQUALS.toSQL()
                + SQLKeywords.QUESTION_MARK.toSQL()
                
                + users.SEGUNDO_NOMBRE.toString()
                + SQLKeywords.EQUALS.toSQL()
                + SQLKeywords.QUESTION_MARK.toSQL()
                
                + users.PRIMER_APELLIDO.toString()
                + SQLKeywords.EQUALS.toSQL()
                + SQLKeywords.QUESTION_MARK.toSQL()
                
                + users.SEGUNDO_APELLIDO.toString()
                + SQLKeywords.EQUALS.toSQL()
                + SQLKeywords.QUESTION_MARK.toSQL()
                
                + users.CORREO.toString()
                + SQLKeywords.EQUALS.toSQL()
                + SQLKeywords.QUESTION_MARK.toSQL()
                
                + users.CARGO.toString()
                + SQLKeywords.EQUALS.toSQL()
                + SQLKeywords.QUESTION_MARK.toSQL()
                
                + users.ORGANIZACIÓN.toString()
                + SQLKeywords.EQUALS.toSQL()
                + SQLKeywords.QUESTION_MARK.toSQL()
                
                + users.TIPO_USUARIO.toString()
                + SQLKeywords.EQUALS.toSQL()
                + SQLKeywords.QUESTION_MARK.toSQL()
                
                + users.CONTRASENA.toString()
                + SQLKeywords.EQUALS.toSQL()
                + SQLKeywords.QUESTION_MARK.toSQL()
                
                + users.DIRECCION_HOST.toString()
                + SQLKeywords.EQUALS.toSQL()
                + SQLKeywords.QUESTION_MARK.toSQL()
                
                + SQLKeywords.WHERE.toSQL()
                + users.NOMBRE_USUARIO.toString()
                + SQLKeywords.EQUALS.toSQL()
                + SQLKeywords.SINGLE_QUOTE.toSQL()
                + getNOMBRE_USUARIO()
                + SQLKeywords.SINGLE_QUOTE.toSQL();

        try {
            pSt = conexion.prepareStatement(query);
            pSt.setString(1, getPRIMER_NOMBRE());
            pSt.setString(2, getSEGUNDO_NOMBRE());
            pSt.setString(3, getPRIMER_APELLIDO());
            pSt.setString(4, getSEGUNDO_APELLIDO());
            pSt.setString(5, getCORREO());
            pSt.setString(6, getCARGO());
            pSt.setString(7, getORGANIZACIÓN());
            pSt.setString(8, getTIPO_USUARIO());
            pSt.setString(9, getCONTRASENA());
            pSt.setString(10, getHOST());
            pSt.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AU1_Editar_Datos_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
    
    
    
}
