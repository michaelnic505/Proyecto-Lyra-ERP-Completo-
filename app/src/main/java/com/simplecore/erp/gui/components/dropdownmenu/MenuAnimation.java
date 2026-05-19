
package com.simplecore.erp.gui.components.dropdownmenu;

import java.awt.Component;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;




public class MenuAnimation {

    public static void showMenu(Component comp, MenuItem item, MigLayout layout, boolean show){
        
        int height = comp.getPreferredSize().height;
        Animator animator = new Animator(300, new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                
                float f = show ? fraction : 1f - fraction;
                layout.setComponentConstraints(comp, "h "+ height * f + "!");
                item.setAnimate(f);
                comp.revalidate();
                comp.repaint();
            }
            
        });
        
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        animator.start();
    }

    
}
