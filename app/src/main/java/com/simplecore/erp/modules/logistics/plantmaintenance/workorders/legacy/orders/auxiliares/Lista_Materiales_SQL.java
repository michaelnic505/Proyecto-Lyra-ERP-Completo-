
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.alta_materiales_almacen;




public class Lista_Materiales_SQL {

    public void cargarDatos(LyraTable table) {

        try {
            LyraTableModel model = (LyraTableModel) table.getModel();
            Connection conexion = PooledConnectionService.getConnection();
            
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT.toSQL()
                    + alta_materiales_almacen.CODIGO_MATERIAL.toString()
                    + alta_materiales_almacen.DESCRIPCION_MATERIAL.toString()
                    + alta_materiales_almacen.CODIGO_ALMACEN.toString()
                    + alta_materiales_almacen.DESCRIPCION_ALMACEN.toString()
                    + alta_materiales_almacen.PRECIO_UNITARIO.toString()
                    + alta_materiales_almacen.ID_UM.toString()
                    + alta_materiales_almacen.SERIE.toString()
                    + alta_materiales_almacen.MODELO.toString()
                    + alta_materiales_almacen.MARCA.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MATERIAL_WAREHOUSE_REGISTRATION.tableName();
            
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet rs = pSt.getResultSet();
            ResultSetMetaData md = pSt.getMetaData();

            int canCol = md.getColumnCount();

            while (rs.next()) {

                Object[] filas = new Object[canCol];

                for (int i = 0; i < canCol; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                model.addRow(filas);

            }

            pSt.close();

            
        } catch (SQLException ex) {
            Logger.getLogger(Lista_Materiales_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

}
