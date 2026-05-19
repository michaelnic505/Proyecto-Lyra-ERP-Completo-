package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders;

import javax.swing.JMenuItem;

public class ControlStatusMenuBarOrder {

    private JMenuItem menuOrdenTrabajo;
    private JMenuItem menuGuardarOrden;
    private JMenuItem menuNuevaOrden;
    private JMenuItem menuModificarOrden;
    private JMenuItem menuVisualizarOrden;

    private JMenuItem menuEstatus;
    private JMenuItem menuListadoEstatus;

    private JMenuItem menuEstatusPlaneacion;
    private JMenuItem menuEstatusAprobacion;
    private JMenuItem menuEstatusAprobada;
    private JMenuItem menuEstatusProgramada;
    private JMenuItem menuEstatusEjecucion;
    private JMenuItem menuEstatusEjecutada;
    private JMenuItem menuEstatusCerrada;
    private JMenuItem menuEstatusRechazado;
    private JMenuItem menuEstatusCancelada;

    private JMenuItem menuSalir;

    private JMenuItem menuCabeceraOrden;
    private JMenuItem menuEquipo;
    private JMenuItem menuUbicaciones;
    private JMenuItem menuTipoMantenimiento;
    private JMenuItem menuClasesMantenimiento;
    private JMenuItem menuPrioridades;
    private JMenuItem menuSistemas;
    private JMenuItem menuComponentes;
    private JMenuItem menuSintomas;

    private JMenuItem menuRecursos;
    private JMenuItem menuOperaciones;
    private JMenuItem menuMateriales;

    private JMenuItem menuCostosOrden;
    private JMenuItem menuCostos;

