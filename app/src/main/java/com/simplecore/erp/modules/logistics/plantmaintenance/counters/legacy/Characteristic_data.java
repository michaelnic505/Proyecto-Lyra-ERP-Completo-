
package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Statements;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import static com.simplecore.erp.config.database.DatabaseTables.Characteristics.CHARACTERISTIC;


public class Characteristic_data {
    
    private String characteristic;
    private String unitMeas;
    private String decimalPlaces;
    private String codeGroup;
    private String description;
    
    public void getCharacteriticData(String characteristic) {

        try {
            List<String> lista = new ArrayList();
            
            lista.add(CHARACTERISTIC.UNITOFMEASURE.toString());
            lista.add(CHARACTERISTIC.NUMBERDECIMALS.toString());
            lista.add(CHARACTERISTIC.CHARGROUP.toString());
            lista.add(CHARACTERISTIC.DESCRIPTION.toString());
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(lista)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.CHARACTERISTICS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + CHARACTERISTIC.CHARACTERISTIC.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + characteristic
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            
            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();
            
            while(rs.next()){
                
                setUnitMeas(rs.getString(1));
                setDecimalPlaces(rs.getString(2));
                setCodeGroup(rs.getString(3));
                setDescription(rs.getString(4));
            }
            
            pst.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Characteristic_data.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    public String getCharacteristic() {
        return characteristic;
    }

    public String getUnitMeas() {
        return unitMeas;
    }

    public String getDecimalPlaces() {
        return decimalPlaces;
    }

    public String getCodeGroup() {
        return codeGroup;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public void setUnitMeas(String unitMeas) {
        this.unitMeas = unitMeas;
    }

    public void setDecimalPlaces(String decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public void setCodeGroup(String codeGroup) {
        this.codeGroup = codeGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
   
    
    
}
