/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils;

import javax.swing.JTextField;


public class Uppercases {
    
    
    public static JTextField HacerMayusculas(JTextField jt){
        
        jt.setText(jt.getText().toUpperCase());
        return jt;
        
    }
    
}
