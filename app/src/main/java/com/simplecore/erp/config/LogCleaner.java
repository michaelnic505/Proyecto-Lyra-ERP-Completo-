package com.simplecore.erp.config;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

public class LogCleaner {
    private static final String LOG_DIR = "logs"; // Directorio de logs
    private static final int DAYS_TO_KEEP = 7;   // Días antes de eliminar

    public static void cleanOldLogs() {
        try (Stream<Path> files = Files.list(Paths.get(LOG_DIR))) {
            Instant cutoff = Instant.now().minus(DAYS_TO_KEEP, ChronoUnit.DAYS);

            files.filter(Files::isRegularFile)
                 .forEach(file -> {
                     try {
                         BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
                         Instant fileTime = attr.creationTime().toInstant();
                         if (fileTime.isBefore(cutoff)) {
                             Files.delete(file);
                             System.out.println("Deleted old log: " + file);
                         }
                     } catch (IOException e) {
                         System.err.println("Error deleting log: " + file + " - " + e.getMessage());
                     }
                 });
        } catch (IOException e) {
            System.err.println("Error accessing log directory: " + e.getMessage());
        }
    }
}
