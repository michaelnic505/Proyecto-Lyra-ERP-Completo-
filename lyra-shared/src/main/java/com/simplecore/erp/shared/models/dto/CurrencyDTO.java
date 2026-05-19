
package com.simplecore.erp.shared.models.dto;

import java.io.Serializable;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

/**
 * Data Transfer Object (DTO) representing a currency and its associated information.
 * Implements {@link Serializable} for transmission across layers or over the network.
 */
public class CurrencyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ISO 4217 currency code (e.g., "USD", "EUR").
     */
    private final String currencyCode;

    /**
     * Localized or full name of the currency (e.g., "US Dollar", "Euro").
     */
    private final String currencyName;

    /**
     * Symbol representing the currency (e.g., "$", "€", "¥").
     */
    private final String symbol;

    /**
     * Descriptive or localized name for the currency symbol.
     */
    private final String symbolName;

    /**
     * Private constructor for builder usage.
     *
     * @param builder the builder instance containing the data
     */
    private CurrencyDTO(Builder builder) {
        this.currencyCode = builder.currencyCode;
        this.currencyName = builder.currencyName;
        this.symbol = builder.symbol;
        this.symbolName = builder.symbolName;
    }

    /**
     * Builder class for constructing {@link CurrencyDTO} instances.
     */
    public static class Builder {
        private String currencyCode;
        private String currencyName;
        private String symbol;
        private String symbolName;

        /**
         * Sets the currency code.
         *
         * @param currencyCode the ISO 4217 code
         * @return the current builder instance
         */
        public Builder withCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }

        /**
         * Sets the currency name.
         *
         * @param currencyName the name of the currency
         * @return the current builder instance
         */
        public Builder withCurrencyName(String currencyName) {
            this.currencyName = currencyName;
            return this;
        }

        /**
         * Sets the currency symbol.
         *
         * @param symbol the symbol of the currency
         * @return the current builder instance
         */
        public Builder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        /**
         * Sets the symbol name.
         *
         * @param symbolName the name or description of the symbol
         * @return the current builder instance
         */
        public Builder withSymbolName(String symbolName) {
            this.symbolName = symbolName;
            return this;
        }

        /**
         * Builds and returns a {@link CurrencyDTO} instance.
         *
         * @return a fully constructed {@code CurrencyDTO}
         */
        public CurrencyDTO build() {
            return new CurrencyDTO(this);
        }
    }

    // Getters

    /**
     * Returns the currency code.
     *
     * @return the ISO 4217 currency code
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Returns the currency name.
     *
     * @return the name of the currency
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * Returns the currency symbol.
     *
     * @return the symbol representing the currency
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the symbol name.
     *
     * @return the descriptive name of the symbol
     */
    public String getSymbolName() {
        return symbolName;
    }
}
