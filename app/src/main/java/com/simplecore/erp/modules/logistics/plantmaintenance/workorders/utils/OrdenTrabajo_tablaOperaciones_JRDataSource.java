
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.utils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class OrdenTrabajo_tablaOperaciones_JRDataSource implements JRDataSource{

    Object[][] listaOperaciones;
    Object valorCampo;
    int index = -1;

    @Override
    public boolean next() throws JRException {
        index++;        
        return (index < listaOperaciones.length);
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        
        String nombreCampo = jrf.getName();
        
        switch(nombreCampo){
         
            case "NUMERO_OPERACION" ->
                valorCampo = listaOperaciones[index][0];
            case "DESCRIPCION_OPERACION" ->
                valorCampo = listaOperaciones[index][1];
            case "CANTIDAD_OPERACION" ->
                valorCampo = listaOperaciones[index][2];
            case "PRECIO_UNITARIO_OPERACION" ->
                valorCampo = listaOperaciones[index][3];
            case "UNIDAD_MEDIDA" ->
                valorCampo = listaOperaciones[index][4];
            case "ID_TIPO_OPERACION" ->
                valorCampo = listaOperaciones[index][5];
            case "TIPO_OPERACION" ->
                valorCampo = listaOperaciones[index][6];
            case "DURACION_OPERACION" ->
                valorCampo = listaOperaciones[index][7];
            case "MONTO_TOTAL_OPERACION" ->
                valorCampo = listaOperaciones[index][8];
            case "PAQUETE" ->
                valorCampo = listaOperaciones[index][9];

        }

        return valorCampo;
    }

    public OrdenTrabajo_tablaOperaciones_JRDataSource() {
        listaOperaciones = new Object[][]{{"1", "MANTENIMIENTO 250 HRS AUTOMOTRIZ", "1", "EA", "MEC", "MECANICO", "3.00", "1,243.00", "1H"},
        {"2", "MANTENIMIENTO 500 HRS AUTOMOTRIZ", "1", "EA", "MEC", "MECANICO", "4.00", "1,500.00", "2H"},
        {"3", "MANTENIMIENTO 1000 HRS AUTOMOTRIZ", "1", "EA", "MEC", "MECANICO", "6.00", "2,500.00", "3H"},
        {"4", "MANTENIMIENTO 2000 HRS AUTOMOTRIZ", "1", "EA", "MEC", "MECANICO", "8.00", "3,500.00", "4H"}};

    }


}
