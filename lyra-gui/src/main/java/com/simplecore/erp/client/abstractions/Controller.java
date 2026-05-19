
package com.simplecore.erp.client.abstractions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author user
 */
@Retention(RetentionPolicy.RUNTIME) // Disponible en tiempo de ejecución
@Target(ElementType.FIELD)          // Solo en atributos
public @interface Controller {
    String name()default"";
    String type()default"";
}
