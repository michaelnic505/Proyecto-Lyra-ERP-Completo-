

package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum COValuationVariantFields {
    
    VALUATION_VARIANT_ID("VALUATION_VARIANT_ID"),
    VALUATION_CODE("VALUATION_CODE"),
    VALUATION_NAME("VALUATION_NAME"),
    VALUATION_DESCRIPTION("VALUATION_DESCRIPTION"),
    IS_ACTIVE("IS_ACTIVE"),
    CREATED_AT("CREATED_AT"),
    CREATED_BY("CREATED_BY"),
    UPDATED_AT("UPDATED_AT"),
    UPDATED_BY("UPDATED_BY");

    private final String columnName;

    COValuationVariantFields(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}