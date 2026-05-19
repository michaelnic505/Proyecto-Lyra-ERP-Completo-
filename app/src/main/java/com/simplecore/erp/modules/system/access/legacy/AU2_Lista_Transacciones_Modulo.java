
package com.simplecore.erp.modules.system.access.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.modules.system.users.AU1_Cargar_Lista_Tipo_Usuarios;
import com.simplecore.erp.config.database.tables.arbol;
import com.simplecore.erp.config.database.utils.SQLKeywords;


public class AU2_Lista_Transacciones_Modulo {

  
    public String getTABLA_SQL() {
        return TABLA_SQL;
    }

    public void setTABLA_SQL(String TABLA_SQL) {
        this.TABLA_SQL = TABLA_SQL;
    }

    public JTable getJTABLA() {
        return JTABLA;
    }

    public void setJTABLA(JTable JTABLA) {
        this.JTABLA = JTABLA;
    }
    
    private String TABLA_SQL;
    private JTable JTABLA;
    
    public void cargarListaTransaccionesModulos(){
        
        try {
            
        LyraTableModel modelo = new LyraTableModel();
        
        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;
    
        String query = SQLKeywords.SELECT.toSQL()
                     + arbol.NODO_ID_TRANSACCION.toString()
                     + arbol.NODO_DESCRIPCION_TRANSACCION_EN.toString()
                     + arbol.MODULOS_EN.toString()
                     + SQLKeywords.FROM.toSQL()
                     + getTABLA_SQL()
                    ;
            
                
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
            
            getJTABLA().setModel(modelo);
            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AU1_Cargar_Lista_Tipo_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }    

        
        
    }
    
}
