package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.modification;

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

public class M05_Cargar_Lista_Area_Almacen {

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

    private JTable JTABLE;
    private String TABLA_SQL;

    public void cargar_Lista_Area_Almacen() {

        DefaultTableModel modelo = new DefaultTableModel();

        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;

        String query = SQLKeywords.SELECT_ALL.toSQL()
                + getTABLA_SQL();

        try {

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet Data = pSt.getResultSet();
            ResultSetMetaData MetaData = Data.getMetaData();

            int canCol = MetaData.getColumnCount();
            Object[] columnas = new Object[canCol];

            for (int i = 0; i < canCol; i++) {
                columnas[i] = MetaData.getColumnName(i + 1);
            }

            modelo.setColumnIdentifiers(columnas);

            while (Data.next()) {
                
                Object[] filas = new Object[canCol];
                
                for (int i = 0; i < canCol; i++) {
                    filas[i] = Data.getObject(i + 1);
                }
                
                modelo.addRow(filas);
            
            }
            
            getJTABLE().setModel(modelo);
            pSt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(M05_Cargar_Lista_Area_Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
