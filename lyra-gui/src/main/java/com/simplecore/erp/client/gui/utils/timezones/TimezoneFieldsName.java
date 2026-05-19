
package com.simplecore.erp.client.gui.utils.timezones;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum TimezoneFieldsName {
    
    TIMEZONE_INFO_WINDOW_TITLE("TIMEZONE.INFO.WINDOW.TITLE"),
    TIMEZONE_INFO_WINDOW_TITLE_PANE("TIMEZONE.INFO.WINDOW.TITLE.PANE"),

    TIMEZONE_ID_TEXT("TIMEZONE.ID.TEXT"),
    TIMEZONE_NAME_TEXT("TIMEZONE.ID.NAME");

    private String key;

    private TimezoneFieldsName(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
