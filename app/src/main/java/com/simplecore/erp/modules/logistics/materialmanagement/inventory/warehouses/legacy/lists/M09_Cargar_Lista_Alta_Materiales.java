
package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.lists;


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


public class M09_Cargar_Lista_Alta_Materiales {

    public String getTABLA_SQL() {
        return TABLA_SQL;
    }

    public JTable getJTABLA() {
        return JTABLA;
    }

    public void setTABLA_SQL(String TABLA_SQL) {
        this.TABLA_SQL = TABLA_SQL;
    }

    public void setJTABLA(JTable JTABLA) {
        this.JTABLA = JTABLA;
    }
    
    private String TABLA_SQL;
    private JTable JTABLA;
    
    public void cargar_Lista_Material() {
        
        LyraTableModel model = new LyraTableModel();

        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;
     
        String query = SQLKeywords.SELECT_ALL.toSQL()
                + getTABLA_SQL();

        try {
       
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet Datos = pSt.getResultSet();
            ResultSetMetaData MetaDatos = Datos.getMetaData();

            int CantCol = MetaDatos.getColumnCount();
            Object[] columnasTabla = new Object[CantCol];

            for (int i = 0; i < CantCol; i++) {
                columnasTabla[i] = MetaDatos.getColumnName(i + 1);
            }

            model.setColumnIdentifiers(columnasTabla);

            while (Datos.next()) {
                Object[] filas = new Object[CantCol];
                for (int i = 0; i < CantCol; i++) {
                    filas[i] = Datos.getObject(i + 1);
                }
                model.addRow(filas);
            }

            
            getJTABLA().setModel(model);
            pSt.close();
            
            

        } catch (SQLException ex) {
            Logger.getLogger(M09_Cargar_Lista_Alta_Materiales.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
}
