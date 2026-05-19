
package com.simplecore.erp.client.abstractions;

import com.simplecore.erp.client.dependencies.IgnoreFromCount;
import java.lang.reflect.Field;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class FormState {

    private final int fieldsCount;
    private int fieldFilledCount;
    private boolean saved;
    private String message;

    public FormState(int fieldsCount) {
        this.fieldsCount = fieldsCount;
        this.fieldFilledCount = 0;
        this.saved = false;
        this.message = "";
    }

    // Método que las subclases deben implementar para contar los campos llenos
    private int countFilledFields(Object target) {
        Class<?> clazz = target.getClass();

        int count = 0;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(IgnoreFromCount.class)) {
                continue; // Saltar campos marcados como ignorables
            }
            field.setAccessible(true);
            try {
                Object value = field.get(target);
                if (value == null) {
                    continue;
                }
                if (value instanceof String str) {
                    if (!str.trim().isEmpty()) {
                        count++;
                    }
                } else {
                    // Para cualquier otro tipo, basta con que no sea null
                    count++;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public void updateFilledFields(Object target) {
        this.fieldFilledCount = countFilledFields(target);
    }

    public int getFieldsCount() {
        return fieldsCount;
    }

    public int getFieldFilledCount() {
        return fieldFilledCount;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCompletionPercent() {
        return (int) ((fieldFilledCount / (double) fieldsCount) * 100);
    }

    public boolean isFormInProgress() {
        return fieldFilledCount > 0 && !saved;
    }
}
