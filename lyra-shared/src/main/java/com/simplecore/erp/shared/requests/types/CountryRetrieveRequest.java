
package com.simplecore.erp.shared.requests.types;

import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.requests.base.RequestType;
import java.io.Serializable;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

/**
 * Request class used to retrieve a country by a specific code.
 * Inherits from {@link BaseRequest} and implements {@link Serializable} to support transmission.
 */
public class CountryRetrieveRequest extends BaseRequest {
    /**
     * The code of the country to be retrieved.
     * This can be an ISO alpha-2 or alpha-3 code depending on system implementation.
     */
    private String countryCode;

    /**
     * Constructs a new {@code CountryRetrieveRequest}.
     *
     * @param sessionId   the session identifier of the user making the request
     * @param userId      the ID of the user making the request
     * @param countryCode the code of the country to retrieve (e.g., "US", "ARG", "DE")
     */
    public CountryRetrieveRequest(String sessionId, int userId, String countryCode) {
        super(sessionId, userId);
        this.countryCode = countryCode;
    }

    /**
     * Returns the country code specified in the request.
     *
     * @return the country code (ISO alpha-2 or alpha-3)
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Returns the type of request.
     *
     * @return {@link RequestType#COUNTRY_BY_CODE} indicating a country lookup by code
     */
    @Override
    public RequestType getRequestType() {
        return RequestType.COUNTRY_BY_CODE;
    }
}
