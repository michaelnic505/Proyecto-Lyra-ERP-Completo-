package com.simplecore.erp.controllers.maintree;

import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.models.maintree.BusinessTransactionsEnum;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.utils.sound.Sound;
import com.simplecore.erp.utils.sound.SoundManager;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class ModulesController {

    private final SystemMessages sysMsg;
    public ModulesController() {
        this.sysMsg = new SystemMessages();
    }
      

    public void openTransaction(BusinessTransactionsEnum transaction) {

        SoundManager.playSound(Sound.OPEN.dir());
        sysMsg.showSuccessMsg(NOT.msg(NOT.ACCESS_GRANTED) +":> " +transaction.getKey());
        

//        switch (transaction) {
//            case R01:
//                
//            case C02:
//                new SystemMessages(NOT.msg(NOT.NO_PERMISSIONS),TypeMessage.WARNING).show();
//            case C03:
//                new SystemMessages(NOT.msg(NOT.NO_PERMISSIONS),TypeMessage.WARNING).show();
//        }
    }

}
