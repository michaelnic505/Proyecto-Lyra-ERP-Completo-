package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceprogramming.maintenance_scheduling.s03_schedule_visualization;

import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.gui.components.labels.JButtonHQ;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.TreeMenu.transactionPanel;

public class S03_Lista_Programacion_Plan extends javax.swing.JPanel {
    
    private TableRowSorter<DefaultTableModel> filtro;
        
    public S03_Lista_Programacion_Plan() {
        initComponents(); 
        addEvents();
        cargarTabla();
    }

    
    private void addEvents(){
        botonSeleccionar();
        botonSalir();

    }

    
    private void cargarTabla(){        
       
        S03_Modelo_Lista_Programacion.setModelo(tablaProgramaciones);
        S03_Cargar_Lista_Programacion nuevaCarga = new S03_Cargar_Lista_Programacion();
        nuevaCarga.setJTABLE(tablaProgramaciones);
        nuevaCarga.cargarDatos();

        Tabla_Formato.tablaNoEditable(tablaProgramaciones,10);
        ConfigurarFiltroEnTabla();

    }

    private void ConfigurarFiltroEnTabla() {

        tablaProgramaciones.setAutoCreateRowSorter(true);
        filtro = new TableRowSorter<>((DefaultTableModel) tablaProgramaciones.getModel());
        tablaProgramaciones.setRowSorter(filtro);
    }

    
    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
            
        });
    }

    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
            PanelLoader.loadPanel(transactionPanel, mainContainerPanel);

        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        cintaControles = new javax.swing.JToolBar();
        btnSalir = new JButtonHQ();
        btnSeleccionar = new JButtonHQ();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jPanel1 = new javax.swing.JPanel();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProgramaciones = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        cintaControles.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cintaControles.setRollover(true);

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnSalir);

        btnSeleccionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.setFocusable(false);
        btnSeleccionar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnSeleccionar);
        cintaControles.add(jSeparator3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        cintaControles.add(jPanel1);

        tablaProgramaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaProgramaciones);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );

        panelScroll.setViewportView(bodyPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cintaControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelScroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(cintaControles, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    public static javax.swing.JButton btnSalir;
    public static javax.swing.JButton btnSeleccionar;
    private javax.swing.JToolBar cintaControles;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JScrollPane panelScroll;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tablaProgramaciones;
    // End of variables declaration//GEN-END:variables
}
