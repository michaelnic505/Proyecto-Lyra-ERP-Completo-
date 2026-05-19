package com.simplecore.erp.client.gui.components.tables.lastverion;

public enum FontStyle {
    
    Plain(0),
    Bold(1),
    Italic(2),
    Bold_Italic(3);

    int style;

    private FontStyle(int style) {
        this.style = style;
    }

    public int getStyle() {
        return style;
    }

}
