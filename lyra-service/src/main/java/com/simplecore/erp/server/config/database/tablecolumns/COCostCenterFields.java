
package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum COCostCenterFields {

    ID("ID"),
    CODE("CODE"),
    NAME("NAME"),
    DESCRIPTION("DESCRIPTION"),
    CREATED_BY("CREATED_BY"),
    CREATED_AT("CREATED_AT"),
    UPDATED_AT("UPDATED_AT"),
    STATUS("STATUS");
    
    private final String columnName;

    private COCostCenterFields(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
