

package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum FICompanyDocuments {

    ID("ID"),
    FI_COMPANY_ID("FI_COMPANY_ID"),
    DOCUMENT_TYPE("DOCUMENT_TYPE"),
    FILE_CONTENT("FILE_CONTENT"),
    FILE_NAME("FILE_NAME"),
    FILE_EXTENSION("FILE_EXTENSION"),
    CREATED_AT("CREATED_AT"),
    UPDATED_AT("UPDATED_AT");

    private final String value;

    FICompanyDocuments(String value) {
        this.value = value;
    }

    public String getColumnName() {
        return value;
    }
}
