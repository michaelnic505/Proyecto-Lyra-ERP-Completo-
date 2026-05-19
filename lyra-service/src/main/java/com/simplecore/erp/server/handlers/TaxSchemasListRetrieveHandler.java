
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.TaxSchemasService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.TaxSchemasListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TaxSchemasListRetrieveHandler implements RequestHandler<TaxSchemasListRetrieveRequest>{

    @Override
    public Object handle(TaxSchemasListRetrieveRequest request) {
        return TaxSchemasService.getTaxSchemasList(request);
    }

}
