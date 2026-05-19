package com.simplecore.erp.utils.documentfilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class DocFilterCurrencyCode extends javax.swing.text.DocumentFilter {

    private int limit;
    private boolean uppercase = false;

    public DocFilterCurrencyCode(int limit) {
        this.limit = limit;
    }

    public DocFilterCurrencyCode setUpperCase(boolean upperCase) {
        this.uppercase = upperCase;
        return this;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string != null) {
            // Eliminar espacios y verificar solo letras mayúsculas
            string = string.replace(" ", ""); // Eliminar espacios en blanco
            string = string.replaceAll("[^A-Z]", ""); // Permitir solo letras mayúsculas

            // Comprobar longitud del texto y aplicar mayúsculas si es necesario
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
            string = string.replaceAll("[^A-Z]", ""); // Permitir solo letras mayúsculas
        }

        int nuevaLongitud = fb.getDocument().getLength() - length + string.length();

        // Verificar la longitud y permitir la sustitución si es válida
        if (nuevaLongitud <= limit) {
            if (uppercase) {
                string = string.toUpperCase();
            }
            super.replace(fb, offset, length, string, attr); // Permitir el reemplazo
        }
    }
}
