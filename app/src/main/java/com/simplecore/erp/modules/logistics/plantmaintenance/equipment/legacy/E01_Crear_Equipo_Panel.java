package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraFrame;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils.StatusEquipment;
import com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils.ListaDivisas;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class E01_Crear_Equipo_Panel extends javax.swing.JPanel {

    public E01_Crear_Equipo_Panel() {
        
        initComponents();
        addEvents();
        estadoInicialComponentes();
    }

    private void addEvents() {

        /*metodos de botones*/
        botonTipoEquipo();
        botonCriticidad();
        btnBuscarEquipoSup();
        btnBuscarUbicacion();
        botonSalir();
        botonCrear();

        /*metodos de barra menu*/
        btnMoneda();

        /*metodos de formatos*/
        formatoFechas();
        filesButtons();

        /*metodos de menu bar*/
        menuBarEvents();
        valorAdquisicionTB();

    }

    public void setMenuBarra() {

        JFrame frame = (JFrame) SwingUtilities.getRoot(mainContainerPanel);
        frame.setJMenuBar(menuBar_E01);
        frame.repaint();
    }

    private void formatoFechas() {

        fechaManufactura.setDateFormatString("yyyy/MM/dd");
        fechaManufactura.setDateFormatString("yyyy/MM/dd");
        fechaPuestaEnServicio.setDateFormatString("yyyy/MM/dd");
        fechaAdquisicion.setDateFormatString("yyyy/MM/dd");

    }

    private void estadoInicialComponentes() {

        //botones
        btnSalir.setEnabled(true);
        btnCrear.setEnabled(true);
        btnBuscarTE.setEnabled(true);
        btnBuscarCriticidad.setEnabled(true);
        btnMoneda.setEnabled(true);
        btnEquipoSuperior.setEnabled(true);
        btnBuscarUbicacion.setEnabled(true);
        btnArchivo1.setEnabled(true);
        btnArchivo2.setEnabled(true);
        btnArchivo3.setEnabled(true);

        //textbox
        idEstatusTB.setText(StatusEquipment.A.toString());
        descripcionEstatusTB.setText(StatusEquipment.A.getDescribe());

        idEquipoTB.setEditable(false);
        denominacionEquipoTB.setEditable(true);
        idEstatusTB.setEditable(false);
        descripcionEstatusTB.setEditable(false);

        idTipoEquipoTB.setEditable(false);
        descripcionTipoEquipoTB.setEditable(false);
        idCriticidadTB.setEditable(false);
        descripcionCriticidadTB.setEditable(false);
        marcaTB.setEditable(true);
        modelTB.setEditable(true);
        serieTB.setEditable(true);
        fechaManufactura.setEnabled(true);
        dimensionTB.setEditable(true);
        fechaPuestaEnServicio.setEnabled(true);

        valorAdquisicionTB.setEditable(true);
        monedaTB.setEditable(false);
        fechaAdquisicion.setEnabled(true);

        fabricanteTB.setEditable(true);
        denominacionTipoTB.setEditable(true);
        numeroPiezaTB.setEditable(true);
        fabricacionNumSerieTB.setEditable(true);
        paisProductorTB.setEditable(true);
        anoConstruccionTB.setEnabled(true);
        mesConstruccionTB.setEnabled(true);

        sociedadTB.setEditable(false);
        activoFijoTB.setEditable(false);
        centroCostosTB.setEditable(false);

        idEmplazamiento.setEditable(false);
        idAreaTB.setEditable(false);

        equipoSuperiorTB.setEditable(false);
        ubicacionTB.setEditable(false);

        potenciaTB.setEditable(true);
        rpmTB.setEditable(true);
        torqueTB.setEditable(true);
        maxCapMecTB.setEditable(true);
        minCapMecTB.setEditable(true);
        tipoEnergiaTB.setEditable(true);
        flujoTB.setEditable(true);
        ratioMecTB.setEditable(true);
        frecuenciaMecTB.setEditable(true);
        lubricanteTB.setEditable(true);
        rodamientosTB.setEditable(true);

        voltajeTB.setEditable(true);
        frecuenciaElecTB.setEditable(true);
        potenciaElecTB.setEditable(true);
        maxCapElecTB.setEditable(true);
        minCapElecTB.setEditable(true);
        tipoEnergiaElecTB.setEditable(true);
        eficienciaNominTB.setEditable(true);
        minEficienciaTB.setEditable(true);
        factorPotenciaTB.setEditable(true);
        factorServicioTB.setEditable(true);
        ratioElecTB.setEditable(true);
        fasesElecTB.setEditable(true);
        elevTempTB.setEditable(true);

        volumenTB.setEditable(true);
        frameTB.setEditable(true);
        numCatTB.setEditable(true);
        especNumTB.setEditable(true);
        numSerieTB.setEditable(true);
        pesoTB.setEditable(true);
        claseTB.setEditable(true);
        estiloTB.setEditable(true);
        disenoTB.setEditable(true);
        vibracionTB.setEditable(true);

        fileUnoTB.setEditable(false);
        fileDosTB.setEditable(false);
        fileTresTB.setEditable(false);
        
        grupoPlanificacion.setEditable(true);

    }

    private void clearFields() {

        idEquipoTB.setText(null);
        denominacionEquipoTB.setText(null);
        idEstatusTB.setText(StatusEquipment.A.toString());
        descripcionEstatusTB.setText(StatusEquipment.A.getDescribe());

        idTipoEquipoTB.setText(null);
        descripcionTipoEquipoTB.setText(null);
        idCriticidadTB.setText(null);
        descripcionCriticidadTB.setText(null);
        marcaTB.setText(null);
        modelTB.setText(null);
        serieTB.setText(null);
        fechaManufactura.setDate(null);
        dimensionTB.setText(null);
        fechaPuestaEnServicio.setDate(null);

        valorAdquisicionTB.setText(null);
        monedaTB.setText(null);
        fechaAdquisicion.setDate(null);

        fabricanteTB.setText(null);
        denominacionTipoTB.setText(null);
        numeroPiezaTB.setText(null);
        fabricacionNumSerieTB.setText(null);
        paisProductorTB.setText(null);


        sociedadTB.setText(null);
        activoFijoTB.setText(null);
        centroCostosTB.setText(null);
        labelDenomCentroCostos.setText(null);

        idEmplazamiento.setText(null);
        labelDenomCeEmplaz.setText(null);
        idAreaTB.setText(null);
        labelDenomArea.setText(null);

        equipoSuperiorTB.setText(null);
        labelDescripcionEquipoSuperior.setText(null);
        ubicacionTB.setText(null);
        labelDenominacionUbicacion.setText(null);

        potenciaTB.setText(null);
        rpmTB.setText(null);
        torqueTB.setText(null);
        maxCapMecTB.setText(null);
        minCapMecTB.setText(null);
        tipoEnergiaTB.setText(null);
        flujoTB.setText(null);
        ratioMecTB.setText(null);
        frecuenciaMecTB.setText(null);
        lubricanteTB.setText(null);
        rodamientosTB.setText(null);

        voltajeTB.setText(null);
        frecuenciaElecTB.setText(null);
        potenciaElecTB.setText(null);
        maxCapElecTB.setText(null);
        minCapElecTB.setText(null);
        tipoEnergiaElecTB.setText(null);
        eficienciaNominTB.setText(null);
        minEficienciaTB.setText(null);
        factorPotenciaTB.setText(null);
        factorServicioTB.setText(null);
        ratioElecTB.setText(null);
        fasesElecTB.setText(null);
        elevTempTB.setText(null);

        volumenTB.setText(null);
        frameTB.setText(null);
        numCatTB.setText(null);
        especNumTB.setText(null);
        numSerieTB.setText(null);
        pesoTB.setText(null);
        claseTB.setText(null);
        estiloTB.setText(null);
        disenoTB.setText(null);
        vibracionTB.setText(null);

        fileUnoTB.setText(null);
        fileDosTB.setText(null);
        fileTresTB.setText(null);

        grupoPlanificacion.setText(null);
    }

    private void estadoFinalComponentes() {

        //botones
        btnSalir.setEnabled(true);
        btnCrear.setEnabled(false);
        btnBuscarTE.setEnabled(false);
        btnBuscarCriticidad.setEnabled(false);
        btnMoneda.setEnabled(false);
        btnEquipoSuperior.setEnabled(false);
        btnBuscarUbicacion.setEnabled(false);
        btnArchivo1.setEnabled(false);
        btnArchivo2.setEnabled(false);
        btnArchivo3.setEnabled(false);

        //textbox
        idEstatusTB.setText(StatusEquipment.A.toString());
        descripcionEstatusTB.setText(StatusEquipment.A.getDescribe());

        idEquipoTB.setEditable(false);
        denominacionEquipoTB.setEditable(false);
        idEstatusTB.setEditable(false);
        descripcionEstatusTB.setEditable(false);

        idTipoEquipoTB.setEditable(false);
        descripcionTipoEquipoTB.setEditable(false);
        idCriticidadTB.setEditable(false);
        descripcionCriticidadTB.setEditable(false);
        marcaTB.setEditable(false);
        modelTB.setEditable(false);
        serieTB.setEditable(false);
        fechaManufactura.setEnabled(false);
        dimensionTB.setEditable(false);
        fechaPuestaEnServicio.setEnabled(false);

        valorAdquisicionTB.setEditable(false);
        monedaTB.setEditable(false);
        fechaAdquisicion.setEnabled(false);

        fabricanteTB.setEditable(false);
        denominacionTipoTB.setEditable(false);
        numeroPiezaTB.setEditable(false);
        fabricacionNumSerieTB.setEditable(false);
        paisProductorTB.setEditable(false);
        anoConstruccionTB.setEnabled(false);
        mesConstruccionTB.setEnabled(false);

        sociedadTB.setEditable(false);
        activoFijoTB.setEditable(false);
        centroCostosTB.setEditable(false);

        idEmplazamiento.setEditable(false);
        idAreaTB.setEditable(false);

        equipoSuperiorTB.setEditable(false);
        ubicacionTB.setEditable(false);

        potenciaTB.setEditable(false);
        rpmTB.setEditable(true);
        torqueTB.setEditable(true);
        maxCapMecTB.setEditable(false);
        minCapMecTB.setEditable(false);
        tipoEnergiaTB.setEditable(false);
        flujoTB.setEditable(false);
        ratioMecTB.setEditable(false);
        frecuenciaMecTB.setEditable(false);
        lubricanteTB.setEditable(false);
        rodamientosTB.setEditable(false);

        voltajeTB.setEditable(false);
        frecuenciaElecTB.setEditable(false);
        potenciaElecTB.setEditable(false);
        maxCapElecTB.setEditable(false);
        minCapElecTB.setEditable(false);
        tipoEnergiaElecTB.setEditable(false);
        eficienciaNominTB.setEditable(false);
        minEficienciaTB.setEditable(false);
        factorPotenciaTB.setEditable(false);
        factorServicioTB.setEditable(false);
        ratioElecTB.setEditable(false);
        fasesElecTB.setEditable(false);
        elevTempTB.setEditable(false);

        volumenTB.setEditable(false);
        frameTB.setEditable(false);
        numCatTB.setEditable(false);
        especNumTB.setEditable(false);
        numSerieTB.setEditable(false);
        pesoTB.setEditable(false);
        claseTB.setEditable(false);
        estiloTB.setEditable(false);
        disenoTB.setEditable(false);
        vibracionTB.setEditable(false);

        fileUnoTB.setEditable(false);
        fileDosTB.setEditable(false);
        fileTresTB.setEditable(false);

        grupoPlanificacion.setEditable(false);
    }

    private void valorAdquisicionTB(){
        
        valorAdquisicionTB.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
//                valorAdquisicionTB.setText(CG15_Double_Formato.setFormat(Double.parseDouble(valorAdquisicionTB.getText())));
            }
        });

        
    }
    
    private void botonTipoEquipo(){
        
        btnBuscarTE.addActionListener((ActionEvent e)->{            
            new E01_Lista_Tipo_Equipos(new javax.swing.JFrame(), true).setVisible(true);
       
        });
    }
    
    private void botonCriticidad(){
        
        btnBuscarCriticidad.addActionListener((ActionEvent e)->{            
            new E01_Lista_Tipo_Criticidad(new javax.swing.JFrame(), true).setVisible(true);
       
        });
    }


    private void btnBuscarUbicacion() {
        
        btnBuscarUbicacion.addActionListener((ActionEvent e) -> {      
            equipoSuperiorTB.setText(null);
            labelDescripcionEquipoSuperior.setText(null);
           
            PanelLoader.loadPanel(new E01_Lista_Ubicaciones(), mainContainerPanel);
      
        });
    }
    

    private void btnBuscarEquipoSup() {
        
        btnEquipoSuperior.addActionListener((ActionEvent e) -> {    
          
            ubicacionTB.setText(null);
            labelDenominacionUbicacion.setText(null);
            PanelLoader.loadPanel(new E01_Lista_Equipos(), mainContainerPanel);
        
        });
    }


    private void botonSalir() {
        
        btnSalir.addActionListener((ActionEvent e) -> {            
            salir();
      
        });
    }

    private void salir() {

        if (treeMenus != null) {

            EventQueue.invokeLater(() -> {

                cargarMenuBarPrincipal();
                PanelLoader.loadPanel(LyraWorkspace.mainTreeMenu(), LyraWorkspace.mainModulesContainer());
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
            createEquipment();

        });
    }

    private void btnMoneda() {

        btnMoneda.addActionListener((ActionEvent e) -> {

            ListaDivisas.text = monedaTB;
            new ListaDivisas(new javax.swing.JFrame(), true).setVisible(true);

        });
    }


    private void filesButtons() {

        btnArchivo1.addActionListener(((e) -> {

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");

            chooser.setFileFilter(filter);

            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                fileUnoTB.setText(chooser.getSelectedFile().getAbsolutePath());
            } else {
                fileUnoTB.setText(null);
            }

        }));

        btnArchivo2.addActionListener(((e) -> {

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");

            chooser.setFileFilter(filter);

            int returnVal = chooser.showOpenDialog(this);
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                fileDosTB.setText(chooser.getSelectedFile().getAbsolutePath());
            } else {
                fileDosTB.setText(null);
            }

        }));

        btnArchivo3.addActionListener(((e) -> {

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");

            chooser.setFileFilter(filter);

            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                fileTresTB.setText(chooser.getSelectedFile().getAbsolutePath());
            } else {
                fileTresTB.setText(null);
            }

        }));
    }

    private void createEquipment(){

        if (!(denominacionEquipoTB.getText().isEmpty()
                | idEstatusTB.getText().isEmpty()
                | descripcionEstatusTB.getText().isEmpty()
                | idTipoEquipoTB.getText().isEmpty()
                | descripcionTipoEquipoTB.getText().isEmpty()
                | idCriticidadTB.getText().isEmpty()
                | descripcionCriticidadTB.getText().isEmpty()
                | marcaTB.getText().isEmpty()
                | modelTB.getText().isEmpty()
                | serieTB.getText().isEmpty()
                | fechaManufactura.getDate() == null
                | fechaPuestaEnServicio.getDate() == null
                | ubicacionTB.getText().isEmpty())) {

                String manuDate;
                String commisionDate;
                String adquisitionDate;

                if (fechaManufactura.getDate()!= null) {
  //                  manuDate = CG20_Fechas_DateChooser.getFechaFormato(fechaManufactura);
                } else {
                    manuDate = null;
                }

                if (fechaPuestaEnServicio.getDate()!= null) {
//                    commisionDate = CG20_Fechas_DateChooser.getFechaFormato(fechaPuestaEnServicio);
                } else {
                    commisionDate = null;
                }

                if (fechaAdquisicion.getDate()!= null) {
    //                adquisitionDate = CG20_Fechas_DateChooser.getFechaFormato(fechaAdquisicion);
                } else {
                    adquisitionDate = null;
                }



                Crear_Equipo newEquipment = new Crear_Equipo();
                newEquipment.setName(denominacionEquipoTB.getText());
                newEquipment.setStatus_id(idEstatusTB.getText());
                newEquipment.setStatus_name(descripcionEstatusTB.getText());
                
                newEquipment.setType_equipment_id(idTipoEquipoTB.getText());
                newEquipment.setType_name(descripcionTipoEquipoTB.getText());
                newEquipment.setCriticality_id(idCriticidadTB.getText());
                newEquipment.setCriticality_name(descripcionCriticidadTB.getText());
                newEquipment.setBrand(marcaTB.getText());
                newEquipment.setModel(modelTB.getText());
                newEquipment.setSerial(serieTB.getText());
//                newEquipment.setManufacture_date(manuDate);
                newEquipment.setDimensions(dimensionTB.getText());
  //              newEquipment.setCommissioning_date(commisionDate);
                
                newEquipment.setAcquisition_value(valorAdquisicionTB.getText());
                newEquipment.setCurrency(monedaTB.getText());
    //            newEquipment.setAcquisition_date(adquisitionDate);
                
                newEquipment.setManufacturer(fabricanteTB.getText());
                newEquipment.setManufacturer_type(denominacionTipoTB.getText());
                newEquipment.setPart_number(numeroPiezaTB.getText());
                newEquipment.setManu_serial_number(fabricacionNumSerieTB.getText());
                newEquipment.setProducing_country(paisProductorTB.getText());
                newEquipment.setYear_construction(anoConstruccionTB.getYear());
                newEquipment.setMonth_construction(mesConstruccionTB.getMonth());
                
                
                
                newEquipment.setSociety(sociedadTB.getText());
                newEquipment.setSocietyName(labelDenomSociedad.getText());
                
                newEquipment.setFixed_assets(activoFijoTB.getText());
                newEquipment.setFixed_assetsName(labelDenominacionActivoFijo.getText());
                        
                newEquipment.setCost_center(centroCostosTB.getText());
                newEquipment.setCost_center_name(labelDenomCentroCostos.getText());
                
                newEquipment.setEmplazement_center(idEmplazamiento.getText());
                newEquipment.setEmplazement_center_name(labelDenomCeEmplaz.getText());
                newEquipment.setArea_id(idAreaTB.getText());
                newEquipment.setArea_name(labelDenomArea.getText());

                if (equipoSuperiorTB.getText().isEmpty()) {
                    newEquipment.setTop_equipment(null);
                    newEquipment.setTop_equipment_name(null);
                }

                newEquipment.setTop_equipment(equipoSuperiorTB.getText());
                newEquipment.setTop_equipment_name(labelDescripcionEquipoSuperior.getText());

                newEquipment.setLocation(ubicacionTB.getText());
                newEquipment.setLocation_name(labelDenominacionUbicacion.getText());

                newEquipment.setMec_power(potenciaTB.getText());
                newEquipment.setRpm(rpmTB.getText());
                newEquipment.setTorque(torqueTB.getText());
                newEquipment.setMec_max_cap(maxCapMecTB.getText());
                newEquipment.setMec_min_cap(minCapMecTB.getText());
                newEquipment.setMec_energy_type(tipoEnergiaTB.getText());
                newEquipment.setFlow(flujoTB.getText());
                newEquipment.setMec_ratio(ratioMecTB.getText());
                newEquipment.setMec_frecuency(frecuenciaMecTB.getText());
                newEquipment.setLubricant(lubricanteTB.getText());
                newEquipment.setBearing(rodamientosTB.getText());
                
                newEquipment.setVoltage(voltajeTB.getText());
                newEquipment.setElec_frecuency(frecuenciaElecTB.getText());
                newEquipment.setElec_power(potenciaElecTB.getText());
                newEquipment.setElec_max_cap(maxCapElecTB.getText());
                newEquipment.setElec_min_cap(minCapElecTB.getText());
                newEquipment.setElec_energy_type(tipoEnergiaElecTB.getText());
                newEquipment.setNominal_eff(eficienciaNominTB.getText());
                newEquipment.setMin_eff(minEficienciaTB.getText());
                newEquipment.setPower_factor(factorPotenciaTB.getText());
                newEquipment.setService_factor(factorServicioTB.getText());
                newEquipment.setElec_ratio(ratioElecTB.getText());
                newEquipment.setPhases(fasesElecTB.getText());
                newEquipment.setRise_temp(elevTempTB.getText());
                
                newEquipment.setVolume(volumenTB.getText());
                newEquipment.setFrame(frameTB.getText());
                newEquipment.setCat_num(numCatTB.getText());
                newEquipment.setEspec_num(especNumTB.getText());
                newEquipment.setSerial_num(numSerieTB.getText());
                newEquipment.setWeight(pesoTB.getText());
                newEquipment.setClasses(claseTB.getText());
                newEquipment.setStyle(estiloTB.getText());
                newEquipment.setDesign(disenoTB.getText());
                newEquipment.setVibration(vibracionTB.getText());
              
                if (fileUnoTB.getText().isEmpty()) {
                    newEquipment.setFile1(null);
                } else {
                    newEquipment.setFile1(new File(fileUnoTB.getText()));
                }

                if (fileDosTB.getText().isEmpty()) {
                    newEquipment.setFile2(null);
                } else {
                    newEquipment.setFile2(new File(fileDosTB.getText()));
                }

                if (fileTresTB.getText().isEmpty()) {
                    newEquipment.setFile3(null);
                } else {
                    newEquipment.setFile3(new File(fileTresTB.getText()));
                }

//                newEquipment.setCreated_by(LyraWorkspace.getUserName());

                String patt  ="yyyy/MM/dd";
                DateFormat df = new SimpleDateFormat(patt);    
                Date today = Calendar.getInstance().getTime();
                
                newEquipment.setCreation_date(df.format(today));
                
                newEquipment.setModified_by(null);
                newEquipment.setModification_date(null);
                
                newEquipment.setGrupoPlanificacion(grupoPlanificacion.getText());
                newEquipment.setDescripcionGruopoPlanif(descripcionGrupoPlanif.getText());
                
                
                newEquipment.create();

                new SystemMessages(NOT.msg(NOT.OPERATION_COMPLETED), TypeMessage.SUCCESS);
                idEquipoTB.setText(String.valueOf(newEquipment.getEquipment_id()));

                E01_Create_Equipment_Locations location = new E01_Create_Equipment_Locations();
                location.setID_UBICACION(idEquipoTB.getText());
                location.setDENOMINACION_UBICACION(denominacionEquipoTB.getText());
                
                if(!equipoSuperiorTB.getText().isEmpty()){
                    
                    location.setID_UBICACION_SUP(equipoSuperiorTB.getText());
                    location.setDENOMINACION_UBICACION_SUP(labelDescripcionEquipoSuperior.getText());
                    
                    
                
                }else{
                
                    location.setID_UBICACION_SUP(ubicacionTB.getText());
                    location.setDENOMINACION_UBICACION_SUP(labelDenominacionUbicacion.getText());
               
                }
                
                location.setESTATUS(true);
                location.setGrupoPlasnif(grupoPlanificacion.getText());
                location.setDescripcionGrupoPlanif(descripcionGrupoPlanif.getText());
                location.crearUbicacion();

                

            
            
            estadoFinalComponentes();

        } else {
            
            new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.SUCCESS);
            
        }

    }
    
    
    private void menuBarEvents(){
        
        //Menu cretion
        menuCreateNewEquipment.addActionListener(((e)->{
             estadoInicialComponentes();
             clearFields();
        }));
        
        menuModifyEquipment.addActionListener(((e)->{

        }));

        menuVisualizeEquipment.addActionListener(((e) -> {

        }));

        menuExit.addActionListener(((e)->{
             salir();
        }));

        //Menu Actions
        
        menuCreate.addActionListener(((e)->{
             createEquipment();
        }));
        
        menuStatus.addActionListener(((e)->{
             new SystemMessages(labelStatus.getText(), TypeMessage.SUCCESS);
        }));
        
        //Menu help
        
        menuInstruction.addActionListener(((e)->{
             
        }));


        
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_E01 = new javax.swing.JMenuBar();
        menuCreation = new javax.swing.JMenu();
        menuCreateNewEquipment = new javax.swing.JMenuItem();
        menuModifyEquipment = new javax.swing.JMenuItem();
        menuVisualizeEquipment = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        menuExit = new javax.swing.JMenuItem();
        menuActions = new javax.swing.JMenu();
        menuCreate = new javax.swing.JMenuItem();
        menuStatus = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuInstruction = new javax.swing.JMenuItem();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        MULTITAB = new javax.swing.JTabbedPane();
        general = new javax.swing.JPanel();
        generalData = new javax.swing.JPanel();
        labelGeneralDataTitle = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelEquipmentType = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        idTipoEquipoTB = new javax.swing.JTextField();
        descripcionTipoEquipoTB = new javax.swing.JTextField();
        labelCriticality = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        idCriticidadTB = new javax.swing.JTextField();
        descripcionCriticidadTB = new javax.swing.JTextField();
        labelBrand = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelSeries = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelModel = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        marcaTB = new javax.swing.JTextField();
        modelTB = new javax.swing.JTextField();
        serieTB = new javax.swing.JTextField();
        labelManufactureDate = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        fechaManufactura = new com.toedter.calendar.JDateChooser();
        labelDimension = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        dimensionTB = new javax.swing.JTextField();
        labelServiceStartDate = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        fechaPuestaEnServicio = new com.toedter.calendar.JDateChooser();
        btnBuscarTE = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnBuscarCriticidad = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        provisioning = new javax.swing.JPanel();
        valorAdquisicionTB = new javax.swing.JTextField();
        monedaTB = new javax.swing.JTextField();
        fechaAdquisicion = new com.toedter.calendar.JDateChooser();
        labelProvisioningDataTitle = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelAdquisitionValue = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelAdquisitionDate = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        btnMoneda = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        manufacturingData = new javax.swing.JPanel();
        fabricanteTB = new javax.swing.JTextField();
        denominacionTipoTB = new javax.swing.JTextField();
        numeroPiezaTB = new javax.swing.JTextField();
        fabricacionNumSerieTB = new javax.swing.JTextField();
        paisProductorTB = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        anoConstruccionTB = new com.toedter.calendar.JYearChooser();
        mesConstruccionTB = new com.toedter.calendar.JMonthChooser();
        labelManufacturer = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelTypeName = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelNumPart = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelNumSeries = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelCountryManufacturer = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelYearMonthManufactured = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelManufactureDataTitle = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        organization = new javax.swing.JPanel();
        imputation = new javax.swing.JPanel();
        sociedadTB = new javax.swing.JTextField();
        activoFijoTB = new javax.swing.JTextField();
        labelImputacionTitle = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelSociedad = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelActivoFijo = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelCostCenter = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        centroCostosTB = new javax.swing.JTextField();
        labelDenomSociedad = new javax.swing.JLabel();
        labelDenominacionActivoFijo = new javax.swing.JLabel();
        labelDenomCentroCostos = new javax.swing.JLabel();
        emplazament = new javax.swing.JPanel();
        idAreaTB = new javax.swing.JTextField();
        labelLocationTitle = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelArea = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelCentroEmplazamiento = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        idEmplazamiento = new javax.swing.JTextField();
        labelDenomCeEmplaz = new javax.swing.JLabel();
        labelDenomArea = new javax.swing.JLabel();
        installation = new javax.swing.JPanel();
        equipoSuperiorTB = new javax.swing.JTextField();
        labelDescripcionEquipoSuperior = new javax.swing.JLabel();
        ubicacionTB = new javax.swing.JTextField();
        labelDenominacionUbicacion = new javax.swing.JLabel();
        labelInstallationDataTitle = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelTopEquipment = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelLocation = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        btnEquipoSuperior = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnBuscarUbicacion = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        installation1 = new javax.swing.JPanel();
        grupoPlanificacion = new javax.swing.JTextField();
        descripcionGrupoPlanif = new javax.swing.JLabel();
        labelDatosPlanificacion = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelGrupoPlan = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        manufacturer = new javax.swing.JPanel();
        mechanic = new javax.swing.JPanel();
        labelMechanicalTitle = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelPower = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelRPM = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        potenciaTB = new javax.swing.JTextField();
        rpmTB = new javax.swing.JTextField();
        labelTorque = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        torqueTB = new javax.swing.JTextField();
        maxCapMecTB = new javax.swing.JTextField();
        labelMaxCapMec = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelMinCapMec = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        minCapMecTB = new javax.swing.JTextField();
        labelMechanicalEnergyType = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        tipoEnergiaTB = new javax.swing.JTextField();
        frecuenciaMecTB = new javax.swing.JTextField();
        labelMecanicalFrecuency = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        ratioMecTB = new javax.swing.JTextField();
        flujoTB = new javax.swing.JTextField();
        labelMechanicalRatio = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelFlow = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        rodamientosTB = new javax.swing.JTextField();
        labelBearing = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelLubricant = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        lubricanteTB = new javax.swing.JTextField();
        electric = new javax.swing.JPanel();
        labelElectricalTitle = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelVoltage = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelElectricFrecuency = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        voltajeTB = new javax.swing.JTextField();
        frecuenciaElecTB = new javax.swing.JTextField();
        labelElectricPower = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        potenciaElecTB = new javax.swing.JTextField();
        maxCapElecTB = new javax.swing.JTextField();
        labelElectricCapMax = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelElectricCapMin = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        minCapElecTB = new javax.swing.JTextField();
        labelElectricEnergyType = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        tipoEnergiaElecTB = new javax.swing.JTextField();
        factorPotenciaTB = new javax.swing.JTextField();
        labelPowerFactor = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        minEficienciaTB = new javax.swing.JTextField();
        eficienciaNominTB = new javax.swing.JTextField();
        labelMinimumEfficiency = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelNominalEfficiency = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        ratioElecTB = new javax.swing.JTextField();
        labelElectricRatio = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelServiceFactor = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        factorServicioTB = new javax.swing.JTextField();
        labelPhases = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        fasesElecTB = new javax.swing.JTextField();
        labelRiseTemp = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        elevTempTB = new javax.swing.JTextField();
        design = new javax.swing.JPanel();
        designdata = new javax.swing.JPanel();
        labelDesignTitle = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelVolume = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelFrame = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        volumenTB = new javax.swing.JTextField();
        frameTB = new javax.swing.JTextField();
        labelCatalogueNumber = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        numCatTB = new javax.swing.JTextField();
        especNumTB = new javax.swing.JTextField();
        labelSpecNumber = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelSerialNumber = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        numSerieTB = new javax.swing.JTextField();
        labelWeight = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        pesoTB = new javax.swing.JTextField();
        disenoTB = new javax.swing.JTextField();
        labelDesign = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        estiloTB = new javax.swing.JTextField();
        claseTB = new javax.swing.JTextField();
        labelStyle = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelClass = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelVibration = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        vibracionTB = new javax.swing.JTextField();
        drawing = new javax.swing.JPanel();
        labelDibujos = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelArchivo1 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        fileUnoTB = new javax.swing.JTextField();
        btnArchivo1 = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        labelArchivo2 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        fileDosTB = new javax.swing.JTextField();
        btnArchivo2 = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        labelArchivo3 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        fileTresTB = new javax.swing.JTextField();
        btnArchivo3 = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        idEquipoTB = new javax.swing.JTextField();
        idEstatusTB = new javax.swing.JTextField();
        descripcionEstatusTB = new javax.swing.JTextField();
        denominacionEquipoTB = new javax.swing.JTextField();
        labelIdEquipment = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelEquipmentName = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelStatus = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnCrear = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        menuCreation.setText("Creacion de equipo");

        menuCreateNewEquipment.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuCreateNewEquipment.setText("E01 - Nuevo Crear equipo");
        menuCreation.add(menuCreateNewEquipment);

        menuModifyEquipment.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuModifyEquipment.setText("E02 - Nuevo Modificar equipo");
        menuCreation.add(menuModifyEquipment);

        menuVisualizeEquipment.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuVisualizeEquipment.setText("E03 - Nuevo Visualizar equipo");
        menuCreation.add(menuVisualizeEquipment);
        menuCreation.add(separador);

        menuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        menuExit.setText("Salir");
        menuCreation.add(menuExit);

        menuBar_E01.add(menuCreation);

        menuActions.setText("Acciones");

        menuCreate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        menuCreate.setText("Crear");
        menuActions.add(menuCreate);

        menuStatus.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuStatus.setText("Estatus");
        menuActions.add(menuStatus);

        menuBar_E01.add(menuActions);

        menuHelp.setText("Ayuda");

        menuInstruction.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        menuInstruction.setText("Manual de instruccion");
        menuHelp.add(menuInstruction);

        menuBar_E01.add(menuHelp);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        MULTITAB.setBackground(new java.awt.Color(202, 216, 237));
        MULTITAB.setForeground(new java.awt.Color(102, 102, 102));
        MULTITAB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        general.setBackground(new java.awt.Color(238, 244, 254));
        general.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        generalData.setBackground(new java.awt.Color(202, 219, 236));
        generalData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        labelGeneralDataTitle.setText("Datos generales");
        labelGeneralDataTitle.setColorBordes(new java.awt.Color(117, 141, 163));
        labelGeneralDataTitle.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelGeneralDataTitle.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelEquipmentType.setText("Equip.Type");
        labelEquipmentType.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelCriticality.setText("Criticality");
        labelCriticality.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelBrand.setText("Brand");
        labelBrand.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelSeries.setText("Series");
        labelSeries.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelModel.setText("Model");
        labelModel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelManufactureDate.setText("Manufact.date");
        labelManufactureDate.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        fechaManufactura.setForeground(new java.awt.Color(255, 255, 255));

        labelDimension.setText("TamañoDimens.");
        labelDimension.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelServiceStartDate.setText("PstaEnServDesde");
        labelServiceStartDate.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        fechaPuestaEnServicio.setForeground(new java.awt.Color(255, 255, 255));

        btnBuscarTE.setBackground(new java.awt.Color(226, 210, 144));
        btnBuscarTE.setText("...");

        btnBuscarCriticidad.setBackground(new java.awt.Color(226, 210, 144));
        btnBuscarCriticidad.setText("...");

        javax.swing.GroupLayout generalDataLayout = new javax.swing.GroupLayout(generalData);
        generalData.setLayout(generalDataLayout);
        generalDataLayout.setHorizontalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addComponent(labelGeneralDataTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(generalDataLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelModel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSeries, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCriticality, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEquipmentType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalDataLayout.createSequentialGroup()
                        .addComponent(idCriticidadTB, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(descripcionCriticidadTB, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(btnBuscarCriticidad, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(generalDataLayout.createSequentialGroup()
                        .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(serieTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                                .addComponent(modelTB, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(marcaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(generalDataLayout.createSequentialGroup()
                                .addComponent(idTipoEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(descripcionTipoEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(btnBuscarTE, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(67, 67, 67)
                        .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelManufactureDate, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelServiceStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDimension, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fechaManufactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dimensionTB)
                            .addComponent(fechaPuestaEnServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
        );
        generalDataLayout.setVerticalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addComponent(labelGeneralDataTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelEquipmentType, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idTipoEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionTipoEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarTE, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelManufactureDate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaManufactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCriticality, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idCriticidadTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionCriticidadTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCriticidad, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(marcaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelModel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDimension, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dimensionTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelSeries, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serieTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelServiceStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaPuestaEnServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        provisioning.setBackground(new java.awt.Color(202, 219, 236));
        provisioning.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));
        provisioning.setForeground(new java.awt.Color(221, 221, 221));

        fechaAdquisicion.setForeground(new java.awt.Color(255, 255, 255));

        labelProvisioningDataTitle.setText("Datos de Aprovicionamiento");
        labelProvisioningDataTitle.setColorBordes(new java.awt.Color(117, 141, 163));
        labelProvisioningDataTitle.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelProvisioningDataTitle.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelAdquisitionValue.setText("Valor adquisición");
        labelAdquisitionValue.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelAdquisitionDate.setText("Fecha adquis.");
        labelAdquisitionDate.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        btnMoneda.setBackground(new java.awt.Color(226, 210, 144));
        btnMoneda.setText("...");

        javax.swing.GroupLayout provisioningLayout = new javax.swing.GroupLayout(provisioning);
        provisioning.setLayout(provisioningLayout);
        provisioningLayout.setHorizontalGroup(
            provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(provisioningLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAdquisitionValue, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(valorAdquisicionTB, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(monedaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelAdquisitionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(fechaAdquisicion, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(provisioningLayout.createSequentialGroup()
                .addComponent(labelProvisioningDataTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        provisioningLayout.setVerticalGroup(
            provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(provisioningLayout.createSequentialGroup()
                .addComponent(labelProvisioningDataTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelAdquisitionValue, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorAdquisicionTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monedaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAdquisitionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaAdquisicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        manufacturingData.setBackground(new java.awt.Color(202, 219, 236));
        manufacturingData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));
        manufacturingData.setForeground(new java.awt.Color(221, 221, 221));

        jLabel1.setText("/");

        labelManufacturer.setText("Fabricante");
        labelManufacturer.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelTypeName.setText("Denomin. tipo");
        labelTypeName.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelNumPart.setText("Num.Pieza fabric");
        labelNumPart.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelNumSeries.setText("Fabr. Num. Serie");
        labelNumSeries.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelCountryManufacturer.setText("Pais productor");
        labelCountryManufacturer.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelYearMonthManufactured.setText("AñoMes constr.");
        labelYearMonthManufactured.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelManufactureDataTitle.setText("Datos de fabricación");
        labelManufactureDataTitle.setColorBordes(new java.awt.Color(117, 141, 163));
        labelManufactureDataTitle.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelManufactureDataTitle.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout manufacturingDataLayout = new javax.swing.GroupLayout(manufacturingData);
        manufacturingData.setLayout(manufacturingDataLayout);
        manufacturingDataLayout.setHorizontalGroup(
            manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manufacturingDataLayout.createSequentialGroup()
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelManufactureDataTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(manufacturingDataLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNumPart, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNumSeries, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0)
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fabricacionNumSerieTB)
                    .addComponent(fabricanteTB, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(denominacionTipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroPiezaTB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelYearMonthManufactured, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCountryManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(manufacturingDataLayout.createSequentialGroup()
                        .addComponent(anoConstruccionTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mesConstruccionTB, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(paisProductorTB))
                .addGap(10, 10, 10))
        );
        manufacturingDataLayout.setVerticalGroup(
            manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manufacturingDataLayout.createSequentialGroup()
                .addComponent(labelManufactureDataTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fabricanteTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCountryManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paisProductorTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(denominacionTipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelYearMonthManufactured, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anoConstruccionTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mesConstruccionTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(2, 2, 2)
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelNumPart, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroPiezaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelNumSeries, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fabricacionNumSerieTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout generalLayout = new javax.swing.GroupLayout(general);
        general.setLayout(generalLayout);
        generalLayout.setHorizontalGroup(
            generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(generalData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manufacturingData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(provisioning, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        generalLayout.setVerticalGroup(
            generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(generalData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(provisioning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manufacturingData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        MULTITAB.addTab("General", general);

        organization.setBackground(new java.awt.Color(238, 244, 254));
        organization.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        imputation.setBackground(new java.awt.Color(202, 219, 236));
        imputation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        labelImputacionTitle.setText("Imputación");
        labelImputacionTitle.setColorBordes(new java.awt.Color(117, 141, 163));
        labelImputacionTitle.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelImputacionTitle.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelSociedad.setText("Sociedad");
        labelSociedad.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelActivoFijo.setText("Activo fijo");
        labelActivoFijo.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelCostCenter.setText("Cost Center");
        labelCostCenter.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelDenomSociedad.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelDenominacionActivoFijo.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelDenomCentroCostos.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout imputationLayout = new javax.swing.GroupLayout(imputation);
        imputation.setLayout(imputationLayout);
        imputationLayout.setHorizontalGroup(
            imputationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imputationLayout.createSequentialGroup()
                .addGroup(imputationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelImputacionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(imputationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(imputationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(imputationLayout.createSequentialGroup()
                                .addGroup(imputationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelActivoFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(imputationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(imputationLayout.createSequentialGroup()
                                        .addComponent(sociedadTB, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelDenomSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(imputationLayout.createSequentialGroup()
                                        .addComponent(activoFijoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelDenominacionActivoFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(imputationLayout.createSequentialGroup()
                                .addComponent(labelCostCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(centroCostosTB, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelDenomCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        imputationLayout.setVerticalGroup(
            imputationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imputationLayout.createSequentialGroup()
                .addComponent(labelImputacionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(imputationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sociedadTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDenomSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(imputationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelActivoFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(activoFijoTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDenominacionActivoFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(imputationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCostCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(centroCostosTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDenomCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        emplazament.setBackground(new java.awt.Color(202, 219, 236));
        emplazament.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        labelLocationTitle.setText("Emplazamiento");
        labelLocationTitle.setColorBordes(new java.awt.Color(117, 141, 163));
        labelLocationTitle.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelLocationTitle.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelArea.setText("Area");
        labelArea.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelCentroEmplazamiento.setText("Emplazamiento");
        labelCentroEmplazamiento.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelDenomCeEmplaz.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelDenomArea.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout emplazamentLayout = new javax.swing.GroupLayout(emplazament);
        emplazament.setLayout(emplazamentLayout);
        emplazamentLayout.setHorizontalGroup(
            emplazamentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emplazamentLayout.createSequentialGroup()
                .addGroup(emplazamentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLocationTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(emplazamentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(emplazamentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(emplazamentLayout.createSequentialGroup()
                                .addComponent(labelArea, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(idAreaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelDenomArea, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(emplazamentLayout.createSequentialGroup()
                                .addComponent(labelCentroEmplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(idEmplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelDenomCeEmplaz, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        emplazamentLayout.setVerticalGroup(
            emplazamentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emplazamentLayout.createSequentialGroup()
                .addComponent(labelLocationTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(emplazamentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCentroEmplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idEmplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDenomCeEmplaz, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(emplazamentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idAreaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDenomArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        installation.setBackground(new java.awt.Color(202, 219, 236));
        installation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        labelDescripcionEquipoSuperior.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        labelDescripcionEquipoSuperior.setText(" ");

        labelDenominacionUbicacion.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        labelDenominacionUbicacion.setText(" ");

        labelInstallationDataTitle.setText("Datos de instalación");
        labelInstallationDataTitle.setColorBordes(new java.awt.Color(117, 141, 163));
        labelInstallationDataTitle.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelInstallationDataTitle.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelTopEquipment.setText("Top equipment");
        labelTopEquipment.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelLocation.setText("Location");
        labelLocation.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        btnEquipoSuperior.setBackground(new java.awt.Color(226, 210, 144));
        btnEquipoSuperior.setText("...");

        btnBuscarUbicacion.setBackground(new java.awt.Color(226, 210, 144));
        btnBuscarUbicacion.setText("...");

        javax.swing.GroupLayout installationLayout = new javax.swing.GroupLayout(installation);
        installation.setLayout(installationLayout);
        installationLayout.setHorizontalGroup(
            installationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(installationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(installationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTopEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(installationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(equipoSuperiorTB, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addComponent(ubicacionTB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(installationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(installationLayout.createSequentialGroup()
                        .addComponent(btnEquipoSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelDescripcionEquipoSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(installationLayout.createSequentialGroup()
                        .addComponent(btnBuscarUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelDenominacionUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(installationLayout.createSequentialGroup()
                .addComponent(labelInstallationDataTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        installationLayout.setVerticalGroup(
            installationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(installationLayout.createSequentialGroup()
                .addComponent(labelInstallationDataTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(installationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelTopEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equipoSuperiorTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEquipoSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDescripcionEquipoSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(installationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubicacionTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDenominacionUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        installation1.setBackground(new java.awt.Color(202, 219, 236));
        installation1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        descripcionGrupoPlanif.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionGrupoPlanif.setText(" ");

        labelDatosPlanificacion.setText("Datos de planificación");
        labelDatosPlanificacion.setColorBordes(new java.awt.Color(117, 141, 163));
        labelDatosPlanificacion.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelDatosPlanificacion.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelGrupoPlan.setText("Grupo planif.");
        labelGrupoPlan.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout installation1Layout = new javax.swing.GroupLayout(installation1);
        installation1.setLayout(installation1Layout);
        installation1Layout.setHorizontalGroup(
            installation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(installation1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelGrupoPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(grupoPlanificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(descripcionGrupoPlanif, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(installation1Layout.createSequentialGroup()
                .addComponent(labelDatosPlanificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        installation1Layout.setVerticalGroup(
            installation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(installation1Layout.createSequentialGroup()
                .addComponent(labelDatosPlanificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(installation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelGrupoPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grupoPlanificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionGrupoPlanif, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout organizationLayout = new javax.swing.GroupLayout(organization);
        organization.setLayout(organizationLayout);
        organizationLayout.setHorizontalGroup(
            organizationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(organizationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(organizationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(installation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(emplazament, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imputation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(installation1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        organizationLayout.setVerticalGroup(
            organizationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(organizationLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(imputation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emplazament, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(installation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(installation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        MULTITAB.addTab("Organization", organization);

        manufacturer.setBackground(new java.awt.Color(238, 244, 254));
        manufacturer.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        mechanic.setBackground(new java.awt.Color(202, 219, 236));
        mechanic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        labelMechanicalTitle.setText("Mechanical data");
        labelMechanicalTitle.setColorBordes(new java.awt.Color(117, 141, 163));
        labelMechanicalTitle.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelMechanicalTitle.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelPower.setText("Power ");
        labelPower.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelRPM.setText("RPM");
        labelRPM.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelTorque.setText("Torque");
        labelTorque.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelMaxCapMec.setText("Maximum capacity");
        labelMaxCapMec.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelMinCapMec.setText("Minimum capacity");
        labelMinCapMec.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelMechanicalEnergyType.setText("Type of energy ");
        labelMechanicalEnergyType.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelMecanicalFrecuency.setText("Frequency ");
        labelMecanicalFrecuency.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelMechanicalRatio.setText("Ratio ");
        labelMechanicalRatio.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelFlow.setText("Flow ");
        labelFlow.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelBearing.setText("Bearings");
        labelBearing.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelLubricant.setText("Lubricant ");
        labelLubricant.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout mechanicLayout = new javax.swing.GroupLayout(mechanic);
        mechanic.setLayout(mechanicLayout);
        mechanicLayout.setHorizontalGroup(
            mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mechanicLayout.createSequentialGroup()
                .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMechanicalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mechanicLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mechanicLayout.createSequentialGroup()
                                .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelMinCapMec, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelMaxCapMec, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelMechanicalEnergyType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(minCapMecTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(maxCapMecTB)
                                    .addComponent(tipoEnergiaTB)))
                            .addGroup(mechanicLayout.createSequentialGroup()
                                .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelRPM, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelPower, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelTorque, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(rpmTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(potenciaTB, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(torqueTB))))))
                .addGap(36, 36, 36)
                .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMechanicalRatio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFlow, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBearing, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLubricant, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMecanicalFrecuency, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ratioMecTB, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flujoTB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rodamientosTB, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lubricanteTB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frecuenciaMecTB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        mechanicLayout.setVerticalGroup(
            mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mechanicLayout.createSequentialGroup()
                .addComponent(labelMechanicalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelPower, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(potenciaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFlow, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flujoTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelRPM, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rpmTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMechanicalRatio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ratioMecTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelTorque, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(torqueTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMecanicalFrecuency, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frecuenciaMecTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelMaxCapMec, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxCapMecTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLubricant, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lubricanteTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelMinCapMec, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minCapMecTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBearing, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rodamientosTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(mechanicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelMechanicalEnergyType, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoEnergiaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        electric.setBackground(new java.awt.Color(202, 219, 236));
        electric.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        labelElectricalTitle.setText("Electrical data");
        labelElectricalTitle.setColorBordes(new java.awt.Color(117, 141, 163));
        labelElectricalTitle.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelElectricalTitle.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelVoltage.setText("Voltage");
        labelVoltage.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelElectricFrecuency.setText("Frequency");
        labelElectricFrecuency.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelElectricPower.setText("Power");
        labelElectricPower.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelElectricCapMax.setText("Maximum capacity");
        labelElectricCapMax.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelElectricCapMin.setText("Minimum capacity");
        labelElectricCapMin.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelElectricEnergyType.setText("Type of energy ");
        labelElectricEnergyType.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelPowerFactor.setText("Power factor ");
        labelPowerFactor.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelMinimumEfficiency.setText("Minimum efficiency ");
        labelMinimumEfficiency.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelNominalEfficiency.setText("Nominal efficiency");
        labelNominalEfficiency.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelElectricRatio.setText("Ratio ");
        labelElectricRatio.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelServiceFactor.setText("Service factor ");
        labelServiceFactor.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelPhases.setText("Phases");
        labelPhases.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelRiseTemp.setText("% Temperature rise");
        labelRiseTemp.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout electricLayout = new javax.swing.GroupLayout(electric);
        electric.setLayout(electricLayout);
        electricLayout.setHorizontalGroup(
            electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(electricLayout.createSequentialGroup()
                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelElectricalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(electricLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(electricLayout.createSequentialGroup()
                                .addComponent(labelNominalEfficiency, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(eficienciaNominTB, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                            .addGroup(electricLayout.createSequentialGroup()
                                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelElectricCapMin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelElectricCapMax, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelElectricEnergyType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(minCapElecTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(maxCapElecTB)
                                    .addComponent(tipoEnergiaElecTB)))
                            .addGroup(electricLayout.createSequentialGroup()
                                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelElectricFrecuency, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelVoltage, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelElectricPower, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(frecuenciaElecTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(voltajeTB, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(potenciaElecTB))))))
                .addGap(36, 36, 36)
                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMinimumEfficiency, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelElectricRatio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelServiceFactor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPowerFactor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPhases, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRiseTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(fasesElecTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(ratioElecTB, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(factorServicioTB, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(factorPotenciaTB, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(minEficienciaTB, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(elevTempTB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        electricLayout.setVerticalGroup(
            electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(electricLayout.createSequentialGroup()
                .addComponent(labelElectricalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelVoltage, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(voltajeTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMinimumEfficiency, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minEficienciaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelElectricFrecuency, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frecuenciaElecTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPowerFactor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(factorPotenciaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelElectricPower, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(potenciaElecTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelServiceFactor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(factorServicioTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelElectricCapMax, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxCapElecTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelElectricRatio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ratioElecTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelElectricCapMin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minCapElecTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPhases, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fasesElecTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelElectricEnergyType, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoEnergiaElecTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRiseTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elevTempTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(electricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelNominalEfficiency, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eficienciaNominTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout manufacturerLayout = new javax.swing.GroupLayout(manufacturer);
        manufacturer.setLayout(manufacturerLayout);
        manufacturerLayout.setHorizontalGroup(
            manufacturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manufacturerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(manufacturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(electric, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mechanic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        manufacturerLayout.setVerticalGroup(
            manufacturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manufacturerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(mechanic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(electric, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MULTITAB.addTab("Manufacturer's specifications", manufacturer);

        design.setBackground(new java.awt.Color(238, 244, 254));
        design.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        designdata.setBackground(new java.awt.Color(202, 219, 236));
        designdata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        labelDesignTitle.setText("Design data");
        labelDesignTitle.setColorBordes(new java.awt.Color(117, 141, 163));
        labelDesignTitle.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelDesignTitle.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelVolume.setText("Volume");
        labelVolume.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelFrame.setText("Frame");
        labelFrame.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelCatalogueNumber.setText("Catalogue number ");
        labelCatalogueNumber.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelSpecNumber.setText("Specification number ");
        labelSpecNumber.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelSerialNumber.setText("Serial number ");
        labelSerialNumber.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelWeight.setText("Weight KG");
        labelWeight.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelDesign.setText("Design");
        labelDesign.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelStyle.setText("Style ");
        labelStyle.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelClass.setText("Class ");
        labelClass.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelVibration.setText("Vibration");
        labelVibration.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout designdataLayout = new javax.swing.GroupLayout(designdata);
        designdata.setLayout(designdataLayout);
        designdataLayout.setHorizontalGroup(
            designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(designdataLayout.createSequentialGroup()
                .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDesignTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(designdataLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(designdataLayout.createSequentialGroup()
                                .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelSpecNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(numSerieTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(especNumTB)
                                    .addComponent(pesoTB)))
                            .addGroup(designdataLayout.createSequentialGroup()
                                .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelCatalogueNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(frameTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(volumenTB, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(numCatTB))))))
                .addGap(36, 36, 36)
                .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelStyle, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelClass, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelVibration, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDesign, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(estiloTB, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claseTB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vibracionTB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(disenoTB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        designdataLayout.setVerticalGroup(
            designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(designdataLayout.createSequentialGroup()
                .addComponent(labelDesignTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volumenTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelClass, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claseTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frameTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelStyle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estiloTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCatalogueNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numCatTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDesign, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(disenoTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelSpecNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(especNumTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelVibration, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vibracionTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelSerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numSerieTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(designdataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesoTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        drawing.setBackground(new java.awt.Color(202, 219, 236));
        drawing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        labelDibujos.setText("Drawings");
        labelDibujos.setColorBordes(new java.awt.Color(117, 141, 163));
        labelDibujos.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelDibujos.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelArchivo1.setText("File 1");
        labelArchivo1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        btnArchivo1.setBackground(new java.awt.Color(226, 210, 144));
        btnArchivo1.setText("...");

        labelArchivo2.setText("File 2");
        labelArchivo2.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        btnArchivo2.setBackground(new java.awt.Color(226, 210, 144));
        btnArchivo2.setText("...");

        labelArchivo3.setText("File 3");
        labelArchivo3.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        btnArchivo3.setBackground(new java.awt.Color(226, 210, 144));
        btnArchivo3.setText("...");

        javax.swing.GroupLayout drawingLayout = new javax.swing.GroupLayout(drawing);
        drawing.setLayout(drawingLayout);
        drawingLayout.setHorizontalGroup(
            drawingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(drawingLayout.createSequentialGroup()
                .addGroup(drawingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(drawingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(drawingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(drawingLayout.createSequentialGroup()
                                .addComponent(labelArchivo3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(fileTresTB, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(btnArchivo3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(drawingLayout.createSequentialGroup()
                                .addComponent(labelArchivo1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(fileUnoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(btnArchivo1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(drawingLayout.createSequentialGroup()
                                .addComponent(labelArchivo2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(fileDosTB, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(btnArchivo2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(labelDibujos, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        drawingLayout.setVerticalGroup(
            drawingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(drawingLayout.createSequentialGroup()
                .addComponent(labelDibujos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(drawingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnArchivo1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileUnoTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelArchivo1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(drawingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelArchivo2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileDosTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArchivo2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(drawingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelArchivo3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileTresTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArchivo3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout designLayout = new javax.swing.GroupLayout(design);
        design.setLayout(designLayout);
        designLayout.setHorizontalGroup(
            designLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, designLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(designLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(drawing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(designdata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        designLayout.setVerticalGroup(
            designLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(designLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(designdata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(drawing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        MULTITAB.addTab("Design", design);

        idEquipoTB.setEditable(false);

        idEstatusTB.setEditable(false);

        descripcionEstatusTB.setEditable(false);

        labelIdEquipment.setText("Equipt.ID");
        labelIdEquipment.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelEquipmentName.setText("Denomination");
        labelEquipmentName.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelStatus.setText("Status");
        labelStatus.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelIdEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEquipmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(bodyPanelLayout.createSequentialGroup()
                                    .addComponent(idEstatusTB)
                                    .addGap(1, 1, 1)
                                    .addComponent(descripcionEstatusTB, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(denominacionEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(idEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(287, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIdEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(denominacionEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEquipmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idEstatusTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionEstatusTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MULTITAB)
                .addContainerGap())
        );

        MULTITAB.getAccessibleContext().setAccessibleName("");

        panelScroll.setViewportView(bodyPanel);

        jPanel_Rounded_Corners_Degradado6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado6.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado6.setColor2(new java.awt.Color(202, 216, 237));

        btnSalir.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        btnCrear.setBackground(new java.awt.Color(226, 210, 144));
        btnCrear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/save.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado6Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado6);
        jPanel_Rounded_Corners_Degradado6.setLayout(jPanel_Rounded_Corners_Degradado6Layout);
        jPanel_Rounded_Corners_Degradado6Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado6Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
            .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTabbedPane MULTITAB;
    protected static javax.swing.JTextField activoFijoTB;
    private com.toedter.calendar.JYearChooser anoConstruccionTB;
    private javax.swing.JPanel bodyPanel;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnArchivo1;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnArchivo2;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnArchivo3;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnBuscarCriticidad;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnBuscarTE;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnBuscarUbicacion;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnCrear;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnEquipoSuperior;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnMoneda;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir;
    protected static javax.swing.JTextField centroCostosTB;
    private javax.swing.JTextField claseTB;
    private javax.swing.JTextField denominacionEquipoTB;
    private javax.swing.JTextField denominacionTipoTB;
    protected static javax.swing.JTextField descripcionCriticidadTB;
    private javax.swing.JTextField descripcionEstatusTB;
    public static javax.swing.JLabel descripcionGrupoPlanif;
    protected static javax.swing.JTextField descripcionTipoEquipoTB;
    private javax.swing.JPanel design;
    private javax.swing.JPanel designdata;
    private javax.swing.JTextField dimensionTB;
    private javax.swing.JTextField disenoTB;
    private javax.swing.JPanel drawing;
    private javax.swing.JTextField eficienciaNominTB;
    private javax.swing.JPanel electric;
    private javax.swing.JTextField elevTempTB;
    private javax.swing.JPanel emplazament;
    protected static javax.swing.JTextField equipoSuperiorTB;
    private javax.swing.JTextField especNumTB;
    private javax.swing.JTextField estiloTB;
    private javax.swing.JTextField fabricacionNumSerieTB;
    private javax.swing.JTextField fabricanteTB;
    private javax.swing.JTextField factorPotenciaTB;
    private javax.swing.JTextField factorServicioTB;
    private javax.swing.JTextField fasesElecTB;
    private com.toedter.calendar.JDateChooser fechaAdquisicion;
    private com.toedter.calendar.JDateChooser fechaManufactura;
    private com.toedter.calendar.JDateChooser fechaPuestaEnServicio;
    private javax.swing.JTextField fileDosTB;
    private javax.swing.JTextField fileTresTB;
    private javax.swing.JTextField fileUnoTB;
    private javax.swing.JTextField flujoTB;
    private javax.swing.JTextField frameTB;
    private javax.swing.JTextField frecuenciaElecTB;
    private javax.swing.JTextField frecuenciaMecTB;
    private javax.swing.JPanel general;
    private javax.swing.JPanel generalData;
    protected static javax.swing.JTextField grupoPlanificacion;
    protected static javax.swing.JTextField idAreaTB;
    protected static javax.swing.JTextField idCriticidadTB;
    protected static javax.swing.JTextField idEmplazamiento;
    private javax.swing.JTextField idEquipoTB;
    private javax.swing.JTextField idEstatusTB;
    protected static javax.swing.JTextField idTipoEquipoTB;
    private javax.swing.JPanel imputation;
    private javax.swing.JPanel installation;
    private javax.swing.JPanel installation1;
    private javax.swing.JLabel jLabel1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelActivoFijo;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelAdquisitionDate;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelAdquisitionValue;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelArchivo1;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelArchivo2;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelArchivo3;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelArea;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelBearing;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelBrand;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCatalogueNumber;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCentroEmplazamiento;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelClass;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCostCenter;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCountryManufacturer;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCriticality;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelDatosPlanificacion;
    public static javax.swing.JLabel labelDenomArea;
    public static javax.swing.JLabel labelDenomCeEmplaz;
    public static javax.swing.JLabel labelDenomCentroCostos;
    public static javax.swing.JLabel labelDenomSociedad;
    public static javax.swing.JLabel labelDenominacionActivoFijo;
    public static javax.swing.JLabel labelDenominacionUbicacion;
    public static javax.swing.JLabel labelDescripcionEquipoSuperior;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelDesign;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelDesignTitle;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelDibujos;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelDimension;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelElectricCapMax;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelElectricCapMin;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelElectricEnergyType;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelElectricFrecuency;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelElectricPower;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelElectricRatio;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelElectricalTitle;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelEquipmentName;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelEquipmentType;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelFlow;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelFrame;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelGeneralDataTitle;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelGrupoPlan;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelIdEquipment;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelImputacionTitle;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelInstallationDataTitle;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelLocation;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelLocationTitle;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelLubricant;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelManufactureDataTitle;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelManufactureDate;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelManufacturer;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelMaxCapMec;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelMecanicalFrecuency;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelMechanicalEnergyType;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelMechanicalRatio;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelMechanicalTitle;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelMinCapMec;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelMinimumEfficiency;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelModel;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelNominalEfficiency;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelNumPart;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelNumSeries;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelPhases;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelPower;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelPowerFactor;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelProvisioningDataTitle;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelRPM;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelRiseTemp;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSerialNumber;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSeries;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelServiceFactor;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelServiceStartDate;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSociedad;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSpecNumber;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelStatus;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelStyle;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelTopEquipment;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelTorque;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelTypeName;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelVibration;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelVoltage;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelVolume;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelWeight;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelYearMonthManufactured;
    private javax.swing.JTextField lubricanteTB;
    private javax.swing.JPanel manufacturer;
    private javax.swing.JPanel manufacturingData;
    private javax.swing.JTextField marcaTB;
    private javax.swing.JTextField maxCapElecTB;
    private javax.swing.JTextField maxCapMecTB;
    private javax.swing.JPanel mechanic;
    public static javax.swing.JMenu menuActions;
    public static javax.swing.JMenuBar menuBar_E01;
    public static javax.swing.JMenuItem menuCreate;
    public static javax.swing.JMenuItem menuCreateNewEquipment;
    public static javax.swing.JMenu menuCreation;
    public static javax.swing.JMenuItem menuExit;
    public static javax.swing.JMenu menuHelp;
    public static javax.swing.JMenuItem menuInstruction;
    public static javax.swing.JMenuItem menuModifyEquipment;
    public static javax.swing.JMenuItem menuStatus;
    public static javax.swing.JMenuItem menuVisualizeEquipment;
    private com.toedter.calendar.JMonthChooser mesConstruccionTB;
    private javax.swing.JTextField minCapElecTB;
    private javax.swing.JTextField minCapMecTB;
    private javax.swing.JTextField minEficienciaTB;
    private javax.swing.JTextField modelTB;
    private javax.swing.JTextField monedaTB;
    private javax.swing.JTextField numCatTB;
    private javax.swing.JTextField numSerieTB;
    private javax.swing.JTextField numeroPiezaTB;
    private javax.swing.JPanel organization;
    private javax.swing.JTextField paisProductorTB;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JTextField pesoTB;
    private javax.swing.JTextField potenciaElecTB;
    private javax.swing.JTextField potenciaTB;
    private javax.swing.JPanel provisioning;
    private javax.swing.JTextField ratioElecTB;
    private javax.swing.JTextField ratioMecTB;
    private javax.swing.JTextField rodamientosTB;
    private javax.swing.JTextField rpmTB;
    private javax.swing.JPopupMenu.Separator separador;
    private javax.swing.JTextField serieTB;
    protected static javax.swing.JTextField sociedadTB;
    private javax.swing.JTextField tipoEnergiaElecTB;
    private javax.swing.JTextField tipoEnergiaTB;
    private javax.swing.JTextField torqueTB;
    protected static javax.swing.JTextField ubicacionTB;
    private javax.swing.JTextField valorAdquisicionTB;
    private javax.swing.JTextField vibracionTB;
    private javax.swing.JTextField voltajeTB;
    private javax.swing.JTextField volumenTB;
    // End of variables declaration//GEN-END:variables
}
