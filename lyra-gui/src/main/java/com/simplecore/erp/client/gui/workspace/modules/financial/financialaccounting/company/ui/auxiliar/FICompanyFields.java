

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.auxiliar;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum FICompanyFields {
    COUNTRY_INFO_WINDOW_TITLE("FICOMPANY.INFO.WINDOW.TITLE"),
    COUNTRY_INFO_WINDOW_TITLE_PANE("FICOMPANY.INFO.WINDOW.TITLE.PANE"),
    
    FI_COMPANY_ID("FI.COMPANY.ID"),
    FI_COMPANY_CODE("FI.COMPANY.CODE"),
    FI_COMPANY_NAME("FI.COMPANY.NAME"),
    FI_LEGAL_NAME("FI.LEGAL.NAME"),
    FI_BUSSINES_TYPE("FI.BUSSINES.TYPE"),
    FI_BUSSINES_CLASSIFICATION("FI.BUSSINES.CLASSIFICATION"),
    FI_COUNTRY_CODE("FI.COUNTRY.CODE"),
    FI_LEGAL_ADDRESS("FI.LEGAL.ADDRESS"),
    FI_PHONE("FI.PHONE"),
    FI_EMAIL("FI.EMAIL"),
    FI_LANGUAGE("FI.LANGUAGE"),
    FI_TIME_ZONE("FI.TIME.ZONE"),
    FI_CHART_OF_ACCOUNT("FI.CHART.OF.ACCOUNT"),
    FI_CREATED_BY("FI.CREATED.BY"),
    FI_CREATED_AT("FI.CREATED.AT"),
    FI_UPDATED_BY("FI.UPDATED.BY"),
    FI_UPDATED_AT("FI.UPDATED.AT"),
    FI_COMPANY_STATUS("FI.COMPANY.STATUS"),
    SYS_TRANSACTION("SYS.TRANSACTION");

    private final String key;

    FICompanyFields(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
