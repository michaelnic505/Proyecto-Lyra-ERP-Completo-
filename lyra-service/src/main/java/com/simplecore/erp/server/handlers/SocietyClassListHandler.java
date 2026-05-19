

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.SocietyClassService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.SocietyClassListRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class SocietyClassListHandler  implements RequestHandler<SocietyClassListRequest> {

    @Override
    public Object handle(SocietyClassListRequest request) {
        return SocietyClassService.getSocietyClassList(request);
    }

}
