
package com.simplecore.erp.client.models.connections;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for reading a serialized database connection file.
 * (Clase para leer un archivo de conexión a base de datos serializado)
 */
public class ServerConnectionFileReader {

    private static final Logger LOGGER = Logger.getLogger(ServerConnectionFileReader.class.getName());

    private int port;
    private String hostname;
    private String filename;
    private final File file;

    public int getPort() {
        return port;
    }

    public String getHostname() {
        return hostname;
    }

    public String getFilename() {
        return filename;
    }

    /**
     * Reads the connection data from the serialized file.
     * (Lee los datos de conexión desde el archivo serializado)
     *
     * @return true if the file was read successfully, false otherwise.
     * (true si el archivo se leyó correctamente, false en caso contrario)
     */
    public ServerConnectionFileReader(File file) {
        this.file = file;
    }

    public boolean readFile() {
        if (file == null || !file.exists()) {
            LOGGER.log(Level.WARNING, "The connection file does not exist: {0}", file != null ? file.getAbsolutePath() : "null");
            return false;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            ServerConnectionRegistor connectionData = (ServerConnectionRegistor) inputStream.readObject();

            this.port = connectionData.getPort();
            this.hostname = connectionData.getHostname();
            this.filename = connectionData.getFilename();

            LOGGER.log(Level.INFO, "Connection file read successfully: {0}", file.getAbsolutePath());
            return true;

        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error reading connection file: " + file.getAbsolutePath(), ex);
            return false;
        }
    }
}
