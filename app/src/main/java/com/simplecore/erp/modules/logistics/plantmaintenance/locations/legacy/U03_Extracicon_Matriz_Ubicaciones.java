package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.tables.Ubications;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;

public class U03_Extracicon_Matriz_Ubicaciones {

    private static ArrayList<String[]> matriz = new ArrayList<>();

    public void extraerMatrizDatos() {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT_ALL.toSQL()
                    + DatabaseTables.LOCATIONS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Ubications.ESTATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true
                    + SQLKeywords.ORDER_BY.toSQL()
                    + Ubications.NIVEL.toString()
                    + SQLKeywords.ASC.toSQL();

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet Datos = pSt.getResultSet();
            ResultSetMetaData metaData = Datos.getMetaData();
            int contColum = metaData.getColumnCount();

            ArrayList<String[]> matriz = new ArrayList<>();

            while (Datos.next()) {
                String[] fila = new String[contColum];
                for (int i = 0; i < contColum; i++) {
                    fila[i] = Datos.getString(i + 1);
                }
                matriz.add(fila);
            }

            setMatriz(matriz);
            pSt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(U03_Extracicon_Matriz_Ubicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<String[]> getMatriz() {
        return matriz;
    }

    public void setMatriz(ArrayList<String[]> aMatriz) {
        matriz = aMatriz;
    }

}
