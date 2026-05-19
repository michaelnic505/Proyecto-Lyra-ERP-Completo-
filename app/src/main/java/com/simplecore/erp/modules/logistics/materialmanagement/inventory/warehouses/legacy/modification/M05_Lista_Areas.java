package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.modification;

import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.event.ActionEvent;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import com.simplecore.erp.config.database.DatabaseTables;


public class M05_Lista_Areas extends javax.swing.JDialog {

    public M05_Lista_Areas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();    
        cargarDatos();
        addEvents();
    }

    private void cargarDatos() {
        
        M05_Cargar_Lista_Area_Almacen nuevaLista = new M05_Cargar_Lista_Area_Almacen();      
        nuevaLista.setTABLA_SQL(DatabaseTables.Areas.tableName());
        nuevaLista.setJTABLE(tablaAreaMaterial);
        nuevaLista.cargar_Lista_Area_Almacen();
        Tabla_Formato.tablaNoEditable(tablaAreaMaterial,10); 
    }
    
    private void addEvents(){
        botonSeleccionar();
        botonSalir();

    }


    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
           seleccionArea();
            
        });
    }
    
    private void botonSalir(){
        btnSalir.addActionListener((ActionEvent e)->{
            this.dispose();
        });
    }

    private void seleccionArea(){
        
        int fila = tablaAreaMaterial.getSelectedRow();
            if(fila>-1){

              String ID_TIPO_MATERIAL = tablaAreaMaterial.getValueAt(fila, 0).toString();
              String DESCRIPCION_TIPO_MAT = tablaAreaMaterial.getValueAt(fila, 1).toString();

              M05_Modificacion_Almacen.ID_AREA_ALMACEN.setText(ID_TIPO_MATERIAL);
              M05_Modificacion_Almacen.DESCRIPCION_AREA_ALMACEN.setText(DESCRIPCION_TIPO_MAT);

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
        tablaAreaMaterial = new com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable();

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

        tablaAreaMaterial.setBackground(new java.awt.Color(203, 229, 242));
        tablaAreaMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaAreaMaterial);

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
    private com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable tablaAreaMaterial;
    // End of variables declaration//GEN-END:variables
}
