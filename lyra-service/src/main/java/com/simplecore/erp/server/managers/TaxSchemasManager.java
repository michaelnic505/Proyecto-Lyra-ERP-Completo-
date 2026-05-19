

package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.TaxSchemas;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.models.dto.TaxSchemaDTO;
import com.simplecore.erp.shared.requests.types.TaxSchemaRetrieveRequest;
import com.simplecore.erp.shared.requests.types.TaxSchemasListRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.TaxSchemaRetrieveResponse;
import com.simplecore.erp.shared.responses.types.TaxSchemasListRetrieveResponse;
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
public class TaxSchemasManager {
    
    private static final Logger logger = LoggerFactory.getLogger(TaxSchemasManager.class);

    public static TaxSchemasListRetrieveResponse getTaxSchemasList(TaxSchemasListRetrieveRequest request){
        List<Object>dataSource = new ArrayList<>();
        
        String query = Q.select(DatabaseTables.FI_TAX_SCHEMAS.tableName(),
                TaxSchemas.ID.getColumnName(),
                TaxSchemas.NAME.getColumnName(),
                TaxSchemas.DESCRIPTION.getColumnName(),
                TaxSchemas.STATUS.getColumnName(),
                TaxSchemas.START_DATE.getColumnName(),
                TaxSchemas.END_DATE.getColumnName(),
                TaxSchemas.IS_EXEMPT.getColumnName(),
                TaxSchemas.COUNTRY_CODE.getColumnName(),
                TaxSchemas.CREATED_AT.getColumnName(),
                TaxSchemas.CREATED_BY.getColumnName(),
                TaxSchemas.UPDATED_AT.getColumnName(),
                TaxSchemas.UPDATED_BY.getColumnName(),
                TaxSchemas.TAX_CODE.getColumnName());
        
        try (Connection conn = PooledConnectionService.getConnection();
                PreparedStatement st = conn.prepareStatement(query);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                // Crear un arreglo para cada fila de datos
                Object[] rowData = new Object[]{
                    rs.getObject(TaxSchemas.ID.getColumnName()),
                    rs.getObject(TaxSchemas.NAME.getColumnName()),
                    rs.getObject(TaxSchemas.DESCRIPTION.getColumnName()),
                    rs.getObject(TaxSchemas.STATUS.getColumnName()),
                    rs.getObject(TaxSchemas.START_DATE.getColumnName()),
                    rs.getObject(TaxSchemas.END_DATE.getColumnName()),
                    rs.getObject(TaxSchemas.IS_EXEMPT.getColumnName()),
                    rs.getObject(TaxSchemas.COUNTRY_CODE.getColumnName()),
                    rs.getObject(TaxSchemas.CREATED_AT.getColumnName()),
                    rs.getObject(TaxSchemas.CREATED_BY.getColumnName()),
                    rs.getObject(TaxSchemas.UPDATED_AT.getColumnName()),
                    rs.getObject(TaxSchemas.UPDATED_BY.getColumnName()),
                    rs.getObject(TaxSchemas.TAX_CODE.getColumnName())
                };

                dataSource.add(rowData);  // Agregar el arreglo a la lista
            }
            if(!dataSource.isEmpty()){
                return new TaxSchemasListRetrieveResponse(request.getSessionId(),ResultType.FOUND ,dataSource);
            }else{
                return new TaxSchemasListRetrieveResponse(request.getSessionId(),ResultType.NOT_FOUND ,dataSource);
            }
            
        } catch (SQLException ex) {
            logger.error("Error retrieving tax schemas", ex);
        }
     return new TaxSchemasListRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR ,dataSource);   
    }

    public static TaxSchemaRetrieveResponse getTaxSchema(TaxSchemaRetrieveRequest request){
        String query = Q.select(DatabaseTables.FI_TAX_SCHEMAS.tableName(),
                TaxSchemas.ID.getColumnName(),
                TaxSchemas.NAME.getColumnName(),
                TaxSchemas.DESCRIPTION.getColumnName(),
                TaxSchemas.STATUS.getColumnName(),
                TaxSchemas.START_DATE.getColumnName(),
                TaxSchemas.END_DATE.getColumnName(),
                TaxSchemas.IS_EXEMPT.getColumnName(),
                TaxSchemas.COUNTRY_CODE.getColumnName(),
                TaxSchemas.CREATED_AT.getColumnName(),
                TaxSchemas.CREATED_BY.getColumnName(),
                TaxSchemas.UPDATED_AT.getColumnName(),
                TaxSchemas.UPDATED_BY.getColumnName(),
                TaxSchemas.TAX_CODE.getColumnName()).concat(Q.where(TaxSchemas.ID.getColumnName()));
        
        try(Connection conn = PooledConnectionService.getConnection();
                PreparedStatement st = conn.prepareStatement(query)){
            st.setInt(1, request.getSchedaId());
            try(ResultSet rs = st.executeQuery()){
                if(rs.next()) {
                    TaxSchemaDTO taxSchema = new TaxSchemaDTO.Builder()
                            .id(rs.getLong(TaxSchemas.ID.getColumnName()))
                            .name(rs.getString(TaxSchemas.NAME.getColumnName()))
                            .description(rs.getString(TaxSchemas.DESCRIPTION.getColumnName()))
                            .status(rs.getString(TaxSchemas.STATUS.getColumnName()))
                            .startDate(rs.getDate(TaxSchemas.START_DATE.getColumnName()))
                            .endDate(rs.getDate(TaxSchemas.END_DATE.getColumnName()))
                            .isExempt(rs.getBoolean(TaxSchemas.IS_EXEMPT.getColumnName()))
                            .countryCode(rs.getString(TaxSchemas.COUNTRY_CODE.getColumnName()))
                            .createdAt(rs.getTimestamp(TaxSchemas.CREATED_AT.getColumnName()))
                            .createdBy(rs.getString(TaxSchemas.CREATED_BY.getColumnName()))
                            .updatedAt(rs.getTimestamp(TaxSchemas.UPDATED_AT.getColumnName()))
                            .updatedBy(rs.getString(TaxSchemas.UPDATED_BY.getColumnName()))
                            .taxCode(rs.getString(TaxSchemas.TAX_CODE.getColumnName()))
                            .build();
                    return new TaxSchemaRetrieveResponse(request.getSessionId(),ResultType.FOUND ,taxSchema);
                }else{
                    return new TaxSchemaRetrieveResponse(request.getSessionId(),ResultType.NOT_FOUND , null);
                }
            }

        } catch (SQLException ex) {
            logger.error("Error retrieving tax schema", ex);
        }
        return new TaxSchemaRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR ,null);
    }

}
