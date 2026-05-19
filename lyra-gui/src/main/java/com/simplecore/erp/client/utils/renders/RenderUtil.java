

package com.simplecore.erp.client.utils.renders;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.sql.Date;
import java.sql.Timestamp;

public class RenderUtil {

    // Método para configurar el renderizador para varios tipos de datos comunes
    public static void configureTableRenderers(JTable tableList) {
        // Obtener el render por defecto
        TableCellRenderer defaultRenderer = tableList.getDefaultRenderer(Object.class);

        // Asignar el render genérico para Object y otros tipos comunes
        tableList.setDefaultRenderer(Object.class, defaultRenderer);        // Para objetos genéricos
        tableList.setDefaultRenderer(String.class, defaultRenderer);        // Para Strings
        tableList.setDefaultRenderer(Integer.class, defaultRenderer);       // Para enteros
        tableList.setDefaultRenderer(Double.class, defaultRenderer);        // Para dobles
        tableList.setDefaultRenderer(Float.class, defaultRenderer);         // Para flotantes
        tableList.setDefaultRenderer(Long.class, defaultRenderer);          // Para long
        tableList.setDefaultRenderer(Short.class, defaultRenderer);         // Para shorts
        tableList.setDefaultRenderer(Byte.class, defaultRenderer);          // Para bytes
        tableList.setDefaultRenderer(Boolean.class, new BooleanCellRenderer()); // Para Boolean (Checkbox)
        tableList.setDefaultRenderer(Date.class, defaultRenderer);  // Para Date (SQL Date)
        tableList.setDefaultRenderer(Timestamp.class, defaultRenderer); // Para Timestamp
        tableList.setDefaultRenderer(java.util.Date.class, defaultRenderer); // Para java.util.Date
        tableList.setDefaultRenderer(java.math.BigDecimal.class, defaultRenderer); // Para BigDecimal
        tableList.setDefaultRenderer(java.util.Calendar.class, defaultRenderer); // Para Calendar

        // Si necesitas otros tipos de datos, puedes añadirlos aquí
    }

    // Implementación personalizada para el renderizado de Boolean
    public static class BooleanCellRenderer extends JCheckBox implements TableCellRenderer {
        public BooleanCellRenderer() {
            setHorizontalAlignment(CENTER); // Centrar el checkbox
            setOpaque(false); // Permitir que el fondo sea visible
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Boolean) {
            setSelected((Boolean) value);
        }

        // Pedimos el render por defecto de la celda y le copiamos el fondo
        TableCellRenderer defaultRenderer = table.getDefaultRenderer(Object.class);
        Component defaultComp = defaultRenderer.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);

        setBackground(defaultComp.getBackground());
        setForeground(defaultComp.getForeground());
        setOpaque(true); // Aseguramos que se pinte el fondo

        return this;
        }
    }

}
