

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.CountryDTO;
import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;
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
 * Represents the response returned after retrieving a country by its code.
 * Extends {@link BaseResponse} and implements {@link Serializable} to allow transmission.
 */
public class CountryRetrieveResponse extends BaseResponse {
    /**
     * DTO containing the retrieved country data.
     */
    private final CountryDTO countryDto;

    /**
     * Constructs a new {@code CountryRetrieveResponse}.
     *
     * @param sessionId   the ID of the session that made the request
     * @param resultType  type of the result
     * @param countryDto  the {@link CountryDTO} containing the retrieved country data
     */
    public CountryRetrieveResponse(String sessionId,ResultType resultType ,CountryDTO countryDto) {
        super(sessionId, resultType);
        this.countryDto = countryDto;
    }

    /**
     * Returns the retrieved {@link CountryDTO}.
     *
     * @return the country data
     */
    public CountryDTO getCountryDto() {
        return countryDto;
    }

    /**
     * Returns the type of response.
     *
     * @return {@link ResponseType#COUNTRY_BY_CODE}
     */
    @Override
    public ResponseType getResponseType() {
        return ResponseType.COUNTRY_BY_CODE;
    }
}

