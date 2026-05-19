

package com.simplecore.erp.shared.requests.types;

import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.requests.base.RequestType;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class COCostVariantByCodeRetrieveRequest extends BaseRequest{

    private final String variantCode;
    public COCostVariantByCodeRetrieveRequest(String sessionId, int userId, String variantCode) {
        super(sessionId, userId);
        this.variantCode = variantCode;
    }

    public String getVariantCode() {
        return variantCode;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.CO_COST_VARIANT_BY_CODE_RETRIEVE;
    }
}
