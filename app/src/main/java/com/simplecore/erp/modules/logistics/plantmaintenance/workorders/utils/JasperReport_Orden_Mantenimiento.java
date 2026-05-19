package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReport_Orden_Mantenimiento {
//
//    final String dir = "src" + File.separator + "lyra" + File.separator + "access" + File.separator + "modules" + File.separator
//                     + "transactions" + File.separator + "maintenance" + File.separator + "formats" + File.separator + "maintenance_work_orders"
//                     + File.separator + "Orden_de_Trabajo_Mtto_A4_RU.jasper";

    OrdenTrabajoJRParameter parametros;
    
    URL url = getClass().getResource("/lyra/access/modules/iconography/main/logo_principal.png");
    URL url1 = getClass().getResource("/lyra/access/modules/iconography/main/stardust.png");
    
    InputStream logoSuperiorIzquierdo = null;
    InputStream logoSuperiorDerecho = null;
    JRBeanCollectionDataSource tablaOperaciones;
    JRBeanCollectionDataSource tablaMateriales;
    
    private void seImages(){
        try {
            
            logoSuperiorIzquierdo = url.openStream();
            logoSuperiorDerecho = url1.openStream();
            
        } catch (IOException ex) {
            Logger.getLogger(JasperReport_Orden_Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void procesarTablas() {
        
        List<TablaOperacionesOrden> operaciones = new ArrayList<>();
        List<TablaMaterialesOrden> materiales = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            TablaOperacionesOrden operaciones1 = new TablaOperacionesOrden();
            operaciones1.setNUMERO_OPERACION("1");
            operaciones1.setDESCRIPCION_OPERACION("MANTENIMIENTO 250 HRS AUTOMOTRIZ");
            operaciones1.setCANTIDAD_OPERACION("1");
            operaciones1.setPRECIO_UNITARIO_OPERACION("35.00");
            operaciones1.setUNIDAD_MEDIDA("EA");
            operaciones1.setID_TIPO_OPERACION("MEC_AU");
            operaciones1.setTIPO_OPERACION("MECANICO AUTOMOTRIZ");
            operaciones1.setDURACION_OPERACION("5.0");
            operaciones1.setMONTO_TOTAL_OPERACION("1,200.00");
            operaciones1.setPAQUETE("1H");

            operaciones.add(operaciones1);
        }

        for (int i = 0; i < 6; i++) {
            TablaMaterialesOrden materiales1 = new TablaMaterialesOrden();
            materiales1.setCODIGO_MATERIAL("10001");
            materiales1.setDESCRIPCION_MATERIAL("TUERCA HEXAGONAL DE 3/8 ACERO INOX.");
            materiales1.setNUMERO_OPERACION("1");
            materiales1.setCANTIDAD_MATERIAL("5");
            materiales1.setPRECIO_UNITARIO("35.00");
            materiales1.setMONTO_TOTAL("1,245.00");
            materiales1.setALMACEN("48001");

            materiales.add(materiales1);
        }

        tablaOperaciones = new JRBeanCollectionDataSource(operaciones);
        tablaMateriales = new JRBeanCollectionDataSource(materiales);
    }

    
    private void procesarParametros(){
            
        procesarTablas();
        

            seImages();
            
            parametros = new OrdenTrabajoJRParameter();
            parametros.agregarParametro("logoSuperiorIzquierdo", logoSuperiorIzquierdo);
            parametros.agregarParametro("logoSuperiorDerecho", logoSuperiorDerecho);
            parametros.agregarParametro("tablaOperaciones", tablaOperaciones);
            parametros.agregarParametro("tablaMateriales", tablaMateriales);
            

    }
    
    
    public void cargarJasperReport_Orden_Mtto(JRDataSource fuenteDatos) {
        
        try {
            procesarParametros();

//
//            File archivoJasper = new File(dir);
//            archivoFormularioJasper = new BufferedInputStream(new FileInputStream(archivoJasper.getAbsoluteFile()));
//
            JasperReport reporteJasper = (JasperReport) JRLoader.loadObject(getClass().getResource("/lyra/access/modules/transactions/maintenance/formats/maintenance_work_orders/Orden_de_Trabajo_Mtto_A4_ENG.jasper"));
            JasperPrint imprimirReporteJasper = JasperFillManager.fillReport(reporteJasper, parametros.getMatrizParametros(), fuenteDatos);
            JasperViewer visualizarReporte = new JasperViewer(imprimirReporteJasper);
            
            visualizarReporte.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            visualizarReporte.setVisible(true);
        
        } catch (JRException ex) {
            Logger.getLogger(JasperReport_Orden_Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
