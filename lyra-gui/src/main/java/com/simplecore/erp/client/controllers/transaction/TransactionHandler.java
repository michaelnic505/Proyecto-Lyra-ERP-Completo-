
package com.simplecore.erp.client.controllers.transaction;

import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author user
 */
public interface TransactionHandler {
     void initialize(String transactionCode,ActiveSession session, ObjectOutputStream output, ObjectInputStream input);
}
