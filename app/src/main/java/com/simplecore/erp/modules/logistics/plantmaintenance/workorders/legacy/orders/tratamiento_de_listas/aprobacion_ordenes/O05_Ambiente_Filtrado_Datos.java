package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.tratamiento_de_listas.aprobacion_ordenes;

import com.toedter.calendar.JTextFieldDateEditor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel2;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraFrame;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Statements;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.WorkOrders;
import com.simplecore.erp.modules.controlling.areas.legacy.A01_Lista_Sociedades;
import com.simplecore.erp.modules.controlling.areas.legacy.A02_Lista_Areas;
import com.simplecore.erp.modules.controlling.costmanagement.costcenters.legacy.F02_Lista_Emplazamientos;
import com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy.U01_Lista_Centro_Costos;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.StatusOrder;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Clase_Mtto;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Criticidad;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Equipos;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Estatus;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Grupo_Planificador;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Ordenes;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Personal;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Tipo_Mtto;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Ubicaciones;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.tratamiento_de_listas.aprobacion_ordenes.AddList.DataTypeForTable;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class O05_Ambiente_Filtrado_Datos extends javax.swing.JPanel {

    
    private final JPanel thisPanel = this;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy.MM.dd");
    private String username;
    
    public O05_Ambiente_Filtrado_Datos(String username) {
        this.username = username;
        initComponents();
        addEvents();
    }
    
    private JFrame getSuperFrame(){
        return (JFrame) SwingUtilities.getWindowAncestor(mainContainerPanel);
    }

    private void removeJMenuBar() {
        getSuperFrame().remove(getSuperFrame().getJMenuBar());
    }

    private void addEvents() {

        removeJMenuBar();
        
        filtros();
        filtroListas();
        
        btnFiltrarNuevamente();
        btnProcesarConsulta();

        btnSalir();

    }
    
    public void setTitle(){
        LyraWorkspace.TitleLabel.setText("Approval of Work Orders by List");
    }
    
    private void filtros(){
        
        buscarEstatus1();
        buscarEstatus2();
        
        buscarOrden1();
        buscarOrden2();
        
        buscarTipo1();
        buscarTipo2();
        
        buscarClase1();
        buscarClase2();
        
        buscarCriticidad1();
        buscarCriticidad2();
        
        buscarEquipo1();
        buscarEquipo2();
        
        buscarUbicacion1();
        buscarUbicacion2();
        
        formatoFechas();
        setFecha1();
        setFecha2();
        
        buscarGrupoPlanif1();
        buscarGrupoPlanif2();
        
        buscarSolicitante1();
        buscarSolicitante2();
        
        buscarResponsable1();
        buscarResponsable2();
        
        buscarSociedad1();
        buscarSociedad2();
        
        buscarArea1();
        buscarArea2();
        
        buscarEmplazamiento1();
        buscarEmplazamiento2();
        
        buscarCentroCostos1();
        buscarCentroCostos2();
                
    }
    private void filtroListas(){
        
        btnFiltroListaEstatus();
        btnFiltroListaOrdenes();
        btnFiltroListaTipos();
        btnFiltroListaClases();
        btnFiltroListaCriticidad();
        btnFiltroListaEquipos();
        btnFiltroListaUbicaciones();
        btnFiltroListaFechas();
        btnFiltroListaGruposPlanif();
        btnFiltroListaSolicitantes();
        btnFiltroListaResponsables();
        btnFiltroListaSociedades();
        btnFiltroListaAreas();
        btnFiltroListaEmplazamientos();
        btnFiltroListaCentroCostos();
    
    }

    
    private String sqlStatement(){
        
            List<String> fieldList = new ArrayList();
            fieldList.add(WorkOrders.ORDER_NUM.toString());
            fieldList.add(WorkOrders.STATUS_CODE.toString());
            fieldList.add(WorkOrders.STATUS_DESCRIPTION.toString());
            fieldList.add(WorkOrders.ORDER_TITLE.toString());
            fieldList.add(WorkOrders.SCHEDULED_START_DATE.toString());
            fieldList.add(WorkOrders.SCHEDULED_END_DATE.toString());
            fieldList.add(WorkOrders.EQUIPMENT_CODE.toString());
            fieldList.add(WorkOrders.EQUIPMENT_DESCRIPTION.toString());
            fieldList.add(WorkOrders.UBICATION_CODE.toString());
            fieldList.add(WorkOrders.UBICATION_DESCRIPTION.toString());
            fieldList.add(WorkOrders.COST_CENTER_CODE.toString());
            fieldList.add(WorkOrders.COST_CENTER_DESCRIPTION.toString());
            fieldList.add(WorkOrders.ESTIMATED_COST.toString());
            fieldList.add(WorkOrders.ESTIMATED_TIME.toString());
            fieldList.add(WorkOrders.TYPE_ORDER_DESCRIPTION.toString());
            fieldList.add(WorkOrders.CLASS_ORDER_DESCRIPTION.toString());
            fieldList.add(WorkOrders.CRITICALITY_DESCRIPTION.toString());
            fieldList.add(WorkOrders.SYSTEM_DESCRIPTION.toString());
            fieldList.add(WorkOrders.COMPONENT_DESCRIPTION.toString());
            fieldList.add(WorkOrders.SYMPTOM_DESCRIPTION.toString());
            fieldList.add(WorkOrders.ORDER_PLANNED_BY.toString());
        
        String query = "";

        if (!getQuery().trim().isEmpty()) {

            query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(fieldList)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.WORK_ORDERS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + getQuery();
        } else {

            query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(fieldList)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.WORK_ORDERS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + WorkOrders.STATUS_CODE.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + StatusOrder.ST3_ORDER_UNDER_APPROVAL.getStatusCode()
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
        }

        return query;
        
    }
    private String getQuery() {

        String ordenes = "";
        String estatus = "";
        String tipos = "";
        String clases = "";
        String criticidad = "";
        String equipos = "";
        String ubicaciones = "";
        String fechas = "";
        String grupoPlanif = "";
        String solicitantes = "";
        String responsables = "";
        String sociedad = "";
        String area = "";
        String emplazamiento = "";
        String centroCostos = "";

        if (!ordenes().trim().isEmpty()) {
            ordenes = ordenes()+" ";
        }

        if (!ordenes().trim().isEmpty() && !estatus().trim().isEmpty()) {
            estatus = SQLKeywords.AND.toSQL() + estatus()+" ";

        } else if (ordenes().equals("") && !estatus().equals("")) {
            estatus = estatus()+" ";

        }

        if (!ordenes().trim().isEmpty() | !estatus().trim().isEmpty()
                &&!tipos().trim().isEmpty()) {
            
            tipos = SQLKeywords.AND.toSQL() + tipos()+" ";

        } else if (ordenes().trim().isEmpty() && estatus().trim().isEmpty() &&!tipos().trim().isEmpty()) {

            tipos = tipos()+" ";
        }

        if (!ordenes().trim().isEmpty() | !estatus().trim().isEmpty() 
                | !tipos().trim().isEmpty() && !clases().trim().isEmpty()) {

            clases = SQLKeywords.AND.toSQL() + clases()+" ";
        
        } else if (ordenes().trim().isEmpty() && estatus().trim().isEmpty() 
                && tipos().trim().isEmpty() && !clases().trim().isEmpty()) {

            clases = clases()+" ";
        }

        if (!ordenes().trim().isEmpty() | !estatus().trim().isEmpty()
                | !tipos().trim().isEmpty() | !clases().trim().isEmpty()&&!criticidad().trim().isEmpty()) {

            criticidad = SQLKeywords.AND.toSQL() + criticidad()+" ";

        } else if (ordenes().trim().isEmpty() && estatus().trim().isEmpty()
                && tipos().trim().isEmpty() && clases().trim().isEmpty() && !criticidad().trim().isEmpty()) {

            criticidad = criticidad()+" ";
        }

        if (!ordenes().trim().isEmpty() | !estatus().trim().isEmpty()
                | !tipos().trim().isEmpty() | !clases().trim().isEmpty()
                | !criticidad().trim().isEmpty()&&!equipos().trim().isEmpty()) {

            equipos = SQLKeywords.AND.toSQL() + equipos()+" ";

        } else if (ordenes().trim().isEmpty() && estatus().trim().isEmpty()
                && tipos().trim().isEmpty() && clases().trim().isEmpty()
                && criticidad().trim().isEmpty() && !equipos().trim().isEmpty()) {
            
            equipos = equipos()+" ";
        }

        if (!ordenes().trim().isEmpty() | !estatus().trim().isEmpty()
                | !tipos().trim().isEmpty() | !clases().trim().isEmpty()
                | !criticidad().trim().isEmpty() | !equipos().trim().isEmpty() && !ubicaciones().trim().isEmpty()) {

            ubicaciones = SQLKeywords.AND.toSQL() + ubicaciones()+" ";

        } else if (ordenes().trim().isEmpty() && estatus().trim().isEmpty()
                && tipos().trim().isEmpty() && clases().trim().isEmpty()
                && criticidad().trim().isEmpty() && equipos().trim().isEmpty() && !ubicaciones().trim().isEmpty()) {

            ubicaciones = ubicaciones()+" ";

        }

        if (!ordenes().trim().isEmpty() | !estatus().trim().isEmpty()
                | !tipos().trim().isEmpty() | !clases().trim().isEmpty()
                | !criticidad().trim().isEmpty() | !equipos().trim().isEmpty()
                | !ubicaciones().trim().isEmpty() && !fechas().trim().isEmpty()) {

            fechas = SQLKeywords.AND.toSQL() + fechas()+" ";

        } else if (ordenes().trim().isEmpty() && estatus().trim().isEmpty()
                && tipos().trim().isEmpty() && clases().trim().isEmpty()
                && criticidad().trim().isEmpty() && equipos().trim().isEmpty()
                && ubicaciones().trim().isEmpty() && !fechas().trim().isEmpty()) {

            fechas = fechas()+" ";

        }

        if (!ordenes().trim().isEmpty() | !estatus().trim().isEmpty()
                | !tipos().trim().isEmpty() | !clases().trim().isEmpty()
                | !criticidad().trim().isEmpty() | !equipos().trim().isEmpty()
                | !ubicaciones().trim().isEmpty() | !fechas().trim().isEmpty() && !grupoPlanif().trim().isEmpty()) {

            grupoPlanif = SQLKeywords.AND.toSQL() + grupoPlanif()+" ";

        } else if (ordenes().trim().isEmpty() && estatus().trim().isEmpty()
                && tipos().trim().isEmpty() && clases().trim().isEmpty()
                && criticidad().trim().isEmpty() && equipos().trim().isEmpty()
                && ubicaciones().trim().isEmpty() && fechas().trim().isEmpty() && !grupoPlanif().trim().isEmpty()) {

            grupoPlanif = grupoPlanif()+" ";

        }

        if (!ordenes().trim().isEmpty() | !estatus().trim().isEmpty()
                | !tipos().trim().isEmpty() | !clases().trim().isEmpty()
                | !criticidad().trim().isEmpty() | !equipos().trim().isEmpty()
                | !ubicaciones().trim().isEmpty() | !fechas().trim().isEmpty()
                | !grupoPlanif().trim().isEmpty() && !solicitantes().trim().isEmpty()) {
            
            solicitantes = SQLKeywords.AND.toSQL() + solicitantes()+" ";

        } else if (ordenes().trim().isEmpty() && estatus().trim().isEmpty()
                && tipos().trim().isEmpty() && clases().trim().isEmpty()
                && criticidad().trim().isEmpty() && equipos().trim().isEmpty()
                && ubicaciones().trim().isEmpty() && fechas().trim().isEmpty()
                && grupoPlanif().trim().isEmpty() && !solicitantes().trim().isEmpty()) {

            solicitantes = solicitantes()+" ";

        }
        if (!ordenes().trim().isEmpty() | !estatus().trim().isEmpty()
                | !tipos().trim().isEmpty() | !clases().trim().isEmpty()
                | !criticidad().trim().isEmpty() | !equipos().trim().isEmpty()
                | !ubicaciones().trim().isEmpty() | !fechas().trim().isEmpty()
                | !grupoPlanif().trim().isEmpty() | !solicitantes().trim().isEmpty()
                && !responsable().trim().isEmpty()) {

            responsables = SQLKeywords.AND.toSQL() + responsable()+" ";

        } else if (ordenes().trim().isEmpty() && estatus().trim().isEmpty()
                && tipos().trim().isEmpty() && clases().trim().isEmpty()
                && criticidad().trim().isEmpty() && equipos().trim().isEmpty()
                && ubicaciones().trim().isEmpty() && fechas().trim().isEmpty()
                && grupoPlanif().trim().isEmpty() && solicitantes().trim().isEmpty()
                && !responsable().trim().isEmpty()) {

            responsables = responsable()+" ";

        }

        if (!ordenes().trim().isEmpty() | !estatus().trim().isEmpty()
                | !tipos().trim().isEmpty() | !clases().trim().isEmpty()
                | !criticidad().trim().isEmpty() | !equipos().trim().isEmpty()
                | !ubicaciones().trim().isEmpty() | !fechas().trim().isEmpty()
                | !grupoPlanif().trim().isEmpty() | !solicitantes().trim().isEmpty()
                | !responsable().trim().isEmpty() && !sociedad().trim().isEmpty()) {

            sociedad = SQLKeywords.AND.toSQL() + sociedad()+" ";

        } else if (ordenes().trim().isEmpty() && estatus().trim().isEmpty()
                && tipos().trim().isEmpty() && clases().trim().isEmpty()
                && criticidad().trim().isEmpty() && equipos().trim().isEmpty()
                && ubicaciones().trim().isEmpty() && fechas().trim().isEmpty()
                && grupoPlanif().trim().isEmpty() && solicitantes().trim().isEmpty()
                && responsable().trim().isEmpty() && !sociedad().trim().isEmpty()) {

            sociedad = sociedad()+" ";

        }

        if (!ordenes().trim().isEmpty() | !estatus().trim().isEmpty()
                | !tipos().trim().isEmpty() | !clases().trim().isEmpty()
                | !criticidad().trim().isEmpty() | !equipos().trim().isEmpty()
                | !ubicaciones().trim().isEmpty() | !fechas().trim().isEmpty()
                | !grupoPlanif().trim().isEmpty() | !solicitantes().trim().isEmpty()
                | !responsable().trim().isEmpty() | !sociedad().trim().isEmpty() && !areas().trim().isEmpty()) {
        
            area = SQLKeywords.AND.toSQL() + areas()+" ";

        } else if (ordenes().trim().isEmpty() && estatus().trim().isEmpty()
                && tipos().trim().isEmpty() && clases().trim().isEmpty()
                && criticidad().trim().isEmpty() && equipos().trim().isEmpty()
                && ubicaciones().trim().isEmpty() && fechas().trim().isEmpty()
                && grupoPlanif().trim().isEmpty() && solicitantes().trim().isEmpty()
                && responsable().trim().isEmpty() && sociedad().trim().isEmpty() && !areas().trim().isEmpty()) {

            area = areas()+" ";

        }

        if (!ordenes().trim().isEmpty() | !estatus().trim().isEmpty()
                | !tipos().trim().isEmpty() | !clases().trim().isEmpty()
                | !criticidad().trim().isEmpty() | !equipos().trim().isEmpty()
                | !ubicaciones().trim().isEmpty() | !fechas().trim().isEmpty()
                | !grupoPlanif().trim().isEmpty() | !solicitantes().trim().isEmpty()
                | !responsable().trim().isEmpty() | !sociedad().trim().isEmpty()
                | !areas().trim().isEmpty() && !emplazamientos().trim().isEmpty()) {

            emplazamiento = SQLKeywords.AND.toSQL() + emplazamientos()+" ";

        } else if (ordenes().trim().isEmpty()&& estatus().trim().isEmpty()
                && tipos().trim().isEmpty() && clases().trim().isEmpty()
                && criticidad().trim().isEmpty() && equipos().trim().isEmpty()
                && ubicaciones().trim().isEmpty() && fechas().trim().isEmpty()
                && grupoPlanif().trim().isEmpty() && solicitantes().trim().isEmpty()
                && responsable().trim().isEmpty() && sociedad().trim().isEmpty()
                && areas().trim().isEmpty() && !emplazamientos().trim().isEmpty()) {

            emplazamiento = emplazamientos()+" ";

        }

        if (!ordenes().trim().isEmpty() | !estatus().trim().isEmpty()
                | !tipos().trim().isEmpty() | !clases().trim().isEmpty()
                | !criticidad().trim().isEmpty()| !equipos().trim().isEmpty()
                | !ubicaciones().trim().isEmpty() | !fechas().trim().isEmpty()
                | !grupoPlanif().trim().isEmpty() | !solicitantes().trim().isEmpty()
                | !responsable().trim().isEmpty() | !sociedad().trim().isEmpty()
                | !areas().trim().isEmpty() | !emplazamientos().trim().isEmpty() && !centroCostos().trim().isEmpty()) {

            centroCostos = SQLKeywords.AND.toSQL() + centroCostos()+" ";

        } else if (ordenes().trim().isEmpty() && estatus().trim().isEmpty()
                && tipos().trim().isEmpty() && clases().trim().isEmpty()
                && criticidad().trim().isEmpty() && equipos().trim().isEmpty()
                && ubicaciones().trim().isEmpty() && fechas().trim().isEmpty()
                && grupoPlanif().trim().isEmpty() && solicitantes().trim().isEmpty()
                && responsable().trim().isEmpty() && sociedad().trim().isEmpty()
                && areas().trim().isEmpty() && emplazamientos().trim().isEmpty() && !centroCostos().trim().isEmpty()) {

            centroCostos = centroCostos()+" ";

        }
        
        String query = ordenes
                + estatus
                + tipos
                + clases
                + criticidad
                + equipos
                + ubicaciones
                + fechas
                + grupoPlanif
                + solicitantes
                + responsables
                + sociedad
                + area
                + emplazamiento
                + centroCostos;
                
                
        return query;
    }
    
    //metodo que ejecuta las operaciones SQL
    private void process_SQL_Query(String query) {

        try {

            O05_Lista_Ordenes_Aprobacion orderList = new O05_Lista_Ordenes_Aprobacion(username);
            orderList.panelAnterior(thisPanel);
            orderList.setQuery(query);

            LyraTableModel2 model = (LyraTableModel2) orderList.getTable().getModel();

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet datos = pSt.getResultSet();
            ResultSetMetaData meta = datos.getMetaData();

            int CantCol = meta.getColumnCount() + 1;
            boolean isNotEmpty = false;

            while (datos.next()) {
                isNotEmpty = true;

                Object[] filas = new Object[CantCol];

                for (int i = 0; i < CantCol; i++) {

                    if (i == 0) {
                        filas[i] = false;
                    } else {
                        filas[i] = datos.getObject(i);
                    }

                }

                model.addRow(filas);
            }

            orderList.getTable().setModel(model);

            if (isNotEmpty) {

                orderList.setTitle();
                orderList.setCheckBoxs();

                PanelLoader.loadPanel(orderList, mainContainerPanel);

            } else {
                notifications(NOT.msg(NOT.RESULTS_NOT_FOUND), TypeMessage.WARNING);
            }

            pSt.close();

        } catch (SQLException ex) {
            Logger.getLogger(O05_Ambiente_Filtrado_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //boton para procesar la consulta
    private void btnProcesarConsulta(){
        btnProcesarConsulta.addActionListener((e)->{
            process_SQL_Query(sqlStatement());
        });
    }

    
    
    //Apartado de ESTATUS
    private void buscarEstatus1() {
        sbEstatus1.getButton().addActionListener((e) -> {
            
            listaEstatus.clear();
            sbEstatus1.getTextBox().setText(null);
            
            Lista_Estatus le = new Lista_Estatus(getSuperFrame(),true);
            le.setTitle(estatusOrdenLabel.getText());
            le.setCampos(sbEstatus1.getTextBox(), null);
            le.setVisible(true);

        });
    }
    private void buscarEstatus2() {
        sbEstatus2.getButton().addActionListener((e) -> {

            listaEstatus.clear();
            sbEstatus2.getTextBox().setText(null);

            Lista_Estatus le = new Lista_Estatus(getSuperFrame(), true);
            le.setTitle(estatusOrdenLabel.getText());
            le.setCampos(sbEstatus2.getTextBox(), null);
            le.setVisible(true);

        });
    }
    
    private final List<String> listaEstatus = new ArrayList();
    private void btnFiltroListaEstatus() {
        btnEstatus.addActionListener((e) -> {

            sbEstatus1.getTextBox().setText(null);
            sbEstatus2.getTextBox().setText(null);
            
            String title = estatusOrdenLabel.getText();

            AddList lst = new AddList(getSuperFrame(), title);
            lst.setModel(title);
            lst.setDataTypeForTable(DataTypeForTable.StringType);
            lst.setLocationRelativeTo(btnEstatus);
            lst.setList(listaEstatus, sbEstatus1.getTextBox());
            lst.setVisible(true);

        });
    }
    
    private String estatus() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaEstatus.isEmpty()) {

            statemen = WorkOrders.STATUS_CODE.toString() + " IN (";
            String estatus = "";

            for (int i = 0; i < listaEstatus.size(); i++) {
                estatus = estatus +"'"+ listaEstatus.get(i) + "',";
            }
            
            statemen = statemen + estatus + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";
            
        } else if (!sbEstatus1.getTextBox().getText().isEmpty() && sbEstatus2.getTextBox().getText().isEmpty()) {

            String estatus1 = sbEstatus1.getTextBox().getText();
            statemen = WorkOrders.STATUS_CODE.toString() + " IN ('" + estatus1 + "')";

        } else if (sbEstatus1.getTextBox().getText().isEmpty() && !sbEstatus2.getTextBox().getText().isEmpty()) {

            String estatus2 = sbEstatus2.getTextBox().getText();
            statemen = WorkOrders.STATUS_CODE.toString() + " IN ('" + estatus2 + "') ";

        } else if (!sbEstatus1.getTextBox().getText().isEmpty() && !sbEstatus2.getTextBox().getText().isEmpty()) {

            String estatus1 = sbEstatus1.getTextBox().getText();
            String estatus2 = sbEstatus2.getTextBox().getText();

            statemen = WorkOrders.STATUS_CODE.toString() + " BETWEEN '" + estatus1 + "' AND '" + estatus2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }
    
    
    
    //Apartado de numeros de ORDENES
    private void buscarOrden1() {
        sbOrder1.getButton().addActionListener((e) -> {

            listaOrdenes.clear();
            sbOrder1.getTextBox().setText(null);

            Lista_Ordenes lo1 = new Lista_Ordenes(getSuperFrame(), true);
            lo1.setTitle(numeroOrdenLB.getText());
            lo1.setNumOrdenTextField(sbOrder1.getTextBox());
            lo1.setVisible(true);

        });
    }
    private void buscarOrden2() {
        sbOrder2.getButton().addActionListener((e) -> {

            listaOrdenes.clear();
            sbOrder2.getTextBox().setText(null);

            Lista_Ordenes lo1 = new Lista_Ordenes(getSuperFrame(), true);
            lo1.setTitle(numeroOrdenLB.getText());
            lo1.setNumOrdenTextField(sbOrder2.getTextBox());
            lo1.setVisible(true);

        });
    }
    
    private final List<String> listaOrdenes = new ArrayList();
    private void btnFiltroListaOrdenes() {
        btnOrdenes.addActionListener((e) -> {

            sbOrder1.getTextBox().setText(null);
            sbOrder2.getTextBox().setText(null);

            String title = numeroOrdenLB.getText();

            AddList lst = new AddList(getSuperFrame(), title);
            lst.setModel(title);
            lst.setDataTypeForTable(DataTypeForTable.NumberType);
            lst.setLocationRelativeTo(btnOrdenes);
            lst.setList(listaOrdenes, sbOrder1.getTextBox());
            lst.setVisible(true);

        });
    }
    
    private String ordenes() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaOrdenes.isEmpty()) {

            statemen = WorkOrders.ORDER_NUM.toString() + " IN (";
            String ordenes = "";

            for (int i = 0; i < listaOrdenes.size(); i++) {
                ordenes = ordenes +"'"+ listaOrdenes.get(i) + "',";
            }
            statemen = statemen + ordenes + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!sbOrder1.getTextBox().getText().isEmpty() && sbOrder2.getTextBox().getText().isEmpty()) {

            String orden1 = sbOrder1.getTextBox().getText();
            statemen = WorkOrders.ORDER_NUM.toString() + " IN ('" + orden1 + "')";

        } else if (sbOrder1.getTextBox().getText().isEmpty() && !sbOrder2.getTextBox().getText().isEmpty()) {

            String orden2 = sbOrder2.getTextBox().getText();
            statemen = WorkOrders.ORDER_NUM.toString() + " IN ('" + orden2 + "') ";

        } else if (!sbOrder1.getTextBox().getText().isEmpty() && !sbOrder2.getTextBox().getText().isEmpty()) {

            String orden1 = sbOrder1.getTextBox().getText();
            String orden2 = sbOrder2.getTextBox().getText();

            statemen = WorkOrders.ORDER_NUM.toString() + " BETWEEN '" + orden1 + "' AND '" + orden2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }

    
    
    
    //Apartado TIPOS
    private void buscarTipo1() {
        sbTipoMtto1.getButton().addActionListener((e) -> {
            
            listaTipos.clear();
            sbTipoMtto1.getTextBox().setText(null);
            
            Lista_Tipo_Mtto ltm = new Lista_Tipo_Mtto(getSuperFrame(), true);
            ltm.setTitle(labelTipoMtto.getText());
            ltm.setCampos(sbTipoMtto1.getTextBox(), null);
            ltm.setVisible(true);

        });
    }
    private void buscarTipo2() {
        sbTipoMtto2.getButton().addActionListener((e) -> {
            listaTipos.clear();
            sbTipoMtto2.getTextBox().setText(null);

            Lista_Tipo_Mtto ltm = new Lista_Tipo_Mtto(getSuperFrame(), true);
            ltm.setTitle(labelTipoMtto.getText());
            ltm.setCampos(sbTipoMtto2.getTextBox(), null);
            ltm.setVisible(true);

        });
    }

    private final List<String> listaTipos = new ArrayList();
    private void btnFiltroListaTipos(){
        btnTipos.addActionListener((e)->{
            
            sbTipoMtto1.getTextBox().setText(null);
            sbTipoMtto2.getTextBox().setText(null);
            
            String title =  labelTipoMtto.getText();
            
            AddList lst = new AddList(getSuperFrame(),title);
            lst.setModel(title);
            lst.setLocationRelativeTo(btnTipos);
            lst.setList(listaTipos,sbTipoMtto1.getTextBox());
            lst.setVisible(true);
            
            
        });
    }
    
    private String tipos() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaTipos.isEmpty()) {

            statemen = WorkOrders.TYPE_ORDER_CODE.toString() + " IN (";
            String tipos = "";

            for (int i = 0; i < listaTipos.size(); i++) {
                tipos = tipos + "'"+listaTipos.get(i) + "',";
            }
            statemen = statemen + tipos + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!sbTipoMtto1.getTextBox().getText().isEmpty() && sbTipoMtto2.getTextBox().getText().isEmpty()) {

            String tipos1 = sbTipoMtto1.getTextBox().getText();
            statemen = WorkOrders.TYPE_ORDER_CODE.toString() + " IN ('" + tipos1 + "')";

        } else if (sbTipoMtto1.getTextBox().getText().isEmpty() && !sbTipoMtto2.getTextBox().getText().isEmpty()) {

            String tipos2 = sbTipoMtto2.getTextBox().getText();
            statemen = WorkOrders.TYPE_ORDER_CODE.toString() + " IN ('" + tipos2 + "') ";

        } else if (!sbTipoMtto1.getTextBox().getText().isEmpty() && !sbTipoMtto2.getTextBox().getText().isEmpty()) {

            String tipos1 = sbTipoMtto1.getTextBox().getText();
            String tipos2 = sbTipoMtto2.getTextBox().getText();

            statemen = WorkOrders.TYPE_ORDER_CODE.toString() + " BETWEEN '" + tipos1 + "' AND '" + tipos2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }
    
    
    
    //Apartado CLASES
    private void buscarClase1() {
        sbClaseOrden1.getButton().addActionListener((e) -> {
            
            listaClases.clear();
            sbClaseOrden1.getTextBox().setText(null);

            Lista_Clase_Mtto lcm = new Lista_Clase_Mtto(getSuperFrame(), true);
            lcm.setTitle(labelClaseMtto.getText());
            lcm.setCampos(sbClaseOrden1.getTextBox(), null);
            lcm.setVisible(true);

        });
    }
    private void buscarClase2() {
        sbClaseOrden2.getButton().addActionListener((e) -> {

            listaClases.clear();
            sbClaseOrden2.getTextBox().setText(null);

            Lista_Clase_Mtto lcm = new Lista_Clase_Mtto(getSuperFrame(), true);
            lcm.setTitle(labelClaseMtto.getText());
            lcm.setCampos(sbClaseOrden2.getTextBox(), null);
            lcm.setVisible(true);

        });
    }

    private final List<String> listaClases = new ArrayList();
    private void btnFiltroListaClases(){
        btnClases.addActionListener((e)->{
            
            sbClaseOrden1.getTextBox().setText(null);
            sbClaseOrden2.getTextBox().setText(null);
            
            String title =  labelClaseMtto.getText();
            
            AddList lst = new AddList(getSuperFrame(),title);
            lst.setModel(title);
            lst.setLocationRelativeTo(btnClases);
            lst.setList(listaClases,sbClaseOrden1.getTextBox());
            lst.setVisible(true);
            
            
        });
    }
    
    private String clases() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaClases.isEmpty()) {

            statemen = WorkOrders.CLASS_ORDER_CODE.toString() + " IN (";
            String clases = "";

            for (int i = 0; i < listaClases.size(); i++) {
                clases = clases +"'" +listaClases.get(i) + "',";
            }
            statemen = statemen + clases + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!sbClaseOrden1.getTextBox().getText().isEmpty() && sbClaseOrden2.getTextBox().getText().isEmpty()) {

            String clases1 = sbClaseOrden1.getTextBox().getText();
            statemen = WorkOrders.CLASS_ORDER_CODE.toString() + " IN ('" + clases1 + "')";

        } else if (sbClaseOrden1.getTextBox().getText().isEmpty() && !sbClaseOrden2.getTextBox().getText().isEmpty()) {

            String clases2 = sbClaseOrden2.getTextBox().getText();
            statemen = WorkOrders.CLASS_ORDER_CODE.toString() + " IN ('" + clases2 + "') ";

        } else if (!sbClaseOrden1.getTextBox().getText().isEmpty() && !sbClaseOrden2.getTextBox().getText().isEmpty()) {

            String clases1 = sbClaseOrden1.getTextBox().getText();
            String clases2 = sbClaseOrden2.getTextBox().getText();

            statemen = WorkOrders.CLASS_ORDER_CODE.toString() + " BETWEEN '" + clases1 + "' AND '" + clases2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }
    
    
    
    //Apartado CRITICIDAD
    private void buscarCriticidad1() {
        sbCriticidad1.getButton().addActionListener((e) -> {

            listaCriticidad.clear();
            sbCriticidad1.getTextBox().setText(null);
            
            Lista_Criticidad lc = new Lista_Criticidad(getSuperFrame(), true);
            lc.setTitle(labelCriticidad.getText());
            lc.setCampos(sbCriticidad1.getTextBox(), null);
            lc.setVisible(true);

        });
    }
    private void buscarCriticidad2() {
        sbCriticidad2.getButton().addActionListener((e) -> {

            listaCriticidad.clear();
            sbCriticidad2.getTextBox().setText(null);

            Lista_Criticidad lc = new Lista_Criticidad(getSuperFrame(), true);
            lc.setTitle(labelCriticidad.getText());
            lc.setCampos(sbCriticidad2.getTextBox(), null);
            lc.setVisible(true);

        });
    }
    
    private final List<String> listaCriticidad = new ArrayList();
    private void btnFiltroListaCriticidad(){
        btnCriticidades.addActionListener((e)->{
            
            sbCriticidad1.getTextBox().setText(null);
            sbCriticidad2.getTextBox().setText(null);
            
            String title =  labelCriticidad.getText();
            
            AddList lst = new AddList(getSuperFrame(),title);
            lst.setModel(title);
            lst.setLocationRelativeTo(btnCriticidades);
            lst.setList(listaCriticidad,sbCriticidad1.getTextBox());
            lst.setVisible(true);
            
            
        });
    }
    
    private String criticidad() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaCriticidad.isEmpty()) {

            statemen = WorkOrders.CRITICALITY_CODE.toString() + " IN (";
            String criticidades = "";

            for (int i = 0; i < listaCriticidad.size(); i++) {
                criticidades = criticidades +"'"+ listaCriticidad.get(i) + "',";
            }
            statemen = statemen + criticidades + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!sbCriticidad1.getTextBox().getText().isEmpty() && sbCriticidad2.getTextBox().getText().isEmpty()) {

            String criticidad1 = sbCriticidad1.getTextBox().getText();
            statemen = WorkOrders.CRITICALITY_CODE.toString() + " IN ('" + criticidad1 + "')";

        } else if (sbCriticidad1.getTextBox().getText().isEmpty() && !sbCriticidad2.getTextBox().getText().isEmpty()) {

            String criticidad2 = sbCriticidad2.getTextBox().getText();
            statemen = WorkOrders.CRITICALITY_CODE.toString() + " IN ('" + criticidad2 + "')";

        } else if (!sbCriticidad1.getTextBox().getText().isEmpty() && !sbCriticidad2.getTextBox().getText().isEmpty()) {

            String criticidad1 = sbCriticidad1.getTextBox().getText();
            String criticidad2 = sbCriticidad2.getTextBox().getText();

            statemen = WorkOrders.CRITICALITY_CODE.toString() + " BETWEEN '" + criticidad1 + "' AND '" + criticidad2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }
    
    
    
    //Apartado EQUIPOS
    private void buscarEquipo1() {
        sbEquipo1.getButton().addActionListener((e) -> {
            
            listaEquipos.clear();
            sbEquipo1.getTextBox().setText(null);

            Lista_Equipos le = new Lista_Equipos();
            le.setCampos(sbEquipo1.getTextBox(), null, null, null, null);
            le.setPanelAnterior(thisPanel);

            PanelLoader.loadPanel(le, mainContainerPanel);

        });
    }
    private void buscarEquipo2() {
        sbEquipo2.getButton().addActionListener((e) -> {

            listaEquipos.clear();
            sbEquipo2.getTextBox().setText(null);

            Lista_Equipos le = new Lista_Equipos();
            le.setCampos(sbEquipo2.getTextBox(), null, null, null, null);
            le.setPanelAnterior(thisPanel);

            PanelLoader.loadPanel(le, mainContainerPanel);

        });
    }

    private final List<String> listaEquipos = new ArrayList();
    private void btnFiltroListaEquipos(){
        btnEquipos.addActionListener((e)->{
            
            sbEquipo1.getTextBox().setText(null);
            sbEquipo2.getTextBox().setText(null);
            
            String title =  labelEquipo.getText();
            
            AddList lst = new AddList(getSuperFrame(),title);
            lst.setModel(title);
            lst.setLocationRelativeTo(btnEquipos);
            lst.setList(listaEquipos,sbEquipo1.getTextBox());
            lst.setVisible(true);
            
            
        });
    }
    
    private String equipos() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaEquipos.isEmpty()) {

            statemen = WorkOrders.EQUIPMENT_CODE.toString() + " IN (";
            String equipos = "";

            for (int i = 0; i < listaEquipos.size(); i++) {
                equipos = equipos + "'"+listaEquipos.get(i) + "',";
            }
            statemen = statemen + equipos + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!sbEquipo1.getTextBox().getText().isEmpty() && sbEquipo2.getTextBox().getText().isEmpty()) {

            String equipos1 = sbEquipo1.getTextBox().getText();
            statemen = WorkOrders.EQUIPMENT_CODE.toString() + " IN ('" + equipos1 + "')";

        } else if (sbEquipo1.getTextBox().getText().isEmpty() && !sbEquipo2.getTextBox().getText().isEmpty()) {

            String equipos2 = sbEquipo2.getTextBox().getText();
            statemen = WorkOrders.EQUIPMENT_CODE.toString() + " IN ('" + equipos2 + "') ";

        } else if (!sbEquipo1.getTextBox().getText().isEmpty() && !sbEquipo2.getTextBox().getText().isEmpty()) {

            String equipos1 = sbEquipo1.getTextBox().getText();
            String equipos2 = sbEquipo2.getTextBox().getText();

            statemen = WorkOrders.EQUIPMENT_CODE.toString() + " BETWEEN '" + equipos1 + "' AND '" + equipos2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }
    
    
    
    //Apartado UBICACIONES
    private void buscarUbicacion1() {

        sbLocation1.getButton().addActionListener((e) -> {
            
            listaUbicaciones.clear();
            sbLocation1.getTextBox().setText(null);

            Lista_Ubicaciones lu = new Lista_Ubicaciones();
            lu.setCodUbi(sbLocation1.getTextBox());
            lu.setPanelAnterior(thisPanel);

            PanelLoader.loadPanel(lu, mainContainerPanel);

        });

    }
    private void buscarUbicacion2() {

        sbLocation2.getButton().addActionListener((e) -> {

            listaUbicaciones.clear();
            sbLocation2.getTextBox().setText(null);

            Lista_Ubicaciones lu = new Lista_Ubicaciones();
            lu.setCodUbi(sbLocation2.getTextBox());
            lu.setPanelAnterior(thisPanel);

            PanelLoader.loadPanel(lu, mainContainerPanel);

        });

    }

    private final List<String> listaUbicaciones = new ArrayList();
    private void btnFiltroListaUbicaciones(){
        btnUbicaciones.addActionListener((e)->{
            
            sbLocation1.getTextBox().setText(null);
            sbLocation2.getTextBox().setText(null);
            
            String title =  labelUbicacion.getText();
            
            AddList lst = new AddList(getSuperFrame(),title);
            lst.setModel(title);
            lst.setLocationRelativeTo(btnUbicaciones);
            lst.setList(listaUbicaciones,sbLocation1.getTextBox());
            lst.setVisible(true);
            
        });
    }
    
    private String ubicaciones() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaUbicaciones.isEmpty()) {

            statemen = WorkOrders.UBICATION_CODE.toString() + " IN (";
            String ubicaciones = "";

            for (int i = 0; i < listaUbicaciones.size(); i++) {
                ubicaciones = ubicaciones + "'"+listaUbicaciones.get(i) + "',";
            }
            statemen = statemen + ubicaciones + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!sbLocation1.getTextBox().getText().isEmpty() && sbLocation2.getTextBox().getText().isEmpty()) {

            String ubicaciones1 = sbLocation1.getTextBox().getText();
            statemen = WorkOrders.UBICATION_CODE.toString() + " IN ('" + ubicaciones1 + "')";

        } else if (sbLocation1.getTextBox().getText().isEmpty() && !sbLocation2.getTextBox().getText().isEmpty()) {

            String ubicaciones2 = sbLocation2.getTextBox().getText();
            statemen = WorkOrders.UBICATION_CODE.toString() + " IN ('" + ubicaciones2 + "')";

        } else if (!sbLocation1.getTextBox().getText().isEmpty() && !sbLocation2.getTextBox().getText().isEmpty()) {

            String ubicaciones1 = sbLocation1.getTextBox().getText();
            String ubicaicones2 = sbLocation2.getTextBox().getText();

            statemen = WorkOrders.UBICATION_CODE.toString() + " BETWEEN '" + ubicaciones1 + "' AND '" + ubicaicones2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }
    
    
    
    //Apartado FECHAS
    private void formatoFechas(){
        fechaInicioProgramado1.setDateFormatString("yyyy.MM.dd");
        fechaInicioProgramado2.setDateFormatString("yyyy.MM.dd");
    }
    private void setFecha1() {
        fechaInicioProgramado1.addPropertyChangeListener((e) -> {
            if ("date".equals(e.getPropertyName())) {
                listaFechas.clear();
            }
        });
    }
    private void setFecha2() {
        fechaInicioProgramado2.addPropertyChangeListener((e) -> {
            if ("date".equals(e.getPropertyName())) {
                listaFechas.clear();
            }
        });
    }

    private final List<String> listaFechas = new ArrayList();
    private void btnFiltroListaFechas(){
        btnFechasProgramadas.addActionListener((e)->{
            
            
            String title =  labelFechaProgramada.getText();
            
            AddList lst = new AddList(getSuperFrame(),title);
            lst.setModel(title);
            lst.setDataTypeForTable(DataTypeForTable.DateType);
            lst.setLocationRelativeTo(btnFechasProgramadas);
            lst.setList(listaFechas ,((JTextFieldDateEditor) fechaInicioProgramado1.getDateEditor()));
            lst.setVisible(true);
            
        });
    }
    
    private String fechas() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaFechas.isEmpty()) {

            statemen = WorkOrders.SCHEDULED_START_DATE.toString() + " IN (";
            String fechas = "";

            for (int i = 0; i < listaFechas.size(); i++) {
                fechas = fechas +"'" +listaFechas.get(i) + "',";
            }
            statemen = statemen + fechas + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!((JTextFieldDateEditor)fechaInicioProgramado1.getDateEditor()).getText().isEmpty() && ((JTextFieldDateEditor)fechaInicioProgramado2.getDateEditor()).getText().isEmpty()) {

            String fecha1 = formatoFecha.format(fechaInicioProgramado1.getDate());
            statemen = WorkOrders.SCHEDULED_START_DATE.toString() + " IN ('" + fecha1 + "')";

        } else if (((JTextFieldDateEditor)fechaInicioProgramado1.getDateEditor()).getText().isEmpty() && !((JTextFieldDateEditor)fechaInicioProgramado2.getDateEditor()).getText().isEmpty()) {

            String fecha2 = formatoFecha.format(fechaInicioProgramado2.getDate());
            statemen = WorkOrders.SCHEDULED_START_DATE.toString() + " IN ('" + fecha2 + "')";

        } else if (!((JTextFieldDateEditor)fechaInicioProgramado1.getDateEditor()).getText().isEmpty() && !((JTextFieldDateEditor)fechaInicioProgramado2.getDateEditor()).getText().isEmpty()) {

            String fecha1 = formatoFecha.format(fechaInicioProgramado1.getDate());
            String fecha2 = formatoFecha.format(fechaInicioProgramado2.getDate());

            statemen = WorkOrders.SCHEDULED_START_DATE.toString() + " BETWEEN '" + fecha1 + "' AND '" + fecha2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }
    
    
    
    //Apartado GRUPOS PLANIFICADORES
    private void buscarGrupoPlanif1() {

        sbGrupoPlanif1.getButton().addActionListener((e) -> {

            listaGruposPlanif.clear();
            sbGrupoPlanif1.getTextBox().setText(null);

            Lista_Grupo_Planificador lgp = new Lista_Grupo_Planificador(getSuperFrame(), true);
            lgp.setCampos(sbGrupoPlanif1.getTextBox(), null);
            lgp.setTitle(labelGrupoPlanif.getText());
            lgp.setVisible(true);

        });
    }
    private void buscarGrupoPlanif2() {

        sbGrupoPlanif2.getButton().addActionListener((e) -> {

            listaGruposPlanif.clear();
            sbGrupoPlanif2.getTextBox().setText(null);

            Lista_Grupo_Planificador lgp = new Lista_Grupo_Planificador(getSuperFrame(), true);
            lgp.setCampos(sbGrupoPlanif2.getTextBox(), null);
            lgp.setTitle(labelGrupoPlanif.getText());
            lgp.setVisible(true);

        });
    }

    private final List<String> listaGruposPlanif = new ArrayList();
    private void btnFiltroListaGruposPlanif(){
        btnGruposPlanificadores.addActionListener((e)->{
            
            sbGrupoPlanif1.getTextBox().setText(null);
            sbGrupoPlanif2.getTextBox().setText(null);
            
            String title =  labelGrupoPlanif.getText();
            
            AddList lst = new AddList(getSuperFrame(),title);
            lst.setModel(title);
            lst.setDataTypeForTable(DataTypeForTable.StringType);
            lst.setLocationRelativeTo(btnGruposPlanificadores);
            lst.setList(listaGruposPlanif,sbGrupoPlanif1.getTextBox());
            lst.setVisible(true);
            
        });
    }
    
    private String grupoPlanif() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaGruposPlanif.isEmpty()) {

            statemen = WorkOrders.GROUP_PLANNING_CODE.toString() + " IN (";
            String grupoPlanif = "";

            for (int i = 0; i < listaGruposPlanif.size(); i++) {
                grupoPlanif = grupoPlanif + "'"+listaGruposPlanif.get(i) + "',";
            }
            statemen = statemen + grupoPlanif + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!sbGrupoPlanif1.getTextBox().getText().isEmpty() && sbGrupoPlanif2.getTextBox().getText().isEmpty()) {

            String grupoPlanif1 = sbGrupoPlanif1.getTextBox().getText();
            statemen = WorkOrders.GROUP_PLANNING_CODE.toString() + " IN ('" + grupoPlanif1 + "')";

        } else if (sbGrupoPlanif1.getTextBox().getText().isEmpty() && !sbGrupoPlanif2.getTextBox().getText().isEmpty()) {

            String grupoPlanif2 = sbGrupoPlanif2.getTextBox().getText();
            statemen = WorkOrders.GROUP_PLANNING_CODE.toString() + " IN ('" + grupoPlanif2 + "')";

        } else if (!sbGrupoPlanif1.getTextBox().getText().isEmpty() && !sbGrupoPlanif2.getTextBox().getText().isEmpty()) {

            String grupoPlanif1 = sbGrupoPlanif1.getTextBox().getText();
            String grupoPlanif2 = sbGrupoPlanif2.getTextBox().getText();

            statemen = WorkOrders.GROUP_PLANNING_CODE.toString() + " BETWEEN '" + grupoPlanif1 + "' AND '" + grupoPlanif2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }
    
    
    
    //Apartado SOLICITANTES
    private void buscarSolicitante1() {
        sbSolicitante1.getButton().addActionListener((e) -> {
            
            listaSolicitante.clear();
            sbSolicitante1.getTextBox().setText(null);

            Lista_Personal lp1 = new Lista_Personal(getSuperFrame(), true);
            lp1.setTitle(labelSolicitante.getText());
            lp1.setTextFields(sbSolicitante1.getTextBox(), null);
            lp1.setVisible(true);

        });
    }
    private void buscarSolicitante2() {
        sbSolicitante2.getButton().addActionListener((e) -> {

            listaSolicitante.clear();
            sbSolicitante2.getTextBox().setText(null);

            Lista_Personal lp1 = new Lista_Personal(getSuperFrame(), true);
            lp1.setTitle(labelSolicitante.getText());
            lp1.setTextFields(sbSolicitante2.getTextBox(), null);
            lp1.setVisible(true);

        });
    }

    private final List<String> listaSolicitante = new ArrayList();
    private void btnFiltroListaSolicitantes(){
        btnSolicitantes.addActionListener((e)->{
            
            sbSolicitante1.getTextBox().setText(null);
            sbSolicitante2.getTextBox().setText(null);
            
            String title =  labelSolicitante.getText();
            
            AddList lst = new AddList(getSuperFrame(),title);
            lst.setModel(title);
            lst.setDataTypeForTable(DataTypeForTable.StringType);
            lst.setLocationRelativeTo(btnSolicitantes);
            lst.setList(listaSolicitante,sbSolicitante1.getTextBox());
            lst.setVisible(true);
            
        });
    }
    
    private String solicitantes() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaSolicitante.isEmpty()) {

            statemen = WorkOrders.APPLICANT_CODE.toString() + " IN (";
            String solicitantes = "";

            for (int i = 0; i < listaSolicitante.size(); i++) {
                solicitantes = solicitantes + "'"+listaSolicitante.get(i) + "',";
            }
            statemen = statemen + solicitantes + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!sbSolicitante1.getTextBox().getText().isEmpty() && sbSolicitante2.getTextBox().getText().isEmpty()) {

            String solicitante1 = sbSolicitante1.getTextBox().getText();
            statemen = WorkOrders.APPLICANT_CODE.toString() + " IN ('" + solicitante1 + "')";

        } else if (sbSolicitante1.getTextBox().getText().isEmpty() && !sbSolicitante2.getTextBox().getText().isEmpty()) {

            String solicitante2 = sbGrupoPlanif2.getTextBox().getText();
            statemen = WorkOrders.APPLICANT_CODE.toString() + " IN ('" + solicitante2 + "')";

        } else if (!sbSolicitante1.getTextBox().getText().isEmpty() && !sbSolicitante2.getTextBox().getText().isEmpty()) {

            String solicitante1 = sbSolicitante1.getTextBox().getText();
            String solicitante2 = sbSolicitante2.getTextBox().getText();

            statemen = WorkOrders.APPLICANT_CODE.toString() + " BETWEEN '" + solicitante1 + "' AND '" + solicitante2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }
    
    
    
    //Apartado RESPONSABLES
    private void buscarResponsable1() {
        sbResponsable1.getButton().addActionListener((e) -> {
            
            listaResponsable.clear();
            sbResponsable1.getTextBox().setText(null);
            
            Lista_Personal lp1 = new Lista_Personal(getSuperFrame(), true);
            lp1.setTitle(labelResponsable.getText());
            lp1.setTextFields(sbResponsable1.getTextBox(), null);
            lp1.setVisible(true);

        });
    }
    private void buscarResponsable2() {
        sbResponsable2.getButton().addActionListener((e) -> {

            listaResponsable.clear();
            sbResponsable2.getTextBox().setText(null);

            Lista_Personal lp1 = new Lista_Personal(getSuperFrame(), true);
            lp1.setTitle(labelResponsable.getText());
            lp1.setTextFields(sbResponsable2.getTextBox(), null);
            lp1.setVisible(true);

        });
    }
    
    private final List<String> listaResponsable = new ArrayList();
    private void btnFiltroListaResponsables(){
        btnResponsables.addActionListener((e)->{
            
            sbResponsable1.getTextBox().setText(null);
            sbResponsable2.getTextBox().setText(null);
            
            String title =  labelResponsable.getText();
            
            AddList lst = new AddList(getSuperFrame(),title);
            lst.setModel(title);
            lst.setDataTypeForTable(DataTypeForTable.StringType);
            lst.setLocationRelativeTo(btnResponsables);
            lst.setList(listaResponsable,sbResponsable1.getTextBox());
            lst.setVisible(true);
            
        });
    }
    
    private String responsable() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaResponsable.isEmpty()) {

            statemen = WorkOrders.RESPONSIBLE_CODE.toString() + " IN (";
            String responsables = "";

            for (int i = 0; i < listaResponsable.size(); i++) {
                responsables = responsables + "'"+listaResponsable.get(i) + "',";
            }
            statemen = statemen + responsables + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!sbResponsable1.getTextBox().getText().isEmpty() && sbResponsable2.getTextBox().getText().isEmpty()) {

            String responsable1 = sbResponsable1.getTextBox().getText();
            statemen = WorkOrders.RESPONSIBLE_CODE.toString() + " IN ('" + responsable1 + "')";

        } else if (sbResponsable1.getTextBox().getText().isEmpty() && !sbResponsable2.getTextBox().getText().isEmpty()) {

            String responsable2 = sbResponsable2.getTextBox().getText();
            statemen = WorkOrders.RESPONSIBLE_CODE.toString() + " IN ('" + responsable2 + "')";

        } else if (!sbResponsable1.getTextBox().getText().isEmpty() && !sbResponsable2.getTextBox().getText().isEmpty()) {

            String responsable1 = sbResponsable1.getTextBox().getText();
            String responsable2 = sbResponsable2.getTextBox().getText();

            statemen = WorkOrders.RESPONSIBLE_CODE.toString() + " BETWEEN '" + responsable1 + "' AND '" + responsable2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }
    
    
    
    //Apartado SOCIEDADES
    private void buscarSociedad1() {
        sbSociedad1.getButton().addActionListener((e) -> {
            
            listaSociedades.clear();
            sbSociedad1.getTextBox().setText(null);

            A01_Lista_Sociedades ls = new A01_Lista_Sociedades(getSuperFrame(), true);
            ls.setCodigoTextfield(sbSociedad1.getTextBox());
            ls.setTitle(labelSociedad.getText());
            ls.setVisible(true);

        });
    }
    private void buscarSociedad2() {
        sbSociedad2.getButton().addActionListener((e) -> {

            listaSociedades.clear();
            sbSociedad2.getTextBox().setText(null);

            A01_Lista_Sociedades ls = new A01_Lista_Sociedades(getSuperFrame(), true);
            ls.setCodigoTextfield(sbSociedad2.getTextBox());
            ls.setTitle(labelSociedad.getText());
            ls.setVisible(true);

        });
    }

    private final List<String> listaSociedades = new ArrayList();
    private void btnFiltroListaSociedades(){
        btnSociedades.addActionListener((e)->{
            
            sbSociedad1.getTextBox().setText(null);
            sbSociedad2.getTextBox().setText(null);
            
            String title =  labelSociedad.getText();
            
            AddList lst = new AddList(getSuperFrame(),title);
            lst.setModel(title);
            lst.setDataTypeForTable(DataTypeForTable.StringType);
            lst.setLocationRelativeTo(btnSociedades);
            lst.setList(listaSociedades,sbSociedad1.getTextBox());
            lst.setVisible(true);
            
        });
    }
    
    private String sociedad() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaSociedades.isEmpty()) {

            statemen = WorkOrders.COMPANY_CODE.toString() + " IN (";
            String sociedades = "";

            for (int i = 0; i < listaSociedades.size(); i++) {
                sociedades = sociedades + "'"+listaSociedades.get(i) + "',";
            }
            statemen = statemen + sociedades + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!sbSociedad1.getTextBox().getText().isEmpty() && sbSociedad2.getTextBox().getText().isEmpty()) {

            String sociedad1 = sbSociedad1.getTextBox().getText();
            statemen = WorkOrders.COMPANY_CODE.toString() + " IN ('" + sociedad1 + "')";

        } else if (sbSociedad1.getTextBox().getText().isEmpty() && !sbSociedad2.getTextBox().getText().isEmpty()) {

            String sociedad2 = sbSociedad2.getTextBox().getText();
            statemen = WorkOrders.COMPANY_CODE.toString() + " IN ('" + sociedad2 + "') ";

        } else if (!sbSociedad1.getTextBox().getText().isEmpty() && !sbSociedad2.getTextBox().getText().isEmpty()) {

            String sociedad1 = sbSociedad1.getTextBox().getText();
            String sociedad2 = sbSociedad2.getTextBox().getText();

            statemen = WorkOrders.COMPANY_CODE.toString() + " BETWEEN '" + sociedad1 + "' AND '" + sociedad2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }
    
    
    
    //Apartado AREAS
    private void buscarArea1() {
        sbArea1.getButton().addActionListener((e) -> {

            listaAreas.clear();
            sbArea1.getTextBox().setText(null);
            
            A02_Lista_Areas la = new A02_Lista_Areas(getSuperFrame(), true);
            la.setAreaCodigoTextField(sbArea1.getTextBox());
            la.setTitle(labelArea.getText());
            la.setVisible(true);

        });
    }
    private void buscarArea2() {
        sbArea2.getButton().addActionListener((e) -> {

            listaAreas.clear();
            sbArea2.getTextBox().setText(null);

            A02_Lista_Areas la = new A02_Lista_Areas(getSuperFrame(), true);
            la.setAreaCodigoTextField(sbArea2.getTextBox());
            la.setTitle(labelArea.getText());
            la.setVisible(true);

        });
    }

    private final List<String> listaAreas = new ArrayList();
    private void btnFiltroListaAreas(){
        btnAreas.addActionListener((e)->{
            
            sbArea1.getTextBox().setText(null);
            sbArea2.getTextBox().setText(null);
            
            String title =  labelArea.getText();
            
            AddList lst = new AddList(getSuperFrame(),title);
            lst.setModel(title);
            lst.setDataTypeForTable(DataTypeForTable.StringType);
            lst.setLocationRelativeTo(btnAreas);
            lst.setList(listaAreas,sbArea1.getTextBox());
            lst.setVisible(true);
            
        });
    }
    
    private String areas() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaAreas.isEmpty()) {

            statemen = WorkOrders.AREA_CODE.toString() + " IN (";
            String areas = "";

            for (int i = 0; i < listaAreas.size(); i++) {
                areas = areas +"'" +listaAreas.get(i) + "',";
            }
            statemen = statemen + areas + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!sbArea1.getTextBox().getText().isEmpty() && sbArea2.getTextBox().getText().isEmpty()) {

            String area1 = sbArea1.getTextBox().getText();
            statemen = WorkOrders.AREA_CODE.toString() + " IN ('" + area1 + "')";

        } else if (sbArea1.getTextBox().getText().isEmpty() && !sbArea2.getTextBox().getText().isEmpty()) {

            String area2 = sbArea2.getTextBox().getText();
            statemen = WorkOrders.AREA_CODE.toString() + " IN ('" + area2 + "')";

        } else if (!sbArea1.getTextBox().getText().isEmpty() && !sbArea2.getTextBox().getText().isEmpty()) {

            String area1 = sbArea1.getTextBox().getText();
            String area2 = sbArea2.getTextBox().getText();

            statemen = WorkOrders.AREA_CODE.toString() + " BETWEEN '" + area1 + "' AND '" + area2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }
    
    
    
    
    //Apartado EMPLAZAMIENTOS
    private void buscarEmplazamiento1() {

        sbEmplazamiento1.getButton().addActionListener((e) -> {
            
            listaEmplazamiento.clear();
            sbEmplazamiento1.getTextBox().setText(null);

            F02_Lista_Emplazamientos le = new F02_Lista_Emplazamientos(getSuperFrame(), true);
            le.setIdEmplazamiento(sbEmplazamiento1.getTextBox());
            le.setTitle(labelEmplazamiento.getText());
            le.setVisible(true);

        });

    }
    private void buscarEmplazamiento2() {

        sbEmplazamiento2.getButton().addActionListener((e) -> {

            listaEmplazamiento.clear();
            sbEmplazamiento2.getTextBox().setText(null);

            F02_Lista_Emplazamientos le = new F02_Lista_Emplazamientos(getSuperFrame(), true);
            le.setIdEmplazamiento(sbEmplazamiento2.getTextBox());
            le.setTitle(labelEmplazamiento.getText());
            le.setVisible(true);

        });

    }

    private final List<String> listaEmplazamiento = new ArrayList();
    private void btnFiltroListaEmplazamientos(){
        btnEmplazamientos.addActionListener((e)->{
            
            sbEmplazamiento1.getTextBox().setText(null);
            sbEmplazamiento2.getTextBox().setText(null);
            
            String title =  labelEmplazamiento.getText();
            
            AddList lst = new AddList(getSuperFrame(),title);
            lst.setModel(title);
            lst.setDataTypeForTable(DataTypeForTable.StringType);
            lst.setLocationRelativeTo(btnEmplazamientos);
            lst.setList(listaEmplazamiento,sbEmplazamiento1.getTextBox());
            lst.setVisible(true);
            
        });
    }
    
    private String emplazamientos() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaEmplazamiento.isEmpty()) {

            statemen = WorkOrders.EMPLAZEMENT_CODE.toString() + " IN (";
            String emplazamientos = "";

            for (int i = 0; i < listaEmplazamiento.size(); i++) {
                emplazamientos = emplazamientos + "'" +listaEmplazamiento.get(i) + "',";
            }
            statemen = statemen + emplazamientos + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!sbEmplazamiento1.getTextBox().getText().isEmpty() && sbEmplazamiento2.getTextBox().getText().isEmpty()) {

            String emplazamiento1 = sbEmplazamiento1.getTextBox().getText();
            statemen = WorkOrders.EMPLAZEMENT_CODE.toString() + " IN ('" + emplazamiento1 + "')";

        } else if (sbEmplazamiento1.getTextBox().getText().isEmpty() && !sbEmplazamiento2.getTextBox().getText().isEmpty()) {

            String emplazamiento2 = sbEmplazamiento2.getTextBox().getText();
            statemen = WorkOrders.EMPLAZEMENT_CODE.toString() + " IN ('" + emplazamiento2 + "')";

        } else if (!sbEmplazamiento1.getTextBox().getText().isEmpty() && !sbEmplazamiento2.getTextBox().getText().isEmpty()) {

            String emplazamiento1 = sbEmplazamiento1.getTextBox().getText();
            String emplazamiento2 = sbEmplazamiento2.getTextBox().getText();

            statemen = WorkOrders.EMPLAZEMENT_CODE.toString() + " BETWEEN '" + emplazamiento1 + "' AND '" + emplazamiento2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }
    
    
    
    //Apartado CENTROS COSTOS
    private void buscarCentroCostos1() {
        sbCentoCostos1.getButton().addActionListener((e) -> {
            
            listaCentroCostos.clear();
            sbCentoCostos1.getTextBox().setText(null);

            U01_Lista_Centro_Costos lcc = new U01_Lista_Centro_Costos(getSuperFrame(), true);
            lcc.setTitle(labelCentroCostos.getText());
            lcc.setIdCentroCostos(sbCentoCostos1.getTextBox());
            lcc.setVisible(true);

        });
    }
    private void buscarCentroCostos2() {
        sbCentoCostos2.getButton().addActionListener((e) -> {

            listaCentroCostos.clear();
            sbCentoCostos2.getTextBox().setText(null);

            U01_Lista_Centro_Costos lcc = new U01_Lista_Centro_Costos(getSuperFrame(), true);
            lcc.setTitle(labelCentroCostos.getText());
            lcc.setIdCentroCostos(sbCentoCostos2.getTextBox());
            lcc.setVisible(true);

        });
    }
        
    private final List<String> listaCentroCostos = new ArrayList();
    private void btnFiltroListaCentroCostos(){
        btnCentrosCostos.addActionListener((e)->{
            
            sbCentoCostos1.getTextBox().setText(null);
            sbCentoCostos2.getTextBox().setText(null);
            
            String title =  labelCentroCostos.getText();
            
            AddList lst = new AddList(getSuperFrame(),title);
            lst.setModel(title);
            lst.setDataTypeForTable(DataTypeForTable.NumberType);
            lst.setLocationRelativeTo(btnCentrosCostos);
            lst.setList(listaCentroCostos,sbCentoCostos1.getTextBox());
            lst.setVisible(true);
            
        });
    }
    private String centroCostos() {

        String statemen = "";

        //si hay datos en  lista
        if (!listaCentroCostos.isEmpty()) {

            statemen = WorkOrders.COST_CENTER_CODE.toString() + " IN (";
            String centroCostos = "";

            for (int i = 0; i < listaCentroCostos.size(); i++) {
                centroCostos = centroCostos + "'"+listaCentroCostos.get(i) + "',";
            }
            statemen = statemen + centroCostos + ")";
            statemen = statemen.substring(0, statemen.length()-2)+")";

        } else if (!sbCentoCostos1.getTextBox().getText().isEmpty() && sbCentoCostos2.getTextBox().getText().isEmpty()) {

            String centroCostos1 = sbCentoCostos1.getTextBox().getText();
            statemen = WorkOrders.COST_CENTER_CODE.toString() + " IN ('" + centroCostos1 + "')";

        } else if (sbCentoCostos1.getTextBox().getText().isEmpty() && !sbCentoCostos2.getTextBox().getText().isEmpty()) {

            String centroCostos2 = sbCentoCostos2.getTextBox().getText();
            statemen = WorkOrders.COST_CENTER_CODE.toString() + " IN ('" + centroCostos2 + "') ";

        } else if (!sbCentoCostos1.getTextBox().getText().isEmpty() && !sbCentoCostos2.getTextBox().getText().isEmpty()) {

            String centroCostos1 = sbCentoCostos1.getTextBox().getText();
            String centroCostos2 = sbCentoCostos2.getTextBox().getText();

            statemen = WorkOrders.COST_CENTER_CODE.toString() + " BETWEEN '" + centroCostos1 + "' AND '" + centroCostos2+"'";

        } else {
            statemen = "";
        }

        return statemen;
    }

    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        jPanel_Rounded_Corners_Degradado1 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        labelClaseMtto = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        btnTipos = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        labelCriticidad = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        btnClases = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        labelEquipo = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelUbicacion = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        btnCriticidades = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnEquipos = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnUbicaciones = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnFechasProgramadas = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnAreas = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnEmplazamientos = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnCentrosCostos = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        labelFechaProgramada = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelGrupoPlanif = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelSolicitante = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta15 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelResponsable = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        btnGruposPlanificadores = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        fechaInicioProgramado1 = new com.toedter.calendar.JDateChooser();
        btnSolicitantes = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnResponsables = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnSociedades = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        labelSociedad = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta5 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta9 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta6 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelArea = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta7 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelEmplazamiento = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta8 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelCentroCostos = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta10 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta11 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta12 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta13 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta14 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta2 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        numeroOrdenLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta3 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta4 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelTipoMtto = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        btnOrdenes = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        estatusOrdenLabel = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hasta1 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        btnEstatus = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        fechaInicioProgramado2 = new com.toedter.calendar.JDateChooser();
        sbOrder1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbTipoMtto1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbClaseOrden1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbCriticidad1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbEquipo1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbLocation1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbGrupoPlanif1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbSolicitante1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbResponsable1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbEstatus1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbSociedad1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbArea1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbEmplazamiento1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbCentoCostos1 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbOrder2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbTipoMtto2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbClaseOrden2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbCriticidad2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbEquipo2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbLocation2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbGrupoPlanif2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbSolicitante2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbResponsable2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbEstatus2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbSociedad2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbArea2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbEmplazamiento2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        sbCentoCostos2 = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir_CrearOrden = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnProcesarConsulta = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnNueva_Orden = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        bodyPanel.setBackground(new java.awt.Color(246, 250, 253));

        jPanel_Rounded_Corners_Degradado1.setColor1(new java.awt.Color(246, 250, 253));
        jPanel_Rounded_Corners_Degradado1.setColor2(new java.awt.Color(202, 216, 237));

        labelClaseMtto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelClaseMtto.setText("Clase Orden");

        btnTipos.setBackground(new java.awt.Color(226, 210, 144));
        btnTipos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnTipos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        labelCriticidad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCriticidad.setText("Criticidad");

        btnClases.setBackground(new java.awt.Color(226, 210, 144));
        btnClases.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnClases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        labelEquipo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelEquipo.setText("Equipo");

        labelUbicacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelUbicacion.setText("Location");

        btnCriticidades.setBackground(new java.awt.Color(226, 210, 144));
        btnCriticidades.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCriticidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        btnEquipos.setBackground(new java.awt.Color(226, 210, 144));
        btnEquipos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEquipos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        btnUbicaciones.setBackground(new java.awt.Color(226, 210, 144));
        btnUbicaciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnUbicaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        btnFechasProgramadas.setBackground(new java.awt.Color(226, 210, 144));
        btnFechasProgramadas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnFechasProgramadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        btnAreas.setBackground(new java.awt.Color(226, 210, 144));
        btnAreas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAreas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        btnEmplazamientos.setBackground(new java.awt.Color(226, 210, 144));
        btnEmplazamientos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEmplazamientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        btnCentrosCostos.setBackground(new java.awt.Color(226, 210, 144));
        btnCentrosCostos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCentrosCostos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        labelFechaProgramada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelFechaProgramada.setText("Fecha programada");

        labelGrupoPlanif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelGrupoPlanif.setText("Grupo planif.");

        labelSolicitante.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSolicitante.setText("Solicitante");

        hasta15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta15.setText("hasta");

        labelResponsable.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelResponsable.setText("Responsable");

        btnGruposPlanificadores.setBackground(new java.awt.Color(226, 210, 144));
        btnGruposPlanificadores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGruposPlanificadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        fechaInicioProgramado1.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        btnSolicitantes.setBackground(new java.awt.Color(226, 210, 144));
        btnSolicitantes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSolicitantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        btnResponsables.setBackground(new java.awt.Color(226, 210, 144));
        btnResponsables.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnResponsables.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        btnSociedades.setBackground(new java.awt.Color(226, 210, 144));
        btnSociedades.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSociedades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        labelSociedad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSociedad.setText("Sociedad");

        hasta5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta5.setText("hasta");

        hasta9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta9.setText("hasta");

        hasta6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta6.setText("hasta");

        labelArea.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelArea.setText("Area");

        hasta7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta7.setText("hasta");

        labelEmplazamiento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelEmplazamiento.setText("Emplazamiento");

        hasta8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta8.setText("hasta");

        labelCentroCostos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCentroCostos.setText("Cost Center");

        hasta10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta10.setText("hasta");

        hasta11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta11.setText("hasta");

        hasta12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta12.setText("hasta");

        hasta13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta13.setText("hasta");

        hasta14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta14.setText("hasta");

        hasta2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta2.setText("hasta");

        numeroOrdenLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numeroOrdenLB.setText("Orden");

        hasta3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta3.setText("hasta");

        hasta4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta4.setText("hasta");

        labelTipoMtto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTipoMtto.setText("Tipo Mtto");

        btnOrdenes.setBackground(new java.awt.Color(226, 210, 144));
        btnOrdenes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnOrdenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        estatusOrdenLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        estatusOrdenLabel.setText("Estatus");

        hasta1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hasta1.setText("hasta");

        btnEstatus.setBackground(new java.awt.Color(226, 210, 144));
        btnEstatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/filter.png"))); // NOI18N

        fechaInicioProgramado2.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado1Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado1);
        jPanel_Rounded_Corners_Degradado1.setLayout(jPanel_Rounded_Corners_Degradado1Layout);
        jPanel_Rounded_Corners_Degradado1Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTipoMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCriticidad, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estatusOrdenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFechaProgramada, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelGrupoPlanif, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelClaseMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelArea, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sbOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbLocation1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEquipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbSolicitante1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbResponsable1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbCentoCostos1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbCriticidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbClaseOrden1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbTipoMtto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaInicioProgramado1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbGrupoPlanif1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEstatus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEmplazamiento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbArea1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbSociedad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hasta1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta14, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta15, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sbCriticidad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbClaseOrden2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbTipoMtto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbGrupoPlanif2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEmplazamiento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbArea2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbSociedad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEstatus2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaInicioProgramado2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbLocation2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEquipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbSolicitante2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbResponsable2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbCentoCostos2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbOrder2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTipos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClases, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCentrosCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCriticidades, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGruposPlanificadores, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFechasProgramadas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSolicitantes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnResponsables, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSociedades, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmplazamientos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(385, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado1Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(numeroOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbOrder2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelTipoMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbTipoMtto1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbTipoMtto2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTipos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelClaseMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbClaseOrden1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbClaseOrden2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClases, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCriticidad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbCriticidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbCriticidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCriticidades, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(hasta6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEquipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEquipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbLocation1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbLocation2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(hasta8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaInicioProgramado2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFechasProgramadas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFechaProgramada, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaInicioProgramado1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelGrupoPlanif, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbGrupoPlanif1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGruposPlanificadores, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbGrupoPlanif2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbSolicitante1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSolicitantes, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbSolicitante2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(hasta11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResponsables, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbResponsable1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbResponsable2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(estatusOrdenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEstatus1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEstatus2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbSociedad1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSociedades, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbSociedad2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbArea2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelEmplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmplazamientos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEmplazamiento1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEmplazamiento2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCentrosCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbCentoCostos1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbCentoCostos2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelScroll.setViewportView(bodyPanel);

        jPanel_Rounded_Corners_Degradado6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado6.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado6.setColor2(new java.awt.Color(202, 216, 237));

        btnSalir_CrearOrden.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir_CrearOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir_CrearOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        btnProcesarConsulta.setBackground(new java.awt.Color(226, 210, 144));
        btnProcesarConsulta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnProcesarConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/launch.png"))); // NOI18N

        btnNueva_Orden.setBackground(new java.awt.Color(226, 210, 144));
        btnNueva_Orden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnNueva_Orden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/new_empty.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado6Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado6);
        jPanel_Rounded_Corners_Degradado6.setLayout(jPanel_Rounded_Corners_Degradado6Layout);
        jPanel_Rounded_Corners_Degradado6Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir_CrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnProcesarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnNueva_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado6Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNueva_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProcesarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir_CrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScroll)
            .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnAreas;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnCentrosCostos;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnClases;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnCriticidades;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnEmplazamientos;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnEquipos;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnEstatus;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnFechasProgramadas;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnGruposPlanificadores;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnNueva_Orden;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnOrdenes;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnProcesarConsulta;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnResponsables;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir_CrearOrden;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSociedades;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSolicitantes;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnTipos;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnUbicaciones;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined estatusOrdenLabel;
    private com.toedter.calendar.JDateChooser fechaInicioProgramado1;
    private com.toedter.calendar.JDateChooser fechaInicioProgramado2;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta1;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta10;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta11;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta12;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta13;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta14;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta15;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta2;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta3;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta4;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta5;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta6;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta7;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta8;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined hasta9;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelArea;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCentroCostos;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelClaseMtto;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCriticidad;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelEmplazamiento;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelEquipo;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelFechaProgramada;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelGrupoPlanif;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelResponsable;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSociedad;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSolicitante;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelTipoMtto;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelUbicacion;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined numeroOrdenLB;
    private javax.swing.JScrollPane panelScroll;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbArea1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbArea2;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbCentoCostos1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbCentoCostos2;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbClaseOrden1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbClaseOrden2;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbCriticidad1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbCriticidad2;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbEmplazamiento1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbEmplazamiento2;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbEquipo1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbEquipo2;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbEstatus1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbEstatus2;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbGrupoPlanif1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbGrupoPlanif2;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbLocation1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbLocation2;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbOrder1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbOrder2;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbResponsable1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbResponsable2;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbSociedad1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbSociedad2;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbSolicitante1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbSolicitante2;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbTipoMtto1;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbTipoMtto2;
    // End of variables declaration//GEN-END:variables

    public void clearAll() {

        sbEstatus1.getTextBox().setText(null);
        sbEstatus2.getTextBox().setText(null);
        listaEstatus.clear();

        sbOrder1.getTextBox().setText(null);
        sbOrder2.getTextBox().setText(null);
        listaOrdenes.clear();

        sbTipoMtto1.getTextBox().setText(null);
        sbTipoMtto2.getTextBox().setText(null);
        listaTipos.clear();

        sbClaseOrden1.getTextBox().setText(null);
        sbClaseOrden2.getTextBox().setText(null);
        listaClases.clear();

        sbCriticidad1.getTextBox().setText(null);
        sbCriticidad2.getTextBox().setText(null);
        listaCriticidad.clear();

        sbEquipo1.getTextBox().setText(null);
        sbEquipo2.getTextBox().setText(null);
        listaEquipos.clear();

        sbLocation1.getTextBox().setText(null);
        sbLocation2.getTextBox().setText(null);
        listaUbicaciones.clear();

        fechaInicioProgramado1.setDate(null);
        fechaInicioProgramado2.setDate(null);
        listaFechas.clear();

        sbGrupoPlanif1.getTextBox().setText(null);
        sbGrupoPlanif2.getTextBox().setText(null);
        listaGruposPlanif.clear();

        sbSolicitante1.getTextBox().setText(null);
        sbSolicitante2.getTextBox().setText(null);
        listaSolicitante.clear();

        sbResponsable1.getTextBox().setText(null);
        sbResponsable2.getTextBox().setText(null);
        listaResponsable.clear();

        sbSociedad1.getTextBox().setText(null);
        sbSociedad2.getTextBox().setText(null);
        listaSociedades.clear();

        sbArea1.getTextBox().setText(null);
        sbArea2.getTextBox().setText(null);
        listaAreas.clear();

        sbEmplazamiento1.getTextBox().setText(null);
        sbEmplazamiento2.getTextBox().setText(null);
        listaEmplazamiento.clear();

        sbCentoCostos1.getTextBox().setText(null);
        sbCentoCostos2.getTextBox().setText(null);
        listaCentroCostos.clear();

        notifications(NOT.msg(NOT.FILTERS_CLEARED), TypeMessage.INFORMATION);

    }

    private void notifications(String msg, TypeMessage type) {

        new SystemMessages(LyraWorkspace.NotificationLabel, msg, type);

    }

    private void btnFiltrarNuevamente() {
        btnNueva_Orden.addActionListener((e) -> {
            clearAll();
        });
    }

    private void btnSalir() {

        btnSalir_CrearOrden.addActionListener((e) -> {
            JFrame ventanaPrincipal = (JFrame) SwingUtilities.getRoot(this);
            Invoke_JMenuBars.setMenuBar(ventanaPrincipal, ventanaPrincipal.getJMenuBar(),
                    LyraWorkspace.barMenu);
            PanelLoader.loadPanel(treeMenus, mainContainerPanel);
        });
    }

}
