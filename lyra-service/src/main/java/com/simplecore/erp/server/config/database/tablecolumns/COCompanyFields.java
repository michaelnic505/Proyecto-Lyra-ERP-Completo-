

package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum COCompanyFields{
    CO_COMPANY_ID("CO_COMPANY_ID"),
    CO_COMPANY_CODE("CO_COMPANY_CODE"),
    CO_COMPANY_NAME("CO_COMPANY_NAME"),
    CO_LEGAL_NAME("CO_LEGAL_NAME"),
    CO_COUNTRY_CODE("CO_COUNTRY_CODE"),
    CO_CURRENCY_CODE("CO_CURRENCY_CODE"),
    CO_LANGUAGE("CO_LANGUAGE"),
    CO_TIME_ZONE("CO_TIME_ZONE"),
    CO_CONTROLLING_TYPE("CO_CONTROLLING_TYPE"),
    CO_VERSION_CONTROL("CO_VERSION_CONTROL"),
    CO_ASSIGNMENT_RULES("CO_ASSIGNMENT_RULES"),
    CO_VALID_FROM("CO_VALID_FROM"),
    CO_VALID_TO("CO_VALID_TO"),
    CO_STATUS("CO_STATUS"),
    CO_CREATED_BY("CO_CREATED_BY"),
    CO_CREATED_AT("CO_CREATED_AT"),
    CO_UPDATED_BY("CO_UPDATED_BY"),
    CO_UPDATED_AT("CO_UPDATED_AT");

    private final String value;

    COCompanyFields(String value) {
        this.value = value;
    }

    public String getColumnName() {
        return value;
    }
}

