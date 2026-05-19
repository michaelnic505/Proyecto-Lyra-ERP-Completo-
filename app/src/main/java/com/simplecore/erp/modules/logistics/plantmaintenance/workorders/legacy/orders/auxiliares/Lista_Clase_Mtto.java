package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;

import java.awt.Component;
import java.awt.Toolkit;
import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import com.simplecore.erp.config.database.utils.Tabla_Formato;


public class Lista_Clase_Mtto extends javax.swing.JDialog {

    
    public Lista_Clase_Mtto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarDatos();
        addEvents();        
//        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lyra/access/main_class/icons/pir20.png")));
    
    }

    
    private void cargarDatos() {
        
        Lista_Clase_Mtto_Model.set(tablaClases);
        
        Lista_Clase_Mtto_SQL cargar = new Lista_Clase_Mtto_SQL();
        cargar.setTable(tablaClases);
        cargar.cargarDatos();
        
        Tabla_Formato.tablaNoEditable(tablaClases,10); 
        Tabla_Formato.editableColumn(tablaClases, 0, 0);
        Tabla_Formato.resizeTable(tablaClases, 7);
        
    }

    
    private void addEvents(){
        
        botonSeleccionar();
        botonSalir();

    }

    public void setTitleWindow(String title){
        setTitle(title);
    }

    public void setUbicacionComponente(Component comp) {
        setLocationRelativeTo(comp);
    }


    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
           selectType();
            
        });
    }
    
    private void botonSalir(){
        btnSalir.addActionListener((ActionEvent e)->{
            this.dispose();
        });
    }
    
    JTextField codigo;
    JTextField descripcion;
    
    public void setCampos(JTextField codigo,JTextField descripcion){
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    private void selectType(){
        
         if (tablaClases.getSelectedRow() > -1) {
             
            String clase = tablaClases.getValueAt(tablaClases.getSelectedRow(), 1).toString();
            String descripcionClase = tablaClases.getValueAt(tablaClases.getSelectedRow(), 2).toString();

            if(codigo!=null){
                codigo.setText(clase);
            }
            if(descripcion!=null){
                descripcion.setText(descripcionClase);
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
        tablaClases = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.TOOLKIT_MODAL);

        jToolBar1.setBackground(new java.awt.Color(172, 200, 225));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close.png"))); // NOI18N
        btnSalir.setFocusable(false);
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnSalir);

        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/ok_icon.png"))); // NOI18N
        btnSeleccionar.setFocusable(false);
        btnSeleccionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSeleccionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnSeleccionar);

        tablaClases.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaClases.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tablaClases);

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
        );
        ContenedorLayout.setVerticalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
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
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Contenedor;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tablaClases;
    // End of variables declaration//GEN-END:variables
}
