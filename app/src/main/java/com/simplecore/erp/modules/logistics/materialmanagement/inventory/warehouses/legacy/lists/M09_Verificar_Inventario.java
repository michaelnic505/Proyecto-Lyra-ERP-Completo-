
package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.lists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.Function_SQL;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.movimientos_material_almacen;


public class M09_Verificar_Inventario {    

    public String getCODIGO_MATERIAL() {
        return CODIGO_MATERIAL;
    }

    public void setCODIGO_MATERIAL(String CODIGO_MATERIAL) {
        this.CODIGO_MATERIAL = CODIGO_MATERIAL;
    }

    public double getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(double CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }
    
    private String CODIGO_MATERIAL;
    private double CANTIDAD;
            
    public void verificarInventario(){
        
        try {

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + Function_SQL.SUM(movimientos_material_almacen.CANTIDAD.toString())
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.Movimientos_material.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + movimientos_material_almacen.CODIGO_MATERIAL.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + getCODIGO_MATERIAL()
                    + SQLKeywords.GROUP_BY.toSQL()
                    + movimientos_material_almacen.CODIGO_MATERIAL.toString()
                    ;
            
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet Datos = pSt.getResultSet();
            
            if(Datos.next()){
                setCANTIDAD(Datos.getDouble(1));
            }
            
            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(M09_Verificar_Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        
        
    }
    
    
}
