package com.simplecore.erp.gui.notifications;

import java.awt.Color;
import javax.swing.*;
import com.simplecore.erp.gui.components.labels.JLabelHQBackground;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.utils.sound.Sound;
import com.simplecore.erp.utils.sound.SoundManager;

public class SystemMessages {

    private static final Icon ICON_SUCCESS = new ImageIcon(SystemMessages.class.getResource("/icons/notifications/success.png"));
    private static final Icon ICON_WARNING = new ImageIcon(SystemMessages.class.getResource("/icons/notifications/warning.png"));
    private static final Icon ICON_INFORMATION = new ImageIcon(SystemMessages.class.getResource("/icons/notifications/information.png"));
    private static final Icon ICON_ERROR = new ImageIcon(SystemMessages.class.getResource("/icons/notifications/error.png"));

    private JLabel label;
    private String message;
    private TypeMessage type;
    private static boolean isShowingNotification = false; // Flag to prevent multiple notifications

    public SystemMessages(JLabel label, String message, TypeMessage type) {
        this.label = label;
        this.message = message;
        this.type = type;
        showNotification();
    }
    public SystemMessages(String message, TypeMessage type) {
        this(LyraWorkspace.NotificationLabel, message, type);
    }
    public SystemMessages(){
        this.label = LyraWorkspace.NotificationLabel;
    }

    public void showSuccessMsg(String successMessage) {
        this.message = successMessage;
        this.type = TypeMessage.SUCCESS;
        showNotification();
    }

    public void showWarningMsg(String warningMessage) {
        this.message = warningMessage;
        this.type = TypeMessage.WARNING;
        showNotification();
    }

    public void showErrorMsg(String errorMessage) {
        this.message = errorMessage;
        this.type = TypeMessage.ERROR;
        showNotification();
    }

    public void showInfoMsg(String infoMessage) {
        this.message = infoMessage;
        this.type = TypeMessage.INFORMATION;
        showNotification();
    }

    public void show() {
        showNotification();
    }

    private void showNotification() {
        if (isShowingNotification) {
            return;  // Prevent starting a new notification if one is already being displayed
        }

        isShowingNotification = true;  // Set flag to indicate a notification is being shown

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                updateLabel();
                displayMessage();
                return null;
            }
        }.execute();
    }

    private void updateLabel() {
        if (type != null) {
            switch (type) {
                case SUCCESS -> {
                    updateLabelStyle(ICON_SUCCESS, new Color(0, 200, 81), new Color(0, 126, 51));
                    SoundManager.playSound(Sound.DONE.dir());
                }
                case WARNING -> {
                    updateLabelStyle(ICON_WARNING, new Color(255, 187, 51), new Color(255, 136, 0));
                    SoundManager.playSound(Sound.NOTIFICATION.dir());
                }
                case INFORMATION -> {
                    updateLabelStyle(ICON_INFORMATION, new Color(51, 181, 229), new Color(0, 153, 204));
                    SoundManager.playSound(Sound.INFO.dir());
                }
                case ERROR -> {
                    updateLabelStyle(ICON_ERROR, new Color(255, 68, 68), new Color(204, 0, 0));
                    SoundManager.playSound(Sound.EMPTY_FIELD.dir());
                }
                default -> {
                }
            }
        }
    }

    private void updateLabelStyle(Icon icon, Color color1, Color color2) {
        label.setIcon(icon);
        if (label instanceof JLabelHQBackground) {
            JLabelHQBackground labelHQ = (JLabelHQBackground) label;
            labelHQ.setFillColor1(color1);
            labelHQ.setFillColor2(color2);
        }
    }

    private void displayMessage() {
        StringBuilder displayText = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            displayText.append(message.charAt(i));  // Append one character at a time
            label.setText(displayText.toString());
            try {
                Thread.sleep(5);  // Slightly increased delay for better visual effect
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(2000);  // Delay before clearing the label
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        label.setIcon(null);
        label.setText(null);

        isShowingNotification = false;  // Reset flag after notification is done
    }
}