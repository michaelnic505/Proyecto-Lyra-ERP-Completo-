package com.simplecore.erp.modules.system.access.legacy;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;
import com.simplecore.erp.utils.notifications.NOT;


public class AU2_Agregar_Transacciones extends javax.swing.JDialog {
   
    public static ArrayList<String> array = new ArrayList<>();
    

    public AU2_Agregar_Transacciones(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarIconos();
        addEvents();
    }

    private void cargarIconos() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lyra/access/main_class/icons/pir20.png")));

    }

    private void addEvents() {
        
        btnAceptar.addActionListener((ActionEvent e) -> {
            guardarNuevaTransaccion();
        });

        btnCancel.addActionListener((ActionEvent e) -> {
            dispose();
        });

        btnLoad.addActionListener((ActionEvent e) -> {
            cargarTablaLocal();
        });
        
        btnTransacciones.addActionListener((ActionEvent e) -> {
            new AU2_Lista_Transacciones_Modulos(new javax.swing.JFrame(), true).setVisible(true);
        });

    }


    private void cargarTablaLocal() {

        if (!(transactionTexBox.getText().isEmpty()
                | transactionNameBox.getText().isEmpty()
                | moduleTextField.getText().isEmpty())) {
            tableTransaction.setValueAt(transactionTexBox.getText(), 0, 1);
            tableTransaction.setValueAt(transactionNameBox.getText(), 0, 2);
            tableTransaction.setValueAt(moduleTextField.getText(), 0, 3);
            tableTransaction.setValueAt(false, 0, 4);
            tableTransaction.setValueAt(false, 0, 5);
            tableTransaction.setValueAt(false, 0, 6);
            tableTransaction.setValueAt(false, 0, 7);
            tableTransaction.setValueAt(false, 0, 8);
            tableTransaction.setValueAt(false, 0, 9);
            btnAceptar.setEnabled(true);
        }

    }

    private void guardarNuevaTransaccion() {
        
        if(tableTransaction.getValueAt(0, 1)!=null
                && tableTransaction.getValueAt(0, 2)!=null
                && tableTransaction.getValueAt(0, 3)!=null)
        {
            
            String transaccion = tableTransaction.getValueAt(0, 1).toString();
            String nombreTransaccion = tableTransaction.getValueAt(0, 2).toString();
            String moduloTransaccion = tableTransaction.getValueAt(0, 3).toString();
            boolean L1 = (boolean)tableTransaction.getValueAt(0, 4);
            boolean L2 = (boolean)tableTransaction.getValueAt(0, 5);
            boolean L3 = (boolean)tableTransaction.getValueAt(0, 6);
            boolean L4 = (boolean)tableTransaction.getValueAt(0, 7);
            boolean L5 = (boolean)tableTransaction.getValueAt(0, 8);
            boolean R6 = (boolean)tableTransaction.getValueAt(0, 9);
            
            
            AU2_Agregar_Transaccion agregarNuevo = new AU2_Agregar_Transaccion();
            agregarNuevo.setTRANSACCION(transaccion);
            agregarNuevo.setNOMBRE_TRANSACCION(nombreTransaccion);
            agregarNuevo.setMODULO_TRANSACCION(moduloTransaccion);

            agregarNuevo.setL1(L1);
            agregarNuevo.setL2(L2);
            agregarNuevo.setL3(L3);
            agregarNuevo.setL4(L4);
            agregarNuevo.setL5(L5);
            agregarNuevo.setR6(R6);

            agregarNuevo.guardarTransaccion();

            Object filas[] = {transaccion, nombreTransaccion, moduloTransaccion, L1, L2, L3, L4, L5, R6};

            LyraTableModel Modelo = (LyraTableModel) AccessManagementSystem.accesosTabla.getModel();
            Modelo.addRow(filas);
            AccessManagementSystem.accesosTabla.setModel(Modelo);
            
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, NOT.msg(NOT.EMPTY_FIELDS),NOT.msg(NOT.TITLE),JOptionPane.WARNING_MESSAGE);
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
        btnLoad = new javax.swing.JButton();
        btnTransacciones = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        LABELTRANSACCION.setText("Transaction :");

        transactionTexBox.setEditable(false);

        LABELMODULENAME.setText("Module name :");

        btnAceptar.setText("Accept");
        btnAceptar.setEnabled(false);

        btnCancel.setText("Cancel");

        tableTransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Transaction", "Name", "Module", "L1", "L2", "L3", "L4", "L5", "R6"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTransaction.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableTransaction.setRowHeight(25);
        jScrollPane4.setViewportView(tableTransaction);
        if (tableTransaction.getColumnModel().getColumnCount() > 0) {
            tableTransaction.getColumnModel().getColumn(0).setPreferredWidth(100);
        }

        btnLoad.setText("Load");

        btnTransacciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/general_windows/lupa15.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                        .addComponent(transactionNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(moduleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAceptar)
                                .addGap(3, 3, 3)
                                .addComponent(btnCancel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLoad)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancel)
                    .addComponent(btnLoad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELTRANSACCION)
                    .addComponent(btnTransacciones, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transactionTexBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transactionNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELMODULENAME)
                    .addComponent(moduleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
    public static javax.swing.JButton btnLoad;
    private javax.swing.JButton btnTransacciones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    protected static javax.swing.JTextField moduleTextField;
    private javax.swing.JTable tableTransaction;
    protected static javax.swing.JTextField transactionNameBox;
    protected static javax.swing.JTextField transactionTexBox;
    // End of variables declaration//GEN-END:variables
}
