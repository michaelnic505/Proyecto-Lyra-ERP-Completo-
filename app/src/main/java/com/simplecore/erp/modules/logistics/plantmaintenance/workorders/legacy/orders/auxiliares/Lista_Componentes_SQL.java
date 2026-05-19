package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;

public class Lista_Componentes_SQL {

    public LyraTable getTable() {
        return table;
    }

    public void setTable(LyraTable table) {
        this.table = table;
    }

    private LyraTable table;

    public void cargarDatos() {

        try {

            LyraTableModel model = (LyraTableModel) table.getModel();
            Connection conexion = PooledConnectionService.getConnection();

            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT_ALL.toSQL()
                    + DatabaseTables.componentes.tableName();

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
            Logger.getLogger(Lista_Componentes_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
