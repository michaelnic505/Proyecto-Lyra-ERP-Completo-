
package com.simplecore.erp.client.gui.workspace.modules.financial.controlling.costvariant.auxiliar;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum CostVariantFields {
    
    COST_VARIANT_WINDOW_TITLE("COST.VARIANT.WINDOW.TITLE"),
    COST_VARIANT_WINDOW_TITLE_PANE("COST.VARIANT.WINDOW.TITLE.PANE"),
    
    COST_VARIANT_ID("COST.VARIANT.ID"),
    COST_VARIANT_CODE("COST.VARIANT.CODE"),
    COST_VARIANT_NAME("COST.VARIANT.NAME"),
    COST_VARIANT_DESCRIPTION("COST.VARIANT.DESCRIPTION"),
    COST_VARIANT_VALID_FROM("COST.VARIANT.VALID.FROM"),
    COST_VARIANT_VALID_TO("COST.VARIANT.VALID.TO"),
    COST_VARIANT_VALUATION_VARIANT_ID("COST.VARIANT.VALUATION.VARIANT.ID"),
    COST_VARIANT_VERSION_ID("COST.VARIANT.VERSION.ID"),
    COST_VARIANT_IS_ACTIVE("COST.VARIANT.IS.ACTIVE"),
    COST_VARIANT_CREATED_AT("COST.VARIANT.CREATED.AT"),
    COST_VARIANT_CREATED_BY("COST.VARIANT.CREATED.BY"),
    COST_VARIANT_UPDATED_AT("COST.VARIANT.UPDATED.AT"),
    COST_VARIANT_UPDATED_BY("COST.VARIANT.UPDATED.BY");
    
    private final String key;

    private CostVariantFields(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
