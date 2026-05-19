package com.simplecore.erp.gui.workspace.legacy;

import com.simplecore.erp.gui.workspace.LyraWorkspace;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.gui.CharacteristicsForm;
import com.simplecore.erp.modules.system.users.AU1_Administracion_Usuarios;
import com.simplecore.erp.modules.system.access.legacy.AccessManagementSystem;
import com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.E01_Crear_Equipo_Panel;
import com.simplecore.erp.modules.controlling.areas.legacy.A01_Crear_Areas;
import com.simplecore.erp.modules.controlling.areas.legacy.A02_Create_Emplazement;
import com.simplecore.erp.modules.controlling.society.legacy.C01_Crear_Nueva_Compania;
import com.simplecore.erp.modules.controlling.costmanagement.costcenters.legacy.F02_Crear_Centro_Costos;
import com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy.U01_Crear_Ubicaciones;
import com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy.U02_Modificacion_Ubicaciones;
import com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy.U03_Estructura_Ubicaciones;
import com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy.PM1_Crear_Punto_Medidas;
import com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy.PM2_Modificar_Punto_Medida;
import com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy.PM3_Visualizar_Punto_Medida;
import com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy.PM4_Ingreso_Datos_Medicion;
import com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy.PM5_Modificacion_Documentos_Medicions;
import com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceprogramming.maintenance_scheduling.s03_schedule_visualization.S03_Visualizar_Programacion_Plan;
import com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.creation.M01_Creacion_Materiales;
import com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.modification.M02_Modificacion_Materiales;
import com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.visual.M03_Visualizacion_Materiales;
import com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.create.M04_Creacion_Almacen;
import com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.modification.M05_Modificacion_Almacen;
import com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.registration_of_materials_on_warehouse.M06_Alta_Materiales_en_Almacen;
import com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.lists.M07_Lista_Materiales;
import com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.lists.M08_Lista_Materiales;
import com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.lists.M09_Movimiento_de_Materiales;
import com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.lists.M10_Lista_Materiales_Inventario;
import com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.lists.M11_Lista_Materiales_Movimientos;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders.O01_Crear_Orden;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders.O02_Modificar_Orden;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o03_display_of_orders.O03_Visualizar_Orden;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o04_approval_of_orders.O04_Aprobar_Ordenes;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.tratamiento_de_listas.aprobacion_ordenes.O05_Ambiente_Filtrado_Datos;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.header.MaintenanceRoutineSheetForm;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.procedures.MaintenanceProcedures;
import com.simplecore.erp.modules.logistics.plantmaintenance.strategies.legacy.news.MaintenanceStrategies;

public class InvokeTransactions {

    public static JPanel cargar(String transaccion) {
        return switch (transaccion) {
            case "E01" ->
                new E01_Crear_Equipo_Panel();
            case "A01" ->
                new A01_Crear_Areas();
            case "A02" ->
                new A02_Create_Emplazement();
            case "C01" ->
                new C01_Crear_Nueva_Compania();
            case "F02" ->
                new F02_Crear_Centro_Costos();
            case "H01" ->
                new MaintenanceRoutineSheetForm();
//            case "Z01" ->
//                new MaintenanceStrategies();
            case "M01" ->
                new M01_Creacion_Materiales();
            case "M02" ->
                new M02_Modificacion_Materiales();
            case "M03" ->
                new M03_Visualizacion_Materiales();
            case "M04" ->
                new M04_Creacion_Almacen();
            case "M05" ->
                new M05_Modificacion_Almacen();
            case "M06" ->
                new M06_Alta_Materiales_en_Almacen();
            case "M07" ->
                new M07_Lista_Materiales();
            case "M08" ->
                new M08_Lista_Materiales();
            case "M09" ->
                new M09_Movimiento_de_Materiales();
            case "M10" ->
                new M10_Lista_Materiales_Inventario();
            case "M11" ->
                new M11_Lista_Materiales_Movimientos();
            case "O01" ->
//      //          new O01_Crear_Orden();
//            case "O02" ->
//       ///         new O02_Modificar_Orden();
//            case "O03" ->
//       //         new O03_Visualizar_Orden();
//            case "O04" ->
//                new O04_Aprobar_Ordenes();
//            case "O05" ->
//                new O05_Ambiente_Filtrado_Datos();
  //          case "P02" ->
                 null;
            case "P03" ->
                 null;
            case "PM1" ->
                new PM1_Crear_Punto_Medidas();
            case "PM2" ->
                new PM2_Modificar_Punto_Medida();
            case "PM3" ->
                new PM3_Visualizar_Punto_Medida();
            case "PM4" ->
                new PM4_Ingreso_Datos_Medicion();
            case "PM5" ->
                new PM5_Modificacion_Documentos_Medicions();
            case "AU1" ->
                new AU1_Administracion_Usuarios();
            case "AU2" ->
                new AccessManagementSystem();
            case "S03" ->
                new S03_Visualizar_Programacion_Plan();
            case "U01" ->
                new U01_Crear_Ubicaciones();
            case "U02" ->
                new U02_Modificacion_Ubicaciones();
            case "U03" ->
                new U03_Estructura_Ubicaciones();
            case "CT01" ->
                new CharacteristicsForm();
//            case "PMC" ->
//                new MaintenanceProcedures();
            default ->
                null;
        }; // If the transaction code does not match any case, return null
    }

    public static JMenuBar cambiarMenuBar(String transaccion) {
        return switch (transaccion) {
            case "E01" ->
                E01_Crear_Equipo_Panel.menuBar_E01;
            case "A01" ->
                A01_Crear_Areas.menuBar_A01;
            case "A02" ->
                A02_Create_Emplazement.menuBar_A02;
            case "C01" ->
                C01_Crear_Nueva_Compania.menuBar_C01;
            case "F02" ->
                F02_Crear_Centro_Costos.menuBar_F02;
            case "M01" ->
                M01_Creacion_Materiales.menuBar_M01;
            case "M02" ->
                M02_Modificacion_Materiales.menuBar_M02;
            case "M03" ->
                M03_Visualizacion_Materiales.menuBar_M03;
            case "M04" ->
                M04_Creacion_Almacen.menuBar_M04;
            case "M05" ->
                M05_Modificacion_Almacen.menuBar_M05;
            case "M06" ->
                M06_Alta_Materiales_en_Almacen.menuBar_M06;
            case "M07" ->
                M07_Lista_Materiales.menuBar_M07;
            case "M08" ->
                M08_Lista_Materiales.menuBar_M08;
            case "M09" ->
                M09_Movimiento_de_Materiales.menuBar_M09;
            case "M10" ->
                M10_Lista_Materiales_Inventario.menuBar_M10;
            case "M11" ->
                M11_Lista_Materiales_Movimientos.menuBar_M11;
            case "P02" ->
                 null;
            case "P03" ->
                 null;
            case "PM1" ->
                PM1_Crear_Punto_Medidas.menuBar_PM1;
            case "AU1" ->
                AU1_Administracion_Usuarios.menuBar_AU1;
            case "AU2" ->
                AccessManagementSystem.menuBar_AU2;
            case "S03" ->
                S03_Visualizar_Programacion_Plan.menuBar_S03;
            case "U01" ->
                U01_Crear_Ubicaciones.menuBar_U01;
            case "U02" ->
                U02_Modificacion_Ubicaciones.menuBar_U02;
            case "U03" ->
                U03_Estructura_Ubicaciones.menuBar_U03;
            default ->
                LyraWorkspace.barMenu; // Menú por defecto si la transacción no coincide
        };
    }

}
