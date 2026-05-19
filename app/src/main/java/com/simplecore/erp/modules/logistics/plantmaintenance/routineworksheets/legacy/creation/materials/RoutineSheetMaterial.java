package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.materials;

/**
 *
 * @Michael F. Sanchez
 */
public class RoutineSheetMaterial {
    private final String routineSheet;
    private final int counter;
    private final String position;
    private final String material;
    private final String componentDenomination;
    private final double quantity;
    private final String unitOfMeasure;
    private final double unitCost;
    private final String warehouse;
    private final double totalAmount;
    private final String classification;

    private RoutineSheetMaterial(Builder builder) {
        this.routineSheet = builder.routineSheet;
        this.counter = builder.counter;
        this.position = builder.position;
        this.material = builder.material;
        this.componentDenomination = builder.componentDenomination;
        this.quantity = builder.quantity;
        this.unitOfMeasure = builder.unitOfMeasure;
        this.unitCost = builder.unitCost;
        this.warehouse = builder.warehouse;
        this.totalAmount = builder.totalAmount;
        this.classification = builder.classification;
    }

    public static class Builder {
        private String routineSheet;
        private int counter;
        private String position;
        private String material;
        private String componentDenomination;
        private double quantity;
        private String unitOfMeasure;
        private double unitCost;
        private String warehouse;
        private double totalAmount;
        private String classification;

        public Builder setRoutineSheet(String routineSheet) {
            this.routineSheet = routineSheet;
            return this;
        }

        public Builder setCounter(int counter) {
            this.counter = counter;
            return this;
        }

        public Builder setPosition(String position) {
            this.position = position;
            return this;
        }

        public Builder setMaterial(String material) {
            this.material = material;
            return this;
        }

        public Builder setComponentDenomination(String componentDenomination) {
            this.componentDenomination = componentDenomination;
            return this;
        }

        public Builder setQuantity(double quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setUnitOfMeasure(String unitOfMeasure) {
            this.unitOfMeasure = unitOfMeasure;
            return this;
        }

        public Builder setUnitCost(double unitCost) {
            this.unitCost = unitCost;
            return this;
        }

        public Builder setWarehouse(String warehouse) {
            this.warehouse = warehouse;
            return this;
        }

        public Builder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setClassification(String classification) {
            this.classification = classification;
            return this;
        }

        public RoutineSheetMaterial build() {
            return new RoutineSheetMaterial(this);
        }
    }

    public String getRoutineSheet() {
        return routineSheet;
    }

    public int getCounter() {
        return counter;
    }

    public String getPosition() {
        return position;
    }

    public String getMaterial() {
        return material;
    }

    public String getComponentDenomination() {
        return componentDenomination;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getClassification() {
        return classification;
    }
}
