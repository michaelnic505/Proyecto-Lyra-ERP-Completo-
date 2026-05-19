package com.simplecore.erp.modules.logistics.plantmaintenance.strategies.legacy.news;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class TextField {

    public static void setOnlyNums(JTextField tf) {
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }

        });
    }

    public static void setMaxChars(JTextField tf, int max) {
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int cant = tf.getText().length();
                if (cant >= max) {
                    e.consume();
                }
            }

        });
    }
}
