package com.simplecore.erp.modules.system.users;

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
import com.simplecore.erp.config.database.DatabaseTables;

public class Consultar_Lista_Usuarios {

    public static void extraerUsuarios(JTable tabla) {

        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        limpiarTablas(modelo);

        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;
        String query = "select * from " + DatabaseTables.USERS_SYSTEM.tableName() + " order by Primer_Nombre ASC";

        try {
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            ResultSet rs = pSt.getResultSet();
            ResultSetMetaData rsm = rs.getMetaData();

            int canCol = rsm.getColumnCount();

            Object[] columnas = new Object[canCol];

            for (int i = 0; i < canCol; i++) {

                columnas[i] = rsm.getColumnName(i + 1);

            }
            modelo.setColumnIdentifiers(columnas);

            while (rs.next()) {

                Object[] filas = new Object[canCol];

                for (int i = 0; i < canCol; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
            tabla.setModel(modelo);

            //REDIMENSIONA LAS COLUMNAS
            for (int i = 0; i < tabla.getColumnCount(); i++) {

                int longitud = tabla.getColumnName(i).length() * 15;

                tabla.getColumnModel().getColumn(i).setMinWidth(longitud);
                tabla.getColumnModel().getColumn(i).setPreferredWidth(longitud);
            }

            //ESTA LINEA HACE QUE LA TABLA SEA PUEDA HACER SCROLL HORIZONTALMENTE
            tabla.setAutoResizeMode(0);

        } catch (SQLException ex) {
            Logger.getLogger(Consultar_Lista_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void limpiarTablas(DefaultTableModel model) {

        int filas = model.getRowCount() - 1;
        for (int i = filas; i > -1; i--) {
            model.removeRow(i);
        }

    }

}
