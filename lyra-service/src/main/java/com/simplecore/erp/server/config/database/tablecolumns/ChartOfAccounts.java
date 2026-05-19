package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum ChartOfAccounts {
    CHART_ID("CHART_ID"),
    NAME("NAME"),
    CODE("CODE"),
    ACCOUNT_MODEL_ID("ACCOUNT_MODEL_ID"),
    CURRENCY_CODE("CURRENCY_CODE"),
    COUNTRY_CODE("COUNTRY_CODE"),
    BUSINESS_TYPE("BUSINESS_TYPE"),
    INDUSTRY_CLASSIFICATION("INDUSTRY_CLASSIFICATION"),
    FISCAL_YEAR("FISCAL_YEAR"),
    FISCAL_START_DATE("FISCAL_START_DATE"),
    FISCAL_END_DATE("FISCAL_END_DATE"),
    ACCOUNTING_STANDARD("ACCOUNTING_STANDARD"),
    STATUS("STATUS"),
    MULTICURRENCY_SUPPORT("MULTICURRENCY_SUPPORT"),
    TAX_SCHEMA("TAX_SCHEMA"),
    VERSION_TAG("VERSION_TAG"),
    DESCRIPTION("DESCRIPTION"),
    NOTES("NOTES"),
    CREATED_BY("CREATED_BY"),
    UPDATED_BY("UPDATED_BY"),
    CREATED_AT("CREATED_AT"),
    UPDATED_AT("UPDATED_AT"),
    SYS_TRANSACTION("SYS_TRANSACTION");

    private final String key;

    private ChartOfAccounts(String key) {
        this.key = key;
    }

    public String getColumnName() {
        return key;
    }
}
