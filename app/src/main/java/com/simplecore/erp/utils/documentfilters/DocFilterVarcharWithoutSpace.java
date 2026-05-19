package com.simplecore.erp.utils.documentfilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class DocFilterVarcharWithoutSpace extends javax.swing.text.DocumentFilter {

    private int limit;
    private boolean uppercase = false;

    public DocFilterVarcharWithoutSpace(int limit) {
        this.limit = limit;
    }

    public DocFilterVarcharWithoutSpace setUpperCase(boolean upperCase) {
        this.uppercase = upperCase;
        return this;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string != null) {
            string = string.replace(" ", ""); // Eliminar espacios en blanco

            if ((fb.getDocument().getLength() + string.length()) <= limit) {
                if (uppercase) {
                    string = string.toUpperCase();
                }

                super.insertString(fb, offset, string, attr); // Permitir la inserción
            }
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) {
            string = "";
        } else {
            string = string.replace(" ", ""); // Eliminar espacios en blanco
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
