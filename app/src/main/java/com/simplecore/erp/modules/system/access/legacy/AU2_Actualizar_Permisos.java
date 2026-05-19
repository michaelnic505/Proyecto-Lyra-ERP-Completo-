package com.simplecore.erp.modules.system.access.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.tables.transacciones;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;

public class AU2_Actualizar_Permisos {

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

    public void actualizarPermisos() {

        try (Connection connection = PooledConnectionService.getConnection()) {

            StringBuilder stringBuilder = new StringBuilder()
                    .append(SQLKeywords.UPDATE.toSQL())
                    .append(DatabaseTables.BUSINESS_TRANSACTIONS.tableName())
                    .append(SQLKeywords.SET.toSQL())
                    .append(transacciones.L1.toString()).append("=?,")
                    .append(transacciones.L2.toString()).append("=?,")
                    .append(transacciones.L3.toString()).append("=?,")
                    .append(transacciones.L4.toString()).append("=?,")
                    .append(transacciones.L5.toString()).append("=?,")
                    .append(transacciones.R6.toString()).append("=?")
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(transacciones.TRANSACCION.toString()).append("=?");

            String query = stringBuilder.toString();

            try (PreparedStatement pSt = connection.prepareStatement(query)) {
                pSt.setBoolean(1, isL1());
                pSt.setBoolean(2, isL2());
                pSt.setBoolean(3, isL3());
                pSt.setBoolean(4, isL4());
                pSt.setBoolean(5, isL5());
                pSt.setBoolean(6, isR6());
                pSt.setString(7, TRANSACCION);
                

                pSt.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(AU2_Actualizar_Permisos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