    public void setStatusComponent(String status) {
        switch (status) {

            // ST1_ORDER_CREATED  -  estatus si la orden esta siendo creada
            case "OCRTD" -> {

                getMenuOrdenTrabajo().setEnabled(true);
                
                getMenuGuardarOrden().setEnabled(true);
                getMenuNuevaOrden().setEnabled(true);
                getMenuModificarOrden().setEnabled(false);
                getMenuVisualizarOrden().setEnabled(false);

                getMenuEstatus().setEnabled(true);
                getMenuListadoEstatus().setEnabled(true);

                getMenuEstatusPlaneacion().setEnabled(true);
                getMenuEstatusAprobacion().setEnabled(false);
                getMenuEstatusAprobada().setEnabled(false);
                getMenuEstatusProgramada().setEnabled(false);
                getMenuEstatusEjecucion().setEnabled(false);
                getMenuEstatusEjecutada().setEnabled(false);
                getMenuEstatusCerrada().setEnabled(false);
                getMenuEstatusRechazado().setEnabled(false);
                getMenuEstatusCancelada().setEnabled(true);

                getMenuSalir().setEnabled(true);

                getMenuCabeceraOrden().setEnabled(true);
                getMenuEquipo().setEnabled(true);
                getMenuUbicaciones().setEnabled(true);
                getMenuTipoMantenimiento().setEnabled(true);
                getMenuClasesMantenimiento().setEnabled(true);
                getMenuPrioridades().setEnabled(true);
                getMenuSistemas().setEnabled(true);
                getMenuComponentes().setEnabled(true);
                getMenuSintomas().setEnabled(true);

                getMenuRecursos().setEnabled(true);
                getMenuOperaciones().setEnabled(true);
                getMenuMateriales().setEnabled(true);

                getMenuCostosOrden().setEnabled(true);
                getMenuCostos().setEnabled(true);

            }
            // ST2_ORDER_IN_PLANNING  -  estatus si la orden esta en planeacion
            case "OIPNN" -> {
                getMenuOrdenTrabajo().setEnabled(true);
                getMenuGuardarOrden().setEnabled(true);
                getMenuNuevaOrden().setEnabled(true);
                getMenuModificarOrden().setEnabled(false);
                getMenuVisualizarOrden().setEnabled(true);

                getMenuEstatus().setEnabled(true);
                getMenuListadoEstatus().setEnabled(true);

                getMenuEstatusPlaneacion().setEnabled(false);
                getMenuEstatusAprobacion().setEnabled(true);
                getMenuEstatusAprobada().setEnabled(false);
                getMenuEstatusProgramada().setEnabled(false);
                getMenuEstatusEjecucion().setEnabled(false);
                getMenuEstatusEjecutada().setEnabled(false);
                getMenuEstatusCerrada().setEnabled(false);
                getMenuEstatusRechazado().setEnabled(false);
                getMenuEstatusCancelada().setEnabled(true);

                getMenuSalir().setEnabled(true);

                getMenuCabeceraOrden().setEnabled(true);
                getMenuEquipo().setEnabled(false);
                getMenuUbicaciones().setEnabled(false);
                getMenuTipoMantenimiento().setEnabled(true);
                getMenuClasesMantenimiento().setEnabled(true);
                getMenuPrioridades().setEnabled(true);
                getMenuSistemas().setEnabled(true);
                getMenuComponentes().setEnabled(true);
                getMenuSintomas().setEnabled(true);

                getMenuRecursos().setEnabled(true);
                getMenuOperaciones().setEnabled(true);
                getMenuMateriales().setEnabled(true);

                getMenuCostosOrden().setEnabled(true);
                getMenuCostos().setEnabled(true);
            }
            //ST3_ORDER_UNDER_APPROVAL  -  Orden en estatus Orden en proceso de aprobacion
            case "OUAPP" -> {
                getMenuOrdenTrabajo().setEnabled(true);
                getMenuGuardarOrden().setEnabled(false);
                getMenuNuevaOrden().setEnabled(true);
                getMenuModificarOrden().setEnabled(false);
                getMenuVisualizarOrden().setEnabled(true);

                getMenuEstatus().setEnabled(true);
                getMenuListadoEstatus().setEnabled(true);

                getMenuEstatusPlaneacion().setEnabled(false);
                getMenuEstatusAprobacion().setEnabled(false);
                getMenuEstatusAprobada().setEnabled(true);
                getMenuEstatusProgramada().setEnabled(false);
                getMenuEstatusEjecucion().setEnabled(false);
                getMenuEstatusEjecutada().setEnabled(false);
                getMenuEstatusCerrada().setEnabled(false);
                getMenuEstatusRechazado().setEnabled(true);
                getMenuEstatusCancelada().setEnabled(false);

                getMenuSalir().setEnabled(true);

                getMenuCabeceraOrden().setEnabled(true);
                getMenuEquipo().setEnabled(false);
                getMenuUbicaciones().setEnabled(false);
                getMenuTipoMantenimiento().setEnabled(false);
                getMenuClasesMantenimiento().setEnabled(false);
                getMenuPrioridades().setEnabled(false);
                getMenuSistemas().setEnabled(false);
                getMenuComponentes().setEnabled(false);
                getMenuSintomas().setEnabled(false);

                getMenuRecursos().setEnabled(false);
                getMenuOperaciones().setEnabled(false);
                getMenuMateriales().setEnabled(false);

                getMenuCostosOrden().setEnabled(false);
                getMenuCostos().setEnabled(false);
            }
            //ST4_ORDER_APPROVED  -  Orden en estatus Orden ya aprobada
            case "OAPPV" -> {
                getMenuOrdenTrabajo().setEnabled(true);
                getMenuGuardarOrden().setEnabled(true);
                getMenuNuevaOrden().setEnabled(true);
                getMenuModificarOrden().setEnabled(false);
                getMenuVisualizarOrden().setEnabled(true);

                getMenuEstatus().setEnabled(true);
                getMenuListadoEstatus().setEnabled(true);

                getMenuEstatusPlaneacion().setEnabled(false);
                getMenuEstatusAprobacion().setEnabled(false);
                getMenuEstatusAprobada().setEnabled(false);
                getMenuEstatusProgramada().setEnabled(true);
                getMenuEstatusEjecucion().setEnabled(false);
                getMenuEstatusEjecutada().setEnabled(false);
                getMenuEstatusCerrada().setEnabled(false);
                getMenuEstatusRechazado().setEnabled(false);
                getMenuEstatusCancelada().setEnabled(true);

                getMenuSalir().setEnabled(true);

                getMenuCabeceraOrden().setEnabled(true);
                getMenuEquipo().setEnabled(false);
                getMenuUbicaciones().setEnabled(false);
                getMenuTipoMantenimiento().setEnabled(false);
                getMenuClasesMantenimiento().setEnabled(false);
                getMenuPrioridades().setEnabled(false);
                getMenuSistemas().setEnabled(false);
                getMenuComponentes().setEnabled(false);
                getMenuSintomas().setEnabled(false);

                getMenuRecursos().setEnabled(false);
                getMenuOperaciones().setEnabled(false);
                getMenuMateriales().setEnabled(false);

                getMenuCostosOrden().setEnabled(false);
                getMenuCostos().setEnabled(false);
            }
            //ST5_SCHEDULED_ORDER - Orden en estatus programada a la espera de ejecucion
            case "OSCHD" -> {
                getMenuOrdenTrabajo().setEnabled(true);
                getMenuGuardarOrden().setEnabled(true);
                getMenuNuevaOrden().setEnabled(true);
                getMenuModificarOrden().setEnabled(false);
                getMenuVisualizarOrden().setEnabled(true);

                getMenuEstatus().setEnabled(true);
                getMenuListadoEstatus().setEnabled(true);

                getMenuEstatusPlaneacion().setEnabled(false);
                getMenuEstatusAprobacion().setEnabled(false);
                getMenuEstatusAprobada().setEnabled(false);
                getMenuEstatusProgramada().setEnabled(false);
                getMenuEstatusEjecucion().setEnabled(true);
                getMenuEstatusEjecutada().setEnabled(false);
                getMenuEstatusCerrada().setEnabled(false);
                getMenuEstatusRechazado().setEnabled(false);
                getMenuEstatusCancelada().setEnabled(true);

                getMenuSalir().setEnabled(true);

                getMenuCabeceraOrden().setEnabled(true);
                getMenuEquipo().setEnabled(false);
                getMenuUbicaciones().setEnabled(false);
                getMenuTipoMantenimiento().setEnabled(false);
                getMenuClasesMantenimiento().setEnabled(false);
                getMenuPrioridades().setEnabled(false);
                getMenuSistemas().setEnabled(false);
                getMenuComponentes().setEnabled(false);
                getMenuSintomas().setEnabled(false);

                getMenuRecursos().setEnabled(false);
                getMenuOperaciones().setEnabled(false);
                getMenuMateriales().setEnabled(false);

                getMenuCostosOrden().setEnabled(false);
                getMenuCostos().setEnabled(false);
            }
            //ST6_ORDER_IN_EXECUTION  -  Orden en estatus de eejecucion
            case "OIEXN" -> {
                getMenuOrdenTrabajo().setEnabled(true);
                getMenuGuardarOrden().setEnabled(true);
                getMenuNuevaOrden().setEnabled(true);
                getMenuModificarOrden().setEnabled(false);
                getMenuVisualizarOrden().setEnabled(false);

                getMenuEstatus().setEnabled(true);
                getMenuListadoEstatus().setEnabled(true);

                getMenuEstatusPlaneacion().setEnabled(false);
                getMenuEstatusAprobacion().setEnabled(false);
                getMenuEstatusAprobada().setEnabled(false);
                getMenuEstatusProgramada().setEnabled(false);
                getMenuEstatusEjecucion().setEnabled(false);
                getMenuEstatusEjecutada().setEnabled(true);
                getMenuEstatusCerrada().setEnabled(false);
                getMenuEstatusRechazado().setEnabled(false);
                getMenuEstatusCancelada().setEnabled(false);

                getMenuSalir().setEnabled(true);

                getMenuCabeceraOrden().setEnabled(true);
                getMenuEquipo().setEnabled(false);
                getMenuUbicaciones().setEnabled(false);
                getMenuTipoMantenimiento().setEnabled(false);
                getMenuClasesMantenimiento().setEnabled(false);
                getMenuPrioridades().setEnabled(false);
                getMenuSistemas().setEnabled(false);
                getMenuComponentes().setEnabled(false);
                getMenuSintomas().setEnabled(false);

                getMenuRecursos().setEnabled(true);
                getMenuOperaciones().setEnabled(true);
                getMenuMateriales().setEnabled(true);

                getMenuCostosOrden().setEnabled(true);
                getMenuCostos().setEnabled(true);
            }
            //ST7_ORDER_EXECUTED  - Estatus orden ejeutada
            case "OEXTD" -> {
                getMenuOrdenTrabajo().setEnabled(true);
                getMenuGuardarOrden().setEnabled(true);
                getMenuNuevaOrden().setEnabled(true);
                getMenuModificarOrden().setEnabled(false);
                getMenuVisualizarOrden().setEnabled(false);

                getMenuEstatus().setEnabled(true);
                getMenuListadoEstatus().setEnabled(true);

                getMenuEstatusPlaneacion().setEnabled(false);
                getMenuEstatusAprobacion().setEnabled(false);
                getMenuEstatusAprobada().setEnabled(false);
                getMenuEstatusProgramada().setEnabled(false);
                getMenuEstatusEjecucion().setEnabled(false);
                getMenuEstatusEjecutada().setEnabled(false);
                getMenuEstatusCerrada().setEnabled(true);
                getMenuEstatusRechazado().setEnabled(false);
                getMenuEstatusCancelada().setEnabled(false);

                getMenuSalir().setEnabled(true);

                getMenuCabeceraOrden().setEnabled(true);
                getMenuEquipo().setEnabled(false);
                getMenuUbicaciones().setEnabled(false);
                getMenuTipoMantenimiento().setEnabled(false);
                getMenuClasesMantenimiento().setEnabled(false);
                getMenuPrioridades().setEnabled(false);
                getMenuSistemas().setEnabled(false);
                getMenuComponentes().setEnabled(false);
                getMenuSintomas().setEnabled(false);

                getMenuRecursos().setEnabled(true);
                getMenuOperaciones().setEnabled(true);
                getMenuMateriales().setEnabled(true);

                getMenuCostosOrden().setEnabled(true);
                getMenuCostos().setEnabled(true);
            }
            //ST8_CLOSED_ORDER - Orden en estatus cerrada
            case "OCLSD" -> {
                getMenuOrdenTrabajo().setEnabled(true);
                getMenuGuardarOrden().setEnabled(false);
                getMenuNuevaOrden().setEnabled(true);
                getMenuModificarOrden().setEnabled(false);
                getMenuVisualizarOrden().setEnabled(true);

                getMenuEstatus().setEnabled(true);
                getMenuListadoEstatus().setEnabled(true);

                getMenuEstatusPlaneacion().setEnabled(false);
                getMenuEstatusAprobacion().setEnabled(false);
                getMenuEstatusAprobada().setEnabled(false);
                getMenuEstatusProgramada().setEnabled(false);
                getMenuEstatusEjecucion().setEnabled(false);
                getMenuEstatusEjecutada().setEnabled(false);
                getMenuEstatusCerrada().setEnabled(false);
                getMenuEstatusRechazado().setEnabled(false);
                getMenuEstatusCancelada().setEnabled(false);

                getMenuSalir().setEnabled(true);

                getMenuCabeceraOrden().setEnabled(true);
                getMenuEquipo().setEnabled(false);
                getMenuUbicaciones().setEnabled(false);
                getMenuTipoMantenimiento().setEnabled(false);
                getMenuClasesMantenimiento().setEnabled(false);
                getMenuPrioridades().setEnabled(false);
                getMenuSistemas().setEnabled(false);
                getMenuComponentes().setEnabled(false);
                getMenuSintomas().setEnabled(false);

                getMenuRecursos().setEnabled(true);
                getMenuOperaciones().setEnabled(true);
                getMenuMateriales().setEnabled(true);

                getMenuCostosOrden().setEnabled(true);
                getMenuCostos().setEnabled(true);
            }
            //ST9_ORDER_REJECTED  -  Orden en estatus rechazada
            case "ORJTD" -> {
                getMenuOrdenTrabajo().setEnabled(true);
                getMenuGuardarOrden().setEnabled(false);
                getMenuNuevaOrden().setEnabled(true);
                getMenuModificarOrden().setEnabled(false);
                getMenuVisualizarOrden().setEnabled(true);

                getMenuEstatus().setEnabled(true);
                getMenuListadoEstatus().setEnabled(true);

                getMenuEstatusPlaneacion().setEnabled(false);
                getMenuEstatusAprobacion().setEnabled(false);
                getMenuEstatusAprobada().setEnabled(false);
                getMenuEstatusProgramada().setEnabled(false);
                getMenuEstatusEjecucion().setEnabled(false);
                getMenuEstatusEjecutada().setEnabled(false);
                getMenuEstatusCerrada().setEnabled(false);
                getMenuEstatusRechazado().setEnabled(false);
                getMenuEstatusCancelada().setEnabled(false);

                getMenuSalir().setEnabled(true);

                getMenuCabeceraOrden().setEnabled(true);
                getMenuEquipo().setEnabled(false);
                getMenuUbicaciones().setEnabled(false);
                getMenuTipoMantenimiento().setEnabled(false);
                getMenuClasesMantenimiento().setEnabled(false);
                getMenuPrioridades().setEnabled(false);
                getMenuSistemas().setEnabled(false);
                getMenuComponentes().setEnabled(false);
                getMenuSintomas().setEnabled(false);

                getMenuRecursos().setEnabled(true);
                getMenuOperaciones().setEnabled(true);
                getMenuMateriales().setEnabled(true);

                getMenuCostosOrden().setEnabled(true);
                getMenuCostos().setEnabled(true);
            }
            //ST10_ORDER_CANCELED - Orden cancelada
            case "OCCLD" -> {
                getMenuOrdenTrabajo().setEnabled(true);
                getMenuGuardarOrden().setEnabled(false);
                getMenuNuevaOrden().setEnabled(true);
                getMenuModificarOrden().setEnabled(false);
                getMenuVisualizarOrden().setEnabled(true);

                getMenuEstatus().setEnabled(true);
                getMenuListadoEstatus().setEnabled(true);

                getMenuEstatusPlaneacion().setEnabled(false);
                getMenuEstatusAprobacion().setEnabled(false);
                getMenuEstatusAprobada().setEnabled(false);
                getMenuEstatusProgramada().setEnabled(false);
                getMenuEstatusEjecucion().setEnabled(false);
                getMenuEstatusEjecutada().setEnabled(false);
                getMenuEstatusCerrada().setEnabled(false);
                getMenuEstatusRechazado().setEnabled(false);
                getMenuEstatusCancelada().setEnabled(false);

                getMenuSalir().setEnabled(true);

                getMenuCabeceraOrden().setEnabled(true);
                getMenuEquipo().setEnabled(false);
                getMenuUbicaciones().setEnabled(false);
                getMenuTipoMantenimiento().setEnabled(false);
                getMenuClasesMantenimiento().setEnabled(false);
                getMenuPrioridades().setEnabled(false);
                getMenuSistemas().setEnabled(false);
                getMenuComponentes().setEnabled(false);
                getMenuSintomas().setEnabled(false);

                getMenuRecursos().setEnabled(true);
                getMenuOperaciones().setEnabled(true);
                getMenuMateriales().setEnabled(true);

                getMenuCostosOrden().setEnabled(true);
                getMenuCostos().setEnabled(true);
            }

        }
    }

