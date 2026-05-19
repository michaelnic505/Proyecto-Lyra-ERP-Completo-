package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.model;

/**
 *
 * @Michael F. Sanchez
 */
public class Characteristic {

    private String characteristic;
    private String validFrom;
    private String description;
    private String charGroup;
    private String charStatus;
    private String status;

    private boolean singleValue;
    private boolean multiplesValues;
    private boolean intervalsAllowed;
    private boolean negativeValsAllowed;
    private boolean restrictable;
    private boolean entryRequired;

    private String dataType;

    private int numberCharacters;
    private int numberDecimals;

    private String unitOfMeasure;
    private String currencySimbols;
    private String template;

    private Characteristic(Builder builder) {
        this.characteristic = builder.characteristic;
        this.validFrom = builder.validFrom;
        this.description = builder.description;
        this.charGroup = builder.charGroup;
        this.charStatus = builder.charStatus;
        this.status = builder.status;
        this.singleValue = builder.singleValue;
        this.multiplesValues = builder.multiplesValues;
        this.intervalsAllowed = builder.intervalsAllowed;
        this.negativeValsAllowed = builder.negativeValsAllowed;
        this.restrictable = builder.restrictable;
        this.entryRequired = builder.entryRequired;
        this.dataType = builder.dataType;
        this.numberCharacters = builder.numberCharacters;
        this.numberDecimals = builder.numberDecimals;
        this.unitOfMeasure = builder.unitOfMeasure;
        this.currencySimbols = builder.currencySimbols;
        this.template = builder.template;
    }

    public static class Builder {

        private String characteristic;
        private String validFrom;
        private String description;
        private String charGroup;
        private String charStatus;
        private String status;

        private boolean singleValue;
        private boolean multiplesValues;
        private boolean intervalsAllowed;
        private boolean negativeValsAllowed;
        private boolean restrictable;
        private boolean entryRequired;

        private String dataType;

        private int numberCharacters;
        private int numberDecimals;

        private String unitOfMeasure;
        private String currencySimbols;
        private String template;

        public Builder characteristic(String characteristic) {
            this.characteristic = characteristic;
            return this;
        }

        public Builder validFrom(String validFrom) {
            this.validFrom = validFrom;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder charGroup(String charGroup) {
            this.charGroup = charGroup;
            return this;
        }

        public Builder charStatus(String charStatus) {
            this.charStatus = charStatus;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder singleValue(boolean singleValue) {
            this.singleValue = singleValue;
            return this;
        }

        public Builder multiplesValues(boolean multiplesValues) {
            this.multiplesValues = multiplesValues;
            return this;
        }

        public Builder intervalsAllowed(boolean intervalsAllowed) {
            this.intervalsAllowed = intervalsAllowed;
            return this;
        }

        public Builder negativeValsAllowed(boolean negativeValsAllowed) {
            this.negativeValsAllowed = negativeValsAllowed;
            return this;
        }

        public Builder restrictable(boolean restrictable) {
            this.restrictable = restrictable;
            return this;
        }

        public Builder entryRequired(boolean entryRequired) {
            this.entryRequired = entryRequired;
            return this;
        }

        public Builder dataType(String dataType) {
            this.dataType = dataType;
            return this;
        }

        public Builder numberCharacters(int numberCharacters) {
            this.numberCharacters = numberCharacters;
            return this;
        }

        public Builder numberDecimals(int numberDecimals) {
            this.numberDecimals = numberDecimals;
            return this;
        }

        public Builder unitOfMeasure(String unitOfMeasure) {
            this.unitOfMeasure = unitOfMeasure;
            return this;
        }

        public Builder currencySimbols(String currencySimbols) {
            this.currencySimbols = currencySimbols;
            return this;
        }

        public Builder template(String template) {
            this.template = template;
            return this;
        }

        public Characteristic build() {
            return new Characteristic(this);
        }
    }

    // Getters
    public String getCharacteristic() {
        return characteristic;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public String getDescription() {
        return description;
    }

    public String getCharGroup() {
        return charGroup;
    }

    public String getCharStatus() {
        return charStatus;
    }

    public String getStatus() {
        return status;
    }

    public boolean isSingleValue() {
        return singleValue;
    }

    public boolean isMultiplesValues() {
        return multiplesValues;
    }

    public boolean isIntervalsAllowed() {
        return intervalsAllowed;
    }

    public boolean isNegativeValsAllowed() {
        return negativeValsAllowed;
    }

    public boolean isRestrictable() {
        return restrictable;
    }

    public boolean isEntryRequired() {
        return entryRequired;
    }

    public String getDataType() {
        return dataType;
    }

    public int getNumberCharacters() {
        return numberCharacters;
    }

    public int getNumberDecimals() {
        return numberDecimals;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public String getCurrencySimbols() {
        return currencySimbols;
    }

    public String getTemplate() {
        return template;
    }

    @Override
    public String toString() {
        return "Characteristic{" +
                "characteristic='" + characteristic + '\'' +
                ", validFrom='" + validFrom + '\'' +
                ", description='" + description + '\'' +
                ", charGroup='" + charGroup + '\'' +
                ", charStatus='" + charStatus + '\'' +
                ", status='" + status + '\'' +
                ", singleValue=" + singleValue +
                ", multiplesValues=" + multiplesValues +
                ", intervalsAllowed=" + intervalsAllowed +
                ", negativeValsAllowed=" + negativeValsAllowed +
                ", restrictable=" + restrictable +
                ", entryRequired=" + entryRequired +
                ", dataType='" + dataType + '\'' +
                ", numberCharacters=" + numberCharacters +
                ", numberDecimals=" + numberDecimals +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", currencySimbols='" + currencySimbols + '\'' +
                ", template='" + template + '\'' +
                '}';
    }
}
