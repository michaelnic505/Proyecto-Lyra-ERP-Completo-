

package com.simplecore.erp.client.gui.workspace.modules.financial.controlling.services.costcenterplan;


/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum CostCenterPlanFields {
    COST_CENTER_PLAN_WINDOW_TITLE("COST.CENTER.PLAN.WINDOW.TITLE"),
    COST_CENTER_PLAN_WINDOW_TITLE_PANE("COST.CENTER.PLAN.WINDOW.TITLE.PANE"),
    
    COST_CENTER_PLAN_ID("COST.CENTER.PLAN.ID"),
    COST_CENTER_PLAN_NAME("COST.CENTER.PLAN.NAME"),
    COST_CENTER_PLAN_DESCRIPTION("COST.CENTER.PLAN.DESCRIPTION"),
    COST_CENTER_PLAN_STATUS("COST.CENTER.PLAN.STATUS"),
    COST_CENTER_PLAN_CREATED_AT("COST.CENTER.PLAN.CREATED_AT"),
    COST_CENTER_PLAN_CREATED_BY("COST.CENTER.PLAN.CREATED_BY"),
    COST_CENTER_PLAN_UPDATED_AT("COST.CENTER.PLAN.UPDATED_AT");
    
    private final String key;

    private CostCenterPlanFields(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
