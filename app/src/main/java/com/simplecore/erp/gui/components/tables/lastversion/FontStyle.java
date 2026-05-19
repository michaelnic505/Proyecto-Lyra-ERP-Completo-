package com.simplecore.erp.gui.components.tables.lastversion;

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
