

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services;

import com.simplecore.erp.client.abstractions.AbstractDataSourceService;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.FICompanyDocument;
import com.simplecore.erp.shared.models.dto.FIDocumentsDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.FICompanyDocumentsRetrieveRequest;
import com.simplecore.erp.shared.responses.types.FICompanyDocumentsRetrieveResponse;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyDocumentsRetrieveService extends AbstractDataSourceService {

    public FICompanyDocumentsRetrieveService(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        super(session, output, input);
    }

    private FIDocumentsDTO getFICompanyDocuments(long companyID) {
       
        var request = new FICompanyDocumentsRetrieveRequest(sessionID, userID, companyID);
        Object response = serverController.sendData(request);
        
        if (response instanceof FICompanyDocumentsRetrieveResponse documents) {
            if (documents.isSqlError()) {
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
            } else if (documents.wasFound()) {
                return documents.getDocumentsDTO();
            } else {
                notificator.showInfoMsg(AppMessages.msg(AppMessages.Key.NO_DOCUMENTS_TO_PRINT));
            }
        }
        return null;
    }
    
    public FIDocumentsDTO getFIDocumentsList(long companyID){
        return getFICompanyDocuments(companyID);
    }
}
