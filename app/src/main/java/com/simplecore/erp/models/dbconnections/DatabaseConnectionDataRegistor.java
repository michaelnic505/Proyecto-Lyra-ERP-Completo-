package com.simplecore.erp.models.dbconnections;

import java.io.Serializable;

/**
 * Represents database connection data for serialization.
 */
public class DatabaseConnectionDataRegistor implements Serializable {

    private static final long serialVersionUID = 1L; // Ensures serialization compatibility

    private String connector;
    private String port;
    private String hostname;
    private String database;
    private String filename;

    public DatabaseConnectionDataRegistor(DatabaseConnection databaseConnection){
        this.connector = databaseConnection.getConnector();
        this.port = databaseConnection.getPort();
        this.hostname = databaseConnection.getHostname();
        this.database = databaseConnection.getDb();
        this.filename = databaseConnection.getFilename();
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "DatabaseConnectionData{" +
                "connector='" + connector + '\'' +
                ", port='" + port + '\'' +
                ", hostname='" + hostname + '\'' +
                ", database='" + database + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
