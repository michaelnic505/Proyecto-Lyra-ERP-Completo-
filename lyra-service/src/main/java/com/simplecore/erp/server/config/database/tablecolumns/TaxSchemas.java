

package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum TaxSchemas {
    ID("ID"),
    NAME("SCHEMA_NAME"),
    DESCRIPTION("DESCRIPTION"),
    STATUS("STATUS"),
    START_DATE("START_DATE"),
    END_DATE("END_DATE"),
    IS_EXEMPT("IS_EXEMPT"),
    COUNTRY_CODE("COUNTRY_CODE"),
    CREATED_AT("CREATED_AT"),
    CREATED_BY("CREATED_BY"),
    UPDATED_AT("UPDATED_AT"),
    UPDATED_BY("UPDATED_BY"),
    TAX_CODE("TAX_CODE");

    private final String columnName;

    TaxSchemas(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
