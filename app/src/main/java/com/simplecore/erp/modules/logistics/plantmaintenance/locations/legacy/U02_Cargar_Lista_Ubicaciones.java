
package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.tables.Ubications;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;

public class U02_Cargar_Lista_Ubicaciones {

    LyraTableModel modelo;

    public void cargarListaUbicaciones(JTable table) {

        try {
            modelo = (LyraTableModel) table.getModel();
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT.toSQL()
                    + Ubications.ID_UBICACION.toString()
                    + Ubications.DENOMINACION_UBICACION.toString()
                    + Ubications.ID_UBICACION_SUPERIOR.toString()
                    + Ubications.DENOMINACION_UBICACION_SUP.toString()
                    + Ubications.CENTRO_COSTOS.toString()
                    + Ubications.DESCRIPCION_CC.toString()
                    + Ubications.EMPLAZAMIENTO.toString()
                    + Ubications.DESCRIPCION_EMP.toString()
                    + Ubications.AREA.toString()
                    + Ubications.DESCRIPCION_AREA.toString()
                    + Ubications.SOCIEDAD.toString()
                    + Ubications.DESCRIPCION_SOCIEDAD.toString()  
                    + Ubications.GRUPO_PLANIF.toString()  
                    + Ubications.DESCRIPCION_G_PLANIF.toString()  
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.LOCATIONS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Ubications.TIPO_REGISTRO.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + UbicationType.L.getType()    
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + SQLKeywords.AND.toSQL()
                    + Ubications.ESTATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true                    
                    ;

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
                modelo.addRow(filas);

            }
            
            table.setModel(modelo);            
            pSt.close();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(U02_Cargar_Lista_Ubicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
