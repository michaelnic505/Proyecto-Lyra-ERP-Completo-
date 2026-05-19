package com.simplecore.erp.modules.system.users.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.gui.workspace.LyraFrame;


public class AU1_Gestion_de_Usuarios_SQL {

    private static String CONECTOR;
    private static String DIRECCION;
    private static String BASE_DATOS;
    private static String USER;
    private static String PASSWORD;

    private static void leerDatosBaseDatos() {

//        LeerArchivoArranqueMYSQL lectorParametros = new LeerArchivoArranqueMYSQL();
//        lectorParametros.leerArchivo();
//
//        CONECTOR = lectorParametros.getConector();
//        DIRECCION = lectorParametros.getDireccion();
//        BASE_DATOS = lectorParametros.getBaseDatos();
//
//        lectorParametros.cerrarArchivo();

    }

    private static void leerDatosUsuarioRoot() {
  //      USER = MainLyra.getUser();
    //    PASSWORD = MainLyra.getPassword();
    }

    public static void CREATE_USER(String nuevoUsuario, String nuevaContrasena, String HOST) {

        leerDatosBaseDatos();
        leerDatosUsuarioRoot();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String dir = "jdbc:mysql://localhost:3306/mysql";
            String DIR = CONECTOR + DIRECCION + BASE_DATOS;
            Connection conexion = DriverManager.getConnection(DIR, USER, PASSWORD);
            PreparedStatement pSt = null;
            String query = "CREATE USER '"
                    + nuevoUsuario
                    + "'@'"
                    + HOST
                    + "' IDENTIFIED BY '"
                    + nuevaContrasena + "'";

            pSt = conexion.prepareStatement(query);
            pSt.executeUpdate();

            
            pSt.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AU1_Gestion_de_Usuarios_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void DROP_USER(String usuario, String HOST) {

        leerDatosBaseDatos();
        leerDatosUsuarioRoot();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String dir = "jdbc:mysql://localhost:3306/mysql";
            String DIR = CONECTOR + DIRECCION + BASE_DATOS;
            Connection conexion = DriverManager.getConnection(DIR, USER, PASSWORD);
            PreparedStatement pSt = null;
            String query = "DROP USER "
                    + usuario
                    + "@"
                    + HOST;

            pSt = conexion.prepareStatement(query);
            pSt.executeUpdate();

           
            pSt.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AU1_Gestion_de_Usuarios_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void GRANT_CREATE(String TABLA_SQL, String BD_SQL, String usuario) {

        leerDatosBaseDatos();
        leerDatosUsuarioRoot();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String dir = "jdbc:mysql://localhost:3306/mysql";
            String DIR = CONECTOR + DIRECCION + BASE_DATOS;
            Connection conexion = DriverManager.getConnection(DIR, USER, PASSWORD);
            PreparedStatement pSt = null;
            String query = "GRANT CREATE ON "
                    + BD_SQL
                    + "."
                    + TABLA_SQL
                    + " TO "
                    + usuario;

            pSt = conexion.prepareStatement(query);
            pSt.executeUpdate();

            pSt.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AU1_Gestion_de_Usuarios_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void GRANT_SELECT(String TABLA_SQL, String BD_SQL, String usuario) {

        leerDatosBaseDatos();
        leerDatosUsuarioRoot();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String dir = "jdbc:mysql://localhost:3306/mysql";
            String DIR = CONECTOR + DIRECCION + BASE_DATOS;
            Connection conexion = DriverManager.getConnection(DIR, USER, PASSWORD);
            PreparedStatement pSt = null;
            String query = "GRANT SELECT ON "
                    + BD_SQL
                    + "."
                    + TABLA_SQL
                    + " TO "
                    + usuario;

            pSt = conexion.prepareStatement(query);
            pSt.executeUpdate();

         
            pSt.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AU1_Gestion_de_Usuarios_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void GRANT_UPDATE(String TABLA_SQL, String BD_SQL, String usuario) {

        leerDatosBaseDatos();
        leerDatosUsuarioRoot();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String dir = "jdbc:mysql://localhost:3306/mysql";
            String DIR = CONECTOR + DIRECCION + BASE_DATOS;
            Connection conexion = DriverManager.getConnection(DIR, USER, PASSWORD);
            PreparedStatement pSt = null;
            String query = "GRANT UPDATE ON "
                    + BD_SQL
                    + "."
                    + TABLA_SQL
                    + " TO "
                    + usuario;

            pSt = conexion.prepareStatement(query);
            pSt.executeUpdate();

          

            pSt.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AU1_Gestion_de_Usuarios_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void GRANT_DELETE(String TABLA_SQL, String BD_SQL, String usuario) {

        leerDatosBaseDatos();
        leerDatosUsuarioRoot();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String dir = "jdbc:mysql://localhost:3306/mysql";
            String DIR = CONECTOR + DIRECCION + BASE_DATOS;
            Connection conexion = DriverManager.getConnection(DIR, USER, PASSWORD);
            PreparedStatement pSt = null;
            String query = "GRANT DELETE ON "
                    + BD_SQL
                    + "."
                    + TABLA_SQL
                    + " TO "
                    + usuario;

            pSt = conexion.prepareStatement(query);
            pSt.executeUpdate();


            pSt.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AU1_Gestion_de_Usuarios_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void GRANT_ALL_PRIVILEGES(String TABLA_SQL, String BD_SQL, String usuario) {

        leerDatosBaseDatos();
        leerDatosUsuarioRoot();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String dir = "jdbc:mysql://localhost:3306/mysql";
            String DIR = CONECTOR + DIRECCION + BASE_DATOS;
            Connection conexion = DriverManager.getConnection(DIR, USER, PASSWORD);
            PreparedStatement pSt = null;
            String query = "GRANT ALL PRIVILEGES ON "
                    + BD_SQL
                    + "."
                    + TABLA_SQL
                    + " TO "
                    + usuario;

            pSt = conexion.prepareStatement(query);
            pSt.executeUpdate();

            
            pSt.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AU1_Gestion_de_Usuarios_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void REVOKE_CREATE(String TABLA_SQL, String BD_SQL, String usuario) {

        leerDatosBaseDatos();
        leerDatosUsuarioRoot();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String dir = "jdbc:mysql://localhost:3306/mysql";
            String DIR = CONECTOR + DIRECCION + BASE_DATOS;
            Connection conexion = DriverManager.getConnection(DIR, USER, PASSWORD);
            PreparedStatement pSt = null;
            String query = "REVOKE CREATE ON "
                    + BD_SQL
                    + "."
                    + TABLA_SQL
                    + " FROM "
                    + usuario;

            pSt = conexion.prepareStatement(query);
            pSt.executeUpdate();

         

            pSt.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AU1_Gestion_de_Usuarios_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void REVOKE_SELECT(String TABLA_SQL, String BD_SQL, String usuario) {

        leerDatosBaseDatos();
        leerDatosUsuarioRoot();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String dir = "jdbc:mysql://localhost:3306/mysql";
            String DIR = CONECTOR + DIRECCION + BASE_DATOS;
            Connection conexion = DriverManager.getConnection(DIR, USER, PASSWORD);
            PreparedStatement pSt = null;
            String query = "REVOKE SELECT ON "
                    + BD_SQL
                    + "."
                    + TABLA_SQL
                    + " FROM "
                    + usuario;

            pSt = conexion.prepareStatement(query);
            pSt.executeUpdate();

            

            pSt.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AU1_Gestion_de_Usuarios_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void REVOKE_UPDATE(String TABLA_SQL, String BD_SQL, String usuario) {

        leerDatosBaseDatos();
        leerDatosUsuarioRoot();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String dir = "jdbc:mysql://localhost:3306/mysql";
            String DIR = CONECTOR + DIRECCION + BASE_DATOS;
            Connection conexion = DriverManager.getConnection(DIR, USER, PASSWORD);
            PreparedStatement pSt = null;
            String query = "REVOKE UPDATE ON "
                    + BD_SQL
                    + "."
                    + TABLA_SQL
                    + " FROM "
                    + usuario;

            pSt = conexion.prepareStatement(query);
            pSt.executeUpdate();

           

            pSt.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AU1_Gestion_de_Usuarios_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void REVOKE_DELETE(String TABLA_SQL, String BD_SQL, String usuario) {

        leerDatosBaseDatos();
        leerDatosUsuarioRoot();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String dir = "jdbc:mysql://localhost:3306/mysql";
            String DIR = CONECTOR + DIRECCION + BASE_DATOS;
            Connection conexion = DriverManager.getConnection(DIR, USER, PASSWORD);
            PreparedStatement pSt = null;
            String query = "REVOKE DELETE ON "
                    + BD_SQL
                    + "."
                    + TABLA_SQL
                    + " FROM "
                    + usuario;

            pSt = conexion.prepareStatement(query);
            pSt.executeUpdate();

           

            pSt.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AU1_Gestion_de_Usuarios_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void REVOKE_ALL_PRIVILEGES(String usuario) {

        leerDatosBaseDatos();
        leerDatosUsuarioRoot();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String dir = "jdbc:mysql://localhost:3306/mysql";
            String DIR = CONECTOR + DIRECCION + BASE_DATOS;
            Connection conexion = DriverManager.getConnection(DIR, USER, PASSWORD);
            PreparedStatement pSt = null;
            String query = "REVOKE ALL PRIVILEGES, GRANT OPTION FROM "
                    + usuario;

            pSt = conexion.prepareStatement(query);
            pSt.executeUpdate();
           

            pSt.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AU1_Gestion_de_Usuarios_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
