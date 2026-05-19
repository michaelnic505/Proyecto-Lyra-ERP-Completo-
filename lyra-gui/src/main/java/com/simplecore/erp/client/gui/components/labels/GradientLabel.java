package com.simplecore.erp.client.gui.components.labels;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

public class GradientLabel extends JLabel {

    private Color startColor = Color.BLUE; // Default start color
    private Color endColor = Color.RED;   // Default end color

    // Default constructor required for JavaBeans
    public GradientLabel() {
        super("Gradient Label");
        setOpaque(false); // Make background transparent
    }

    // Constructor to initialize with text
    public GradientLabel(String text) {
        super(text);
        setOpaque(false); // Make background transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Cast Graphics to Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        // Create gradient paint for the text (vertical gradient: top to bottom)
        int height = getHeight();
        GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, height, endColor);
        g2d.setPaint(gradient);

        // Draw the text
        FontMetrics fm = g2d.getFontMetrics(getFont());
        int x = 0; // Horizontal alignment
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent(); // Vertical centering
        g2d.drawString(getText(), x, y);

        // Avoid default painting of JLabel text
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

    // Getter for startColor
    public Color getStartColor() {
        return startColor;
    }

    // Setter for startColor
    public void setStartColor(Color startColor) {
        this.startColor = startColor;
        repaint(); // Redraw the component with the new color
    }

    // Getter for endColor
    public Color getEndColor() {
        return endColor;
    }

    // Setter for endColor
    public void setEndColor(Color endColor) {
        this.endColor = endColor;
        repaint(); // Redraw the component with the new color
    }

    // Ensure the class works with JavaBeans introspection
    static {
        try {
            BeanInfo info = Introspector.getBeanInfo(GradientLabel.class);
            for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                if ("startColor".equals(pd.getName()) || "endColor".equals(pd.getName())) {
                    pd.setValue("transient", Boolean.FALSE);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
