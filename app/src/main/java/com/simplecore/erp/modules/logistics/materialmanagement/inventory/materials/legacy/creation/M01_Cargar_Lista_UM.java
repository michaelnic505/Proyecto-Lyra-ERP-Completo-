
package com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.creation;



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


public class M01_Cargar_Lista_UM {

    public String getTABLA_SQL() {
        return TABLA_SQL;
    }

    public JTable getJTABLA() {
        return JTABLA;
    }

    public void setTABLA_SQL(String TABLA_SQL) {
        this.TABLA_SQL = TABLA_SQL;
    }

    public void setJTABLA(JTable JTABLA) {
        this.JTABLA = JTABLA;
    }
    
    private String TABLA_SQL;
    private JTable JTABLA;
    private DefaultTableModel Modelo;
    
    public void cargar_Unidad_Medida() {
        
        Modelo = new DefaultTableModel();

        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;
        String query = SQLKeywords.SELECT_ALL.toSQL()
                + getTABLA_SQL();

        try {
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet Datos = pSt.getResultSet();
            ResultSetMetaData MetaDatos = Datos.getMetaData();

            int CantCol = MetaDatos.getColumnCount();
            Object[] columnasTabla = new Object[CantCol];

            for (int i = 0; i < CantCol; i++) {
                columnasTabla[i] = MetaDatos.getColumnName(i + 1);
            }

            Modelo.setColumnIdentifiers(columnasTabla);

            while (Datos.next()) {
                Object[] filas = new Object[CantCol];
                for (int i = 0; i < CantCol; i++) {
                    filas[i] = Datos.getObject(i + 1);
                }
                Modelo.addRow(filas);
            }

            
            
            getJTABLA().setModel(Modelo);
            pSt.close();
            
            

        } catch (SQLException ex) {
            Logger.getLogger(M01_Cargar_Lista_UM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
}
