
package com.simplecore.erp.gui.components.searchbox;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class PanelNegroButtonRedondeado extends JPanel {

    public int getAjuste() {
        return ajuste;
    }

    public void setAjuste(int ajuste) {
        this.ajuste = ajuste;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    
    private Color color1 = new Color(0, 146, 229);
    private Color color2 = new Color(0, 146, 229);
    
    private int ajuste = 0;
    
    @Override
    protected void paintComponent(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        
                GradientPaint gp = new GradientPaint(0, 0, getColor1(), 0, getHeight(), getColor2());
        g2.setPaint(gp);
        
        g2.fillRect(0, 0, getWidth()/2, getHeight());
        g2.fillArc(getAjuste(), 0  , getWidth(), getHeight(), 0 , 360);
        
    }
    
    
}
