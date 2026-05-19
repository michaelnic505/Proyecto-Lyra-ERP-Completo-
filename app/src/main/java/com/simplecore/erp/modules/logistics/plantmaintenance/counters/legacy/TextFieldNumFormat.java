package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.JTextField;

public class TextFieldNumFormat {

    private boolean nums = true;
    private boolean dec = false;
    private int cant = 0;
    private boolean ready = false;

    private int decimals;

    public void controlFormatNumber(JTextField jtf, KeyEvent e, int numChars, int decimals) {

        this.decimals = decimals;
        String text = jtf.getText();
        char c = e.getKeyChar();

        if (decimals > 0) {

            if (c == '.' && text.contains(".")) {
                e.consume();
            } else if (c == '.' && !text.contains(".")) {
                nums = false;
                dec = true;
                cant = 0;
            }
            if (!Character.isDigit(c) && c != '.') {
                e.consume();
            }

            if (nums) {

                if (cant == numChars) {
                    e.consume();
                } else if (Character.isDigit(c)) {
                    cant++;
                }

            }

            if (dec) {

                if (cant == decimals) {
                    e.consume();
                } else if (Character.isDigit(c)) {
                    cant++;
                    if (cant == decimals) {
                        ready = true;
                    }
                }
            }

        } else {

            if (!Character.isDigit(c)) {
                e.consume();
            }

            if (cant == numChars) {
                e.consume();
            } else if (Character.isDigit(c)) {
                cant++;
                if (cant == numChars) {
                    ready = true;
                }
            }

        }
    }

    public void restart(JTextField jtf, KeyEvent e,JTextField ird) {

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

            jtf.setText(null);
            ird.setText(null);
            nums = true;
            dec = false;
            cant = 0;
            ready = false;
        }
    }

    public void formatNumber(JTextField jtf, KeyEvent e) {

            String ceros = "";

            if (decimals > 0) {
                for (int i = 0; i < decimals; i++) {
                    ceros = ceros + "0";
                }
                ceros = "." + ceros;
            }

            DecimalFormat df = new DecimalFormat("#,##0" + ceros);
            
            if (ready) {

                String num = jtf.getText().trim().replace(",", "");
                String numd = df.format(Double.valueOf(num));

                jtf.setText(numd);
            }

    }

    public void formatNumber(JTextField jtf) {

        String ceros = "";

        if (decimals > 0) {
            for (int i = 0; i < decimals; i++) {
                ceros = ceros + "0";
            }
            ceros = "." + ceros;
        }

        DecimalFormat df = new DecimalFormat("#,##0" + ceros);

        if (ready) {

            String num = jtf.getText().trim().replace(",", "");
            String numd = df.format(Double.valueOf(num));

            jtf.setText(numd);
        }

    }

}
