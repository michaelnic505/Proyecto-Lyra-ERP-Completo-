
package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.TransactionManager;
import com.simplecore.erp.shared.requests.types.TransactionRequest;
import java.sql.Connection;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class TransactionService {
    public static Object checkTransactionAccess(TransactionRequest request) {
        return TransactionManager.checkTransactionAccess(request);
    }
    public static String getRoleByUserId(Connection conn,int userId){
        return TransactionManager.getRoleByUserId(conn, userId);
    }
}
