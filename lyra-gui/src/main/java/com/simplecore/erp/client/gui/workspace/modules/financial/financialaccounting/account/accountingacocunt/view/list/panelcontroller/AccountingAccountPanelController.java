
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.panelcontroller;

import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.gui.InitViewListAccountingAccount;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service.PastedListInterfaces;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service.ListButtonService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service.MatchCodeControllerService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service.QueryExecutorButtonHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service.QueryService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service.TextFieldFilterService;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

/**
 * Controlador principal para el panel de consulta de cuentas contables.
 * <p>
 * Este controlador inicializa todos los servicios necesarios para realizar búsquedas
 * avanzadas de cuentas contables, empaquetar los filtros definidos por el usuario y
 * enviar la solicitud al servidor mediante sockets.
 * </p>
 *
 * <p>
 * Este patrón de diseño puede ser replicado en cualquier otro módulo de consulta
 * en el sistema ERP. Simplemente debes adaptar los elementos visuales y los campos
 * utilizados en los filtros, manteniendo la misma estructura modular.
 * </p>
 */
public class AccountingAccountPanelController {

    private final InitViewListAccountingAccount panel;
    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final SystemMessages notificator;

    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final ActiveSession session;
    
    private String transactionCode;

        /**
     * Constructor del controlador.
     *
     * @param panel    Vista gráfica del panel de consulta.
     * @param session  Sesión activa del usuario (con permisos, idioma, etc.).
     * @param output   Stream de salida para enviar solicitudes al servidor.
     * @param input    Stream de entrada para recibir respuestas del servidor.
     * @param transactionCode
     */
    public AccountingAccountPanelController(InitViewListAccountingAccount panel,
                                            ActiveSession session,
                                            ObjectOutputStream output,
                                            ObjectInputStream input,String transactionCode) {
        this.panel = panel;
        this.session = session;
        this.output = output;
        this.input = input;
        this.transactionCode = transactionCode;
        // Traductores y notificadores obtenidos del panel.
        this.tableTranslator = panel.getTableTranslator();
        this.windowTranslator = panel.getWindowTranslator();
        this.notificator = panel.getNotificator();
    }
    
    /**
     * Inicializa el panel de consulta de cuentas contables.
     * <p>
     * Este método configura todos los servicios necesarios y los conecta entre sí
     * para habilitar la funcionalidad completa de filtrado, búsqueda y consulta
     * de información contable.
     * </p>
     * <p>
     * Estructura modular:
     * <ol>
     *     <li><b>PastedListInterfaces</b>: Interfaces de comunicación entre listas de usuario y campos de texto.</li>
     *     <li><b>TextFieldFilterService</b>: Detecta cambios en los campos de texto y mantiene sincronizadas las listas.</li>
     *     <li><b>ListButtonService</b>: Controladores de ventana emergente para mostrar listas de selección.</li>
     *     <li><b>MatchCodeControllerService</b>: Controla los matchcodes, permitiendo seleccionar datos desde tablas.</li>
     *     <li><b>QueryService</b>: Empaqueta todos los filtros definidos en una lista de objetos.</li>
     *     <li><b>QueryExecutorButtonHandler</b>: Ejecuta la consulta al servidor usando los filtros generados.</li>
     * </ol>
     * </p>
     */
    public void initializePanel() {
        // 1. Interfaces entre las listas de usuario y los campos de texto
        PastedListInterfaces interfaces = buildPastedInterfaces();

        // 2. Servicio para detectar cambios manuales y mantener la integridad de las listas
        TextFieldFilterService textFieldService = buildTextFieldService(interfaces);
        interfaces.setTextFieldService(textFieldService); // Comunicación bidireccional

        // 3. Servicio que gestiona los botones de apertura de ventanas de lista
        ListButtonService buttonService = buildButtonService(interfaces);

        // 4. Servicio de control de matchcodes (ventanas emergentes con datos)
        MatchCodeControllerService matchCodeService = buildMatchCodeService();

        // 5. Servicio que empaqueta todos los filtros definidos por el usuario
        QueryService queryService = buildQueryService(interfaces);

        // 6. Manejador del botón de consulta, que ejecuta la búsqueda con los filtros definidos
        QueryExecutorButtonHandler handler = new QueryExecutorButtonHandler(panel.getNextButton(), queryService);
        handler.initialize(transactionCode,session, output, input);

        // Se inyectan todos los servicios al panel para su uso conjunto
        panel.injectServices(interfaces, textFieldService, buttonService, queryService, handler);
    }

