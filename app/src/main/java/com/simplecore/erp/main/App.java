package com.simplecore.erp.main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.simplecore.erp.config.LoggerConfig;
import java.awt.EventQueue;
import com.simplecore.erp.gui.dbconnections.DatabaseConnectionsFrame;
import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public  static void main(String[] args) {        
        EventQueue.invokeLater(() -> {
            FlatIntelliJLaf.setup();   
            LoggerConfig.configureLogger();
            LOGGER.info("Se ha arrancado el sistema.");
            new DatabaseConnectionsFrame().setVisible(true);
        });
    }
}
