package com.simplecore.erp.client.controllers.connections;

import java.io.File;
/**
 * @author Michael F. Sánchez
 * @version 1.0
 * @since 2024
 * @project Lyre Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class ServerConnectionFileManager {
    private static final String CONNECTION_FILE_URL = "config" + File.separator + "connections" + File.separator + "servers";

    public static void createDirectoriesIfNeeded() {
        File dir = new File(CONNECTION_FILE_URL);

        // Check if the directory exists; if not, create it (Verifica si el directorio existe; si no, lo crea)
        if (!dir.exists()) {
            // Create the directories and their parents if they don't exist (Crea los directorios y sus padres si no existen)
            boolean directoriesCreated = dir.mkdirs();

            // Check if the directories were created successfully (Verifica si los directorios fueron creados exitosamente)
            if (directoriesCreated) {
                System.out.println("Directories created successfully: " + dir.getAbsolutePath()); // Success message (Mensaje de éxito)
            } else {
                System.out.println("Failed to create directories: " + dir.getAbsolutePath()); // Failure message (Mensaje de fallo)
            }
        } else {
            System.out.println("Directories already exist: " + dir.getAbsolutePath()); // Message when directories already exist (Mensaje cuando los directorios ya existen)
        }
    }

    public static File getConnectionDirectory() {
        // Return the directory as a File object (Devuelve el directorio como un objeto File)
        return new File(CONNECTION_FILE_URL);
    }

    public static File getConnectionFile(String fileName) {
        return new File(CONNECTION_FILE_URL + File.separator + fileName);
    }

    public static void deleteConnectionFile(File file) {
        file.delete();
    }
    
}
