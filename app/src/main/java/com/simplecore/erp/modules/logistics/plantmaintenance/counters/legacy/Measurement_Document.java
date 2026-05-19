package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Statements;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.MeasurementDocuments;

public class Measurement_Document {

    private String measNumDoc;
    private String measPoint;
    private String measPosition;
    private String measPosDesc;
    private String measTypeCode;
    private String equipment;
    private String equipDesc;
    private String characteristic;
    private String readingDate;
    private String readingTime;
    private String mUnit;
    private String dailyPerfIndex;
    private String measuredValue;
    private String theoreticalValue;
    private String text;
    private String createdBy;
    private String createdOn;
    private String prevDPI;
    private String daysLastReg;
    private String previusValue;

    public void getData(String numDoc) {

        try {
            List<String> list = new ArrayList();
            list.add(MeasurementDocuments.MEASNUMDOC.toString());
            list.add(MeasurementDocuments.MEASPOINT.toString());
            list.add(MeasurementDocuments.MEASPOSITION.toString());
            list.add(MeasurementDocuments.MEASPOSDESC.toString());
            list.add(MeasurementDocuments.MEASTYPECODE.toString());
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
            list.add(MeasurementDocuments.CREATEDBY.toString());
            list.add(MeasurementDocuments.CREATEDON.toString());
            list.add(MeasurementDocuments.IRDPREV.toString());
            list.add(MeasurementDocuments.DAYSLASTREG.toString());
            list.add(MeasurementDocuments.PREVIOUSVALUE.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(list)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASNUMDOC.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + numDoc;

            st = conexion.prepareStatement(query);
            st.executeQuery();

            ResultSet rs = st.getResultSet();

            if (rs.next()) {
                
                setMeasNumDoc(rs.getString(1));
                setMeasPoint(rs.getString(2));
                setMeasPosition(rs.getString(3));
                setMeasPosDesc(rs.getString(4));
                setMeasTypeCode(rs.getString(5));
                setEquipment(rs.getString(6));
                setEquipDesc(rs.getString(7));
                setCharacteristic(rs.getString(8));
                setReadingDate(rs.getString(9));
                setReadingTime(rs.getString(10));
                setmUnit(rs.getString(11));
                setDailyPerfIndex(rs.getString(12));
                setMeasuredValue(rs.getString(13));
                setTheoreticalValue(rs.getString(14));
                setText(rs.getString(15));
                setCreatedBy(rs.getString(16));
                setCreatedOn(rs.getString(17));
                setPrevDPI(rs.getString(18));
                setDaysLastReg(rs.getString(19));
                setPreviusValue(rs.getString(20));

            }
            
            st.close();
            

        } catch (SQLException ex) {
            Logger.getLogger(Measurement_Document.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getMeasNumDoc() {
        return measNumDoc;
    }

    public String getMeasPoint() {
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

    public String getReadingTime() {
        return readingTime;
    }

    public String getmUnit() {
        return mUnit;
    }

    public String getDailyPerfIndex() {
        return dailyPerfIndex;
    }

    public String getMeasuredValue() {
        return measuredValue;
    }

    public String getTheoreticalValue() {
        return theoreticalValue;
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

    public String getPrevDPI() {
        return prevDPI;
    }

    public String getDaysLastReg() {
        return daysLastReg;
    }

    public String getPreviusValue() {
        return previusValue;
    }

    public void setMeasNumDoc(String measNumDoc) {
        this.measNumDoc = measNumDoc;
    }

    public void setMeasPoint(String measPoint) {
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

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    public void setDailyPerfIndex(String dailyPerfIndex) {
        this.dailyPerfIndex = dailyPerfIndex;
    }

    public void setMeasuredValue(String measuredValue) {
        this.measuredValue = measuredValue;
    }

    public void setTheoreticalValue(String theoreticalValue) {
        this.theoreticalValue = theoreticalValue;
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

    public void setPrevDPI(String prevDPI) {
        this.prevDPI = prevDPI;
    }

    public void setDaysLastReg(String daysLastReg) {
        this.daysLastReg = daysLastReg;
    }

    public void setPreviusValue(String previusValue) {
        this.previusValue = previusValue;
    }

}
