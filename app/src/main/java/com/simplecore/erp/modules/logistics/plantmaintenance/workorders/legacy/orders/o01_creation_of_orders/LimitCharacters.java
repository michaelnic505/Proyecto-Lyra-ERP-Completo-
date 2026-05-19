
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.text.JTextComponent;

/**
 *
 * @author user
 */
public class LimitCharacters {
    
    public static void limitsComponent(JTextComponent comp, int limit){
        
        int character = limit;
        
        comp.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                
                if(comp.getDocument().getLength()==character){
                    e.consume();
                }

            }

            
        });
        
    }
    
}
