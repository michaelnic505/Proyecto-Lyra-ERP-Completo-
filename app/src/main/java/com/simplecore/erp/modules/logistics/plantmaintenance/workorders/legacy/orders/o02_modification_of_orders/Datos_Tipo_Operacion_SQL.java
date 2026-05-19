package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.Tipo_Operacion_Orden_Mtto;

/**
 *
 * @author user
 */
public class Datos_Tipo_Operacion_SQL {

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getIdTipo() {
        return idTipo;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public String getCostoUnitario() {
        return costoUnitario;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setIdTipo(String idTipo) {
        this.idTipo = idTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    public void setCostoUnitario(String costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    private String idTipo;
    private String descripcionTipo;
    private String costoUnitario;
    private String unidadMedida;
    private String moneda;

    public void extraerDatos() {

        try {
            
            Connection conexion = PooledConnectionService.getConnection();            
            PreparedStatement pSt = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                        + Tipo_Operacion_Orden_Mtto.DESCRIPCION_ACTIVIDAD_MTTO.toString()
                        + Tipo_Operacion_Orden_Mtto.COSTO_UNITARIO.toString()
                        + Tipo_Operacion_Orden_Mtto.UNIDAD_MEDIDA.toString()
                        + Tipo_Operacion_Orden_Mtto.MONEDA.toString()
                        + SQLKeywords.FROM.toSQL()
                        + DatabaseTables.OPERATION_TYPE_MAINTENANCE_ORDER.tableName()
                        + SQLKeywords.WHERE.toSQL()
                        + Tipo_Operacion_Orden_Mtto.ID_ACTIVIDAD_MTTO.toString()
                        + SQLKeywords.EQUALS.toSQL()
                        + SQLKeywords.SINGLE_QUOTE.toSQL()
                        + getIdTipo()
                        + SQLKeywords.SINGLE_QUOTE.toSQL();
                    
                    
            
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet rs = pSt.getResultSet();
            
            while(rs.next()){
                
                setDescripcionTipo(rs.getString(1));
                setCostoUnitario(rs.getString(2));
                setUnidadMedida(rs.getString(3));
                setMoneda(rs.getString(4));
            }
            
            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Datos_Tipo_Operacion_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
