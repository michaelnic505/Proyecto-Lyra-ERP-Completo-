
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.TransactionService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.TransactionRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TransactionHandler implements RequestHandler<TransactionRequest>{

    @Override
    public Object handle(TransactionRequest request) {
        return TransactionService.checkTransactionAccess(request);
    }

}
