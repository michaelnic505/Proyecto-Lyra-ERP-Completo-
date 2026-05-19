package com.simplecore.erp.gui.components.tables.lastversion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class LyraTableAuxiliar extends JTable {

    public Color getColorSeleccionFuente() {
        return colorSeleccionFuente;
    }

    public void setColorSeleccionFuente(Color colorSeleccionFuente) {
        this.colorSeleccionFuente = colorSeleccionFuente;
    }

    public Color getColorSeleccionColumnas() {
        return colorSeleccionColumnas;
    }

    public void setColorSeleccionColumnas(Color colorSeleccionColumnas) {
        this.colorSeleccionColumnas = colorSeleccionColumnas;
    }

    public Color getColorColumnas() {
        return colorColumnas;
    }

    public void setColorColumnas(Color colorColumnas) {
        this.colorColumnas = colorColumnas;
    }

    public Color getColorSeleccionColumna1() {
        return colorSeleccionColumna1;
    }

    public void setColorSeleccionColumna1(Color colorSeleccionColumna1) {
        this.colorSeleccionColumna1 = colorSeleccionColumna1;
    }

    public Color getColorColumna1() {
        return colorColumna1;
    }

    public void setColorColumna1(Color colorColumna1) {
        this.colorColumna1 = colorColumna1;
    }

    public FontAlignment getPosition() {
        return position;
    }

    public void setPosition(FontAlignment position) {
        this.position = position;
    }

    public Color getForegroundColor() {
        return ForegroundColor;
    }

    public void setForegroundColor(Color ForegroundColor) {
        this.ForegroundColor = ForegroundColor;
    }

    public Color getColumnColor() {
        return columnColor;
    }

    public void setColumnColor(Color columnColor) {
        this.columnColor = columnColor;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public FontStyle getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
    }

    public ColumnFonts getFonts() {
        return fonts;
    }

    public void setFonts(ColumnFonts fonts) {
        this.fonts = fonts;
    }


    public LyraTableAuxiliar() {
        
        getTableHeader().setDefaultRenderer(new columnas());
        setDefaultRenderer(Object.class, new CeldasRenderLyraTable2());
      
        
    }
    
    private ColumnFonts fonts = ColumnFonts.SegoeUI;
    String fuente = ColumnFonts.SegoeUI.getFont();
    private void selectFont() {
        
        switch (getFonts()) {
            case SegoeUI -> {
                fuente = ColumnFonts.SegoeUI.getFont();
            }
            case Calibri -> {
                fuente = ColumnFonts.Calibri.getFont();
            }
            case Arial -> {
                fuente = ColumnFonts.Arial.getFont();
            }
            case Open_Sans -> {
                fuente = ColumnFonts.Open_Sans.getFont();
            }
            case Consolas -> {
                fuente = ColumnFonts.Consolas.getFont();
            }
            case Tahoma -> {
                fuente = ColumnFonts.Tahoma.getFont();
            }
            default -> throw new AssertionError(getFonts().name());

        }
    }
    
    private FontStyle fontStyle = FontStyle.Plain;
    int style = 0;
    private void setStyleFont(){
        switch(fontStyle){
            case Plain -> {
                style = FontStyle.Plain.getStyle();
            }
            case Bold -> {
                style = FontStyle.Bold.getStyle();
            }
            case Italic -> {
                style = FontStyle.Italic.getStyle();
            }
            case Bold_Italic -> {
                style = FontStyle.Bold_Italic.getStyle();
            }
            default -> throw new AssertionError(fontStyle.name());
            
        }
    }
    
    private FontAlignment position = FontAlignment.CENTER;
    int p = SwingConstants.CENTER;
    private void posicionarFuente(){
        
        switch(getPosition()){

            case LEFT -> {
                p = SwingConstants.LEFT;
            }
            case CENTER -> {
                p = SwingConstants.CENTER;
            }
            case RIGHT -> {
                p = SwingConstants.RIGHT;
            }
            
        }
        
    }
    
    

    private int fontSize = 12;
    private Color columnColor = new Color(226,210,144);
    private Color ForegroundColor = new Color(0,0,0);
    
    private class columnas extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 

            comp.setBackground(getColumnColor());
            comp.setForeground(getForegroundColor());
            
            selectFont();
            setStyleFont();
            posicionarFuente();
            
            comp.setFont(new Font(fuente, style,fontSize));                   
            setBorder(new BevelBorder(BevelBorder.RAISED));
            setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
            
            setHorizontalAlignment(p);
            
            
            
            return comp;
            
        }

    }

    
    private Color colorColumna1 = new Color(172,226,255);
    private Color colorColumnas = new Color(235,248,250);
    
    private Color colorSeleccionColumna1 = new Color(255,242,156);
    private Color colorSeleccionColumnas = new Color(255,200,43);

    private Color colorSeleccionFuente = new Color(56,56,56);
    
    private  class CeldasRenderLyraTable2 extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (column == 1) {
                comp.setBackground(getColorColumna1());

            } else {
                comp.setBackground(getColorColumnas());
            }

            if (isSelected) {
                if (column == 1) {
                    comp.setBackground(getColorSeleccionColumna1());
                    comp.setForeground(getColorSeleccionFuente());
                } else {
                    comp.setBackground(getColorSeleccionColumnas());
                    comp.setForeground(getColorSeleccionFuente());
                }
            }

            return comp;
        }

        
        
    }
    
    
    
}
