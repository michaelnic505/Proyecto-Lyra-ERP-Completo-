
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o04_approval_of_orders.auxiliares;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.ordenes_de_mantenimiento;


public class O04_Aprobar_Orden {

    public String getNUM_ORDEN() {
        return NUM_ORDEN;
    }

    public void setNUM_ORDEN(String NUM_ORDEN) {
        this.NUM_ORDEN = NUM_ORDEN;
    }

    public String getMODIFICADA_POR() {
        return MODIFICADA_POR;
    }

    public String getMODIFICADA_EN() {
        return MODIFICADA_EN;
    }

    public void setMODIFICADA_POR(String MODIFICADA_POR) {
        this.MODIFICADA_POR = MODIFICADA_POR;
    }

    public void setMODIFICADA_EN(String MODIFICADA_EN) {
        this.MODIFICADA_EN = MODIFICADA_EN;
    }

    public String getID_ESTATUS() {
        return ID_ESTATUS;
    }

    public String getESTATUS() {
        return ESTATUS;
    }

    public void setID_ESTATUS(String ID_ESTATUS) {
        this.ID_ESTATUS = ID_ESTATUS;
    }

    public void setESTATUS(String ESTATUS) {
        this.ESTATUS = ESTATUS;
    }


    
    private String NUM_ORDEN;
    private String ID_ESTATUS;
    private String ESTATUS;
    private String MODIFICADA_POR;
    private String MODIFICADA_EN;

    public void aprobarOrden() {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;

            String query = SQLKeywords.UPDATE.toSQL()
                    + DatabaseTables.WORK_ORDERS.tableName()
                    + SQLKeywords.SET.toSQL()
                    + ordenes_de_mantenimiento.ID_ESTATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.QUESTION_MARK.toSQL()
                    + ordenes_de_mantenimiento.ESTATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.QUESTION_MARK.toSQL()
                    + ordenes_de_mantenimiento.MODIFICADA_POR.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.QUESTION_MARK.toSQL()
                    + ordenes_de_mantenimiento.MODIFICADA_EN.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.QUESTION_MARK.toSQL()
                    + SQLKeywords.WHERE.toSQL()
                    + ordenes_de_mantenimiento.NUM_ORDER.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + getNUM_ORDEN();
            
            pSt = conexion.prepareStatement(query);
            pSt.setString(1, getID_ESTATUS());
            pSt.setString(2, getESTATUS());
            pSt.setString(3, getMODIFICADA_POR());
            pSt.setString(4, getMODIFICADA_EN());
            pSt.executeUpdate();

            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(O04_Aprobar_Orden.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
