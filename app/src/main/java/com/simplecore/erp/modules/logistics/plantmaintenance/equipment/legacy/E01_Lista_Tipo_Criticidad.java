package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import com.simplecore.erp.gui.components.tables.lastversion.CellEditorLyraTable;
import com.simplecore.erp.gui.components.tables.interfaces.TableEventSimpleButton;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils.Modelo_Criticidad;

public class E01_Lista_Tipo_Criticidad extends javax.swing.JDialog {



    public E01_Lista_Tipo_Criticidad(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();        
        cargarModeloTabla();
        cargarDatos();
        addEvents();
        
        funcionesBotones();
    }

    private void funcionesBotones() {
        
        TableEventSimpleButton e = new TableEventSimpleButton(){
            @Override
            public void selectionRow(int row) {

            }
            
        };
                
        tablaTipoCriticidad.getColumnModel().getColumn(0).setCellEditor(new CellEditorLyraTable(e));
    }

    private void cargarModeloTabla() {
        Modelo_Criticidad.setLanguage(tablaTipoCriticidad);
    }

    private void cargarDatos() {

        E01_Cargar_Lista_Tipo_Criticidad nuevaLista = new E01_Cargar_Lista_Tipo_Criticidad();
        nuevaLista.cargarDatos(tablaTipoCriticidad); 
        
        Tabla_Formato.tablaNoEditable(tablaTipoCriticidad, 10);        
        Tabla_Formato.editableColumn(tablaTipoCriticidad, 0, 0);
        Tabla_Formato.resizeTable(tablaTipoCriticidad, 15);
    }
    
    private void addEvents(){
        botonSeleccionar();
        botonSalir();   
        
    }

    private void botonSeleccionar() {
        btnSeleccionar.addActionListener((ActionEvent e)->{
            seleccionarCriticidad();
        });
    }
    
    private void botonSalir(){
        btnSalir.addActionListener((ActionEvent e)->{
            this.dispose();
        });
    }
    
    private void seleccionarCriticidad() {

        int fila = tablaTipoCriticidad.getSelectedRow();
        if (fila > -1) {

            String ID = tablaTipoCriticidad.getValueAt(fila, 1).toString();
            String DESCRIPCION = tablaTipoCriticidad.getValueAt(fila, 2).toString();

            E01_Crear_Equipo_Panel.idCriticidadTB.setText(ID);
            E01_Crear_Equipo_Panel.descripcionCriticidadTB.setText(DESCRIPCION);

            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Contenedor = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnSalir = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTipoCriticidad = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setBackground(new java.awt.Color(172, 200, 225));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N
        btnSalir.setFocusable(false);
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnSalir);

        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N
        btnSeleccionar.setFocusable(false);
        btnSeleccionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSeleccionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnSeleccionar);

        tablaTipoCriticidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaTipoCriticidad);

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
        );
        ContenedorLayout.setVerticalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tablaTipoCriticidad;
    // End of variables declaration//GEN-END:variables
}
