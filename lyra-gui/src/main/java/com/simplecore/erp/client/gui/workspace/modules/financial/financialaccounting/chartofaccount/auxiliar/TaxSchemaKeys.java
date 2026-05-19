

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.auxiliar;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum TaxSchemaKeys {

    CO01_TAX_SCHEMA_ID("CO01.TAX.SCHEMA.ID"),
    CO01_TAX_SCHEMA_NAME("CO01.TAX.SCHEMA.NAME"),
    CO01_TAX_SCHEMA_DESCRIPTION("CO01.TAX.SCHEMA.DESCRIPTION"),
    CO01_TAX_SCHEMA_STATUS("CO01.TAX.SCHEMA.STATUS"),
    CO01_TAX_SCHEMA_START_DATE("CO01.TAX.SCHEMA.START_DATE"),
    CO01_TAX_SCHEMA_END_DATE("CO01.TAX.SCHEMA.END_DATE"),
    CO01_TAX_SCHEMA_IS_EXEMPT("CO01.TAX.SCHEMA.IS_EXEMPT"),
    CO01_TAX_SCHEMA_COUNTRY_CODE("CO01.TAX.SCHEMA.COUNTRY_CODE"),
    CO01_TAX_SCHEMA_CREATED_AT("CO01.TAX.SCHEMA.CREATED_AT"),
    CO01_TAX_SCHEMA_CREATED_BY("CO01.TAX.SCHEMA.CREATED_BY"),
    CO01_TAX_SCHEMA_UPDATED_AT("CO01.TAX.SCHEMA.UPDATED_AT"),
    CO01_TAX_SCHEMA_UPDATED_BY("CO01.TAX.SCHEMA.UPDATED_BY"),
    CO01_TAX_SCHEMA_TAX_CODE("CO01.TAX.SCHEMA.TAX_CODE");

    private final String key;

    TaxSchemaKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
