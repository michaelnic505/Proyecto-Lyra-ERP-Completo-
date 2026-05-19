
package com.simplecore.erp.client.controllers.servicebuttons;

import com.simplecore.erp.client.utils.sound.Sound;
import com.simplecore.erp.client.utils.sound.SoundManager;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class ButtonServices extends JButton {
    private ActionListener clickSoundListener;
    private Sound sound;

    // Constructor vacío para permitir su uso en el diseñador de NetBeans
    public ButtonServices() {
        super();
    }

    // Asignar sonido y configurar el listener
    public void setSound(Sound sound) {
        this.sound = sound;
        if (clickSoundListener != null) {
            this.removeActionListener(clickSoundListener);
        }
        if (sound != null) {
            this.clickSoundListener = e -> {
                if(isPlayable){
                    SoundManager.playSound(sound.dir());
                }
            };
            this.addActionListener(clickSoundListener);
        }else{
            this.removeActionListener(clickSoundListener);
        }
    }

    // Agregar tareas sin eliminar el sonido
    public void addTaskAction(ActionListener taskListener) {
        this.addActionListener(taskListener);
    }
    private boolean isPlayable = true;
    public void setPlayable(boolean isPlayable){
        this.isPlayable = isPlayable;
    }

    // Limpiar las acciones sin eliminar el sonido
    public void clearTaskActions() {
        for (ActionListener al : this.getActionListeners()) {
            if (al != clickSoundListener) { // No eliminar el sonido
                this.removeActionListener(al);
            }
        }
    }
}
