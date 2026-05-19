package com.simplecore.erp.client.models.connections;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyre Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class ServerConnection {

    private final int port;
    private final String hostname;
    private final String filename;

    // Constructor privado para evitar la creación directa de objetos sin pasar por el Builder
    private ServerConnection(Builder builder) {
        this.port = builder.port;
        this.hostname = builder.hostname;
        this.filename = builder.filename;
    }

    // Getters
    public int getPort() {
        return port;
    }

    public String getHostname() {
        return hostname;
    }

    public String getFilename() {
        return filename;
    }

    // Clase Builder
    public static class Builder {

        private int port;
        private String hostname;
        private String filename;

        // Métodos para establecer cada propiedad
        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setHostname(String hostname) {
            this.hostname = hostname;
            return this;
        }

        public Builder setFilename(String filename) {
            this.filename = filename;
            return this;
        }

        // Método para construir el objeto DatabaseConnection
        public ServerConnection build() {
            return new ServerConnection(this); // Crea el objeto final
        }
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
