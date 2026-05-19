
package com.simplecore.erp.gui.components.tables.lastversion;

import javax.swing.table.DefaultTableModel;


public class LyraTableModel2 extends DefaultTableModel{

    public LyraTableModel2() {
        
    }

    
    @Override
    public void addRow(Object[] rowData) {
        
        int cant = rowData.length;
        Object[] newRowData = new Object[cant+1];
        
        for(int i = 0; i < newRowData.length; i++){
            if(i==0){
               newRowData[i]=null;
            }else{
               newRowData[i]=rowData[i-1];
            }            
        }
        
        super.addRow(newRowData); 
    }

    @Override
    public void setColumnIdentifiers(Object[] newIdentifiers) {
         int cant = newIdentifiers.length;
         Object[] newIdentifier = new Object[cant+1];
         
         for(int i = 0; i < newIdentifier.length; i++){
             if(i==0){
                 newIdentifier[i]="";
             }else{
                 newIdentifier[i]=newIdentifiers[i-1];
             }
         }
                 
        super.setColumnIdentifiers(newIdentifier); 
    }
    
     
    
    
    
}
