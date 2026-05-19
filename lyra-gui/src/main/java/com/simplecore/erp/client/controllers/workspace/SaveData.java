

package com.simplecore.erp.client.controllers.workspace;

import com.simplecore.erp.client.utils.sound.SoundManager;
import java.awt.event.ActionListener;


/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class SaveData implements TaskPanel{

    private ActionListener task;
    public void setActionTask(ActionListener task,boolean isReady){
        SoundManager.setActivate(isReady);
        this.task = task;
    }

    @Override
    public ActionListener getOnTaskComplete() {
        return task;
    }
    
}
