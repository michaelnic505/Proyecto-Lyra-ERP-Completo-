

package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum FICompanyFields {
    FI_COMPANY_ID("FI_COMPANY_ID"),
    FI_COMPANY_CODE("FI_COMPANY_CODE"),
    FI_COMPANY_NAME("FI_COMPANY_NAME"),
    FI_LEGAL_NAME("FI_LEGAL_NAME"),
    FI_BUSSINES_TYPE("FI_BUSSINES_TYPE"),
    FI_BUSSINES_CLASSIFICATION("FI_BUSSINES_CLASSIFICATION"),
    FI_COUNTRY_CODE("FI_COUNTRY_CODE"),
    FI_LEGAL_ADDRESS("FI_LEGAL_ADDRESS"),
    FI_PHONE("FI_PHONE"),
    FI_EMAIL("FI_EMAIL"),
    FI_LANGUAGE("FI_LANGUAGE"),
    FI_TIME_ZONE("FI_TIME_ZONE"),
    FI_CHART_OF_ACCOUNT("FI_CHART_OF_ACCOUNT"),
    FI_CREATED_BY("FI_CREATED_BY"),
    FI_CREATED_AT("FI_CREATED_AT"),
    FI_UPDATED_BY("FI_UPDATED_BY"),
    FI_UPDATED_AT("FI_UPDATED_AT"),
    FI_COMPANY_STATUS("FI_COMPANY_STATUS"),
    FI_SYS_TRANSACTION("SYS_TRANSACTION");

    private final String value;

    FICompanyFields(String value) {
        this.value = value;
    }

    public String getColumnName() {
        return value;
    }
}