    public JMenuItem getMenuOrdenTrabajo() {
        return menuOrdenTrabajo;
    }

    public JMenuItem getMenuGuardarOrden() {
        return menuGuardarOrden;
    }

    public JMenuItem getMenuNuevaOrden() {
        return menuNuevaOrden;
    }

    public JMenuItem getMenuModificarOrden() {
        return menuModificarOrden;
    }

    public JMenuItem getMenuVisualizarOrden() {
        return menuVisualizarOrden;
    }

    public JMenuItem getMenuEstatus() {
        return menuEstatus;
    }

    public JMenuItem getMenuListadoEstatus() {
        return menuListadoEstatus;
    }

    public JMenuItem getMenuEstatusPlaneacion() {
        return menuEstatusPlaneacion;
    }

    public JMenuItem getMenuEstatusAprobacion() {
        return menuEstatusAprobacion;
    }

    public JMenuItem getMenuEstatusAprobada() {
        return menuEstatusAprobada;
    }

    public JMenuItem getMenuEstatusProgramada() {
        return menuEstatusProgramada;
    }

    public JMenuItem getMenuEstatusEjecucion() {
        return menuEstatusEjecucion;
    }

    public JMenuItem getMenuEstatusEjecutada() {
        return menuEstatusEjecutada;
    }

