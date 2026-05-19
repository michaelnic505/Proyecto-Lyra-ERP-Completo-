
package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.COCompanyFields;
import com.simplecore.erp.server.config.database.tablecolumns.FICORelationFields;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.models.dto.FICOAssociationsDTO;
import com.simplecore.erp.shared.models.dto.FICOCompanyRelation;
import com.simplecore.erp.shared.requests.types.FICORelationByFICompanyRetrieveRequest;
import com.simplecore.erp.shared.requests.types.FICORelationStatusListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.FICORelationTypesRetriveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.FICORelationByFICompanyRetrieveResponse;
import com.simplecore.erp.shared.responses.types.FICORelationStatusListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.FICORelationTypesRetriveResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
public class FICORelationManager {
    private static final Logger logger = LoggerFactory.getLogger(FICORelationManager.class);
    
    public static FICORelationByFICompanyRetrieveResponse getFICORelationsByFICompany(FICORelationByFICompanyRetrieveRequest request){
        List<FICOCompanyRelation> companyCOAssociations = new ArrayList<>();
 
        String queryJoin = Q.selectF(
                Q.fieldAlias("FICO", FICORelationFields.FICO_RELATION_ID.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.FI_COMPANY_ID.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.CO_COMPANY_ID.getColumnName()),
                Q.fieldAlias("CO", COCompanyFields.CO_COMPANY_CODE.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.COSTING_VARIANT.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.COST_CURRENCY.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.ALLOW_INTERNAL_ORDERS.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.REQUIRE_COST_CENTER.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.COST_CENTER_PLAN.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.RELATION_TYPE.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.FICO_VALID_FROM.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.FICO_VALID_TO.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.FICO_STATUS.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.FICO_CREATED_AT.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.FICO_CREATED_BY.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.FICO_UPDATED_AT.getColumnName()),
                Q.fieldAlias("FICO", FICORelationFields.FICO_UPDATED_BY.getColumnName())
                )
                .concat(Q.fromAlias("FICO", DatabaseTables.FICO_COMPANY_RELATION.tableName()))
                .concat(Q.join(Q.fromAlias("CO", DatabaseTables.CO_COMPANY.tableName())))
                .concat(Q.onEqual("FICO", FICORelationFields.CO_COMPANY_ID.getColumnName(), 
                        "CO", COCompanyFields.CO_COMPANY_ID.getColumnName()))
                .concat(Q.where(FICORelationFields.FI_COMPANY_ID.getColumnName()));


        try(Connection conn = PooledConnectionService.getConnection();
                PreparedStatement st  = conn.prepareStatement(queryJoin)){
            st.setLong(1, request.getCompanyIdFI());
            try(ResultSet rs = st.executeQuery()){
                while (rs.next()) {
                    FICOCompanyRelation relationFICO = new FICOCompanyRelation.Builder()
                            .withFICORelationID(rs.getLong(FICORelationFields.FICO_RELATION_ID.getColumnName()))
                            .withFICompanyId(rs.getLong(FICORelationFields.FI_COMPANY_ID.getColumnName()))
                            .withCOCompanyId(rs.getLong(FICORelationFields.CO_COMPANY_ID.getColumnName()))
                            .withCOCompanyCode(rs.getString(COCompanyFields.CO_COMPANY_CODE.getColumnName()))
                            .withCostingVariant(rs.getString(FICORelationFields.COSTING_VARIANT.getColumnName()))
                            .withCostCurrency(rs.getString(FICORelationFields.COST_CURRENCY.getColumnName()))
                            .withAllowInternalOrders(rs.getBoolean(FICORelationFields.ALLOW_INTERNAL_ORDERS.getColumnName()))
                            .withRequireCostCenter(rs.getBoolean(FICORelationFields.REQUIRE_COST_CENTER.getColumnName()))
                            .withCostCenterPlan(rs.getString(FICORelationFields.COST_CENTER_PLAN.getColumnName()))
                            .withRelationType(rs.getString(FICORelationFields.RELATION_TYPE.getColumnName()))
                            .withValidFrom(rs.getDate(FICORelationFields.FICO_VALID_FROM.getColumnName()))
                            .withValidTo(rs.getDate(FICORelationFields.FICO_VALID_TO.getColumnName()))
                            .withStatus(rs.getString(FICORelationFields.FICO_STATUS.getColumnName()))
                            .withCreatedBy(rs.getString(FICORelationFields.FICO_CREATED_BY.getColumnName()))
                            .withCreatedAt(rs.getTimestamp(FICORelationFields.FICO_CREATED_AT.getColumnName()))
                            .withUpdatedBy(rs.getString(FICORelationFields.FICO_UPDATED_BY.getColumnName()))
                            .withUpdatedAt(rs.getTimestamp(FICORelationFields.FICO_UPDATED_AT.getColumnName()))
                            .build();
                    companyCOAssociations.add(relationFICO);
                }
                if(!companyCOAssociations.isEmpty()){
                    FICOAssociationsDTO ficoDTO = new FICOAssociationsDTO(null,null,companyCOAssociations);
                    return new FICORelationByFICompanyRetrieveResponse(request.getSessionId(), ResultType.FOUND, ficoDTO);
                }
                return new FICORelationByFICompanyRetrieveResponse(request.getSessionId(), ResultType.FOUND, null);
            }
        } catch (SQLException ex) {
            logger.error("Error while retrieving FICO relations for FI company ID: {}", request.getCompanyIdFI(), ex);
            return new FICORelationByFICompanyRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, null);
        }
    }

    public static FICORelationStatusListRetrieveResponse getFICORelationStatusList(FICORelationStatusListRetrieveRequest request) {
        List<String> dataSource = new ArrayList<>();
        try (Connection conn = PooledConnectionService.getConnection(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SHOW COLUMNS FROM "
                + DatabaseTables.FICO_COMPANY_RELATION.tableName()
                + " LIKE '" + FICORelationFields.FICO_STATUS.getColumnName() + "'")) {

            if (rs.next()) {
                String enumDef = rs.getString("Type");
                enumDef = enumDef.substring(enumDef.indexOf("(") + 1, enumDef.lastIndexOf(")"));

                // Procesar valores del ENUM y agregarlos a la lista como Object[]
                Arrays.stream(enumDef.split(","))
                        .map(s -> s.replace("'", "").trim()) // Cada valor en un Object[]
                        .forEach(dataSource::add);
                return new FICORelationStatusListRetrieveResponse(request.getSessionId(), ResultType.FOUND, dataSource);
            }

            return new FICORelationStatusListRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, dataSource);
        } catch (SQLException ex) {
            logger.error("Error retrieving ENUM values", ex);
        }
        return new FICORelationStatusListRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, dataSource);
    }

    public static FICORelationTypesRetriveResponse getFICORelationTypes(FICORelationTypesRetriveRequest request) {
        List<String> dataSource = new ArrayList<>();
        try (Connection conn = PooledConnectionService.getConnection(); 
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SHOW COLUMNS FROM "
                + DatabaseTables.FICO_COMPANY_RELATION.tableName()
                + " LIKE '" + FICORelationFields.RELATION_TYPE.getColumnName() + "'")) {

            if (rs.next()) {
                String enumDef = rs.getString("Type");
                enumDef = enumDef.substring(enumDef.indexOf("(") + 1, enumDef.lastIndexOf(")"));

                // Procesar valores del ENUM y agregarlos a la lista como Object[]
                Arrays.stream(enumDef.split(","))
                        .map(s -> s.replace("'", "").trim()) // Cada valor en un Object[]
                        .forEach(dataSource::add);
                return new FICORelationTypesRetriveResponse(request.getSessionId(), ResultType.FOUND, dataSource);
            }

            return new FICORelationTypesRetriveResponse(request.getSessionId(), ResultType.NOT_FOUND, dataSource);
        } catch (SQLException ex) {
            logger.error("Error retrieving ENUM values", ex);
            return new FICORelationTypesRetriveResponse(request.getSessionId(), ResultType.SQL_ERROR, dataSource);
        }
    }
}
