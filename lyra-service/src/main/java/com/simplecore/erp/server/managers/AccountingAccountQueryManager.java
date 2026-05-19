package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.shared.models.queries.QueryFilters;
import com.simplecore.erp.shared.requests.types.AcountingAccountFilterQueryRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.AcountingAccountFilterQueryRetrieveResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class AccountingAccountQueryManager {

    private static final Logger logger = LoggerFactory.getLogger(AccountingAccountQueryManager.class);

    // Mapa que contiene campos virtuales con sus respectivos nombres mapeados.
    private static final Map<String, String> VIRTUAL_FIELDS = Map.of(
            "acc.PARENT_ACCOUNT_ID", "PARENT_ACCOUNT_CODE"
    // Aquí puedes seguir agregando más campos virtuales
    // por ejemplo: "acc.MODEL_ID" -> "MODEL_CODE"
    );

    // Verifica si un campo es virtual según el nombre del campo.
    private static boolean isVirtualField(String fieldName) {
        return VIRTUAL_FIELDS.containsKey(fieldName);
    }

    // Obtiene la clave del campo virtual asociado a un campo real.
    private static String getVirtualFieldKey(String fieldName) {
        return VIRTUAL_FIELDS.get(fieldName);
    }

    /**
     * Recupera una lista de cuentas contables filtradas según los criterios
     * proporcionados.
     *
     * @param request La solicitud con los filtros y parámetros de búsqueda.
     * @return Un objeto de respuesta que contiene los datos filtrados.
     */
    public static AcountingAccountFilterQueryRetrieveResponse getFilteredQuery(AcountingAccountFilterQueryRetrieveRequest request) {

        // Construir la consulta SQL y los parámetros correspondientes
        Map<String, Object> queryAndParams = buildSQLQuery(request);
        String query = (String) queryAndParams.get("query");
        List<Object> parameters = (List<Object>) queryAndParams.get("parameters");
        List<Object[]> dataSource = new ArrayList<>();

        
        try (Connection conn = PooledConnectionService.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            // Asignar los valores de los parámetros a la consulta
            int index = 1;
            for (Object param : parameters) {
                stmt.setObject(index++, param);  // Asigna el parámetro a la posición correspondiente
            }

            // Ejecutar la consulta y procesar los resultados
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Agregar los resultados de la consulta a la lista de datos
                    dataSource.add(new Object[]{
                        rs.getObject("ACCOUNT_CODE"),
                        rs.getObject("ACCOUNT_NAME"),
                        rs.getObject("ACCOUNT_CREATED_BY"),
                        rs.getObject("ACCOUNT_CREATED_AT"),
                        rs.getObject("ACCOUNT_UPDATED_BY"),
                        rs.getObject("ACCOUNT_UPDATED_AT"),
                        rs.getObject("ACCOUNT_STATUS"),
                        rs.getObject("IS_CLOSED"),
                        rs.getObject("SUBCLASS_CODE"),
                        rs.getObject("SUBCLASS_NAME"),
                        rs.getObject("SUBCLASS_CREATED_AT"),
                        rs.getObject("CLASS_ID"),
                        rs.getObject("CLASS_NAME"),
                        rs.getObject("MODEL_NAME"),
                        rs.getObject("DESCRIPTION"),
                        rs.getObject("MODEL_CREATED_AT"),
                        rs.getObject("MODEL_CREATED_BY"),
                        rs.getObject("STATUS"),
                        rs.getObject("MODEL_MODIFIED_BY"),
                        rs.getObject("MODEL_MODIFIED_AT"),
                        rs.getObject("STATE")});
                }
                if (!dataSource.isEmpty()) {
                    return new AcountingAccountFilterQueryRetrieveResponse(request.getSessionId(), ResultType.FOUND, dataSource);
                } else {
                    return new AcountingAccountFilterQueryRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, dataSource);
                }
            }

        } catch (SQLException ex) {
             // Manejar excepciones de SQL
            logger.info("No se pudo realizar la extraccion de lista de cuentas contables por filtros");
        }
        return new AcountingAccountFilterQueryRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR,dataSource);
    }

    /**
     * Construye la consulta SQL completa para la recuperación de datos según
     * los filtros proporcionados.
     *
     * @param request La solicitud que contiene los filtros.
     * @return Un mapa con la consulta SQL y los parámetros para la consulta.
     */
    private static Map<String, Object> buildSQLQuery(AcountingAccountFilterQueryRetrieveRequest request) {
        StringBuilder query = buildBaseQuery();

        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        // Procesar los filtros proporcionados y construir las condiciones de búsqueda
        processFilters(request.getFilters(), conditions, parameters);
        
         // Si hay condiciones, agregarlas a la consulta
        if (!conditions.isEmpty()) {
            query.append(" WHERE ");
            query.append(String.join(" AND ", conditions));
        }

        // Devolver la consulta junto con los parámetros
        Map<String, Object> result = new HashMap<>();
        result.put("query", query.toString());
        result.put("parameters", parameters);
        
        return result;
    }
    
    /**
     * Construye la base de la consulta SQL para recuperar las cuentas
     * contables.
     *
     * @return La consulta base sin condiciones.
     */
    private static StringBuilder buildBaseQuery() {
        return new StringBuilder("""
                SELECT 
                    acc.ACCOUNT_CODE,
                    acc.ACCOUNT_NAME,
                    acc.CREATED_BY AS ACCOUNT_CREATED_BY,
                    acc.CREATED_AT AS ACCOUNT_CREATED_AT,
                    acc.UPDATED_BY AS ACCOUNT_UPDATED_BY,
                    acc.UPDATED_AT AS ACCOUNT_UPDATED_AT,
                    acc.ACCOUNT_STATUS,
                    acc.IS_CLOSED,

                    sub.SUBCLASS_CODE,
                    sub.SUBCLASS_NAME,
                    sub.CREATED_AT AS SUBCLASS_CREATED_AT,

                    sub.CLASS_ID,
                    cls.CLASS_NAME,

                    mdl.MODEL_NAME,
                    mdl.DESCRIPTION,
                    mdl.CREATED_AT AS MODEL_CREATED_AT,
                    mdl.CREATED_BY AS MODEL_CREATED_BY,
                    mdl.STATUS,
                    mdl.MODIFIED_BY AS MODEL_MODIFIED_BY,
                    mdl.MODIFIED_AT AS MODEL_MODIFIED_AT,
                    mdl.STATE

                FROM accounting_accounts acc
                JOIN account_subclasses sub ON acc.SUBCLASS_ID = sub.SUBCLASS_ID
                JOIN account_classes cls ON sub.CLASS_ID = cls.CLASS_ID
                JOIN account_models mdl ON sub.MODEL_ID = mdl.MODEL_ID""");
    }
    
    /**
    * Procesa los filtros de la solicitud y agrega las condiciones correspondientes a la consulta.
    * 
    * @param filters Los filtros a aplicar a la consulta.
    * @param conditions Las condiciones a agregar a la consulta.
    * @param parameters Los parámetros que se asociarán a las condiciones.
    */
    private static void processFilters(List<QueryFilters> filters, List<String> conditions, List<Object> parameters) {
        for (QueryFilters filter : filters) {
            String fieldName = filter.getFieldName();
            // Si el campo es virtual, se maneja de manera especial
            if (isVirtualField(fieldName)) {
                handleVirtualFieldFilter(filter, conditions, parameters);
            } else {
                // Si el campo es estándar, se maneja con la lógica estándar
                handleStandardFieldFilter(filter, conditions, parameters);
            }
        }
    }
    
    /**
    * Maneja los filtros de campos virtuales en la consulta.
    * 
    * @param filter El filtro que se debe aplicar.
    * @param conditions Las condiciones de la consulta.
    * @param parameters Los parámetros que se asociarán a las condiciones.
    */
    private static void handleVirtualFieldFilter(QueryFilters filter, List<String> conditions, List<Object> parameters) {
        String virtualKey = getVirtualFieldKey(filter.getFieldName());
        // Dependiendo del campo virtual, aplicar diferentes condiciones
        switch (virtualKey) {
            case "PARENT_ACCOUNT_CODE" ->
                handleParentAccountCodeFilter(filter, conditions, parameters);
        }
    }

    /**
    * Maneja el filtro para el código de cuenta padre.
    * 
    * @param filter El filtro que se debe aplicar.
    * @param conditions Las condiciones de la consulta.
    * @param parameters Los parámetros que se asociarán a las condiciones.
    */
    private static void handleParentAccountCodeFilter(QueryFilters filter, List<String> conditions, List<Object> parameters) {
        switch (filter.getOperation()) {
            case "RANGE" -> {
                conditions.add("acc.ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM accounting_accounts WHERE ACCOUNT_CODE BETWEEN ? AND ?)");
                parameters.add(filter.getFromValue());
                parameters.add(filter.getToValue());
            }
            case "IN" -> {
                String placeholders = String.join(",", Collections.nCopies(filter.getValues().size(), "?"));
                conditions.add("acc.ACCOUNT_ID IN (SELECT ACCOUNT_ID FROM accounting_accounts WHERE ACCOUNT_CODE IN (" + placeholders + "))");
                parameters.addAll(filter.getValues());
            }
            case "EQUALS" -> {
                conditions.add("acc.ACCOUNT_ID = (SELECT ACCOUNT_ID FROM accounting_accounts WHERE ACCOUNT_CODE = ?)");
                parameters.add(filter.getFromValue());
            }
        }
    }

    /**
    * Maneja los filtros de campos estándar en la consulta.
    * 
    * @param filter El filtro que se debe aplicar.
    * @param conditions Las condiciones de la consulta.
    * @param parameters Los parámetros que se asociarán a las condiciones.
    */
    private static void handleStandardFieldFilter(QueryFilters filter, List<String> conditions, List<Object> parameters) {
        switch (filter.getOperation()) {
            case "RANGE" -> {
                conditions.add(filter.getFieldName() + " BETWEEN ? AND ?");
                parameters.add(filter.getFromValue());
                parameters.add(filter.getToValue());
            }
            case "IN" -> {
                String placeholders = String.join(",", Collections.nCopies(filter.getValues().size(), "?"));
                conditions.add(filter.getFieldName() + " IN (" + placeholders + ")");
                parameters.addAll(filter.getValues());
            }
            case "EQUALS" -> {
                conditions.add(filter.getFieldName() + " = ?");
                parameters.add(filter.getFromValue());
            }
        }
    }
}
