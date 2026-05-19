

package com.simplecore.erp.client.dependencies;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
@Retention(RetentionPolicy.RUNTIME) // Disponible en tiempo de ejecución
@Target(ElementType.FIELD)          // Solo en atributos
public @interface InjectDependency {
    String name()default"";
}
