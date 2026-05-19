package com.simplecore.erp.modules.controlling.areas.legacy;

import java.awt.Toolkit;
import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.config.database.utils.Tabla_Formato;


public class A02_Lista_Areas extends javax.swing.JDialog {

    TableRowSorter<DefaultTableModel> filtro;
    
    public A02_Lista_Areas(java.awt.Frame parent, boolean modal) {
        
        super(parent, modal);
        initComponents();
        cargarDatos();
        addEvents();
        
        toolBar2.setVisible(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lyra/access/main_class/icons/pir20.png")));
        
  
    }


    private void cargarDatos() {        
        
        Modelo_Lista_Areas.setLanguage(tablaAreas);
        
        A02_Cargar_Lista_Areas nuevaLista = new A02_Cargar_Lista_Areas();
        nuevaLista.setTable(tablaAreas);
        nuevaLista.cargarDatos();
        
        Tabla_Formato.tablaNoEditable(tablaAreas, 10);
        ConfigurarFiltroEnTabla();
    }

    private void btnMostrarBusqueda() {

        btnMostrarBusqueda.addActionListener((ActionEvent e) -> {
            if (toolBar2.isVisible()) {
                toolBar2.setVisible(false);
            } else {
                toolBar2.setVisible(true);
            }
        });
    }

    private void ConfigurarFiltroEnTabla() {

        tablaAreas.setAutoCreateRowSorter(true);
        filtro = new TableRowSorter<>((DefaultTableModel) tablaAreas.getModel());
        tablaAreas.setRowSorter(filtro);
    }

    private void busquedaTextBox() {
        busquedaTextBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtro.setRowFilter(RowFilter.regexFilter(busquedaTextBox.getText()));
            }
        });
    }

    
    private void addEvents(){
        
        botonSeleccionar();
        botonSalir();
        btnMostrarBusqueda();
        busquedaTextBox();
        
    }

    private void botonSeleccionar(){
        
        btnSeleccionar.addActionListener((ActionEvent e)->{
            seleccionarAreaSuperior();
            
        });
    }
    
    private void botonSalir(){
        
        btnSalir.addActionListener((ActionEvent e)->{
            this.dispose();
        });
    }
    
    private JTextField idArea;
    public void setAreaCodigoTextField(JTextField idArea){
        this.idArea =idArea;
    }
    
    private JTextField descripcionArea;
    public void setAreaDescripcionTextField(JTextField descripcionArea) {
        this.descripcionArea = descripcionArea;
    }
    
        private JTextField idSociedad;
    public void setSociedadCodigoTextField(JTextField idSociedad){
        this.idSociedad =idSociedad;
    }
    
    private JTextField descripcionSociedad;
    public void setSociedadDescripcionTextField(JTextField descripcionSociedad) {
        this.descripcionSociedad = descripcionSociedad;
    }

    
    private void seleccionarAreaSuperior(){
        
         if (tablaAreas.getSelectedRow() > -1) {

            
            String idArea1 = tablaAreas.getValueAt(tablaAreas.getSelectedRow(), 0).toString();
            String descripcionArea1 = tablaAreas.getValueAt(tablaAreas.getSelectedRow(), 1).toString();
            String idSociedad1 = tablaAreas.getValueAt(tablaAreas.getSelectedRow(), 2).toString();
            String descripcionSociedad1 = tablaAreas.getValueAt(tablaAreas.getSelectedRow(), 3).toString();
            
            if(idArea!=null){
                idArea.setText(idArea1);
            }
            if(descripcionArea!=null){
                descripcionArea.setText(descripcionArea1);
            }
            if(idSociedad!=null){
                idSociedad.setText(idSociedad1);
            }
            if(descripcionSociedad!=null){
                descripcionSociedad.setText(descripcionSociedad1);
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
        tablaAreas = new com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable();
        toolBar2 = new javax.swing.JToolBar();
        busquedaTextBox = new javax.swing.JTextField();
        btnMostrarBusqueda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setBackground(new java.awt.Color(172, 200, 225));
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

        tablaAreas.setAutoCreateRowSorter(true);
        tablaAreas.setBackground(new java.awt.Color(202, 219, 236));
        tablaAreas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaAreas);

        toolBar2.setRollover(true);
        toolBar2.add(busquedaTextBox);

        btnMostrarBusqueda.setBackground(new java.awt.Color(226, 210, 144));
        btnMostrarBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/tree_location/desplegable.png"))); // NOI18N

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(btnMostrarBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(toolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ContenedorLayout.setVerticalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorLayout.createSequentialGroup()
                .addComponent(btnMostrarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(toolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))
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
    private javax.swing.JButton btnMostrarBusqueda;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JTextField busquedaTextBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable tablaAreas;
    private javax.swing.JToolBar toolBar2;
    // End of variables declaration//GEN-END:variables
}
