package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.MeasuringPoints;

public class Load_MeasPoint_Data {
    
    private int measurePoint;
    private String objMeasPoint;
    private String measPosition;
    private String measType;
    private String description;
    private int equipment;
    private String equipmentDescription;
    private String characteristic;
    private String charactUnit;
    private int decimalPlaces;
    private boolean isCounter;
    private String codeGroup;
    private String assembly;
    private String authorizGroup;
    private String targetValue;
    private String text;
    private boolean status;

    public void loadData(String measPoint) {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String query = SQLKeywords.SELECT_ALL.toSQL()
                    + DatabaseTables.MEASURING_POINTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasuringPoints.MEASURE_POINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + measPoint
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            st = conexion.prepareStatement(query);
            st.executeQuery();
            
            ResultSet rs = st.getResultSet();

            while (rs.next()) {

                setMeasurePoint(rs.getInt(1));
                setObjMeasPoint(rs.getString(2));
                setMeasPosition(rs.getString(3));
                setMeasType(rs.getString(4));
                setDescription(rs.getString(5));
                setEquipment(rs.getInt(6));
                setEquipmentDescription(rs.getString(7));
                setCharacteristic(rs.getString(8));
                setCharactUnit(rs.getString(9));
                setDecimalPlaces(rs.getInt(10));
                setIsCounter(rs.getBoolean(11));
                setCodeGroup(rs.getString(12));
                setAssembly(rs.getString(13));
                setAuthorizGroup(rs.getString(14));
                setTargetValue(rs.getString(15));
                setText(rs.getString(16));
                setStatus(rs.getBoolean(17));

            }

            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Load_MeasPoint_Data.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getMeasurePoint() {
        return measurePoint;
    }

    public String getObjMeasPoint() {
        return objMeasPoint;
    }

    public String getMeasPosition() {
        return measPosition;
    }

    public String getMeasType() {
        return measType;
    }

    public String getDescription() {
        return description;
    }

    public int getEquipment() {
        return equipment;
    }

    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public String getCharactUnit() {
        return charactUnit;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public boolean isIsCounter() {
        return isCounter;
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

    public void setMeasurePoint(int measurePoint) {
        this.measurePoint = measurePoint;
    }

    public void setObjMeasPoint(String objMeasPoint) {
        this.objMeasPoint = objMeasPoint;
    }

    public void setMeasPosition(String measPosition) {
        this.measPosition = measPosition;
    }

    public void setMeasType(String measType) {
        this.measType = measType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEquipment(int equipment) {
        this.equipment = equipment;
    }

    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public void setCharactUnit(String charactUnit) {
        this.charactUnit = charactUnit;
    }

    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public void setIsCounter(boolean isCounter) {
        this.isCounter = isCounter;
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
