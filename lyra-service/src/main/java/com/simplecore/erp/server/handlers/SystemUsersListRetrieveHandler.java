

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.SystemUsersService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.SystemUsersListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class SystemUsersListRetrieveHandler implements RequestHandler<SystemUsersListRetrieveRequest> {

    @Override
    public Object handle(SystemUsersListRetrieveRequest request) {
        return SystemUsersService.getSystemUsersList(request);
    }

}
