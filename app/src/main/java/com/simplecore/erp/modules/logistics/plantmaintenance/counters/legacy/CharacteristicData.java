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
import com.simplecore.erp.config.database.DatabaseTables.Characteristics;
import com.simplecore.erp.config.database.DatabaseTables.CharacteristicsDescriptions;

public class CharacteristicData {

    public static String getDescription(String lang,String characteristic) {

        String description = null;
        
        try {
            
            List l = new ArrayList<String>();
            l.add(CharacteristicsDescriptions.DESCRIPTION.toString());
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(l)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.CHARACTERISTICS_DESCRIPTIONS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + CharacteristicsDescriptions.CHARACTERISTIC.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + characteristic
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + SQLKeywords.AND.toSQL()
                    + CharacteristicsDescriptions.LANGUAGE.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + lang
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            st = conexion.prepareStatement(query);
            st.executeQuery();
            
            ResultSet rs = st.getResultSet();
            
            if(rs.next()){
                
                description = rs.getString(1);
            
            } else {

                st.close();

                List l2 = new ArrayList<String>();
                l2.add(Characteristics.DESCRIPTION.toString());

                String query2 = SQLKeywords.SELECT.toSQL()
                        + SQL_Statements.Select(l2)
                        + SQLKeywords.FROM.toSQL()
                        + DatabaseTables.CHARACTERISTICS.tableName()
                        + SQLKeywords.WHERE.toSQL()
                        + Characteristics.CHARACTERISTIC.toString()
                        + SQLKeywords.EQUALS.toSQL()
                        + SQLKeywords.SINGLE_QUOTE.toSQL()
                        + characteristic
                        + SQLKeywords.SINGLE_QUOTE.toSQL();
                
                st = conexion.prepareStatement(query2);
                st.executeQuery(); 
                
                rs = st.getResultSet();
                
                if(rs.next()){
                   description = rs.getString(1);
                }else{
                   description = null;
                }
                
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicData.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return description;
    }
}
