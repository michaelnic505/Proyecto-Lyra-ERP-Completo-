
package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.CountriesInfo;
import com.simplecore.erp.server.config.database.tablecolumns.Currencies;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.models.dto.CountryDTO;
import com.simplecore.erp.shared.models.dto.CurrencyDTO;
import com.simplecore.erp.shared.requests.types.CountriesListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.CountryRetrieveRequest;
import com.simplecore.erp.shared.requests.types.CurrenciesListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.CurrencyRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.CountriesListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.CountryRetrieveResponse;
import com.simplecore.erp.shared.responses.types.CurrenciesListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.CurrencyRetrieveResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
/**
 * The CountryInfoManager class handles the logic for retrieving country information 
 * from the database. This includes querying country details such as country codes, 
 * country names, currency codes, and currency names.
 * <p>
 * It utilizes JDBC for executing the SQL query, retrieving the results, and returning 
 * the information encapsulated in a response object.
 */
public class CountryInfoManager {
    
    private static final Logger logger = LoggerFactory.getLogger(CountryInfoManager.class);
    
    /**
     * Retrieves a list of country information based on the request provided.
     * <p>
     * This method builds and executes an SQL query to fetch details of countries 
     * including their alpha-2 and alpha-3 codes, country name, currency code, and 
     * currency name. The results are stored in a list of Object arrays and returned 
     * as a {@link CountriesListRetrieveResponse}.
     * 
     * @param request The {@link CountriesListRetrieveRequest} containing information 
     *                necessary to retrieve the country list.
     * @return A {@link CountriesListRetrieveResponse} containing the status of the 
     *         operation and the list of country data, or an empty list in case of failure.
     *         If successful, the {@link CountriesListRetrieveResponse} will have the 
     *         dataSource populated with the country details.
     */
    public static CountriesListRetrieveResponse getCountriesList(CountriesListRetrieveRequest request) {
        List<Object[]> dataSource = new ArrayList<>();  // Stores the results from the SQL query
        
        // Construct the SQL query to retrieve country details
        String query = Q.select(DatabaseTables.COUNTRIES.tableName(),
                CountriesInfo.ID.getColumnName(),
                CountriesInfo.ALPHA_2.getColumnName(),
                CountriesInfo.ALPHA_3.getColumnName(),
                CountriesInfo.COUNTRY_NAME.getColumnName(),
                CountriesInfo.CURRENCY_CODE.getColumnName(),
                CountriesInfo.CURRENCY_NAME.getColumnName());
        
        // Try-with-resources block to automatically manage resources (Connection, PreparedStatement, ResultSet)
        try (Connection conn = PooledConnectionService.getConnection(); 
             PreparedStatement st = conn.prepareStatement(query)) {
            
            // Execute the query and process the result set
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    // Add the retrieved data to the dataSource list
                    dataSource.add(new Object[]{
                        rs.getObject(CountriesInfo.ID.getColumnName()),
                        rs.getObject(CountriesInfo.ALPHA_2.getColumnName()),
                        rs.getObject(CountriesInfo.ALPHA_3.getColumnName()),
                        rs.getObject(CountriesInfo.COUNTRY_NAME.getColumnName()),
                        rs.getObject(CountriesInfo.CURRENCY_CODE.getColumnName()),
                        rs.getObject(CountriesInfo.CURRENCY_NAME.getColumnName())
                    });
                }

                if (!dataSource.isEmpty()) {
                    return new CountriesListRetrieveResponse(request.getSessionId(), ResultType.FOUND, dataSource);
                } else {
                    return new CountriesListRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, dataSource);
                }
            }

        } catch (SQLException ex) {
            // Log any SQL errors that occur during the database interaction
            logger.error("Error retrieving countries list from SQL", ex);
        }
        // Return a response indicating failure if an exception occurs
        return new CountriesListRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, dataSource);
    }

    /**
    * Retrieves a list of currencies from the database.
    * This method executes a SQL query to fetch details such as currency code, 
    * currency name, and currency symbol for all countries, storing the results
    * in a list of Object arrays.
    * 
    * @param request The request object containing the session ID for tracking the request.
    * @return A {@link CurrenciesListRetrieveResponse} containing the list of currency data 
    *         if the operation is successful, or a response indicating failure if an error occurs.
    */
    public static CurrenciesListRetrieveResponse getCurrenciesInfo(CurrenciesListRetrieveRequest request) {
        List<Object[]> dataSource = new ArrayList<>();  // Stores the results from the SQL query

        String query = Q.select(DatabaseTables.CURRENCIES.tableName(),
                Currencies.CURRENCY_CODE.getColumnName(),
                Currencies.CURRENCY_NAME.getColumnName(),
                Currencies.SYMBOL.getColumnName(),
                Currencies.SYMBOL_NAME.getColumnName());

        // Try-with-resources block to automatically manage resources (Connection, PreparedStatement, ResultSet)
        try (Connection conn = PooledConnectionService.getConnection(); 
                PreparedStatement st = conn.prepareStatement(query)) {

            // Execute the query and process the result set
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    // Add the retrieved data to the dataSource list
                    dataSource.add(new Object[]{
                        rs.getObject(Currencies.CURRENCY_CODE.getColumnName()),
                        rs.getObject(Currencies.CURRENCY_NAME.getColumnName()),
                        rs.getObject(Currencies.SYMBOL.getColumnName()),
                        rs.getObject(Currencies.SYMBOL_NAME.getColumnName()),
                    });
                }

                if(!dataSource.isEmpty()){
                    return new CurrenciesListRetrieveResponse(request.getSessionId(),ResultType.FOUND ,dataSource);
                }else{
                    return new CurrenciesListRetrieveResponse(request.getSessionId(),ResultType.NOT_FOUND ,dataSource);
                }
            }

        } catch (SQLException ex) {
            // Log any SQL errors that occur during the database interaction
            logger.error("Error retrieving currencies list from SQL", ex);
        }

        // Return a response indicating failure if an exception occurs
        return new CurrenciesListRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR ,dataSource);
    }

    /**
    * Retrieves information about a country based on the provided country code.
    * This method executes a SQL query to fetch details like country name, currency code, 
    * and currency symbol from the database, using the provided country code from the request.
    *
    * @param request The request containing the session ID and the country code for which the information is to be retrieved.
    * @return A {@link CountryRetrieveResponse} object containing the country details if found, 
    *         or a response indicating failure if no country is found or an error occurs.
    */
    public static CountryRetrieveResponse getCountryInfo(CountryRetrieveRequest request) {

        String query = Q.select(DatabaseTables.COUNTRIES.tableName(),
                CountriesInfo.ID.getColumnName(),
                CountriesInfo.ALPHA_2.getColumnName(),
                CountriesInfo.ALPHA_3.getColumnName(),
                CountriesInfo.COUNTRY_NAME.getColumnName(),
                CountriesInfo.CURRENCY_CODE.getColumnName(),
                CountriesInfo.CURRENCY_NAME.getColumnName(),
                CountriesInfo.CURRENCY_SYMBOL.getColumnName(),
                CountriesInfo.CURRENCY_SYMBOL_NAME.getColumnName())
                .concat(Q.where(CountriesInfo.ALPHA_2.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection(); 
                PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, request.getCountryCode());
            
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    
                    var countryDto = new CountryDTO.Builder()
                            .withID(rs.getInt(CountriesInfo.ID.getColumnName()))
                            .withAlpha2(rs.getString(CountriesInfo.ALPHA_2.getColumnName()))
                            .withAlpha3(rs.getString(CountriesInfo.ALPHA_3.getColumnName()))
                            .withCountryName(rs.getString(CountriesInfo.COUNTRY_NAME.getColumnName()))
                            .withCurrencyCode(rs.getString(CountriesInfo.CURRENCY_CODE.getColumnName()))
                            .withCurrencyName(rs.getString(CountriesInfo.CURRENCY_NAME.getColumnName()))
                            .withSymbol(rs.getString(CountriesInfo.CURRENCY_SYMBOL.getColumnName()))
                            .withSymbolName(rs.getString(CountriesInfo.CURRENCY_SYMBOL_NAME.getColumnName()))
                            .build();

                    return new CountryRetrieveResponse(request.getSessionId(), ResultType.FOUND, countryDto);
                } else {
                    return new CountryRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, null);
                }
            }

        } catch (SQLException ex) {
            // Log the error with an English message
            logger.error("Error retrieving country information for country code: " + request.getCountryCode(), ex);
        }
        return new CountryRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, null);
    }

    /**
    * Retrieves information about a currency based on the provided currency code.
    * This method executes a SQL query to fetch details like currency name, 
    * currency symbol, and symbol name from the database, using the provided currency code from the request.
    *
    * @param request The request containing the session ID and the currency code for which the information is to be retrieved.
    * @return A {@link CurrencyRetrieveResponse} object containing the currency details if found, 
    *         or a response indicating failure if no currency is found or an error occurs.
    */
    public static CurrencyRetrieveResponse getCurrencyInfo(CurrencyRetrieveRequest request) {

        String query = Q.select(DatabaseTables.CURRENCIES.tableName(),
                Currencies.CURRENCY_CODE.getColumnName(),
                Currencies.CURRENCY_NAME.getColumnName(),
                Currencies.SYMBOL.getColumnName(),
                Currencies.SYMBOL_NAME.getColumnName())
                .concat(Q.where(Currencies.CURRENCY_CODE.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection(); 
                PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, request.getCurrencyCode());
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    
                    var currencyDTO = new CurrencyDTO.Builder()
                            .withCurrencyCode(rs.getString(Currencies.CURRENCY_CODE.getColumnName()))
                            .withCurrencyName(rs.getString(Currencies.CURRENCY_NAME.getColumnName()))
                            .withSymbol(rs.getString(Currencies.SYMBOL.getColumnName()))
                            .withSymbolName(rs.getString(Currencies.SYMBOL_NAME.getColumnName()))
                            .build();

                    return new CurrencyRetrieveResponse(request.getSessionId(), ResultType.FOUND, currencyDTO);
                }else{
                    return new CurrencyRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, null);
                }
            }

        } catch (SQLException ex) {
            // Log the error with an English message
            logger.error("Error retrieving currency information for currency code: " + request.getCurrencyCode(), ex);
        }
        return new CurrencyRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR, null);
    }

}
