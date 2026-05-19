package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

public class ValuesTable_CellRender extends DefaultCellEditor {

    private final String tpFt;
    private  int nChar;
    private final int dec;
    private final String uMeas;
    private final String curr;
    private final String tmp;
    private boolean interV;
    private boolean neg;

    public ValuesTable_CellRender(String tpFt, int nChar, int dec, String uMeas, 
            String curr, String tmp,boolean interV,boolean neg) {

        super(new JTextField());
        this.tpFt = tpFt;
        this.nChar = nChar;
        this.dec = dec;

        this.uMeas = uMeas;
        this.curr = curr;
        this.tmp = tmp;
        
        this.interV = interV;
        this.neg = neg;

        setClickCountToStart(1);

    }

    
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        table.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                e.consume();
            }
            
        });
        
        JTextField text = (JTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column); 
        parameters(text,table,column,row);
        
        return text;
    }

    private int charCount = 0;
    private boolean inNum = true;
    private boolean inDec = false;
    int menos = 0;
    int punto = 1;
    int sep = 1;
    int enteros = 0;

    

    private void parameters(JTextField textField,JTable table, int column,int row) {
        if (tpFt != null) {

            switch (tpFt) {
                case "CHAR" -> {

                    textField.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            if (textField.getText().length() == nChar) {
                                e.consume();
                            }
                        }
                    });

                }
                case "CURR" -> {

                    NumberFormat form = new DecimalFormat(tmp);

                    textField.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            char r = e.getKeyChar();
                            if (!Character.isDigit(r) && r != '.') {
                                e.consume();
                            }
                        }

                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                                if (!textField.getText().isEmpty()) {

                                    String data = textField.getText().replace(",", "");

                                    if (data.length() > 3) {

                                        char c1 = data.charAt(data.length() - 1);
                                        char c2 = data.charAt(data.length() - 2);
                                        char c3 = data.charAt(data.length() - 3);

                                        if (Character.isLetter(c1)
                                                && Character.isLetter(c2)
                                                && Character.isLetter(c3)) {

                                            String r = form.format(Double.parseDouble(
                                                    textField.getText().substring(0, textField.getText().length() - 3).replace(",", "")));
                                            textField.setText(r + " " + curr);

                                        } else {
                                            String r = form.format(Double.parseDouble(textField.getText()));
                                            textField.setText(r + " " + curr);
                                        }
                                    } else {
                                        String r = form.format(Double.parseDouble(textField.getText()));
                                        textField.setText(r + " " + curr);
                                    }
                                }
                            }
                        }

                    });
                    textField.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {

                            if (!textField.getText().isEmpty()) {

                                if (textField.getText().length() > 3) {

                                    String data = textField.getText();

                                    char c1 = data.charAt(data.length() - 1);
                                    char c2 = data.charAt(data.length() - 2);
                                    char c3 = data.charAt(data.length() - 3);

                                    if (Character.isLetter(c1)
                                            && Character.isLetter(c2)
                                            && Character.isLetter(c3)) {

                                        data = data.substring(0, data.length() - 3);
                                        data = data.replace(",", "");

                                        textField.setText(data.trim());

                                    } else {

                                        data = data.replace(",", "");
                                        textField.setText(data.trim());

                                    }

                                }

                            }

                        }

                    });

                    table.getColumnModel().getColumn(column).getCellEditor().addCellEditorListener(new CellEditorListener() {
                        @Override
                        public void editingStopped(ChangeEvent e) {

                            if (table.getValueAt(row, column) != null && !table.getValueAt(row, column).toString().isEmpty()) {

                                String data2 = table.getValueAt(row, column).toString().replace(",", "");
                                if (data2.length() > 3) {
                                    char c1 = data2.charAt(data2.length() - 1);
                                    char c2 = data2.charAt(data2.length() - 2);
                                    char c3 = data2.charAt(data2.length() - 3);

                                    if (Character.isLetter(c1)
                                            && Character.isLetter(c2)
                                            && Character.isLetter(c3)) {

                                        String r = form.format(Double.parseDouble(
                                                data2.substring(0, data2.length() - 3).replace(",", "")));
                                        table.setValueAt(r + " " + curr, row, column);

                                    } else {
                                        String r = form.format(Double.parseDouble(data2));
                                        table.setValueAt(r + " " + curr, row, column);
                                    }
                                } else {
                                    String r = form.format(Double.parseDouble(data2));
                                    table.setValueAt(r + " " + curr, row, column);
                                }

                            }

                        }

                        @Override
                        public void editingCanceled(ChangeEvent e) {

                        }

                    });

                }
                case "NUM" -> {

                    String ceros = "";

                    if (dec > 0) {
                        for (int i = 0; i < dec; i++) {
                            ceros = ceros + "#";
                        }
                        ceros = "." + ceros;
                    }

                    NumberFormat nFormat = new DecimalFormat("#,###"+ceros+";-#,###"+ceros);
                    
                    textField.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {

                            
                            char r = e.getKeyChar();

                            if (interV) {

                                if (neg) {

                                    if (dec > 0) {
                                        
                                       //INTERVALO - NEGATIVOS - DECIMALES - ok
                                       
                                        if (!Character.isDigit(r) && r != '-' && r != '.'&&r!='/') {
                                            e.consume();
                                        } else if (r == '-' && menos == 1) {
                                            e.consume();
                                        }else if (r == '.' && punto == 1) {
                                            e.consume();
                                        }else if (r == '/' && sep == 1) {
                                            e.consume();
                                        }

                                        
                                        if (inNum) {
                             
                                            if (charCount == nChar) {
                                        
                                                textField.setText(textField.getText() + ".");
                                                punto = 1;
                                                inDec = true;
                                                charCount = 0;
                                                inNum = false;
                                                enteros++;

                                            } else if (charCount > 0 && r == '.') {

                                                punto = 1;
                                                inDec = true;
                                                charCount = 0;
                                                inNum = false;
                                                enteros++;

                                            } else if (Character.isDigit(r)) {
                                                charCount++;
                                            }
                                        }

                                        if (inDec) {
                                            if (charCount == dec) {
                                                if (enteros < 2) {
                                                    sep = 0;
                                                    if (r == '/') {
                                                        inNum = true;
                                                        charCount = 0;
                                                        inDec = false;
                                                        menos = 0;
                                                        sep = 1;
                                                    } else {
                                                        e.consume();
                                                    }

                                                } else {
                                                    e.consume();
                                                }
                                            } else {
                                                if(Character.isDigit(r)){
                                                    charCount++;
                                                }
                                                
                                            }
                                        }

                                        if (r == '-') {
                                            menos = 1;
                                        } else if (charCount > 0) {
                                            punto = 0;
                                            menos = 1;                                            
                                        }

                                        if (r == '.' && punto == 0) {
                                            punto = 1;
                                            inDec = true;
                                            inNum = false;
                                        }

                                        
                                        
                                    }else{
                                      
                                        //INTERVALO - NEGATIVOS - NO DECIMALES - ok

                                        if (!Character.isDigit(r) && r != '-' && r != '/') {
                                            e.consume();
                                        } else if (r == '-' && menos == 1) {
                                            e.consume();
                                        } else if (r == '/' && sep == 1) {
                                            e.consume();
                                        }

                                        if (charCount == nChar) {
                                            sep = 0;
                                            menos = 0;
                                            if (r == '/') {
                                                charCount = 0;
                                                sep=1;
                                            }else{
                                                e.consume();
                                            }

                                        } else if (Character.isDigit(r)) {
                                            charCount++;
                                        }

                                        if (r == '-') {
                                            menos = 1;
                                        } else if (charCount > 0) {
                                            menos = 1;
                                        }

                                        
                                    }
                                }else {

                                    if (dec > 0) {

                                        //INTERVALO - NO NEGATIVOS - DECIMALES - ok
                                        if (!Character.isDigit(r) && r != '.' && r != '/') {
                                            e.consume();
                                        }else if (r == '.' && punto == 1) {
                                            e.consume();
                                        } else if (r == '/' && sep == 1) {
                                            e.consume();
                                        }

                                        if (inNum) {

                                            if (charCount == nChar) {

                                                textField.setText(textField.getText() + ".");
                                                punto = 1;
                                                inDec = true;
                                                charCount = 0;
                                                inNum = false;
                                                enteros++;

                                            } else if (charCount > 0 && r == '.') {

                                                punto = 1;
                                                inDec = true;
                                                charCount = 0;
                                                inNum = false;
                                                enteros++;

                                            } else if (Character.isDigit(r)) {
                                                charCount++;
                                            }
                                        }

                                        if (inDec) {
                                            if (charCount == dec) {
                                                if (enteros < 2) {
                                                    sep = 0;
                                                    if (r == '/') {
                                                        inNum = true;
                                                        charCount = 0;
                                                        inDec = false;
                                                        menos = 0;
                                                        sep = 1;
                                                    } else {
                                                        e.consume();
                                                    }

                                                } else {
                                                    e.consume();
                                                }
                                            } else {
                                                if(Character.isDigit(r)){
                                                    charCount++;
                                                }
                                            }
                                        }

                                        if (r == '.' && punto == 0) {
                                            punto = 1;
                                            inDec = true;
                                            inNum = false;
                                        }

                                        
                                    } else {
                             
                                        //INTERVALO - NO NEGATIVOS - NO DECIMALES - ok
                                        
                                        if (!Character.isDigit(r) && r != '/') {
                                            e.consume();
                                        } else if (r == '/' && sep == 1) {
                                            e.consume();
                                        }

                                        if (charCount == nChar) {
                               
                                            sep = 0;

                                            if (r == '/') {
                                                sep = 1;
                                                charCount = 0;
                                            }else{
                                                e.consume();
                                            }


                                        } else if (Character.isDigit(r)) {
                                            charCount++;
                                        }

                                    }

                                }
                            } else {
                                
                                if (neg) {
                                    
                                    if (dec > 0) {
                                        
                                        //NO INTERVALO - NEGATIVOS - DECIMALES - ok

                                        if (!Character.isDigit(r) && r != '-' && r != '.') {
                                            e.consume();
                                        } else if (r == '-' && menos == 1) {
                                            e.consume();
                                        }else if (r == '.' && punto == 1) {
                                            e.consume();
                                        }

                                        
                                        if (inNum) {
                             
                                            if (charCount == nChar) {
                                        
                                                textField.setText(textField.getText() + ".");
                                                punto = 1;
                                                inDec = true;
                                                charCount = 0;
                                                inNum = false;
                                                

                                            } else if (charCount > 0 && r == '.') {

                                                punto = 1;
                                                inDec = true;
                                                charCount = 0;
                                                inNum = false;
                                                

                                            } else if (Character.isDigit(r)) {
                                                charCount++;
                                            }
                                        }

                                        if (inDec) {
                                            if (charCount == dec) {
                                                
                                                e.consume();
                                           
                                            } else {
                                               if(Character.isDigit(r)){
                                                    charCount++;
                                                }
                                            }
                                        }

                                        if (r == '-') {
                                            menos = 1;
                                        } else if (charCount > 0) {
                                            punto = 0;
                                            menos = 1;                                            
                                        }

                                        if (r == '.' && punto == 0) {
                                            punto = 1;
                                            inDec = true;
                                            inNum = false;
                                        }
                                        
                                        
                                    } else {
                                        
                                        //NO INTERVALO - NEGATIVOS - NO DECIMALES
                                        
                                        if (!Character.isDigit(r) && r != '-') {
                                            e.consume();
                                        } else if (r == '-' && menos == 1) {
                                            e.consume();
                                        }

                                        
                                        if (charCount == nChar) {
                                           
                                            e.consume();

                                        }else if (Character.isDigit(r)) {
                                            charCount++;
                                        }



                                        if (r == '-') {
                                            menos = 1;
                                        } else if (charCount > 0) {
                                            menos = 1;                                            
                                        }


                                    }

                                } else {
                                    if (dec > 0) {
                                        //NO INTERVALO - NO NEGATIVOS - DECIMALES - ok

                                        if (!Character.isDigit(r) && r != '.') {
                                            e.consume();
                                        }else if (r == '.' && punto == 1) {
                                            e.consume();
                                        }

                                        if (inNum) {

                                            if (charCount == nChar) {

                                                textField.setText(textField.getText() + ".");
                                                punto = 1;
                                                inDec = true;
                                                charCount = 0;
                                                inNum = false;

                                            } else if (charCount > 0 && r == '.') {

                                                punto = 1;
                                                inDec = true;
                                                charCount = 0;
                                                inNum = false;

                                            } else if (Character.isDigit(r)) {
                                                charCount++;
                                            }
                                        }

                                        if (inDec) {
                                            if (charCount == dec) {

                                                e.consume();

                                            } else {
                                                if(Character.isDigit(r)){
                                                    charCount++;
                                                }
                                            }
                                        }

                                        if (charCount > 0) {
                                            punto = 0;
                                        }

                                        if (r == '.' && punto == 0) {
                                            punto = 1;
                                            inDec = true;
                                            inNum = false;
                                        }

                                    } else {
                                        //NO INTERVALO - NO NEGATIVOS - NO DECIMALES
                                                                                
                                        if (!Character.isDigit(r)) {
                                            e.consume();
                                        }

                                        if (charCount == nChar) {

                                            e.consume();

                                        } else if (Character.isDigit(r)) {
                                            charCount++;
                                        }

                                    }

                                }

                            }
                        }

                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

                                textField.setText(null);
                                restart();   

                            }
                        }



                    });
                    
                    /* Leer los valores al entrar en foco */
                    
                    textField.addFocusListener(new FocusAdapter() {
                      
                        @Override
                        public void focusGained(FocusEvent e) {
                            
                            
                            if (!textField.getText().isEmpty()) {
                                

                                String data = textField.getText().replace(",", "");
                                char[] c = data.toCharArray();
                               
                                String number = "";
                                
                                for (int i = 0; i < data.length(); i++) {
                                   
                                    number = number + c[i];    
                                    
                                    if(!Character.isDigit(c[i]) && c[i]!='.' && c[i]!='-' && c[i]!='/'){
                                        break;
                                    }
                                }
                                
                                textField.setText(number.trim());
                                
                            }
                              
                            
                        }
                    });
                    /*  Fin del codigo  */
                    
                    
                    table.getColumnModel().getColumn(column).getCellEditor().addCellEditorListener(new CellEditorListener() {
                        
                        /* Formatear los valores al termina de realizar el ingreso */
                        @Override
                        public void editingStopped(ChangeEvent e) {

                            int intervalIndex = 0;

                            if (table.getValueAt(row, column) != null && !table.getValueAt(row, column).toString().isEmpty()) {

                                String data = table.getValueAt(row, column).toString().replace(",", "");
                                char[] c = data.toCharArray();

                                String value = "";

                                for (int i = 0; i < data.length(); i++) {

                                    value = value + c[i];

                                    if (!Character.isDigit(c[i]) && c[i] != '.' && c[i] != '-' && c[i] != '/') {
                                        break;
                                    }

                                    if (c[i] == '/') {
                                        intervalIndex = i;
                                    }

                                }

                                if (intervalIndex > 0) {

                                    String val1 = value.substring(0, intervalIndex);
                                    String val2 = value.substring(intervalIndex+1, value.length());

                                    table.setValueAt(nFormat.format(Double.parseDouble(val1)) + "/" + nFormat.format(Double.parseDouble(val2)) + " " + uMeas, row, column);

                                } else {
                                    table.setValueAt(nFormat.format(Double.parseDouble(value)) + " " + uMeas, row, column);
                                }

                                
                            }

                            table.getColumnModel().getColumn(1).setCellEditor(new ValuesTable_CellRender(tpFt, nChar, dec, uMeas,
                                    curr, tmp, interV, neg));

                        }

                        /*      Fin del codigo      */
                        
                        @Override
                        public void editingCanceled(ChangeEvent e) {
                           table.getColumnModel().getColumn(1).setCellEditor(new ValuesTable_CellRender(tpFt, nChar, dec, uMeas,
                                    curr, tmp, interV, neg));
                        }

                    });

                }

            }

        }

    }
    
    private void restart() {
        charCount = 0;
        inNum = true;
        inDec = false;
        menos = 0;
        punto = 1;
        sep = 1;
        enteros = 0;
    }

}
