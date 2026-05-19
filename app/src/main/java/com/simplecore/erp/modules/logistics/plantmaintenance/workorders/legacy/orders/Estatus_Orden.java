    
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders;

import javax.swing.ImageIcon;
import lyra.access.modules.iconography.orders_icons.Directorios;


public enum Estatus_Orden {

    //Se asigna un texto de Letra de estatus y icono para representar graficamente 
    GENERADA("G","G-GENERADA", new ImageIcon(Directorios.DIR_OT.text()+"1G AZUL30.png"),
            new ImageIcon(Directorios.DIR_OT.text()+"1G NEGRA30.png")),
    
    EN_PLANEACION("P","P-EN PLANEACION", new ImageIcon(Directorios.DIR_OT.text()+"2P AZUL30.png"),
            new ImageIcon(Directorios.DIR_OT.text()+"2P NEGRA30.png")),
    
    PENDIENTE_APROBACION("D","D-PENDIENTE APROBACION", new ImageIcon(Directorios.DIR_OT.text()+"3D AZUL30.png"),
            new ImageIcon(Directorios.DIR_OT.text()+"3D NEGRA30.png")),
    
    APROBADA("A","A-APROBADA", new ImageIcon(Directorios.DIR_OT.text()+"4A AZUL30.png"),
            new ImageIcon(Directorios.DIR_OT.text()+"4A NEGRA30.png")),
    
    PROGRAMADA("R","R-PROGRAMADA", new ImageIcon(Directorios.DIR_OT.text()+"5R AZUL30.png"),
            new ImageIcon(Directorios.DIR_OT.text()+"5R NEGRA30.png ")),
    
    EN_EJECUCION("E","E-EN EJECUCION", new ImageIcon(Directorios.DIR_OT.text()+"6E AZUL30.png"),
            new ImageIcon(Directorios.DIR_OT.text()+"6E NEGRA30.png")),
    
    EJECUTADA("T","T-EJECUTADA", new ImageIcon(Directorios.DIR_OT.text()+"7T AZUL30.png"),
            new ImageIcon(Directorios.DIR_OT.text()+"7T NEGRA30.png")),
    
    CERRADA("C","C-CERRADA", new ImageIcon(Directorios.DIR_OT.text()+"8C AZUL30.png"),
            new ImageIcon(Directorios.DIR_OT.text()+"8C NEGRA30.png")),
    
    RECHAZADA("Z","Z-RECHAZADA", new ImageIcon(Directorios.DIR_OT.text()+"8C AZUL30.png"),
            new ImageIcon(Directorios.DIR_OT.text()+"8C NEGRA30.png"));

    
    private String ID;
    private String estatus;
    private ImageIcon iconoAzul;
    private ImageIcon iconoNegro;

    private Estatus_Orden(String ID, String estatus, ImageIcon iconoAzul, ImageIcon iconoNegro) {

        this.estatus = estatus;
        this.iconoAzul = iconoAzul;
        this.iconoNegro = iconoNegro;
        this.ID = ID;
    }

    public String establecerEstatus() {

        return estatus;

    }

    public ImageIcon estatusAzulIcono() {
        
        return iconoAzul;
    }

    public ImageIcon estatusNegroIcono() {
        return iconoNegro;
    }
    
    public String establecerID(){
        return ID;
    }

}
