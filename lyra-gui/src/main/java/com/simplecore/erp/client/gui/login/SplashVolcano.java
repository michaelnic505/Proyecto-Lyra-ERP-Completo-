
package com.simplecore.erp.client.gui.login;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class SplashVolcano extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // ¡Siempre llama primero al método de la clase padre!
        
        // Carga la imagen JPG desde recursos
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/splash/volcano.jpg"));
        Image img = icon.getImage();
        
        // Dibuja la imagen en el panel (ajusta el tamaño si es necesario)
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}