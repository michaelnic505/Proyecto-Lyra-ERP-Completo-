

package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum COCostVariantFields {

    VARIANT_ID("VARIANT_ID"),
    VARIANT_CODE("VARIANT_CODE"),
    VARIANT_NAME("VARIANT_NAME"),
    VARIANT_DESCRIPTION("VARIANT_DESCRIPTION"),
    VALID_FROM("VALID_FROM"),
    VALID_TO("VALID_TO"),
    VALUATION_VARIANT_ID("VALUATION_VARIANT_ID"),
    VERSION_ID("VERSION_ID"),
    IS_ACTIVE("IS_ACTIVE"),
    CREATED_AT("CREATED_AT"),
    CREATED_BY("CREATED_BY"),
    UPDATED_AT("UPDATED_AT"),
    UPDATED_BY("UPDATED_BY");

    private final String columnName;

    private COCostVariantFields(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
