
package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.activity;

public class RoutineSheetOperation {
    private final String routineSheet;
    private final int counter;
    private final String operation;
    private final String procedure;
    private final String operationDescription;
    private final double work;
    private final double quantity;
    private final double duration;
    private final String operationTypeCode;
    private final String operationType;
    private final double unitCost;
    private final String unitOfMeasure;
    private final double totalAmount;
    private final String orderRequest;
    private final String currency;

    private RoutineSheetOperation(Builder builder) {
        this.routineSheet = builder.routineSheet;
        this.counter = builder.counter;
        this.operation = builder.operation;
        this.procedure = builder.procedure;
        this.operationDescription = builder.operationDescription;
        this.work = builder.work;
        this.quantity = builder.quantity;
        this.duration = builder.duration;
        this.operationTypeCode = builder.operationTypeCode;
        this.operationType = builder.operationType;
        this.unitCost = builder.unitCost;
        this.unitOfMeasure = builder.unitOfMeasure;
        this.totalAmount = builder.totalAmount;
        this.orderRequest = builder.orderRequest;
        this.currency = builder.currency;
    }

    public static class Builder {
        private String routineSheet;
        private int counter;
        private String operation;
        private String procedure;
        private String operationDescription;
        private double work;
        private double quantity;
        private double duration;
        private String operationTypeCode;
        private String operationType;
        private double unitCost;
        private String unitOfMeasure;
        private double totalAmount;
        private String orderRequest;
        private String currency;

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

        public Builder setProcedure(String procedure) {
            this.procedure = procedure;
            return this;
        }

        public Builder setOperationDescription(String operationDescription) {
            this.operationDescription = operationDescription;
            return this;
        }

        public Builder setWork(double work) {
            this.work = work;
            return this;
        }

        public Builder setQuantity(double quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setDuration(double duration) {
            this.duration = duration;
            return this;
        }

        public Builder setOperationTypeCode(String operationTypeCode) {
            this.operationTypeCode = operationTypeCode;
            return this;
        }

        public Builder setOperationType(String operationType) {
            this.operationType = operationType;
            return this;
        }

        public Builder setUnitCost(double unitCost) {
            this.unitCost = unitCost;
            return this;
        }

        public Builder setUnitOfMeasure(String unitOfMeasure) {
            this.unitOfMeasure = unitOfMeasure;
            return this;
        }

        public Builder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setOrderRequest(String orderRequest) {
            this.orderRequest = orderRequest;
            return this;
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public RoutineSheetOperation build() {
            return new RoutineSheetOperation(this);
        }
    }

    public String getRoutineSheet() {
        return routineSheet;
    }

    public int getCounter() {
        return counter;
    }

    public String getOperation() {
        return operation;
    }

    public String getProcedure() {
        return procedure;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public double getWork() {
        return work;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getDuration() {
        return duration;
    }

    public String getOperationTypeCode() {
        return operationTypeCode;
    }

    public String getOperationType() {
        return operationType;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderRequest() {
        return orderRequest;
    }

    public String getCurrency() {
        return currency;
    }
}
