package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.auxiliar;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum ChartMessages {
    CHARTS_INFO_WINDOW_TITLE("CHART.OF.ACCOUNT.INFO.WINDOW.TITLE"),
    CHARTS_INFO_WINDOW_TITLE_PANE("CHART.OF.ACCOUNT.INFO.WINDOW.TITLE.PANE");

    private String key;

    private ChartMessages(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
