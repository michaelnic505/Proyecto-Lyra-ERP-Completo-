

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
import java.io.Serializable;

/**
 * Data Transfer Object (DTO) representing a country and its associated currency information.
 * Implements {@link Serializable} for safe object serialization.
 */
public class CountryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the country.
     */
    private int ID;

    /**
     * ISO 3166-1 alpha-2 country code.
     */
    private String alpha2;

    /**
     * ISO 3166-1 alpha-3 country code.
     */
    private String alpha3;

    /**
     * Localized name of the country.
     */
    private String countryName;

    /**
     * ISO 4217 currency code associated with the country.
     */
    private String currencyCode;

    /**
     * Localized name of the currency.
     */
    private String currencyName;

    /**
     * Symbol representing the currency (e.g., $, €, ¥).
     */
    private String symbol;

    /**
     * Localized or descriptive name of the symbol.
     */
    private String symbolName;

    /**
     * Private constructor for the builder pattern.
     *
     * @param builder the builder instance containing the values
     */
    private CountryDTO(Builder builder) {
        this.ID = builder.ID;
        this.alpha2 = builder.alpha2;
        this.alpha3 = builder.alpha3;
        this.countryName = builder.countryName;
        this.currencyCode = builder.currencyCode;
        this.currencyName = builder.currencyName;
        this.symbol = builder.symbol;
        this.symbolName = builder.symbolName;
    }

    /**
     * Builder class for constructing {@link CountryDTO} instances.
     */
    public static class Builder {

        private int ID;
        private String alpha2;
        private String alpha3;
        private String countryName;
        private String currencyCode;
        private String currencyName;
        private String symbol;
        private String symbolName;

        /**
         * Sets the country ID.
         *
         * @param id the country ID
         * @return the current builder instance
         */
        public Builder withID(int id) {
            this.ID = id;
            return this;
        }

        /**
         * Sets the ISO alpha-2 country code.
         *
         * @param alpha2 the alpha-2 code
         * @return the current builder instance
         */
        public Builder withAlpha2(String alpha2) {
            this.alpha2 = alpha2;
            return this;
        }

        /**
         * Sets the ISO alpha-3 country code.
         *
         * @param alpha3 the alpha-3 code
         * @return the current builder instance
         */
        public Builder withAlpha3(String alpha3) {
            this.alpha3 = alpha3;
            return this;
        }

        /**
         * Sets the country name.
         *
         * @param countryName the name of the country
         * @return the current builder instance
         */
        public Builder withCountryName(String countryName) {
            this.countryName = countryName;
            return this;
        }

        /**
         * Sets the currency code.
         *
         * @param currencyCode the ISO 4217 currency code
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
         * @param symbol the currency symbol
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
         * Builds and returns a new {@link CountryDTO} instance.
         *
         * @return a fully constructed CountryDTO object
         */
        public CountryDTO build() {
            return new CountryDTO(this);
        }
    }

    // Getters

    /**
     * Returns the country ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns the alpha-2 country code.
     */
    public String getAlpha2() {
        return alpha2;
    }

    /**
     * Returns the alpha-3 country code.
     */
    public String getAlpha3() {
        return alpha3;
    }

    /**
     * Returns the country name.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Returns the currency code.
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Returns the currency name.
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * Returns the currency symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the symbol name.
     */
    public String getSymbolName() {
        return symbolName;
    }
}
