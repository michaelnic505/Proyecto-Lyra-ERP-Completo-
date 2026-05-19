

package com.simplecore.erp.shared.responses.base;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum ResultType {

    // Lectura (SELECT)
    FOUND,
    NOT_FOUND,

    // Creación (INSERT)
    CREATED,
    NOT_CREATED,

    // Actualización (UPDATE)
    UPDATED,
    NOT_UPDATED,

    // Eliminación (DELETE)
    DELETED,
    NOT_DELETED,

    // Errores generales
    SQL_ERROR,
    VALIDATION_ERROR,
    UNAUTHORIZED,
    UNKNOWN_ERROR,
    EXECUTED
}
