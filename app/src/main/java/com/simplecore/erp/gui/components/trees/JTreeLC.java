/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.simplecore.erp.gui.components.trees;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.Serializable;

public class JTreeLC extends JTree implements Serializable {

    private Color baseColor = new Color(238,244,251); // Color base editable
    private static final float ATTENUATION_FACTOR = 0.92f; // Factor de aclarado (ajustado para suavizar la transición)
    private Color borderColor = new Color(158,179,197); // Color del borde
    private int borderThickness = 1; // Grosor del borde

    // Constructor sin argumentos para uso en editores visuales
    public JTreeLC() {
        this(new DefaultTreeModel(new DefaultMutableTreeNode("Root"))); // Modelo predeterminado
    }

    public JTreeLC(TreeModel model) {
        super(model);
        setOpaque(false); // Evita que el fondo blanco cubra los colores
        setCellRenderer(new CustomTreeCellRenderer());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Pintar el fondo completo con el color base
        g2d.setColor(baseColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Pintar los fondos de los nodos y calcular los bordes de los grupos
        int rowCount = getRowCount();
        Rectangle groupBounds = null; // Área que abarca un grupo de nodos desplegados
        int lastDepth = -1; // Profundidad del último nodo procesado

        for (int row = 0; row < rowCount; row++) {
            TreePath path = getPathForRow(row);
            if (path != null) {
                int depth = path.getPathCount() - 1; // Profundidad del nodo actual

                // Si cambia la profundidad, dibujar el borde del grupo anterior
                if (depth != lastDepth && groupBounds != null) {
                    drawGroupBorder(g2d, groupBounds); // Dibujar borde del grupo
                    groupBounds = null; // Reiniciar el área del grupo
                }

                // Pintar el fondo del nodo actual
                g2d.setColor(getColorForLevel(depth)); // Color según el nivel de profundidad
                Rectangle rowBounds = getRowBounds(row);
                if (rowBounds != null) {
                    g2d.fillRect(0, rowBounds.y, getWidth(), rowBounds.height);

                    // Actualizar el área del grupo actual
                    if (groupBounds == null) {
                        groupBounds = new Rectangle(0, rowBounds.y, getWidth(), rowBounds.height);
                    } else {
                        groupBounds.add(new Rectangle(0, rowBounds.y, getWidth(), rowBounds.height));
                    }
                }

                lastDepth = depth; // Actualizar la profundidad del último nodo procesado
            }
        }

        // Dibujar el borde del último grupo si existe
        if (groupBounds != null) {
            drawGroupBorder(g2d, groupBounds);
        }

        g2d.dispose();
        super.paintComponent(g); // Pintar los nodos y el texto
    }

    private void drawGroupBorder(Graphics2D g2d, Rectangle groupBounds) {
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderThickness)); // Grosor del borde
        g2d.drawRect(
            0, // El borde comienza desde el borde izquierdo del JTree
            groupBounds.y - borderThickness, // Ajustar la posición Y para incluir el grosor del borde
            getWidth(), // El borde ocupa todo el ancho del JTree
            groupBounds.height + 2 * borderThickness // Ajustar la altura para incluir el grosor del borde
        );
    }

private Color getColorForLevel(int level) {
    float factor = (float) Math.pow(1 / ATTENUATION_FACTOR, level); // Invertir el factor de atenuación
    int r = Math.min((int) (baseColor.getRed() * factor), 255); // Limitar el aclarado al valor máximo de 255
    int g = Math.min((int) (baseColor.getGreen() * factor), 255); // Limitar el aclarado al valor máximo de 255
    int b = Math.min((int) (baseColor.getBlue() * factor), 255); // Limitar el aclarado al valor máximo de 255
    return new Color(r, g, b);
}


    public Color getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
        repaint(); // Vuelve a pintar el componente cuando se cambia el color base
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint(); // Vuelve a pintar el componente cuando se cambia el color del borde
    }

    public int getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
        repaint(); // Vuelve a pintar el componente cuando se cambia el grosor del borde
    }

    private static class CustomTreeCellRenderer extends DefaultTreeCellRenderer {

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            setOpaque(false); // Hace que el fondo del renderizador sea transparente
            return this;
        }
    }
}