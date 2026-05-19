
package com.simplecore.erp.gui.components.labels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

public class JLabelHQFraming extends JLabel {

    public Color getColorBordes() {
        return colorBordes;
    }

    public void setColorBordes(Color colorBordes) {
        this.colorBordes = colorBordes;
    }

    public Color getColorRelleno() {
        return colorRelleno;
    }

    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }
    
    private Color colorRelleno = new Color(37, 150, 190);
    private Color colorBordes = new Color(0,0,0);

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        Map<RenderingHints.Key, Object> Hints = new HashMap();
        Hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        RenderingHints rh = new RenderingHints(Hints);
        g2.setRenderingHints(rh);
        int fontSize = g2.getFont().getSize();
        int pxText = g.getFontMetrics().stringWidth(getText()) + 3;
        int pX = getWidth();
        int pY = getHeight() / 2 + fontSize-2;

        g2.setColor(colorRelleno);
        

        Line2D lineaInf = new Line2D.Double(pxText + 22, 0, pxText-3, 0);
        Line2D lineaIncl = new Line2D.Double(pxText, pY, pxText + 22, 0);                       

        Line2D[] lineas1 = new Line2D[]{lineaIncl,lineaInf}; 

        Shape l = createSingleShape(lineas1);
        g2.fill(l);
        
        Shape rect = new Rectangle2D.Double(0, 0, pxText, pY);
        g2.fill(rect);
        
        
        g2.setPaint(colorBordes);
        Shape lineaInf1 = new Line2D.Double(0, pY, pxText, pY);
        g2.draw(lineaInf1);
        Shape lineaIncl1 = new Line2D.Double(pxText, pY, pxText + 22, 0);
        g2.draw(lineaIncl1);
        
        super.paintComponent(g);
    }

    
    
    
    
    private Path2D createSingleShape(Line2D[] lines) {
        Path2D path = new Path2D.Float();
        
        for (Line2D line : lines) {
            path.append(line, path.getCurrentPoint() != null);
        }

        path.closePath();

        return path;
    }


}
