package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;

public class Create_Measurement_Document {

    private int measNumDoc;
    private int measPoint;
    private String measPosition;
    private String measPosDesc;
    private String measTypeCode;
    private String equipment;
    private String equipDesc;
    private String characteristic;
    private String readingDate;
    private String mUnit;
    private double ird;
    private double measuredValue;
    private double theoricalValue;
    private String text;
    private String createdBy;
    private String createdOn;
    private double irdPrev;
    private double daysLastReg;
    private double previousValue;
    private boolean status;
    private String time;

    public void createMeasDoc() {

        try {
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SentenceValues.setValues(21);

            st = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "0");
            st.setInt(2, getMeasPoint());
            st.setString(3, getMeasPosition());
            st.setString(4, getMeasPosDesc());
            st.setString(5, getMeasTypeCode());
            st.setString(6, getEquipment());
            st.setString(7, getEquipDesc());
            st.setString(8, getCharacteristic());
            st.setString(9, getReadingDate());
            st.setString(10, getTime());
            st.setString(11, getmUnit());
            st.setDouble(12, getIrd());
            st.setDouble(13, getMeasuredValue());
            st.setDouble(14, getTheoricalValue());
            st.setString(15, getText());
            st.setString(16, getCreatedBy());
            st.setString(17, getCreatedOn());
            st.setDouble(18, getIrdPrev());
            st.setDouble(19, getDaysLastReg());
            st.setDouble(20, getPreviousValue());
            st.setBoolean(21, isStatus());
            
            st.executeUpdate();
            
            
            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                setMeasNumDoc(rs.getInt(1));
            }

            st.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Create_Measurement_Document.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getMeasNumDoc() {
        return measNumDoc;
    }

    public int getMeasPoint() {
        return measPoint;
    }

    public String getMeasPosition() {
        return measPosition;
    }

    public String getMeasPosDesc() {
        return measPosDesc;
    }

    public String getMeasTypeCode() {
        return measTypeCode;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getEquipDesc() {
        return equipDesc;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public String getReadingDate() {
        return readingDate;
    }

    public String getmUnit() {
        return mUnit;
    }

    public double getIrd() {
        return ird;
    }

    public double getMeasuredValue() {
        return measuredValue;
    }

    public double getTheoricalValue() {
        return theoricalValue;
    }

    public String getText() {
        return text;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public double getIrdPrev() {
        return irdPrev;
    }

    public double getDaysLastReg() {
        return daysLastReg;
    }

    public double getPreviousValue() {
        return previousValue;
    }

    public boolean isStatus() {
        return status;
    }

    public void setMeasNumDoc(int measNumDoc) {
        this.measNumDoc = measNumDoc;
    }

    public void setMeasPoint(int measPoint) {
        this.measPoint = measPoint;
    }

    public void setMeasPosition(String measPosition) {
        this.measPosition = measPosition;
    }

    public void setMeasPosDesc(String measPosDesc) {
        this.measPosDesc = measPosDesc;
    }

    public void setMeasTypeCode(String measTypeCode) {
        this.measTypeCode = measTypeCode;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public void setEquipDesc(String equipDesc) {
        this.equipDesc = equipDesc;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public void setReadingDate(String readingDate) {
        this.readingDate = readingDate;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    public void setIrd(double ird) {
        this.ird = ird;
    }

    public void setMeasuredValue(double measuredValue) {
        this.measuredValue = measuredValue;
    }

    public void setTheoricalValue(double theoricalValue) {
        this.theoricalValue = theoricalValue;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setIrdPrev(double irdPrev) {
        this.irdPrev = irdPrev;
    }

    public void setDaysLastReg(double daysLastReg) {
        this.daysLastReg = daysLastReg;
    }

    public void setPreviousValue(double previousValue) {
        this.previousValue = previousValue;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
