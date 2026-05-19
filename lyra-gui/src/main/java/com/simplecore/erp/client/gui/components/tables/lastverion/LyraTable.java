package com.simplecore.erp.client.gui.components.tables.lastverion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class LyraTable extends JTable {
    
        
    //METODO CONSTRUCTOR
    
    public LyraTable() {
        
        getTableHeader().setDefaultRenderer(new columnas());
        setDefaultRenderer(Object.class, new celdas());
        setDefaultEditor(Object.class, new CellTableEditor());
        setBackground(new Color(202, 219, 236));
        
    }

    



    public Color getColorTextOnEdit() {
        return colorTextOnEdit;
    }

    public void setColorTextOnEdit(Color colorTextOnEdit) {
        this.colorTextOnEdit = colorTextOnEdit;
    }

    public Color getColorOnEdit() {
        return colorBackgroundOnEdit;
    }

    public void setColorOnEdit(Color colorOnEdit) {
        this.colorBackgroundOnEdit = colorOnEdit;
    }

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
            default ->
                throw new AssertionError(getFonts().name());
            
        }
    }
    
    private FontStyle fontStyle = FontStyle.Plain;
    int style = 0;
    
    private void setStyleFont() {
        switch (fontStyle) {
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
            default ->
                throw new AssertionError(fontStyle.name());
            
        }
    }
    
    private FontAlignment position = FontAlignment.CENTER;
    int p = SwingConstants.CENTER;
    
    private void posicionarFuente() {
        
        switch (getPosition()) {
            
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
    private Color columnColor = new Color(226, 210, 144);
    private Color ForegroundColor = new Color(0, 0, 0);
    
    
    
    private class columnas extends DefaultTableCellRenderer {
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            comp.setBackground(getColumnColor());
            comp.setForeground(getForegroundColor());
            
            selectFont();
            setStyleFont();
            posicionarFuente();
            
            comp.setFont(new Font(fuente, style, fontSize));
            setBorder(new BevelBorder(BevelBorder.RAISED));
            setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
            
            setHorizontalAlignment(p);
            
            if (column == 0) {
                comp.setBackground(new Color(202, 219, 236));                
                getColumnModel().getColumn(column).setMaxWidth(23);
                getColumnModel().getColumn(column).setMinWidth(23);
                getColumnModel().getColumn(column).setPreferredWidth(23);
                getColumnModel().getColumn(column).setIdentifier("L");
            }
            
            table.getTableHeader().setReorderingAllowed(false);
            

            return comp;
            
        }
        
    }


    
    
    private Color ColorSelection = new Color(255, 230, 127);
    private Color ColorSelectFont = new Color(0, 0, 0);
    private Color colorUnselectText = new Color(0,0,0);
    
    
    private Color colorRow1 = new Color(232, 247, 253);
    private Color colorRow2 = new Color(203, 229, 242);
    
    Icon icon = null;//= new ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/chekOk.png"));
    int rowImage;
    
    public void setButtonImage(Icon icon){
        this.icon = icon;
    }
    
    public void setRowToImageIcon(int row) {
        this.rowImage = row;
    }



    private class celdas extends DefaultTableCellRenderer {

        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
           
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                        
            table.setGridColor(gridColor);
            table.setShowGrid(true);
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            table.setRowHeight(22);

            

            if (isCellSelected(row, column)) {

                comp.setBackground(getColorSelection());
                comp.setForeground(getColorSelectFont());

            } else {
                if (row % 2 == 0) {
                    comp.setBackground(getColorRow1());
                    comp.setForeground(getColorUnselectText());
                } else {
                    comp.setBackground(getColorRow2());
                    comp.setForeground(getColorUnselectText());
                }
            }

        if (column == 0) {
            TableSimpleButton button = new TableSimpleButton();
            if (icon != null) {
                if (row == rowImage) {
                    button.butt.setIcon(icon);
                }

            }

            comp = button;
            comp.setBackground(new Color(202, 219, 236));

        }


            return comp;
        }

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
    
    private Color colorBackgroundOnEdit = new Color(0, 0, 153);
    private Color colorTextOnEdit = new Color(255, 255, 255);



    public class CellTableEditor extends DefaultCellEditor {
        
        public CellTableEditor() {
            super(new JTextField());
        }


        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            Component comp = super.getTableCellEditorComponent(table, value, isSelected, row, column);
            
            Color localColorBG = comp.getBackground();
            Color localColorText = comp.getForeground();

            if (isSelected) {
                comp.setBackground(colorBackgroundOnEdit);
                comp.setForeground(getColorTextOnEdit());
            } else {
                comp.setBackground(localColorBG);
                comp.setForeground(localColorText);
            }

            
            return comp;

        }

    }


    
}
