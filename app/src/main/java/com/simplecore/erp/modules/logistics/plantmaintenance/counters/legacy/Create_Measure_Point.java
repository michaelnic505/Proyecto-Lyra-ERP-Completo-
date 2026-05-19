package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;

public class Create_Measure_Point {

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

    public void createMeasPoint() {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.MEASURING_POINTS.tableName()
                    + SentenceValues.setValues(17);

            st = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "0");
            st.setString(2, getObjMeasPoint());
            st.setString(3, getMeasPosition());
            st.setString(4, getMeasType());
            st.setString(5, getDescription());
            st.setInt(6, getEquipment());
            st.setString(7, getEquipmentDescription());
            st.setString(8, getCharacteristic());
            st.setString(9, getCharactUnit());
            st.setInt(10, getDecimalPlaces());
            st.setBoolean(11, isIsCounter());
            st.setString(12, getCodeGroup());
            st.setString(13, getAssembly());
            st.setString(14, getAuthorizGroup());
            st.setString(15, getTargetValue());
            st.setString(16, getText());
            st.setBoolean(17, isStatus());

            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                setMeasurePoint(rs.getInt(1));
            }

            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(Create_Measure_Point.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getMeasurePoint() {
        return measurePoint;
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

    public String getObjMeasPoint() {
        return objMeasPoint;
    }

    public void setObjMeasPoint(String objMeasPoint) {
        this.objMeasPoint = objMeasPoint;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
