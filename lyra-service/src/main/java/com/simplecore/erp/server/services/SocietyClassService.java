

package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.SocietyClassManager;
import com.simplecore.erp.shared.requests.types.SocietyClassListRequest;
import com.simplecore.erp.shared.requests.types.SocietyClassRequest;
import com.simplecore.erp.shared.responses.types.SocietyClassResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class SocietyClassService {

    public static SocietyClassResponse getSocietyClass(SocietyClassRequest request) {
        return SocietyClassManager.getSocietyClass(request);
    }

    public static Object getSocietyClassList(SocietyClassListRequest request) {
        return SocietyClassManager.getSocietyClassList(request);
    }
}
