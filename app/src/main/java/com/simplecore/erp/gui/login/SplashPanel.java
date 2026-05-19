package com.simplecore.erp.gui.login;


import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

//  Esta clase se encarga de darle el color a las barras superior e inferior que decoran la ventana principal
public class SplashPanel extends JPanel{
    

    @Override
    protected void paintComponent(Graphics g) {        
        

        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/splash/mikel_logo.png"));
        Image img = icon.getImage();

        super.paintComponent(g);
        g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(), null);
        
    }

    
}
