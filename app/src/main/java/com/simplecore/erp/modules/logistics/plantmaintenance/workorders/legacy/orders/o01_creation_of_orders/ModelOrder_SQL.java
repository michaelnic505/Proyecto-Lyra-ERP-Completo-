package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Statements;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.WorkOrders;

public class ModelOrder_SQL {

    private boolean parada;
    private String tituloOrden;
    private String textoExtendido;

    private String tipoOrden;
    private String descripcionTipoOrden;

    private String codigoSistema;
    private String descripcionSistema;
    private String codigoComponente;
    private String descripcionComponente;
    private String codigoSintoma;
    private String descripcionSintoma;

    private String solicitante;
    private String nombreSolicitante;

    private String responsable;
    private String nombreResponsable;

    public void getData(String order) {

        try {
            List<String> lista = new ArrayList();
            
            lista.add(WorkOrders.STOP.toString());
            lista.add(WorkOrders.ORDER_TITLE.toString());
            lista.add(WorkOrders.EXTENDED_DESCRIPTION.toString());
            lista.add(WorkOrders.TYPE_ORDER_CODE.toString());
            lista.add(WorkOrders.TYPE_ORDER_DESCRIPTION.toString());
            lista.add(WorkOrders.SYSTEM_CODE.toString());
            lista.add(WorkOrders.SYSTEM_DESCRIPTION.toString());
            lista.add(WorkOrders.COMPONENT_CODE.toString());
            lista.add(WorkOrders.COMPONENT_DESCRIPTION.toString());
            lista.add(WorkOrders.SYMPTOM_CODE.toString());
            lista.add(WorkOrders.SYMPTOM_DESCRIPTION.toString());
            lista.add(WorkOrders.APPLICANT_CODE.toString());
            lista.add(WorkOrders.APPLICANT_DESCRIPTION.toString());
            lista.add(WorkOrders.RESPONSIBLE_CODE.toString());
            lista.add(WorkOrders.DESCRIPTION_OF_RESPONSIBLE.toString());
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(lista)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.WORK_ORDERS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + WorkOrders.ORDER_NUM.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + order;
            
            pst = conexion.prepareStatement(query);
            pst.executeQuery();
            
            
            ResultSet rs = pst.getResultSet();

            if (rs.next()) {
                
                setParada(rs.getBoolean(WorkOrders.STOP.toString()));
                setTituloOrden(rs.getString(WorkOrders.ORDER_TITLE.toString()));
                setTextoExtendido(rs.getString(WorkOrders.EXTENDED_DESCRIPTION.toString()));
                setTipoOrden(rs.getString(WorkOrders.TYPE_ORDER_CODE.toString()));
                setDescripcionTipoOrden(rs.getString(WorkOrders.TYPE_ORDER_DESCRIPTION.toString()));
                setCodigoSistema(rs.getString(WorkOrders.SYSTEM_CODE.toString()));
                setDescripcionSistema(rs.getString(WorkOrders.SYSTEM_DESCRIPTION.toString()));
                setCodigoComponente(rs.getString(WorkOrders.COMPONENT_CODE.toString()));
                setDescripcionComponente(rs.getString(WorkOrders.COMPONENT_DESCRIPTION.toString()));
                setCodigoSintoma(rs.getString(WorkOrders.SYMPTOM_CODE.toString()));
                setDescripcionSintoma(rs.getString(WorkOrders.SYMPTOM_DESCRIPTION.toString()));
                setSolicitante(rs.getString(WorkOrders.APPLICANT_CODE.toString()));
                setNombreSolicitante(rs.getString(WorkOrders.APPLICANT_DESCRIPTION.toString()));
                setResponsable(rs.getString(WorkOrders.RESPONSIBLE_CODE.toString()));
                setNombreResponsable(rs.getString(WorkOrders.DESCRIPTION_OF_RESPONSIBLE.toString()));

            }
            
            pst.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ModelOrder_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isParada() {
        return parada;
    }

    public String getTituloOrden() {
        return tituloOrden;
    }

    public String getTextoExtendido() {
        return textoExtendido;
    }

    public String getTipoOrden() {
        return tipoOrden;
    }

    public String getDescripcionTipoOrden() {
        return descripcionTipoOrden;
    }

    public String getCodigoSistema() {
        return codigoSistema;
    }

    public String getDescripcionSistema() {
        return descripcionSistema;
    }

    public String getCodigoComponente() {
        return codigoComponente;
    }

    public String getDescripcionComponente() {
        return descripcionComponente;
    }

    public String getCodigoSintoma() {
        return codigoSintoma;
    }

    public String getDescripcionSintoma() {
        return descripcionSintoma;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public String getResponsable() {
        return responsable;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setParada(boolean parada) {
        this.parada = parada;
    }

    public void setTituloOrden(String tituloOrden) {
        this.tituloOrden = tituloOrden;
    }

    public void setTextoExtendido(String textoExtendido) {
        this.textoExtendido = textoExtendido;
    }

    public void setTipoOrden(String tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public void setDescripcionTipoOrden(String descripcionTipoOrden) {
        this.descripcionTipoOrden = descripcionTipoOrden;
    }

    public void setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    public void setDescripcionSistema(String descripcionSistema) {
        this.descripcionSistema = descripcionSistema;
    }

    public void setCodigoComponente(String codigoComponente) {
        this.codigoComponente = codigoComponente;
    }

    public void setDescripcionComponente(String descripcionComponente) {
        this.descripcionComponente = descripcionComponente;
    }

    public void setCodigoSintoma(String codigoSintoma) {
        this.codigoSintoma = codigoSintoma;
    }

    public void setDescripcionSintoma(String descripcionSintoma) {
        this.descripcionSintoma = descripcionSintoma;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

}
