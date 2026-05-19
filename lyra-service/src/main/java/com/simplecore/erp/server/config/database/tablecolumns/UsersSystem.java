package com.simplecore.erp.server.config.database.tablecolumns;

public enum UsersSystem {

    USERNAME,
    FIRSTNAME,
    SECONDNAME,
    FIRSTLASTNAME,
    SECONDLASTNAME,
    EMAIL,
    POSITION,
    ORGANIZATION,
    USERTYPE,
    PASSWORD,
    HOSTNAME;

    public int index() {
        return ordinal() + 1; // Devuelve el índice 1-based en lugar de 0-based
    }
}
