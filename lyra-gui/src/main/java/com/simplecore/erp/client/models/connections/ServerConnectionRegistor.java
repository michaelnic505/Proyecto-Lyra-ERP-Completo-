package com.simplecore.erp.client.models.connections;

import java.io.Serializable;

/**
 * Represents database connection data for serialization.
 */
public class ServerConnectionRegistor implements Serializable {

    private static final long serialVersionUID = 1L; // Ensures serialization compatibility

    private int port;
    private String hostname;
    private String filename;

    public ServerConnectionRegistor(ServerConnection serverConnection) {
        this.port = serverConnection.getPort();
        this.hostname = serverConnection.getHostname();
        this.filename = serverConnection.getFilename();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "ServerConnection{"
                + "port='" + port + '\''
                + ", hostname='" + hostname + '\''
                + ", filename='" + filename + '\''
                + '}';
    }

}
