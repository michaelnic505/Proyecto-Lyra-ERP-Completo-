
package com.simplecore.erp.utils.sound;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum Sound {
    ACCEPT("/sounds/basics/accept.wav"),
    BUTTON("/sounds/basics/button.wav"),
    CLOSE("/sounds/basics/close.wav"),
    CLOSE_DIALOG("/sounds/basics/close_dialog.wav"),
    CLOSE_WINDOW("/sounds/basics/close_window.wav"),
    COLLAPSE_NODE("/sounds/basics/collapse_node.wav"),
    EMPTY_FIELD("/sounds/basics/empty_field.wav"),
    EXPAND_NODE("/sounds/basics/expand_node.wav"),
    NEXT("/sounds/basics/next.wav"),
    OPEN("/sounds/basics/open.wav"),
    SELECTION("/sounds/basics/selection.wav"),
    DONE("/sounds/notification/done.wav"),
    INFO("/sounds/notification/info.wav"),
    NOTIFICATION("/sounds/notification/notification.wav"),
    SAVED("/sounds/notification/saved.wav");
    
    private String url;

    private Sound(String url) {
        this.url = url;
    }
    public String dir(){
        return url;
    }
    
}
