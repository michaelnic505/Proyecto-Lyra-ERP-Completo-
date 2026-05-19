
package com.simplecore.erp.client.services.login;

import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.responses.types.LogoutResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class LogoutController {

    public static void logoutIfNecessary(Object response) throws InterruptedException {
        if (response instanceof LogoutResponse logout) {
            if (logout.isForceLogout()) {
                new SystemMessages().showWarningMsg(AppMessages.msg(AppMessages.Key.CLOSING_SESSION));
                Thread.sleep(2010);
            }
        }
    }
}
