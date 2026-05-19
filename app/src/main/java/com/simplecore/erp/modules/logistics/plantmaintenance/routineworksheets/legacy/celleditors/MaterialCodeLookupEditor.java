package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.celleditors;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MaterialCodeLookupEditor extends DefaultCellEditor {

    private MaterialCodeLookupList popupDialog;
    private final AWTEventListener awtEventListener;

    public MaterialCodeLookupEditor(JTextField textField) {
        super(textField);
        setClickCountToStart(1);

        // Global listener to detect clicks outside the dialog
        awtEventListener = event -> {
            if (popupDialog != null && popupDialog.isVisible() && event instanceof MouseEvent) {
                MouseEvent mouseEvent = (MouseEvent) event;
                if (mouseEvent.getID() == MouseEvent.MOUSE_PRESSED) {
                    Point clickPoint = mouseEvent.getLocationOnScreen();
                    Rectangle dialogBounds = popupDialog.getBounds();
                    if (!dialogBounds.contains(clickPoint)) {
                        popupDialog.setVisible(false);
                    }
                }
            }
        };

        // Register the global listener
        Toolkit.getDefaultToolkit().addAWTEventListener(awtEventListener, AWTEvent.MOUSE_EVENT_MASK);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        JTextField materialCodeField = (JTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
        materialCodeField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Press F1");

        // Reuse the dialog if it already exists
        if (popupDialog == null) {
            popupDialog = new MaterialCodeLookupList((Frame) null, false);
        }
                
        popupDialog.setMaterialCodeField(materialCodeField);
        popupDialog.setWarehouseCodeField(table, row);
        
        // Configure the KeyListener to show the dialog when F1 is pressed
        materialCodeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    showPopupDialog(materialCodeField);
                }
            }
        });

        return materialCodeField;
    }

    private void showPopupDialog(JTextField editorField) {
        Point location = editorField.getLocationOnScreen();
        int editorHeight = editorField.getHeight();

        // Get the screen bounds
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        Rectangle screenBounds = gd.getDefaultConfiguration().getBounds();

        // Calculate the initial position of the dialog
        int dialogX = location.x;
        int dialogY = location.y + editorHeight;

        // Check if the dialog would go off the bottom of the screen
        int dialogHeight = popupDialog.getHeight();
        if (dialogY + dialogHeight > screenBounds.height) {
            // Show the dialog above the cell if it doesn't fit below
            dialogY = location.y - dialogHeight;

            // If it still doesn't fit, show it in the middle of the screen
            if (dialogY < 0) {
                dialogY = (screenBounds.height - dialogHeight) / 2;
            }
        }

        // Ensure the dialog doesn't go off the sides of the screen
        if (dialogX + popupDialog.getWidth() > screenBounds.width) {
            dialogX = screenBounds.width - popupDialog.getWidth();
        }
        if (dialogX < 0) {
            dialogX = 0;
        }

        // Set the dialog position
        popupDialog.setLocation(dialogX, dialogY);
        popupDialog.setVisible(true);
    }

    /**
     * Releases resources used by the editor.
     */
    public void dispose() {
        // Remove the global listener to avoid memory leaks
        Toolkit.getDefaultToolkit().removeAWTEventListener(awtEventListener);

        // Close and release the dialog if it's open
        if (popupDialog != null) {
            popupDialog.setVisible(false);
            popupDialog.dispose();
            popupDialog = null;
        }
    }

}
