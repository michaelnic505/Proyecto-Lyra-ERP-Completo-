package com.simplecore.erp.client.main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.simplecore.erp.client.gui.connections.ServerConnectionsForm;
import java.awt.EventQueue;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class LyraClient {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            FlatIntelliJLaf.setup();
            new ServerConnectionsForm().setVisible(true);
        });
    }
}
