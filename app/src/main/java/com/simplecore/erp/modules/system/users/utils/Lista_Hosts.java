
package com.simplecore.erp.modules.system.users.utils;


public enum Lista_Hosts {
    
    LOCAL_HOST("localhost")
    
    ;
    
    private String HOST;
    
    private Lista_Hosts(String HOST){
        
        this.HOST = HOST;
    }
    
    
    public String getHost(){
        return HOST;
    }
    
    
}
