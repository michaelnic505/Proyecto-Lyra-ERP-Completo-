
package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum FICORelationFields {
    FICO_RELATION_ID("FICO_RELATION_ID"),
    FI_COMPANY_ID("FI_COMPANY_ID"),
    CO_COMPANY_ID("CO_COMPANY_ID"),
    COSTING_VARIANT("COSTING_VARIANT"),
    COST_CURRENCY("COST_CURRENCY"),
    ALLOW_INTERNAL_ORDERS("ALLOW_INTERNAL_ORDERS"),
    REQUIRE_COST_CENTER("REQUIRE_COST_CENTER"),
    COST_CENTER_PLAN("COST_CENTER_PLAN"),
    RELATION_TYPE("RELATION_TYPE"),
    FICO_VALID_FROM("FICO_VALID_FROM"),
    FICO_VALID_TO("FICO_VALID_TO"),
    FICO_STATUS("FICO_STATUS"),
    FICO_CREATED_BY("FICO_CREATED_BY"),
    FICO_CREATED_AT("FICO_CREATED_AT"),
    FICO_UPDATED_BY("FICO_UPDATED_BY"),
    FICO_UPDATED_AT("FICO_UPDATED_AT");

    private final String value;

    FICORelationFields(String value) {
        this.value = value;
    }

    public String getColumnName() {
        return value;
    }
}

