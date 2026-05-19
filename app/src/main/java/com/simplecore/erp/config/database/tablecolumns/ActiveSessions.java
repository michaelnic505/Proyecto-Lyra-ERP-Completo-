package com.simplecore.erp.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum ActiveSessions {
    ID,
    USERID,
    SESSIONID,
    TERMINAL,
    IPADDRESS,
    LONGINTIME,
    LASTACTIVITY;

    public int index() {
        return ordinal() + 1; // Devuelve el índice 1-based en lugar de 0-based
    }
}
