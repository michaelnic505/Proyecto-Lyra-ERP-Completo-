
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.auxiliar;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum TaxWindowMessages {
    TAX_SCHEMAS_WINDOW_TITLE("TAX.SCHEMAS.WINDOW.TITLE"),
    TAX_SCHEMAS_WINDOW_TITLE_PANE("TAX.SCHEMAS.WINDOW.TITLE.PANE");

    private String key;

    private TaxWindowMessages(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
