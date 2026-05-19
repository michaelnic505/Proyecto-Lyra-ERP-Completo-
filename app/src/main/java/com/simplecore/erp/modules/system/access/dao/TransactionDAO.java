package com.simplecore.erp.modules.system.access.dao;

import com.simplecore.erp.modules.system.access.exceptions.DatabaseException;
import com.simplecore.erp.modules.system.access.models.Transaction;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author user
 */
public interface TransactionDAO {

    /**
     * Retrieves a transaction by its ID.
     *
     * @param transactionId The ID of the transaction.
     * @return The Transaction object if found, otherwise null.
     * @throws com.simplecore.erp.modules.system.access.exceptions.DatabaseException
     */
    Optional<Transaction> getTransactionById(String transactionId)throws DatabaseException;

    /**
     * Checks if a user has access to a specific transaction.This is determined
 by verifying if the user's role includes the transaction.
     *
     * @param roleId The ID of the user.
     * @param transactionId The ID of the transaction.
     * @return True if the user has access, otherwise false.
     * @throws com.simplecore.erp.modules.system.access.exceptions.DatabaseException
     */
    boolean hasRoleAccessToTransaction(String roleId, String transactionId)throws DatabaseException;

}

