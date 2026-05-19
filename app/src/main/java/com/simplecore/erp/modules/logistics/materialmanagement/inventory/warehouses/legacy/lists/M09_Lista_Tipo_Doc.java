package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.lists;

import java.awt.event.ActionEvent;
import com.simplecore.erp.gui.components.labels.JButtonHQ;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import com.simplecore.erp.config.database.tables.tipos_movimientos_material;


public class M09_Lista_Tipo_Doc extends javax.swing.JDialog {

    String VistaSQL;
    
    public M09_Lista_Tipo_Doc(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarDatos();
        addEvents();
    }

    private void cargarDatos() {
        
        M09_Cargar_Tipos_Doc nuevaLista = new M09_Cargar_Tipos_Doc ();     
        nuevaLista.setJTABLA(tablaTiposDocumentos);
        nuevaLista.setTABLA_SQL(VistaSQL);
        nuevaLista.cargarDatos();
        Tabla_Formato.tablaNoEditable(tablaTiposDocumentos,10);
 
    }
    
    private void addEvents(){
        botonSeleccionar();
        botonSalir();

    }


    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
           seleccionTipoDoc();
            
        });
    }
    
    private void botonSalir(){
        btnSalir.addActionListener((ActionEvent e)->{
            this.dispose();
        });
    }

    private void seleccionTipoDoc(){
        
       int fila = tablaTiposDocumentos.getSelectedRow();
       
            if (fila > -1) {

                String ID_TIPO_DOC = tablaTiposDocumentos.getValueAt(fila, 0).toString();
                String DESCRIPCION_TIPO_DOC = tablaTiposDocumentos.getValueAt(fila, 1).toString();

                M09_Movimiento_de_Materiales.ID_TIPO_DOC.setText(ID_TIPO_DOC);
                M09_Movimiento_de_Materiales.DESCRIPCION_TIPO_DOC.setText(DESCRIPCION_TIPO_DOC);


                M09_Movimiento_de_Materiales.CANTIDAD.setEditable(true);          
                M09_Movimiento_de_Materiales.CODIGO_MATERIAL.setText(null);
                M09_Movimiento_de_Materiales.DESCRIPCION_MATERIAL.setText(null);
                M09_Movimiento_de_Materiales.ID_UM.setText(null);
                M09_Movimiento_de_Materiales.DESCRIPCION_UM.setText(null);
                M09_Movimiento_de_Materiales.MARCA.setText(null);
                M09_Movimiento_de_Materiales.SERIE.setText(null);
                M09_Movimiento_de_Materiales.MODELO.setText(null);
                M09_Movimiento_de_Materiales.CODIGO_ALMACEN.setText(null);
                M09_Movimiento_de_Materiales.DESCRIPCION_ALMACEN.setText(null);
                M09_Movimiento_de_Materiales.CENTRO_COSTOS.setText(null);
                M09_Movimiento_de_Materiales.DESCRIPCION_CC.setText(null);

                if(ID_TIPO_DOC.equals(tipos_movimientos_material.INPUT.toString())){
                    M09_Movimiento_de_Materiales.PRECIO_UNITARIO.setEditable(true);
                    M09_Movimiento_de_Materiales.CENTRO_COSTOS.setEditable(false);
                    M09_Movimiento_de_Materiales.DESCRIPCION_CC.setEditable(false);
                    M09_Movimiento_de_Materiales.btnCentroCostos.setEnabled(false);

                } else {
                    M09_Movimiento_de_Materiales.PRECIO_UNITARIO.setEditable(false);
                    M09_Movimiento_de_Materiales.CENTRO_COSTOS.setEditable(false);
                    M09_Movimiento_de_Materiales.DESCRIPCION_CC.setEditable(false);
                    M09_Movimiento_de_Materiales.btnCentroCostos.setEnabled(true);

                }

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
        tablaTiposDocumentos = new com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable();

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

        tablaTiposDocumentos.setBackground(new java.awt.Color(202, 219, 236));
        tablaTiposDocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaTiposDocumentos);

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
    private com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable tablaTiposDocumentos;
    // End of variables declaration//GEN-END:variables
}
