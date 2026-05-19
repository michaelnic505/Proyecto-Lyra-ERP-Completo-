
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service;

import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.shared.models.queries.QueryFilters;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JCheckBox;
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
public class QueryService {
    
    // Fields that store the UI components and state values for queries
    private final JTextField accountNameFromTextField;
    private final JTextField accountNameToTextField;
    private final MatchCode accountNumberFromMatchCode;
    private final MatchCode accountNumberToMatchCode;
    private final MatchCode parentFromMatchCode;
    private final MatchCode parentToMatchCode;
    private final MatchCode subclassFromMatchCode;
    private final MatchCode subclassToMatchCode;
    private final MatchCode modelFromMatchCode;
    private final MatchCode modelToMatchCode;
    private final MatchCode modelStateFromMatchCode;
    private final MatchCode modelStateToMatchCode;
    private final MatchCode createdByFromMatchCode;
    private final MatchCode createdByToMatchCode;
    private final MatchCode updatedByFromMatchCode;
    private final MatchCode updatedByToMatchCode;
    private final JComboBox<String> statusAccountComboBox;
    private final JCheckBox isModelActive;
    private final JCheckBox isAccountClosed;
    private final JDateChooser createdAtFromDateChooser;
    private final JDateChooser updatedAtFromDateChooser;
    private final JDateChooser createdAtToDateChooser;
    private final JDateChooser updatedAtToDateChooser;
    private final PastedListInterfaces pastedListInterface;
    
    
    // Constructor that accepts a Builder instance to set up the fields
    public QueryService(Builder builder) {
        this.queries = new ArrayList<>();
        this.accountNameFromTextField = builder.accountNameFromTextField;
        this.accountNameToTextField = builder.accountNameToTextField;
        this.accountNumberFromMatchCode = builder.accountNumberFromMatchCode;
        this.accountNumberToMatchCode = builder.accountNumberToMatchCode;
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
        this.statusAccountComboBox = builder.statusAccountComboBox;
        this.isModelActive = builder.isModelActive;
        this.isAccountClosed = builder.isAccountClosed;
        this.createdAtFromDateChooser = builder.createdAtFromDateChooser;
        this.updatedAtFromDateChooser = builder.updatedAtFromDateChooser;
        this.createdAtToDateChooser = builder.createdAtToDateChooser;
        this.updatedAtToDateChooser = builder.updatedAtToDateChooser;
        this.pastedListInterface = builder.pastedListInterface;
    }

    // Builder class to set the fields step by step
    public static class Builder {

        // Fields similar to the ones in the main QueryService class
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
        private JComboBox<String> statusAccountComboBox;
        private JCheckBox isModelActive;
        private JCheckBox isAccountClosed;
        private JDateChooser createdAtFromDateChooser;
        private JDateChooser updatedAtFromDateChooser;
        private JDateChooser createdAtToDateChooser;
        private JDateChooser updatedAtToDateChooser;
        private PastedListInterfaces pastedListInterface;
        private JTextField accountNameFromTextField;
        private JTextField accountNameToTextField;

        // Methods to set each field in the Builder
        public Builder accountNumberFromMatchCode(MatchCode matchCode) {
            this.accountNumberFromMatchCode = matchCode;
            return this;
        }

        public Builder accountNumberToMatchCode(MatchCode matchCode) {
            this.accountNumberToMatchCode = matchCode;
            return this;
        }

        public Builder parentFromMatchCode(MatchCode matchCode) {
            this.parentFromMatchCode = matchCode;
            return this;
        }

        public Builder parentToMatchCode(MatchCode matchCode) {
            this.parentToMatchCode = matchCode;
            return this;
        }

        public Builder subclassFromMatchCode(MatchCode matchCode) {
            this.subclassFromMatchCode = matchCode;
            return this;
        }

        public Builder subclassToMatchCode(MatchCode matchCode) {
            this.subclassToMatchCode = matchCode;
            return this;
        }

        public Builder modelFromMatchCode(MatchCode matchCode) {
            this.modelFromMatchCode = matchCode;
            return this;
        }

        public Builder modelToMatchCode(MatchCode matchCode) {
            this.modelToMatchCode = matchCode;
            return this;
        }

        public Builder modelStateFromMatchCode(MatchCode matchCode) {
            this.modelStateFromMatchCode = matchCode;
            return this;
        }

