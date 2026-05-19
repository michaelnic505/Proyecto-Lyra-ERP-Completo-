package com.simplecore.erp.client.controllers.transaction;

import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.change.InitChangeAccountingAccount;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.create.InitCreateAccountingAccount;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.InitViewAccountingAccount;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.gui.InitViewListAccountingAccount;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.classes.InitDisplayAccountClasses;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.models.InitManagementAccountModels;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.subclasses.InitManageAccountingSubclasses;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.init.InitModifyChartOfAccounts;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.init.InitCreateChartsOfAccounts;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.init.InitViewChartOfAccounts;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.init.InitCreateFICompany;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.init.InitModifyFICompany;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.init.InitViewFICompany;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.init.InitFinancialAccountSetup;
import com.simplecore.erp.client.models.maintree.BusinessTransactions;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class TransactionRouter {
    
    // Stores the mapping of transaction codes to their corresponding handlers.
    // Almacena la asignación de códigos de transacción a sus manejadores correspondientes.
    private static final Map<String, TransactionHandler> transactionMap = new HashMap<>();

    static {
        // Maps a transaction code to its corresponding handler.
        // Mapea un código de transacción con su manejador correspondiente.
        transactionMap.put(BusinessTransactions.AC11.getKey(), (transactionCode,session, output, input) -> openPanel(new InitDisplayAccountClasses(),transactionCode,session, output, input));
        transactionMap.put(BusinessTransactions.AC12.getKey(), (transactionCode,session, output, input) -> openPanel(new InitManagementAccountModels(),transactionCode,session, output, input));
        transactionMap.put(BusinessTransactions.AC13.getKey(), (transactionCode,session, output, input) -> openPanel(new InitManageAccountingSubclasses(),transactionCode,session, output, input));
        transactionMap.put(BusinessTransactions.AC15.getKey(), (transactionCode,session, output, input) -> openPanel(new InitCreateAccountingAccount(),transactionCode,session, output, input));
        transactionMap.put(BusinessTransactions.AC16.getKey(), (transactionCode,session, output, input) -> openPanel(new InitChangeAccountingAccount(),transactionCode,session, output, input));
        transactionMap.put(BusinessTransactions.AC17.getKey(), (transactionCode,session, output, input) -> openPanel(new InitViewAccountingAccount(),transactionCode,session, output, input));
        transactionMap.put(BusinessTransactions.AC18.getKey(), (transactionCode,session, output, input) -> openPanel(new InitViewListAccountingAccount(),transactionCode,session, output, input));
        transactionMap.put(BusinessTransactions.CO01.getKey(), (transactionCode,session, output, input) -> openPanel(new InitCreateChartsOfAccounts(),transactionCode,session, output, input));
        transactionMap.put(BusinessTransactions.CO02.getKey(), (transactionCode,session, output, input) -> openPanel(new InitModifyChartOfAccounts(),transactionCode,session, output, input));
        transactionMap.put(BusinessTransactions.CO03.getKey(), (transactionCode,session, output, input) -> openPanel(new InitViewChartOfAccounts(),transactionCode,session, output, input));
        transactionMap.put(BusinessTransactions.EN01.getKey(), (transactionCode,session, output, input) -> openPanel(new InitCreateFICompany(),transactionCode,session, output, input));
        transactionMap.put(BusinessTransactions.EN02.getKey(), (transactionCode,session, output, input) -> openPanel(new InitModifyFICompany(),transactionCode,session, output, input));
        transactionMap.put(BusinessTransactions.EN03.getKey(), (transactionCode,session, output, input) -> openPanel(new InitViewFICompany(),transactionCode,session, output, input));
        transactionMap.put(BusinessTransactions.CO04.getKey(), (transactionCode,session, output, input) -> openPanel(new InitFinancialAccountSetup(),transactionCode,session, output, input));
        // Add more transactions as needed.
        // Agrega más transacciones según sea necesario.
    }

    /**
     * Routes a transaction based on its code, initializing the corresponding handler.Enruta una transacción según su código, inicializando el manejador correspondiente.
     *
     * @param transactionCode The transaction identifier.
     *                         El identificador de la transacción.
     * @param lastPanel
     * @param session         The active user session.
     *                         La sesión activa del usuario.
     * @param output          The output stream to communicate with the server.
     *                         El flujo de salida para comunicarse con el servidor.
     * @param input           The input stream to receive data from the server.
     *                         El flujo de entrada para recibir datos del servidor.
     */
    public static void routeTransaction(String transactionCode,ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        // Retrieves the handler associated with the transaction code.
        // Obtiene el manejador asociado con el código de transacción.
        TransactionHandler handler = transactionMap.get(transactionCode);

        // If a handler exists for the transaction code, it is executed.
        // Si existe un manejador para el código de transacción, se ejecuta.
        if (handler != null) {
            handler.initialize(transactionCode,session, output, input);
        } else {
            // Logs a message if the transaction code is not found.
            // Registra un mensaje si el código de transacción no se encuentra.
            System.out.println("Transaction not found: " + transactionCode);
        }
    }

    /**
     * Opens the corresponding panel in the GUI and initializes it if necessary.
     * Abre el panel correspondiente en la interfaz gráfica y lo inicializa si es necesario.
     *
     * @param panel   The JPanel that will be displayed.
     *                 El JPanel que será mostrado.
     * @param session The active user session.
     *                 La sesión activa del usuario.
     * @param output  The output stream to send data to the server.
     *                 El flujo de salida para enviar datos al servidor.
     * @param input   The input stream to receive data from the server.
     *                 El flujo de entrada para recibir datos del servidor.
     */
    private static void openPanel(JPanel panel, String transactionCode, ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {

        // If the panel implements the TransactionPanel interface, initialize it with session and streams.
        // Si el panel implementa la interfaz TransactionPanel, lo inicializa con la sesión y los flujos.
        if (panel instanceof TransactionPanel transactionPanel) {
            transactionPanel.initialize(transactionCode, session, output, input);
        }
        // Loads the panel into the main container of the workspace.
        // Carga el panel en el contenedor principal del espacio de trabajo.

        PanelManager.goToPanel(panel);
    }
}
