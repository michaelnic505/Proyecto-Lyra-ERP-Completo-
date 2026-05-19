package com.simplecore.erp.client.gui.components.matchcode;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MatchCode extends JComponent {

    
    private JTextField textField;
    private JButton button;
    private Font customFont; // Fuente personalizada
    private Color colorBG;
    private Color colorOnFocus = new Color(255,239,161);

    public MatchCode() {
        setLayout(null); // Posicionamiento manual
        colorBG = getBackground();
        // Fuente predeterminada (Roboto Light)
        try {
            customFont = new Font("Roboto Light", Font.PLAIN, 14);
        } catch (Exception e) {
            customFont = new Font("SansSerif", Font.PLAIN, 14); // Fuente alternativa
        }

        // Campo de texto con fuente por defecto
        textField = new JTextField();
        textField.setFont(customFont);

        // Botón (por defecto oculto)
        button = new JButton(new CustomSVGIcon("/icons/svg/search_button_icon.svg", new Dimension(20,20))); // Puedes cambiar el ícono
        button.setPreferredSize(new Dimension(24, 24));
        button.setVisible(false);

        // Manejo del foco para mostrar/ocultar el botón
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setBackground(getColorOnFocus());
                button.setVisible(true);
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                textField.setBackground(colorBG);
                button.setVisible(false);
                repaint();
            }
        });

        // Agregar componentes
        add(textField);
        add(button);
    }

    @Override
    public void doLayout() {
        int width = getWidth();
        int height = getHeight();
        
        int buttonWidth = 24; // Ancho fijo del botón

        // Ajustar el tamaño del botón para que coincida con la altura del JTextField
        textField.setBounds(0, 0, width - buttonWidth, height);
        button.setBounds(width - buttonWidth, 0, buttonWidth, height);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 30); // Tamaño inicial recomendado
    }

    // Método para cambiar la fuente desde el editor de NetBeans
    @Override
    public void setFont(Font font) {
        this.customFont = font;
        textField.setFont(font);
        repaint();
    }

    @Override
    public Font getFont() {
        return customFont;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JButton getButton() {
        return button;
    }

    /**
     * @return the colorOnFocus
     */
    public Color getColorOnFocus() {
        return colorOnFocus;
    }

    /**
     * @param colorOnFocus the colorOnFocus to set
     */
    public void setColorOnFocus(Color colorOnFocus) {
        this.colorOnFocus = colorOnFocus;
    }


}
