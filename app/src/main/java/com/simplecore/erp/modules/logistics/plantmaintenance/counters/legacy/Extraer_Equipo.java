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
import com.simplecore.erp.config.database.DatabaseTables.Equipments;

public class Extraer_Equipo {

    public String getEquipo() {
        return equipo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }



    private String equipo;
    private String denominacion;
    

    public void getData(String equipo) {

        try {
            this.setEquipo(equipo);
            
            List<String> lista = new ArrayList();
            lista.add(Equipments.EQUIPMENT_ID.toString());
            lista.add(Equipments.NAME.toString());

            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(lista)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.EQUIPMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Equipments.EQUIPMENT_ID.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + equipo;
            
            
            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();

            if (rs.next()) {
                
                setEquipo(rs.getString(Equipments.EQUIPMENT_ID.toString()));
                setDenominacion(rs.getString(Equipments.NAME.toString()));

            }
            
            pst.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Extraer_Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public static boolean equipmentExists(String equipo) {

        boolean exists = false;

        try {

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + Equipments.EQUIPMENT_ID.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.EQUIPMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Equipments.EQUIPMENT_ID.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + equipo;

            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();
            if (rs.next()) {
                exists = true;
            }

            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Extraer_Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

}