    /**
     * Crea y configura las interfaces que vinculan los botones de lista con las acciones del sistema.
     */

    private PastedListInterfaces buildPastedInterfaces() {
        return new PastedListInterfaces.Builder()
                .accountNumberFilterButton(panel.getAccountNumberFilterButton())
                .accountNameFilterButton(panel.getAccountNameFilterButton())
                .parentFilterButton(panel.getParentFilterButton())
                .subclassFilterButton(panel.getSubclassFilterButton())
                .modelFilterButton(panel.getModelFilterButton())
                .modelStateFilterButton(panel.getModelStateFilterButton())
                .createdByFilterButton(panel.getCreatedByFilterButton())
                .createdAtAccountFilterButton(panel.getCreatedAtAccountFilterButton())
                .updatedByAccountFilterButton(panel.getUpdatedByAccountFilterButton())
                .updatedAtAccountFilterButton(panel.getUpdatedAtAccountFilterButton())
                .build();
    }

    /**
     * Crea el servicio que gestiona los campos de texto y detecta cambios manuales para mantener consistencia.
     */
    private TextFieldFilterService buildTextFieldService(PastedListInterfaces interfaces) {
        return new TextFieldFilterService.Builder()
            .interfaceList(interfaces)
            .accountNumberFromMatchCode(panel.getAccountNumberFromMatchCode())
            .accountNumberToMatchCode(panel.getAccountNumberToMatchCode())
            .accountNameFrom(panel.getAccountNameFromTextField())
            .accountNameTo(panel.getAccountNameToTextField())
            .parentFromMatchCode(panel.getParentFromMatchCode())
            .parentToMatchCode(panel.getParentToMatchCode())
            .subclassFromMatchCode(panel.getSubclassFromMatchCode())
            .subclassToMatchCode(panel.getSubclassToMatchCode())
            .modelFromMatchCode(panel.getModelFromMatchCode())
            .modelToMatchCode(panel.getModelToMatchCode())
            .modelStateFromMatchCode(panel.getModelStateFromMatchCode())
            .modelStateToMatchCode(panel.getModelStateToMatchCode())
            .createdByFromMatchCode(panel.getCreatedByFromMatchCode())
            .createdAtFromChooser(panel.getCreatedAtFromDateChooser())
            .createdByToMatchCode(panel.getCreatedByToMatchCode())
            .createdAtToChooser(panel.getCreatedAtToDateChooser())
            .updatedByFromMatchCode(panel.getUpdatedByFromMatchCode())
            .updatedAtFromChooser(panel.getUpdatedAtFromDateChooser())
            .updatedByToMatchCode(panel.getUpdatedByToMatchCode())
            .updatedAtToChooser(panel.getUpdatedAtToDateChooser())
            .build();
    }

