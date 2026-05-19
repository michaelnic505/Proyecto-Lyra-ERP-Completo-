
package lyra.access.modules.iconography.orders_icons;


import javax.swing.ImageIcon;

//Para que esta clase del tipo Enum pueda presentarse sin errores en las clases donde se llame, debe implementarse la clase Icon
public enum Iconos {
    
//        //Principal
//    /* src/lyra/access/modules/iconography/main/   */     
//        //DIR
//    /* src/lyra/access/modules/iconography/order_icons/ */    
//        //ARBOL
//    /* src/lyra/access/modules/iconography/tree_location/ */    
//        //TOOLBAR
//    /* src/lyra/access/modules/iconography/toolbar/ */    
//        //MAINTREE
//    /* src/lyra/access/modules/iconography/main_tree/ */    
//        //DIR VENTANA PRINCIPAL
//    /* src/lyra/access/modules/iconography/general_windows/ */   
//    
//    
//    
//    LOGO_PRINCIPAL_LOGIN("src/lyra/access/modules/iconography/main/logoPrincipal.png"),
//    LOGO_PRINCIPAL_LOGIN2("src/lyra/access/modules/iconography/main/logo_principal.png"),
//    
//    ImageLogo("src/lyra/access/modules/iconography/order_icons/image.png"),
//    //Iconos para ventanas
//    TRIANG_GRANDE_PRI("src/lyra/access/modules/iconography/general_windows/LogoP2.png"),
//    TRIANG_MEDIANO_PRI("src/lyra/access/modules/iconography/general_windows/LogoP.png"),
//    TRIANG_PEQUEÑO_PRI("src/lyra/access/modules/iconography/general_windows/pir30.png"),
//    TRIANG_PEQUEÑO_PRI20("src/lyra/access/modules/iconography/general_windows/pir20.png"),
//    TRIANG_VENTANA("src/lyra/access/modules/iconography/general_windows/trian1.png"),
//    
//    NODO_ROOT("src/lyra/access/modules/iconography/tree_location/empresa_root.png"),
//    NODO_PADRE("src/lyra/access/modules/iconography/tree_location/administracion.png"),
//    NODO_HIJO("src/lyra/access/modules/iconography/tree_location/nodo.png"),
//    NODO_UBICACION("src/lyra/access/modules/iconography/tree_location/ubicacion.png"),
//    NODO_DESPLEGADO("src/lyra/access/modules/iconography/tree_location/desplegable.png"),
//    NODO_DESPLEGAR("src/lyra/access/modules/iconography/tree_location/desplegar.png"),
//    
//    TOOLBAR_SEARCH("src/lyra/access/modules/iconography/toolbar/search.png"),
//    TOOLBAR_BOTON_VERDE("src/lyra/access/modules/iconography/toolbar/boton_verde.png"),
//    TOOLBAR_BOTON_NARANJA("src/lyra/access/modules/iconography/toolbar/boton_naranja.png"),
//    TOOLBAR_BOTON_ROJO("src/lyra/access/modules/iconography/toolbar/boton_salir.png"),
//    TOOLBAR_ERROR("src/lyra/access/modules/iconography/toolbar/error50.png"),
//    
//    TOOLBAR_ABRIR_CARPETA("src/lyra/access/modules/iconography/toolbar/abrir_carpeta.png"),
//    TOOLBAR_CERRAR_CARPETA("src/lyra/access/modules/iconography/toolbar/cerrar_carpeta.png"),
//    TOOLBAR_HOME("src/lyra/access/modules/iconography/toolbar/hogar.png"),
//    TOOLBAR_CONTINUAR("src/lyra/access/modules/iconography/toolbar/adelante.png"),
//    TOOLBAR_CARGAR("src/lyra/access/modules/iconography/toolbar/reloj.png"),
//    TOOLBAR_NUEVO("src/lyra/access/modules/iconography/toolbar/nuevo.png"),
//    TOOLBAR_ACTUALIZAR("src/lyra/access/modules/iconography/toolbar/actualizar.png"),
//    TOOLBAR_EDITAR("src/lyra/access/modules/iconography/toolbar/editar.png"),
//    TOOLBAR_ELIMINAR("src/lyra/access/modules/iconography/toolbar/eliminar.png"),
//    TOOLBAR_PAGINA_ABAJO("src/lyra/access/modules/iconography/toolbar/pagina-abajo.png"),
//    TOOLBAR_PAGINA_ARRIBA("src/lyra/access/modules/iconography/toolbar/pagina-arriba.png"),
//    TOOLBAR_IMPRIMIR("src/lyra/access/modules/iconography/toolbar/impresora.png"),
//    TOOLBAR_NUEVA_VENTANA("src/lyra/access/modules/iconography/toolbar/nueva-ventana.png"),
//    TOOLBAR_AYUDA("src/lyra/access/modules/iconography/toolbar/pregunta.png"),
//    TOOLBAR_BUSCAR("src/lyra/access/modules/iconography/toolbar/busqueda.png"),
//    TOOLBAR_LIMPIAR("src/lyra/access/modules/iconography/toolbar/cubeta.png"),
//    TOOLBAR_LANZAR("src/lyra/access/modules/iconography/toolbar/cohete.png"),
//    TOOLBAR_DETENER("src/lyra/access/modules/iconography/toolbar/detener.png"),
//    
//    NUEVO("src/lyra/access/modules/iconography/general_windows/archivo-nuevo20.png"),
//    GUARDAR("src/lyra/access/modules/iconography/general_windows/guardar15.png"),
//    PAGINA_ABAJO("src/lyra/access/modules/iconography/order_icons/pagina-abajo20.png"),
//    PAGINA_ARRIBA("src/lyra/access/modules/iconography/order_icons/pagina-arriba20.png"),
//    
//    ACTIVIDAD_HOJA_RUTA("src/lyra/access/modules/iconography/order_icons/actividad.png"),
//    BORRAR_ACTIVIDAD_HOJA_RUTA("src/lyra/access/modules/iconography/order_icons/borrar.png"),
//    AGREGAR_HOJA_RUTA("src/lyra/access/modules/iconography/order_icons/agregar-archivo.png"),
//    CORREGIR_HOJA_RUTA("src/lyra/access/modules/iconography/order_icons/corregido.png"),
//    MATERIALES("src/lyra/access/modules/iconography/order_icons/piezas-de-repuesto.png"),
//    
//    ELIMINAR_LINEA("src/lyra/access/modules/iconography/order_icons/eliminarlinea.png"),
//    AGREGAR_LINEA("src/lyra/access/modules/iconography/order_icons/agregarlinea.png"),
//    
//    
//    CERRAR("src/lyra/access/modules/iconography/general_windows/cerrar.png"),
//    CHECK_VERDE("src/lyra/access/modules/iconography/general_windows/cheque20.png"),
//    REGRESAR("src/lyra/access/modules/iconography/general_windows/regresar15.png"),
//    WARNING("src/lyra/access/modules/iconography/general_windows/advertencia20.png"),
//    LUPA("src/lyra/access/modules/iconography/general_windows/lupa15.png"),
//    LLAVES1("src/lyra/access/modules/iconography/general_windows/llave1.png"),
//    LLAVES2("src/lyra/access/modules/iconography/general_windows/llave2.png"),
//    LOGO_STARDUST("src/lyra/access/modules/iconography/general_windows/star4.png"),
//    
//    FONDO_LYRA("src/lyra/access/modules/iconography/general_windows/lyra.jpg"),
//   //Iconos para carpetas
//    CARPETA_CERRADA("src/lyra/access/modules/iconography/main_tree/carpeta_cerrada.png"),    
//    CARPETA_ABIERTA("src/lyra/access/modules/iconography/main_tree/file-and-folder.png"),   
//    ICONO_TRANSACCIONES("src/lyra/access/modules/iconography/main_tree/pir.png"),
//    ICONO_TRANSACCIONES_CUBO1("src/lyra/access/modules/iconography/main_tree/cubo_1.png"),
//    ICONO_TRANSACCIONES_CUBO2("src/lyra/access/modules/iconography/main_tree/cubo_2.png"),
//    ICONO_TRANSACCIONES_CUBO3("src/lyra/access/modules/iconography/main_tree/cubo_3.png"),
//    ICONO_TRANSACCIONES_CUBO4("src/lyra/access/modules/iconography/main_tree/cubo_4.png"),
//    ICONO_TRANSACCIONES_CUBO5("src/lyra/access/modules/iconography/main_tree/cubo_5.png"),
//    
//    NUMERO_UNO_NEGRO("src/lyra/access/modules/iconography/order_icons/uno negro.png"),    
//    NUMERO_DOS_NEGRO("src/lyra/access/modules/iconography/order_icons/dos negro.png"),    
//    NUMERO_TRES_NEGRO("src/lyra/access/modules/iconography/order_icons/tres negro.png"),    
//    NUMERO_CUATRO_NEGRO("src/lyra/access/modules/iconography/order_icons/cuatro negro.png"),   
//    NUMERO_UNO_VERDE("src/lyra/access/modules/iconography/order_icons/uno verde.png"),
//    NUMERO_DOS_VERDE("src/lyra/access/modules/iconography/order_icons/dos verde.png"),    
//    NUMERO_TRES_VERDE("src/lyra/access/modules/iconography/order_icons/tres verde.png"),    
//    NUMERO_CUATRO_VERDE("src/lyra/access/modules/iconography/order_icons/cuatro verde.png"),    
//    
//    
//    
//    ICONO_GUARDADO("src/lyra/access/modules/iconography/general_windows/guardado30.png"),    
//    ICONO_FILTRO("src/lyra/access/modules/iconography/order_icons/filtro15.png"),    
//    GPLOT("src/lyra/access/modules/iconography/general_windows/estadisticas.png"),    
//    USUARIO_CREADO("src/lyra/access/modules/iconography/general_windows/usuario_creado.png"),
//    USUARIO_ELIMINADO("src/lyra/access/modules/iconography/general_windows/usuario_eliminado.png"),
//    FICHA_ROMPE_CABEZA("src/lyra/access/modules/iconography/general_windows/ficha20.png"),
//     
//    
//    BOTON_REG_ROJO_PRESS("src/lyra/access/modules/iconography/order_icons/cerrar20.png"),    
//    BOTON_OK_VERDE("src/lyra/access/modules/iconography/order_icons/check_ok.png"),  
//    BOTON_CERRAR_ROJO("src/lyra/access/modules/iconography/order_icons/cerrar20.png"), 
//    BOTON_ROJO_CERRAR("src/lyra/access/modules/iconography/order_icons/OKverde20.png"),  
//    
//    
//    BOTON_TODO("src/lyra/access/modules/iconography/order_icons/all30.png"),        
//    BOTON_OK_AZUL("src/lyra/access/modules/iconography/order_icons/OKazul20.png"),
//    HOJA_CALCULO("src/lyra/access/modules/iconography/order_icons/hoja_calculo.png"),
//    EJECUTAR("src/lyra/access/modules/iconography/order_icons/ejecutar20.png"),
//    NUEVO_DOCUMENTO("src/lyra/access/modules/iconography/order_icons/nuevo-documento25.png"),
//    BINOCULARES("src/lyra/access/modules/iconography/order_icons/prismaticos20.png"),
//    SALIR_ROJO("src/lyra/access/modules/iconography/order_icons/salir20.png"),
//    MENU_SALIR_ROJO("src/lyra/access/modules/iconography/order_icons/cerrar20.png"),
//    MENU_CONFIGURACIONES("src/lyra/access/modules/iconography/order_icons/engranaje20.png"),
//    MENU_RED("src/lyra/access/modules/iconography/order_icons/ajuste20.png"),
//    MENU_PREFERENCIAS("src/lyra/access/modules/iconography/order_icons/preferencias20.png"),
//    MENU_CAMBIAR_CONTRASENA("src/lyra/access/modules/iconography/order_icons/llave20.png"),
//    CUBO4("src/lyra/access/modules/iconography/order_icons/cubo_4.png"),
//    
//    
//    BOTON_SALIR("src/lyra/access/modules/iconography/general_windows/salida25.png"),
//    BOTON_ATRAS("src/lyra/access/modules/iconography/general_windows/deshacer25.png"),
//    BOTON_EXCEL("src/lyra/access/modules/iconography/general_windows/archivo-excel25.png"),
//    
//    
//    CANDADO_DESBLOQUEADO("src/lyra/access/modules/iconography/general_windows/desbloqueado.png"),
//    CANDADO_INICIO_SESION("src/lyra/access/modules/iconography/general_windows/candado_login.png"),
//    CANDADO_RESTRICCION("src/lyra/access/modules/iconography/general_windows/restriccion.png"),
//    FONDO_STARDUST("src/lyra/access/modules/iconography/general_windows/starMar.png"),
//    FONDO_STARDUST_ROJO("src/lyra/access/modules/iconography/general_windows/stardust.png"),        
//    FONDO_DESKTOP("src/lyra/access/modules/iconography/general_windows/s22.png"),   
//    FONDO_DESKTOP_PAPER("src/lyra/access/modules/iconography/general_windows/starPaper.png"),   
//    
//    BOTON_REG_ROJO("src/lyra/access/modules/iconography/general_windows/RegresarRojo25.png"),   
//    CAMPOS_VACIOS("src/lyra/access/modules/iconography/general_windows/caja.png"),
//    OPERACION_REALIZADA("src/lyra/access/modules/iconography/general_windows/realizado.png"),
//    NO_DISPONIBLE("src/lyra/access/modules/iconography/general_windows/no_disponible.png"),
//    INSTANCIA_ACTIVA("src/lyra/access/modules/iconography/general_windows/instancia.png"),
//    SELECCION_PRIMERO("src/lyra/access/modules/iconography/general_windows/seleccionar.png"),
//    ORDEN_NO_EXISTE("src/lyra/access/modules/iconography/general_windows/sin_resultado.png"),
//    REGISTRO_DUPLICADO("src/lyra/access/modules/iconography/general_windows/duplicar.png"),
//    SIN_RED("src/lyra/access/modules/iconography/general_windows/computadora.png"),
//    
//    GUARDAR_DATO("src/lyra/access/modules/iconography/general_windows/icono_guardar.png"),
//    GUARDADO_DATO("src/lyra/access/modules/iconography/general_windows/icono_guardado.png"),
//    
//    PRIVILEGIO_OTORGADO("src/lyra/access/modules/iconography/general_windows/privilegio_otorgado.png"),
//    PRIVILEGIO_RETIRADO("src/lyra/access/modules/iconography/general_windows/privilegio_retirado.png"),
//    
//    
//    LIQUIDACION_PARCIAL("src/lyra/access/modules/iconography/order_icons/liquidacion_parcial.png"),
//    LIQUIDACION_TOTAL("src/lyra/access/modules/iconography/order_icons/liquidacion_total.png"),
//    APLICAR_LIQUIDACION("src/lyra/access/modules/iconography/order_icons/aplicado.png");
//    
//    
//    String icono;
//
//    private Iconos(String icono) {
//        this.icono = icono;
//    }
//    
//       
//
//    public ImageIcon set() {
//
//        return new ImageIcon(getClass().getResource(icono));
//    }

}
