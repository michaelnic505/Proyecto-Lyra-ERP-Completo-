
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.persistence;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller.ChartOfAccountFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller.ChartOfAccountMapper;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.ChartOfAccountDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.ChartOfAccountChangeRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountCreateRequest;
import com.simplecore.erp.shared.responses.types.ChartOfAccountChangeResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountCreateResponse;
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
public class ChartOfAccountPersistenceService {

    private final ServerController serverController;
    private final SystemMessages notificator;
    private ChartOfAccountFormState formState;
    private int modelID;
    private String transactionCode;
    private String createdBy;
    private String updatedBy;

    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final ActiveSession activeSession;
    private final OperationType operationType;

    private ChartOfAccountPersistenceService(Builder builder) {
        this.formState = builder.formState;
        this.modelID = builder.modelID;
        this.transactionCode = builder.transactionCode;
        this.createdBy = builder.createdBy;
        this.updatedBy = builder.updatedBy;
        this.output = builder.output;
        this.input = builder.input;
        this.activeSession = builder.session;
        this.operationType = builder.operationType;
        this.serverController = new ServerController(output, input); 
        this.notificator = new SystemMessages();
        turnOffState(operationType);
    }
    
    private void turnOffState(OperationType operationType) {
        if (operationType == OperationType.VIEW) {
            formState.setSaved(true);
        }
    }

    // Métodos de servicio...
    public static class Builder {

        private ChartOfAccountFormState formState;
        private int modelID;
        private String transactionCode;
        private String createdBy;
        private String updatedBy;
        private ObjectOutputStream output;
        private ObjectInputStream input;
        private ActiveSession session;
        private OperationType operationType;


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

        // Métodos para configurar los campos del Builder
        public Builder withFormState(ChartOfAccountFormState formState) {
            this.formState = formState;
            return this;
        }

        public Builder withModelID(int modelID) {
            this.modelID = modelID;
            return this;
        }

        public Builder withTransactionCode(String transactionCode) {
            this.transactionCode = transactionCode;
            return this;
        }

        public Builder withCreatedBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }
        
        public Builder withUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }
        
        public Builder withOperationType(OperationType operationType) {
            this.operationType = operationType;
            return this;
        }

        // Método para construir la instancia de ChartOfAccountPersistenceService
        public ChartOfAccountPersistenceService build() {
            // Validación o procesamiento adicional si es necesario
            return new ChartOfAccountPersistenceService(this);
        }
    }

    // Método de servicio para persistir los datos (envía el DTO al servidor)
    public void persistData() {
        persistRequestByOperationType();
    }
    
    private void persistRequestByOperationType() {
        if(operationType==null){
            return;
        }
        switch (operationType) {
            case CREATE -> {
                executeInsert(createRequest());
            }
            case MODIFY -> {
                executeUpdate(changeRequest());
            }
        }
    }

    private void executeInsert(ChartOfAccountCreateRequest request) {
        Object response = serverController.sendData(request);
        if (response != null && response instanceof ChartOfAccountCreateResponse createResponse) {
            if(createResponse.isSqlError()){
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                return;
            }
            if (createResponse.wasCreated()) {
                // Mostrar mensaje de éxito y navegar hacia atrás

                notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.CREATED)
                        + " " + formState.getChartOfAccountCode());
                formState.setSaved(true);
                PanelManager.goBack();
            }else{
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_CREATED));
            }
        }
    }

    private void executeUpdate(ChartOfAccountChangeRequest request) {
        Object response = serverController.sendData(request);
        if (response != null && response instanceof ChartOfAccountChangeResponse changeResponse) {
            if(changeResponse.isSqlError()){
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                return;
            }
            
            if (changeResponse.wasUpdated()) {
                notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.UPDATED)
                        + " " + formState.getChartOfAccountCode());
                formState.setSaved(true);
                PanelManager.goBack();
            }else{
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_UPDATED));
            }
        }
    }

    private ChartOfAccountCreateRequest createRequest() {
        String session = activeSession.getSessionId();
        int userId = activeSession.getUserId();
        // Mapeo del estado del formulario a un DTO
        ChartOfAccountDTO dtoCreate = ChartOfAccountMapper.mapToDTOToCreate(formState, transactionCode, createdBy, modelID);
        // Crear la solicitud con la información de la sesión necesaria
        return new ChartOfAccountCreateRequest(session, userId, dtoCreate);
    }

    private ChartOfAccountChangeRequest changeRequest() {
        String session = activeSession.getSessionId();
        int userId = activeSession.getUserId();
        // Mapeo del estado del formulario a un DTO
        ChartOfAccountDTO dtoChange = ChartOfAccountMapper.mapToDTOToChange(formState, transactionCode, updatedBy);
        // Crear la solicitud con la información de la sesión necesaria
        return new ChartOfAccountChangeRequest(session, userId, dtoChange);
    }
}
