
package com.simplecore.erp.client.gui.components.frames;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.W32APIOptions;
import javax.swing.*;
import java.awt.*;

public class CustomJFrame extends JFrame {

    // Interfaz para acceder a funciones de la API nativa de Windows (user32.dll)
    public interface AdvancedUser32 extends com.sun.jna.Library {
        AdvancedUser32 INSTANCE = Native.load("user32", AdvancedUser32.class, W32APIOptions.DEFAULT_OPTIONS);

        HWND FindWindow(String lpClassName, String lpWindowName);

        boolean ShowWindow(HWND hWnd, int nCmdShow);

        boolean SetWindowPos(HWND hWnd, HWND hWndInsertAfter, int x, int y, int cx, int cy, int uFlags);

        int GetWindowLong(HWND hWnd, int nIndex);

        int SetWindowLong(HWND hWnd, int nIndex, int dwNewLong);
    }

    private static final int GWL_STYLE = -16;
    private static final int WS_MINIMIZEBOX = 0x00020000;
    private static final int WS_MAXIMIZEBOX = 0x00010000;
    private static final int WS_CAPTION = 0x00C00000; // Bordes decorativos
    private static final int WS_SYSMENU = 0x00080000; // Menú del sistema
    private static final int SWP_FRAMECHANGED = 0x0020;

    private boolean isMaximized = false;

    public CustomJFrame() {
        setTitle("Custom JFrame");
        setUndecorated(true); // Eliminar la barra de título nativa
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear la barra de título personalizada con los botones
        JPanel titleBar = createCustomTitleBar();
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.LIGHT_GRAY);

        setLayout(new BorderLayout());
        add(titleBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        // Preservar las funcionalidades nativas después de ocultar el decorado
        SwingUtilities.invokeLater(this::enableCustomUndecoratedStyle);
    }

    private JPanel createCustomTitleBar() {
        JPanel titleBar = new JPanel();
        titleBar.setLayout(new FlowLayout(FlowLayout.RIGHT));  // Alinear los botones a la derecha
        titleBar.setBackground(Color.DARK_GRAY);
        titleBar.setPreferredSize(new Dimension(getWidth(), 30));  // Reducir la altura de la barra de título

        // Botón Minimizar
        JButton minimizeButton = new JButton("_");
        minimizeButton.setBackground(Color.GRAY);
        minimizeButton.setForeground(Color.WHITE);
        minimizeButton.setFocusPainted(false);
        minimizeButton.setBorderPainted(false);
        minimizeButton.addActionListener(e -> minimizeWindow());

        // Botón Maximizar/Restaurar
        JButton maximizeButton = new JButton("[ ]");
        maximizeButton.setBackground(Color.GRAY);
        maximizeButton.setForeground(Color.WHITE);
        maximizeButton.setFocusPainted(false);
        maximizeButton.setBorderPainted(false);
        maximizeButton.addActionListener(e -> toggleMaximizeRestore());

        // Botón Cerrar
        JButton closeButton = new JButton("X");
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.addActionListener(e -> closeWindow());

        // Agregar los botones a la barra de título personalizada
        titleBar.add(minimizeButton);
        titleBar.add(maximizeButton);
        titleBar.add(closeButton);

        return titleBar;
    }

    private void minimizeWindow() {
        HWND hWnd = getWindowHandle();
        if (hWnd != null) {
            AdvancedUser32.INSTANCE.ShowWindow(hWnd, WinUser.SW_MINIMIZE);  // Función nativa de minimizar
        }
    }

    private void toggleMaximizeRestore() {
        HWND hWnd = getWindowHandle();
        if (hWnd != null) {
            isMaximized = !isMaximized;
            AdvancedUser32.INSTANCE.ShowWindow(hWnd, isMaximized ? WinUser.SW_MAXIMIZE : WinUser.SW_RESTORE);  // Función nativa de maximizar/restaurar
        }
    }

    private void closeWindow() {
        System.exit(0);  // Función nativa de cerrar
    }

    private HWND getWindowHandle() {
        return AdvancedUser32.INSTANCE.FindWindow(null, getTitle());
    }

    private void enableCustomUndecoratedStyle() {
        HWND hWnd = getWindowHandle();
        if (hWnd != null) {
            // Obtener el estilo actual de la ventana
            int currentStyle = AdvancedUser32.INSTANCE.GetWindowLong(hWnd, GWL_STYLE);

            // Eliminar los botones nativos (minimizar, maximizar y cerrar)
            int newStyle = currentStyle & ~WS_MINIMIZEBOX & ~WS_MAXIMIZEBOX & ~WS_SYSMENU & ~WS_CAPTION;

            // Establecer los nuevos estilos
            AdvancedUser32.INSTANCE.SetWindowLong(hWnd, GWL_STYLE, newStyle);

            // Aplicar los cambios de estilo sin mover ni cambiar el tamaño de la ventana
            AdvancedUser32.INSTANCE.SetWindowPos(hWnd, null, 0, 0, 0, 0,
                    SWP_FRAMECHANGED | WinUser.SWP_NOZORDER);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomJFrame frame = new CustomJFrame();
            frame.setVisible(true);
        });
    }
}
