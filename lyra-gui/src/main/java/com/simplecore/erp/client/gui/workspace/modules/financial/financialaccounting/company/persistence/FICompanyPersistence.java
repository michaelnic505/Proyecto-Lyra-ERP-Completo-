
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.persistence;

import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.model.FICompanyMapper;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util.FICompanyDocumentsInjector;
import com.simplecore.erp.client.services.base.AbstractPersistence;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.FICOAssociationsDTO;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;
import com.simplecore.erp.shared.models.dto.FIDocumentsDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.FICompanyCreateRequest;
import com.simplecore.erp.shared.requests.types.FICompanyExistenceCheckRequest;
import com.simplecore.erp.shared.requests.types.FICompanyModifyRequest;
import com.simplecore.erp.shared.responses.types.FICompanyCreateResponse;
import com.simplecore.erp.shared.responses.types.FICompanyExistenceCheckResponse;
import com.simplecore.erp.shared.responses.types.FICompanyModifyResponse;
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
public class FICompanyPersistence extends AbstractPersistence<
        FICompanyFormState,
        FICompanyCreateRequest,
        FICompanyModifyRequest>{

    private FICompanyDocumentsInjector documentsInjector;
    
    public FICompanyPersistence(FICompanyFormState formState, 
            ObjectOutputStream output, 
            ObjectInputStream input, 
            ActiveSession activeSession, 
            OperationType operationType, 
            String transactionCode) {
        super(formState, output, input, activeSession, operationType, transactionCode);
    }
    
    public void setDocumentInjector(FICompanyDocumentsInjector documentsInjector){
        this.documentsInjector = documentsInjector;
    }
    
    @Override
    protected FICompanyCreateRequest buildCreateRequest() {
        
        FICompanyDTO companyDTO = FICompanyMapper.mapToDTOToCreate(formState, username);
        FIDocumentsDTO documentsDTO = FICompanyMapper.mapToDTOToCreateDocs(formState);
        FICOAssociationsDTO associationDTO = FICompanyMapper.mapToFICOAssociationDTOToCreate(formState);

        return new FICompanyCreateRequest(sessionID, userID, companyDTO, documentsDTO, associationDTO);
    }

    @Override
    protected void executeInsert(FICompanyCreateRequest request) {
        String companyCode = formState.getCompanyCode();
        var companyCodeExists = new FICompanyExistenceCheckRequest(sessionID, userID, companyCode);
        Object exitsResponse = serverController.sendData(companyCodeExists);
        if(exitsResponse instanceof FICompanyExistenceCheckResponse existsResponse){
            if(existsResponse.wasFound()){
                
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.ERROR_DUPLICATE_CODE)+" :"+companyCode);
                return;
            }
        }
        Object response = serverController.sendData(request);
        if (response != null && response instanceof FICompanyCreateResponse createResponse) {
            if (createResponse.isSqlError()) {
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                return;
            }
            if (createResponse.wasCreated()) {
                // Mostrar mensaje de éxito y navegar hacia atrás
                notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.CREATED) + " "
                        + companyCode);
                formState.setSaved(true);
                PanelManager.goBack();
            } else {
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_CREATED));
            }
        }
    }
    
    @Override
    protected FICompanyModifyRequest buildModifyRequest() {
        FICompanyDTO companyDTO = FICompanyMapper.mapToDTOToModify(formState, username);
        FIDocumentsDTO documentsDTO = FICompanyMapper.mapToDTOToModifyDocs(formState, documentsInjector.getOriginalDocumentsData());
        FICOAssociationsDTO associationDTO = FICompanyMapper.mapToFICOAssociationDTOToModify(formState);
        
        return new FICompanyModifyRequest(sessionID, userID, companyDTO, documentsDTO, associationDTO);
    }

    @Override
    protected void executeChange(FICompanyModifyRequest request) {
        
        String companyCode = formState.getCompanyCode();
        Object response = serverController.sendData(request);
        
        if (response != null && response instanceof FICompanyModifyResponse modifyResponse) {
            if (modifyResponse.isSqlError()) {
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
            }else if(modifyResponse.wasUpdated()){
                notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.UPDATED)+ " "
                        + companyCode);
                formState.setSaved(true);
                PanelManager.goBack();
            }else{
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_UPDATED));
            }
        }
    }
}
