package com.simplecore.erp.server.models.database;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyre Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class DatabaseConfig {
    private String dbUsername;
    private String dbPassword;
    private String dbConnector;
    private String dbHost;
    private String dbPort;
    private String dbName;

    // Constructor
    public DatabaseConfig(String dbUsername, String dbPassword, String dbConnector, String dbHost, String dbPort, String dbName) {
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.dbConnector = dbConnector;
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbName = dbName;
    }

    // Getters y Setters
    public String getDbUsername() { return dbUsername; }
    public String getDbPassword() { return dbPassword; }
    public String getDbConnector() { return dbConnector; }
    public String getDbHost() { return dbHost; }
    public String getDbPort() { return dbPort; }
    public String getDbName() { return dbName; }
}
