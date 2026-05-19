
package com.simplecore.erp.models.dbconnections;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyre Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

public class DatabaseConnection {

    private final String connector;
    private final String port;
    private final String hostname;
    private final String db;
    private final String filename;

    // Constructor privado para evitar la creación directa de objetos sin pasar por el Builder
    private DatabaseConnection(Builder builder) {
        this.connector = builder.connector;
        this.port = builder.port;
        this.hostname = builder.hostname;
        this.db = builder.db;
        this.filename = builder.filename;
    }

    // Getters
    public String getConnector() {
        return connector;
    }

    public String getPort() {
        return port;
    }

    public String getHostname() {
        return hostname;
    }

    public String getDb() {
        return db;
    }

    public String getFilename() {
        return filename;
    }

    // Clase Builder
    public static class Builder {

        private String connector;
        private String port;
        private String hostname;
        private String db;
        private String filename;

        // Métodos para establecer cada propiedad
        public Builder setConnector(String connector) {
            this.connector = connector;
            return this; // Retorna el mismo Builder para encadenar llamadas
        }

        public Builder setPort(String port) {
            this.port = port;
            return this;
        }

        public Builder setHostname(String hostname) {
            this.hostname = hostname;
            return this;
        }

        public Builder setDb(String db) {
            this.db = db;
            return this;
        }

        public Builder setFilename(String filename) {
            this.filename = filename;
            return this;
        }

        // Método para construir el objeto DatabaseConnection
        public DatabaseConnection build() {
            return new DatabaseConnection(this); // Crea el objeto final
        }
    }

    @Override
    public String toString() {
        return "DatabaseConnection{" +
                "connector='" + connector + '\'' +
                ", port='" + port + '\'' +
                ", hostname='" + hostname + '\'' +
                ", db='" + db + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
