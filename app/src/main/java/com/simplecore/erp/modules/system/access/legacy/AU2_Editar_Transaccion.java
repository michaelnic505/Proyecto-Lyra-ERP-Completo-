
package com.simplecore.erp.modules.system.access.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.tables.transacciones;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;



public class AU2_Editar_Transaccion {
    
    private int CantidadColumnas;
    
    public void UpdateTransaccion(String transaccion, int numFilaUpdate, JTable tabla) {

        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pst = null;


        String queryArmado = "";

        for (int i = 0; i < tabla.getColumnCount()-3; i++) {
            
            queryArmado = queryArmado+tabla.getColumnName(i+3) + "=?,";
        }

        String finalQuery = queryArmado.substring(0, queryArmado.length() - 1);

        String query = SQLKeywords.UPDATE.toSQL()
                     + DatabaseTables.BUSINESS_TRANSACTIONS.tableName()
                     + SQLKeywords.SET.toSQL()
                     + finalQuery
                     + SQLKeywords.WHERE.toSQL()
                     + transacciones.TRANSACCION.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.SINGLE_QUOTE.toSQL()
                     + transaccion
                     + SQLKeywords.SINGLE_QUOTE.toSQL();
                
        try {

            pst = conexion.prepareStatement(query);

            for (int i = 1; i < tabla.getColumnCount() - 2; i++) {

                pst.setString(i, (String) tabla.getValueAt(numFilaUpdate, i+2));

            }

            pst.executeUpdate();


            pst.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AU2_Editar_Transaccion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public void calcularCantCampos(String transaccion) {

        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pst = null;

        String query = SQLKeywords.SELECT_ALL.toSQL()
                     + DatabaseTables.BUSINESS_TRANSACTIONS.tableName()
                     + SQLKeywords.WHERE.toSQL()
                     + transacciones.TRANSACCION.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.SINGLE_QUOTE.toSQL()
                     + transaccion
                     + SQLKeywords.SINGLE_QUOTE.toSQL();
                
              
        try {
            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();
            ResultSetMetaData rsm = rs.getMetaData();

            int canCol = rsm.getColumnCount();

            setCantidadColumnas(canCol);


        } catch (SQLException ex) {
            Logger.getLogger(AU2_Editar_Transaccion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public int getCantidadColumnas() {
        return CantidadColumnas;
    }

 
    public void setCantidadColumnas(int CantidadColumnas) {
        this.CantidadColumnas = CantidadColumnas;
    }
    
    
    
    
    
}
