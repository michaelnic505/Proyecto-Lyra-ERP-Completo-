
package com.simplecore.erp.shared.exceptions;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountNumberLimitExceededException extends Exception {
    public AccountNumberLimitExceededException(String message) {
        super(message);
    }
}

