

package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.COCostCenterPlanFields;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.models.dto.COCostCenterPlanDTO;
import com.simplecore.erp.shared.requests.types.COCostCenterPlanByIdRetrieveRequest;
import com.simplecore.erp.shared.requests.types.COCostCenterPlanByListRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.COCostCenterPlanByIdRetrieveResponse;
import com.simplecore.erp.shared.responses.types.COCostCenterPlanByListRetrieveResponse;
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
public class COCostCenterPlanManager {
    
    private static final Logger logger = LoggerFactory.getLogger(COCostCenterPlanManager.class);

    public static COCostCenterPlanByListRetrieveResponse getCostCenterPlanByList(COCostCenterPlanByListRetrieveRequest request) {
        List<Object[]> dataSource = new ArrayList<>();

        String query = Q.select(DatabaseTables.CO_COST_CENTER_PLAN.tableName(),
                COCostCenterPlanFields.ID.getColumnName(),
                COCostCenterPlanFields.NAME.getColumnName(),
                COCostCenterPlanFields.DESCRIPTION.getColumnName(),
                COCostCenterPlanFields.STATUS.getColumnName(),
                COCostCenterPlanFields.CREATED_BY.getColumnName(),
                COCostCenterPlanFields.CREATED_AT.getColumnName(),
                COCostCenterPlanFields.UPDATED_AT.getColumnName());

        try (Connection conn = PooledConnectionService.getConnection();
                PreparedStatement st = conn.prepareStatement(query);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                dataSource.add(new Object[]{
                    rs.getObject(COCostCenterPlanFields.ID.getColumnName()),
                    rs.getObject(COCostCenterPlanFields.NAME.getColumnName()),
                    rs.getObject(COCostCenterPlanFields.DESCRIPTION.getColumnName()),
                    rs.getObject(COCostCenterPlanFields.STATUS.getColumnName()),
                    rs.getObject(COCostCenterPlanFields.CREATED_BY.getColumnName()),
                    rs.getObject(COCostCenterPlanFields.CREATED_AT.getColumnName()),
                    rs.getObject(COCostCenterPlanFields.UPDATED_AT.getColumnName()),});
            }
            if (!dataSource.isEmpty()) {
                return new COCostCenterPlanByListRetrieveResponse(request.getSessionId(), ResultType.FOUND, dataSource);
            }
            return new COCostCenterPlanByListRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, dataSource);
        } catch (SQLException ex) {
            // Log error message in English
            logger.error("Error retrieving cost center plan by list: SQL exception occurred. Details: ", ex);
            return new COCostCenterPlanByListRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, dataSource);
        }
    }

    public static COCostCenterPlanByIdRetrieveResponse getCostCenterPlanById(COCostCenterPlanByIdRetrieveRequest request) {
        String query = Q.select(DatabaseTables.CO_COST_CENTER_PLAN.tableName(),
                COCostCenterPlanFields.ID.getColumnName(),
                COCostCenterPlanFields.NAME.getColumnName(),
                COCostCenterPlanFields.DESCRIPTION.getColumnName(),
                COCostCenterPlanFields.STATUS.getColumnName(),
                COCostCenterPlanFields.CREATED_BY.getColumnName(),
                COCostCenterPlanFields.CREATED_AT.getColumnName(),
                COCostCenterPlanFields.UPDATED_AT.getColumnName()).concat(Q.where(COCostCenterPlanFields.ID.getColumnName()));
        
        try (Connection conn = PooledConnectionService.getConnection(); 
                PreparedStatement st = conn.prepareStatement(query)) {
            System.out.println(request.getCostCenterPlanID());
            st.setLong(1, request.getCostCenterPlanID());
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    var costCenterPlanDTO = new COCostCenterPlanDTO.Builder()
                            .setCostCenterPlanID(rs.getLong(COCostCenterPlanFields.ID.getColumnName()))
                            .setCostCenterPlanName(rs.getString(COCostCenterPlanFields.NAME.getColumnName()))
                            .setCostCenterPlanDescription(rs.getString(COCostCenterPlanFields.DESCRIPTION.getColumnName()))
                            .setCostCenterPlanStatus(rs.getString(COCostCenterPlanFields.STATUS.getColumnName()))
                            .setCostCenterPlanCreatedBy(rs.getString(COCostCenterPlanFields.CREATED_BY.getColumnName()))
                            .setCostCenterPlanCreatedAt(rs.getTimestamp(COCostCenterPlanFields.CREATED_AT.getColumnName()))
                            .setCostCenterPlanUpdatedAt(rs.getTimestamp(COCostCenterPlanFields.UPDATED_AT.getColumnName()))
                            .build();
                    return new COCostCenterPlanByIdRetrieveResponse(request.getSessionId(), ResultType.FOUND, costCenterPlanDTO);
                }
                return new COCostCenterPlanByIdRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, null);
            }
        } catch (SQLException ex) {
            // An error occurred while retrieving the cost center plan by ID
            logger.error("Failed to retrieve Cost Center Plan by ID", ex);
            return new COCostCenterPlanByIdRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, null);
        }
    }

}
