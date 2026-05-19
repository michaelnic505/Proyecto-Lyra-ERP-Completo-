package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableAuxiliar;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Statements;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.MeasuringPoints;

public class Measurement_Point_List {


    public void loadData(LyraTableAuxiliar table) {

        try {
            List<String> list = new ArrayList();

            list.add(MeasuringPoints.MEASURE_POINT.toString());
            list.add(MeasuringPoints.OBJECT_MEAS_POINT.toString());
            list.add(MeasuringPoints.MEAS_POSITION.toString());
            list.add(MeasuringPoints.MEAS_TYPE.toString());
            list.add(MeasuringPoints.DESCRIPTION.toString());
            list.add(MeasuringPoints.EQUIPMENT.toString());
            list.add(MeasuringPoints.EQUIPMENT_DESCRIPTION.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(list)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASURING_POINTS.tableName();

            st = conexion.prepareStatement(query);
            st.executeQuery();

            ResultSet rs = st.getResultSet();
            ResultSetMetaData meta = rs.getMetaData();

            int colCant = meta.getColumnCount();

            while (rs.next()) {

                String[] fila = new String[colCant];

                for (int i = 0; i < fila.length; i++) {
                    fila[i] = rs.getString(i + 1);
                }
                ((DefaultTableModel) table.getModel()).addRow(fila);
            }

            st.close();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Measurement_Point_List.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
