

package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.COCostVariantFields;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.models.dto.COCostVariantDTO;
import com.simplecore.erp.shared.requests.types.COCostVariantByCodeRetrieveRequest;
import com.simplecore.erp.shared.requests.types.COCostVariantListRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.COCostVariantByCodeRetrieveResponse;
import com.simplecore.erp.shared.responses.types.COCostVariantListRetrieveResponse;
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
public class COCostVariantManager {
    private static final Logger logger = LoggerFactory.getLogger(COCostVariantManager.class);

    public static COCostVariantListRetrieveResponse getCostVariantList(COCostVariantListRetrieveRequest request){
        List<Object[]> variants = new ArrayList<>();
        
        String query = Q.select(DatabaseTables.CO_COST_VARIANT.tableName(),
                COCostVariantFields.VARIANT_CODE.getColumnName(),
                COCostVariantFields.VARIANT_NAME.getColumnName(),
                COCostVariantFields.VARIANT_DESCRIPTION.getColumnName(),
                COCostVariantFields.VALID_FROM.getColumnName(),
                COCostVariantFields.VALID_TO.getColumnName(),
                COCostVariantFields.IS_ACTIVE.getColumnName(),
                COCostVariantFields.CREATED_AT.getColumnName(),
                COCostVariantFields.CREATED_BY.getColumnName(),
                COCostVariantFields.UPDATED_AT.getColumnName(),
                COCostVariantFields.UPDATED_BY.getColumnName());
        
        try(Connection conn = PooledConnectionService.getConnection();
                PreparedStatement st = conn.prepareStatement(query);
               ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                variants.add(new Object[]{
                    rs.getString(COCostVariantFields.VARIANT_CODE.getColumnName()),
                    rs.getString(COCostVariantFields.VARIANT_NAME.getColumnName()),
                    rs.getString(COCostVariantFields.VARIANT_DESCRIPTION.getColumnName()),
                    rs.getDate(COCostVariantFields.VALID_FROM.getColumnName()),
                    rs.getDate(COCostVariantFields.VALID_TO.getColumnName()),
                    rs.getBoolean(COCostVariantFields.IS_ACTIVE.getColumnName()),
                    rs.getTimestamp(COCostVariantFields.CREATED_AT.getColumnName()),
                    rs.getString(COCostVariantFields.CREATED_BY.getColumnName()),
                    rs.getTimestamp(COCostVariantFields.UPDATED_AT.getColumnName()),
                    rs.getString(COCostVariantFields.UPDATED_BY.getColumnName())
                });
            }
            if (!variants.isEmpty()) {
                return new COCostVariantListRetrieveResponse(request.getSessionId(), ResultType.FOUND, variants);
            }
            return new COCostVariantListRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, variants);

        } catch (SQLException ex) {
            logger.error("Error while retrieving cost variants | SQL State: {} | Error: {}",  
             ex.getSQLState(), ex.getMessage());
            return new COCostVariantListRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, variants);
        }
    }
    
    public static COCostVariantByCodeRetrieveResponse getCostVariantByCode(COCostVariantByCodeRetrieveRequest request) {
        String query = Q.select(DatabaseTables.CO_COST_VARIANT.tableName(),
                COCostVariantFields.VARIANT_ID.getColumnName(),
                COCostVariantFields.VARIANT_CODE.getColumnName(),
                COCostVariantFields.VARIANT_NAME.getColumnName(),
                COCostVariantFields.VARIANT_DESCRIPTION.getColumnName(),
                COCostVariantFields.VALID_FROM.getColumnName(),
                COCostVariantFields.VALID_TO.getColumnName(),
                COCostVariantFields.VALUATION_VARIANT_ID.getColumnName(),
                COCostVariantFields.VERSION_ID.getColumnName(),
                COCostVariantFields.IS_ACTIVE.getColumnName(),
                COCostVariantFields.CREATED_AT.getColumnName(),
                COCostVariantFields.CREATED_BY.getColumnName(),
                COCostVariantFields.UPDATED_AT.getColumnName(),
                COCostVariantFields.UPDATED_BY.getColumnName())
                .concat(Q.where(COCostVariantFields.VARIANT_CODE.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection(); 
                PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, request.getVariantCode());
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    var costVariantDTO = new COCostVariantDTO.Builder()
                            .withVariantId(rs.getLong(COCostVariantFields.VARIANT_ID.getColumnName()))
                            .withVariantCode(rs.getString(COCostVariantFields.VARIANT_CODE.getColumnName()))
                            .withVariantName(rs.getString(COCostVariantFields.VARIANT_NAME.getColumnName()))
                            .withVariantDescription(rs.getString(COCostVariantFields.VARIANT_DESCRIPTION.getColumnName()))
                            .withValidFrom(rs.getDate(COCostVariantFields.VALID_FROM.getColumnName()))
                            .withValidTo(rs.getDate(COCostVariantFields.VALID_TO.getColumnName()))
                            .withValuationVariantId(rs.getLong(COCostVariantFields.VALUATION_VARIANT_ID.getColumnName()))
                            .withVersionId(rs.getLong(COCostVariantFields.VERSION_ID.getColumnName()))
                            .withIsActive(rs.getBoolean(COCostVariantFields.IS_ACTIVE.getColumnName()))
                            .withCreatedAt(rs.getDate(COCostVariantFields.CREATED_AT.getColumnName()))
                            .withCreatedBy(rs.getString(COCostVariantFields.CREATED_BY.getColumnName()))
                            .withUpdatedAt(rs.getDate(COCostVariantFields.UPDATED_AT.getColumnName()))
                            .withUpdatedBy(rs.getString(COCostVariantFields.UPDATED_BY.getColumnName()))
                            .build();
                    return new COCostVariantByCodeRetrieveResponse(request.getSessionId(), ResultType.FOUND, costVariantDTO);
                }
                return new COCostVariantByCodeRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, null);
            }

        } catch (SQLException ex) {
            logger.error("Error while retrieving cost variants | SQL State: {} | Error: {}",
                    ex.getSQLState(), ex.getMessage());
            return new COCostVariantByCodeRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, null);
        }
    }

}
