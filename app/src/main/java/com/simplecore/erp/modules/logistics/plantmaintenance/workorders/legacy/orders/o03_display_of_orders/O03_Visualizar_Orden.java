package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o03_display_of_orders;


import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Verificar_Existencia_Orden_SQL;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Ordenes_Trabajo;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class O03_Visualizar_Orden extends javax.swing.JPanel {

    
    private JFrame frame;
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy.MM.dd");
    private SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");
    private String numeroOrden;
    private JPanel thisPanel = this;
    private String titulo;
    private String username;
    
    public O03_Visualizar_Orden(String username) {
        this.username = username;
        initComponents();
        frame = (JFrame) SwingUtilities.getWindowAncestor(mainContainerPanel);
        addEvents();

    }   

    private void addEvents(){

        btnSalir();
        buscarOrden();
        abrirListaOrdenes();
        buttonAbrirOrden();
        
    }
 
    public void setTitle() {
        LyraWorkspace.TitleLabel.setText(titulo);
    }

    private void btnSalir() {

        btnSalir_CrearOrden.addActionListener((e) -> {

                JFrame ventanaPrincipal = (JFrame) SwingUtilities.getRoot(this);
                Invoke_JMenuBars.setMenuBar(ventanaPrincipal, ventanaPrincipal.getJMenuBar(),
                        LyraWorkspace.barMenu);

                PanelLoader.loadPanel(treeMenus, mainContainerPanel);

        });
    }

    
    private void buscarOrden(){

        cajaBusquedaOrden.getTextBox().addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                if(Character.isLetter(e.getKeyChar())){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (!cajaBusquedaOrden.getTextBox().getText().isEmpty()) {

                        String orden = cajaBusquedaOrden.getTextBox().getText();
                       
                        if (Verificar_Existencia_Orden_SQL.orderExists(orden)) {
                            
                            ambienteModificacion();
                            
                        } else {

                            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.ORDER_DOES_NOT_EXIST), TypeMessage.ERROR);

                        }

                    }

                }
            }
            
        });
    }
    private void abrirListaOrdenes(){
        cajaBusquedaOrden.getButton().addActionListener((e)->{
            
            Lista_Ordenes_Trabajo lo = new Lista_Ordenes_Trabajo();
            lo.setPanelAnterior(thisPanel);
            lo.setNumOrderTextBox(cajaBusquedaOrden.getTextBox());
            
            PanelLoader.loadPanel(lo, mainContainerPanel);
            
        });
    }
    private void ambienteModificacion() {

        String orden = cajaBusquedaOrden.getTextBox().getText();
        
        O03_Visualizacion_Ordenes_Trabajo mod = new O03_Visualizacion_Ordenes_Trabajo(username);       
        mod.setPanelAnterior(thisPanel);
        mod.cargarDatosCabeceraOrden(orden);
        mod.cargarListaOperaciones(orden);
        mod.cargarListaMateriales(orden);
        mod.addEvents();
        mod.disableComponentsOnVisualizingModule();
        PanelLoader.loadPanel(mod, mainContainerPanel);
     
    }

    private void buttonAbrirOrden() {
        btnAbrirOrden.addActionListener((e) -> {

            if (!cajaBusquedaOrden.getTextBox().getText().isEmpty()) {
                String orden = cajaBusquedaOrden.getTextBox().getText();

                if (Verificar_Existencia_Orden_SQL.orderExists(orden)) {
                    ambienteModificacion();
                } else {
                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.ORDER_DOES_NOT_EXIST), TypeMessage.ERROR);
                }
            }
        });
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        jPanel_Rounded_Corners_Degradado1 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        generalData = new javax.swing.JPanel();
        numeroOrdenLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelIngresarOrden = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        cajaBusquedaOrden = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir_CrearOrden = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnAbrirOrden = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnNueva_Orden = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        jPanel_Rounded_Corners_Degradado1.setColor1(new java.awt.Color(246, 250, 253));
        jPanel_Rounded_Corners_Degradado1.setColor2(new java.awt.Color(202, 216, 237));

        generalData.setBackground(new java.awt.Color(238, 244, 254));
        generalData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        numeroOrdenLB.setText("Orden");
        numeroOrdenLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelIngresarOrden.setText("Ingrese el numero de orden a tratar");
        labelIngresarOrden.setColorBordes(new java.awt.Color(117, 141, 163));
        labelIngresarOrden.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelIngresarOrden.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout generalDataLayout = new javax.swing.GroupLayout(generalData);
        generalData.setLayout(generalDataLayout);
        generalDataLayout.setHorizontalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numeroOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cajaBusquedaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(labelIngresarOrden, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
        );
        generalDataLayout.setVerticalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addComponent(labelIngresarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cajaBusquedaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado1Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado1);
        jPanel_Rounded_Corners_Degradado1.setLayout(jPanel_Rounded_Corners_Degradado1Layout);
        jPanel_Rounded_Corners_Degradado1Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(generalData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(390, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado1Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(generalData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(393, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelScroll.setViewportView(bodyPanel);

        jPanel_Rounded_Corners_Degradado6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado6.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado6.setColor2(new java.awt.Color(202, 216, 237));

        btnSalir_CrearOrden.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir_CrearOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir_CrearOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        btnAbrirOrden.setBackground(new java.awt.Color(226, 210, 144));
        btnAbrirOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAbrirOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N

        btnNueva_Orden.setBackground(new java.awt.Color(226, 210, 144));
        btnNueva_Orden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnNueva_Orden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/new_empty.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado6Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado6);
        jPanel_Rounded_Corners_Degradado6.setLayout(jPanel_Rounded_Corners_Degradado6Layout);
        jPanel_Rounded_Corners_Degradado6Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir_CrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnAbrirOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnNueva_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado6Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNueva_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbrirOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir_CrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
            .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnAbrirOrden;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnNueva_Orden;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir_CrearOrden;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox cajaBusquedaOrden;
    private javax.swing.JPanel generalData;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelIngresarOrden;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined numeroOrdenLB;
    private javax.swing.JScrollPane panelScroll;
    // End of variables declaration//GEN-END:variables


}
