
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.init.InitCreateChartsOfAccounts;
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
 * Controlador encargado de inicializar el panel de creación de cuentas contables
 * y configurar los diferentes componentes, como el filtro de texto y el controlador
 * del botón de proceder.
 */
public class InitCreateChartOfAccountsController {

    private final InitCreateChartsOfAccounts panel;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final ActiveSession session;
    private String transactionCode;
    private OperationType operationType;

    /**
     * Constructor para inicializar el controlador con los parámetros necesarios.
     * 
     * @param panel El panel de la interfaz gráfica donde se mostrarán los elementos.
     * @param output Flujo de salida para la comunicación con el servidor.
     * @param input Flujo de entrada para recibir datos del servidor.
     * @param session La sesión activa del usuario.
     * @param transactionCode El código de la transacción que se está ejecutando.
     * @param operationType
     */
    public InitCreateChartOfAccountsController(InitCreateChartsOfAccounts panel,
            ObjectOutputStream output,
            ObjectInputStream input, 
            ActiveSession session, 
            String transactionCode,
            OperationType operationType) {
        this.panel = panel;
        this.output = output;
        this.input = input;
        this.session = session;
        this.transactionCode = transactionCode;
        this.operationType = operationType;
    }
    
    /**
     * Inicializa el panel configurando todos los componentes necesarios,
     * como el controlador del botón de proceder y el servicio de filtros de texto.
     */
    public void initializePanel() {
        InitCreateProceedButtonController proceedButtonController = buildProceedButtonController();
        TextFieldFilterService textFieldFilterService = buildTextFieldFilterService();
    }
    
    /**
     * Crea e inicializa el controlador del botón de proceder, que se encarga
     * de manejar las acciones cuando el usuario hace clic en el botón.
     * 
     * @return Un controlador de botones configurado con los parámetros necesarios.
     */
    private InitCreateProceedButtonController buildProceedButtonController(){
        return new InitCreateProceedButtonController.Builder()
                .withOperationType(operationType)
                .withOutput(output)
                .withInput(input)
                .withTransactionCode(transactionCode)
                .withSession(session)
                .withButton(panel.getProceedButton())
                .withAccountModelMatchCode(panel.getAccountModelMatchCode())
                .withAccountModelDescriptionLabel(panel.getAccountModelDescriptionLb())
                .withChartAccountCodeTextField(panel.getChartAccountCodeTextField())
                .withChartAccountNameTextField(panel.getChartAccountNameTextField())
                .build();
    }
    
    /**
     * Crea el servicio que se encarga de asignar los filtros de texto a los campos.
     * 
     * @return El servicio de filtros de texto configurado con los campos correspondientes.
     */
    private TextFieldFilterService buildTextFieldFilterService(){
        return new TextFieldFilterService.Builder()
                .accountModelMatchCode(panel.getAccountModelMatchCode())
                .chartAccountCodeTextField(panel.getChartAccountCodeTextField())
                .chartAccountNameTextField(panel.getChartAccountNameTextField())
                .build();
    }
}
