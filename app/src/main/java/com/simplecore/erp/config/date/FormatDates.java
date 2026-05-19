package com.simplecore.erp.config.date;

import com.toedter.calendar.JDateChooser;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

public class FormatDates {

// Date and time format patterns used for formatting and parsing dates and times in various formats
    public static final String DATE_AMPM = "MM.dd.yyyy";
    public static final String DATE_AMPM_HOUR = "MM.dd.yyyy hh:mm a";
    public static final String DATE_AMPM_HOUR_SEC = "MM.dd.yyyy hh:mm:ss a";
    public static final String DATE_24H_HOUR = "MM.dd.yyyy HH:mm:ss";
    public static final String HOUR_AMPM = "hh:mm:ss";
    public static final String HOUR_24H = "HH:mm:ss";

    public static final String WINDOWS_TIME = "time.windors.com";//Responde
    public static final String GOOGLE_TIME = "time.google.com";//Responde
    public static final String APPLE_TIME = "time.apple.com";//Responde
    public static final String NTP_PROJECT = "pool.ntp.org";//Responde
    public static final String NIST = "time.nist.gov";//Responde
    public static final String UBUNTU_TIME = "ntp.ubuntu.com";//Responde
    public static final String USA_NAVY = "tick.usno.navy.mil";//No responde
    public static final String NTP_ORG = "ntp.org"; //No responde
    public static final String NTP_TIME = "ntp.time.org";//No responde

    private static String[] ntpServersList = {
        GOOGLE_TIME,
        WINDOWS_TIME,
        APPLE_TIME,
        NTP_PROJECT,
        NIST,
        UBUNTU_TIME
    };

    public static SimpleDateFormat dateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    
    //Validate if jdatechooser date is correct
    public static boolean isDateValid(JDateChooser dateChooser, String dateFormat, Date minDate) {
        // Get the selected date
        Date date = dateChooser.getDate();
        if (date == null) {
            return false; // No date selected
        }

        // Create a date format
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {
            // Format the selected date to a String
            String dateString = sdf.format(date);

            // Try to parse the date in the specified format
            Date parsedDate = sdf.parse(dateString);

            // Check if the date is greater than or equal to the minimum
            if (parsedDate.compareTo(minDate) >= 0) {
                return true; // The date meets the format and the minimum
            } else {
                return false; // The date is earlier than the minimum
            }
        } catch (ParseException e) {
            return false; // The date does not meet the format
        }
    }


    
    
    /*Connection to Time Servers on Internet to get UTC (USE INSTANT)*/
   
    public static Instant getInstantCurrentTimeFromNTP(String ntpServer) throws IOException {

        NTPUDPClient client = new NTPUDPClient();
        try {
            client.setDefaultTimeout(Duration.ofMillis(4000));
            client.open();
            InetAddress hostAddr = InetAddress.getByName(ntpServer);
            TimeInfo timeInfo = client.getTime(hostAddr);

            if (timeInfo == null) {
                return null;
            }

            long serverTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
            return Instant.ofEpochMilli(serverTime); // Devuelve un Instant en UTC

        } catch (UnknownHostException e) {
        
            // Log error if the NTP server cannot be resolved
            Logger.getLogger(FormatDates.class.getName()).log(Level.SEVERE, "Could not resolve NTP server: " + ntpServer, e);
            return null;
        
        } catch (IOException e) {
            
            // Log error if there is a communication error with the NTP server
            Logger.getLogger(FormatDates.class.getName()).log(Level.SEVERE, "Communication error with NTP server: " + ntpServer, e);
            return null;
        
        } finally {
            client.close();
        }
    }

    public static Instant getInstantCurrentTimeFromNTPServers() {

        for (String ntpServer : ntpServersList) {

            try {
                Instant date = getInstantCurrentTimeFromNTP(ntpServer);
                if (date != null) {
                    return date;
                }
            } catch (IOException ex) {
                // Log error if there is an issue obtaining time from the NTP server
                Logger.getLogger(FormatDates.class.getName()).log(Level.SEVERE, "Error getting time from NTP server: " + ntpServer, ex);
            }
        }

        return getUtcTime();
    }

    public static String getFormattedInstant(Instant instant, String pattern, String zoneId) {

        DateTimeFormatter formatter;

        if (zoneId != null) {
            formatter = DateTimeFormatter.ofPattern(pattern)
                    .withZone(ZoneId.of(zoneId));
        } else {
            formatter = DateTimeFormatter.ofPattern(pattern)
                    .withZone(ZoneOffset.UTC);
        }
        return formatter.format(instant);
    }

    public static String getZoneDescription(String zoneIdString) {
        try {
            // Convierte el string en un objeto ZoneId
            ZoneId zoneId = ZoneId.of(zoneIdString);

            // Obtiene la hora actual en la zona horaria especificada
            ZonedDateTime zdt = ZonedDateTime.now(zoneId);

            // Calcula el desplazamiento en segundos desde UTC
            int offsetInSeconds = zdt.getOffset().getTotalSeconds();
            String offset = String.format("UTC%s%02d:%02d",
                    offsetInSeconds >= 0 ? "+" : "-",
                    Math.abs(offsetInSeconds / 3600),
                    Math.abs((offsetInSeconds % 3600) / 60));

            // Devuelve la descripción concatenada
            return zoneId.getId() + " (" + offset + ")";

        } catch (Exception e) {
            // Maneja errores si el ID de la zona horaria no es válido
            return "Invalid Zone ID: " + zoneIdString;
        }
    }

    private static Instant getUtcTime() {
        
        // Get the current time in UTC
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    
        return calendar.getTime().toInstant();
    }
    
    
    //ProgressBar to return servers time
    public interface DateCallback {
        void onDateRetrieved(Date date);
    }

    public void getCurrentTimeFromNTPServers(JProgressBar bar, DateCallback callback) {

        SwingWorker<Date, Integer> worker = new SwingWorker<>() {
            @Override
            protected Date doInBackground() throws Exception {

                for (int i = 0; i < ntpServersList.length; i++) {

                    publish(10);

                    NTPUDPClient client = new NTPUDPClient();

                    try {
                        client.open();
                        client.setSoTimeout(Duration.ofMillis(3000));
                        publish(33);

                        InetAddress hostAddr = InetAddress.getByName(ntpServersList[i]);
                        TimeInfo timeInfo = client.getTime(hostAddr);
                        publish(70);

                        if (timeInfo == null) {
                            continue;
                        }
                        long serverTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
                        client.close();

                        publish(100);
                        return new Date(serverTime);

                    } catch (IOException e) {
                        continue;
                    }
                }
                publish(100);
                return Calendar.getInstance().getTime();
            }

            @Override
            protected void process(List<Integer> chunks) {
                bar.setValue(Collections.max(chunks));
            }

            @Override
            protected void done() {
                try {
                    Date date = get();
                    callback.onDateRetrieved(date);
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(FormatDates.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        worker.execute();
    }

}
