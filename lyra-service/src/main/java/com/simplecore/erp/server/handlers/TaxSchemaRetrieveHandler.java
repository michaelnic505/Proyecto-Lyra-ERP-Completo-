

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.TaxSchemasService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.TaxSchemaRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TaxSchemaRetrieveHandler implements RequestHandler<TaxSchemaRetrieveRequest>{

    @Override
    public Object handle(TaxSchemaRetrieveRequest request) {
        return TaxSchemasService.getTaxSchema(request);
    }

}
