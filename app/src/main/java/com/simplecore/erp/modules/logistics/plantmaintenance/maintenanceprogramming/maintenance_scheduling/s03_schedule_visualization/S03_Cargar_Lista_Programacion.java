
package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceprogramming.maintenance_scheduling.s03_schedule_visualization;

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
import com.simplecore.erp.config.database.utils.ClearTable;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;

public class S03_Cargar_Lista_Programacion {

    public JTable getJTABLE() {
        return JTABLE;
    }

    public void setJTABLE(JTable JTABLE) {
        this.JTABLE = JTABLE;
    }

    private JTable JTABLE;
    private LyraTableModel model;

    public void cargarDatos() {

        try {
            ClearTable.clear(getJTABLE());
            model =  (LyraTableModel) getJTABLE().getModel();
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT_ALL.toSQL()
                    + DatabaseTables.Programacion_mantenimiento.tableName();
            
                        
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
            
            getJTABLE().setModel(model);           
            pSt.close();

            
        } catch (SQLException ex) {
            Logger.getLogger(S03_Cargar_Lista_Programacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
