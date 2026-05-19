package com.simplecore.erp.gui.components.tabbedpanes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTabbedPane;

/**
 *
 * @author user
 */
public class JTabbedPaneCustom extends JTabbedPane {

    public JTabbedPaneCustom() {

        setBackground(new Color(202, 216, 237));
        setForeground(Color.DARK_GRAY);

        Component[] co = getComponents();

        addChangeListener((e) -> {

            for (int i = 0; i < getComponentCount(); i++) {
                if (co[i] == getSelectedComponent()) {

                    co[i].setBackground(new Color(51, 102, 255));
                    co[i].setForeground(Color.BLACK);

                }else{
                    co[i].setBackground(new Color(202, 216, 237));
                    co[i].setForeground(Color.DARK_GRAY);
                }

            }

        });

    }

}
