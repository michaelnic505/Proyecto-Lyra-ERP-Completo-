
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
import com.simplecore.erp.config.database.tables.sociedades;
import com.simplecore.erp.modules.controlling.society.legacy.TypesCompanies;


public class A01_Cargar_Lista_Sociedad {

    
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
                    + DatabaseTables.Empresas.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + sociedades.TIPO.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + TypesCompanies.FI.toString()
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
                    
            
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
            Logger.getLogger(A01_Cargar_Lista_Sociedad.class.getName()).log(Level.SEVERE, null, ex);
        }
            

        
    }
    
    
    
}
