package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Languages extends Properties {

    private static final long serialVersionUID = 1L;

    public Languages(String idioma) {

        switch (idioma) {
            case "ES" ->
                getProperties("/lyra/access/modules/master_data/characteristics/es.characteristics.properties");
            case "EN" ->
                getProperties("/lyra/access/modules/master_data/characteristics/en.characteristics.properties");
            case "PT" ->
                getProperties("/lyra/access/modules/master_data/characteristics/en.characteristics.properties");
            default ->
                getProperties("/lyra/access/modules/master_data/characteristics/en.characteristics.properties");
        }

    }

    private void getProperties(String idioma) {

        try {
            this.load(getClass().getResourceAsStream(idioma));
        } catch (IOException ex) {
            Logger.getLogger(Languages.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
