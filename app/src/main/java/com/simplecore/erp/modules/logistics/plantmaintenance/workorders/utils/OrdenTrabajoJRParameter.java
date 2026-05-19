package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.utils;

import java.util.HashMap;
import java.util.Map;


public class OrdenTrabajoJRParameter{

    Map<String, Object> parametros = new HashMap();
    
    public void agregarParametro(String nombreParametro, Object objeto) {        
        parametros.put(nombreParametro, objeto);        
    }
    
    public Map getMatrizParametros(){        
        return parametros;
    }
    
}
