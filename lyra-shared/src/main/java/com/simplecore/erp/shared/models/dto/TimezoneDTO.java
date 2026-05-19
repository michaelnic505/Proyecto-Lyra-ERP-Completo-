

package com.simplecore.erp.shared.models.dto;

import java.io.Serializable;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TimezoneDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private final int id;
    private final String name;

    public TimezoneDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