        public Builder modelStateToMatchCode(MatchCode matchCode) {
            this.modelStateToMatchCode = matchCode;
            return this;
        }

        public Builder createdByFromMatchCode(MatchCode matchCode) {
            this.createdByFromMatchCode = matchCode;
            return this;
        }

        public Builder createdByToMatchCode(MatchCode matchCode) {
            this.createdByToMatchCode = matchCode;
            return this;
        }

        public Builder updatedByFromMatchCode(MatchCode matchCode) {
            this.updatedByFromMatchCode = matchCode;
            return this;
        }

        public Builder updatedByToMatchCode(MatchCode matchCode) {
            this.updatedByToMatchCode = matchCode;
            return this;
        }

        public Builder statusAccountComboBox(JComboBox<String> comboBox) {
            this.statusAccountComboBox = comboBox;
            return this;
        }

        public Builder isModelActive(JCheckBox checkBox) {
            this.isModelActive = checkBox;
            return this;
        }

        public Builder isAccountClosed(JCheckBox checkBox) {
            this.isAccountClosed = checkBox;
            return this;
        }

        public Builder createdAtFromDateChooser(JDateChooser dateChooser) {
            this.createdAtFromDateChooser = dateChooser;
            return this;
        }

        public Builder updatedAtFromDateChooser(JDateChooser dateChooser) {
            this.updatedAtFromDateChooser = dateChooser;
            return this;
        }

        public Builder createdAtToDateChooser(JDateChooser dateChooser) {
            this.createdAtToDateChooser = dateChooser;
            return this;
        }

        public Builder updatedAtToDateChooser(JDateChooser dateChooser) {
            this.updatedAtToDateChooser = dateChooser;
            return this;
        }
        public Builder pastedListInterface(PastedListInterfaces listener) {
            this.pastedListInterface = listener;
            return this;
        }
        public Builder accountNameFromTextField(JTextField textField){
            this.accountNameFromTextField = textField;
            return this;
        }

        public Builder accountNameToTextField(JTextField textField) {
            this.accountNameToTextField = textField;
            return this;
        }
        
        public QueryService build(){
            return new QueryService(this);
        }
    }

    // List to hold query filters
    private final List<QueryFilters> queries;
    
    // Method to get the prepared query filters
    public List<QueryFilters> getListQueries() {
        preparedQueriesList();
        return queries;
    }

    
    // Prepares the list of queries by checking the fields and adding filters
    private void preparedQueriesList() {
        queries.clear();
        
        // Get text fields from DateChoosers to prepare for query
        JTextField createdAtFromTextField = ((JTextFieldDateEditor) createdAtFromDateChooser.getDateEditor());
        JTextField updatedAtFromTextField = ((JTextFieldDateEditor) updatedAtFromDateChooser.getDateEditor());
        JTextField createdAtToTextField = ((JTextFieldDateEditor) createdAtToDateChooser.getDateEditor());
        JTextField updatedAtToTextField = ((JTextFieldDateEditor) updatedAtToDateChooser.getDateEditor());

        // Add filters for different fields based on match codes and pasted list interface
        addQueryFilterToList("acc.ACCOUNT_CODE", accountNumberFromMatchCode, accountNumberToMatchCode, pastedListInterface.accountNumbersList());
        addQueryFilterToList("acc.ACCOUNT_NAME", accountNameFromTextField, accountNameToTextField, pastedListInterface.accountNameList());
        addQueryFilterToList("acc.PARENT_ACCOUNT_ID", parentFromMatchCode, parentToMatchCode, pastedListInterface.accountParentList());
        addQueryFilterToList("sub.SUBCLASS_CODE", subclassFromMatchCode, subclassToMatchCode, pastedListInterface.accountSubclassList());
        addQueryFilterToList("mdl.MODEL_NAME", modelFromMatchCode, modelToMatchCode, pastedListInterface.accountModelNameList());
        addQueryFilterToList("mdl.STATE", modelStateFromMatchCode, modelStateToMatchCode, pastedListInterface.accountModelStatesList());
        addQueryFilterToList("acc.CREATED_BY", createdByFromMatchCode, createdByToMatchCode, pastedListInterface.accountCreatedByList());
        addQueryFilterToList("acc.CREATED_AT", createdAtFromTextField, createdAtToTextField, pastedListInterface.accountCreatedAtList());
        addQueryFilterToList("acc.UPDATED_BY", updatedByFromMatchCode, updatedByToMatchCode, pastedListInterface.accountUpdatedByList());
        addQueryFilterToList("acc.UPDATED_AT", updatedAtFromTextField, updatedAtToTextField, pastedListInterface.accountUpdatedAtList());
        
        // Add unique filters for specific attributes
        preparedUniqueQueries();
    }

