package com.simplecore.erp.client.gui.components.panels;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class PanelGradient3 extends JPanel {

    /**
     * @return the color1
     */
    public Color getColor1() {
        return color1;
    }

    /**
     * @param color1 the color1 to set
     */
    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    /**
     * @return the color2
     */
    public Color getColor2() {
        return color2;
    }

    /**
     * @param color2 the color2 to set
     */
    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    /**
     * @return the color3
     */
    public Color getColor3() {
        return color3;
    }

    /**
     * @param color3 the color3 to set
     */
    public void setColor3(Color color3) {
        this.color3 = color3;
    }
    
    private Color color1 = new Color(181,203,231);
    private Color color2 = new Color(214,227,247);
    private Color color3 = new Color(181,203,231);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Obtener el tamaño del panel
        int width = getWidth();
        int height = getHeight();

        // Crear un Graphics2D para dibujar el degradado
        Graphics2D g2d = (Graphics2D) g;

        // Crear el primer degradado (de color1 a color2)
        GradientPaint gradient1 = new GradientPaint(
                0, 0, getColor1(),
                0, height / 2, getColor2());

        // Crear el segundo degradado (de color2 a color3)
        GradientPaint gradient2 = new GradientPaint(
                0, height / 2, getColor2(),
                0, height, getColor3());

        // Dibujar el primer degradado (mitad superior)
        g2d.setPaint(gradient1);
        g2d.fillRect(0, 0, width, height / 2);

        // Dibujar el segundo degradado (mitad inferior)
        g2d.setPaint(gradient2);
        g2d.fillRect(0, height / 2, width, height / 2);
    }
}
