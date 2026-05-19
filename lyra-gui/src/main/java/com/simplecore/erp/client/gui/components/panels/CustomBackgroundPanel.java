package com.simplecore.erp.client.gui.components.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.CubicCurve2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CustomBackgroundPanel extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Get panel dimensions
        int width = getWidth();
        int height = getHeight();

        // Paint gradient background
        GradientPaint gradient = new GradientPaint(0, 0, new Color(72, 144, 255), 0, height, new Color(20, 96, 190));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);

        // Draw abstract curves
        g2d.setColor(new Color(255, 255, 255, 150)); // Semi-transparent white
        g2d.setStroke(new BasicStroke(2f)); // Thicker lines

        // Draw curved lines
        drawCurve(g2d, width, height, 0.1f);
        drawCurve(g2d, width, height, 0.3f);
        drawCurve(g2d, width, height, 0.5f);

        // Draw leaf-like shapes
        g2d.setStroke(new BasicStroke(1f));
        drawLeafShape(g2d, width / 2, height / 2);

        // Add additional abstract elements as needed
    }

    // Method to draw smooth curves
    private void drawCurve(Graphics2D g2d, int width, int height, float offset) {
        int x1 = (int) (width * 0.2);
        int y1 = (int) (height * offset);
        int x2 = (int) (width * 0.8);
        int y2 = (int) (height * (offset + 0.2));

        int ctrlX1 = (int) (width * 0.5);
        int ctrlY1 = (int) (height * (offset - 0.1));

        int ctrlX2 = (int) (width * 0.5);
        int ctrlY2 = (int) (height * (offset + 0.3));

        // Draw cubic Bezier curve
        g2d.draw(new CubicCurve2D.Float(x1, y1, ctrlX1, ctrlY1, ctrlX2, ctrlY2, x2, y2));
    }

    // Method to draw a simple leaf shape
    private void drawLeafShape(Graphics2D g2d, int x, int y) {
        Polygon leaf = new Polygon();
        leaf.addPoint(x, y);
        leaf.addPoint(x - 10, y + 30);
        leaf.addPoint(x + 10, y + 30);
        g2d.fill(leaf);
    }

    // Main method to test the panel
    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Background Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new CustomBackgroundPanel());
        frame.setVisible(true);
    }
    
}
