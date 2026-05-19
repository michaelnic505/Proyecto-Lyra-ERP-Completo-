
package com.simplecore.erp.client.utils.time;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampUtils {

    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    /**
     * Convierte un Timestamp a String con formato yyyy.MM.dd HH:mm:ss
     */
    public static String formatForDisplay(Timestamp timestamp) {
        if (timestamp == null) return "";
        return timestamp.toLocalDateTime().format(DISPLAY_FORMATTER);
    }

    /**
     * Convierte un String con formato yyyy.MM.dd HH:mm:ss a Timestamp
     */
    public static Timestamp parseFromDisplay(String formattedDateTime) {
        if (formattedDateTime == null || formattedDateTime.isEmpty()) return null;
        LocalDateTime localDateTime = LocalDateTime.parse(formattedDateTime, DISPLAY_FORMATTER);
        return Timestamp.valueOf(localDateTime);
    }
}
