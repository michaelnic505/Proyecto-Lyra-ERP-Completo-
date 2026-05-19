package com.simplecore.erp.services.dbconnections;

import com.simplecore.erp.models.dbconnections.DatabaseConnectionFileCreator;
import com.simplecore.erp.controllers.dbconnections.ConnectionFileManager;
import com.simplecore.erp.models.dbconnections.DatabaseConnection;
import com.simplecore.erp.models.dbconnections.DatabaseConnectionFileReader;
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
public class ConnectionService {

    public static void createConnectionFile(File url, DatabaseConnection databaseConnection) {
        DatabaseConnectionFileCreator newFile = new DatabaseConnectionFileCreator(databaseConnection,url);
        newFile.createFile();
    }

    public static void modifyConnectionFile(File file, DatabaseConnection databaseConnection) {
        DatabaseConnectionFileCreator newFile = new DatabaseConnectionFileCreator(databaseConnection,file);
        newFile.createFile();
    }
    
    public static DatabaseConnectionFileReader readConnectionFile(File file){
        DatabaseConnectionFileReader fileToRead = new DatabaseConnectionFileReader(file);
        fileToRead.readFile();
        
        return fileToRead;
    }
    
    public static void createDirectoryIfNeeded(){
        ConnectionFileManager.createDirectoriesIfNeeded();
    }
    
    public static File getConnectionDirectoy(){
        return ConnectionFileManager.getConnectionDirectory();
    }

}
