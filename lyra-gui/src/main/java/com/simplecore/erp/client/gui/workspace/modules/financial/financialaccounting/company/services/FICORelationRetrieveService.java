

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services;

import com.simplecore.erp.client.abstractions.AbstractDataSourceService;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.FICOAssociationsDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.FICORelationByFICompanyRetrieveRequest;
import com.simplecore.erp.shared.responses.types.FICORelationByFICompanyRetrieveResponse;
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
public class FICORelationRetrieveService extends AbstractDataSourceService {

    public FICORelationRetrieveService(
            ActiveSession session,
            ObjectOutputStream output,
            ObjectInputStream input) {
        super(session, output, input);
    }

    public FICOAssociationsDTO getFICOAssociationDTO(long companyID) {
        var request = new FICORelationByFICompanyRetrieveRequest(sessionID, userID, companyID);
        Object response = serverController.sendData(request);
        if (response instanceof FICORelationByFICompanyRetrieveResponse relation) {
            if (relation.isSqlError()) {
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
            } else if (relation.wasFound()) {
                return relation.getAssociationsDTO();
            } else {
                notificator.showInfoMsg(AppMessages.msg(AppMessages.Key.NO_FICO_RELATION_FOUND));
            }
        }
        
        return null;
    }
}