    public JMenuItem getMenuEstatusCerrada() {
        return menuEstatusCerrada;
    }

    public JMenuItem getMenuEstatusRechazado() {
        return menuEstatusRechazado;
    }

    public JMenuItem getMenuEstatusCancelada() {
        return menuEstatusCancelada;
    }

    public JMenuItem getMenuSalir() {
        return menuSalir;
    }

    public JMenuItem getMenuCabeceraOrden() {
        return menuCabeceraOrden;
    }

    public JMenuItem getMenuEquipo() {
        return menuEquipo;
    }

    public JMenuItem getMenuUbicaciones() {
        return menuUbicaciones;
    }

    public JMenuItem getMenuTipoMantenimiento() {
        return menuTipoMantenimiento;
    }

    public JMenuItem getMenuClasesMantenimiento() {
        return menuClasesMantenimiento;
    }

    public JMenuItem getMenuPrioridades() {
        return menuPrioridades;
    }

    public JMenuItem getMenuSistemas() {
        return menuSistemas;
    }

    public JMenuItem getMenuComponentes() {
        return menuComponentes;
    }

    public JMenuItem getMenuSintomas() {
        return menuSintomas;
    }

    public JMenuItem getMenuRecursos() {
        return menuRecursos;
    }

    public JMenuItem getMenuOperaciones() {
        return menuOperaciones;
    }

