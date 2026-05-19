
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.model.ComboItem;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.ChartOfAccountStatusRetrieveRequest;
import com.simplecore.erp.shared.responses.types.ChartOfAccountStatusRetrieveResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.swing.JComboBox;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class ChartOfAccountStatusService {

    private final JComboBox<ComboItem> comboItem;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final ActiveSession session;
    private ServerController serverController;

    private ChartOfAccountStatusService(Builder builder) {
        this.comboItem = builder.comboItem;
        this.output = builder.output;
        this.input = builder.input;
        this.session = builder.session;
        serverController = new ServerController(output, input);
        initializeCombo();
    }

    private void initializeCombo() {
        String[] data = sendDataRequest();

        IntStream.range(0, data.length)
                .forEach(i -> {
                    String item = data[i];
                    // Puedes usar 'i' para el índice
                    comboItem.addItem(new ComboItem(i, item, null));
                });
    }

    private String[] sendDataRequest() {
        String sessionId = session.getSessionId();
        int userId = session.getUserId();

        try {
            var request = new ChartOfAccountStatusRetrieveRequest(sessionId, userId);
            Object response = serverController.sendRequest(request);
            if (response instanceof ChartOfAccountStatusRetrieveResponse accountingStandards) {
                return accountingStandards.getStatus();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ChartOfAccountStatusService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Builder Class
    public static class Builder {

        private JComboBox<ComboItem> comboItem;
        private ObjectOutputStream output;
        private ObjectInputStream input;
        private ActiveSession session;

        // Setters para cada propiedad opcional de la clase
        public Builder withComboItem(JComboBox<ComboItem> comboItem) {
            this.comboItem = comboItem;
            return this;
        }

        public Builder withOutput(ObjectOutputStream output) {
            this.output = output;
            return this;
        }

        public Builder withInput(ObjectInputStream input) {
            this.input = input;
            return this;
        }

        public Builder withSession(ActiveSession session) {
            this.session = session;
            return this;
        }

        // Método para construir el objeto AccountStandardsService
        public ChartOfAccountStatusService build() {
            // Verificar si todos los campos necesarios están presentes antes de construir el objeto
            if (this.comboItem == null || this.output == null || this.input == null || this.session == null) {
                throw new IllegalStateException("All fields must be set");
            }
            return new ChartOfAccountStatusService(this);
        }
    }
}
