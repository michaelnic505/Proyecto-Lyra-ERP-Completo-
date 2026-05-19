package com.simplecore.erp.server.config;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.ConsoleHandler;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */

public class LoggerConfig {
    private static final String LOG_DIR = "logs";
    private static final String LOG_FILE = LOG_DIR + "/application.log";

    public static void configureLogger() {
        try {
            // Ensure the log directory exists
            Files.createDirectories(Paths.get(LOG_DIR));

            // Configure the root logger
            Logger rootLogger = Logger.getLogger("");
            rootLogger.setLevel(Level.INFO);

            // Remove existing handlers to prevent duplicate logs
            for (Handler handler : rootLogger.getHandlers()) {
                rootLogger.removeHandler(handler);
            }
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO); // Asegura que muestre los logs adecuados
            rootLogger.addHandler(consoleHandler);

            // Configure the file handler with rotation
            FileHandler fileHandler = new FileHandler(LOG_FILE, 10 * 1024 * 1024, 5, true);
            fileHandler.setFormatter(new SimpleFormatter());
            rootLogger.addHandler(fileHandler);
        } catch (IOException e) {
            System.err.println("Error configuring logger: " + e.getMessage());
        }
    }
}


/* COMO USAR LOGGER:
LoggerConfig.configureLogger();
Logger logger = Logger.getLogger(UserRepository.class.getName());
logger.info("Logger configurado correctamente.");
*/