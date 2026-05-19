package com.simplecore.erp.modules.system.access.legacy;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import com.simplecore.erp.config.database.DatabaseTables;

public class AU2_Lista_Transacciones_Modulos extends javax.swing.JDialog {


    public AU2_Lista_Transacciones_Modulos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarIconos();
        cargarDatos();
        addEvents();
    }

    private void cargarIconos() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lyra/access/main_class/icons/pir20.png")));

    }


    private void cargarDatos() {
        
        AU2_Lista_Transacciones_Modulo nuevaLista = new AU2_Lista_Transacciones_Modulo();
        nuevaLista.setTABLA_SQL(DatabaseTables.Arbol.tableName());
        nuevaLista.setJTABLA(tablaTransacciones);
        nuevaLista.cargarListaTransaccionesModulos();
        
        Tabla_Formato.tablaNoEditable(tablaTransacciones,10);
       
    }

    private void addEvents() {
        botonSeleccionar();
        botonSalir();
    }

    private void botonSeleccionar() {
        
        btnSeleccionar.addActionListener((ActionEvent e) -> {

        int fila = tablaTransacciones.getSelectedRow();
        if(fila>-1){
                        
           String NODO_ID_TRANSACCION = tablaTransacciones.getValueAt(fila, 1).toString();
           String NODO_DESCRIPCION_TRANSACCION_ENG =  tablaTransacciones.getValueAt(fila, 2).toString();
           String MODULOS_ENG = tablaTransacciones.getValueAt(fila, 3).toString();
           
           AU2_Agregar_Transacciones.transactionTexBox.setText(NODO_ID_TRANSACCION);
           AU2_Agregar_Transacciones.transactionNameBox.setText(NODO_DESCRIPCION_TRANSACCION_ENG);
           AU2_Agregar_Transacciones.moduleTextField.setText(MODULOS_ENG);
           
           
           this.dispose();
        }
        });
    }

    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Contenedor = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnSalir = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTransacciones = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/orders_icons/cerrar20.png"))); // NOI18N
        btnSalir.setFocusable(false);
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnSalir);

        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/orders_icons/OKverde20.png"))); // NOI18N
        btnSeleccionar.setFocusable(false);
        btnSeleccionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSeleccionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnSeleccionar);

        tablaTransacciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaTransacciones);

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        ContenedorLayout.setVerticalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE))
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
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tablaTransacciones;
    // End of variables declaration//GEN-END:variables
}
