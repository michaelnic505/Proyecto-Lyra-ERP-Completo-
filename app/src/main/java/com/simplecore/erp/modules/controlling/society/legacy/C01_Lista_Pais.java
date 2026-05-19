package com.simplecore.erp.modules.controlling.society.legacy;

import java.awt.Toolkit;
import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.config.database.utils.Tabla_Formato;


public class C01_Lista_Pais extends javax.swing.JDialog {

    
    private TableRowSorter<DefaultTableModel> filtro;
      
        
    public C01_Lista_Pais(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarDatos();
        addEvents();
        
        toolBar2.setVisible(false);        
 
    }


    
    private void cargarDatos() {
        
        Modelo_Lista_Paises.setLanguage(tablaPaises);

        C01_Cargar_Lista_Paises nuevaLista = new C01_Cargar_Lista_Paises();        
        nuevaLista.setTable(tablaPaises);
        nuevaLista.cargarDatos();
        
        Tabla_Formato.tablaNoEditable(tablaPaises, 10);
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

        tablaPaises.setAutoCreateRowSorter(true);
        filtro = new TableRowSorter<>((DefaultTableModel) tablaPaises.getModel());
        tablaPaises.setRowSorter(filtro);
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
            seleccionarPais();
    
        });
    
    }
    
    private void botonSalir(){
       
        btnSalir.addActionListener((ActionEvent e)->{
            this.dispose();
    
        });
    
    }
    
    private void seleccionarPais(){        
        
            if(tablaPaises.getSelectedRow()>-1){      
                
            String idPais = tablaPaises.getValueAt(tablaPaises.getSelectedRow(),0).toString();
            String descripcionPais = tablaPaises.getValueAt(tablaPaises.getSelectedRow(),1).toString();
            
            C01_Crear_Nueva_Compania.idPais.setText(idPais);
            C01_Crear_Nueva_Compania.descripcionPais.setText(descripcionPais);            
            
            this.dispose();
        }      
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Contenedor = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        btnSalir = new JButtonHQ();
        btnSeleccionar = new JButtonHQ();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPaises = new com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable();
        btnMostrarBusqueda = new javax.swing.JButton();
        toolBar2 = new javax.swing.JToolBar();
        busquedaTextBox = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        toolBar.setBackground(new java.awt.Color(172, 200, 225));
        toolBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        toolBar.setRollover(true);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/orders_icons/cerrar20.png"))); // NOI18N
        btnSalir.setFocusable(false);
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(btnSalir);

        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/orders_icons/OKverde20.png"))); // NOI18N
        btnSeleccionar.setFocusable(false);
        btnSeleccionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSeleccionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(btnSeleccionar);

        tablaPaises.setAutoCreateRowSorter(true);
        tablaPaises.setBackground(new java.awt.Color(202, 219, 236));
        tablaPaises.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaPaises);

        btnMostrarBusqueda.setBackground(new java.awt.Color(226, 210, 144));
        btnMostrarBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/tree_location/desplegable.png"))); // NOI18N

        toolBar2.setRollover(true);
        toolBar2.add(busquedaTextBox);

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
            .addComponent(btnMostrarBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(toolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ContenedorLayout.setVerticalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnMostrarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(toolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
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
    private javax.swing.JButton btnMostrarBusqueda;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JTextField busquedaTextBox;
    private javax.swing.JScrollPane jScrollPane1;
    private com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable tablaPaises;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JToolBar toolBar2;
    // End of variables declaration//GEN-END:variables
}
