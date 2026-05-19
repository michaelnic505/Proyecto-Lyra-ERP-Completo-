
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
import com.simplecore.erp.config.database.tables.transacciones;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;

public class AU2_Cargar_Datos_Transacciones {

   
    public String getTABLA_SQL() {
        return TABLA_SQL;
    }

 
    public void setTABLA_SQL(String TABLA_SQL) {
        this.TABLA_SQL = TABLA_SQL;
    }


    public JTable getJTABLE() {
        return JTABLE;
    }


    public void setJTABLE(JTable JTABLE) {
        this.JTABLE = JTABLE;
    }

    private String TABLA_SQL;
    private JTable JTABLE;
    LyraTableModel Modelo;
    
    public void cargar_Datos(){
        
        Modelo = (LyraTableModel) getJTABLE().getModel();
        
        Connection conexion = PooledConnectionService.getConnection();

        PreparedStatement pSt = null;
        String query = SQLKeywords.SELECT_ALL.toSQL()
                + DatabaseTables.BUSINESS_TRANSACTIONS.tableName()               
                + SQLKeywords.ORDER_BY.toSQL()
                + transacciones.TRANSACCION.toString()
                + SQLKeywords.ASC.toSQL();
        
        
        try {
            pSt = conexion.prepareStatement(query);
 
            pSt.executeQuery();
            ResultSet Data = pSt.getResultSet();
            ResultSetMetaData MetaData = Data.getMetaData();

            int canCol = MetaData.getColumnCount();

            while (Data.next()) {
                Object[] filas = new Object[canCol];

                for (int i = 0; i < 3; i++) {

                    filas[i] = Data.getObject(i + 1);
                }

                Modelo.addRow(filas);

            }
            
            getJTABLE().setModel(Modelo);            
            pSt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AU2_Cargar_Datos_Transacciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
