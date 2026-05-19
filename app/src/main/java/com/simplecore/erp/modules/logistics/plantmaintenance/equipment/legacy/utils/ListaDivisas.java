
package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import javax.swing.ImageIcon;


public class ListaDivisas extends javax.swing.JDialog {
    
    
    public static JTextField text;
    private TableRowSorter<DefaultTableModel> filtro;

    
    public ListaDivisas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarDatos();
        BUSQUEDA_TEXTBOX();
        tablaDivisas();
        toolbarBusqueda.setVisible(false);
        btnMostrarBusqueda();
    }

    private void btnMostrarBusqueda() {
        btnMostrarBusqueda.addActionListener((e) -> {
            if (toolbarBusqueda.isVisible()) {

                btnMostrarBusqueda.setIcon(new ImageIcon(getClass().getResource("/icons/auxiliarwindows/deployed.png")));
                toolbarBusqueda.setVisible(false);

            } else {

                btnMostrarBusqueda.setIcon(new ImageIcon(getClass().getResource("/icons/auxiliarwindows/deploy.png")));
                toolbarBusqueda.setVisible(true);

            }
        });
    }

    private void cargarDatos(){        
               
        CargarListadoDivisas cargarListadoDivisas = new CargarListadoDivisas();
        cargarListadoDivisas.cargarDivisas();       
        
        tablaDivisas.setModel(cargarListadoDivisas.getModel());
        Tabla_Formato.tablaNoEditable(tablaDivisas, 5);
        
        ConfigurarFiltroEnTabla();
    }
    
    private void tablaDivisas(){
        tablaDivisas.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
               if(e.getClickCount()==2){
                   String dato = tablaDivisas.getValueAt(tablaDivisas.getSelectedRow(), 2).toString();
                   text.setText(dato);
                   dispose();
               }
            }

        });
    }

    private void ConfigurarFiltroEnTabla() {

        tablaDivisas.setAutoCreateRowSorter(true);
        filtro = new TableRowSorter<>((DefaultTableModel) tablaDivisas.getModel());
        tablaDivisas.setRowSorter(filtro);
    }

    private void BUSQUEDA_TEXTBOX() {
        BUSQUEDA_TEXTBOX.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtro.setRowFilter(RowFilter.regexFilter(BUSQUEDA_TEXTBOX.getText()));
            }
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        toolbarBusqueda = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        BUSQUEDA_TEXTBOX = new javax.swing.JTextField();
        btnMostrarBusqueda = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDivisas = new com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        toolbarBusqueda.setBackground(new java.awt.Color(121, 163, 215));
        toolbarBusqueda.setRollover(true);

        jPanel1.setBackground(new java.awt.Color(121, 163, 215));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BUSQUEDA_TEXTBOX, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BUSQUEDA_TEXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        toolbarBusqueda.add(jPanel1);

        btnMostrarBusqueda.setBackground(new java.awt.Color(226, 210, 144));
        btnMostrarBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/deploy.png"))); // NOI18N

        tablaDivisas.setAutoCreateRowSorter(true);
        tablaDivisas.setBackground(new java.awt.Color(202, 219, 236));
        tablaDivisas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaDivisas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbarBusqueda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMostrarBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnMostrarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(toolbarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BUSQUEDA_TEXTBOX;
    private javax.swing.JButton btnMostrarBusqueda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable tablaDivisas;
    private javax.swing.JToolBar toolbarBusqueda;
    // End of variables declaration//GEN-END:variables
}
