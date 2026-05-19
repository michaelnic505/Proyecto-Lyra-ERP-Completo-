package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum COCompanyCostCenterPlanFields {
    ID("ID"),
    COMPANY_ID("COMPANY_ID"),
    COST_CENTER_PLAN_ID("COST_CENTER_PLAN_ID"),
    ASSIGNED_AT("ASSIGNED_AT"),
    ASSIGNED_BY("ASSIGNED_BY");

    private final String columnName;

    private COCompanyCostCenterPlanFields(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

}
