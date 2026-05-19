
package com.simplecore.erp.client.gui.components.tables.lastverion;


public enum ColumnFonts {
    
    SegoeUI("SegoeUI"),
    Calibri("a"),
    Arial("Arial"),
    Open_Sans("Open_Sans"),
    Consolas("Consolas"),
    Tahoma("Tahoma");
    
    String font;

    private ColumnFonts(String font) {
        this.font = font;
    }

    public String getFont() {
        return font;
    }
    
    
}
