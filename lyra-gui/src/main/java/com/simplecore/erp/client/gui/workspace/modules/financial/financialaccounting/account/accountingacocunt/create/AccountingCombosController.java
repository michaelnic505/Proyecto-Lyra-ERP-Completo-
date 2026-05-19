package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.create;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.models.AcModComboItem;
import com.simplecore.erp.shared.models.dto.AccountSubclassDTO;
import com.simplecore.erp.shared.models.dto.AccountingAccountDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountClassesByModelRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassByClassRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountsBySubclassRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountClassesByModelRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountSubclassesRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountsBySubclassRetrieveResponse;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class AccountingCombosController {

    private final ActiveSession activeSession;
    private final ServerController serverConn;
    private final int modelId;

    public AccountingCombosController(int modelId, ActiveSession activeSession, ObjectOutputStream output, ObjectInputStream input) {
        this.activeSession = activeSession;
        this.modelId = modelId;
        this.serverConn = new ServerController(output, input);
    }
    private JComboBox classCombo;
    private JComboBox subclassCombo;
    private JComboBox parentCombo;
    private JTextField accountNumberTf;

    public void initializeCombos(JComboBox classCombo, JComboBox subclassCombo, JComboBox parentCombo, JTextField accountNumberTf) {
        this.classCombo = classCombo;
        this.subclassCombo = subclassCombo;
        this.parentCombo = parentCombo;
        this.accountNumberTf = accountNumberTf;
        initializeClassCombo();
    }

    private void initializeClassCombo() {
        try {
            String sessionId = activeSession.getSessionId();
            int userId = activeSession.getUserId();
            
            Object response = serverConn.sendRequest(new AccountClassesByModelRetrieveRequest(sessionId, userId, modelId));
            if (response != null) {
                if (response instanceof AccountClassesByModelRetrieveResponse classesResponse) {
                    loadDataInClassCombo(classCombo, classesResponse.getClassesList());
                }
            }
            setClassComboEvents();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountingCombosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setClassComboEvents() {
        classCombo.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                AcModComboItem item = (AcModComboItem) e.getItem();
                // Verificar si el item es nulo o su representación en String está vacía
                if (item == null || item.toString().trim().isEmpty()) {
                    subclassCombo.removeAllItems();
                    return;
                }

                int classId = item.getId();
                initializeSubclassesCombo(classId);
            }
        });
    }

    private void initializeSubclassesCombo(int classId) {
        try {
            subclassCombo.removeAllItems(); // Asegurar que no haya datos previos
            
            String sessionId = activeSession.getSessionId();
            int userId = activeSession.getUserId();
            
            Object response = serverConn.sendRequest(new AccountSubclassByClassRetrieveRequest(sessionId, userId, modelId, classId));
            if (response instanceof AccountSubclassesRetrieveResponse subclassesResponse && subclassesResponse.wasFound()) {
                // Agregar un elemento vacío al inicio
                subclassCombo.addItem(new AcModComboItem(null, "", null));
                for (AccountSubclassDTO classes : subclassesResponse.getSubclassesList()) {
                    subclassCombo.addItem(new AcModComboItem(classes.getSubclassCode(), classes.getSubclassName(), classes));
                }
            }
            setSubclassComboEvents();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountingCombosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setSubclassComboEvents() {
        subclassCombo.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {

                AcModComboItem item = (AcModComboItem) e.getItem();
                if (item == null || item.toString().trim().isEmpty()) {
                    accountNumberTf.setText(null);
                    parentCombo.removeAllItems();
                    return;
                }
                int subclassId = ((AccountSubclassDTO) item.getObjectClass()).getSubclassId();
                initializeParentAccountCombo(subclassId);
            }
        });
    }

    private void initializeParentAccountCombo(int subclassId) {
        try {
            parentCombo.removeAllItems();
            
            String sessionId = activeSession.getSessionId();
            int userId = activeSession.getUserId();
            
            Object response = serverConn.sendRequest(new AccountsBySubclassRetrieveRequest(sessionId, userId, subclassId));
            if (response instanceof AccountsBySubclassRetrieveResponse accountsResponse && accountsResponse.wasFound()) {
                parentCombo.addItem(new AcModComboItem(null, "", null));
                
                for (AccountingAccountDTO accountingAccount : accountsResponse.getAccountsList()) {
                    int accountNumber = Integer.parseInt(accountingAccount.getAccountCode());
                    String accountName = accountingAccount.getAccountName();
                    parentCombo.addItem(new AcModComboItem(accountNumber, accountName, accountingAccount));
                }
            }
            setParentComboEvents();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountingCombosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setParentComboEvents() {
        subclassCombo.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                AcModComboItem item = (AcModComboItem) e.getItem();
                if (item == null || item.toString().trim().isEmpty()) {
                    accountNumberTf.setText(null);
                }
            }
        });
    }

    private void loadDataInClassCombo(JComboBox combo, Map<Integer, String> dataMap) {
        combo.addItem(new AcModComboItem(null, "", null));
        for (Map.Entry<Integer, String> entry : dataMap.entrySet()) {
            combo.addItem(new AcModComboItem(entry.getKey(), entry.getValue(), null));
        }
        combo.setSelectedItem(0);
    }

}
