
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service;

import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TextFieldFilterService {

    public MatchCode accountNumberFromMatchCode;
    public MatchCode accountNumberToMatchCode;
    public JTextField accountNameFrom;
    public JTextField accountNameTo;
    public MatchCode parentFromMatchCode;
    public MatchCode parentToMatchCode;
    public MatchCode subclassFromMatchCode;
    public MatchCode subclassToMatchCode;
    public MatchCode modelFromMatchCode;
    public MatchCode modelToMatchCode;
    public MatchCode modelStateFromMatchCode;
    public MatchCode modelStateToMatchCode;
    public MatchCode createdByFromMatchCode;
    public MatchCode createdByToMatchCode;
    public MatchCode updatedByFromMatchCode;
    public MatchCode updatedByToMatchCode;
    public JDateChooser createdAtFromChooser;
    public JDateChooser createdAtToChooser;
    public JDateChooser updatedAtFromChooser;
    public JDateChooser updatedAtToChooser;
    private final PastedListInterfaces pastedListListener;


    public TextFieldFilterService(Builder builder) {
        this.pastedListListener = builder.interfaceList;
        this.accountNumberFromMatchCode = builder.accountNumberFromMatchCode;
        this.accountNumberToMatchCode = builder.accountNumberToMatchCode;
        this.accountNameFrom = builder.accountNameFrom;
        this.accountNameTo = builder.accountNameTo;
        this.parentFromMatchCode = builder.parentFromMatchCode;
        this.parentToMatchCode = builder.parentToMatchCode;
        this.subclassFromMatchCode = builder.subclassFromMatchCode;
        this.subclassToMatchCode = builder.subclassToMatchCode;
        this.modelFromMatchCode = builder.modelFromMatchCode;
        this.modelToMatchCode = builder.modelToMatchCode;
        this.modelStateFromMatchCode = builder.modelStateFromMatchCode;
        this.modelStateToMatchCode = builder.modelStateToMatchCode;
        this.createdByFromMatchCode = builder.createdByFromMatchCode;
        this.createdByToMatchCode = builder.createdByToMatchCode;
        this.updatedByFromMatchCode = builder.updatedByFromMatchCode;
        this.updatedByToMatchCode = builder.updatedByToMatchCode;
        this.createdAtFromChooser = builder.createdAtFromChooser;
        this.createdAtToChooser = builder.createdAtToChooser;
        this.updatedAtFromChooser = builder.updatedAtFromChooser;
        this.updatedAtToChooser = builder.updatedAtToChooser;
        attachListener();
    }
    
    private void attachListener() {
        attachTextFieldListeners(accountNumberFromMatchCode, accountNumberToMatchCode,
                pastedListListener.accountNumbersList(), pastedListListener.accountNumberFilterButton());

        attachTextFieldListeners(accountNameFrom, accountNameTo,
                pastedListListener.accountNameList(), pastedListListener.accountNameFilterButton());

        attachTextFieldListeners(parentFromMatchCode, parentToMatchCode,
                pastedListListener.accountParentList(), pastedListListener.parentFilterButton());

        attachTextFieldListeners(subclassFromMatchCode, subclassToMatchCode,
                pastedListListener.accountSubclassList(), pastedListListener.subclassFilterButton());

        attachTextFieldListeners(modelFromMatchCode, modelToMatchCode,
                pastedListListener.accountModelNameList(), pastedListListener.modelFilterButton());

        attachTextFieldListeners(modelStateFromMatchCode, modelStateToMatchCode,
                pastedListListener.accountModelStatesList(), pastedListListener.modelStateFilterButton());

        attachTextFieldListeners(createdByFromMatchCode, createdByToMatchCode,
                pastedListListener.accountCreatedByList(), pastedListListener.createdByFilterButton());

        attachTextFieldListeners(createdAtFromChooser, createdAtToChooser,
                pastedListListener.accountCreatedAtList(), pastedListListener.createdAtAccountFilterButton());

        attachTextFieldListeners(updatedByFromMatchCode, updatedByToMatchCode,
                pastedListListener.accountUpdatedByList(), pastedListListener.updatedByAccountFilterButton());

        attachTextFieldListeners(updatedAtFromChooser, updatedAtToChooser,
                pastedListListener.accountUpdatedAtList(), pastedListListener.updatedAtAccountFilterButton());
    }

    private boolean isProgrammaticChange = false;

    public void setFirstValueOnMatchCode(List<Object> list, MatchCode matchCode1, MatchCode matchCode2) {
        isProgrammaticChange = true;
       
        if (!list.isEmpty()) {
            matchCode1.getTextField().setText(String.valueOf(list.get(0)));
            matchCode2.getTextField().setText("");
        } else {
            matchCode1.getTextField().setText("");
            matchCode2.getTextField().setText("");
        }

        isProgrammaticChange = false;
        
    }

    public void setFirstValueOnMatchCode(List<Object> list, JTextField matchCode1, JTextField matchCode2) {
        isProgrammaticChange = true;
        
        if (!list.isEmpty()) {
            matchCode1.setText(String.valueOf(list.get(0)));
            matchCode2.setText("");
        } else {
            matchCode1.setText("");           // Limpiamos el primer campo
            matchCode2.setText("");           // Limpiamos el segundo campo
        }

        isProgrammaticChange = false;
    }
    
    public void setFirstValueOnMatchCode(List<Object> list, JDateChooser dateChooser, JDateChooser dateChooser2) {
        isProgrammaticChange = true;
        
        JTextField chooser1 = ((JTextFieldDateEditor)dateChooser.getDateEditor());
        JTextField chooser2 = ((JTextFieldDateEditor)dateChooser2.getDateEditor());
        
        if (!list.isEmpty()) {
            chooser1.setText(String.valueOf(list.get(0)));
            chooser2.setText("");
        } else {
            chooser1.setText("");           // Limpiamos el primer campo
            chooser2.setText("");           // Limpiamos el segundo campo
        }
        
        isProgrammaticChange = false;
    }

    public void attachTextFieldListeners(JTextField matchCode1, JTextField matchCode2, List<Object> list,JButton button) {
        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                clearListIfNotProgrammatic();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                clearListIfNotProgrammatic();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                clearListIfNotProgrammatic();
            }

            private void clearListIfNotProgrammatic() {
                if (!isProgrammaticChange && !list.isEmpty()) {
                    list.clear();
                    setAddFilterIcon(button);
                }
            }
        };
        matchCode1.getDocument().addDocumentListener(listener);
        matchCode2.getDocument().addDocumentListener(listener);
    }

    public void attachTextFieldListeners(MatchCode matchCode1, MatchCode matchCode2, List<Object> list,JButton button) {
        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                clearListIfNotProgrammatic();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                clearListIfNotProgrammatic();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                clearListIfNotProgrammatic();
            }

            private void clearListIfNotProgrammatic() {
                if (!isProgrammaticChange && !list.isEmpty()) {
                    list.clear();
                    setAddFilterIcon(button);
                }
            }
        };
        matchCode1.getTextField().getDocument().addDocumentListener(listener);
        matchCode2.getTextField().getDocument().addDocumentListener(listener);
    }

    public void attachTextFieldListeners(JDateChooser dateChooser1, JDateChooser dateChooser2, List<Object> list,JButton button) {
        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                clearListIfNotProgrammatic();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                clearListIfNotProgrammatic();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                clearListIfNotProgrammatic();
            }

            private void clearListIfNotProgrammatic() {
                if (!isProgrammaticChange && !list.isEmpty()) {
                    list.clear();
                    setAddFilterIcon(button);
                }
            }
        };
        ((JTextFieldDateEditor) dateChooser1.getDateEditor()).getDocument().addDocumentListener(listener);
        ((JTextFieldDateEditor) dateChooser2.getDateEditor()).getDocument().addDocumentListener(listener);
    }

    private void setAddFilterIcon(JButton button) {
        button.setIcon(new CustomSVGIcon("/icons/svg/filter_add.svg", new Dimension(24, 24)));
    }


    public static class Builder {

        private MatchCode accountNumberFromMatchCode;
        private MatchCode accountNumberToMatchCode;
        private MatchCode parentFromMatchCode;
        private MatchCode parentToMatchCode;
        private MatchCode subclassFromMatchCode;
        private MatchCode subclassToMatchCode;
        private MatchCode modelFromMatchCode;
        private MatchCode modelToMatchCode;
        private MatchCode modelStateFromMatchCode;
        private MatchCode modelStateToMatchCode;
        private MatchCode createdByFromMatchCode;
        private MatchCode createdByToMatchCode;
        private MatchCode updatedByFromMatchCode;
        private MatchCode updatedByToMatchCode;
        private JDateChooser createdAtFromChooser;
        private JDateChooser createdAtToChooser;
        private JDateChooser updatedAtFromChooser;
        private JDateChooser updatedAtToChooser;
        private PastedListInterfaces interfaceList;
        private JTextField accountNameFrom;
        private JTextField accountNameTo;
        
        public Builder accountNameFrom(JTextField accountNameFrom){
            this.accountNameFrom = accountNameFrom;
            return this;
        }
        public Builder accountNameTo(JTextField accountNameTo){
            this.accountNameTo = accountNameTo;
            return this;
        }

        public Builder interfaceList(PastedListInterfaces interfaceList){
            this.interfaceList = interfaceList;
            return this;
        }
        public Builder accountNumberFromMatchCode(MatchCode accountNumberFromMatchCode) {
            this.accountNumberFromMatchCode = accountNumberFromMatchCode;
            return this;
        }

        public Builder accountNumberToMatchCode(MatchCode accountNumberToMatchCode) {
            this.accountNumberToMatchCode = accountNumberToMatchCode;
            return this;
        }

        public Builder parentFromMatchCode(MatchCode parentFromMatchCode) {
            this.parentFromMatchCode = parentFromMatchCode;
            return this;
        }

        public Builder parentToMatchCode(MatchCode parentToMatchCode) {
            this.parentToMatchCode = parentToMatchCode;
            return this;
        }

        public Builder subclassFromMatchCode(MatchCode subclassFromMatchCode) {
            this.subclassFromMatchCode = subclassFromMatchCode;
            return this;
        }

        public Builder subclassToMatchCode(MatchCode subclassToMatchCode) {
            this.subclassToMatchCode = subclassToMatchCode;
            return this;
        }

        public Builder modelFromMatchCode(MatchCode modelFromMatchCode) {
            this.modelFromMatchCode = modelFromMatchCode;
            return this;
        }

        public Builder modelToMatchCode(MatchCode modelToMatchCode) {
            this.modelToMatchCode = modelToMatchCode;
            return this;
        }

        public Builder modelStateFromMatchCode(MatchCode modelStateFromMatchCode) {
            this.modelStateFromMatchCode = modelStateFromMatchCode;
            return this;
        }

        public Builder modelStateToMatchCode(MatchCode modelStateToMatchCode) {
            this.modelStateToMatchCode = modelStateToMatchCode;
            return this;
        }

        public Builder createdByFromMatchCode(MatchCode createdByFromMatchCode) {
            this.createdByFromMatchCode = createdByFromMatchCode;
            return this;
        }

        public Builder createdByToMatchCode(MatchCode createdByToMatchCode) {
            this.createdByToMatchCode = createdByToMatchCode;
            return this;
        }

        public Builder updatedByFromMatchCode(MatchCode updatedByFromMatchCode) {
            this.updatedByFromMatchCode = updatedByFromMatchCode;
            return this;
        }

        public Builder updatedByToMatchCode(MatchCode updatedByToMatchCode) {
            this.updatedByToMatchCode = updatedByToMatchCode;
            return this;
        }

        public Builder createdAtFromChooser(JDateChooser createdAtFromChooser) {
            this.createdAtFromChooser = createdAtFromChooser;
            return this;
        }

        public Builder createdAtToChooser(JDateChooser createdAtToChooser) {
            this.createdAtToChooser = createdAtToChooser;
            return this;
        }

        public Builder updatedAtFromChooser(JDateChooser updatedAtFromChooser) {
            this.updatedAtFromChooser = updatedAtFromChooser;
            return this;
        }

        public Builder updatedAtToChooser(JDateChooser updatedAtToChooser) {
            this.updatedAtToChooser = updatedAtToChooser;
            return this;
        }

        public TextFieldFilterService build() {
            return new TextFieldFilterService(this);
        }
    }

}
