

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.gui.ViewAccountingAccountList;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.queries.QueryFilters;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AcountingAccountFilterQueryRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AcountingAccountFilterQueryRetrieveResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

/**
 * La clase QueryExecutorButtonHandler gestiona el comportamiento de un botón de ejecución de consultas en una interfaz gráfica.
 * Se encarga de enviar una solicitud de consulta al servidor y manejar la respuesta.
 */
public class QueryExecutorButtonHandler {


    // Botón de ejecución de la consulta
    private final JButton executeButton;
    // Servicio que proporciona las consultas a ejecutar
    private final QueryService queryService;
    // Sesión activa del usuario
    private ActiveSession activeSession;
    // Controlador para interactuar con el servidor
    private ServerController serverController;
    // Notificador para mostrar mensajes del sistema
    private final SystemMessages notificator;
    
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    private String transactionCode;
        /**
     * Constructor de la clase QueryExecutorButtonHandler.
     * 
     * @param executeButton El botón que ejecutará la consulta.
     * @param queryService El servicio que proporciona las consultas a ejecutar.
     */
    public QueryExecutorButtonHandler(JButton executeButton,QueryService queryService) {
        this.executeButton = executeButton;
        this.queryService = queryService;
        this.notificator = new SystemMessages();
        setExecuteButtonEvents();
    }

    
    /**
     * Inicializa el manejador con la sesión activa y los flujos de entrada/salida para la comunicación con el servidor.
     * 
     * @param activeSession La sesión activa del usuario.
     * @param output El flujo de salida para enviar solicitudes al servidor.
     * @param input El flujo de entrada para recibir respuestas del servidor.
     */
    public void initialize(String transactionCode,ActiveSession activeSession,ObjectOutputStream output,ObjectInputStream input){
        this.transactionCode = transactionCode;
        this.activeSession = activeSession;
        this.serverController = new ServerController(output, input);
        this.output = output;
        this.input = input;
    }
    
    
    /**
     * Configura los eventos para el botón de ejecución de la consulta.
     * Cuando el botón es presionado, se ejecuta el método sendQueryRequest().
     */
    private void setExecuteButtonEvents(){
        executeButton.addActionListener(e->{
            sendQueryRequest();
        });
    }
    
    
    /**
     * Envía una solicitud de consulta al servidor utilizando los filtros configurados en el servicio de consultas.
     * Obtiene la respuesta y maneja el resultado.
     */
    private void sendQueryRequest() {
        // Obtención de los parámetros necesarios para la consulta
        String sessionId = activeSession.getSessionId();
        int userId = activeSession.getUserId();
        List<QueryFilters> filtersList = queryService.getListQueries();
        
        try {
            // Crear la solicitud de consulta con los filtros proporcionados
            var accountingAccountFilteredQuery = new AcountingAccountFilterQueryRetrieveRequest(sessionId, userId, filtersList);

            // Enviar la solicitud al servidor y obtener la respuesta
            Object response = serverController.sendRequest(accountingAccountFilteredQuery);

            // Si la respuesta es una lista de cuentas filtradas, manejarla
            if (response instanceof AcountingAccountFilterQueryRetrieveResponse listResponse) {
                // Si no se encontraron resultados, mostrar un mensaje de advertencia
                if (listResponse.isSqlError()) {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                    return;
                }

                if (listResponse.wasFound()) {
                    goToNextPanel(listResponse.getAccountFilteredList());
                } else {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                }
            }

        } catch (IOException | ClassNotFoundException ex) {
            // Manejo de excepciones en caso de error de comunicación con el servidor
            Logger.getLogger(QueryExecutorButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goToNextPanel(Object[][] results) {
        var viewAccountList = new ViewAccountingAccountList(results);
        viewAccountList.initialize(transactionCode,activeSession, output, input);
        PanelManager.goToPanel(viewAccountList);
    }

}
