/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simplecore.erp.config.date;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import java.time.Duration;
import java.util.concurrent.ExecutionException;

public class NTPDateFetcher extends JFrame {
    
    private static final String GOOGLE_TIME = "time.google.com";
    private JProgressBar progressBar;
    private JButton startButton;

    public NTPDateFetcher() {
        
        // Configuración del JFrame
        setTitle("NTP Date Fetcher");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Crear componentes
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true); // Mostrar porcentaje
        progressBar.setPreferredSize(new Dimension(350, 30));
        
        startButton = new JButton("Start");
        startButton.addActionListener(e -> startProcess());

        // Layout
        setLayout(new FlowLayout());
        add(progressBar);
        add(startButton);

        // Hacer visible el JFrame
        setVisible(true);
    }

    
    
    
    // Método para iniciar el proceso
    private void startProcess() {
      
        startButton.setEnabled(false);  // Deshabilitar el botón mientras se ejecuta el proceso
        
        // Usamos SwingWorker para ejecutar el proceso en segundo plano
        SwingWorker<Date, Integer> worker = new SwingWorker<>() {
            @Override
            protected Date doInBackground() throws Exception {
                // Paso 1: Intentar abrir la conexión (esto tomará un poco de tiempo)
                publish(10); // Actualizamos la barra de progreso
               
                
                
                NTPUDPClient client = new NTPUDPClient();
                client.open();
                client.setSoTimeout(Duration.ofMillis(3000));

                // Paso 2: Realizar la consulta al servidor NTP
                publish(50); // Actualizamos la barra de progreso
                
                
                
                InetAddress hostAddr = InetAddress.getByName(GOOGLE_TIME);
                TimeInfo timeInfo = client.getTime(hostAddr);

                // Paso 3: Obtener la fecha
                publish(80); // Actualizamos la barra de progreso
                
                
                
                
                Date date = null;
                if (timeInfo != null) {
                    long serverTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
                    date = new Date(serverTime); // Fecha obtenida desde el servidor NTP
                }

                
                
                
                // Paso 4: Finalizar
                publish(100); // Actualizamos la barra de progreso a 100%
               
                
                
                client.close();

                return date;  // Devolver la fecha obtenida
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                // Actualizamos la barra de progreso con los valores enviados
                progressBar.setValue(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                try {
                    Date date = get(); // Obtener la fecha una vez que el proceso termine
                    if (date != null) {
                        JOptionPane.showMessageDialog(null, "Fecha obtenida: " + date);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo obtener la fecha.");
                    }
                } catch (HeadlessException | InterruptedException | ExecutionException e) {
                    JOptionPane.showMessageDialog(null, "Error al obtener la fecha");
                } finally {
                    startButton.setEnabled(true);  // Habilitar el botón nuevamente
                }
            }
        };

        worker.execute();  // Ejecutar el worker en segundo plano
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(NTPDateFetcher::new);
    }
}
