package com.simplecore.erp.client.gui.components.panels;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class JPanelRoundedGradient extends JPanel {

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

    public int getEsquinaSupIzquierda() {
        return esquinaSupIzquierda;
    }

    public int getEsquinaSupDerecha() {
        return esquinaSupDerecha;
    }

    public int getEsquinaInfIzquierda() {
        return esquinaInfIzquierda;
    }

    public int getEsquinaInfDerecha() {
        return esquinaInfDerecha;
    }

    public void setEsquinaSupIzquierda(int esquinaSupIzquierda) {
        this.esquinaSupIzquierda = esquinaSupIzquierda;
        repaint();
    }

    public void setEsquinaSupDerecha(int esquinaSupDerecha) {
        this.esquinaSupDerecha = esquinaSupDerecha;
        repaint();
    }

    public void setEsquinaInfIzquierda(int esquinaInfIzquierda) {
        this.esquinaInfIzquierda = esquinaInfIzquierda;
        repaint();
    }

    public void setEsquinaInfDerecha(int esquinaInfDerecha) {
        this.esquinaInfDerecha = esquinaInfDerecha;
        repaint();
    }

    private Shape redondearEsquinaSupDerecha() {

        int ancho = getWidth();
        int alto = getHeight();
        int redondeadoX = Math.min(ancho, esquinaSupDerecha);
        int redondeadoY = Math.min(alto, esquinaSupDerecha);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, ancho, alto, redondeadoX, redondeadoY));
        area.add(new Area(new Rectangle2D.Double(0, 0, ancho - redondeadoX / 2, alto)));
        area.add(new Area(new Rectangle2D.Double(0, redondeadoY / 2, ancho, alto - redondeadoY / 2)));

        return area;
    }

    private Shape redondearEsquinaSupIzquierda() {

        int ancho = getWidth();
        int alto = getHeight();
        int redondeadoX = Math.min(ancho, esquinaSupIzquierda);
        int redondeadoY = Math.min(alto, esquinaSupIzquierda);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, ancho, alto, redondeadoX, redondeadoY));
        area.add(new Area(new Rectangle2D.Double(redondeadoX / 2, 0, ancho - redondeadoX / 2, alto)));
        area.add(new Area(new Rectangle2D.Double(0, redondeadoY / 2, ancho, alto - redondeadoY / 2)));

        return area;
    }

    private Shape redondearEsquinaInfIzquierda() {

        int ancho = getWidth();
        int alto = getHeight();
        int redondeadoX = Math.min(ancho, esquinaInfIzquierda);
        int redondeadoY = Math.min(alto, esquinaInfIzquierda);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, ancho, alto, redondeadoX, redondeadoY));
        area.add(new Area(new Rectangle2D.Double(redondeadoX / 2, 0, ancho - redondeadoX / 2, alto)));
        area.add(new Area(new Rectangle2D.Double(0, 0, ancho, alto - redondeadoY / 2)));

        return area;
    }

    private Shape redondearEsquinaInfDerecha() {

        int ancho = getWidth();
        int alto = getHeight();
        int redondeadoX = Math.min(ancho, esquinaInfDerecha);
        int redondeadoY = Math.min(alto, esquinaInfDerecha);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, ancho, alto, redondeadoX, redondeadoY));
        area.add(new Area(new Rectangle2D.Double(0, 0, ancho - redondeadoX / 2, alto)));
        area.add(new Area(new Rectangle2D.Double(0, 0, ancho, alto - redondeadoY / 2)));

        return area;
    }

    private int esquinaSupIzquierda = 0;
    private int esquinaSupDerecha = 0;
    private int esquinaInfIzquierda = 0;
    private int esquinaInfDerecha = 0;

    private Color color1 = new Color(30,130,166);
    private Color color2 = new Color(10,48,80);

    public JPanelRoundedGradient() {
        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());

        Area area = new Area(redondearEsquinaSupIzquierda());
        if (esquinaSupDerecha > 0) {
            area.intersect(new Area(redondearEsquinaSupDerecha()));
        }
        if (esquinaSupIzquierda > 0) {
            area.intersect(new Area(redondearEsquinaSupIzquierda()));
        }
        if (esquinaInfDerecha > 0) {
            area.intersect(new Area(redondearEsquinaInfDerecha()));
        }
        if (esquinaInfIzquierda > 0) {
            area.intersect(new Area(redondearEsquinaInfIzquierda()));
        }

        int width = getWidth();
        int height = getHeight();

        GradientPaint gp = new GradientPaint(0, 0, getColor1(), 0, height, getColor2());
        g2d.setPaint(gp);
        g2d.fill(area);
        g2d.dispose();
        super.paintComponent(g);
    }

}
