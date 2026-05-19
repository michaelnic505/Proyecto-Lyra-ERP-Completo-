package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Statements;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.WorkOrder_Fields;

public class Lista_Ordenes_Trabajo_SQL {
    
    public void cargarDatos(JTable table) {
        
        try {
            List<String> fieldList = new ArrayList();
            
            fieldList.add(WorkOrder_Fields.ORDER_NUM.toString());
            fieldList.add(WorkOrder_Fields.STATUS_CODE.toString());
            fieldList.add(WorkOrder_Fields.STATUS_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.STOP.toString());
            fieldList.add(WorkOrder_Fields.ORDER_TITLE.toString());
            fieldList.add(WorkOrder_Fields.TYPE_ORDER_CODE.toString());
            fieldList.add(WorkOrder_Fields.TYPE_ORDER_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.CLASS_ORDER_CODE.toString());
            fieldList.add(WorkOrder_Fields.CLASS_ORDER_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.CRITICALITY_CODE.toString());
            fieldList.add(WorkOrder_Fields.CRITICALITY_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.SYSTEM_CODE.toString());
            fieldList.add(WorkOrder_Fields.SYSTEM_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.COMPONENT_CODE.toString());
            fieldList.add(WorkOrder_Fields.COMPONENT_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.SYMPTOM_CODE.toString());
            fieldList.add(WorkOrder_Fields.SYMPTOM_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.EQUIPMENT_CODE.toString());
            fieldList.add(WorkOrder_Fields.EQUIPMENT_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.UBICATION_CODE.toString());
            fieldList.add(WorkOrder_Fields.UBICATION_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.ACTUAL_START_DATE.toString());
            fieldList.add(WorkOrder_Fields.ACTUAL_END_DATE.toString());
            fieldList.add(WorkOrder_Fields.ACTUAL_START_TIME.toString());
            fieldList.add(WorkOrder_Fields.ACTUAL_END_TIME.toString());
            fieldList.add(WorkOrder_Fields.TOTAL_REAL_TIME.toString());
            fieldList.add(WorkOrder_Fields.SCHEDULED_START_DATE.toString());
            fieldList.add(WorkOrder_Fields.SCHEDULED_END_DATE.toString());
            fieldList.add(WorkOrder_Fields.SCHEDULED_START_TIME.toString());
            fieldList.add(WorkOrder_Fields.SCHEDULED_END_TIME.toString());
            fieldList.add(WorkOrder_Fields.SCHEDULED_TOTAL_TIME.toString());
            fieldList.add(WorkOrder_Fields.CREATION_DATE_ORDER.toString());
            fieldList.add(WorkOrder_Fields.APPROVAL_DATE_ORDER.toString());
            fieldList.add(WorkOrder_Fields.CREATION_TIME_ORDER.toString());
            fieldList.add(WorkOrder_Fields.AUTHORIZATION_TIME_ORDER.toString());
            fieldList.add(WorkOrder_Fields.TOTAL_CREATION_HOURS.toString());
            fieldList.add(WorkOrder_Fields.GROUP_PLANNING_CODE.toString());
            fieldList.add(WorkOrder_Fields.PLANNING_GROUP_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.APPLICANT_CODE.toString());
            fieldList.add(WorkOrder_Fields.APPLICANT_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.RESPONSIBLE_CODE.toString());
            fieldList.add(WorkOrder_Fields.DESCRIPTION_OF_RESPONSIBLE.toString());
            fieldList.add(WorkOrder_Fields.COMPANY_CODE.toString());
            fieldList.add(WorkOrder_Fields.COMPANY_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.AREA_CODE.toString());
            fieldList.add(WorkOrder_Fields.AREA_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.EMPLAZEMENT_CODE.toString());
            fieldList.add(WorkOrder_Fields.EMPLAZEMENT_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.COST_CENTER_CODE.toString());
            fieldList.add(WorkOrder_Fields.COST_CENTER_DESCRIPTION.toString());
            fieldList.add(WorkOrder_Fields.ORDER_CREATED_BY.toString());
            fieldList.add(WorkOrder_Fields.ORDER_PLANNED_BY.toString());
            fieldList.add(WorkOrder_Fields.ORDER_APPROVED_BY.toString());
            fieldList.add(WorkOrder_Fields.ORDER_EXECUTED_BY.toString());
            fieldList.add(WorkOrder_Fields.ESTIMATED_TIME.toString());
            fieldList.add(WorkOrder_Fields.REAL_TIME.toString());
            fieldList.add(WorkOrder_Fields.ESTIMATED_COST.toString());
            fieldList.add(WorkOrder_Fields.REAL_COST.toString());
            fieldList.add(WorkOrder_Fields.COUNTER.toString());
            fieldList.add(WorkOrder_Fields.COUNTER_VALUE.toString());
            fieldList.add(WorkOrder_Fields.ROAD_SHEET.toString());
            fieldList.add(WorkOrder_Fields.PLAN.toString());
            
            LyraTableModel model = (LyraTableModel) table.getModel();
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(fieldList)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.WORK_ORDERS.tableName();
            
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
            Logger.getLogger(Lista_Ordenes_Trabajo_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
