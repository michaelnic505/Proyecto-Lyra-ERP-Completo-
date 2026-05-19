package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum COCostCenterModelDetailFields {
    ID("ID"),
    COST_CENTER_MODEL_ID("COST_CENTER_MODEL_ID"),
    COST_CENTER_ID("COST_CENTER_ID"),
    DISPLAY_ORDER("DISPLAY_ORDER"),
    NOTES("NOTES");

    private final String columnName;

    private COCostCenterModelDetailFields(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

}
