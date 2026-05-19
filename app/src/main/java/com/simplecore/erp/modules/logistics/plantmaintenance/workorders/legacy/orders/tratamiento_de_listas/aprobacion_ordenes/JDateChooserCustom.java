
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.tratamiento_de_listas.aprobacion_ordenes;

import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;


public class JDateChooserCustom extends JDateChooser{

    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy.MM.dd");
    
    public JDateChooserCustom() {
        setDateFormatString("yyyy.MM.dd");    
    }
    
    
    private String getDateString(){
        return formatoFecha.format(getDate());
    }
    
    public void setDateAtTable(ListDateEvent evt, int row) {
        
        getDateEditor().addPropertyChangeListener((e) -> {
            if ("date".equals(e.getPropertyName())) {
                evt.setDate(row,getDateString());
            }
        });

    }

    
}
