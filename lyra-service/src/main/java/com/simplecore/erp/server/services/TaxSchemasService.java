

package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.TaxSchemasManager;
import com.simplecore.erp.shared.requests.types.TaxSchemaRetrieveRequest;
import com.simplecore.erp.shared.requests.types.TaxSchemasListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.TaxSchemaRetrieveResponse;
import com.simplecore.erp.shared.responses.types.TaxSchemasListRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TaxSchemasService {

    public static TaxSchemasListRetrieveResponse getTaxSchemasList(TaxSchemasListRetrieveRequest request){
        return TaxSchemasManager.getTaxSchemasList(request);
    }
    
    public static TaxSchemaRetrieveResponse getTaxSchema(TaxSchemaRetrieveRequest request){
        return TaxSchemasManager.getTaxSchema(request);
    }
}
