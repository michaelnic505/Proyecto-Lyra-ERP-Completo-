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
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.WorkOrder_Fields.MaterialsWorkOrder_Fields;

public class Cargar_Materiales_Orden_Trabajo_SQL {

    public void cargarMateriales(String orden, LyraTable table) {

        LyraTableModel model = (LyraTableModel) table.getModel();
        
        try {
            List<String> fieldList = new ArrayList();

            fieldList.add(MaterialsWorkOrder_Fields.POSITION.toString());
            fieldList.add(MaterialsWorkOrder_Fields.MATERIAL_CODE.toString());
            fieldList.add(MaterialsWorkOrder_Fields.MATERIAL_DESCRIPTION.toString());
            fieldList.add(MaterialsWorkOrder_Fields.QUANTITY.toString());
            fieldList.add(MaterialsWorkOrder_Fields.MEASUREMENT_UNIT.toString());
            fieldList.add(MaterialsWorkOrder_Fields.UNIT_COST.toString());
            fieldList.add(MaterialsWorkOrder_Fields.WAREHOUSE_CODE.toString());
            fieldList.add(MaterialsWorkOrder_Fields.TOTAL_AMOUNT.toString());
            fieldList.add(MaterialsWorkOrder_Fields.OPERATION_NUMBER.toString());
            fieldList.add(MaterialsWorkOrder_Fields.MTTO_PACKAGE.toString());


            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(fieldList)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MATERIALS_WORK_ORDERS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MaterialsWorkOrder_Fields.NUM_WORK_ORDER.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + orden
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + SQLKeywords.ORDER_BY.toSQL()
                    + MaterialsWorkOrder_Fields.POSITION.toString()
                    + SQLKeywords.ASC.toSQL()
                    ;
            

            st = conexion.prepareStatement(query);
            st.executeQuery();

            ResultSet datos = st.getResultSet();
            
            int cols = model.getColumnCount();

            while(datos.next()){

                Object[] celdas = new Object[cols];
   
                celdas[0] = datos.getObject(MaterialsWorkOrder_Fields.POSITION.toString());
                celdas[1] = datos.getObject(MaterialsWorkOrder_Fields.MATERIAL_CODE.toString());
                celdas[2] = null;
                celdas[3] = datos.getObject(MaterialsWorkOrder_Fields.MATERIAL_DESCRIPTION.toString());
    //            celdas[4] = CG15_Double_Formato.setFormat(datos.getDouble(MaterialsWorkOrder_Fields.QUANTITY.toString()));
                celdas[5] = datos.getObject(MaterialsWorkOrder_Fields.MEASUREMENT_UNIT.toString());
//                celdas[6] = CG15_Double_Formato.setFormat(datos.getDouble(MaterialsWorkOrder_Fields.UNIT_COST.toString()));
                celdas[7] = datos.getObject(MaterialsWorkOrder_Fields.WAREHOUSE_CODE.toString());
  //              celdas[8] = CG15_Double_Formato.setFormat(datos.getDouble(MaterialsWorkOrder_Fields.TOTAL_AMOUNT.toString()));
                celdas[9] = datos.getObject(MaterialsWorkOrder_Fields.OPERATION_NUMBER.toString());
                celdas[10] = datos.getObject(MaterialsWorkOrder_Fields.MTTO_PACKAGE.toString());

                
                model.addRow(celdas);
            }

            table.setModel(model);
            
            st.close();
            
            

        } catch (SQLException ex) {
            Logger.getLogger(Cargar_Materiales_Orden_Trabajo_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
