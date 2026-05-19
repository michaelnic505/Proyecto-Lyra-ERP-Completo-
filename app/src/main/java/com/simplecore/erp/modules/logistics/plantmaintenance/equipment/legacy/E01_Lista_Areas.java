package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy;

import java.awt.Toolkit;
import com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils.Modelo_Lista_Areas;
import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import com.simplecore.erp.gui.components.tables.lastversion.CellEditorLyraTable;
import com.simplecore.erp.gui.components.tables.interfaces.TableEventSimpleButton;
import com.simplecore.erp.config.database.utils.Tabla_Formato;


public class E01_Lista_Areas extends javax.swing.JDialog {

    
    public E01_Lista_Areas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarModeloTabla();
        cargarIconos();
        cargarDatos();
        addEvents();
        
        funcionesBotones();
    }
    
    private void funcionesBotones() {

        TableEventSimpleButton event = (int row) -> {
            JOptionPane.showMessageDialog(null, "hola");
        };
        tablaAreas.getColumnModel().getColumn(0).setCellEditor(new CellEditorLyraTable(event));
    }


    private void cargarIconos() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lyra/access/main_class/icons/pir20.png")));

        
    }
    
    private void cargarModeloTabla(){
        Modelo_Lista_Areas.set(tablaAreas);
    }   


    private void cargarDatos() {
        
        E01_Cargar_Lista_Areas nuevaLista = new E01_Cargar_Lista_Areas();      
        nuevaLista.cargar_Lista_Areas(tablaAreas);
        Tabla_Formato.tablaNoEditable(tablaAreas,10);
 
        Tabla_Formato.editableColumn(tablaAreas, 0, 0);
    }

    
    private void addEvents(){
        botonSeleccionar();
        botonSalir();

    }


    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
           seleccionAreaMaterial();
            
        });
    }
    
    private void botonSalir(){
        btnSalir.addActionListener((ActionEvent e)->{
            this.dispose();
        });
    }

    private void seleccionAreaMaterial(){
        
         if (tablaAreas.getSelectedRow() > -1) {
             
            String AREA_SUPERIOR = tablaAreas.getValueAt(tablaAreas.getSelectedRow(), 1).toString();
            String DESCRIPCION_AREA_SUPERIOR = tablaAreas.getValueAt(tablaAreas.getSelectedRow(), 2).toString();
            String CENTRO_COSTOS = tablaAreas.getValueAt(tablaAreas.getSelectedRow(), 3).toString();
            String DESCRIPCION_CENTRO_COSTOS = tablaAreas.getValueAt(tablaAreas.getSelectedRow(), 4).toString();
            
            E01_Crear_Equipo_Panel.idAreaTB.setText(AREA_SUPERIOR);
            E01_Crear_Equipo_Panel.labelDenomArea.setText(DESCRIPCION_AREA_SUPERIOR);
            E01_Crear_Equipo_Panel.centroCostosTB.setText(CENTRO_COSTOS);
            E01_Crear_Equipo_Panel.labelDenomCentroCostos.setText(DESCRIPCION_CENTRO_COSTOS);
            
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
        tablaAreas = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setBackground(new java.awt.Color(121, 163, 215));
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

        tablaAreas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaAreas);

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        ContenedorLayout.setVerticalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
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
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tablaAreas;
    // End of variables declaration//GEN-END:variables
}
