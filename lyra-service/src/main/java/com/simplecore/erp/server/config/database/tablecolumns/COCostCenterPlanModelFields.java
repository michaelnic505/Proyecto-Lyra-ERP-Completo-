package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum COCostCenterPlanModelFields {

    ID("ID"),
    COST_CENTER_PLAN_ID("COST_CENTER_PLAN_ID"),
    COST_CENTER_MODEL_ID("COST_CENTER_MODEL_ID"),
    DISPLAY_ORDER("DISPLAY_ORDER"),
    NOTES("NOTES");

    private final String colunmName;

    private COCostCenterPlanModelFields(String colunmName) {
        this.colunmName = colunmName;
    }

    public String getColunmName() {
        return colunmName;
    }

}
