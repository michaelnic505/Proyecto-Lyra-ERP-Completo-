package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.modification;

import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.event.ActionEvent;
import com.simplecore.erp.config.database.utils.Tabla_Formato;


public class M05_Lista_Almacen extends javax.swing.JDialog {

        String VistaSQL;
    
    public M05_Lista_Almacen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();   
        cargarTabla();
        addEvents();
    }

    private void addEvents(){
        botonSeleccionar();
        botonSalir();
    }


    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
         seleccionAlmacen();
            
        });
    }
    
    private void botonSalir(){
        btnSalir.addActionListener((ActionEvent e)->{
            this.dispose();
        });
    }
    
    private void cargarTabla() {

        M05_Cargar_Lista_Almacenes nuevaLista = new M05_Cargar_Lista_Almacenes();
        nuevaLista.setTABLA_SQL(VistaSQL);
        nuevaLista.setJTABLA(tabla_Almacenes);
        nuevaLista.cargar_Lista_Almacenes();
        Tabla_Formato.tablaNoEditable(tabla_Almacenes, 15);
    }
    
    private void seleccionAlmacen() {        
        if (tabla_Almacenes.getSelectedRow() > -1) {
            M05_Modificacion_Almacen.CODIGO_ALMACEN.setText((String) tabla_Almacenes.getValueAt(tabla_Almacenes.getSelectedRow(), 0));            
            this.dispose();
        }        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Contenedor = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnSalir = new JButtonHQ();
        btnSeleccionar = new JButtonHQ();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Almacenes = new com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setBackground(new java.awt.Color(121, 163, 215));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);

        btnSalir.setFocusable(false);
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnSalir);

        btnSeleccionar.setFocusable(false);
        btnSeleccionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSeleccionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnSeleccionar);

        tabla_Almacenes.setBackground(new java.awt.Color(203, 229, 242));
        tabla_Almacenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_Almacenes);

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        ContenedorLayout.setVerticalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Contenedor;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable tabla_Almacenes;
    // End of variables declaration//GEN-END:variables
}
