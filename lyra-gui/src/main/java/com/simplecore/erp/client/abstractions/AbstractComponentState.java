

package com.simplecore.erp.client.abstractions;

import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.services.base.AbstractFormPanel;
import com.toedter.calendar.JDateChooser;
import java.util.function.Supplier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 * @author Michael F. Sánchez
 * @param <T>
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class AbstractComponentState <T extends AbstractFormPanel>{

    protected T panel;

    public AbstractComponentState(T panel) {
        this.panel = panel;
    }

    public abstract void applyCreateMode();
    public abstract void applyModifyMode();
    public abstract void applyViewMode();

    protected void setEditable(Supplier<? extends JTextComponent> supplier, boolean editable) {
        JTextComponent comp = supplier.get();
        if (comp != null) {
            comp.setEditable(editable);
        }
    }
    
    protected void setEditableMatchText(Supplier<? extends MatchCode> supplier, boolean editable) {
        JTextField comp = supplier.get().getTextField();
        if (comp != null) {
            comp.setEditable(editable);
        }
    }

    protected void setEnabled(Supplier<? extends JComponent> supplier, boolean enabled) {
        JComponent comp = supplier.get();
        if (comp != null) {
            comp.setEnabled(enabled);
        }
    }
   
    protected void setEnabledMatchButton(Supplier<? extends MatchCode> supplier, boolean enabled) {
        JButton comp = supplier.get().getButton();
        if (comp != null) {
            comp.setEnabled(enabled);
        }
    }

    protected void setEnabledDateChooser(Supplier<JDateChooser> supplier, boolean enabled) {
        JDateChooser chooser = supplier.get();
        if (chooser != null) {
            chooser.getCalendarButton().setEnabled(enabled);
            if (chooser.getDateEditor() != null) {
                chooser.getDateEditor().setEnabled(enabled);
            }
        }
    }
}
