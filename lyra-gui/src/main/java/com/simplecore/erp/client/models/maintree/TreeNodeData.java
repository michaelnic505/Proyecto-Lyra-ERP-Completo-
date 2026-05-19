
package com.simplecore.erp.client.models.maintree;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TreeNodeData {

    private String code;  // Código interno (clave única)
    private String displayName; // Nombre visible en el JTree
    private boolean showCode; // Indica si el código debe mostrarse junto con la descripción

    public TreeNodeData(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
        this.showCode = false; // Por defecto, no mostrar el código
    }

    public String getCode() {
        return code;
    }

    public void setShowCode(boolean showCode) {
        this.showCode = showCode;
    }

    public boolean isShowCode() {
        return showCode;
    }

    @Override
    public String toString() {
        if (showCode) {
            return "("+code+")" + " " + displayName; // Mostrar código y descripción
        } else {
            return displayName; // Mostrar solo la descripción
        }
    }
}