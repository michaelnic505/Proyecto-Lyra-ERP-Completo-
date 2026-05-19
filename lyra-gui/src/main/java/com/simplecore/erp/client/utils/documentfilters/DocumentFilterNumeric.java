package com.simplecore.erp.client.utils.documentfilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class DocumentFilterNumeric extends javax.swing.text.DocumentFilter {

    private int limit;

    public DocumentFilterNumeric(int limit) {
        this.limit = limit;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string != null && string.matches("\\d*") && (fb.getDocument().getLength() + string.length() <= limit)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
        if (string != null && string.matches("\\d*") && (fb.getDocument().getLength() - length + string.length() <= limit)) {
            super.replace(fb, offset, length, string, attr);
        }
    }

}
