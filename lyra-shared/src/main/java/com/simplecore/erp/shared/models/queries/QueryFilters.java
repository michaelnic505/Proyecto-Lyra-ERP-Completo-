
package com.simplecore.erp.shared.models.queries;

import java.io.Serializable;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class QueryFilters implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String fieldName; // Nombre del campo a filtrar
    private final String operation; // Tipo de operación: "RANGE", "IN", "EQUALS"
    private final Object fromValue; // Valor inicial (si aplica)
    private final Object toValue;   // Valor final (si aplica)
    private final List<Object> values; // Lista de valores específicos (si aplica)

    public QueryFilters(String fieldName, String operation, Object fromValue, Object toValue, List<Object> values) {
        this.fieldName = fieldName;
        this.operation = operation;
        this.fromValue = fromValue;
        this.toValue = toValue;
        this.values = values;
    }
    public String getFieldName() {
        return fieldName;
    }
    public String getOperation() {
        return operation;
    }
    public Object getFromValue() {
        return fromValue;
    }
    public Object getToValue() {
        return toValue;
    }
    public List<Object> getValues() {
        return values;
    }
}