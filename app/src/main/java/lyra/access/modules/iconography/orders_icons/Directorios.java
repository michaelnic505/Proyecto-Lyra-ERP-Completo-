
package lyra.access.modules.iconography.orders_icons;

import java.io.File;

/*C:\Users\ user\Documents\NetBeansProjects\Lyra Access\src\lyra\access\modules\iconography*/

public enum Directorios {
    
    DIR(File.separator + "lyra" + File.separator + "access" + File.separator + "modules" + File.separator
            + "iconography" + File.separator +"orders_icons"+File.separator),
    
    ARBOL_UBICACIONES(File.separator + "lyra" + File.separator + "access" + File.separator + "modules" + File.separator
            + "iconography" + File.separator+ "tree_location" + File.separator),
    
    TOOLBAR(File.separator + "lyra" + File.separator + "access" + File.separator + "modules" + File.separator
            + "iconography" + File.separator+ "toolbar" + File.separator),
    
    DIR_OT(File.separator + "lyra" + File.separator + "access" + File.separator + "modules" + File.separator
            + "iconography" + File.separator+ "order_icons" + File.separator),
    
    DIR_ARBOL(File.separator + "lyra" + File.separator + "access" + File.separator + "modules" + File.separator
            + "iconography" + File.separator+ "main_tree" + File.separator),
    
    DIR_VENTANA_GENE(File.separator + "lyra" + File.separator + "access" + File.separator + "modules" + File.separator
            + "iconography" + File.separator+ "general_windows" + File.separator),

    DIR_PRINCIPAL(File.separator + "lyra" + File.separator + "access" + File.separator + "modules" + File.separator
            + "iconography" + File.separator+ "main" + File.separator);

    private String Dir;

    private Directorios(String Dir) {
        this.Dir = Dir;

    }

    public String text() {

        return Dir;
    }

}
