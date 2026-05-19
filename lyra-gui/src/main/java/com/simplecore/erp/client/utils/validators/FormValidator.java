
package com.simplecore.erp.client.utils.validators;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.models.AcModComboItem;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.model.ComboItem;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.*;

public class FormValidator {

    private final Set<JComponent> components = new LinkedHashSet<>();

    public FormValidator add(JComponent component) {
        components.add(component);
        return this;
    }

    public boolean validate(SystemMessages notificator) {
        for (JComponent comp : components) {
            if (comp instanceof JTextField textField) {
                if (textField.getText().trim().isEmpty()) {
                    textField.requestFocus();
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
                    return false;
                }

            } else if (comp instanceof JLabel label) {
                if (label.getText().trim().isEmpty()) {
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
                    return false;
                }

            } else if (comp instanceof JComboBox comboBox) {
                Object item = comboBox.getSelectedItem();
                if (item instanceof String stringItem) {
                    if (stringItem.trim().isEmpty()) {
                        comboBox.requestFocus();
                        notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
                        return false;
                    }
                } else if (item instanceof AcModComboItem acModComboItem) {
                    if (acModComboItem.toString().trim().isEmpty()) {
                        comboBox.requestFocus();
                        notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
                        return false;
                    }
                } else if (item instanceof ComboItem comboItem) {
                    if (comboItem.toString().trim().isEmpty()) {
                        comboBox.requestFocus();
                        notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
                        return false;
                    }
                }

            } else if (comp instanceof JTextArea textArea) {
                if (textArea.getText().trim().isEmpty()) {
                    textArea.requestFocus();
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
                    return false;
                }
            } else if (comp instanceof JDateChooser chooser) {
                String pattern = chooser.getDateFormatString(); // Ej: "dd/MM/yyyy"
                JTextField textField = (JTextFieldDateEditor) chooser.getDateEditor();
                String inputText = textField.getText().trim();

                if (inputText.isEmpty()) {
                    textField.requestFocus();
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
                    return false;
                }

                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                sdf.setLenient(false); // No permitir fechas como 32/01/2024

                try {
                    sdf.parse(inputText); // Si falla, lanza ParseException
                } catch (ParseException e) {
                    textField.requestFocus();
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.INCORRECT_FORMAT));
                    return false;
                }
            }

        }
        return true;
    }
}
