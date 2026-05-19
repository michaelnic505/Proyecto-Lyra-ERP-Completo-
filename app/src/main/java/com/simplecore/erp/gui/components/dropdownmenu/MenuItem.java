package com.simplecore.erp.gui.components.dropdownmenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import com.simplecore.erp.gui.components.panels.ShadowRenderer;

public class MenuItem extends JButton {


    public float getAnimate() {
        return animate;
    }

    public void setAnimate(float animate) {
        this.animate = animate;
    }

    public int getIndex() {
        return index;
    }

    public boolean isSubMenuAble() {
        return subMenuAble;
    }

    public int getSubMenuIndex() {
        return subMenuIndex;
    }

    public int getLength() {
        return length;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setSubMenuAble(boolean subMenuAble) {
        this.subMenuAble = subMenuAble;
    }

    public void setSubMenuIndex(int subMenuIndex) {
        this.subMenuIndex = subMenuIndex;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private RippleEffect rippleEffect;
    private BufferedImage shadow;
    private int shadowWidth;
    private int shadowSize = 10;
    private int index;
    private boolean subMenuAble;
    private float animate;

    private int subMenuIndex;
    private int length;
    
    
    //Nombre del boton, index del boton y si tiene menu
    public MenuItem(String name, int index, boolean subMenuAble) {
        
        super(name);
        this.index = index;
        this.subMenuAble = subMenuAble;
        
        setContentAreaFilled(false);
        setForeground(new Color(81,81,81));
        setFont(new Font("Roboto Ligth",Font.BOLD,13));
        setHorizontalAlignment(SwingUtilities.LEFT);
        setBorder(new EmptyBorder(9, 10, 9, 10));
        setIconTextGap(10);
       
        rippleEffect = new RippleEffect(this);
        rippleEffect.setRippleColor(new Color(43,87,154));//color del efecto al dar click

        addMouseListener(menuItem);

    }
    
        MouseAdapter menuItem = new MouseAdapter() {
        
        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(new Color(37, 150, 190));
            setForeground(new Color(0, 0, 0));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(new Color(238, 244, 254));
            setForeground(new Color(81, 81, 81));
        }

    };

    private void createShadowImage() {

        int width = getWidth();
        int height = 5;
        
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.BLACK);
        g2.fill(new Rectangle2D.Double(0, 0, width, height));
        shadow = new ShadowRenderer(shadowSize, 0.3f, Color.BLACK).createShadow(img);
        g2.dispose();

    }


    
    public void initSubMenu(int subMenuIndex, int length) {
       
        this.setSubMenuIndex(subMenuIndex);
        this.setLength(length);
        
        setBorder(new EmptyBorder(9, 33, 9, 10));
        setBackground(new Color(246,250,253));
        setOpaque(true);
        
    }

    @Override //etodo para pintar las rayitas en el sub menu
    protected void paintComponent(Graphics g) {
       
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (length != 0) {
            g2.setColor(getForeground());

            if (subMenuIndex == 1) {

                g2.drawImage(shadow, -shadowSize, -20, null);
                g2.drawLine(18, 0, 18, getHeight());
                g2.drawLine(18, getHeight() / 2, 26, getHeight() / 2);

            } else if (subMenuIndex == length - 1) {

                g2.drawImage(shadow, -shadowSize, getHeight() - 6, null);
                g2.drawLine(18, 0, 18, getHeight() / 2);
                g2.drawLine(18, getHeight() / 2, 26, getHeight() / 2);

            } else {

                g2.drawLine(18, 0, 18, getHeight());
                g2.drawLine(18, getHeight() / 2, 26, getHeight() / 2);

            }

        } else if (subMenuAble) {

            g2.setColor(getForeground());
          
            int arrowWidth = 8;
            int arrowHeight = 4;
            
            Path2D p = new Path2D.Double();
            
            p.moveTo(0, arrowHeight * animate);
            p.lineTo(arrowWidth / 2, (1f - animate) * arrowHeight);
            p.lineTo(arrowWidth, arrowHeight * animate);
            
            g2.translate(getWidth() - arrowWidth - 15, (getHeight() - arrowHeight) / 2);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2.draw(p);
        }

        g2.dispose();
        rippleEffect.reder(g, new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        createShadowImage();
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

}
