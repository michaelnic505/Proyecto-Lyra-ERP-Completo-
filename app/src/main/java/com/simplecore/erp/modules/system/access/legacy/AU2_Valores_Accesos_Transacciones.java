package com.simplecore.erp.modules.system.access.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.tables.transacciones;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;

public class AU2_Valores_Accesos_Transacciones {

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

    public String getTRANSACCION() {
        return TRANSACCION;
    }

    public void setTRANSACCION(String TRANSACCION) {
        this.TRANSACCION = TRANSACCION;
    }

    private String TRANSACCION;
    private boolean L1;
    private boolean L2;
    private boolean L3;
    private boolean L4;
    private boolean L5;
    private boolean R6;

    public void cargar_Datos() {

        Connection conexion = PooledConnectionService.getConnection();

        PreparedStatement pSt = null;
        String query = SQLKeywords.SELECT.toSQL()
                + transacciones.L1.toString()
                + transacciones.L2.toString()
                + transacciones.L3.toString()
                + transacciones.L4.toString()
                + transacciones.L5.toString()
                + transacciones.R6.toString()
                + SQLKeywords.FROM.toSQL()
                + DatabaseTables.BUSINESS_TRANSACTIONS.tableName()
                + SQLKeywords.WHERE.toSQL()
                + transacciones.TRANSACCION.toString()
                + SQLKeywords.EQUALS.toSQL()
                + SQLKeywords.SINGLE_QUOTE.toSQL()
                + getTRANSACCION()
                + SQLKeywords.SINGLE_QUOTE.toSQL();

        try {
            pSt = conexion.prepareStatement(query);

            pSt.executeQuery();
            ResultSet Data = pSt.getResultSet();

            while (Data.next()) {
              
                    setL1(Data.getBoolean(1));              
                    setL2(Data.getBoolean(2));
                    setL3(Data.getBoolean(3));
                    setL4(Data.getBoolean(4));
                    setL5(Data.getBoolean(5));
                    setR6(Data.getBoolean(6));
            }
            
            pSt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AU2_Valores_Accesos_Transacciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
