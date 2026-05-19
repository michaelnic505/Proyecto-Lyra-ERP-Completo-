
package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

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
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;


public class U01_Cargar_Lista_GruposPlanif {

    
    
    public void cargar_Lista_Areas(JTable table){
        
        try {
            LyraTableModel model = (LyraTableModel) table.getModel();
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT_ALL.toSQL()
                    + DatabaseTables.PLANNING_GROUPS.tableName();
            
            
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
            
            table.setModel(model);
            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(U01_Cargar_Lista_GruposPlanif.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            

    }
}
