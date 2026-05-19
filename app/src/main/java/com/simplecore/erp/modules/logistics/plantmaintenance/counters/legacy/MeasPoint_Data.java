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
import com.simplecore.erp.config.database.DatabaseTables.MeasuringPoints;

public class MeasPoint_Data {

    private String measPoint;
    private String measPosition;
    private String measPosDescription;
    private String measType;
    private String equipment;
    private String equipDescription;
    private String characteristic;
    private int decPlaces;
    private String charUnit;

    public void loadData(String measPoint) {

        this.setMeasPoint(measPoint);
        
        try {
        
            List list = new ArrayList<String>();
            list.add(MeasuringPoints.MEAS_POSITION.toString());
            list.add(MeasuringPoints.DESCRIPTION.toString());
            list.add(MeasuringPoints.MEAS_TYPE.toString());
            list.add(MeasuringPoints.EQUIPMENT.toString());
            list.add(MeasuringPoints.EQUIPMENT_DESCRIPTION.toString());
            list.add(MeasuringPoints.CHARACTERISTIC.toString());
            list.add(MeasuringPoints.DECIMAL_PLACES.toString());
            list.add(MeasuringPoints.CHARACT_UNIT.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(list)
                    + SQLKeywords.FROM.toSQL()
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
                
                setMeasPosition(rs.getString(1));
                setMeasPosDescription(rs.getString(2));
                setMeasType(rs.getString(3));
                setEquipment(rs.getString(4));
                setEquipDescription(rs.getString(5));
                setCharacteristic(rs.getString(6));
                setDecPlaces(rs.getInt(7));
                setCharUnit(rs.getString(8));
            
            }
            
            st.close();

            
        } catch (SQLException ex) {
            Logger.getLogger(MeasPoint_Data.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getMeasPoint() {
        return measPoint;
    }

    public String getMeasPosition() {
        return measPosition;
    }

    public String getMeasPosDescription() {
        return measPosDescription;
    }

    public String getMeasType() {
        return measType;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getEquipDescription() {
        return equipDescription;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public int getDecPlaces() {
        return decPlaces;
    }

    public String getCharUnit() {
        return charUnit;
    }

    public void setMeasPoint(String measPoint) {
        this.measPoint = measPoint;
    }

    public void setMeasPosition(String measPosition) {
        this.measPosition = measPosition;
    }

    public void setMeasPosDescription(String measPosDescription) {
        this.measPosDescription = measPosDescription;
    }

    public void setMeasType(String measType) {
        this.measType = measType;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public void setEquipDescription(String equipDescription) {
        this.equipDescription = equipDescription;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public void setDecPlaces(int decPlaces) {
        this.decPlaces = decPlaces;
    }

    public void setCharUnit(String charUnit) {
        this.charUnit = charUnit;
    }

}
