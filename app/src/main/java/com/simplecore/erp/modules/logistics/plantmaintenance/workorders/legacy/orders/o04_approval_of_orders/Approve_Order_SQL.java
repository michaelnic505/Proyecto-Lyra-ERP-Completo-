package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o04_approval_of_orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Modify_Statement;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.WorkOrders;

public class Approve_Order_SQL {

    private String order;
    private String codigoEstatus;
    private String descripcionEstatus;
    private String fechaAprobacion;
    private String horaAprobacion;
    private String aprobadaPor;

    public void processOrder(String order) {
        this.setOrder(order);

        try {
            List<String> fieldList = new ArrayList();

            fieldList.add(WorkOrders.STATUS_CODE.toString());
            fieldList.add(WorkOrders.STATUS_DESCRIPTION.toString());
            fieldList.add(WorkOrders.APPROVAL_DATE_ORDER.toString());
            fieldList.add(WorkOrders.AUTHORIZATION_TIME_ORDER.toString());
            fieldList.add(WorkOrders.ORDER_APPROVED_BY.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String modify = SQLKeywords.UPDATE.toSQL()
                    + DatabaseTables.WORK_ORDERS.tableName()
                    + SQLKeywords.SET.toSQL()
                    + SQL_Modify_Statement.setModifyFields(fieldList)
                    + SQLKeywords.WHERE.toSQL()
                    + WorkOrders.ORDER_NUM.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + order;

            st = conexion.prepareStatement(modify);
            st.setString(1, getCodigoEstatus());
            st.setString(2, getDescripcionEstatus());
            st.setString(3, getFechaAprobacion());
            st.setString(4, getHoraAprobacion());
            st.setString(5, getAprobadaPor());

            st.executeUpdate();

            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(Approve_Order_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getOrder() {
        return order;
    }

    public String getCodigoEstatus() {
        return codigoEstatus;
    }

    public String getDescripcionEstatus() {
        return descripcionEstatus;
    }

    public String getFechaAprobacion() {
        return fechaAprobacion;
    }

    public String getHoraAprobacion() {
        return horaAprobacion;
    }

    public String getAprobadaPor() {
        return aprobadaPor;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setCodigoEstatus(String codigoEstatus) {
        this.codigoEstatus = codigoEstatus;
    }

    public void setDescripcionEstatus(String descripcionEstatus) {
        this.descripcionEstatus = descripcionEstatus;
    }

    public void setFechaAprobacion(String fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public void setHoraAprobacion(String horaAprobacion) {
        this.horaAprobacion = horaAprobacion;
    }

    public void setAprobadaPor(String aprobadaPor) {
        this.aprobadaPor = aprobadaPor;
    }

}
