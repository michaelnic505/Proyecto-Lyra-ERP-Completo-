
package com.simplecore.erp.client.utils.documentfilters;

import javax.swing.text.*;

public class NumDoubleLimitFilter extends DocumentFilter {

    private final int maxIntegerDigits; // Número máximo de dígitos antes del punto
    private final int maxDecimalDigits; // Número máximo de dígitos después del punto

    public NumDoubleLimitFilter(int maxIntegerDigits, int maxDecimalDigits) {
        this.maxIntegerDigits = maxIntegerDigits;
        this.maxDecimalDigits = maxDecimalDigits;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null || string.isEmpty()) {
            return;
        }

        // Obtener el texto actual de la celda
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = currentText.substring(0, offset) + string + currentText.substring(offset);

        // Validar si el nuevo texto es un número válido
        if (isValidDouble(newText)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
        if (string == null || string.isEmpty()) {
            return;
        }

        // Obtener el texto actual de la celda
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = currentText.substring(0, offset) + string + currentText.substring(offset + length);

        // Validar si el nuevo texto es un número válido
        if (isValidDouble(newText)) {
            super.replace(fb, offset, length, string, attrs);
        }
    }

    private boolean isValidDouble(String text) {
        // Expresión regular para limitar tanto los dígitos enteros antes del punto
        // como los decimales después del punto
        String regex = "^\\d{0," + maxIntegerDigits + "}(\\.\\d{0," + maxDecimalDigits + "})?$";
        return text.matches(regex);
    }
}
