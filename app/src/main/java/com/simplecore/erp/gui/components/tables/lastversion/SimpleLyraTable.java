package com.simplecore.erp.gui.components.tables.lastversion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class SimpleLyraTable extends JTable {

    public Color getColorUnselectText() {
        return colorUnselectText;
    }

    public void setColorUnselectText(Color colorUnselectText) {
        this.colorUnselectText = colorUnselectText;
    }

    public Color getColorRow1() {
        return colorRow1;
    }

    public Color getColorRow2() {
        return colorRow2;
    }

    public void setColorRow1(Color colorRow1) {
        this.colorRow1 = colorRow1;
    }

    public void setColorRow2(Color colorRow2) {
        this.colorRow2 = colorRow2;
    }

    public Color getColorSelection() {
        return ColorSelection;
    }

    public Color getColorSelectFont() {
        return ColorSelectFont;
    }

    public void setColorSelection(Color ColorSelection) {
        this.ColorSelection = ColorSelection;
    }

    public void setColorSelectFont(Color ColorSelectFont) {
        this.ColorSelectFont = ColorSelectFont;
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



    public Color getColorGrid() {
        return colorGrid;
    }

    public void setColorGrid(Color colorGrid) {
        this.colorGrid = colorGrid;
    }



    public SimpleLyraTable() {
        getTableHeader().setDefaultRenderer(new columnas());
        setDefaultRenderer(Object.class, new celdas());

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

    private Color colorGrid = new Color(0, 153, 153);
    private Color ColorSelection = new Color(255, 230, 127);
    private Color ColorSelectFont = new Color(0, 0, 0);
    private Color colorUnselectText = new Color(0,0,0);

    private Color colorRow1 = new Color(232, 247, 253);
    private Color colorRow2 = new Color(203, 229, 242);

    private class celdas extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if(isCellSelected(row,column)){
                comp.setBackground(getColorSelection());
                comp.setForeground(getColorSelectFont());
            }else{
               if(row%2==0){
                   comp.setBackground(getColorRow1());
                   comp.setForeground(getColorUnselectText());
               }else{
                   comp.setBackground(getColorRow2());
                   comp.setForeground(getColorUnselectText());
               }
            }
            
            setGridColor(getColorGrid());
            
            return comp;
        }

    }
    
}
