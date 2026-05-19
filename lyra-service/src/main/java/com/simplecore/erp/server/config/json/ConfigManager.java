
package com.simplecore.erp.server.config.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
/**
 * ConfigManager handles reading and writing server configuration from a JSON file.
 * (ConfigManager maneja la lectura y escritura de la configuración del servidor desde un archivo JSON.)
 */
public class ConfigManager {

    private static final String CONFIG_FILE = "config.json";

    /**
     * Reads the configuration from the JSON file.
     * (Lee la configuración desde el archivo JSON.)
     * 
     * @return JsonObject containing the configuration data. (JsonObject con los datos de configuración.)
     */
    public static JsonObject readConfig() {
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            return new Gson().fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            System.err.println("[ConfigManager] Error reading config: " + e.getMessage());
            return null;
        }
    }

    /**
     * Writes the configuration to the JSON file.
     * (Escribe la configuración en el archivo JSON.)
     * 
     * @param config JsonObject containing the new configuration. (JsonObject con la nueva configuración.)
     */
    public static void writeConfig(JsonObject config) {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            new Gson().toJson(config, writer);
        } catch (IOException e) {
            System.err.println("[ConfigManager] Error writing config: " + e.getMessage());
        }
    }
}