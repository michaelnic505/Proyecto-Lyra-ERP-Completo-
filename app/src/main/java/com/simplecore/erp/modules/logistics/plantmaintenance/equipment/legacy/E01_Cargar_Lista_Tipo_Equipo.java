
package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy;

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


public class E01_Cargar_Lista_Tipo_Equipo {

    
    public void cargarDatos(JTable tabla){
        
        LyraTableModel model = (LyraTableModel) tabla.getModel();
        
        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;
        String query = SQLKeywords.SELECT_ALL.toSQL()
                + DatabaseTables.Tipo_Equipo.tableName();
        
        try {            
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet Data = pSt.getResultSet();
            ResultSetMetaData MetaData = Data.getMetaData();
            int canCol = MetaData.getColumnCount();
            

            while (Data.next()) {
                Object[] filas = new Object[canCol];
                for (int i = 0; i < canCol; i++) {
                    filas[i] = Data.getObject(i + 1);
                }
                
                model.addRow(filas);
            }
            
            tabla.setModel(model);
            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(E01_Cargar_Lista_Tipo_Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
    
}
