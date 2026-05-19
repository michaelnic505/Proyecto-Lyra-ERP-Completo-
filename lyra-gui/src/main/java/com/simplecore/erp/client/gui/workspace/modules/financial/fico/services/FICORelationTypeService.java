
package com.simplecore.erp.client.gui.workspace.modules.financial.fico.services;

import com.simplecore.erp.client.services.base.AbstractComboService;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.FICORelationTypesRetriveRequest;
import com.simplecore.erp.shared.responses.types.FICORelationTypesRetriveResponse;
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
public class FICORelationTypeService extends AbstractComboService {

    public FICORelationTypeService(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        super(session, output, input);
    }

    @Override
    public String[] getDataRequested() {
        var request = new FICORelationTypesRetriveRequest(sessionID, userID);
        Object response = serverController.sendData(request);
        if (response instanceof FICORelationTypesRetriveResponse types) {
            if (types.isSqlError()) {
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
            } else if (types.wasFound()) {
                return types.getRelationTypes();
            } else {
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
            }
        }
        return new String[0];
    }

}
