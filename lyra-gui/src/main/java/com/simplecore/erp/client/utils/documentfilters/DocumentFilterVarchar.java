package com.simplecore.erp.client.utils.documentfilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class DocumentFilterVarchar extends javax.swing.text.DocumentFilter {

    private int limit;
    private boolean uppercase = false;

    public DocumentFilterVarchar(int limit) {
        this.limit = limit;
    }
    public DocumentFilterVarchar setUpperCase(boolean upperCase){
        this.uppercase = upperCase;
        return this;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if ((fb.getDocument().getLength() + string.length()) <= limit) {
            if(uppercase){
                string = string.toUpperCase();
            }
                
            super.insertString(fb, offset, string, attr); // Permitir la inserción
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
        
        if(string==null){
            string = "";
        }
        
        int nuevaLongitud = fb.getDocument().getLength() - length + string.length();
        
        if (nuevaLongitud <= limit) {
            if (uppercase) {
                string = string.toUpperCase();
            }
            super.replace(fb, offset, length, string, attr); // Permitir el reemplazo
        }
    }

}
