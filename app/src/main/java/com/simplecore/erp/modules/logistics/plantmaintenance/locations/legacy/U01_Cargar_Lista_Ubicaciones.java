package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.Ubications;

/**
 *
 * @author user
 */
public class U01_Cargar_Lista_Ubicaciones {

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    private JTable table;

    public void cargarDatos() {

        try {
            
            DefaultTableModel model = (DefaultTableModel) getTable().getModel();

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + Ubications.ID_UBICACION.toString()
                    + Ubications.DENOMINACION_UBICACION.toString()
                    + Ubications.ID_UBICACION_SUPERIOR.toString()
                    + Ubications.DENOMINACION_UBICACION_SUP.toString()
                    + Ubications.NIVEL.toString()   
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.LOCATIONS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Ubications.ESTATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true;

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet Datos = pSt.getResultSet();
            ResultSetMetaData MetaDatos = Datos.getMetaData();

            int CantCol = MetaDatos.getColumnCount();

            while (Datos.next()) {

                Object[] filas = new Object[CantCol];

                for (int i = 0; i < CantCol; i++) {
                    filas[i] = Datos.getObject(i + 1);
                }

                model.addRow(filas);
            }

            
            getTable().setModel(model);
            pSt.close();
            
            
            

        } catch (SQLException ex) {
            Logger.getLogger(U01_Cargar_Lista_Ubicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
