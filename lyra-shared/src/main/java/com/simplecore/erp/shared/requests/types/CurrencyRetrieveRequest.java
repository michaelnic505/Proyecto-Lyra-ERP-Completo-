

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
/**
 * Request class used to retrieve currency details by its code.
 * Inherits from {@link BaseRequest} and includes the currency code to be looked up.
 */
public class CurrencyRetrieveRequest extends BaseRequest {
    /**
     * The ISO 4217 currency code to be retrieved (e.g., "USD", "EUR", "JPY").
     */
    private String currencyCode;

    /**
     * Constructs a new {@code CurrencyRetrieveRequest}.
     *
     * @param sessionId    the session identifier of the user making the request
     * @param userId       the ID of the user making the request
     * @param currencyCode the ISO 4217 currency code to retrieve
     */
    public CurrencyRetrieveRequest(String sessionId, int userId, String currencyCode) {
        super(sessionId, userId);
        this.currencyCode = currencyCode;
    }

    /**
     * Returns the currency code specified in the request.
     *
     * @return the ISO 4217 currency code
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Returns the type of the request.
     *
     * @return {@link RequestType#CURRENCY_BY_CODE} indicating a currency lookup by code
     */
    @Override
    public RequestType getRequestType() {
        return RequestType.CURRENCY_BY_CODE;
    }
}
