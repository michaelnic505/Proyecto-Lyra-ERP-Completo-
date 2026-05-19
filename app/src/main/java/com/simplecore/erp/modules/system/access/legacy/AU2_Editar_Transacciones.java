package com.simplecore.erp.modules.system.access.legacy;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AU2_Editar_Transacciones extends javax.swing.JDialog {

    AU2_Lista_Transacciones_Modulos nuevaVentanaLista;
    public static ArrayList<String> array = new ArrayList<>();
    public int numFilaUpdate;

    public AU2_Editar_Transacciones(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarIconos();
        addEvents();
    }

    private void cargarIconos() {
      //  this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lyra/access/main_class/icons/pir20.png")));

    }

    private void addEvents() {

        btnAceptar.addActionListener((ActionEvent e) -> {
            guardarTransaccionEditada();
        });

        btnCancel.addActionListener((ActionEvent e) -> {
            dispose();
        });

        btnTransacciones.addActionListener((ActionEvent e) -> {

        });

    }

    private void guardarTransaccionEditada() {

        int rows = tableTransaction.getRowCount();

        if (rows > 0) {

            Object[] choices = new Object[rows + 3];

            choices[0] = transactionTexBox.getText();
            choices[1] = transactionNameBox.getText();
            choices[2] = moduleTextField.getText();

            for (int i = 0; i < tableTransaction.getColumnCount() - 1; i++) {

                String value = tableTransaction.getValueAt(i, 4).toString();
                if (!(value).isEmpty()) {
                    choices[i + 3] = value;
                } else {
                    choices[i + 3] = "No";
                }
            }

            for (int i = 0; i < choices.length; i++) {

                AccessManagementSystem.accesosTabla.setValueAt(choices[i], numFilaUpdate, i);
            }

            AU2_Editar_Transaccion mandaraSQL = new AU2_Editar_Transaccion();
            mandaraSQL.calcularCantCampos(transactionTexBox.getText());
            mandaraSQL.UpdateTransaccion(transactionTexBox.getText(), numFilaUpdate, AccessManagementSystem.accesosTabla);

            this.dispose();

        } else {
            JOptionPane.showMessageDialog(null, "There is not data", "Incomplete", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LABELTRANSACCION = new javax.swing.JLabel();
        transactionTexBox = new javax.swing.JTextField();
        transactionNameBox = new javax.swing.JTextField();
        LABELMODULENAME = new javax.swing.JLabel();
        moduleTextField = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableTransaction = new javax.swing.JTable();
        btnTransacciones = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        LABELTRANSACCION.setText("Transaction:");

        transactionTexBox.setEditable(false);

        transactionNameBox.setEditable(false);

        LABELMODULENAME.setText("Module name:");

        btnAceptar.setText("Accept");

        btnCancel.setText("Cancel");

        tableTransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Role", "Transaction", "Name", "Module", "Choice"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTransaction.setRowHeight(25);
        jScrollPane4.setViewportView(tableTransaction);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(3, 3, 3)
                        .addComponent(btnCancel))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LABELMODULENAME, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(LABELTRANSACCION))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(btnTransacciones, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(transactionTexBox, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(transactionNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(moduleTextField)))
                            .addGap(87, 87, 87))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancel))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnTransacciones, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transactionTexBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transactionNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELTRANSACCION))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELMODULENAME)
                    .addComponent(moduleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel LABELMODULENAME;
    public static javax.swing.JLabel LABELTRANSACCION;
    public static javax.swing.JButton btnAceptar;
    public static javax.swing.JButton btnCancel;
    private javax.swing.JButton btnTransacciones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    protected static javax.swing.JTextField moduleTextField;
    protected static javax.swing.JTable tableTransaction;
    protected static javax.swing.JTextField transactionNameBox;
    protected static javax.swing.JTextField transactionTexBox;
    // End of variables declaration//GEN-END:variables
}
