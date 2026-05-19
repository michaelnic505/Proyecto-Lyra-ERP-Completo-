
package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

public class FilterDocumentEditor extends DefaultCellEditor {

    private DocumentFilter documentFilter;

    // Constructor that accepts a filter for double-type numbers
    public FilterDocumentEditor(DocumentFilter filter) {
        super(new JTextField()); // Initialize the editor with a JTextField
        this.documentFilter = filter;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Create a new JTextField each time the editor is invoked
        JTextField textField = (JTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);

        // Apply the filter to the JTextField
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(documentFilter);

        // Reset the value of the JTextField with the value of the cell
        textField.setText(value != null ? value.toString() : "");  // If there is no value, leave it empty

        // Return the JTextField as the editor component
        return textField;
    }

    @Override
    public Object getCellEditorValue() {
        // Get the value from the JTextField when the editor is closed
        String value = ((JTextField) getComponent()).getText();

        // If the value is empty, return null
        return value.isEmpty() ? null : value;
    }

    @Override
    public boolean stopCellEditing() {
        // Remove the DocumentFilter from the JTextField when the editor is closed
        ((AbstractDocument) ((JTextField) getComponent()).getDocument()).setDocumentFilter(null);  // Remove the filter

        return super.stopCellEditing();  // Call the parent class's method to continue closing the editing
    }
}
