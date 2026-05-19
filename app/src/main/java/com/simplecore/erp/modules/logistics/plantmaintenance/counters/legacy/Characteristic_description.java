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

public class Characteristic_description {

    private static String getDesciptionChar(String charact) {

        String text = "";

        try {
            List<String> fieldList = new ArrayList();
            fieldList.add(Characteristics.DESCRIPTION.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(fieldList)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.CHARACTERISTICS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Characteristics.CHARACTERISTIC.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + charact
                    + SQLKeywords.SINGLE_QUOTE.toSQL();

            st = conexion.prepareStatement(query);
            st.executeQuery();

            ResultSet rs = st.getResultSet();

            if (rs.next()) {
                text = rs.getString(1);
            }

            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(Characteristic_description.class.getName()).log(Level.SEVERE, null, ex);
        }

        return text;
    }

    public static String getDescription(String charact, String language) {

        String text = "";

        try {

            List<String> fieldList = new ArrayList();
            fieldList.add(CharacteristicsDescriptions.DESCRIPTION.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(fieldList)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.CHARACTERISTICS_DESCRIPTIONS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + CharacteristicsDescriptions.CHARACTERISTIC.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + charact
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + SQLKeywords.AND.toSQL()
                    + CharacteristicsDescriptions.LANGUAGE.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + language
                    + SQLKeywords.SINGLE_QUOTE.toSQL();

            st = conexion.prepareStatement(query);
            st.executeQuery();

            ResultSet rs = st.getResultSet();

            if (rs.next()) {
                text = rs.getString(1);
            }

            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(Characteristic_description.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Si no hay resultados entonces extrae la descripcion nativa de la caracteristica
        
        if (text.equals("")) {
            text = getDesciptionChar(charact);
        }

        return text;
    }

}
