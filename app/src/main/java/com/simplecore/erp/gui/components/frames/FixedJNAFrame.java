
package com.simplecore.erp.gui.components.frames;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.W32APIOptions;

import javax.swing.*;
import java.awt.*;

public class FixedJNAFrame extends JFrame {

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
    private static final int SWP_FRAMECHANGED = 0x0020;

    private boolean isMaximized = false;

    public FixedJNAFrame() {
        setTitle("Fixed JNA Frame");
        setUndecorated(false); // Permitimos decorado inicialmente
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel titleBar = createCustomTitleBar();
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.LIGHT_GRAY);

        setLayout(new BorderLayout());
        add(titleBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        // Ocultar decorado visual al iniciar
        SwingUtilities.invokeLater(this::enableCustomUndecoratedStyle);
    }

    private JPanel createCustomTitleBar() {
        JPanel titleBar = new JPanel();
        titleBar.setBackground(Color.DARK_GRAY);
        titleBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Botón Minimizar
        JButton minimizeButton = new JButton("_");
        minimizeButton.addActionListener(e -> minimizeWindow());

        // Botón Maximizar / Restaurar
        JButton maximizeButton = new JButton("[ ]");
        maximizeButton.addActionListener(e -> toggleMaximizeRestore());

        // Botón Cerrar
        JButton closeButton = new JButton("X");
        closeButton.addActionListener(e -> closeWindow());

        titleBar.add(minimizeButton);
        titleBar.add(maximizeButton);
        titleBar.add(closeButton);

        return titleBar;
    }

    private void minimizeWindow() {
        HWND hWnd = getWindowHandle();
        if (hWnd != null) {
            AdvancedUser32.INSTANCE.ShowWindow(hWnd, WinUser.SW_MINIMIZE);
        }
    }

    private void toggleMaximizeRestore() {
        HWND hWnd = getWindowHandle();
        if (hWnd != null) {
            isMaximized = !isMaximized;
            AdvancedUser32.INSTANCE.ShowWindow(hWnd, isMaximized ? WinUser.SW_MAXIMIZE : WinUser.SW_RESTORE);
        }
    }

    private void closeWindow() {
        System.exit(0);  // Cerrar la aplicación
    }

    private HWND getWindowHandle() {
        return AdvancedUser32.INSTANCE.FindWindow(null, getTitle());
    }

    private void enableCustomUndecoratedStyle() {
        HWND hWnd = getWindowHandle();
        if (hWnd != null) {
            // Obtener el estilo actual de la ventana
            int currentStyle = AdvancedUser32.INSTANCE.GetWindowLong(hWnd, GWL_STYLE);

            // Eliminar los botones nativos pero mantener los bordes de la ventana
            int newStyle = currentStyle & ~(WS_MINIMIZEBOX | WS_MAXIMIZEBOX | 0x00080000); // Eliminar botones nativos
            newStyle = newStyle | WS_CAPTION; // Mantener los bordes de la ventana

            // Establecer el nuevo estilo
            AdvancedUser32.INSTANCE.SetWindowLong(hWnd, GWL_STYLE, newStyle);

            // Cambiar la posición de la ventana para aplicar los nuevos estilos sin moverla
            AdvancedUser32.INSTANCE.SetWindowPos(hWnd, null, 0, 0, 0, 0,
                    SWP_FRAMECHANGED | WinUser.SWP_NOZORDER);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FixedJNAFrame frame = new FixedJNAFrame();
            frame.setVisible(true);
        });
    }
}
