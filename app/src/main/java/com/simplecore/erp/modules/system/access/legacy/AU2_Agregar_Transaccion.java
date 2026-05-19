package com.simplecore.erp.modules.system.access.legacy;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;


public class AU2_Agregar_Transaccion {

    public String getTRANSACCION() {
        return TRANSACCION;
    }

    public String getNOMBRE_TRANSACCION() {
        return NOMBRE_TRANSACCION;
    }

    public String getMODULO_TRANSACCION() {
        return MODULO_TRANSACCION;
    }

    public boolean isL1() {
        return L1;
    }

    public boolean isL2() {
        return L2;
    }

    public boolean isL3() {
        return L3;
    }

    public boolean isL4() {
        return L4;
    }

    public boolean isL5() {
        return L5;
    }

    public boolean isR6() {
        return R6;
    }

    public void setTRANSACCION(String TRANSACCION) {
        this.TRANSACCION = TRANSACCION;
    }

    public void setNOMBRE_TRANSACCION(String NOMBRE_TRANSACCION) {
        this.NOMBRE_TRANSACCION = NOMBRE_TRANSACCION;
    }

    public void setMODULO_TRANSACCION(String MODULO_TRANSACCION) {
        this.MODULO_TRANSACCION = MODULO_TRANSACCION;
    }

    public void setL1(boolean L1) {
        this.L1 = L1;
    }

    public void setL2(boolean L2) {
        this.L2 = L2;
    }

    public void setL3(boolean L3) {
        this.L3 = L3;
    }

    public void setL4(boolean L4) {
        this.L4 = L4;
    }

    public void setL5(boolean L5) {
        this.L5 = L5;
    }

    public void setR6(boolean R6) {
        this.R6 = R6;
    }
    
    private String TRANSACCION;
    private String NOMBRE_TRANSACCION;
    private String MODULO_TRANSACCION;
    private boolean L1;
    private boolean L2;
    private boolean L3;
    private boolean L4;
    private boolean L5;
    private boolean R6;


    public void guardarTransaccion() {

        try {

            Connection conexion = PooledConnectionService.getConnection();

            PreparedStatement pSt = null;
            String query = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.BUSINESS_TRANSACTIONS.tableName()
                    + SentenceValues.setValues(9);

            pSt = conexion.prepareStatement(query);
            pSt.setString(1, getTRANSACCION());
            pSt.setString(2, getNOMBRE_TRANSACCION());
            pSt.setString(3, getMODULO_TRANSACCION());
            pSt.setBoolean(4, isL1());
            pSt.setBoolean(5, isL2());
            pSt.setBoolean(6, isL3());
            pSt.setBoolean(7, isL4());
            pSt.setBoolean(8, isL5());
            pSt.setBoolean(9, isR6());
            
            pSt.executeUpdate();
            pSt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AU2_Agregar_Transaccion.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
