package com.simplecore.erp.client.gui.components.labels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.SimpleBeanInfo;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JButtonGradient extends JButton implements Serializable {

    private boolean over;
    private boolean pressed;

    private Color color1 = new Color(0,146,229);
    private Color color2 = new Color(0,99,155);
    private Color colorOver1 = new Color(55, 103, 191);
    private Color colorOver2 = new Color(40, 80, 160);
    private Color colorClick1 = new Color(205, 52, 52);
    private Color colorClick2 = new Color(180, 30, 30);
    private Color borderColor = new Color(20, 40, 80);

    private int radius = 10; // Mayor radio para suavizar esquinas
    private boolean showBorder = true; // Opcional, por defecto el borde está visible

    public JButtonGradient() {
        setContentAreaFilled(false);
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                over = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                over = false;
                repaint();
            }
        });
    }

    // Métodos para NetBeans (JavaBeans Properties)
    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
        repaint();
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
        repaint();
    }

    public Color getColorOver1() {
        return colorOver1;
    }

    public void setColorOver1(Color colorOver1) {
        this.colorOver1 = colorOver1;
        repaint();
    }

    public Color getColorOver2() {
        return colorOver2;
    }

    public void setColorOver2(Color colorOver2) {
        this.colorOver2 = colorOver2;
        repaint();
    }

    public Color getColorClick1() {
        return colorClick1;
    }

    public void setColorClick1(Color colorClick1) {
        this.colorClick1 = colorClick1;
        repaint();
    }

    public Color getColorClick2() {
        return colorClick2;
    }

    public void setColorClick2(Color colorClick2) {
        this.colorClick2 = colorClick2;
        repaint();
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    // Método para mostrar/ocultar el borde
    public boolean isShowBorder() {
        return showBorder;
    }

    public void setShowBorder(boolean showBorder) {
        this.showBorder = showBorder;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Si showBorder es true, dibuja el borde
        if (showBorder) {
            g2d.setColor(borderColor);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        }

        // Determinar los colores del degradado según el estado del botón
        Color startColor, endColor;
        if (pressed) {
            startColor = colorClick1;
            endColor = colorClick2;
        } else if (over) {
            startColor = colorOver1;
            endColor = colorOver2;
        } else {
            startColor = color1;
            endColor = color2;
        }

        // Dibujar degradado vertical
        GradientPaint gradient = new GradientPaint(0, 2, startColor, 0, getHeight() - 2, endColor);
        g2d.setPaint(gradient);
        g2d.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);

        g2d.dispose();
        super.paintComponent(g);
    }
    
        @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Map<RenderingHints.Key, Object> Hints = new HashMap();
        Hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        Hints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        Hints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

        RenderingHints rh = new RenderingHints(Hints);
        g2.setRenderingHints(rh);

        super.paint(g);
    }

    // Se define un BeanInfo para mejorar la integración con NetBeans
    public static class JButtonGradientBeanInfo extends SimpleBeanInfo {
        @Override
        public BeanInfo[] getAdditionalBeanInfo() {
            try {
                return new BeanInfo[]{Introspector.getBeanInfo(JButton.class)};
            } catch (Exception e) {
                return null;
            }
        }
    }
}

