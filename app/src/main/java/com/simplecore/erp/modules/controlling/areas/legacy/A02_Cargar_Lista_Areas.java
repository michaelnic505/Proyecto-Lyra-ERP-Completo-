
package com.simplecore.erp.modules.controlling.areas.legacy;

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
import com.simplecore.erp.config.database.DatabaseTables;


public class A02_Cargar_Lista_Areas {

    
    public JTable getTable() {
        return table;
    }

    public void setTable(JTable JTABLE) {
        this.table = JTABLE;
    }
    
    
    
    private JTable table;
    
    public void cargarDatos(){
        
        try {
            DefaultTableModel model = (DefaultTableModel) getTable().getModel();
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            
            String query = SQLKeywords.SELECT_ALL.toSQL()
                    + DatabaseTables.Areas.tableName();
            
            
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet Datos = pSt.getResultSet();
            ResultSetMetaData MetaDatos = Datos.getMetaData();
            
            int CantCol = MetaDatos.getColumnCount();

            while (Datos.next()) {
              
                Object[] filas = new Object[CantCol];
                
                for (int i = 0; i < CantCol; i++) {
                    filas[i] = Datos.getObject(i + 1);
                }
                
                model.addRow(filas);
            }
            
            
            getTable().setModel(model);            
            pSt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(A02_Cargar_Lista_Areas.class.getName()).log(Level.SEVERE, null, ex);
        }
            

        
    }
    
    
    
}
