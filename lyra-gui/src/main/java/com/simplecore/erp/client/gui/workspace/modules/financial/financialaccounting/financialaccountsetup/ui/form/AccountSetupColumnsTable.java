

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum AccountSetupColumnsTable {
    ACCOUNT_ID("ACCOUNTSETUP.COLUMN.ACCOUNT_ID"),
    CHART_OF_ACCOUNT_CODE("ACCOUNTSETUP.COLUMN.CHART_OF_ACCOUNT_CODE"),
    CHART_OF_ACCOUNT_NAME("ACCOUNTSETUP.COLUMN.CHART_OF_ACCOUNT_NAME"),
    CLASS_NAME("ACCOUNTSETUP.COLUMN.CLASS_NAME"),
    SUBCLASS_CODE("ACCOUNTSETUP.COLUMN.SUBCLASS_CODE"),
    SUBCLASS_NAME("ACCOUNTSETUP.COLUMN.SUBCLASS_NAME"),
    ACCOUNT_NAME("ACCOUNTSETUP.COLUMN.ACCOUNT_NAME"),
    ACCOUNT_DESCRIPTION("ACCOUNTSETUP.COLUMN.ACCOUNT_DESCRIPTION"),
    ACCOUNT_STATUS("ACCOUNTSETUP.COLUMN.ACCOUNT_STATUS"),
    ACCOUNT_CODE("ACCOUNTSETUP.COLUMN.ACCOUNT_CODE");

    private final String propertyKey;

    AccountSetupColumnsTable(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getKey() {
        return propertyKey;
    }
}
