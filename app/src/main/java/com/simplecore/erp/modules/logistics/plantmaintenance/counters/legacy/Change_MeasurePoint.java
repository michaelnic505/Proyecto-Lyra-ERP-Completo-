package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Modify_Statement;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.MeasuringPoints;

public class Change_MeasurePoint {

    private String description;
    private String codeGroup;
    private String assembly;
    private String authorizGroup;
    private String targetValue;
    private String text;
    private boolean status;

    public void changeMeasPoint(String measPoint) {

        try {
            List<String> fieldList = new ArrayList();
            
            fieldList.add(MeasuringPoints.DESCRIPTION.toString());
            fieldList.add(MeasuringPoints.CODE_GROUP.toString());
            fieldList.add(MeasuringPoints.ASSEMBLY.toString());
            fieldList.add(MeasuringPoints.AUTHORIZ_GROUP.toString());
            fieldList.add(MeasuringPoints.TARGET_VALUE.toString());
            fieldList.add(MeasuringPoints.TEXT.toString());
            fieldList.add(MeasuringPoints.STATUS.toString());
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String query = SQLKeywords.UPDATE.toSQL()
                    + DatabaseTables.MEASURING_POINTS.tableName()
                    + SQLKeywords.SET.toSQL()
                    + SQL_Modify_Statement.setModifyFields(fieldList)
                    + SQLKeywords.WHERE.toSQL()
                    + MeasuringPoints.MEASURE_POINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + measPoint
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            st = conexion.prepareStatement(query);
            st.setString(1, getDescription());
            st.setString(2, getCodeGroup());
            st.setString(3, getAssembly());
            st.setString(4, getAuthorizGroup());
            st.setString(5, getTargetValue());
            st.setString(6, getText());
            st.setBoolean(7, isStatus());
              
            
            st.executeUpdate();
            
            st.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Change_MeasurePoint.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    public String getDescription() {
        return description;
    }

    public String getCodeGroup() {
        return codeGroup;
    }

    public String getAssembly() {
        return assembly;
    }

    public String getAuthorizGroup() {
        return authorizGroup;
    }

    public String getTargetValue() {
        return targetValue;
    }

    public String getText() {
        return text;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCodeGroup(String codeGroup) {
        this.codeGroup = codeGroup;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    public void setAuthorizGroup(String authorizGroup) {
        this.authorizGroup = authorizGroup;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
