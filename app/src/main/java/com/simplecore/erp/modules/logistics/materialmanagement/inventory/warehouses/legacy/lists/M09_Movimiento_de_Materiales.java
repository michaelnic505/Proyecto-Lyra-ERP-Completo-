package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.lists;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.config.database.tables.tipos_movimientos_material;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class M09_Movimiento_de_Materiales extends javax.swing.JPanel {

    protected static String ESTRATEGIA;  
    
    
    public M09_Movimiento_de_Materiales() {
        
        initComponents(); 
        addEvents();
        componentesAlIniciar();        
    }

    
    protected static void componentesAlIniciar(){
      
        btnSalir.setEnabled(true);
        btnGuardar.setEnabled(true); 
        btnNuevo.setEnabled(false);
        
        NO_DOCUMENTO.setText(null);
        ID_TIPO_DOC.setText(null);
        DESCRIPCION_TIPO_DOC.setText(null);
        CANTIDAD.setText(null);
        PRECIO_UNITARIO.setText(null);
        
        CODIGO_MATERIAL.setText(null);
        DESCRIPCION_MATERIAL.setText(null);
        ID_UM.setText(null);
        DESCRIPCION_UM.setText(null);
        MODELO.setText(null);
        MARCA.setText(null);
        
        SERIE.setText(null);
        OBSERVACION.setText(null);
        CENTRO_COSTOS.setText(null);
        DESCRIPCION_CC.setText(null);
        
        CODIGO_ALMACEN.setText(null);
        DESCRIPCION_ALMACEN.setText(null);
        
    }
    
    protected static void componentesDespuesGuardar(){
       
        btnSalir.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnNuevo.setEnabled(true);
    }
    
    private void addEvents(){
        botonSalir();
        botonCrear();   
        btnNuevo();
        btnBuscarTipoDoc();
        btnCentroCostos();
        btnBuscarMaterial();
        MENU_ITEM_SALIR();
    }

    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
            salir();
        });
    }

    private void MENU_ITEM_SALIR() {
        MENU_ITEM_SALIR.addActionListener((ActionEvent e) -> {
            salir();
        });
    }

    private void salir() {
        if (treeMenus != null) {
            EventQueue.invokeLater(() -> {
                
                cargarMenuBarPrincipal();
                PanelLoader.loadPanel(treeMenus, mainContainerPanel);
               
            });
        } 
    }
    
    
    private void cargarMenuBarPrincipal() {

        JFrame ventanaPrincipal = (JFrame) SwingUtilities.getRoot(this);

        Invoke_JMenuBars.setMenuBar(ventanaPrincipal, ventanaPrincipal.getJMenuBar(),
                LyraWorkspace.barMenu);

    }

    private void botonCrear() {
        btnGuardar.addActionListener((ActionEvent e) -> {
            grabarMovimiento();
        });
    }

    private void btnNuevo() {
        btnNuevo.addActionListener((ActionEvent e) -> {
            componentesAlIniciar();
        });
    }
    
    private void btnBuscarTipoDoc() {
        btnBuscarTipoDoc.addActionListener((ActionEvent e) -> {
            new M09_Lista_Tipo_Doc(new javax.swing.JFrame(), true).setVisible(true);
        });
    }
    
    private void btnBuscarMaterial() {
        btnBuscarMaterial.addActionListener((ActionEvent e) -> {
            verListaMateriales();
        });
    }
        
    private void btnCentroCostos() {
        btnCentroCostos.addActionListener((ActionEvent e) -> {
            new M09_Lista_Centro_Costos(new javax.swing.JFrame(), true).setVisible(true);
        });
    }
    

    private double sumarInventario() {
        
        M09_Verificar_Inventario nuevo = new M09_Verificar_Inventario();
        nuevo.setCODIGO_MATERIAL(CODIGO_MATERIAL.getText());
        nuevo.verificarInventario();        
        return nuevo.getCANTIDAD();
    }
    
    private void verListaMateriales() {

        if (ID_TIPO_DOC.getText().equals(tipos_movimientos_material.INPUT.toString())) {          
            PanelLoader.loadPanel(new M09_Lista_Materiales_Alta(), mainContainerPanel);

        } else if (ID_TIPO_DOC.getText().equals(tipos_movimientos_material.OUTPUT.toString())) {            
            PanelLoader.loadPanel(new M09_Lista_Materiales_Inventario(), mainContainerPanel);
        }
    }
    
    private void grabarMovimiento() {

        if (!(ID_TIPO_DOC.getText().isEmpty()
                | DESCRIPCION_TIPO_DOC.getText().isEmpty()
                | CANTIDAD.getText().isEmpty()
                | PRECIO_UNITARIO.getText().isEmpty()
                | OBSERVACION.getText().isEmpty()
                | CODIGO_ALMACEN.getText().isEmpty()
                | DESCRIPCION_ALMACEN.getText().isEmpty()
                | CODIGO_MATERIAL.getText().isEmpty()
                | DESCRIPCION_MATERIAL.getText().isEmpty()
                | ID_UM.getText().isEmpty()
                | DESCRIPCION_UM.getText().isEmpty()
                )) {

            if (ID_TIPO_DOC.getText().equals(tipos_movimientos_material.OUTPUT.toString())) {

                if (sumarInventario() < Double.parseDouble(CANTIDAD.getText())) {
                   JOptionPane.showMessageDialog(this, NOT.msg(NOT.VALUE_GREATER_THAN),NOT.msg(NOT.TITLE),JOptionPane.ERROR_MESSAGE);
                } else {
                    double cantidad = Double.parseDouble(CANTIDAD.getText());
                    double cantidadConvertida = cantidad * -1;
                    CANTIDAD.setText(String.valueOf(cantidadConvertida));

                    double precio_unitario = Double.parseDouble(PRECIO_UNITARIO.getText());
                    double precio_unitarioConvertido = precio_unitario * -1;
                    PRECIO_UNITARIO.setText(String.valueOf(precio_unitarioConvertido));

                    movimientoMaterial();

                }

            } else if (ID_TIPO_DOC.getText().equals(tipos_movimientos_material.INPUT.toString())) {
                movimientoMaterial();
            }

        } else {
            JOptionPane.showMessageDialog(this, NOT.msg(NOT.EMPTY_FIELDS),NOT.msg(NOT.TITLE),JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void movimientoMaterial() {
        
        M09_Movimiento_Material nuevoMovimiento = new M09_Movimiento_Material();
        nuevoMovimiento.setID_TIPO_DOCUMENTO(ID_TIPO_DOC.getText());
        nuevoMovimiento.setTIPO_DOCUMENTO(DESCRIPCION_TIPO_DOC.getText());
        nuevoMovimiento.setCANTIDAD(Double.parseDouble(CANTIDAD.getText()));
        nuevoMovimiento.setPRECIO_UNITARIO(Double.parseDouble(PRECIO_UNITARIO.getText()));
        nuevoMovimiento.setOBSERVACIONES(OBSERVACION.getText());
        nuevoMovimiento.setCODIGO_ALMACEN(CODIGO_ALMACEN.getText());
        nuevoMovimiento.setDESCRIPCION_ALMACEN(DESCRIPCION_ALMACEN.getText());
        nuevoMovimiento.setCODIGO_MATERIAL(CODIGO_MATERIAL.getText());
        nuevoMovimiento.setDESCRIPCION_MATERIAL(DESCRIPCION_MATERIAL.getText());
        nuevoMovimiento.setID_UM(ID_UM.getText());
        nuevoMovimiento.setDESCRIPCION_UM(DESCRIPCION_UM.getText());
        nuevoMovimiento.setSERIE(SERIE.getText());
        nuevoMovimiento.setMODELO(MODELO.getText());
        nuevoMovimiento.setMARCA(MARCA.getText());
        nuevoMovimiento.crearMovimiento();
        
        NO_DOCUMENTO.setText(nuevoMovimiento.getNo_DOCUMENTO());
        JOptionPane.showMessageDialog(this, NOT.msg(NOT.OPERATION_COMPLETED),NOT.msg(NOT.TITLE), JOptionPane.INFORMATION_MESSAGE);
        
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_M09 = new javax.swing.JMenuBar();
        MENU_LISTA_MATERIALES = new javax.swing.JMenu();
        MENU_MOVIMIENTO = new javax.swing.JMenu();
        MENU_ENTRADA_MATERIALES = new javax.swing.JMenu();
        MENU_ITEM_FACTURA = new javax.swing.JMenuItem();
        MENU_ITEM_REMISION = new javax.swing.JMenuItem();
        MENU_SALIDA_MATERIALES = new javax.swing.JMenu();
        MENU_ITEM_VALE = new javax.swing.JMenuItem();
        MENU_ITEM_EFECTUAR = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_SALIR = new javax.swing.JMenuItem();
        MENU_DATOS = new javax.swing.JMenu();
        MENU_EXPORTAR = new javax.swing.JMenu();
        MENU_ITEM_HOJA_CALCULO = new javax.swing.JMenuItem();
        MENU_AYUDA = new javax.swing.JMenu();
        cintaControles = new javax.swing.JToolBar();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnGuardar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnNuevo = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        PANELCAMPOS = new javax.swing.JPanel();
        LABELCODIGOMATERIAL = new javax.swing.JLabel();
        CODIGO_MATERIAL = new javax.swing.JTextField();
        DESCRIPCION_MATERIAL = new javax.swing.JTextField();
        LABELUM = new javax.swing.JLabel();
        ID_UM = new javax.swing.JTextField();
        DESCRIPCION_UM = new javax.swing.JTextField();
        LABELMODELO = new javax.swing.JLabel();
        MODELO = new javax.swing.JTextField();
        LABELSERIE = new javax.swing.JLabel();
        SERIE = new javax.swing.JTextField();
        NO_DOCUMENTO = new javax.swing.JTextField();
        LABELNUMERODOCUMENTO = new javax.swing.JLabel();
        LABELTIPODOCUMENTO = new javax.swing.JLabel();
        LABELCANTIDAD = new javax.swing.JLabel();
        CANTIDAD = new javax.swing.JTextField();
        LABELPRECIOUNITARIO = new javax.swing.JLabel();
        PRECIO_UNITARIO = new javax.swing.JTextField();
        LABELINFORMACIONMATERIAL = new javax.swing.JLabel();
        LABELOBSERVACIONES = new javax.swing.JLabel();
        LABELALMACEN = new javax.swing.JLabel();
        CODIGO_ALMACEN = new javax.swing.JTextField();
        DESCRIPCION_ALMACEN = new javax.swing.JTextField();
        LABELINFORMACIONMOVIMIENTO = new javax.swing.JLabel();
        SCROLLOBSERVACION = new javax.swing.JScrollPane();
        OBSERVACION = new javax.swing.JTextArea();
        DESCRIPCION_TIPO_DOC = new javax.swing.JTextField();
        ID_TIPO_DOC = new javax.swing.JTextField();
        btnBuscarTipoDoc = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnBuscarMaterial = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        LABELMARCA = new javax.swing.JLabel();
        MARCA = new javax.swing.JTextField();
        LABELCC = new javax.swing.JLabel();
        CENTRO_COSTOS = new javax.swing.JTextField();
        DESCRIPCION_CC = new javax.swing.JTextField();
        btnCentroCostos = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        MENU_LISTA_MATERIALES.setText("Movimiento de Materiales");

        MENU_MOVIMIENTO.setText("Movimiento");

        MENU_ENTRADA_MATERIALES.setText("Entrada de Materiales");

        MENU_ITEM_FACTURA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_FACTURA.setText("Factura");
        MENU_ENTRADA_MATERIALES.add(MENU_ITEM_FACTURA);

        MENU_ITEM_REMISION.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_REMISION.setText("Remision");
        MENU_ENTRADA_MATERIALES.add(MENU_ITEM_REMISION);

        MENU_MOVIMIENTO.add(MENU_ENTRADA_MATERIALES);

        MENU_SALIDA_MATERIALES.setText("Salida de Materiales");

        MENU_ITEM_VALE.setText("Vale de salida");
        MENU_SALIDA_MATERIALES.add(MENU_ITEM_VALE);

        MENU_MOVIMIENTO.add(MENU_SALIDA_MATERIALES);

        MENU_LISTA_MATERIALES.add(MENU_MOVIMIENTO);

        MENU_ITEM_EFECTUAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        MENU_ITEM_EFECTUAR.setText("Efectuar Movimiento");
        MENU_LISTA_MATERIALES.add(MENU_ITEM_EFECTUAR);
        MENU_LISTA_MATERIALES.add(separador);

        MENU_ITEM_SALIR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        MENU_ITEM_SALIR.setText("Salir");
        MENU_LISTA_MATERIALES.add(MENU_ITEM_SALIR);

        menuBar_M09.add(MENU_LISTA_MATERIALES);

        MENU_DATOS.setText("Datos");

        MENU_EXPORTAR.setText("Exportar");

        MENU_ITEM_HOJA_CALCULO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_HOJA_CALCULO.setText("Hoja de Calculo");
        MENU_EXPORTAR.add(MENU_ITEM_HOJA_CALCULO);

        MENU_DATOS.add(MENU_EXPORTAR);

        menuBar_M09.add(MENU_DATOS);

        MENU_AYUDA.setText("Ayuda");
        menuBar_M09.add(MENU_AYUDA);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        cintaControles.setBackground(new java.awt.Color(121, 163, 215));
        cintaControles.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cintaControles.setRollover(true);

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnSalir);
        cintaControles.add(jSeparator2);

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cintaControles.add(btnGuardar);

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setFocusable(false);
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnNuevo);
        cintaControles.add(jSeparator1);

        PANELCAMPOS.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PANELCAMPOS.setAutoscrolls(true);

        LABELCODIGOMATERIAL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELCODIGOMATERIAL.setText("Codigo Material :");

        CODIGO_MATERIAL.setEditable(false);
        CODIGO_MATERIAL.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        DESCRIPCION_MATERIAL.setEditable(false);

        LABELUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELUM.setText("UM :");

        ID_UM.setEditable(false);

        DESCRIPCION_UM.setEditable(false);

        LABELMODELO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELMODELO.setText("Modelo :");

        MODELO.setEditable(false);

        LABELSERIE.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELSERIE.setText("Serie :");

        SERIE.setEditable(false);

        NO_DOCUMENTO.setEditable(false);

        LABELNUMERODOCUMENTO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELNUMERODOCUMENTO.setText("No. Doc. :");

        LABELTIPODOCUMENTO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELTIPODOCUMENTO.setText("Tipo Doc :");

        LABELCANTIDAD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELCANTIDAD.setText("Cant. :");

        CANTIDAD.setEditable(false);

        LABELPRECIOUNITARIO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELPRECIOUNITARIO.setText("P. Unit. :");

        PRECIO_UNITARIO.setEditable(false);

        LABELINFORMACIONMATERIAL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        LABELINFORMACIONMATERIAL.setText("Información del material");

        LABELOBSERVACIONES.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELOBSERVACIONES.setText("Obs.:");

        LABELALMACEN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELALMACEN.setText("Almacén :");

        CODIGO_ALMACEN.setEditable(false);

        DESCRIPCION_ALMACEN.setEditable(false);

        LABELINFORMACIONMOVIMIENTO.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        LABELINFORMACIONMOVIMIENTO.setText("Información de movimiento");

        OBSERVACION.setColumns(20);
        OBSERVACION.setLineWrap(true);
        OBSERVACION.setRows(5);
        SCROLLOBSERVACION.setViewportView(OBSERVACION);

        DESCRIPCION_TIPO_DOC.setEditable(false);

        ID_TIPO_DOC.setEditable(false);

        LABELMARCA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELMARCA.setText("Marca :");

        MARCA.setEditable(false);

        LABELCC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELCC.setText("C. Costo :");

        CENTRO_COSTOS.setEditable(false);

        DESCRIPCION_CC.setEditable(false);

        javax.swing.GroupLayout PANELCAMPOSLayout = new javax.swing.GroupLayout(PANELCAMPOS);
        PANELCAMPOS.setLayout(PANELCAMPOSLayout);
        PANELCAMPOSLayout.setHorizontalGroup(
            PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                        .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LABELCODIGOMATERIAL)
                            .addComponent(LABELUM)
                            .addComponent(LABELSERIE)
                            .addComponent(LABELMODELO)
                            .addComponent(LABELMARCA))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                                .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                                        .addComponent(ID_TIPO_DOC, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DESCRIPCION_TIPO_DOC, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(PRECIO_UNITARIO)
                                    .addComponent(CANTIDAD)
                                    .addComponent(NO_DOCUMENTO))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LABELALMACEN)
                                    .addComponent(LABELOBSERVACIONES)
                                    .addComponent(LABELCC))
                                .addGap(18, 18, 18)
                                .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                                        .addComponent(CENTRO_COSTOS, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DESCRIPCION_CC, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(SCROLLOBSERVACION, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                                        .addComponent(CODIGO_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DESCRIPCION_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49))
                            .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                                .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MARCA, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                                        .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                                                .addComponent(ID_UM, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(DESCRIPCION_UM, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                                                .addComponent(CODIGO_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(3, 3, 3)
                                                .addComponent(DESCRIPCION_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(MODELO, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(5, 5, 5)
                                        .addComponent(btnBuscarMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(SERIE, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 250, Short.MAX_VALUE))))
                    .addComponent(LABELINFORMACIONMOVIMIENTO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator3)
                    .addComponent(LABELINFORMACIONMATERIAL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                        .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LABELPRECIOUNITARIO)
                            .addComponent(LABELNUMERODOCUMENTO)
                            .addComponent(LABELTIPODOCUMENTO)
                            .addComponent(LABELCANTIDAD))
                        .addContainerGap())))
        );
        PANELCAMPOSLayout.setVerticalGroup(
            PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(LABELINFORMACIONMOVIMIENTO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(NO_DOCUMENTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LABELNUMERODOCUMENTO))
                        .addGap(5, 5, 5)
                        .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(LABELTIPODOCUMENTO)
                            .addComponent(ID_TIPO_DOC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DESCRIPCION_TIPO_DOC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(SCROLLOBSERVACION, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PANELCAMPOSLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(LABELOBSERVACIONES)))
                .addGap(10, 10, 10)
                .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCANTIDAD)
                    .addComponent(CANTIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELCC)
                    .addComponent(CENTRO_COSTOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_CC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELPRECIOUNITARIO)
                    .addComponent(PRECIO_UNITARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELALMACEN)
                    .addComponent(CODIGO_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(LABELINFORMACIONMATERIAL)
                .addGap(2, 2, 2)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCODIGOMATERIAL)
                    .addComponent(CODIGO_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELUM)
                    .addComponent(ID_UM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_UM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELMODELO)
                    .addComponent(MODELO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELMARCA)
                    .addComponent(MARCA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PANELCAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELSERIE)
                    .addComponent(SERIE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(PANELCAMPOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(PANELCAMPOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        panelScroll.setViewportView(bodyPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cintaControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(cintaControles, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected static javax.swing.JTextField CANTIDAD;
    protected static javax.swing.JTextField CENTRO_COSTOS;
    protected static javax.swing.JTextField CODIGO_ALMACEN;
    protected static javax.swing.JTextField CODIGO_MATERIAL;
    protected static javax.swing.JTextField DESCRIPCION_ALMACEN;
    protected static javax.swing.JTextField DESCRIPCION_CC;
    protected static javax.swing.JTextField DESCRIPCION_MATERIAL;
    protected static javax.swing.JTextField DESCRIPCION_TIPO_DOC;
    protected static javax.swing.JTextField DESCRIPCION_UM;
    protected static javax.swing.JTextField ID_TIPO_DOC;
    protected static javax.swing.JTextField ID_UM;
    public static javax.swing.JLabel LABELALMACEN;
    public static javax.swing.JLabel LABELCANTIDAD;
    public static javax.swing.JLabel LABELCC;
    public static javax.swing.JLabel LABELCODIGOMATERIAL;
    public static javax.swing.JLabel LABELINFORMACIONMATERIAL;
    public static javax.swing.JLabel LABELINFORMACIONMOVIMIENTO;
    public static javax.swing.JLabel LABELMARCA;
    public static javax.swing.JLabel LABELMODELO;
    public static javax.swing.JLabel LABELNUMERODOCUMENTO;
    public static javax.swing.JLabel LABELOBSERVACIONES;
    public static javax.swing.JLabel LABELPRECIOUNITARIO;
    public static javax.swing.JLabel LABELSERIE;
    public static javax.swing.JLabel LABELTIPODOCUMENTO;
    public static javax.swing.JLabel LABELUM;
    protected static javax.swing.JTextField MARCA;
    private javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_DATOS;
    public static javax.swing.JMenu MENU_ENTRADA_MATERIALES;
    public static javax.swing.JMenu MENU_EXPORTAR;
    public static javax.swing.JMenuItem MENU_ITEM_EFECTUAR;
    public static javax.swing.JMenuItem MENU_ITEM_FACTURA;
    public static javax.swing.JMenuItem MENU_ITEM_HOJA_CALCULO;
    public static javax.swing.JMenuItem MENU_ITEM_REMISION;
    public static javax.swing.JMenuItem MENU_ITEM_SALIR;
    public static javax.swing.JMenuItem MENU_ITEM_VALE;
    public static javax.swing.JMenu MENU_LISTA_MATERIALES;
    public static javax.swing.JMenu MENU_MOVIMIENTO;
    public static javax.swing.JMenu MENU_SALIDA_MATERIALES;
    protected static javax.swing.JTextField MODELO;
    protected static javax.swing.JTextField NO_DOCUMENTO;
    protected static javax.swing.JTextArea OBSERVACION;
    private javax.swing.JPanel PANELCAMPOS;
    protected static javax.swing.JTextField PRECIO_UNITARIO;
    private javax.swing.JScrollPane SCROLLOBSERVACION;
    protected static javax.swing.JTextField SERIE;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btnBuscarMaterial;
    private javax.swing.JButton btnBuscarTipoDoc;
    protected static javax.swing.JButton btnCentroCostos;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btnSalir;
    private javax.swing.JToolBar cintaControles;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public static javax.swing.JMenuBar menuBar_M09;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JPopupMenu.Separator separador;
    // End of variables declaration//GEN-END:variables
}
