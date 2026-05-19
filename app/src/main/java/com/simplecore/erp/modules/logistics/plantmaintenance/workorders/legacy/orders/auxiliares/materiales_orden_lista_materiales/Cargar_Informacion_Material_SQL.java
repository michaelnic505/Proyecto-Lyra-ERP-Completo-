package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.materiales_orden_lista_materiales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.alta_materiales_almacen;

/**
 *
 * @author user
 */
public class Cargar_Informacion_Material_SQL {

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    private String codigo;
    private String descripcionMaterial;
    private String um;
    private String precioUnit;
    private String almacen;
    private boolean existe=true;

    public void cargar(int codigo) {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT.toSQL()
                    + alta_materiales_almacen.DESCRIPCION_MATERIAL.toString()
                    + alta_materiales_almacen.ID_UM.toString()
                    + alta_materiales_almacen.PRECIO_UNITARIO.toString()
                    + alta_materiales_almacen.CODIGO_ALMACEN.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MATERIAL_WAREHOUSE_REGISTRATION.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + alta_materiales_almacen.CODIGO_MATERIAL.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + codigo;
                    
                    
                    
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet rs = pSt.getResultSet();

                if (rs.next()) {

                    setDescripcionMaterial(rs.getString(1));
                    setUm(rs.getString(2));
                    setPrecioUnit(rs.getString(3));
                    setAlmacen(rs.getString(4));

                }else{
                    setExiste(false);
                }

            
            pSt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Cargar_Informacion_Material_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDescripcionMaterial() {
        return descripcionMaterial;
    }

    public String getUm() {
        return um;
    }

    public String getPrecioUnit() {
        return precioUnit;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setDescripcionMaterial(String descripcionMaterial) {
        this.descripcionMaterial = descripcionMaterial;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public void setPrecioUnit(String precioUnit) {
        this.precioUnit = precioUnit;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
