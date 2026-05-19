
package com.simplecore.erp.client.gui.components.searchbox;

import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class FieldBox extends JTextField {

    @Override
    public void setEditable(boolean b) {
        super.setEditable(b);
        Color color = b ? Color.WHITE : new Color(202,216,237);
        setBackground(color);
    }

}
