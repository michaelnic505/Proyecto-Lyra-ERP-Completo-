

package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.COCompanyFields;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.models.dto.COCompanyDTO;
import com.simplecore.erp.shared.requests.types.COCompanyByCodeRetrieveRequest;
import com.simplecore.erp.shared.requests.types.COCompanyListRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.COCompanyByCodeRetrieveResponse;
import com.simplecore.erp.shared.responses.types.COCompanyListRetrieveResponse;
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
public class COCompanyManager {
    
    private static final Logger logger = LoggerFactory.getLogger(COCompanyManager.class);

    public static COCompanyListRetrieveResponse getCOCompanyList(COCompanyListRetrieveRequest request){
        List<Object[]> dataSource = new ArrayList<>();
        String query = Q.select(DatabaseTables.CO_COMPANY.tableName(), 
                COCompanyFields.CO_COMPANY_CODE.getColumnName(),
                COCompanyFields.CO_COMPANY_NAME.getColumnName(),
                COCompanyFields.CO_LEGAL_NAME.getColumnName(),
                COCompanyFields.CO_COUNTRY_CODE.getColumnName(),
                COCompanyFields.CO_CURRENCY_CODE.getColumnName(),
                COCompanyFields.CO_TIME_ZONE.getColumnName(),
                COCompanyFields.CO_VALID_FROM.getColumnName(),
                COCompanyFields.CO_VALID_TO.getColumnName(),
                COCompanyFields.CO_STATUS.getColumnName(),
                COCompanyFields.CO_CREATED_BY.getColumnName());
        
        try(Connection conn = PooledConnectionService.getConnection();
                PreparedStatement st = conn.prepareStatement(query);
                ResultSet rs = st.executeQuery()){
            while(rs.next()){
                dataSource.add(new Object[]{
                rs.getObject(COCompanyFields.CO_COMPANY_CODE.getColumnName()),
                rs.getObject(COCompanyFields.CO_COMPANY_NAME.getColumnName()),
                rs.getObject(COCompanyFields.CO_LEGAL_NAME.getColumnName()),
                rs.getObject(COCompanyFields.CO_COUNTRY_CODE.getColumnName()),
                rs.getObject(COCompanyFields.CO_CURRENCY_CODE.getColumnName()),
                rs.getObject(COCompanyFields.CO_TIME_ZONE.getColumnName()),
                rs.getObject(COCompanyFields.CO_VALID_FROM.getColumnName()),
                rs.getObject(COCompanyFields.CO_VALID_TO.getColumnName()),
                rs.getObject(COCompanyFields.CO_STATUS.getColumnName()),
                rs.getObject(COCompanyFields.CO_CREATED_BY.getColumnName())
                });
            }
            if(!dataSource.isEmpty()){
                return new COCompanyListRetrieveResponse(request.getSessionId(), ResultType.FOUND, dataSource);
            }
            return new COCompanyListRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, dataSource);
            
        } catch (SQLException ex) {
            logger.error("Database error while retrieving CO Company list for session {}: {}", 
            request.getSessionId(), ex.getMessage(), ex);
        }
        return new COCompanyListRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, dataSource);
    }
    
    public static COCompanyByCodeRetrieveResponse getCOCompanyByCode(COCompanyByCodeRetrieveRequest request) {
        String query = Q.select(DatabaseTables.CO_COMPANY.tableName(),
                COCompanyFields.CO_COMPANY_ID.getColumnName(),
                COCompanyFields.CO_COMPANY_CODE.getColumnName(),
                COCompanyFields.CO_COMPANY_NAME.getColumnName(),
                COCompanyFields.CO_LEGAL_NAME.getColumnName(),
                COCompanyFields.CO_COUNTRY_CODE.getColumnName(),
                COCompanyFields.CO_CURRENCY_CODE.getColumnName(),
                COCompanyFields.CO_LANGUAGE.getColumnName(),
                COCompanyFields.CO_TIME_ZONE.getColumnName(),
                COCompanyFields.CO_CONTROLLING_TYPE.getColumnName(),
                COCompanyFields.CO_VERSION_CONTROL.getColumnName(),
                COCompanyFields.CO_ASSIGNMENT_RULES.getColumnName(),
                COCompanyFields.CO_VALID_FROM.getColumnName(),
                COCompanyFields.CO_VALID_TO.getColumnName(),
                COCompanyFields.CO_STATUS.getColumnName(),
                COCompanyFields.CO_CREATED_BY.getColumnName(),
                COCompanyFields.CO_CREATED_AT.getColumnName(),
                COCompanyFields.CO_UPDATED_BY.getColumnName(),
                COCompanyFields.CO_UPDATED_AT.getColumnName()).concat(Q.where(COCompanyFields.CO_COMPANY_CODE.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection(); 
                PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, request.getCompanyCode());
           
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    COCompanyDTO companyDTO = new COCompanyDTO.Builder()
                            .withCompanyId(rs.getInt(COCompanyFields.CO_COMPANY_ID.getColumnName()))
                            .withCompanyCode(rs.getString(COCompanyFields.CO_COMPANY_CODE.getColumnName()))
                            .withCompanyName(rs.getString(COCompanyFields.CO_COMPANY_NAME.getColumnName()))
                            .withLegalName(rs.getString(COCompanyFields.CO_LEGAL_NAME.getColumnName()))
                            .withCountryCode(rs.getString(COCompanyFields.CO_COUNTRY_CODE.getColumnName()))
                            .withCurrencyCode(rs.getString(COCompanyFields.CO_CURRENCY_CODE.getColumnName()))
                            .withLanguage(rs.getString(COCompanyFields.CO_LANGUAGE.getColumnName()))
                            .withTimeZone(rs.getString(COCompanyFields.CO_TIME_ZONE.getColumnName()))
                            .withControllingType(rs.getString(COCompanyFields.CO_CONTROLLING_TYPE.getColumnName()))
                            .withVersionControl(rs.getString(COCompanyFields.CO_VERSION_CONTROL.getColumnName()))
                            .withAssignmentRules(rs.getString(COCompanyFields.CO_ASSIGNMENT_RULES.getColumnName()))
                            .withValidFrom(rs.getDate(COCompanyFields.CO_VALID_FROM.getColumnName()))
                            .withValidTo(rs.getDate(COCompanyFields.CO_VALID_TO.getColumnName()))
                            .withStatus(rs.getString(COCompanyFields.CO_STATUS.getColumnName()))
                            .withCreatedBy(rs.getString(COCompanyFields.CO_CREATED_BY.getColumnName()))
                            .withCreatedAt(rs.getTimestamp(COCompanyFields.CO_CREATED_AT.getColumnName()))
                            .withUpdatedBy(rs.getString(COCompanyFields.CO_UPDATED_BY.getColumnName()))
                            .withUpdatedAt(rs.getTimestamp(COCompanyFields.CO_UPDATED_AT.getColumnName()))
                            .build();

                    return new COCompanyByCodeRetrieveResponse(request.getSessionId(), ResultType.FOUND, companyDTO);
                }
                return new COCompanyByCodeRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, null);
            }

        } catch (SQLException ex) {
            logger.error("Error executing query to retrieve company by code", ex);
        }
        return new COCompanyByCodeRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, null);
    }
}
