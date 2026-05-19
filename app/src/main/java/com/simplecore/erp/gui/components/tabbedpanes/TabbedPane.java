package com.simplecore.erp.gui.components.tabbedpanes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class TabbedPane extends JTabbedPane {

    @Override
    public void updateUI() {

        super.updateUI();
        UIManager.put("TabbedPane.highlight", Color.GRAY);
        setUI(new LyraTabbedPane());
    }

    public Color getTabBackgroundColor() {
        return tabBackgroundColor;
    }

    public Color getTabBorderColor() {
        return tabBorderColor;
    }

    public int getAjuste() {
        return ajuste;
    }

    public void setTabBackgroundColor(Color tabBackgroundColor) {
        this.tabBackgroundColor = tabBackgroundColor;
    }

    public void setTabBorderColor(Color tabBorderColor) {
        this.tabBorderColor = tabBorderColor;
    }

    public void setAjuste(int ajuste) {
        this.ajuste = ajuste;
    }

    public Color getSelectedTabColor() {
        return selectedTabColor;
    }

    public void setSelectedTabColor(Color selectedTabColor) {
        this.selectedTabColor = selectedTabColor;
    }

    private Color tabBackgroundColor = new Color(42, 103, 158);
    private Color tabBorderColor = new Color(0, 62, 94);
    private Color selectedTabColor = new Color(52, 60, 148);

    private int ajuste = 13;

    private class LyraTabbedPane extends BasicTabbedPaneUI {

        @Override
        protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
            int tabCount = tabPane.getTabCount();

            Rectangle iconRect = new Rectangle();
            Rectangle textRect = new Rectangle();
            Rectangle clipRect = g.getClipBounds();

            for (int i = runCount - 1; i >= 0; i--) {
                int start = tabRuns[i];

                int next = tabRuns[(i + 1) % runCount];

                int end = (next - 1 + tabCount) % tabCount;

                for (int j = end; j >= start; j--) {
                    if (j != selectedIndex && rects[j].intersects(clipRect)) {
                        paintTab(g, tabPlacement, rects, j, iconRect, textRect);
                    }
                }
            }
            if (selectedIndex >= 0 && rects[selectedIndex].intersects(clipRect)) {
                paintTab(g, tabPlacement, rects, selectedIndex, iconRect, textRect);
            }
        }

        @Override
        protected void paintTabBorder(
                Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            // Do nothing

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //int textShiftOffset = isSelected ? 0 : 1;
            int textShiftOffset = 0;

            GeneralPath trapezoid = new GeneralPath();
            trapezoid.moveTo(x, y + h - 10);
            trapezoid.lineTo(x + getAjuste(), y + textShiftOffset);
            trapezoid.lineTo(x + w + 5, y + textShiftOffset);
            trapezoid.lineTo(x + w + 5, y + h);
            trapezoid.lineTo(x, y + h);

            g2.setColor(tabBorderColor);
            g2.draw(trapezoid);

        }

        @Override
        protected void paintFocusIndicator(
                Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex,
                Rectangle iconRect, Rectangle textRect, boolean isSelected) {
            // Do nothing

        }

        @Override
        protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //int textShiftOffset = isSelected ? 0 : 1;
            int textShiftOffset = 0;

            Rectangle clipRect = g2.getClipBounds();
            clipRect.grow(getAjuste() + 1, 0);
            g2.setClip(clipRect);

            GeneralPath trapezoid = new GeneralPath();

            trapezoid.moveTo(x, y + h - 10);
            trapezoid.lineTo(x + getAjuste(), y + textShiftOffset);
            trapezoid.lineTo(x + w + 5, y + textShiftOffset);
            trapezoid.lineTo(x + w + 5, y + h);
            trapezoid.lineTo(x, y + h);

            g2.setColor(isSelected ? getSelectedTabColor() : getTabBackgroundColor());
            g2.fill(trapezoid);

            g2.dispose();
        }
    }
}
