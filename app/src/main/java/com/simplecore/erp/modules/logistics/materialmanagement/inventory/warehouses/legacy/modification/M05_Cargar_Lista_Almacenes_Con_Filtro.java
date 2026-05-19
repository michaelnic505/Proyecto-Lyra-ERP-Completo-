
package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.modification;



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
import com.simplecore.erp.config.database.utils.CampoDentroPorcent;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.almacenes;


public class M05_Cargar_Lista_Almacenes_Con_Filtro {

    public String getCODIGO_ALMACEN() {
        return CODIGO_ALMACEN;
    }

    public String getDESCRIPCION_ALMACEN() {
        return DESCRIPCION_ALMACEN;
    }

    public String getTIPO_ALMACEN() {
        return TIPO_ALMACEN;
    }

    public String getEMPRESA_ALMACEN() {
        return EMPRESA_ALMACEN;
    }

    public void setCODIGO_ALMACEN(String CODIGO_ALMACEN) {
        this.CODIGO_ALMACEN = CODIGO_ALMACEN;
    }

    public void setDESCRIPCION_ALMACEN(String DESCRIPCION_ALMACEN) {
        this.DESCRIPCION_ALMACEN = DESCRIPCION_ALMACEN;
    }

    public void setTIPO_ALMACEN(String TIPO_ALMACEN) {
        this.TIPO_ALMACEN = TIPO_ALMACEN;
    }

    public void setEMPRESA_ALMACEN(String EMPRESA_ALMACEN) {
        this.EMPRESA_ALMACEN = EMPRESA_ALMACEN;
    }

    public JTable getTABLA_JTABLE() {
        return TABLA_JTABLE;
    }

    public void setTABLA_JTABLE(JTable TABLA_JTABLE) {
        this.TABLA_JTABLE = TABLA_JTABLE;
    }
    
    private String CODIGO_ALMACEN;
    private String DESCRIPCION_ALMACEN;    
    private String TIPO_ALMACEN; 
    private String EMPRESA_ALMACEN;
    private JTable TABLA_JTABLE;
    private DefaultTableModel model;
    
    
    public void cargar_Lista_Almacen_Filtrado() {

        model = new DefaultTableModel();

        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;
       
        String query = SQLKeywords.SELECT_ALL.toSQL()
                + DatabaseTables.Almacenes.tableName()
                + SQLKeywords.WHERE.toSQL()
                + almacenes.CODIGO_ALMACEN.toString()
                + SQLKeywords.LIKE.toSQL()
                + CampoDentroPorcent.meterTexto(getCODIGO_ALMACEN())
                + SQLKeywords.OR.toSQL()
                + almacenes.DESCRIPCION_ALMACEN.toString()
                + SQLKeywords.LIKE.toSQL()
                + CampoDentroPorcent.meterTexto(getDESCRIPCION_ALMACEN())
                + SQLKeywords.OR.toSQL()
                + almacenes.DESCRIPCION_TIPO_ALMACEN.toString()
                + SQLKeywords.LIKE.toSQL()
                + CampoDentroPorcent.meterTexto(getTIPO_ALMACEN())
                + SQLKeywords.OR.toSQL()
                + almacenes.DESCRIPCION_COMPANIA.toString()
                + SQLKeywords.LIKE.toSQL()
                + CampoDentroPorcent.meterTexto(getEMPRESA_ALMACEN());

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

            model.setColumnIdentifiers(columnasTabla);

            while (Datos.next()) {

                Object[] filas = new Object[CantCol];

                for (int i = 0; i < CantCol; i++) {
                    filas[i] = Datos.getObject(i + 1);
                }

                model.addRow(filas);

            }

            getTABLA_JTABLE().setModel(model);
            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(M05_Cargar_Lista_Almacenes_Con_Filtro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
