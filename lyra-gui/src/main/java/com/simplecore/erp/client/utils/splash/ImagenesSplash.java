
package com.simplecore.erp.client.utils.splash;

import javax.swing.ImageIcon;


public enum ImagenesSplash {
    
    STAR_1(new ImageIcon("src\\lyra\\access\\modules\\system_resources\\notifications\\starMar.png")),
    STAR_2(new ImageIcon("src\\lyra\\access\\modules\\system_resources\\notifications\\starDUST.png")),
    STAR_3(new ImageIcon("src\\lyra\\access\\modules\\system_resources\\notifications\\star3.png"));
    
     
   
    private ImageIcon icono;
    
    private ImagenesSplash(ImageIcon icono) {
        this.icono = icono;

    }

    public ImageIcon asignarIcono() {

        return icono;
    }
    
    
}
