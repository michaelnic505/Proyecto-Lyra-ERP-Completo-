

package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.FICORelationFields;
import com.simplecore.erp.server.config.database.tablecolumns.FICompanyDocuments;
import com.simplecore.erp.server.config.database.tablecolumns.FICompanyFields;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.models.dto.FICOCompanyRelation;
import com.simplecore.erp.shared.models.dto.FICOAssociationsDTO;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;
import com.simplecore.erp.shared.models.dto.FICompanyDocument;
import com.simplecore.erp.shared.models.dto.FIDocumentsDTO;
import com.simplecore.erp.shared.requests.types.FICompanyByCodeRetrieveRequest;
import com.simplecore.erp.shared.requests.types.FICompanyCreateRequest;
import com.simplecore.erp.shared.requests.types.FICompanyExistenceCheckRequest;
import com.simplecore.erp.shared.requests.types.FICompanyByListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.FICompanyDocumentsRetrieveRequest;
import com.simplecore.erp.shared.requests.types.FICompanyModifyRequest;
import com.simplecore.erp.shared.requests.types.FICompanyStatusListRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.FICompanyByCodeRetrieveResponse;
import com.simplecore.erp.shared.responses.types.FICompanyCreateResponse;
import com.simplecore.erp.shared.responses.types.FICompanyExistenceCheckResponse;
import com.simplecore.erp.shared.responses.types.FICompanyByListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.FICompanyDocumentsRetrieveResponse;
import com.simplecore.erp.shared.responses.types.FICompanyModifyResponse;
import com.simplecore.erp.shared.responses.types.FICompanyStatusListRetrieveResponse;
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
public class FICompanyManager {
    private static final Logger logger = LoggerFactory.getLogger(FICompanyManager.class);

    
    public static FICompanyCreateResponse createFICompany(FICompanyCreateRequest request){
        var fiCompanyDTO = request.getFICompanyDTO();
        var fiCompanyDocuments = request.getDocumentsDTO();
        var ficoAssociation = request.getFICOAssociationDTO();
        
        String query = Q.insertInto(DatabaseTables.FI_COMPANY.tableName(),
                FICompanyFields.FI_COMPANY_CODE.getColumnName(),
                FICompanyFields.FI_COMPANY_NAME.getColumnName(),
                FICompanyFields.FI_LEGAL_NAME.getColumnName(),
                FICompanyFields.FI_BUSSINES_TYPE.getColumnName(),
                FICompanyFields.FI_BUSSINES_CLASSIFICATION.getColumnName(),
                FICompanyFields.FI_COUNTRY_CODE.getColumnName(),
                FICompanyFields.FI_LEGAL_ADDRESS.getColumnName(),
                FICompanyFields.FI_PHONE.getColumnName(),
                FICompanyFields.FI_EMAIL.getColumnName(),
                FICompanyFields.FI_LANGUAGE.getColumnName(),
                FICompanyFields.FI_TIME_ZONE.getColumnName(),
                FICompanyFields.FI_CHART_OF_ACCOUNT.getColumnName(),
                FICompanyFields.FI_CREATED_BY.getColumnName(),
                FICompanyFields.FI_COMPANY_STATUS.getColumnName(),
                FICompanyFields.FI_SYS_TRANSACTION.getColumnName()
        );
        
        try(Connection conn = PooledConnectionService.getConnection()){
            PooledConnectionService.beginTransaction(conn);
            try(PreparedStatement st = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
                st.setString(1, fiCompanyDTO.getCompanyCode());
                st.setString(2, fiCompanyDTO.getCompanyName());
                st.setString(3, fiCompanyDTO.getCompanyLegalName());
                st.setString(4, fiCompanyDTO.getCompanyBussinesType());
                st.setString(5, fiCompanyDTO.getCompanyBussinesClassification());
                st.setString(6, fiCompanyDTO.getCompanyCountryCode());
                st.setString(7, fiCompanyDTO.getCompanyLegalAddress());
                st.setString(8, fiCompanyDTO.getCompanyPhone());
                st.setString(9, fiCompanyDTO.getCompanyEmail());
                st.setString(10, fiCompanyDTO.getCompanyOfficialLanguage());
                st.setString(11, fiCompanyDTO.getCompanyTimeZone());
                st.setString(12, fiCompanyDTO.getCompanyChartOfAccount());
                st.setString(13, fiCompanyDTO.getCompanyCreatedBy());
                st.setString(14, fiCompanyDTO.getCompanyStatus());
                st.setString(15, fiCompanyDTO.getSysTransaction());
                
                int reg = st.executeUpdate();
                if (reg == 0) {
                    PooledConnectionService.rollbackTransaction(conn);
                    return new FICompanyCreateResponse(request.getSessionId(), -1, ResultType.NOT_CREATED);
                }

                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                       
                        if (fiCompanyDocuments != null && !fiCompanyDocuments.getDocumentsToCreate().isEmpty()) {
                            createdCompanyDocuments(rs.getInt(1), fiCompanyDocuments, conn);
                        }
                        
                        if (ficoAssociation != null && !ficoAssociation.getAssociationToCreate().isEmpty()) {
                            createFICORelations(rs.getInt(1), ficoAssociation, conn);
                        }
                        
                        PooledConnectionService.commitTransaction(conn);
                        return new FICompanyCreateResponse(request.getSessionId(), rs.getInt(1), ResultType.CREATED);
                    }
                    PooledConnectionService.rollbackTransaction(conn);
                    return new FICompanyCreateResponse(request.getSessionId(), -1, ResultType.SQL_ERROR);
                }
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(conn);
                logger.warn("FI Company creation returned 0 rows inserted",ex);
            }

        } catch (SQLException ex) {
            logger.error("FI Company creation failed: ", ex);
        }
        return new FICompanyCreateResponse(request.getSessionId(),-1 ,ResultType.SQL_ERROR);
    }
 
    public static FICompanyModifyResponse modifyFICompany(FICompanyModifyRequest request) {
        var fiCompanyDTO = request.getFICompanyDTO();
        var fiCompanyDocuments = request.getDocumentsDTO();
        var ficoAssociation = request.getFICOAssociationDTO();

        String query = Q.update(DatabaseTables.FI_COMPANY.tableName(),
                FICompanyFields.FI_COMPANY_NAME.getColumnName(),
                FICompanyFields.FI_LEGAL_NAME.getColumnName(),
                FICompanyFields.FI_BUSSINES_TYPE.getColumnName(),
                FICompanyFields.FI_BUSSINES_CLASSIFICATION.getColumnName(),
                FICompanyFields.FI_COUNTRY_CODE.getColumnName(),
                FICompanyFields.FI_LEGAL_ADDRESS.getColumnName(),
                FICompanyFields.FI_PHONE.getColumnName(),
                FICompanyFields.FI_EMAIL.getColumnName(),
                FICompanyFields.FI_LANGUAGE.getColumnName(),
                FICompanyFields.FI_TIME_ZONE.getColumnName(),
                FICompanyFields.FI_CHART_OF_ACCOUNT.getColumnName(),
                FICompanyFields.FI_COMPANY_STATUS.getColumnName(),
                FICompanyFields.FI_UPDATED_BY.getColumnName(),
                FICompanyFields.FI_SYS_TRANSACTION.getColumnName()).concat(Q.where(FICompanyFields.FI_COMPANY_ID.getColumnName()));
        
        try (Connection conn = PooledConnectionService.getConnection();) {
            try (PreparedStatement st = conn.prepareStatement(query)) {
                PooledConnectionService.beginTransaction(conn);

                st.setString(1, fiCompanyDTO.getCompanyName());
                st.setString(2, fiCompanyDTO.getCompanyLegalName());
                st.setString(3, fiCompanyDTO.getCompanyBussinesType());
                st.setString(4, fiCompanyDTO.getCompanyBussinesClassification());
                st.setString(5, fiCompanyDTO.getCompanyCountryCode());
                st.setString(6, fiCompanyDTO.getCompanyLegalAddress());
                st.setString(7, fiCompanyDTO.getCompanyPhone());
                st.setString(8, fiCompanyDTO.getCompanyEmail());
                st.setString(9, fiCompanyDTO.getCompanyOfficialLanguage());
                st.setString(10, fiCompanyDTO.getCompanyTimeZone());
                st.setString(11, fiCompanyDTO.getCompanyChartOfAccount());
                st.setString(12, fiCompanyDTO.getCompanyStatus());
                st.setString(13, fiCompanyDTO.getCompanyUpdatedBy());
                st.setString(14, fiCompanyDTO.getSysTransaction());
                
                st.setLong(15, fiCompanyDTO.getCompanyID());

                int updateFICompany = st.executeUpdate();

                if (updateFICompany == 0) {
                    PooledConnectionService.rollbackTransaction(conn);
                    return new FICompanyModifyResponse(request.getSessionId(), ResultType.NOT_UPDATED);
                }
                
                long fiCompanyID = fiCompanyDTO.getCompanyID();
                
                if (fiCompanyDocuments != null) {
                    modifyCompanyDocuments(fiCompanyID, fiCompanyDocuments, conn);
                }
                if (ficoAssociation != null){
                    modifyFICORelations(fiCompanyID, ficoAssociation, conn);
                }

                PooledConnectionService.commitTransaction(conn);
                return new FICompanyModifyResponse(request.getSessionId(), ResultType.UPDATED);
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(conn);
            }
        } catch (SQLException ex) {
            logger.error("Error updating FICompany", ex);
        }
        return new FICompanyModifyResponse(request.getSessionId(), ResultType.SQL_ERROR);
    }
    
    public static FICompanyByCodeRetrieveResponse getFICompanyByCode(FICompanyByCodeRetrieveRequest request) {
        String query = Q.select(DatabaseTables.FI_COMPANY.tableName(),
                FICompanyFields.FI_COMPANY_ID.getColumnName(),
                FICompanyFields.FI_COMPANY_CODE.getColumnName(),
                FICompanyFields.FI_COMPANY_NAME.getColumnName(),
                FICompanyFields.FI_LEGAL_NAME.getColumnName(),
                FICompanyFields.FI_BUSSINES_TYPE.getColumnName(),
                FICompanyFields.FI_BUSSINES_CLASSIFICATION.getColumnName(),
                FICompanyFields.FI_COUNTRY_CODE.getColumnName(),
                FICompanyFields.FI_LEGAL_ADDRESS.getColumnName(),
                FICompanyFields.FI_PHONE.getColumnName(),
                FICompanyFields.FI_EMAIL.getColumnName(),
                FICompanyFields.FI_LANGUAGE.getColumnName(),
                FICompanyFields.FI_TIME_ZONE.getColumnName(),
                FICompanyFields.FI_CHART_OF_ACCOUNT.getColumnName(),
                FICompanyFields.FI_CREATED_BY.getColumnName(),
                FICompanyFields.FI_CREATED_AT.getColumnName(),
                FICompanyFields.FI_UPDATED_BY.getColumnName(),
                FICompanyFields.FI_UPDATED_AT.getColumnName(),
                FICompanyFields.FI_COMPANY_STATUS.getColumnName())
                .concat(Q.where(FICompanyFields.FI_COMPANY_CODE.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection(); PreparedStatement st = conn.prepareStatement(query)) {

            st.setString(1, request.getCompanyCode());

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    FICompanyDTO companyDTO = new FICompanyDTO.Builder()
                            .setCompanyID(rs.getInt(FICompanyFields.FI_COMPANY_ID.getColumnName()))
                            .setCompanyCode(rs.getString(FICompanyFields.FI_COMPANY_CODE.getColumnName()))
                            .setCompanyName(rs.getString(FICompanyFields.FI_COMPANY_NAME.getColumnName()))
                            .setCompanyLegalName(rs.getString(FICompanyFields.FI_LEGAL_NAME.getColumnName()))
                            .setCompanyBussinesType(rs.getString(FICompanyFields.FI_BUSSINES_TYPE.getColumnName()))
                            .setCompanyBussinesClassification(rs.getString(FICompanyFields.FI_BUSSINES_CLASSIFICATION.getColumnName()))
                            .setCompanyCountryCode(rs.getString(FICompanyFields.FI_COUNTRY_CODE.getColumnName()))
                            .setCompanyLegalAddress(rs.getString(FICompanyFields.FI_LEGAL_ADDRESS.getColumnName()))
                            .setCompanyPhone(rs.getString(FICompanyFields.FI_PHONE.getColumnName()))
                            .setCompanyEmail(rs.getString(FICompanyFields.FI_EMAIL.getColumnName()))
                            .setCompanyOfficialLanguage(rs.getString(FICompanyFields.FI_LANGUAGE.getColumnName()))
                            .setCompanyTimeZone(rs.getString(FICompanyFields.FI_TIME_ZONE.getColumnName()))
                            .setCompanyChartOfAccount(rs.getString(FICompanyFields.FI_CHART_OF_ACCOUNT.getColumnName()))
                            .setCompanyCreatedBy(rs.getString(FICompanyFields.FI_CREATED_BY.getColumnName()))
                            .setCompanyCreatedAt(rs.getTimestamp(FICompanyFields.FI_CREATED_AT.getColumnName()))
                            .setCompanyUpdatedBy(rs.getString(FICompanyFields.FI_UPDATED_BY.getColumnName()))
                            .setCompanyUpdatedAt(rs.getTimestamp(FICompanyFields.FI_UPDATED_AT.getColumnName()))
                            .setCompanyStatus(rs.getString(FICompanyFields.FI_COMPANY_STATUS.getColumnName()))
                            .build();

                    return new FICompanyByCodeRetrieveResponse(
                            request.getSessionId(),
                            ResultType.FOUND,
                            companyDTO
                    );
                } else {
                    return new FICompanyByCodeRetrieveResponse(
                            request.getSessionId(),
                            ResultType.NOT_FOUND,
                            null
                    );
                }
            }

        } catch (SQLException ex) {
            logger.error("Error retrieving company by code: {}", request.getCompanyCode(), ex);
            return new FICompanyByCodeRetrieveResponse(
                    request.getSessionId(),
                    ResultType.SQL_ERROR,
                    null
            );
        }
    }


    //Documents methods
    private static void createdCompanyDocuments(int fiCompanyID, FIDocumentsDTO fiDocumentsDTO, Connection conn) throws SQLException {
        List<FICompanyDocument> documentsDTO = fiDocumentsDTO.getDocumentsToCreate();
        executeDocumentBachInsert(fiCompanyID, documentsDTO, conn);
    }

    private static void modifyCompanyDocuments(long fiCompanyID,FIDocumentsDTO fiDocumentsDTO,Connection conn)throws SQLException{
       
        List<FICompanyDocument> documentsToCreate = fiDocumentsDTO.getDocumentsToCreate();
        List<FICompanyDocument> documentsToModify = fiDocumentsDTO.getDocumentsToModify();
        List<FICompanyDocument> documentsToDelete = fiDocumentsDTO.getDocumentsToDelete();
        
        executeDocumentBachInsert(fiCompanyID, documentsToCreate, conn);
        executeDocumentBatchUpdate(fiCompanyID, documentsToModify, conn);
        executeDocumentBatchDelete(fiCompanyID, documentsToDelete, conn);
    }
    
    private static void executeDocumentBachInsert(long fiCompanyID, List<FICompanyDocument> documentsDTO, Connection conn) throws SQLException {
        if(documentsDTO==null || documentsDTO.isEmpty()){
            return;
        }
        String query = Q.insertInto(DatabaseTables.FI_COMPANY_DOCUMENTS.tableName(),
                FICompanyDocuments.FI_COMPANY_ID.getColumnName(),
                FICompanyDocuments.DOCUMENT_TYPE.getColumnName(),
                FICompanyDocuments.FILE_CONTENT.getColumnName(),
                FICompanyDocuments.FILE_NAME.getColumnName(),
                FICompanyDocuments.FILE_EXTENSION.getColumnName()
        );
        try (PreparedStatement st = conn.prepareStatement(query)) {
            for (FICompanyDocument docs : documentsDTO) {
                st.setLong(1, fiCompanyID);
                st.setString(2, docs.getDocumentType());
                st.setBytes(3, docs.getFileContent());
                st.setString(4, docs.getFileName());
                st.setString(5, docs.getFileExtension());
                st.addBatch();
            }
            int[] exBatch = st.executeBatch();
            validateBatchResults(exBatch, documentsDTO.size());
        }
    }
    
    private static void executeDocumentBatchUpdate(long fiCompanyID, List<FICompanyDocument> documentsDTO, Connection conn) throws SQLException {
        if(documentsDTO==null || documentsDTO.isEmpty()){
            return;
        }
        String query = Q.update(DatabaseTables.FI_COMPANY_DOCUMENTS.tableName(),
                FICompanyDocuments.DOCUMENT_TYPE.getColumnName(),
                FICompanyDocuments.FILE_CONTENT.getColumnName(),
                FICompanyDocuments.FILE_NAME.getColumnName(),
                FICompanyDocuments.FILE_EXTENSION.getColumnName())
                .concat(Q.where(FICompanyDocuments.ID.getColumnName(),
                        FICompanyDocuments.FI_COMPANY_ID.getColumnName()));

        try (PreparedStatement st = conn.prepareStatement(query)) {
            for (FICompanyDocument docs : documentsDTO) {
                st.setString(1, docs.getDocumentType());
                st.setBytes(2, docs.getFileContent());
                st.setString(3, docs.getFileName());
                st.setString(4, docs.getFileExtension());
                st.setLong(5, docs.getDocumentID());
                st.setLong(6, fiCompanyID);
                st.addBatch();
            }
            int[] exBatch = st.executeBatch();
            validateBatchResults(exBatch, documentsDTO.size());
        }
    }
    
    private static void executeDocumentBatchDelete(long fiCompanyID, List<FICompanyDocument> documentsDTO, Connection conn) throws SQLException {
        if(documentsDTO==null || documentsDTO.isEmpty()){
            return;
        }
        String query = Q.deleteFrom(DatabaseTables.FI_COMPANY_DOCUMENTS.tableName(),
                FICompanyDocuments.ID.getColumnName(),
                FICompanyDocuments.FI_COMPANY_ID.getColumnName());

        try (PreparedStatement st = conn.prepareStatement(query)) {
            for (FICompanyDocument doc : documentsDTO) {
                st.setLong(1, doc.getDocumentID());
                st.setLong(2, fiCompanyID);
                st.addBatch();
            }
            int[] exBatch = st.executeBatch();
            validateBatchResults(exBatch, documentsDTO.size());
        }
    }
    //End Documents methods
    
    //FICO Relation methods
    private static void createFICORelations(int FICompanyID, FICOAssociationsDTO dto, Connection conn) throws SQLException {
        List<FICOCompanyRelation> data = dto.getAssociationToCreate();
        executeRelationsBatchInsert(FICompanyID, data, conn);
    }
    
    private static void modifyFICORelations(long FICompanyID, FICOAssociationsDTO dto, Connection conn) throws SQLException {
        List<FICOCompanyRelation> relationListToCreate = dto.getAssociationToCreate();
        List<FICOCompanyRelation> relationListToModify = dto.getAssociationToModify();
        
        executeRelationsBatchInsert(FICompanyID, relationListToCreate, conn);
        executeRelationsBatchUpdate(FICompanyID, relationListToModify, conn);
    }
 
    private static void executeRelationsBatchInsert(long FICompanyID, List<FICOCompanyRelation> relationList, Connection conn) throws SQLException {
        if (relationList == null || relationList.isEmpty()) {
            return;
        }
        String query = Q.insertInto(DatabaseTables.FICO_COMPANY_RELATION.tableName(),
                FICORelationFields.CO_COMPANY_ID.getColumnName(),
                FICORelationFields.FI_COMPANY_ID.getColumnName(),
                FICORelationFields.COSTING_VARIANT.getColumnName(),
                FICORelationFields.COST_CURRENCY.getColumnName(),
                FICORelationFields.ALLOW_INTERNAL_ORDERS.getColumnName(),
                FICORelationFields.REQUIRE_COST_CENTER.getColumnName(),
                FICORelationFields.COST_CENTER_PLAN.getColumnName(),
                FICORelationFields.RELATION_TYPE.getColumnName(),
                FICORelationFields.FICO_VALID_FROM.getColumnName(),
                FICORelationFields.FICO_VALID_TO.getColumnName(),
                FICORelationFields.FICO_STATUS.getColumnName(),
                FICORelationFields.FICO_CREATED_BY.getColumnName());

        try (PreparedStatement st = conn.prepareStatement(query)) {
            for (FICOCompanyRelation dataAsso : relationList) {
                st.setLong(1, dataAsso.getCoCompanyId());
                st.setLong(2, FICompanyID);
                st.setString(3, dataAsso.getCostingVariant());
                st.setString(4, dataAsso.getCostCurrency());
                st.setBoolean(5, dataAsso.isAllowInternalOrders());
                st.setBoolean(6, dataAsso.isRequireCostCenter());
                st.setString(7, dataAsso.getCostCenterPlan());
                st.setString(8, dataAsso.getRelationType());
                st.setDate(9, dataAsso.getValidFrom());
                st.setDate(10, dataAsso.getValidTo());
                st.setString(11, dataAsso.getStatus());
                st.setString(12, dataAsso.getCreatedBy());
                st.addBatch();
            }
            int[] result = st.executeBatch();
            validateBatchResults(result, relationList.size());
        }
    }
    
    private static void executeRelationsBatchUpdate(long FICompanyID, List<FICOCompanyRelation> relationList, Connection conn) throws SQLException {
        if (relationList == null || relationList.isEmpty()) {
            return;
        }
        String query = Q.update(DatabaseTables.FICO_COMPANY_RELATION.tableName(), 
                FICORelationFields.FICO_STATUS.getColumnName(),
                FICORelationFields.FICO_VALID_FROM.getColumnName(),
                FICORelationFields.FICO_VALID_TO.getColumnName(),
                FICORelationFields.RELATION_TYPE.getColumnName())
                .concat(Q.where(FICORelationFields.FICO_RELATION_ID.getColumnName(),
                        FICORelationFields.FI_COMPANY_ID.getColumnName()));

        try (PreparedStatement st = conn.prepareStatement(query)) {
            for (FICOCompanyRelation dataAsso : relationList) {
                st.setString(1, dataAsso.getStatus());
                st.setDate(2, dataAsso.getValidFrom());
                st.setDate(3, dataAsso.getValidTo());
                st.setString(4, dataAsso.getRelationType());
                st.setLong(5, dataAsso.getFICORelationID());
                st.setLong(6, FICompanyID);
                st.addBatch();
            }
            int[] result = st.executeBatch();
            validateBatchResults(result, relationList.size());
        }
    }
    //Ends FICO Relation methods 
    
    //Retrieve methods
    public static FICompanyStatusListRetrieveResponse getFICompanyStatusList(FICompanyStatusListRetrieveRequest request) {
        List<String> dataSource = new ArrayList<>();
        try (Connection conn = PooledConnectionService.getConnection(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SHOW COLUMNS FROM "
                + DatabaseTables.FI_COMPANY.tableName()
                + " LIKE '" + FICompanyFields.FI_COMPANY_STATUS.getColumnName() + "'")) {

            if (rs.next()) {
                String enumDef = rs.getString("Type");
                enumDef = enumDef.substring(enumDef.indexOf("(") + 1, enumDef.lastIndexOf(")"));

                // Procesar valores del ENUM y agregarlos a la lista como Object[]
                Arrays.stream(enumDef.split(","))
                        .map(s -> s.replace("'", "").trim()) // Cada valor en un Object[]
                        .forEach(dataSource::add);
                return new FICompanyStatusListRetrieveResponse(request.getSessionId(), ResultType.FOUND, dataSource);
            }
            return new FICompanyStatusListRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, dataSource);

        } catch (SQLException ex) {
            logger.error("Error retrieving ENUM values", ex);
        }
        return new FICompanyStatusListRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, dataSource);
    }

    public static FICompanyExistenceCheckResponse isFICompanyCreated(FICompanyExistenceCheckRequest request){
        String query = Q.select(DatabaseTables.FI_COMPANY.tableName(),
                FICompanyFields.FI_COMPANY_CODE.getColumnName())
                .concat(Q.where(FICompanyFields.FI_COMPANY_CODE.getColumnName()));
        try(Connection conn = PooledConnectionService.getConnection();
                PreparedStatement st = conn.prepareStatement(query)){
            st.setString(1, request.getCompanyCode());
            try(ResultSet rs = st.executeQuery()){
                if(rs.next()){
                    return new FICompanyExistenceCheckResponse(request.getSessionId(), ResultType.FOUND);
                }
                return new FICompanyExistenceCheckResponse(request.getSessionId(), ResultType.NOT_FOUND);
            }
        } catch (SQLException ex) {
            logger.error("SQL exception occurred while checking if FI company exists for code [{}]",
                    request.getCompanyCode(), ex);
            return new FICompanyExistenceCheckResponse(request.getSessionId(), ResultType.SQL_ERROR);
        }
    }

    public static FICompanyByListRetrieveResponse getFICompanyList(FICompanyByListRetrieveRequest request) {
        List<Object[]> dataSource = new ArrayList<>();
        String query = Q.select(DatabaseTables.FI_COMPANY.tableName(),
                FICompanyFields.FI_COMPANY_CODE.getColumnName(),
                FICompanyFields.FI_COMPANY_NAME.getColumnName(),
                FICompanyFields.FI_LEGAL_NAME.getColumnName(),
                FICompanyFields.FI_BUSSINES_TYPE.getColumnName(),
                FICompanyFields.FI_BUSSINES_CLASSIFICATION.getColumnName(),
                FICompanyFields.FI_COUNTRY_CODE.getColumnName(),
                FICompanyFields.FI_PHONE.getColumnName(),
                FICompanyFields.FI_EMAIL.getColumnName());

        try (Connection conn = PooledConnectionService.getConnection(); 
                PreparedStatement st = conn.prepareStatement(query); 
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                dataSource.add(new Object[]{
                    rs.getObject(FICompanyFields.FI_COMPANY_CODE.getColumnName()),
                    rs.getObject(FICompanyFields.FI_COMPANY_NAME.getColumnName()),
                    rs.getObject(FICompanyFields.FI_LEGAL_NAME.getColumnName()),
                    rs.getObject(FICompanyFields.FI_BUSSINES_TYPE.getColumnName()),
                    rs.getObject(FICompanyFields.FI_BUSSINES_CLASSIFICATION.getColumnName()),
                    rs.getObject(FICompanyFields.FI_COUNTRY_CODE.getColumnName()),
                    rs.getObject(FICompanyFields.FI_PHONE.getColumnName()),
                    rs.getObject(FICompanyFields.FI_EMAIL.getColumnName()),});
            }
            if(!dataSource.isEmpty()){
                return new FICompanyByListRetrieveResponse(request.getSessionId(), ResultType.FOUND, dataSource);
            }
            return new FICompanyByListRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, dataSource);

        } catch (SQLException ex) {
            logger.error("An error occurred while retrieving the list of financial companies", ex);
            return new FICompanyByListRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, dataSource);
        }
    }

 
    public static FICompanyDocumentsRetrieveResponse getFICompanyDocuments(FICompanyDocumentsRetrieveRequest request){
        List<FICompanyDocument> documentList = new ArrayList<>();
       
        String query = Q.select(DatabaseTables.FI_COMPANY_DOCUMENTS.tableName(), 
                FICompanyDocuments.ID.getColumnName(),
                FICompanyDocuments.FI_COMPANY_ID.getColumnName(),
                FICompanyDocuments.DOCUMENT_TYPE.getColumnName(),
                FICompanyDocuments.FILE_CONTENT.getColumnName(),
                FICompanyDocuments.FILE_NAME.getColumnName(),
                FICompanyDocuments.FILE_EXTENSION.getColumnName(),
                FICompanyDocuments.CREATED_AT.getColumnName(),
                FICompanyDocuments.UPDATED_AT.getColumnName())
                .concat(Q.where(FICompanyDocuments.FI_COMPANY_ID.getColumnName()));
        
        try(Connection conn = PooledConnectionService.getConnection();
                PreparedStatement st = conn.prepareStatement(query)){
            st.setLong(1, request.getCompanyID());
            try(ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    FICompanyDocument document = new FICompanyDocument.Builder()
                            .setFIDocumentID(rs.getLong(FICompanyDocuments.ID.getColumnName()))
                            .setFICompanyID(rs.getLong(FICompanyDocuments.FI_COMPANY_ID.getColumnName()))
                            .setDocumentType(rs.getString(FICompanyDocuments.DOCUMENT_TYPE.getColumnName()))
                            .setFileContent(rs.getBytes(FICompanyDocuments.FILE_CONTENT.getColumnName()))
                            .setFileName(rs.getString(FICompanyDocuments.FILE_NAME.getColumnName()))
                            .setFileExtension(rs.getString(FICompanyDocuments.FILE_EXTENSION.getColumnName()))
                            .setCreatedAt(rs.getTimestamp(FICompanyDocuments.CREATED_AT.getColumnName()))
                            .setUdpatedAt(rs.getTimestamp(FICompanyDocuments.UPDATED_AT.getColumnName()))
                            .build();
                    
                    documentList.add(document);
                }
                if(!documentList.isEmpty()){
                    FIDocumentsDTO documentsDTO = new FIDocumentsDTO(documentList,null,null,null);
                    return new FICompanyDocumentsRetrieveResponse(request.getSessionId(), ResultType.FOUND, documentsDTO);
                }
                return new FICompanyDocumentsRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, null);
            }
        } catch (SQLException ex) {
            logger.error("Error retrieving company documents for company ID " + request.getCompanyID(), ex);
            return new FICompanyDocumentsRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, null);
        }
    }
    //Ends Retrieve methods

    private static void validateBatchResults(int[] batchResults, int expectedSize) throws SQLException {
        if (batchResults.length != expectedSize) {
            throw new SQLException("Batch insert mismatch: expected " + expectedSize + " but got " + batchResults.length);
        }
        for (int result : batchResults) {
            if (result == Statement.EXECUTE_FAILED) {
                throw new SQLException("One or more batch insert operations failed.");
            }
        }
    }


}
