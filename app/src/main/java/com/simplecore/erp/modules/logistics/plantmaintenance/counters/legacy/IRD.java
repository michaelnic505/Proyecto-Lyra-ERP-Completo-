package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Statements;
import com.simplecore.erp.config.database.utils.Function_SQL;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.MeasurementDocuments;

public class IRD {

    private static double ird;

    public static double getLastIRD(String measPoint) {

        try {
            List lista = new ArrayList<String>();
            lista.add(MeasurementDocuments.IRD.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(lista)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASPOINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + measPoint
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.READINGDATE.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.OPEN_PARENTHESIS.toSQL()
                    + SQLKeywords.SELECT.toSQL()
                    + Function_SQL.MAX(MeasurementDocuments.READINGDATE.toString())
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASPOINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + measPoint
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true
                    + SQLKeywords.CLOSE_PARENTHESIS.toSQL()
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.READINGTIME.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.OPEN_PARENTHESIS.toSQL()
                    + SQLKeywords.SELECT.toSQL()
                    + Function_SQL.MAX(MeasurementDocuments.READINGTIME.toString())
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASPOINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + measPoint
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true
                    + SQLKeywords.CLOSE_PARENTHESIS.toSQL();

            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();

            if (rs.next()) {
                ird = rs.getDouble(1);
            } else {
                ird = 0;
            }

            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(IRD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ird;
    }

    private static double previousValue;

    public static double getPreviousValue(String measPoint) {

        try {
            List lista = new ArrayList<String>();
            lista.add(MeasurementDocuments.MEASUREDVALUE.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(lista)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASPOINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + measPoint
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.READINGDATE.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.OPEN_PARENTHESIS.toSQL()
                    + SQLKeywords.SELECT.toSQL()
                    + Function_SQL.MAX(MeasurementDocuments.READINGDATE.toString())
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASPOINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + measPoint
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true
                    + SQLKeywords.CLOSE_PARENTHESIS.toSQL();

            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();

            if (rs.next()) {
                previousValue = rs.getDouble(1);
            } else {
                previousValue = 0.0;
            }

            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(IRD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return previousValue;
    }

    public static String getPreviousReadingDate(String measPoint) {

        String date = "";

        try {
            List lista = new ArrayList<String>();
            lista.add(MeasurementDocuments.READINGDATE.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(lista)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASPOINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + measPoint
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.READINGDATE.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.OPEN_PARENTHESIS.toSQL()
                    + SQLKeywords.SELECT.toSQL()
                    + Function_SQL.MAX(MeasurementDocuments.READINGDATE.toString())
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASPOINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + measPoint
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true
                    + SQLKeywords.CLOSE_PARENTHESIS.toSQL();

            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();

            if (rs.next()) {
                date = rs.getString(1);
            } else {
                date = null;
            }

            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(IRD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return date;
    }

    public static String getPreviousReadingTime(String measPoint) {

        String date = "";

        try {
            List lista = new ArrayList<String>();
            lista.add(MeasurementDocuments.READINGTIME.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(lista)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASPOINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + measPoint
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.READINGDATE.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.OPEN_PARENTHESIS.toSQL()
                    + SQLKeywords.SELECT.toSQL()
                    + Function_SQL.MAX(MeasurementDocuments.READINGDATE.toString())
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASPOINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + measPoint
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true
                    + SQLKeywords.CLOSE_PARENTHESIS.toSQL();

            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();

            if (rs.next()) {
                date = rs.getString(1);
            } else {
                date = null;
            }

            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(IRD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return date;
    }

    public static String getMaxLastReading(String measPoint) {

        String date = null;

        try {
            
            List lista = new ArrayList<String>();
            
            lista.add(MeasurementDocuments.READINGDATE.toString());
            lista.add(MeasurementDocuments.READINGTIME.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(lista)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.READINGDATE.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.OPEN_PARENTHESIS.toSQL()
                    + SQLKeywords.SELECT.toSQL() 
                    + Function_SQL.MAX(MeasurementDocuments.READINGDATE.toString())
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASPOINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + measPoint
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true
                    + SQLKeywords.CLOSE_PARENTHESIS.toSQL()
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.READINGTIME.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.OPEN_PARENTHESIS.toSQL()
                    + SQLKeywords.SELECT.toSQL()
                    + Function_SQL.MAX(MeasurementDocuments.READINGTIME.toString())
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASPOINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + measPoint
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.READINGDATE.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.OPEN_PARENTHESIS.toSQL()
                    + SQLKeywords.SELECT.toSQL() 
                    + Function_SQL.MAX(MeasurementDocuments.READINGDATE.toString())
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASPOINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + measPoint
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true
                    + SQLKeywords.CLOSE_PARENTHESIS.toSQL()
                    + SQLKeywords.CLOSE_PARENTHESIS.toSQL();



            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();

            if (rs.next()) {
                date = rs.getString(1)+" "+rs.getString(2);
            } else {
                date = null;
            }

            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(IRD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return date;
    }

}