    public JMenuItem getMenuMateriales() {
        return menuMateriales;
    }

    public JMenuItem getMenuCostosOrden() {
        return menuCostosOrden;
    }

    public JMenuItem getMenuCostos() {
        return menuCostos;
    }

    public void setMenuOrdenTrabajo(JMenuItem menuOrdenTrabajo) {
        this.menuOrdenTrabajo = menuOrdenTrabajo;
    }

    public void setMenuGuardarOrden(JMenuItem menuGuardarOrden) {
        this.menuGuardarOrden = menuGuardarOrden;
    }

    public void setMenuNuevaOrden(JMenuItem menuNuevaOrden) {
        this.menuNuevaOrden = menuNuevaOrden;
    }

    public void setMenuModificarOrden(JMenuItem menuModificarOrden) {
        this.menuModificarOrden = menuModificarOrden;
    }

    public void setMenuVisualizarOrden(JMenuItem menuVisualizarOrden) {
        this.menuVisualizarOrden = menuVisualizarOrden;
    }

    public void setMenuEstatus(JMenuItem menuEstatus) {
        this.menuEstatus = menuEstatus;
    }

    public void setMenuListadoEstatus(JMenuItem menuListadoEstatus) {
        this.menuListadoEstatus = menuListadoEstatus;
    }

