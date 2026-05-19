
package com.simplecore.erp.gui.components.labels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;

public class JButtonCustom extends JButton {

    public boolean isOver() {
        return over;
    }

    public Color getColor() {
        return color;
    }

    public Color getColorOver() {
        return colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public JButtonCustom() {
        
        setBackground(new Color(40,75,138));
        color = new Color(40,75,138);
        colorOver = new Color(55,103,191);
        colorClick = new Color(205,52,52);
        borderColor = new Color(31,58,107);
        setContentAreaFilled(false);
        setSize(20, 20);      
        
        addMouseListener(new MouseAdapter(){

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            
                if(over){
                    setBackground(colorOver);
                }else{
                    setBackground(color);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorOver);
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                over = false;
            }

        });

        
    }
    
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    int radius = 5;
    
    

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

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(borderColor);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(),radius,radius);
        g2d.setColor(getBackground());
        
        g2d.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, radius, radius);
        
        super.paintComponent(g);
    }

    
    

    
    
}
