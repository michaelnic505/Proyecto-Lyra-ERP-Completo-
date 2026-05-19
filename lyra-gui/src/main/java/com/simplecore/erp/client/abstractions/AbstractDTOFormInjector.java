

package com.simplecore.erp.client.abstractions;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.model.ComboItem;
import com.simplecore.erp.client.services.base.AbstractFormPanel;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;


/**
 * @author Michael F. Sánchez
 * @param <P>
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class AbstractDTOFormInjector<P extends AbstractFormPanel> {

    protected P panel;

    public AbstractDTOFormInjector(P panel) {
        this.panel = panel;
    }

    protected void setValue(Supplier<? extends JTextComponent> supplier, Object value) {
       Optional.ofNullable(supplier.get())
            .filter(comp -> value != null)
            .ifPresent(comp -> comp.setText(value.toString()));
    }

    protected void setLabelValue(Supplier<JLabel> supplier, Object value) {
        Optional.ofNullable(supplier.get())
                .ifPresent(label -> label.setText(Optional.ofNullable(value).map(Object::toString).orElse("")));
    }

    protected void setCheckValue(Supplier<JCheckBox> supplier, boolean value) {
        Optional.ofNullable(supplier.get())
            .ifPresent(checkBox -> checkBox.setSelected(value));
    }

    protected void setDateValue(Supplier<JDateChooser> supplier, Date value) {
        Optional.ofNullable(supplier.get())
            .ifPresent(chooser -> chooser.setDate(value));
    }

    protected void setValueC(Supplier<JComboBox> supplier, Object value) {
        Optional.ofNullable(supplier.get())
                .filter(comp -> value != null)
                .ifPresent(comp -> {
                    int items = comp.getItemCount();
                    for (int i = 0; i < items; i++) {
                        Object obj = comp.getItemAt(i);
                        if (obj instanceof ComboItem) {
                            ComboItem item = (ComboItem) obj;
                            if (value.toString().equals(item.getDescription())) {
                                comp.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                });
    }

}
