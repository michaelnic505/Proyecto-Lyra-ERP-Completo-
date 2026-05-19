package com.simplecore.erp.gui.components.trees;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class JTreeRenderizer extends DefaultTreeCellRenderer{

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        if(leaf){
            tree.setBackground(new Color(202,216,237));
        }else if(expanded){
            tree.setBackground(new Color(238,244,254));
        }else{
            tree.setBackground(new Color(255,255,255));
        }
        
        Component comp = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        
        return comp;
    }

    

}
