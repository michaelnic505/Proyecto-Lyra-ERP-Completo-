package com.simplecore.erp.client.services.connections;

import com.simplecore.erp.client.controllers.connections.ServerConnectionFileManager;
import com.simplecore.erp.client.models.connections.ServerConnection;
import com.simplecore.erp.client.models.connections.ServerConnectionFileCreator;
import com.simplecore.erp.client.models.connections.ServerConnectionFileReader;
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

    public static void createConnectionFile(File url, ServerConnection databaseConnection) {
        ServerConnectionFileCreator newFile = new ServerConnectionFileCreator(databaseConnection,url);
        newFile.createFile();
    }

    public static void modifyConnectionFile(File file, ServerConnection databaseConnection) {
        ServerConnectionFileCreator newFile = new ServerConnectionFileCreator(databaseConnection,file);
        newFile.createFile();
    }
    
    public static ServerConnectionFileReader readConnectionFile(File file){
        ServerConnectionFileReader fileToRead = new ServerConnectionFileReader(file);
        fileToRead.readFile();
        
        return fileToRead;
    }
    
    public static void createDirectoryIfNeeded(){
        ServerConnectionFileManager.createDirectoriesIfNeeded();
    }
    
    public static File getConnectionDirectoy(){
        return ServerConnectionFileManager.getConnectionDirectory();
    }
    
    

}
