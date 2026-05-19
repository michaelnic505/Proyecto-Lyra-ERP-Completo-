
package com.simplecore.erp.client.gui.components.labels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

public class JLabelHQUnderlined extends JLabel {

    public JLabelHQUnderlined() {        
        setFont(new Font("Roboto Light",Font.BOLD,12));
    }
    
    
    private Color colorRayado = new Color(176,176,176);

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        Map<RenderingHints.Key, Object> Hints = new HashMap();
        Hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        RenderingHints rh = new RenderingHints(Hints);
        g2.setRenderingHints(rh);
        int fontSize = g2.getFont().getSize();

        int pX = getWidth();
        int pY = getHeight() / 2 + (fontSize);

        
        g2.setPaint(getColorRayado());   
        g2.setStroke(new BasicStroke(1.7f));        
        Shape linea = new Line2D.Double(0, pY, pX, pY);
        g2.draw(linea);
        

        
        super.paintComponent(g);
    }

    public Color getColorRayado() {
        return colorRayado;
    }

    public void setColorRayado(Color colorRayado) {
        this.colorRayado = colorRayado;
    }
    
    

}
