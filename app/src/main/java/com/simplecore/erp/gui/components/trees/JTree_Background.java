package com.simplecore.erp.gui.components.trees;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class JTree_Background extends JTree {

    public JTree_Background() {
    
        setCellRenderer(new MyTreeCellRenderer());
    }
    
    

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        for (int i = 0; i < getRowCount(); i++) {
            Object o = getPathForRow(i).getLastPathComponent();
            g2.setColor(getNodeColor(o));
            Rectangle r = getRowBounds(i);
            g2.fillRect(0, r.y, getWidth(), r.height);
        }

        g2.dispose();
        super.paintComponent(g);

    }

    static class MyTreeCellRenderer extends DefaultTreeCellRenderer {

        private final Color color = new Color(0x0, true);

        @Override
        public Color getBackgroundSelectionColor() {
            return color;
        }

        @Override
        public Color getBackgroundNonSelectionColor() {
            return color;
        }

        
        
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                boolean leaf, int row, boolean hasFocus) {
            
            JLabel l = (JLabel) super.getTreeCellRendererComponent(
                    tree, value, selected, expanded, leaf, row, hasFocus);
            
            if (getRowOfNode(value) == 2) {
                l.setForeground(Color.WHITE);
            }
            
            return l;
        }
        
    }

    
    
    public static Color getNodeColor(Object value) {
        
        switch (getRowOfNode(value)) {
            case 1:
                return Color.RED;
            case 2:
                return Color.BLUE;
            default:
                return Color.WHITE;
        }
    
    }

    public static int getRowOfNode(Object value) {
        
        if (value instanceof DefaultMutableTreeNode) {
        
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            
            return node.getPath().length;
        }
        
        return -1;
    }

}
