package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableAuxiliar;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Statements;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.MeasurementDocuments;

public class Measurement_Documents_List {

    public void loadData(LyraTableAuxiliar table) {

        try {
            List<String> list = new ArrayList();
            list.add(MeasurementDocuments.MEASNUMDOC.toString());
            list.add(MeasurementDocuments.MEASPOINT.toString());
            list.add(MeasurementDocuments.MEASPOSITION.toString());
            list.add(MeasurementDocuments.MEASPOSDESC.toString());
            list.add(MeasurementDocuments.EQUIPMENT.toString());
            list.add(MeasurementDocuments.EQUIPDESC.toString());
            list.add(MeasurementDocuments.CHARACTERISTIC.toString());
            list.add(MeasurementDocuments.READINGDATE.toString());
            list.add(MeasurementDocuments.READINGTIME.toString());
            list.add(MeasurementDocuments.MUNIT.toString());
            list.add(MeasurementDocuments.IRD.toString());
            list.add(MeasurementDocuments.MEASUREDVALUE.toString());
            list.add(MeasurementDocuments.THEORICALVALUE.toString());
            list.add(MeasurementDocuments.TEXT.toString());
            list.add(MeasurementDocuments.CREATEDON.toString());
            list.add(MeasurementDocuments.CREATEDBY.toString());
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(list)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true                    
                    ;
            
            st = conexion.prepareStatement(query);
            st.executeQuery();
            
            ResultSet rs = st.getResultSet();
            ResultSetMetaData meta = rs.getMetaData();
            
            int colCant = meta.getColumnCount();
            
            while (rs.next()) {
                
                String[] fila = new String[colCant];
                
                for (int i = 0; i < fila.length; i++) {
                    fila[i] = rs.getString(i + 1);
                }
                ((DefaultTableModel) table.getModel()).addRow(fila);
            }

            st.close();


        } catch (SQLException ex) {
            Logger.getLogger(Measurement_Documents_List.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
