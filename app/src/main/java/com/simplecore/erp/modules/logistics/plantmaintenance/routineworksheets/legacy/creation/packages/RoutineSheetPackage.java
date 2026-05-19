
package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.packages;

/**
 *
 * @Michael F. Sanchez
 */
public class RoutineSheetPackage {
    private String routineSheet;
    private int counter;
    private String operation;
    private String packageCode;
    private String maintenanceStrategy;

    // Constructor privado, solo accesible a través del Builder
    private RoutineSheetPackage(Builder builder) {
        this.routineSheet = builder.routineSheet;
        this.counter = builder.counter;
        this.operation = builder.operation;
        this.packageCode = builder.packageCode;
        this.maintenanceStrategy = builder.maintenanceStrategy;
    }

    // Getters
    public String getRoutineSheet() {
        return routineSheet;
    }

    public int getCounter() {
        return counter;
    }

    public String getOperation() {
        return operation;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public String getMaintenanceStrategy() {
        return maintenanceStrategy;
    }

    // Método toString() para representar la clase de forma legible
    @Override
    public String toString() {
        return "RoutineSheetPackage{" +
                "routineSheet='" + routineSheet + '\'' +
                ", counter=" + counter +
                ", operation='" + operation + '\'' +
                ", packageCode='" + packageCode + '\'' +
                ", maintenanceStrategy='" + maintenanceStrategy + '\'' +
                '}';
    }

    // Clase Builder
    public static class Builder {
        private String routineSheet;
        private int counter;
        private String operation;
        private String packageCode;
        private String maintenanceStrategy;

        // Métodos del Builder para configurar los campos
        public Builder setRoutineSheet(String routineSheet) {
            this.routineSheet = routineSheet;
            return this;
        }

        public Builder setCounter(int counter) {
            this.counter = counter;
            return this;
        }

        public Builder setOperation(String operation) {
            this.operation = operation;
            return this;
        }

        public Builder setPackageCode(String packageCode) {
            this.packageCode = packageCode;
            return this;
        }

        public Builder setMaintenanceStrategy(String maintenanceStrategy) {
            this.maintenanceStrategy = maintenanceStrategy;
            return this;
        }

        // Método para construir la instancia de RoutineSheetPackage
        public RoutineSheetPackage build() {
            if (routineSheet == null || operation == null || packageCode == null || maintenanceStrategy == null) {
                throw new IllegalArgumentException("All fields must be set.");
            }
            return new RoutineSheetPackage(this);
        }
    }
}
