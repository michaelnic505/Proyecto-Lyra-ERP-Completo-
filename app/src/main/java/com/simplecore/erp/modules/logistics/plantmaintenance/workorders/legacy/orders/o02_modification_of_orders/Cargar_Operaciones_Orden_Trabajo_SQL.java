package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders;

import java.sql.Connection;
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
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.WorkOrder_Fields.OperationsWorkOrder_Fields;

public class Cargar_Operaciones_Orden_Trabajo_SQL {

    public void cargarOperaciones(String orden, LyraTable table) {

        LyraTableModel model = (LyraTableModel) table.getModel();
        
        try {
            List<String> fieldList = new ArrayList();

            fieldList.add(OperationsWorkOrder_Fields.OPERATION_NUMBER.toString());
            fieldList.add(OperationsWorkOrder_Fields.EXPLANATORY_TEXT_OPERATION.toString());
            fieldList.add(OperationsWorkOrder_Fields.TITLE_OPERATION.toString());
            fieldList.add(OperationsWorkOrder_Fields.WORKING_HOURS.toString());
            fieldList.add(OperationsWorkOrder_Fields.QUANTITY.toString());
            fieldList.add(OperationsWorkOrder_Fields.DURATION.toString());
            fieldList.add(OperationsWorkOrder_Fields.OPERATION_TYPE_CODE.toString());
            fieldList.add(OperationsWorkOrder_Fields.OPERATION_TYPE_DESCRIPTION.toString());
            fieldList.add(OperationsWorkOrder_Fields.UNIT_COST.toString());
            fieldList.add(OperationsWorkOrder_Fields.MEASUREMENT_UNIT.toString());
            fieldList.add(OperationsWorkOrder_Fields.TOTAL_AMOUNT.toString());
            fieldList.add(OperationsWorkOrder_Fields.MTTO_PACKAGE.toString());
            fieldList.add(OperationsWorkOrder_Fields.ORDER_REQUEST.toString());
            fieldList.add(OperationsWorkOrder_Fields.CURRENCY.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(fieldList)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.OPERATIONS_WORK_ORDERS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + OperationsWorkOrder_Fields.NUM_WORK_ORDER.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + orden
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + SQLKeywords.ORDER_BY.toSQL()
                    + OperationsWorkOrder_Fields.OPERATION_NUMBER.toString()
                    + SQLKeywords.ASC.toSQL()
                    ;
            

            st = conexion.prepareStatement(query);
            st.executeQuery();

            ResultSet datos = st.getResultSet();
            
            int cols = model.getColumnCount();

            while(datos.next()){

                Object[] celdas = new Object[cols];
   
                celdas[0] = datos.getObject(OperationsWorkOrder_Fields.OPERATION_NUMBER.toString());
                celdas[1] = null;
                celdas[2] = datos.getObject(OperationsWorkOrder_Fields.EXPLANATORY_TEXT_OPERATION.toString());
                celdas[3] = datos.getObject(OperationsWorkOrder_Fields.TITLE_OPERATION.toString());
                celdas[4] = datos.getObject(OperationsWorkOrder_Fields.WORKING_HOURS.toString());
             //   celdas[5] = CG15_Double_Formato.setFormat(datos.getDouble(OperationsWorkOrder_Fields.QUANTITY.toString()));
             //   celdas[6] = CG15_Double_Formato.setFormat(datos.getDouble(OperationsWorkOrder_Fields.DURATION.toString()));
                celdas[7] = datos.getObject(OperationsWorkOrder_Fields.OPERATION_TYPE_CODE.toString());
                celdas[8] = datos.getObject(OperationsWorkOrder_Fields.OPERATION_TYPE_DESCRIPTION.toString());
               // celdas[9] = CG15_Double_Formato.setFormat(datos.getDouble(OperationsWorkOrder_Fields.UNIT_COST.toString()));
                celdas[10] = datos.getObject(OperationsWorkOrder_Fields.MEASUREMENT_UNIT.toString());
               // celdas[11] = CG15_Double_Formato.setFormat(datos.getDouble(OperationsWorkOrder_Fields.TOTAL_AMOUNT.toString()));
                celdas[12] = datos.getObject(OperationsWorkOrder_Fields.MTTO_PACKAGE.toString());
                celdas[13] = datos.getObject(OperationsWorkOrder_Fields.ORDER_REQUEST.toString());
                celdas[14] = datos.getObject(OperationsWorkOrder_Fields.CURRENCY.toString());


                
                model.addRow(celdas);
            }

            table.setModel(model);
            
            st.close();
            
            

        } catch (SQLException ex) {
            Logger.getLogger(Cargar_Operaciones_Orden_Trabajo_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
