

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.CurrencyDTO;
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
public class CurrencyRetrieveResponse extends BaseResponse{

    private CurrencyDTO currencyDto;
    
    public CurrencyRetrieveResponse(String sessionId,ResultType resultType,CurrencyDTO currencyDto) {
        super(sessionId,  resultType);
        this.currencyDto = currencyDto;
    }

    public CurrencyDTO getCurrencyDto() {
        return currencyDto;
    }
    

    @Override
    public ResponseType getResponseType() {
        return ResponseType.CURRENCY_BY_CODE;
    }

}
