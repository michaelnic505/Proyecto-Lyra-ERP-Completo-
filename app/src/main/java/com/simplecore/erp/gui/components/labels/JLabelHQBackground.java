
package com.simplecore.erp.gui.components.labels;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

public class JLabelHQBackground extends JLabel {

    public JLabelHQBackground() {
    
        setText(null);
    }
    
    
    

    public Color getColorRelleno1() {
        return colorRelleno1;
    }

    public Color getColorRelleno2() {
        return colorRelleno2;
    }

    public void setFillColor1(Color colorRelleno1) {
        this.colorRelleno1 = colorRelleno1;
    }

    public void setFillColor2(Color colorRelleno2) {
        this.colorRelleno2 = colorRelleno2;
    }

    
    private Color colorRelleno1 = new Color(37, 150, 190);
    private Color colorRelleno2 = new Color(37, 150, 190);

    @Override
    public void paintComponent(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g.create();

        Map<RenderingHints.Key, Object> Hints = new HashMap();
        Hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        RenderingHints rh = new RenderingHints(Hints);
        g2.setRenderingHints(rh);
 
        if (getText()!=null) {
            
            int pxText = g.getFontMetrics().stringWidth(getText()) + 30;

            int pX = getWidth();
            int pY = getHeight();

            GradientPaint gp = new GradientPaint(0, 0, getColorRelleno1(), 0, getHeight(), getColorRelleno2());
            g2.setPaint(gp);

            Shape r2 = new Rectangle2D.Double(0, 0, pxText, pY + pxText);
            g2.fill(r2);
        }

        super.paintComponent(g);
    }


}
