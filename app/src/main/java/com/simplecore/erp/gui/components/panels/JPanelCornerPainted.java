package com.simplecore.erp.gui.components.panels;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

public class JPanelCornerPainted extends JPanel {

    public JPanelCornerPainted() {
        setBackground(new Color(238,244,254));

    }
    
    

    private Color color1 = new Color(0, 146, 229);
    private Color color2 = new Color(0, 146, 229);
    
    private int alto = 10;
    private int ancho = 10;

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        Map<RenderingHints.Key, Object> Hints = new HashMap();
        Hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RenderingHints rh = new RenderingHints(Hints);
        g2.setRenderingHints(rh);
        
        g2.setPaint(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        

        GradientPaint gp = new GradientPaint(0, 0, getColor1(), 0, getHeight(), getColor2());
        g2.setPaint(gp);

        Line2D lineaInf = new Line2D.Double(getWidth() - getAncho(), getHeight(), getWidth(), getHeight()-getAlto());
        Line2D lineaIncl = new Line2D.Double(getWidth(), getHeight()-getAlto(), getWidth(), getHeight());

        Line2D[] lineas1 = new Line2D[]{lineaIncl, lineaInf};

        Shape l = createSingleShape(lineas1);
        g2.fill(l);
        
        
        
    }

    private Path2D createSingleShape(Line2D[] lines) {
        Path2D path = new Path2D.Float();

        for (Line2D line : lines) {
            path.append(line, path.getCurrentPoint() != null);
        }

        path.closePath();

        return path;
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

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

}
