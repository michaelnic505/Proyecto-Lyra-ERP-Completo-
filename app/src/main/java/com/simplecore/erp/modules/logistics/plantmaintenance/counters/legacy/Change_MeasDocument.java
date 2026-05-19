package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Modify_Statement;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.MeasurementDocuments;

public class Change_MeasDocument {

    private String numdoc;

    public Change_MeasDocument(String numdoc) {
        this.numdoc = numdoc;
    }
    
    public void updateText(String text) {

        try {
            List<String> fieldList = new ArrayList();
            fieldList.add(MeasurementDocuments.TEXT.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.UPDATE.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.SET.toSQL()
                    + SQL_Modify_Statement.setModifyFields(fieldList)
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASNUMDOC.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + numdoc;

            st = conexion.prepareStatement(query);
            st.setString(1, text);

            st.executeUpdate();

            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(Change_MeasDocument.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateStatus(boolean status) {
        try {
            List<String> fieldList = new ArrayList();
            fieldList.add(MeasurementDocuments.STATUS.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.UPDATE.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.SET.toSQL()
                    + SQL_Modify_Statement.setModifyFields(fieldList)
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASNUMDOC.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + numdoc;

            st = conexion.prepareStatement(query);
            st.setBoolean(1, status);

            st.executeUpdate();

            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(Change_MeasDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
