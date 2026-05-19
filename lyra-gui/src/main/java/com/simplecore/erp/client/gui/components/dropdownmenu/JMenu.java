package com.simplecore.erp.client.gui.components.dropdownmenu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class JMenu extends JComponent {


    public MenuEvent getEvent() {
        return event;
    }
    
    public void setEvent(MenuEvent event) {
        this.event = event;
    }
   
    
    private MenuEvent event;
    private MigLayout layout;
    
    private String[][] menuMatrix;
    
    public JMenu() {
        init();
    }
    
    private void init() {
       
        layout = new MigLayout("wrap 1, fillx, gapy 0, inset 0", "fill");
        setLayout(layout);
        setOpaque(true);
        
    }

    
    
    public void setMenuMatrix(String[][] menuMatrix) {
    
        this.menuMatrix = menuMatrix;
        
        for (int i = 0; i < menuMatrix.length; i++) {
            addMenu(menuMatrix[i][0], i);
        }
    
    }
    
    
    
    //aqui se agregan los menus principales
    private void addMenu(String menuName, int index) {

        
        int length = menuMatrix[index].length;

        MenuItem item = new MenuItem(menuName, index, length > 1);
        Icon icon = IconosCarpetaCerrada();
        item.setIcon(icon);

        
        item.addActionListener((ActionEvent e) -> {
            
            
            if (length > 1) {
                
                if (!item.isSelected()) {
                    
                    item.setSelected(true);
                    //aqui agregar el submenu correspondiente a esta opcion
                    addSubMenu(item, index, length, getComponentZOrder(item));
                    
                    item.setIcon(IconosCarpetaAbierta());
                    
                } else {
                    
                    hideMenu(item, index);
                    item.setSelected(false);
                    item.setIcon(IconosCarpetaCerrada());
                    
                }
                
            } else {
                if (event != null) {
                    event.selected(index, 0);
                }
            }
        });
        add(item);
        revalidate();
        repaint();
        
    }



    
    //aqui se agregan las opciones de cada menu
    private void addSubMenu(MenuItem item, int index, int lenght, int indexZorder) {

        JPanel panel = new JPanel(new MigLayout("wrap 1, fillx, gapy 0, inset 0", "fill"));
        panel.setName(index + "");
        panel.setOpaque(false);
       

        for (int i = 1; i < lenght; i++) {

            MenuItem subItem = new MenuItem(menuMatrix[index][i], i, false);
            subItem.setIcon(getSubMenuIcon());
          
            subItem.addActionListener((ActionEvent e) -> {
                if (event != null) {
                    event.selected(index, subItem.getIndex());
                }
            });

            subItem.initSubMenu(i, lenght);
            panel.add(subItem);

        }

        add(panel, "h 0!", indexZorder + 1);
        revalidate();
        repaint();

        MenuAnimation.showMenu(panel, item, layout, true);
    }

    
    
    
    
    //Oculta el submenu
    private void hideMenu(MenuItem item, int index) {

        for (Component com : getComponents()) {
            if (com instanceof JPanel && com.getName() != null && com.getName().equals(index + "")) {
                com.setName(null);
                MenuAnimation.showMenu(com, item, layout, false);
                break;
            }
        }

    }

    
    
    
    
    @Override
    protected void paintComponent(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new Color(238,244,254));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        
        super.paintComponent(g);
    
    }

    
    private Icon IconosCarpetaCerrada() {
        URL url = getClass().getResource("/icons/treemenu/close_file.png");
        return new ImageIcon(url);
    }

    private Icon IconosCarpetaAbierta() {
        URL url = getClass().getResource("/icons/treemenu/open_file.png");
        return new ImageIcon(url);
    }

    
    private Icon getSubMenuIcon() {
        URL url = getClass().getResource("/icons/treemenu/function.png");
        return new ImageIcon(url);
    }

}
