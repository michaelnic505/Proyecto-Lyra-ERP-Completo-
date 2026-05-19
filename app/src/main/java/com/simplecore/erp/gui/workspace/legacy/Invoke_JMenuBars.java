
package com.simplecore.erp.gui.workspace.legacy;

import javax.swing.JFrame;
import javax.swing.JMenuBar;


public class Invoke_JMenuBars {
    
    
    public static void setMenuBar(JFrame frame, JMenuBar menuBar1, JMenuBar menuBar2){
        
        
        if(menuBar1!=null){
            frame.remove(menuBar1);
        }
        
        frame.setJMenuBar(menuBar2);
        frame.revalidate();
        frame.repaint();
        
    }
}