    /**
     * Crea el servicio para gestionar los botones de listas emergentes.
     */  
    private ListButtonService buildButtonService(PastedListInterfaces interfaces) {
        return new ListButtonService.Builder()
            .accountNumber(panel.getAccountNumberFilterButton(), panel.getAccountNumberLabel())
            .accountName(panel.getAccountNameFilterButton(), panel.getAccountNameLabel())
            .parent(panel.getParentFilterButton(), panel.getParentLabel())
            .subclass(panel.getSubclassFilterButton(), panel.getSubclassLabel())
            .model(panel.getModelFilterButton(), panel.getModelLabel())
            .modelState(panel.getModelStateFilterButton(), panel.getModelStateLabel())
            .createdBy(panel.getCreatedByFilterButton(), panel.getCreatedByLabel())
            .createdAt(panel.getCreatedAtAccountFilterButton(), panel.getCreatedAtLabel())
            .updatedBy(panel.getUpdatedByAccountFilterButton(), panel.getUpdatedByLabel())
            .updatedAt(panel.getUpdatedAtAccountFilterButton(), panel.getUpdatedAtLabel())
            .pastedListInterfaces(interfaces)
            .build();
    }

    
    /**
     * Crea el servicio de control para los componentes de matchcode.
     */
    private MatchCodeControllerService buildMatchCodeService() {
        return new MatchCodeControllerService.Builder()
            .activeSession(session)
            .output(output)
            .input(input)
            .accountNumberFromMatchCode(panel.getAccountNumberFromMatchCode())
            .accountNumberToMatchCode(panel.getAccountNumberToMatchCode())
            .parentFromMatchCode(panel.getParentFromMatchCode())
            .parentToMatchCode(panel.getParentToMatchCode())
            .subclassFromMatchCode(panel.getSubclassFromMatchCode())
            .subclassToMatchCode(panel.getSubclassToMatchCode())
            .modelFromMatchCode(panel.getModelFromMatchCode())
            .modelToMatchCode(panel.getModelToMatchCode())
            .modelStateFromMatchCode(panel.getModelStateFromMatchCode())
            .modelStateToMatchCode(panel.getModelStateToMatchCode())
            .createdByFromMatchCode(panel.getCreatedByFromMatchCode())
            .createdByToMatchCode(panel.getCreatedByToMatchCode())
            .updatedByFromMatchCode(panel.getUpdatedByFromMatchCode())
            .updatedByToMatchCode(panel.getUpdatedByToMatchCode())
            .statusAccountComboBox(panel.getStatusAccountComboBox())
            .build();
    }
    
    /**
     * Crea el servicio de consulta, que toma todos los datos de filtros del panel
     * y los empaqueta como una lista de parámetros de búsqueda.
     */
    private QueryService buildQueryService(PastedListInterfaces interfaces) {
        return new QueryService.Builder()
                .pastedListInterface(interfaces)
                .accountNumberFromMatchCode(panel.getAccountNumberFromMatchCode())
                .accountNumberToMatchCode(panel.getAccountNumberToMatchCode())
                .accountNameFromTextField(panel.getAccountNameFromTextField())
                .accountNameToTextField(panel.getAccountNameToTextField())
                .parentFromMatchCode(panel.getParentFromMatchCode())
                .parentToMatchCode(panel.getParentToMatchCode())
                .subclassFromMatchCode(panel.getSubclassFromMatchCode())
                .subclassToMatchCode(panel.getSubclassToMatchCode())
                .modelFromMatchCode(panel.getModelFromMatchCode())
                .modelToMatchCode(panel.getModelToMatchCode())
                .modelStateFromMatchCode(panel.getModelStateFromMatchCode())
                .modelStateToMatchCode(panel.getModelStateToMatchCode())
                .isModelActive(panel.getModelStatusCheckBox())
                .isAccountClosed(panel.getAccountIsClosedCheckBox())
                .statusAccountComboBox(panel.getStatusAccountComboBox())
                .createdByFromMatchCode(panel.getCreatedByFromMatchCode())
                .createdByToMatchCode(panel.getCreatedByToMatchCode())
                .createdAtFromDateChooser(panel.getCreatedAtFromDateChooser())
                .createdAtToDateChooser(panel.getCreatedAtToDateChooser())
                .updatedByFromMatchCode(panel.getUpdatedByFromMatchCode())
                .updatedByToMatchCode(panel.getUpdatedByToMatchCode())
                .updatedAtFromDateChooser(panel.getUpdatedAtFromDateChooser())
                .updatedAtToDateChooser(panel.getUpdatedAtToDateChooser())
                .build();
    }

}
