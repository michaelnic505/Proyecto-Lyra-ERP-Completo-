package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Statements;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.WorkOrders;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.StatusOrder;

public class Lista_Ordenes_SQL {

    public void cargarDatos(LyraTable table) {

        List<String> fieldList = new ArrayList();
        fieldList.add(WorkOrders.ORDER_NUM.toString());
        fieldList.add(WorkOrders.STATUS_CODE.toString());
        fieldList.add(WorkOrders.STATUS_DESCRIPTION.toString());
        fieldList.add(WorkOrders.ORDER_TITLE.toString());
        fieldList.add(WorkOrders.EQUIPMENT_CODE.toString());
        fieldList.add(WorkOrders.EQUIPMENT_DESCRIPTION.toString());
        fieldList.add(WorkOrders.UBICATION_CODE.toString());
        fieldList.add(WorkOrders.UBICATION_DESCRIPTION.toString());

        try {

            LyraTableModel model = (LyraTableModel) table.getModel();
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(fieldList)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.WORK_ORDERS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + WorkOrders.STATUS_CODE.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + StatusOrder.ST3_ORDER_UNDER_APPROVAL.getStatusCode()
                    + SQLKeywords.SINGLE_QUOTE.toSQL();

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet datos = pSt.getResultSet();
            ResultSetMetaData meta = datos.getMetaData();

            int CantCol = meta.getColumnCount();

            while (datos.next()) {

                Object[] filas = new Object[CantCol];

                for (int i = 0; i < CantCol; i++) {
                    filas[i] = datos.getObject(i + 1);
                }

                model.addRow(filas);
            }

            table.setModel(model);

            pSt.close();

        } catch (SQLException ex) {
            Logger.getLogger(Lista_Ordenes_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
