
package com.simplecore.erp.modules.system.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;


public class AU1_Cargar_Datos_Usuarios {

   
    public JTable getJTABLE() {
        return JTABLE;
    }


    public void setJTABLE(JTable JTABLE) {
        this.JTABLE = JTABLE;
    }

  
    public String getTABLA_SQL() {
        return TABLA_SQL;
    }

  
    public void setTABLA_SQL(String TABLA_SQL) {
        this.TABLA_SQL = TABLA_SQL;
    }
    
    
    private String TABLA_SQL;
    private JTable JTABLE;
    
    public void cargar_Datos_Usuarios(){
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;
        String query =  SQLKeywords.SELECT_ALL.toSQL()
                      + getTABLA_SQL()
                                      ;
                
        try {
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery(query);
            ResultSet Datos = pSt.getResultSet();
            ResultSetMetaData MetaData = Datos.getMetaData();
            
            int CantCol = MetaData.getColumnCount();
            Object[] columnasTabla = new Object[CantCol];
           
            for (int i = 0; i < CantCol; i++) {
                columnasTabla[i] = MetaData.getColumnName(i + 1);
            } 
            
            modelo.setColumnIdentifiers(columnasTabla);
            
            while (Datos.next()) {
                Object[] filas = new Object[CantCol];
                for (int i = 0; i < CantCol; i++) {
                    filas[i] = Datos.getObject(i + 1);
                }
                modelo.addRow(filas);
            }            
            
            getJTABLE().setModel(modelo);
            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AU1_Cargar_Datos_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
