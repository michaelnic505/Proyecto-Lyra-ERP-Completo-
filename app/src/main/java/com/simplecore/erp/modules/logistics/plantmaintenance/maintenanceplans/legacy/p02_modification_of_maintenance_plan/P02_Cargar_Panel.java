package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceplans.legacy.p02_modification_of_maintenance_plan;

import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


public class P02_Cargar_Panel {
    
    public static void cargar_en_Principal(JPanel panelHijo, JPanel panelPrincipal){     
        
        try {
            
            panelHijo.setSize(panelPrincipal.getWidth() - 5, panelPrincipal.getHeight() - 5);
            panelHijo.setLocation(5, 5);
            
            panelPrincipal.removeAll();
            
            
            Thread.sleep(500);
            
            panelPrincipal.add(panelHijo, BorderLayout.CENTER);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(P02_Cargar_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
