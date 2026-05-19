
package com.simplecore.erp.client.utils.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class SoundManager {

    private static boolean isActive = true;
    
    public static void setActivate(boolean isActive){
        SoundManager.isActive = isActive;
    }
    public static boolean isActivated(){
        return isActive;
    }
    
    public static void playSound(String soundFileName) {
        if(!isActive){
            return;
        }
        
        try {
            // Obtiene el archivo de sonido desde el classpath
            InputStream audioSrc = SoundManager.class.getResourceAsStream(soundFileName);
            if (audioSrc == null) {
                System.out.println("Error: No se encontró el archivo de sonido.");
                return;
            }
            // Carga el sonido
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioSrc);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();  // Reproduce el sonido
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
