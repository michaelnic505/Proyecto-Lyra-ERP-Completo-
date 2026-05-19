
package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.modules.controlling.society.legacy.C01_Extrae_Sociedad_CO;
import com.simplecore.erp.modules.controlling.society.legacy.C01_Verificar_Sociedad_CO;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class U01_Crear_Ubicaciones extends javax.swing.JPanel {
    
    
    boolean verificacionRealizada;    
    private JFrame frame;
    
    public U01_Crear_Ubicaciones() {
        
        initComponents(); 
        addEvents();
        componentesAlInicio();
        frame = (JFrame) SwingUtilities.getWindowAncestor(mainContainerPanel);
    }
    
    
    private void componentesAlInicio(){
        
        btnSalir.setEnabled(true);
        btnCrear.setEnabled(true);
        btnNuevo.setEnabled(true);
        
        idCentroCostos.setEditable(false);
        descripcionCentroCosto.setEditable(false);
        btnCentroCostos.setEnabled(false);
        
        idEmplazamiento.setEditable(false);
        descripcionEmplazamiento.setEditable(false);
        idArea.setEditable(false);
        descripcionArea.setEditable(false);
        idSociedad.setEditable(false);
        descripcionSociedad.setEditable(false);
        
        montajePermitido.setEnabled(false);
        
        idUbicacion.setEditable(true);
        descripcionUbicacion.setEditable(true);
        btnEstructura.setEnabled(true);
        btnVerificar.setEnabled(true);
        btnRehacer.setEnabled(true);
        btnGuardar.setEnabled(true);
        
        btnSociedad.setEnabled(false);
        btnArea.setEnabled(false);
        btnEmplazamiento.setEnabled(false);
        
        grupoPlanificacion.setEditable(false);
        descripcionGrupoPlanif.setEditable(false);
        btnGrupoPlanif.setEnabled(false);
        
        
    }
    private void componentesAlGuardar(){
        
        btnSalir.setEnabled(true);
        btnCrear.setEnabled(false);
        btnNuevo.setEnabled(true);
        
        idCentroCostos.setEditable(false);
        descripcionCentroCosto.setEditable(false);
        btnCentroCostos.setEnabled(false);
        
        idEmplazamiento.setEditable(false);
        descripcionEmplazamiento.setEditable(false);
        idArea.setEditable(false);
        descripcionArea.setEditable(false);
        idSociedad.setEditable(false);
        descripcionSociedad.setEditable(false);
        
        montajePermitido.setEnabled(false);
        
        idUbicacion.setEditable(false);
        descripcionUbicacion.setEditable(false);
        btnEstructura.setEnabled(false);
        btnVerificar.setEnabled(false);
        btnRehacer.setEnabled(false);
        btnGuardar.setEnabled(false);

        btnSociedad.setEnabled(false);
        btnArea.setEnabled(false);
        btnEmplazamiento.setEnabled(false);

        grupoPlanificacion.setEditable(false);
        descripcionGrupoPlanif.setEditable(false);
        btnGrupoPlanif.setEnabled(false);
    }
    private void addEvents(){

        botonSalir();
        botonCrear();
        btnNuevo();
        botonGuardar();
        btnRehacer();
        btnVerificar();
        
        btnSociedad();
        btnArea();
        btnEmplazamiento();
        btnCentroCostos();
        
        menuItemSalir();
        codigoUbicacion();
        denominacionUbicacion();
        btnUbicaciones();
        
        btnGrupoPlanif();
    }
    
    
    private void btnUbicaciones() {

        btnUbicaciones.addActionListener((ActionEvent e) -> {
            new U01_Lista_Ubicaciones(new javax.swing.JFrame(), true).setVisible(true);

        });

    }
    private void btnGrupoPlanif() {

        btnGrupoPlanif.addActionListener((ActionEvent e) -> {
            
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            U01_Lista_GruposPlanificadores gp = new U01_Lista_GruposPlanificadores(frame,true);
            gp.setJTextFields(grupoPlanificacion, descripcionGrupoPlanif);
            gp.setTitle(labelGrupoPlan.getText());
            gp.setVisible(true);
            
        });

    }
    private void btnSociedad(){
        
        btnSociedad.addActionListener((ActionEvent e) -> {
            new U01_Lista_Sociedades(new javax.swing.JFrame(), true).setVisible(true);  
            
        });
        
    }
    
        
    private void btnArea(){
        
        btnArea.addActionListener((ActionEvent e) -> {
            new U01_Lista_Areas(new javax.swing.JFrame(), true).setVisible(true);  
            
        });
        
    }
    private void btnEmplazamiento(){
        
        btnEmplazamiento.addActionListener((ActionEvent e) -> {
            new U01_Lista_Emplazamientos(new javax.swing.JFrame(), true).setVisible(true);  
            
        });
        
    }
    private void btnCentroCostos(){
        
        btnCentroCostos.addActionListener((ActionEvent e) -> {
           
            U01_Lista_Centro_Costos lcc = new U01_Lista_Centro_Costos(frame,true);
          
            lcc.setIdCentroCostos(idCentroCostos);
            lcc.setDescripcionCentroCosto(descripcionCentroCosto);
            lcc.setIdEmplazamiento(idEmplazamiento);
            lcc.setDescripcionEmplazamiento(descripcionEmplazamiento);
            lcc.setIdArea(idArea);
            lcc.setDescripcionArea(descripcionArea);
            lcc.setIdSociedad(idSociedad);
            lcc.setDescripcionSociedad(descripcionSociedad);
            
            lcc.setTitle(labelCentroCosto.getText());
            lcc.setLocationRelativeTo(btnCentroCostos);
            
            lcc.setVisible(true);
            
        });
        
    }

    
    
    private void botonSalir() {
        
        btnSalir.addActionListener((ActionEvent e) -> {
            salir();
        
        });
    }
    private void menuItemSalir(){
        
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
        
        btnCrear.addActionListener((ActionEvent e) -> {
            
            if(!idUbicacion.getText().isEmpty()){
                crearUbicacion();
            
            }else{

                new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.WARNING);
                idUbicacion.requestFocus();
            
            }
        
        });
    }
    
    private void botonGuardar(){
       
        btnGuardar.addActionListener((ActionEvent e) -> {
         
            if(!idUbicacion.getText().isEmpty()){
           
                crearUbicacion();
           
            }else{
                
                new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.WARNING);
                idUbicacion.requestFocus();
           
            }
        });
    }

    private void btnNuevo(){
      
        btnNuevo.addActionListener((ActionEvent e) -> {
            
            componentesAlInicio();
            
            idCentroCostos.setText(null);
            descripcionCentroCosto.setText(null);
            idEmplazamiento.setText(null);
            descripcionEmplazamiento.setText(null);
            idArea.setText(null);
            descripcionArea.setText(null);
            idSociedad.setText(null);
            descripcionSociedad.setText(null);
            montajePermitido.setSelected(false);
            idUbicacion.setText(null);
            descripcionUbicacion.setText(null);
            grupoPlanificacion.setText(null);
            descripcionGrupoPlanif.setText(null);
            
            
        });
    }
    private void btnRehacer() {
       
        btnRehacer.addActionListener((ActionEvent e) -> {
         
            idUbicacion.setText(null);
            idUbicacion.setEditable(true);
            descripcionUbicacion.setText(null);
            descripcionUbicacion.setEditable(true);
            idUbicacion.requestFocus();
       
        });
    }
    private void btnVerificar() {
       
        btnVerificar.addActionListener((ActionEvent e) -> {
        
            if (!idUbicacion.getText().isEmpty()) {
            
                verificarUbicacionBD();
                
          
            } else {
                idUbicacion.requestFocus();
           
            }

        });
    }


    private void codigoUbicacion() {

        
        idUbicacion.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (idUbicacion.isEditable()) {
                    String ubicacion = idUbicacion.getText();
                    int longText = idUbicacion.getText().length() + 1;

                    switch (longText) {
                        case 4 ->
                            idUbicacion.setText(ubicacion + "-");
                        case 8 ->
                            idUbicacion.setText(ubicacion + "-");
                        case 11 ->
                            idUbicacion.setText(ubicacion + "-");
                        case 15 ->
                            idUbicacion.setText(ubicacion + "-");
                        case 19 ->
                            idUbicacion.setText(ubicacion + "-");

                        default -> {
                        }
                    }

                    
                    
                    
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                 
                if (idUbicacion.isEditable()) {
                     
                String texto = idUbicacion.getText().toUpperCase();
                int longtext = idUbicacion.getText().length();

                switch (longtext) {
                   
                    case 3 -> idUbicacion.setText(texto);
                    case 7 -> idUbicacion.setText(texto);
                    case 10 -> idUbicacion.setText(texto);
                    case 14 -> idUbicacion.setText(texto);
                    case 18 -> idUbicacion.setText(texto);
                    case 23 -> idUbicacion.setText(texto);
                    default -> {
                        }
                    }

                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        idUbicacion.setText(texto);
                    }
                }

            }

        });

        idUbicacion.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

                if (idUbicacion.isEditable()) {
                    if (!idUbicacion.getText().isEmpty()) {
                        int lon = idUbicacion.getText().length();

                        if (lon < 3
                                | lon > 3 && lon < 7
                                | lon > 7 && lon < 10
                                | lon > 10 && lon < 14
                                | lon > 14 && lon < 18
                                | lon > 18 && lon < 23) {

                            new SystemMessages(NOT.msg(NOT.DATA_INCONSISTENCY), TypeMessage.WARNING);
                            idUbicacion.requestFocus();

                        } else {
                            idUbicacion.setText(idUbicacion.getText().toUpperCase());
                        }
                    }
                }

            }

        });

        idUbicacion.getDocument().addDocumentListener(new DocumentListener() {
         
            @Override
            public void insertUpdate(DocumentEvent e) {
          
                if (idUbicacion.isEditable()) {
            
                    verificarNivelUbicacion();
                    verificacionRealizada = false;
           
                }

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
              
                if (idUbicacion.isEditable()) {
             
                    verificarNivelUbicacion();
                    verificacionRealizada = false;
               
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
              
                if (idUbicacion.isEditable()) {
              
                    verificarNivelUbicacion();
                    verificacionRealizada = false;
                
                }

            }

        });

    }
    private void denominacionUbicacion() {
       
        descripcionUbicacion.addFocusListener(new FocusListener() {
         
            @Override
            public void focusGained(FocusEvent e) {
            }

           
            @Override
            public void focusLost(FocusEvent e) {
            
                if (descripcionUbicacion.isEditable()) {
            
                    if (!descripcionUbicacion.getText().isEmpty()) {
                   
                        descripcionUbicacion.setText(descripcionUbicacion.getText().toUpperCase());
                   
                    }
                }

            }

        });

        descripcionUbicacion.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
               
                if (descripcionUbicacion.isEditable()) {
                
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                   
                        descripcionUbicacion.setText(descripcionUbicacion.getText().toUpperCase());
                    
                    }
                }

            }

        });
    }
    private void verificarNivelUbicacion() {

        labelCodigoUbicacionSup.setText("");
        labelDenominacionUbicSup.setText("");

        int longitud = idUbicacion.getText().length();

        if (!idUbicacion.getText().isEmpty()) {
            if (longitud < 3) {
                labelNivelUbicacion.setText(null);
           
            } else if (longitud == 3) {
                labelNivelUbicacion.setText(U01_Ubicaciones_Enum.NIVEL_1.getNivel());

            } else if (longitud == 7) {
                labelNivelUbicacion.setText(U01_Ubicaciones_Enum.NIVEL_2.getNivel());


            } else if (longitud == 10) {
                labelNivelUbicacion.setText(U01_Ubicaciones_Enum.NIVEL_3.getNivel());

            } else if (longitud == 14) {
                labelNivelUbicacion.setText(U01_Ubicaciones_Enum.NIVEL_4.getNivel());

            } else if (longitud == 18) {
                labelNivelUbicacion.setText(U01_Ubicaciones_Enum.NIVEL_5.getNivel());

            } else if (longitud == 23) {
                labelNivelUbicacion.setText(U01_Ubicaciones_Enum.NIVEL_6.getNivel());

            }
        } else {
            labelNivelUbicacion.setText(null);

        }
    }
    private void verificarUbicacionBD() {

        String codigoUbicacion = idUbicacion.getText();

        U01_Verificar_Existencia_Ubicacion nuevaRevision = new U01_Verificar_Existencia_Ubicacion();
        nuevaRevision.setCodigoUbicacion(codigoUbicacion);
        nuevaRevision.verificar();

        boolean existe = nuevaRevision.isExiste();

        if (existe) {
       
            new SystemMessages(NOT.msg(NOT.RECORD_ALREADY_EXISTS), TypeMessage.WARNING);
            idUbicacion.requestFocus();
       
        } else {

            
            if(codigoUbicacion.length()==3){
              
                U01_Verificar_Existencia_Ubicacion nuevaRevision2 = new U01_Verificar_Existencia_Ubicacion();
                nuevaRevision2.setCodigoUbicacion(idUbicacion.getText());
                nuevaRevision2.verificar();
                
                if(nuevaRevision2.isExiste()){
              
                    new SystemMessages(NOT.msg(NOT.RECORD_ALREADY_EXISTS), TypeMessage.WARNING);
                    idUbicacion.requestFocus();
                
                }else{
                
                    new SystemMessages(NOT.msg(NOT.LOCATION_LEVEL_1), TypeMessage.WARNING);
                    verificacionRealizada = true;
                
                }
                
            }else if (codigoUbicacion.length() > 3) {
                
                U01_Verificar_Existencia_Ubicacion nuevaRevision3 = new U01_Verificar_Existencia_Ubicacion();                
                String codigoUbicacionSup = "";
                
                switch (codigoUbicacion.length()) {
                    
                    case 7 -> codigoUbicacionSup = codigoUbicacion.substring(0, 3);
                    case 10 -> codigoUbicacionSup = codigoUbicacion.substring(0, 7);
                    case 14 -> codigoUbicacionSup = codigoUbicacion.substring(0, 10);
                    case 18 -> codigoUbicacionSup = codigoUbicacion.substring(0, 14);
                    case 23 -> codigoUbicacionSup = codigoUbicacion.substring(0, 18);
                    default -> {
                    }
                }

                nuevaRevision3.setCodigoUbicacion(codigoUbicacionSup);
                nuevaRevision3.verificar();

                if (nuevaRevision3.isExiste()) {
              
                    labelCodigoUbicacionSup.setText(nuevaRevision3.getCodigoUbicacion());
                    labelDenominacionUbicSup.setText(nuevaRevision3.getDescripcionUbicacion());
                    verificacionRealizada = true;
                    
                    
                } else {
                
                    new SystemMessages(NOT.msg(NOT.SUPERIOR_LOCATION_DOES_NOT_EXIST), TypeMessage.WARNING);
                    idUbicacion.requestFocus();
                    verificacionRealizada = false;
                
                }
            }
            
            verificacionRealizada = true;            
            datosFinancieros();
        }

    }

    
    private void limpiarCampos() {
        
        btnCentroCostos.setEnabled(false);
        btnEmplazamiento.setEnabled(false);
        btnArea.setEnabled(false);
        btnSociedad.setEnabled(false);

        idCentroCostos.setText(null);
        descripcionCentroCosto.setText(null);
        idEmplazamiento.setText(null);
        descripcionEmplazamiento.setText(null);
        idArea.setText(null);
        descripcionArea.setText(null);
        idSociedad.setText(null);
        descripcionSociedad.setText(null);

    }
    private void datosFinancieros() {

        if (Integer.parseInt(labelNivelUbicacion.getText()) == 1) {

            limpiarCampos();
            boolean existeCO = C01_Verificar_Sociedad_CO.verificar();

            if (existeCO) {

                boolean nivel1 = U01_Verificar_Ubicacion_Nivel_1.verificar();

                if (!nivel1) {
                    
                    C01_Extrae_Sociedad_CO company = new C01_Extrae_Sociedad_CO();
                    company.extraer();

                    idSociedad.setText(company.getMatriz());
                    descripcionSociedad.setText(company.getDescripcionMatriz());
                
                }else{
                    
                    new SystemMessages(NOT.msg(NOT.LOCATION_LEVEL1_EXISTS), TypeMessage.WARNING);
                    MULTITAB.setSelectedIndex(1);
                    idUbicacion.requestFocus();
                }

            } else {

                new SystemMessages(NOT.msg(NOT.SOCIETY_CO_NOT_FOUND), TypeMessage.WARNING);

            }

        } else if (Integer.parseInt(labelNivelUbicacion.getText()) == 2) { 
            
            limpiarCampos();
            btnSociedad.setEnabled(true);
            btnArea.setEnabled(true);
            
            
        } else if (Integer.parseInt(labelNivelUbicacion.getText()) == 3) {

            limpiarCampos();

            String ubic = idUbicacion.getText().substring(0, 7).trim();

            U01_Extrae_Datos_Ubicacion_Nivel_2 u2 = new U01_Extrae_Datos_Ubicacion_Nivel_2();
            u2.setIdUbicacion(ubic);
            u2.extraerSociedad();

            idSociedad.setText(u2.getSociedad());
            descripcionSociedad.setText(u2.getDescripcionSociedad());
            idArea.setText(u2.getArea());
            descripcionArea.setText(u2.getDescripcionArea());
            
            btnEmplazamiento.setEnabled(true);
            MULTITAB.setSelectedIndex(1);
            descripcionEmplazamiento.requestFocus();
            

        } else if (Integer.parseInt(labelNivelUbicacion.getText()) == 4) {

            limpiarCampos();
            
            String ubic = idUbicacion.getText().substring(0, 10).trim();
            
            U01_Extrae_Datos_Ubicacion_Nivel_3 u3 = new U01_Extrae_Datos_Ubicacion_Nivel_3();
            u3.setIdUbicacion(ubic);
            u3.extraerDatos();
            
            idSociedad.setText(u3.getSociedad());
            descripcionSociedad.setText(u3.getDescripcionSociedad());
            
            idArea.setText(u3.getArea());
            descripcionArea.setText(u3.getDescripcionArea());
            
            idEmplazamiento.setText(u3.getEmplazamiento());
            descripcionEmplazamiento.setText(u3.getDescripcionEmp());
            
            MULTITAB.setSelectedIndex(1);
            idCentroCostos.requestFocus();           
            btnGrupoPlanif.setEnabled(true);
            
            btnCentroCostos.setEnabled(true);

        } else if (Integer.parseInt(labelNivelUbicacion.getText()) > 4) {

           
            limpiarCampos();
            
            
            String ubic = idUbicacion.getText().substring(0, 14).trim();

            U01_Extrae_Datos_Ubicacion_Nivel_4 u4 = new U01_Extrae_Datos_Ubicacion_Nivel_4();
            u4.setIdUbicacion(ubic);
            u4.extraerDatos();
            

            idCentroCostos.setText(u4.getCentroCosto());
            descripcionCentroCosto.setText(u4.getDescripcionCC());
            
            idEmplazamiento.setText(u4.getEmplazamiento());
            descripcionEmplazamiento.setText(u4.getDescripcionEmp());
            
            idArea.setText(u4.getArea());
            descripcionArea.setText(u4.getDescripcionArea());

            idSociedad.setText(u4.getSociedad());
            descripcionSociedad.setText(u4.getDescripcionSociedad());

            montajePermitido.setEnabled(true);
            MULTITAB.setSelectedIndex(0);
            montajePermitido.requestFocus();

            grupoPlanificacion.setText(u4.getGrupoPlanif());
            descripcionGrupoPlanif.setText(u4.getDescripcionGrupoPlanif());
        }

    }

    
    private void crearUbicacion() {

        if (verificacionRealizada) {

            if (!(idUbicacion.getText().isEmpty()
                    | descripcionUbicacion.getText().isEmpty())) {

                if (Integer.parseInt(labelNivelUbicacion.getText()) == 1) {
                    
                    if (!idSociedad.getText().isEmpty()) {
                        
                        grabarDatos();
                        componentesAlGuardar();
                        new SystemMessages(NOT.msg(NOT.OPERATION_COMPLETED), TypeMessage.SUCCESS);

                    } else {
                        
                        new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.WARNING);
                        MULTITAB.setSelectedIndex(1);
                        descripcionSociedad.requestFocus();
                    }

                } else if (Integer.parseInt(labelNivelUbicacion.getText()) == 2) {

                    if (!(idSociedad.getText().isEmpty()
                            | idArea.getText().isEmpty())) {

                        grabarDatos();
                        componentesAlGuardar();
                        new SystemMessages(NOT.msg(NOT.OPERATION_COMPLETED), TypeMessage.SUCCESS);

                    } else {

                        new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.WARNING);
                        MULTITAB.setSelectedIndex(1);
                        descripcionArea.requestFocus();

                    }

                } else if (Integer.parseInt(labelNivelUbicacion.getText()) == 3) {

                    if (!(idSociedad.getText().isEmpty()
                            | idArea.getText().isEmpty()
                            | idEmplazamiento.getText().isEmpty())) {

                        grabarDatos();
                        componentesAlGuardar();
                        new SystemMessages(NOT.msg(NOT.OPERATION_COMPLETED), TypeMessage.SUCCESS);

                    } else {

                        new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.WARNING);
                        MULTITAB.setSelectedIndex(1);
                        descripcionEmplazamiento.requestFocus();

                    }

                } else if (Integer.parseInt(labelNivelUbicacion.getText()) == 4) {

                    if (!(idSociedad.getText().isEmpty()
                            | idArea.getText().isEmpty()
                            | idEmplazamiento.getText().isEmpty()
                            | idCentroCostos.getText().isEmpty())) {

                        grabarDatos();
                        componentesAlGuardar();
                        new SystemMessages(NOT.msg(NOT.OPERATION_COMPLETED), TypeMessage.SUCCESS);

                    } else {

                        new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.WARNING);
                        MULTITAB.setSelectedIndex(1);
                        idCentroCostos.requestFocus();
                    }

                } else if (Integer.parseInt(labelNivelUbicacion.getText()) > 4) {

                    grabarDatos();
                    componentesAlGuardar();
                    new SystemMessages(NOT.msg(NOT.OPERATION_COMPLETED), TypeMessage.SUCCESS);

                }

            } else {
                
                new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.WARNING);
            }

        } else {

            new SystemMessages(NOT.msg(NOT.VERIFY_DATA_FIRST), TypeMessage.WARNING);
            btnVerificar.requestFocus();

        }

    }
    private void grabarDatos() {

        boolean montaje = montajePermitido.isSelected();

        U01_Crear_Ubicacions ub = new U01_Crear_Ubicacions();
        ub.setIdUbicacion(idUbicacion.getText());
        ub.setDescripcionUbic(descripcionUbicacion.getText());
        ub.setIdUbicacionSup(labelCodigoUbicacionSup.getText());
        ub.setDescripcionUbicSup(labelDenominacionUbicSup.getText());
        ub.setMontajePermitido(montaje);
        ub.setNivel(Integer.parseInt(labelNivelUbicacion.getText()));
        ub.setEstatus(true);
        ub.setTipoRegistro(UbicationType.L.getType());
        ub.setCentroCosto(idCentroCostos.getText());
        ub.setDescripcionCC(descripcionCentroCosto.getText());
        ub.setEmplazamiento(idEmplazamiento.getText());
        ub.setDescripcionEmp(descripcionEmplazamiento.getText());
        ub.setArea(idArea.getText());
        ub.setDescripcionArea(descripcionArea.getText());
        ub.setSociedad(idSociedad.getText());
        ub.setDescripcionSoc(descripcionSociedad.getText());
        ub.setGrupoPlanif(grupoPlanificacion.getText());
        ub.setDescGrupoPlanif(descripcionGrupoPlanif.getText());

        ub.crearUbicacion();

    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_U01 = new javax.swing.JMenuBar();
        MENU_CREACION = new javax.swing.JMenu();
        SUB_MENU_E01_CREAR_EQUIPO = new javax.swing.JMenuItem();
        SUB_MENU_E02_MODIFICAR_EQUIPO = new javax.swing.JMenuItem();
        SUB_MENU_E03_VISUALIZAR_EQUIPO = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_SALIR = new javax.swing.JMenuItem();
        MENU_ACCIONES = new javax.swing.JMenu();
        SUB_MENU_GUARDAR = new javax.swing.JMenuItem();
        SUB_MENU_ESTATUS = new javax.swing.JMenuItem();
        MENU_AYUDA = new javax.swing.JMenu();
        SUB_MENU_INSTRUCCIONES = new javax.swing.JMenuItem();
        SUB_MENU_ACERCA_DE_EQUIPOS = new javax.swing.JMenuItem();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        MULTITAB = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        btnEstructura = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnVerificar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnRehacer = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnGuardar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        LABELCODIGOUBICACION = new javax.swing.JLabel();
        idUbicacion = new javax.swing.JTextField();
        montajePermitido = new javax.swing.JCheckBox();
        LABELDENOMINACION = new javax.swing.JLabel();
        descripcionUbicacion = new javax.swing.JTextField();
        LABELMODELO = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LABELNIVEL = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        LABELUBICACION = new javax.swing.JLabel();
        labelNivelUbicacion = new javax.swing.JLabel();
        LABELCODIGOUBICACIONSUP = new javax.swing.JLabel();
        LABELDENOMINACIONSUP = new javax.swing.JLabel();
        labelCodigoUbicacionSup = new javax.swing.JLabel();
        labelDenominacionUbicSup = new javax.swing.JLabel();
        btnUbicaciones = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        labelGeneralDataTitle = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        idCentroCostos = new javax.swing.JTextField();
        descripcionCentroCosto = new javax.swing.JTextField();
        labelCentroCosto = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelDescripcionCC = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        btnCentroCostos = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jPanel5 = new javax.swing.JPanel();
        labelOrganizacion = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        descripcionArea = new javax.swing.JTextField();
        idArea = new javax.swing.JTextField();
        labelArea = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelSociedad = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        idSociedad = new javax.swing.JTextField();
        descripcionSociedad = new javax.swing.JTextField();
        labelEmplazamiento = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        idEmplazamiento = new javax.swing.JTextField();
        descripcionEmplazamiento = new javax.swing.JTextField();
        btnSociedad = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnArea = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnEmplazamiento = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        labelGrupoPlan = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        grupoPlanificacion = new javax.swing.JTextField();
        descripcionGrupoPlanif = new javax.swing.JTextField();
        btnGrupoPlanif = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jPanel_Rounded_Corners_Degradado5 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnCrear = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnNuevo = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        MENU_CREACION.setText("Creacion de equipo");

        SUB_MENU_E01_CREAR_EQUIPO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_E01_CREAR_EQUIPO.setText("E01 - Nuevo Crear equipo");
        MENU_CREACION.add(SUB_MENU_E01_CREAR_EQUIPO);

        SUB_MENU_E02_MODIFICAR_EQUIPO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_E02_MODIFICAR_EQUIPO.setText("E02 - Nuevo Modificar equipo");
        MENU_CREACION.add(SUB_MENU_E02_MODIFICAR_EQUIPO);

        SUB_MENU_E03_VISUALIZAR_EQUIPO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_E03_VISUALIZAR_EQUIPO.setText("E03 - Nuevo Visualizar equipo");
        MENU_CREACION.add(SUB_MENU_E03_VISUALIZAR_EQUIPO);
        MENU_CREACION.add(separador);

        MENU_ITEM_SALIR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        MENU_ITEM_SALIR.setText("Salir");
        MENU_CREACION.add(MENU_ITEM_SALIR);

        menuBar_U01.add(MENU_CREACION);

        MENU_ACCIONES.setText("Acciones");

        SUB_MENU_GUARDAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        SUB_MENU_GUARDAR.setText("Crear");
        MENU_ACCIONES.add(SUB_MENU_GUARDAR);

        SUB_MENU_ESTATUS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_ESTATUS.setText("Estatus");
        MENU_ACCIONES.add(SUB_MENU_ESTATUS);

        menuBar_U01.add(MENU_ACCIONES);

        MENU_AYUDA.setText("Ayuda");

        SUB_MENU_INSTRUCCIONES.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        SUB_MENU_INSTRUCCIONES.setText("Manual de instruccion");
        MENU_AYUDA.add(SUB_MENU_INSTRUCCIONES);

        SUB_MENU_ACERCA_DE_EQUIPOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        SUB_MENU_ACERCA_DE_EQUIPOS.setText("Acerca de Equipos");
        MENU_AYUDA.add(SUB_MENU_ACERCA_DE_EQUIPOS);

        menuBar_U01.add(MENU_AYUDA);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        MULTITAB.setBackground(new java.awt.Color(202, 216, 237));
        MULTITAB.setForeground(new java.awt.Color(102, 102, 102));
        MULTITAB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(238, 244, 254));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnEstructura.setBackground(new java.awt.Color(226, 210, 144));
        btnEstructura.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        btnEstructura.setText("Estructura");
        btnEstructura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnVerificar.setBackground(new java.awt.Color(226, 210, 144));
        btnVerificar.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        btnVerificar.setText("Verificar");

        btnRehacer.setBackground(new java.awt.Color(226, 210, 144));
        btnRehacer.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        btnRehacer.setText("Rehacer");

        btnGuardar.setBackground(new java.awt.Color(226, 210, 144));
        btnGuardar.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        btnGuardar.setText("Crear");

        LABELCODIGOUBICACION.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        LABELCODIGOUBICACION.setText("Codigo");
        LABELCODIGOUBICACION.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        idUbicacion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        idUbicacion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        idUbicacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        montajePermitido.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        montajePermitido.setText("Montaje permitido");
        montajePermitido.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LABELDENOMINACION.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        LABELDENOMINACION.setText("Denominación");
        LABELDENOMINACION.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        descripcionUbicacion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        descripcionUbicacion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        descripcionUbicacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LABELMODELO.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        LABELMODELO.setText("Modelo ->");

        jLabel2.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jLabel2.setText("XXX-XXX-XX-XXX-XXX-XXXX");

        LABELNIVEL.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        LABELNIVEL.setText("Nivel ->");

        label1.setBackground(new java.awt.Color(204, 204, 204));
        label1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText("1");
        label1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        label2.setBackground(new java.awt.Color(204, 204, 204));
        label2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label2.setText("2");
        label2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        label3.setBackground(new java.awt.Color(204, 204, 204));
        label3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label3.setText("3");
        label3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        label4.setBackground(new java.awt.Color(204, 204, 204));
        label4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label4.setText("4");
        label4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        label5.setBackground(new java.awt.Color(204, 204, 204));
        label5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label5.setText("5");
        label5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        label6.setBackground(new java.awt.Color(204, 204, 204));
        label6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label6.setText("6");
        label6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel1.setBackground(new java.awt.Color(202, 219, 236));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LABELUBICACION.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        LABELUBICACION.setText("Ubicacion");
        LABELUBICACION.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelNivelUbicacion.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        labelNivelUbicacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNivelUbicacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LABELCODIGOUBICACIONSUP.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        LABELCODIGOUBICACIONSUP.setText("Codigo Ubic. Superior");
        LABELCODIGOUBICACIONSUP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LABELDENOMINACIONSUP.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        LABELDENOMINACIONSUP.setText("Denominacion Ubic. Sup.");
        LABELDENOMINACIONSUP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelCodigoUbicacionSup.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        labelCodigoUbicacionSup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCodigoUbicacionSup.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelDenominacionUbicSup.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        labelDenominacionUbicSup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDenominacionUbicSup.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LABELUBICACION, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LABELCODIGOUBICACIONSUP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LABELDENOMINACIONSUP, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDenominacionUbicSup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNivelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodigoUbicacionSup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELUBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNivelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCODIGOUBICACIONSUP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodigoUbicacionSup, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELDENOMINACIONSUP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDenominacionUbicSup, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        btnUbicaciones.setBackground(new java.awt.Color(226, 210, 144));
        btnUbicaciones.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        btnUbicaciones.setText("...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnEstructura)
                        .addGap(344, 344, 344)
                        .addComponent(btnVerificar)
                        .addGap(2, 2, 2)
                        .addComponent(btnRehacer)
                        .addGap(2, 2, 2)
                        .addComponent(btnGuardar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(LABELMODELO)
                                    .addComponent(LABELNIVEL))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(montajePermitido)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(LABELCODIGOUBICACION, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(LABELDENOMINACION, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(idUbicacion, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(descripcionUbicacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(montajePermitido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCODIGOUBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELDENOMINACION, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELMODELO)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELNIVEL)
                    .addComponent(label1)
                    .addComponent(label2)
                    .addComponent(label3)
                    .addComponent(label4)
                    .addComponent(label5)
                    .addComponent(label6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRehacer, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEstructura, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99))
        );

        MULTITAB.addTab("Datos de Ubicacion", jPanel2);

        jPanel3.setBackground(new java.awt.Color(238, 244, 254));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBackground(new java.awt.Color(202, 219, 236));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        labelGeneralDataTitle.setText("Datos generales");
        labelGeneralDataTitle.setColorBordes(new java.awt.Color(117, 141, 163));
        labelGeneralDataTitle.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelGeneralDataTitle.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelCentroCosto.setText("Centro Coste");
        labelCentroCosto.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelDescripcionCC.setText("Descripcion");
        labelDescripcionCC.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        btnCentroCostos.setBackground(new java.awt.Color(226, 210, 144));
        btnCentroCostos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(labelGeneralDataTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDescripcionCC, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCentroCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(idCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 301, Short.MAX_VALUE))
                    .addComponent(descripcionCentroCosto))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(labelGeneralDataTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCentroCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelDescripcionCC, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionCentroCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(202, 219, 236));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        labelOrganizacion.setText("Organizacion");
        labelOrganizacion.setColorBordes(new java.awt.Color(117, 141, 163));
        labelOrganizacion.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelOrganizacion.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelArea.setText("Area");
        labelArea.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelSociedad.setText("Sociedad");
        labelSociedad.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelEmplazamiento.setText("Emplazamiento");
        labelEmplazamiento.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        btnSociedad.setBackground(new java.awt.Color(226, 210, 144));
        btnSociedad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/search.png"))); // NOI18N

        btnArea.setBackground(new java.awt.Color(226, 210, 144));
        btnArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/search.png"))); // NOI18N

        btnEmplazamiento.setBackground(new java.awt.Color(226, 210, 144));
        btnEmplazamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/search.png"))); // NOI18N

        labelGrupoPlan.setText("Grupo planif.");
        labelGrupoPlan.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        btnGrupoPlanif.setBackground(new java.awt.Color(226, 210, 144));
        btnGrupoPlanif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(labelOrganizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelArea, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(labelSociedad, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(labelEmplazamiento, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(labelGrupoPlan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(idArea, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(idEmplazamiento, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idSociedad)
                    .addComponent(grupoPlanificacion))
                .addGap(2, 2, 2)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descripcionGrupoPlanif)
                    .addComponent(descripcionArea, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(descripcionEmplazamiento, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(descripcionSociedad))
                .addGap(2, 2, 2)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGrupoPlanif, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(labelOrganizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelEmplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idEmplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionEmplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(descripcionSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSociedad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelGrupoPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(grupoPlanificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(descripcionGrupoPlanif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGrupoPlanif, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MULTITAB.addTab("Datos Generales", jPanel3);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(434, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(MULTITAB)
                .addContainerGap())
        );

        panelScroll.setViewportView(bodyPanel);

        jPanel_Rounded_Corners_Degradado5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado5.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado5.setColor2(new java.awt.Color(202, 216, 237));

        btnSalir.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        btnCrear.setBackground(new java.awt.Color(226, 210, 144));
        btnCrear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/save.png"))); // NOI18N

        btnNuevo.setBackground(new java.awt.Color(226, 210, 144));
        btnNuevo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/new_document.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado5Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado5);
        jPanel_Rounded_Corners_Degradado5.setLayout(jPanel_Rounded_Corners_Degradado5Layout);
        jPanel_Rounded_Corners_Degradado5Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado5Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel_Rounded_Corners_Degradado5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
            .addComponent(jPanel_Rounded_Corners_Degradado5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel LABELCODIGOUBICACION;
    public static javax.swing.JLabel LABELCODIGOUBICACIONSUP;
    public static javax.swing.JLabel LABELDENOMINACION;
    public static javax.swing.JLabel LABELDENOMINACIONSUP;
    public static javax.swing.JLabel LABELMODELO;
    public static javax.swing.JLabel LABELNIVEL;
    public static javax.swing.JLabel LABELUBICACION;
    public static javax.swing.JMenu MENU_ACCIONES;
    public static javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_CREACION;
    private javax.swing.JMenuItem MENU_ITEM_SALIR;
    public static javax.swing.JTabbedPane MULTITAB;
    public static javax.swing.JMenuItem SUB_MENU_ACERCA_DE_EQUIPOS;
    public static javax.swing.JMenuItem SUB_MENU_E01_CREAR_EQUIPO;
    public static javax.swing.JMenuItem SUB_MENU_E02_MODIFICAR_EQUIPO;
    public static javax.swing.JMenuItem SUB_MENU_E03_VISUALIZAR_EQUIPO;
    public static javax.swing.JMenuItem SUB_MENU_ESTATUS;
    public static javax.swing.JMenuItem SUB_MENU_GUARDAR;
    public static javax.swing.JMenuItem SUB_MENU_INSTRUCCIONES;
    private javax.swing.JPanel bodyPanel;
    protected static javax.swing.JButton btnArea;
    protected static javax.swing.JButton btnCentroCostos;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnCrear;
    protected static javax.swing.JButton btnEmplazamiento;
    public static javax.swing.JButton btnEstructura;
    protected static javax.swing.JButton btnGrupoPlanif;
    public static javax.swing.JButton btnGuardar;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnNuevo;
    public static javax.swing.JButton btnRehacer;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir;
    protected static javax.swing.JButton btnSociedad;
    public static javax.swing.JButton btnUbicaciones;
    public static javax.swing.JButton btnVerificar;
    protected static javax.swing.JTextField descripcionArea;
    protected static javax.swing.JTextField descripcionCentroCosto;
    protected static javax.swing.JTextField descripcionEmplazamiento;
    protected static javax.swing.JTextField descripcionGrupoPlanif;
    protected static javax.swing.JTextField descripcionSociedad;
    private javax.swing.JTextField descripcionUbicacion;
    protected static javax.swing.JTextField grupoPlanificacion;
    protected static javax.swing.JTextField idArea;
    protected static javax.swing.JTextField idCentroCostos;
    protected static javax.swing.JTextField idEmplazamiento;
    protected static javax.swing.JTextField idSociedad;
    protected static javax.swing.JTextField idUbicacion;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado5;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelArea;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCentroCosto;
    private javax.swing.JLabel labelCodigoUbicacionSup;
    private javax.swing.JLabel labelDenominacionUbicSup;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelDescripcionCC;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelEmplazamiento;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelGeneralDataTitle;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelGrupoPlan;
    private javax.swing.JLabel labelNivelUbicacion;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelOrganizacion;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSociedad;
    public static javax.swing.JMenuBar menuBar_U01;
    public static javax.swing.JCheckBox montajePermitido;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JPopupMenu.Separator separador;
    // End of variables declaration//GEN-END:variables
}
