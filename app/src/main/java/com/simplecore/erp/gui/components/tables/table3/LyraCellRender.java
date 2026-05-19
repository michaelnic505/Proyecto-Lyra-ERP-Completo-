package com.simplecore.erp.gui.components.tables.table3;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;

/**
 *
 * @author user
 */
public class LyraCellRender extends DefaultTableCellRenderer {


    public Color getColorSelection() {
        return ColorSelection;
    }

    public Color getColorSelectFont() {
        return ColorSelectFont;
    }

    public Color getColorUnselectText() {
        return colorUnselectText;
    }

    public void setColorSelection(Color ColorSelection) {
        this.ColorSelection = ColorSelection;
    }

    public void setColorSelectFont(Color ColorSelectFont) {
        this.ColorSelectFont = ColorSelectFont;
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

    private Color colorRow1 = new Color(232, 247, 253);
    private Color colorRow2 = new Color(203, 229, 242);
    private Color ColorSelection = new Color(255, 230, 127);
    private Color ColorSelectFont = new Color(0, 0, 0);
    private Color colorUnselectText = new Color(0, 0, 0);

    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

        if (isSelected) {

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


        return comp;
    }

    public class AlineacionCeldas{
     
        public static void alinearCentro(LyraTable table, int column){
            
            table.getColumnModel().getColumn(column).setCellRenderer(new LyraCellRender(){
                @Override
                public void setHorizontalAlignment(int alignment) {
                    alignment = SwingUtilities.CENTER;
                    super.setHorizontalAlignment(alignment); 
                }

            });
        }

        public static void alinearIzquierda(LyraTable table, int column) {

            table.getColumnModel().getColumn(column).setCellRenderer(new LyraCellRender() {
                @Override
                public void setHorizontalAlignment(int alignment) {
                    alignment = SwingUtilities.LEFT;
                    super.setHorizontalAlignment(alignment);
                }

            });
        }
        
        public static void alinearDerecha(LyraTable table, int column) {

            table.getColumnModel().getColumn(column).setCellRenderer(new LyraCellRender() {
                @Override
                public void setHorizontalAlignment(int alignment) {
                    alignment = SwingUtilities.RIGHT;
                    super.setHorizontalAlignment(alignment);
                }

            });
        }

    }

    public class DimensionesCeldas {

        public static void setWidth(LyraTable tabla, int columna, int ancho) {
            tabla.getColumnModel().getColumn(columna).setMinWidth(ancho);
            tabla.getColumnModel().getColumn(columna).setMaxWidth(ancho);
            tabla.getColumnModel().getColumn(columna).setPreferredWidth(ancho);
        }

        public static void setWidthResizable(LyraTable tabla, int columna, int ancho) {
            tabla.getColumnModel().getColumn(columna).setMinWidth(ancho);
            tabla.getColumnModel().getColumn(columna).setMaxWidth(ancho);
            tabla.getColumnModel().getColumn(columna).setPreferredWidth(ancho);
        }

    }

}
