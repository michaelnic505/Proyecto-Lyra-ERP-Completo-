package com.simplecore.erp.client.gui.login;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

//  Esta clase se encarga de darle el color a las barras superior e inferior que decoran la ventana principal
public class LoginGradientPanel extends JPanel{
    
    
    Color color1;
    Color color2;

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
/*
        Leer_Tema leer = new Leer_Tema();
        leer.Leer_ArchivoTema();
        String tema = leer.getTema();
        
        if (tema.equals(Preferencias.FlatMacDarkLaf.getTema())) {*/
            color1 = Color.decode("#1e1e1e");
            color2 = Color.decode("#063970");
     /*   }else if(tema.equals(Preferencias.FlatMacLightLaf.getTema())){
            color1 = Color.decode("#ffffff");
            color2 = Color.decode("#e0e0e0");
        }*/

        GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

    }


}
