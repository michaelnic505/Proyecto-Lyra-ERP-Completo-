
package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.tables.divisas;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;

public class CargarListadoDivisas {

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    private DefaultTableModel model = new DefaultTableModel();
    
    private void setIdentifiers(){
        String[] columasES = {"País","Divisa","Código"};
        String[] columasEN = {"Country","Currency","Code"}; 
        String[] columasFR = {"Pays", "Devise", "Code"}; 
        String[] columasPT = {"País","Moeda","Código"}; 
        String[] columasRU = {"Страна", "Валюта", "Код"}; 
        
            getModel().setColumnIdentifiers(columasEN);
               
        
    }
    
    

    public void cargarDivisas() {
        
        setIdentifiers();
        
        
        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT.toSQL()
                    + divisas.pais.toString()
                    + divisas.divisa.toString()
                    + divisas.codigo.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.Divisas.tableName();
            
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            ResultSet Datos = pSt.getResultSet();
            ResultSetMetaData meta = Datos.getMetaData();
            
            int colCant = meta.getColumnCount();
            
            while(Datos.next()){
                String[] fila = new String[colCant];
                for (int i = 0; i < fila.length; i++) {
                    fila[i] = Datos.getString(i+1);
                }
                getModel().addRow(fila);
            }
            
            pSt.close();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CargarListadoDivisas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
