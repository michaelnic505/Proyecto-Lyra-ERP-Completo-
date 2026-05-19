package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.utils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class OrdenTrabajoDataSource implements JRDataSource {


    private Object NUMERO_ORDEN;
    private Object ID_ESTATUS;
    private Object DESCRIPCION_ESTATUS;
    private Object DESCRIPCION_GENERAL;
    private Object TIPO_MTTO;
    private Object CLASE_MTTO;
    private Object CRITICIDAD;
    private Object SISTEMA;
    private Object COMPONENTE;
    private Object SINTOMA;
    private Object FECHA_INICIO;
    private Object FECHA_FIN;
    private Object FECHA_PROG;
    private Object FECHA_CIERRE;
    private Object FECHA_CREACION_ORDEN;
    private Object FECHA_AUTORIZADO;
    private Object CODIGO_EQUIPO;
    private Object DESCRIPCION_EQUIPO;
    private Object CENTRO_COSTOS;
    private Object DESCRIPCION_CENTRO_COSTOS;
    private Object AREA;
    private Object DESCRIPCION_AREA;
    private Object UBICACION;
    private Object DESCRIPCION_UBICACION;
    private Object TIEMPO_ESTIMADO;
    private Object TIEMPO_REAL;
    private Object COSTO_ESTIMADO;
    private Object COSTO_REAL;
    private Object ORDEN_CREADA_POR;
    private Object ORDEN_PLANEADA_POR;
    private Object ORDEN_APROBADA_POR;
    private Object ORDEN_EJECUTADA_POR;
    private Object TEXTO_EXTENDIDO;
    private Object VALOR_CONTADOR;
    private Object HOJA_RUTA;
    private Object PLAN_MTTO;
    private Object PUNTO_MEDIDA;

    Object valor;
    int detener = 0;

    @Override
    public boolean next() throws JRException {        
        detener++;
        return (detener == 1);

    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {

        String campo = jrf.getName();

        switch (campo) {

            case "NUM_ORDEN" -> valor = NUMERO_ORDEN;
            case "ID_ESTATUS" -> valor = ID_ESTATUS;
            case "DESCRIPCION_ESTATUS" -> valor = DESCRIPCION_ESTATUS;
            case "DESCRIPCION_GENERAL" -> valor = DESCRIPCION_GENERAL;
            case "TIPO_MTTO" -> valor = TIPO_MTTO;
            case "CLASE_MTTO" -> valor = CLASE_MTTO;
            case "CRITICIDAD" -> valor = CRITICIDAD;
            case "SISTEMA" -> valor = SISTEMA;
            case "COMPONENTE" -> valor = COMPONENTE;
            case "SINTOMA" -> valor = SINTOMA;
            case "FECHA_INICIO" -> valor = FECHA_INICIO;
            case "FECHA_FIN" -> valor = FECHA_FIN;
            case "FECHA_PROG" -> valor = FECHA_PROG;
            case "FECHA_CIERRE" -> valor = FECHA_CIERRE;
            case "FECHA_CREACION_ORDEN" -> valor = FECHA_CREACION_ORDEN;
            case "FECHA_AUTORIZADO" -> valor = FECHA_AUTORIZADO;
            case "CODIGO_EQUIPO" -> valor = CODIGO_EQUIPO;
            case "DESCRIPCION_EQUIPO" -> valor = DESCRIPCION_EQUIPO;
            case "CENTRO_COSTOS" -> valor = CENTRO_COSTOS;
            case "DESCRIPCION_CENTRO_COSTOS" -> valor = DESCRIPCION_CENTRO_COSTOS;
            case "AREA" -> valor = AREA;
            case "DESCRIPCION_AREA" -> valor = DESCRIPCION_AREA;
            case "UBICACION" -> valor = UBICACION;
            case "DESCRIPCION_UBICACION" -> valor = DESCRIPCION_UBICACION;
            case "TIEMPO_ESTIMADO" -> valor = TIEMPO_ESTIMADO;
            case "TIEMPO_REAL" -> valor = TIEMPO_REAL;
            case "COSTO_ESTIMADO" -> valor = COSTO_ESTIMADO;
            case "COSTO_REAL" -> valor = COSTO_REAL;
            case "ORDEN_CREADA_POR" -> valor = ORDEN_CREADA_POR;
            case "ORDEN_PLANEADA_POR" -> valor = ORDEN_PLANEADA_POR;
            case "ORDEN_APROBADA_POR" -> valor = ORDEN_APROBADA_POR;
            case "ORDEN_EJECUTADA_POR" -> valor = ORDEN_EJECUTADA_POR;
            case "TEXTO_EXTENDIDO" -> valor = TEXTO_EXTENDIDO;
            case "VALOR_CONTADOR" -> valor = VALOR_CONTADOR;
            case "HOJA_RUTA" -> valor = HOJA_RUTA;
            case "PLAN_MTTO" -> valor = PLAN_MTTO;
            case "PUNTO_MEDIDA" -> valor = PUNTO_MEDIDA;
        }

        return valor;
    }
    
    

    public OrdenTrabajoDataSource(OrdenTrabajo_Campos orden) {
        asignarValores(orden);
    }


    private void asignarValores(OrdenTrabajo_Campos orden) {

        this.NUMERO_ORDEN = orden.getNUMERO_ORDEN();
        this.ID_ESTATUS = orden.getID_ESTATUS();
        this.DESCRIPCION_ESTATUS = orden.getDESCRIPCION_ESTATUS();
        this.DESCRIPCION_GENERAL = orden.getDESCRIPCION_GENERAL();
        this.TIPO_MTTO = orden.getTIPO_MTTO();
        this.CLASE_MTTO = orden.getCLASE_MTTO();
        this.CRITICIDAD = orden.getCRITICIDAD();
        this.SISTEMA = orden.getSISTEMA();
        this.COMPONENTE = orden.getCOMPONENTE();
        this.SINTOMA = orden.getSINTOMA();
        this.FECHA_INICIO = orden.getFECHA_INICIO();
        this.FECHA_FIN = orden.getFECHA_FIN();
        this.FECHA_PROG = orden.getFECHA_PROG();
        this.FECHA_CIERRE = orden.getFECHA_CIERRE();
        this.FECHA_CREACION_ORDEN = orden.getFECHA_CREACION_ORDEN();
        this.FECHA_AUTORIZADO = orden.getFECHA_AUTORIZADO();
        this.CODIGO_EQUIPO = orden.getCODIGO_EQUIPO();
        this.DESCRIPCION_EQUIPO = orden.getDESCRIPCION_EQUIPO();
        this.CENTRO_COSTOS = orden.getCENTRO_COSTOS();
        this.DESCRIPCION_CENTRO_COSTOS = orden.getDESCRIPCION_CENTRO_COSTOS();
        this.AREA = orden.getAREA();
        this.DESCRIPCION_AREA = orden.getDESCRIPCION_AREA();
        this.UBICACION = orden.getUBICACION();
        this.DESCRIPCION_UBICACION = orden.getDESCRIPCION_UBICACION();
        this.TIEMPO_ESTIMADO = orden.getTIEMPO_ESTIMADO();
        this.TIEMPO_REAL = orden.getTIEMPO_REAL();
        this.COSTO_ESTIMADO = orden.getCOSTO_ESTIMADO();
        this.COSTO_REAL = orden.getCOSTO_REAL();
        this.ORDEN_CREADA_POR = orden.getORDEN_CREADA_POR();
        this.ORDEN_PLANEADA_POR = orden.getORDEN_PLANEADA_POR();
        this.ORDEN_APROBADA_POR = orden.getORDEN_APROBADA_POR();
        this.ORDEN_EJECUTADA_POR = orden.getORDEN_EJECUTADA_POR();
        this.TEXTO_EXTENDIDO = orden.getTEXTO_EXTENDIDO();
        this.VALOR_CONTADOR = orden.getVALOR_CONTADOR();
        this.HOJA_RUTA = orden.getHOJA_RUTA();
        this.PLAN_MTTO = orden.getPLAN_MTTO();
        this.PUNTO_MEDIDA = orden.getPUNTO_MEDIDA();

    }

}
