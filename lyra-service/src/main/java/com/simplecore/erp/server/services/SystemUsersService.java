package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.SystemUsersManager;
import com.simplecore.erp.shared.requests.types.SystemUsersListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.SystemUsersListRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class SystemUsersService {

    public static SystemUsersListRetrieveResponse getSystemUsersList(SystemUsersListRetrieveRequest request) {
        return SystemUsersManager.getSystemUsersList(request);
    }
}
