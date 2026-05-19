
package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum COCostingVersion {
    VERSION_ID("VERSION_ID"),
    VERSION_CODE("VERSION_CODE"),
    VERSION_NAME("VERSION_NAME"),
    VERSION_DESCRIPTION("VERSION_DESCRIPTION"),
    IS_PLANNING_VERSION("IS_PLANNING_VERSION"),
    IS_REAL_VERSION("IS_REAL_VERSION"),
    IS_SIMULATION_VERSION("IS_SIMULATION_VERSION"),
    IS_ACTIVE("IS_ACTIVE"),
    CREATED_AT("CREATED_AT"),
    CREATED_BY("CREATED_BY"),
    UPDATED_AT("UPDATED_AT"),
    UPDATED_BY("UPDATED_BY");

    private final String columnName;

    COCostingVersion(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}