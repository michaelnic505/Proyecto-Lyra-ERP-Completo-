
package com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.modification;



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
import com.simplecore.erp.config.database.tables.materiales;


public class M02_Cargar_Lista_Materiales_Con_Filtro {

    public String getCODIGO_MATERIAL() {
        return CODIGO_MATERIAL;
    }

    public String getDESCRIPCION_MATERIAL() {
        return DESCRIPCION_MATERIAL;
    }

    public String getMARCA() {
        return MARCA;
    }


    public JTable getTABLA_JTABLE() {
        return TABLA_JTABLE;
    }

    public void setCODIGO_MATERIAL(String CODIGO_MATERIAL) {
        this.CODIGO_MATERIAL = CODIGO_MATERIAL;
    }

    public void setDESCRIPCION_MATERIAL(String DESCRIPCION_MATERIAL) {
        this.DESCRIPCION_MATERIAL = DESCRIPCION_MATERIAL;
    }

    public void setMARCA(String MARCA) {
        this.MARCA = MARCA;
    }

    public void setTABLA_JTABLE(JTable TABLA_JTABLE) {
        this.TABLA_JTABLE = TABLA_JTABLE;
    }
    
    private String CODIGO_MATERIAL;
    private String DESCRIPCION_MATERIAL;    
    private String MARCA;   
    private JTable TABLA_JTABLE;
    private DefaultTableModel model;
    
    
    public void cargar_Lista_Material_Filtrado() {

        model = new DefaultTableModel();
        
        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;
        
        
        String query = SQLKeywords.SELECT_ALL.toSQL()
                + DatabaseTables.Materiales.tableName()
                + SQLKeywords.WHERE.toSQL()
                + materiales.CODIGO_MATERIAL.toString()
                + SQLKeywords.LIKE.toSQL()
                + CampoDentroPorcent.meterTexto(getCODIGO_MATERIAL())
                + SQLKeywords.OR.toSQL()
                + materiales.DESCRIPCION_MATERIAL.toString()
                + SQLKeywords.LIKE.toSQL()
                + CampoDentroPorcent.meterTexto(getDESCRIPCION_MATERIAL())
                + SQLKeywords.OR.toSQL()
                + materiales.MARCA.toString()
                + SQLKeywords.LIKE.toSQL()
                + CampoDentroPorcent.meterTexto(getMARCA());

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
            Logger.getLogger(M02_Cargar_Lista_Materiales_Con_Filtro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