    public void setMenuEstatusPlaneacion(JMenuItem menuEstatusPlaneacion) {
        this.menuEstatusPlaneacion = menuEstatusPlaneacion;
    }

    public void setMenuEstatusAprobacion(JMenuItem menuEstatusAprobacion) {
        this.menuEstatusAprobacion = menuEstatusAprobacion;
    }

    public void setMenuEstatusAprobada(JMenuItem menuEstatusAprobada) {
        this.menuEstatusAprobada = menuEstatusAprobada;
    }

    public void setMenuEstatusProgramada(JMenuItem menuEstatusProgramada) {
        this.menuEstatusProgramada = menuEstatusProgramada;
    }

    public void setMenuEstatusEjecucion(JMenuItem menuEstatusEjecucion) {
        this.menuEstatusEjecucion = menuEstatusEjecucion;
    }

    public void setMenuEstatusEjecutada(JMenuItem menuEstatusEjecutada) {
        this.menuEstatusEjecutada = menuEstatusEjecutada;
    }

    public void setMenuEstatusCerrada(JMenuItem menuEstatusCerrada) {
        this.menuEstatusCerrada = menuEstatusCerrada;
    }

    public void setMenuEstatusRechazado(JMenuItem menuEstatusRechazado) {
        this.menuEstatusRechazado = menuEstatusRechazado;
    }

