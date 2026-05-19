package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class CalculoHorasFechas {

    private static double hrs;

    public static double getHourCount(Date fechaInicio, Date fechaFin, String horaInicio, String horaFin) {

        try {

            SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy.MM.dd");
            SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat formatFechaHora = new SimpleDateFormat("yyyy.MM.dd hh:mm a");

            String fechaI = formatFecha.format(fechaInicio);
            String fechaF = formatFecha.format(fechaFin);

            Date horaI = formatHora.parse(horaInicio);
            Date horaF = formatHora.parse(horaFin);

            String hrI = formatHora.format(horaI);
            String hrF = formatHora.format(horaF);

            Date fechaHoraI = formatFechaHora.parse(fechaI + " " + hrI);
            Date fechaHoraF = formatFechaHora.parse(fechaF + " " + hrF);

            long cantHoras = fechaHoraF.getTime() - fechaHoraI.getTime();

            hrs = (int) TimeUnit.MILLISECONDS.toHours(cantHoras);

        } catch (ParseException ex) {
            Logger.getLogger(CalculoHorasFechas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hrs;
    }

    public static boolean fechaFinalMenorInicial(Date fechaInicio, Date fechaFin, String horaInicio, String horaFin) {

        long cantHoras = 0;
        try {

            SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy.MM.dd");
            SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat formatFechaHora = new SimpleDateFormat("yyyy.MM.dd hh:mm a");

            String fechaI = formatFecha.format(fechaInicio);
            String fechaF = formatFecha.format(fechaFin);

            Date horaI = formatHora.parse(horaInicio);
            Date horaF = formatHora.parse(horaFin);

            String hrI = formatHora.format(horaI);
            String hrF = formatHora.format(horaF);

            Date fechaHoraI = formatFechaHora.parse(fechaI + " " + hrI);
            Date fechaHoraF = formatFechaHora.parse(fechaF + " " + hrF);

            cantHoras = fechaHoraF.getTime() - fechaHoraI.getTime();

            if (cantHoras < 0) {
                return true;
            }

        } catch (ParseException ex) {
            Logger.getLogger(CalculoHorasFechas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    

}
