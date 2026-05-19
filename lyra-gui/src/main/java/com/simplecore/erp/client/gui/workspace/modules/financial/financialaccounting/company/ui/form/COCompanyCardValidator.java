

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form;

import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.workspace.modules.financial.fico.util.ComboItem;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.toedter.calendar.JDateChooser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class COCompanyCardValidator {

    private SystemMessages notificator = new SystemMessages();
    private COCompanyCard card;

    public COCompanyCardValidator(COCompanyCard card) {
        this.card = card;
    }

    public boolean isCompleted() {
        if (!isFieldFilled(card.getCOCompanyMatchCode().getTextField())) {
            return false;
        }
        if (!isFieldFilled(card.getCostVariantMatchCode().getTextField())) {
            return false;
        }
        if (!isFieldFilled(card.getCurrencyMatchCode().getTextField())) {
            return false;
        }
        if (!isFieldFilled(card.getCostCenterPlanMatchCode().getTextField())) {
            return false;
        }
        if(!isComboSelected(card.getRelationTypeCombo())){
            return false;
        }
        if(!isComboSelected(card.getStatusCombo())){
            return false;
        }
        if(!isDateCompleted(card.getValidFromChooser())){
            return false;
        }
        if(!isDateCompleted(card.getValidToChooser())){
            return false;
        }
        return true;
    }

    private boolean isFieldFilled(JTextField textField) {
        if (textField.getText().trim().isEmpty()) {
            textField.requestFocus();
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
            return false;
        }
        return true;
    }

    private boolean isComboSelected(JComboBox<ComboItem> combo) {
        ComboItem item = (ComboItem) combo.getSelectedItem();
        String desc = item.getDescription();
        if (desc == null || desc.isEmpty()) {
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
            return false;
        }
        return true;
    }

    private boolean isDateCompleted(JDateChooser chooser) {
        String pattern = chooser.getDateFormatString();
        String text = ((JTextField) chooser.getDateEditor().getUiComponent()).getText().trim();

        // Comprobación de si la fecha es válida
        if (chooser.getDate() == null || text.isEmpty() || pattern == null || pattern.isEmpty() || !isPatternMatch(text, pattern)) {
            // Si la fecha no es válida, mostrar el mensaje de error y devolver false
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
            return false;
        }
        // Si la fecha es válida, devolver true
        return true;
    }

    private boolean isPatternMatch(String text, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false);
            return sdf.parse(text) != null;
        } catch (ParseException e) {
            return false;
        }
    }

}