    public void setMenuEstatusCancelada(JMenuItem menuEstatusCancelada) {
        this.menuEstatusCancelada = menuEstatusCancelada;
    }

    public void setMenuSalir(JMenuItem menuSalir) {
        this.menuSalir = menuSalir;
    }

    public void setMenuCabeceraOrden(JMenuItem menuCabeceraOrden) {
        this.menuCabeceraOrden = menuCabeceraOrden;
    }

    public void setMenuEquipo(JMenuItem menuEquipo) {
        this.menuEquipo = menuEquipo;
    }

    public void setMenuUbicaciones(JMenuItem menuUbicaciones) {
        this.menuUbicaciones = menuUbicaciones;
    }

    public void setMenuTipoMantenimiento(JMenuItem menuTipoMantenimiento) {
        this.menuTipoMantenimiento = menuTipoMantenimiento;
    }

    public void setMenuClasesMantenimiento(JMenuItem menuClasesMantenimiento) {
        this.menuClasesMantenimiento = menuClasesMantenimiento;
    }

    public void setMenuPrioridades(JMenuItem menuPrioridades) {
        this.menuPrioridades = menuPrioridades;
    }

    public void setMenuSistemas(JMenuItem menuSistemas) {
        this.menuSistemas = menuSistemas;
    }

    public void setMenuComponentes(JMenuItem menuComponentes) {
        this.menuComponentes = menuComponentes;
    }

    public void setMenuSintomas(JMenuItem menuSintomas) {
        this.menuSintomas = menuSintomas;
    }

    public void setMenuRecursos(JMenuItem menuRecursos) {
        this.menuRecursos = menuRecursos;
    }

    public void setMenuOperaciones(JMenuItem menuOperaciones) {
        this.menuOperaciones = menuOperaciones;
    }

    public void setMenuMateriales(JMenuItem menuMateriales) {
        this.menuMateriales = menuMateriales;
    }

    public void setMenuCostosOrden(JMenuItem menuCostosOrden) {
        this.menuCostosOrden = menuCostosOrden;
    }

    public void setMenuCostos(JMenuItem menuCostos) {
        this.menuCostos = menuCostos;
    }

}
