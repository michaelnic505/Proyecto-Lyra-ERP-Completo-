

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.TaxSchemaDTO;
import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;
/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TaxSchemaRetrieveResponse extends BaseResponse{

    private final TaxSchemaDTO taxSchema;
    
    public TaxSchemaRetrieveResponse(String sessionId,ResultType resultType,TaxSchemaDTO taxSchema) {
        super(sessionId, resultType);
        this.taxSchema = taxSchema;
    }

    public TaxSchemaDTO getTaxSchema() {
        return taxSchema;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_TAX_SCHEMA;
    }
}
