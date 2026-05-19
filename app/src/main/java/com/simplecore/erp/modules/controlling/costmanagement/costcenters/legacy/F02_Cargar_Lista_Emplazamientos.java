package com.simplecore.erp.modules.controlling.costmanagement.costcenters.legacy;

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

public class F02_Cargar_Lista_Emplazamientos {

    public String getIdEmplazamiento() {
        return idEmplazamiento;
    }

    public String getDescripcionEmp() {
        return descripcionEmp;
    }

    public String getIdArea() {
        return idArea;
    }

    public String getDescripcionArea() {
        return descripcionArea;
    }

    public String getIdSociedad() {
        return idSociedad;
    }

    public String getDescripcionSociedad() {
        return descripcionSociedad;
    }

    public JTable getTable() {
        return table;
    }

    public void setIdEmplazamiento(String idEmplazamiento) {
        this.idEmplazamiento = idEmplazamiento;
    }

    public void setDescripcionEmp(String descripcionEmp) {
        this.descripcionEmp = descripcionEmp;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public void setIdSociedad(String idSociedad) {
        this.idSociedad = idSociedad;
    }

    public void setDescripcionSociedad(String descripcionSociedad) {
        this.descripcionSociedad = descripcionSociedad;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    private String idEmplazamiento;
    private String descripcionEmp;
    private String idArea;
    private String descripcionArea;
    private String idSociedad;
    private String descripcionSociedad;
    private JTable table;

    public void loadData() {

        try {
            DefaultTableModel model = (DefaultTableModel) getTable().getModel();

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.SELECT_ALL.toSQL()
                    + DatabaseTables.emplazement.tableName();

            st = conexion.prepareStatement(query);
            st.executeQuery();

            ResultSet Datos = st.getResultSet();
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
            st.close();
            
            
            

        } catch (SQLException ex) {
            Logger.getLogger(F02_Cargar_Lista_Emplazamientos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