     // Adds unique queries for model active status and account closed status
    private void preparedUniqueQueries() {
        
        queries.add(new QueryFilters("mdl.STATUS", "EQUALS", isModelActive.isSelected(), "", Collections.emptyList()));
        queries.add(new QueryFilters("acc.IS_CLOSED", "EQUALS", isAccountClosed.isSelected(), "", Collections.emptyList()));

        // Get the selected status and add it as a filter
        String status = statusAccountComboBox.getSelectedItem().toString();
        queries.add(new QueryFilters("acc.ACCOUNT_STATUS", "EQUALS", status, "", Collections.emptyList()));
    }

    // Helper method to handle JTextField cases
    private void addQueryFilterToList(String field, MatchCode matchCodeFrom, MatchCode matchCodeTo, List<Object> list) {
        // Llamamos a getFilteredQuery y verificamos si es null antes de agregarlo
        QueryFilters filter = getFilteredQuery(field, matchCodeFrom.getTextField().getText(),
                matchCodeTo.getTextField().getText(), list);

        // Solo agregamos el filtro si no es null
        if (filter != null) {
            queries.add(filter);
        }
    }

     // Method to generate the appropriate filter based on the input values
    private void addQueryFilterToList(String field, JTextField matchCodeFrom, JTextField matchCodeTo, List<Object> list) {
        // Llamamos a getFilteredQuery y verificamos si es null antes de agregarlo
        QueryFilters filter = getFilteredQuery(field, matchCodeFrom.getText(),
                matchCodeTo.getText(), list);

        // Solo agregamos el filtro si no es null
        
        if (filter != null) {
            queries.add(filter);
        }
    }

    /**
     * Generates a `QueryFilters` object based on the provided field, input
     * values, and a list of values. The method determines the type of filter to
     * apply (IN, RANGE, EQUALS) based on the provided input parameters.
     *
     * @param field The field name to be used in the query filter.
     * @param inputFrom The lower bound value for the filter (used for RANGE or
     * EQUALS operations).
     * @param inputTo The upper bound value for the filter (used for RANGE
     * operation).
     * @param listed A list of values used for the "IN" operation. Can be null
     * or empty.
     * @return A `QueryFilters` object representing the constructed filter or
     * null if no filter is applicable.
     *
     * The method first checks if the `listed` parameter contains any values to
     * determine if the filter should be an "IN" operation. Then, it checks if
     * both `inputFrom` and `inputTo` are provided to apply a "RANGE" filter. If
     * only `inputFrom` is provided, it applies an "EQUALS" filter. If none of
     * these conditions are met, the method returns null, indicating no filter
     * should be applied.
     */
    private QueryFilters getFilteredQuery(String field, Object inputFrom, Object inputTo, List<Object> listed) {
        // Check if 'listed' is not null and contains elements.
        boolean hasList = listed != null && !listed.isEmpty();

        // Check if 'inputFrom' is provided and is not an empty string.
        boolean hasFrom = inputFrom != null && !(inputFrom instanceof String && ((String) inputFrom).isEmpty());

        // Check if 'inputTo' is provided and is not an empty string.
        boolean hasTo = inputTo != null && !(inputTo instanceof String && ((String) inputTo).isEmpty());

        // Create and return the appropriate query filter based on the conditions.
        if (hasList) {
            // If a list of values is provided, apply the "IN" filter.
            return new QueryFilters(field, "IN", null, null, listed);
        } else if (hasFrom && hasTo) {
            // If both 'from' and 'to' values are provided, apply the "RANGE" filter.
            return new QueryFilters(field, "RANGE", inputFrom, inputTo, listed);
        } else if (hasFrom) {
            // If only the 'from' value is provided, apply the "EQUALS" filter.
            return new QueryFilters(field, "EQUALS", inputFrom, null, listed);
        }

        // Return null if no filter condition is met.
        return null;
    }

}
