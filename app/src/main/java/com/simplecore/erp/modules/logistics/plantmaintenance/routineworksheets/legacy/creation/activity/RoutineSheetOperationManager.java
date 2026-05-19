
package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.activity;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author user
 */
public class RoutineSheetOperationManager {
    private Map<String, String> operations; 

    public RoutineSheetOperationManager(Map<String, String> operations) {
        this.operations = operations;
    }
    
        // Agregar una operación
    public void addOperation(String operation) {
        if (!operations.containsKey(operation)) {
            operations.put(operation, "new");  // Nueva operación
        } else if (operations.get(operation).equals("deleted")) {
            operations.put(operation, "saved"); // Restaurar si estaba eliminada
        }
    }
    
        // Eliminar una operación
    public void removeOperation(String operation) {
        if (operations.containsKey(operation)) {
            if (operations.get(operation).equals("saved")) {
                operations.put(operation, "deleted"); // Marcar para eliminación si ya estaba en BD
            } else {
                operations.remove(operation); // Si era nueva, se borra directamente
            }
        }
    }
        // Simular la recuperación de datos de la BD en una nueva sesión
    public void loadFromDatabase(Set<String> dbOperations) {
        this.operations.clear();
        for (String op : dbOperations) {
            operations.put(op, "saved");
        }
    }
    
}
