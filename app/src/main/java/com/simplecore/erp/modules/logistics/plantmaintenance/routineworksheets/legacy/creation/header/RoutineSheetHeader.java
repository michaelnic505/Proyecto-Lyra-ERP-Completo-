package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.header;



import java.sql.Date;

public class RoutineSheetHeader {
    private String routineSheet;
    private int counter;
    private String positionName;
    private String planningGroup;
    private String operationType;
    private boolean status;
    private int operatingContext;
    private String maintenanceStrategy;
    private int usage;
    private Date scheduledDay;

    // Constructor privado para que solo se pueda acceder desde el Builder
    private RoutineSheetHeader(Builder builder) {
        this.routineSheet = builder.routineSheet;
        this.counter = builder.counter;
        this.positionName = builder.positionName;
        this.planningGroup = builder.planningGroup;
        this.operationType = builder.operationType;
        this.status = builder.status;
        this.operatingContext = builder.operatingContext;
        this.maintenanceStrategy = builder.maintenanceStrategy;
        this.usage = builder.usage;
        this.scheduledDay = builder.scheduledDay;
    }

    // Métodos getter
    public String getRoutineSheet() {
        return routineSheet;
    }

    public int getCounter() {
        return counter;
    }

    public String getPositionName() {
        return positionName;
    }

    public String getPlanningGroup() {
        return planningGroup;
    }

    public String getOperationType() {
        return operationType;
    }

    public boolean isStatus() {
        return status;
    }

    public int getOperatingContext() {
        return operatingContext;
    }

    public String getMaintenanceStrategy() {
        return maintenanceStrategy;
    }

    public int getUsage() {
        return usage;
    }

    public Date getScheduledDay() {
        return scheduledDay;
    }

    // Builder
    public static class Builder {
        private String routineSheet;
        private int counter;
        private String positionName;
        private String planningGroup;
        private String operationType;
        private boolean status;
        private int operatingContext;
        private String maintenanceStrategy;
        private int usage;
        private Date scheduledDay;

        // Métodos para configurar los valores
        public Builder setRoutineSheet(String routineSheet) {
            this.routineSheet = routineSheet;
            return this;
        }

        public Builder setCounter(int counter) {
            this.counter = counter;
            return this;
        }

        public Builder setPositionName(String positionName) {
            this.positionName = positionName;
            return this;
        }

        public Builder setPlanningGroup(String planningGroup) {
            this.planningGroup = planningGroup;
            return this;
        }

        public Builder setOperationType(String operationType) {
            this.operationType = operationType;
            return this;
        }

        public Builder setStatus(boolean status) {
            this.status = status;
            return this;
        }

        public Builder setOperatingContext(int operatingContext) {
            this.operatingContext = operatingContext;
            return this;
        }

        public Builder setMaintenanceStrategy(String maintenanceStrategy) {
            this.maintenanceStrategy = maintenanceStrategy;
            return this;
        }

        public Builder setUsage(int usage) {
            this.usage = usage;
            return this;
        }

        public Builder setScheduledDay(Date scheduledDay) {
            this.scheduledDay = scheduledDay;
            return this;
        }

        public RoutineSheetHeader build() {
            return new RoutineSheetHeader(this);
        }
    }

    @Override
    public String toString() {
        return "RoutineSheetHeader{" +
                "routineSheet='" + routineSheet + '\'' +
                ", counter=" + counter +
                ", positionName='" + positionName + '\'' +
                ", planningGroup='" + planningGroup + '\'' +
                ", operationType='" + operationType + '\'' +
                ", status=" + status +
                ", operatingContext=" + operatingContext +
                ", maintenanceStrategy='" + maintenanceStrategy + '\'' +
                ", usage=" + usage +
                ", scheduledDay=" + scheduledDay +
                '}';
    }
}
