

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.FICompanyService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.FICompanyModifyRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyModifyHandler implements RequestHandler<FICompanyModifyRequest>{

    @Override
    public Object handle(FICompanyModifyRequest request) {
        return FICompanyService.modifyFICompany(request);
    }

}
