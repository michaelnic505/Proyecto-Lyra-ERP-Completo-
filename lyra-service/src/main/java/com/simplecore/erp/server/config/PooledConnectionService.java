package com.simplecore.erp.server.config;

import com.google.gson.JsonObject;
import com.simplecore.erp.server.config.json.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PooledConnectionService {

    // Pool de conexiones HikariCP
    private static HikariDataSource dataSource;

    // Método para inicializar el pool de conexiones con un objeto DatabaseConfig
    static {

        JsonObject jsonConfig = ConfigManager.readConfig();
        if (jsonConfig == null) {
            throw new RuntimeException("Failed to load database configuration.");
        }

        JsonObject dbConfig = jsonConfig.getAsJsonObject("database");

        // Configuración del DataSource
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + dbConfig.get("dbhost").getAsString() + ":"
                + dbConfig.get("dbport").getAsInt() + "/"
                + dbConfig.get("dbname").getAsString());
        config.setUsername(dbConfig.get("dbuser").getAsString());
        config.setPassword(dbConfig.get("dbpassword").getAsString());
        config.setMaximumPoolSize(10); // Ajusta según tus necesidades
        config.setIdleTimeout(600000); // Tiempo máximo de inactividad de las conexiones
        config.setConnectionTimeout(60000); // Tiempo máximo de espera para obtener una conexión

        // Inicializar el DataSource (pool de conexiones)
        dataSource = new HikariDataSource(config);
    }

    // Método para obtener una conexión del pool
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();  // Obtener la conexión del pool

        } catch (SQLException ex) {
            Logger.getLogger(PooledConnectionService.class.getName()).log(Level.SEVERE, "Error obteniendo la conexión del pool", ex);
            return null;
        }
    }

    // Método para cerrar una conexión (en realidad, la devuelve al pool)
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();  // Devuelve la conexión al pool
            }
        } catch (SQLException ex) {
            Logger.getLogger(PooledConnectionService.class.getName()).log(Level.SEVERE, "Error cerrando la conexión", ex);
        }
    }

    // Begin transaction (con el pool, se sigue trabajando igual)
    public static void beginTransaction(Connection connection) {
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            Logger.getLogger(PooledConnectionService.class.getName()).log(Level.SEVERE, "Error comenzando la transacción", e);
        }
    }

    // Commit transaction (igual con el pool)
    public static void commitTransaction(Connection connection) {
        try {
            if (connection != null) {
                connection.commit();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            Logger.getLogger(PooledConnectionService.class.getName()).log(Level.SEVERE, "Error cometiendo la transacción", e);
        }
    }

    // Rollback transaction (igual con el pool)
    public static void rollbackTransaction(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            Logger.getLogger(PooledConnectionService.class.getName()).log(Level.SEVERE, "Error haciendo rollback de la transacción", e);
        }
    }

    // Método para cerrar el pool (generalmente se llama al final de la aplicación)
    public static void closePool() {
        if (dataSource != null) {
            dataSource.close();  // Cerrar el pool de conexiones
        }
    }

    public static boolean isPoolRunning() {
        return dataSource != null && !dataSource.isClosed();
    }

    public static int getActiveConnections() {
        try {
            // Suponiendo que usas HikariCP u otro pool que tenga métricas
            return dataSource.getHikariPoolMXBean().getActiveConnections();
        } catch (Exception e) {
            Logger.getLogger(PooledConnectionService.class.getName()).log(Level.SEVERE, "Error retrieving active connections", e);
            return -1;
        }
    }

}
