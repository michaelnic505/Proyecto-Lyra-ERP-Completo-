
package com.simplecore.erp.client.gui.components.trees;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

public class JTree_BackgroundCustomized extends JTree {

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

    
    

}

