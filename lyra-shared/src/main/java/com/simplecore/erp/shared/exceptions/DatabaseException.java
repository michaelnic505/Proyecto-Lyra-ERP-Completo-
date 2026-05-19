package com.simplecore.erp.shared.exceptions;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class DatabaseException extends Exception{
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
        public DatabaseException(String message) {
        super(message);
    }
}
