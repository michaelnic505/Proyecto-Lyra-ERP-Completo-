
package com.simplecore.erp.controllers.gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanelLoader {

    public static void loadPanel(JPanel subContainer, JPanel mainContainer) {
        mainContainer.removeAll(); // Elimina el contenido actual

        // Asegurar que mainContainer tenga BorderLayout
        if (!(mainContainer.getLayout() instanceof BorderLayout)) {
            mainContainer.setLayout(new BorderLayout());
        }

        // Asegurar que subContainer se expanda completamente
        subContainer.setPreferredSize(null);
        subContainer.setMinimumSize(new Dimension(0, 0));
        subContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        // Agregar el nuevo panel al centro
        mainContainer.add(subContainer, BorderLayout.CENTER);

        // FORZAR ACTUALIZACIÓN COMPLETA
        mainContainer.revalidate();
        mainContainer.repaint();

        // SOLUCIÓN ADICIONAL: Asegurar que el contenedor principal se actualice también
        SwingUtilities.invokeLater(() -> {
            Window window = SwingUtilities.getWindowAncestor(mainContainer);
            if (window != null) {
                window.revalidate();
                window.repaint();
            }
        });
    }
}

