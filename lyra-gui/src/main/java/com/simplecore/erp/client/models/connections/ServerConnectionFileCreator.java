
package com.simplecore.erp.client.models.connections;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the creation of database connection files. (Administra la creación de archivos de conexión a base de datos)
 */
public class ServerConnectionFileCreator {
    
    private static final Logger LOGGER = Logger.getLogger(ServerConnectionFileCreator.class.getName());
    private static final String FILE_EXTENSION = ".ly";
    private final String filename;
    private final File directory;
    /**
     * Constructor for CreateDataConnectionFile. (Constructor de CreateDataConnectionFile)
     * 
     * @param connector Database connector string (Cadena del conector de la base de datos)
     * @param port Database port (Puerto de la base de datos)
     * @param hostname Database hostname (Nombre del host de la base de datos)
     * @param db Database name (Nombre de la base de datos)
     * @param filename Name of the connection file without extension (Nombre del archivo de conexión sin extensión)
     * @param directory Directory where the file will be created (Directorio donde se creará el archivo)
     */

    private final ServerConnection serverConnection;
    public ServerConnectionFileCreator(ServerConnection serverConnection, File directory) {
        this.serverConnection = serverConnection;
        this.filename = serverConnection.getFilename();
        this.directory = directory;
    }
    /**
     * Creates the database connection file. (Crea el archivo de conexión a la base de datos)
     */
    public void createFile() {
        if (!directory.exists() && !directory.mkdirs()) {
            LOGGER.log(Level.SEVERE, "Failed to create directory: {0}", directory.getAbsolutePath());
            return;
        }

        File file = new File(directory, filename + FILE_EXTENSION);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            ServerConnectionRegistor record = new ServerConnectionRegistor(serverConnection);
            outputStream.writeObject(record);
            LOGGER.log(Level.INFO, "Connection file created successfully: {0}", file.getAbsolutePath());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error creating connection file: " + file.getAbsolutePath(), ex);
        }
    }
}
