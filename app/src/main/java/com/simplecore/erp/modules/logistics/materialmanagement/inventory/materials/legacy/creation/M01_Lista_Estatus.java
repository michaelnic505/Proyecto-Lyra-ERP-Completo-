package com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.creation;

import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.event.ActionEvent;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import com.simplecore.erp.config.database.DatabaseTables;


public class M01_Lista_Estatus extends javax.swing.JDialog {
    
    public M01_Lista_Estatus(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarModeloTabla();       
        cargar_Estatus_Material();
        addEvents();
        
    }
    


    
    private void cargarModeloTabla(){
        M01_Modelo_Lista_Estatus.set(tablaEstatusMaterial);
    }   
   

    private void cargar_Estatus_Material() {
        
        M01_Cargar_Lista_Estatus_Material nuevaLista = new M01_Cargar_Lista_Estatus_Material();   
        nuevaLista.setTABLA_SQL(DatabaseTables.Estatus_Material.tableName());
        nuevaLista.setJTABLE(tablaEstatusMaterial);
        nuevaLista.cargar_Lista_Tipo_Material();
        
        Tabla_Formato.tablaNoEditable(tablaEstatusMaterial,5);
 
    }

    
    private void addEvents(){
        botonSeleccionar();
        botonSalir();

    }


    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
           seleccionEstatusMaterial();
        });
    }
    
    private void botonSalir(){
        btnSalir.addActionListener((ActionEvent e)->{
            this.dispose();
        });
    }

    private void seleccionEstatusMaterial(){
        
        int fila = tablaEstatusMaterial.getSelectedRow();
            if(fila>-1){

              String ID_TIPO_MATERIAL = tablaEstatusMaterial.getValueAt(fila, 0).toString();
              String DESCRIPCION_TIPO_MAT = tablaEstatusMaterial.getValueAt(fila, 1).toString();

              M01_Creacion_Materiales.ID_ESTATUS_MATERIAL.setText(ID_TIPO_MATERIAL);
              M01_Creacion_Materiales.DESCRIPCION_ESTATUS.setText(DESCRIPCION_TIPO_MAT);

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
        tablaEstatusMaterial = new com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable();

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

        tablaEstatusMaterial.setBackground(new java.awt.Color(203, 229, 242));
        tablaEstatusMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaEstatusMaterial);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
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
    private com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable tablaEstatusMaterial;
    // End of variables declaration//GEN-END:variables
}
