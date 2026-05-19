package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o04_approval_of_orders.auxiliares;


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

public class O04_Cargar_Lista_Ordenes_Aprobacion {

    public String getTABLA_SQL() {
        return TABLA_SQL;
    }

    public JTable getTABLA() {
        return TABLA;
    }

    public void setTABLA_SQL(String TABLA_SQL) {
        this.TABLA_SQL = TABLA_SQL;
    }

    public void setTABLA(JTable TABLA) {
        this.TABLA = TABLA;
    }

    private String TABLA_SQL;
    private JTable TABLA;

    public void cargarListadoOrdenes() {

        try {
            
            DefaultTableModel model;

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT_ALL.toSQL()
                    + getTABLA_SQL();

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet Datos = pSt.getResultSet();
            ResultSetMetaData MetaDatos = Datos.getMetaData();

            int CantCol = MetaDatos.getColumnCount();
            Object[] columnasTabla = new Object[CantCol];
            
            
            for (int i = 0; i < CantCol; i++) {
                columnasTabla[i] = MetaDatos.getColumnName(i + 1);
            }

            model = new DefaultTableModel(null,columnasTabla){
                @Override
                public boolean isCellEditable(int row, int column) {
                    if(column==18){
                        return true;
                    }
                    if(column==19){
                        return true;
                    }
                        return false;                    
                    
                }                        
                
            };

            while (Datos.next()) {
                Object[] filas = new Object[CantCol];
                for (int i = 0; i < CantCol; i++) {
                    filas[i] = Datos.getObject(i + 1);
                }
                model.addRow(filas);
            }
            
            model.addColumn("Aprove");
            model.addColumn("Reject");
            
            getTABLA().setModel(model);            
            pSt.close();
            

        } catch (SQLException ex) {
            Logger.getLogger(O04_Cargar_Lista_Ordenes_Aprobacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
