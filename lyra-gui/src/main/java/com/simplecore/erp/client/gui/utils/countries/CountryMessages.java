package com.simplecore.erp.client.gui.utils.countries;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum CountryMessages {

    COUNTRY_INFO_WINDOW_TITLE("COUNTRY.INFO.WINDOW.TITLE"),
    COUNTRY_INFO_WINDOW_TITLE_PANE("COUNTRY.INFO.WINDOW.TITLE.PANE"),
    CURRENCY_INFO_WINDOW_TITLE("CURRENCY.INFO.WINDOW.TITLE"),
    CURRENCY_INFO_WINDOW_TITLE_PANE("CURRENCY.INFO.WINDOW.TITLE.PANE");
    

    private String key;

    private CountryMessages(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    

}
