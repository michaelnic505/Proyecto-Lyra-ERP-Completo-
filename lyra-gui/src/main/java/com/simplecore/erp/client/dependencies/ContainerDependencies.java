package com.simplecore.erp.client.dependencies;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class ContainerDependencies {

    private final Map<String, Object> namedDependencies = new HashMap<>();
    private final Map<Class<?>, Object> typeDependencies = new HashMap<>();
    private final List<DependencyRegistrar> registrars = new ArrayList<>();

    public <T> void register(Class<T> clazz, T instance) {
        typeDependencies.put(clazz, instance);
    }

    public <T> void register(String name, Class<T> clazz, T instance) {
        namedDependencies.put(name, instance);
    }

    public void addRegistrar(DependencyRegistrar registrar) {
        registrars.add(registrar);
    }

    public void registerAll() {
        for (DependencyRegistrar reg : registrars) {
            reg.registerDependencies();
        }
    }

    public <T> T getInstanceOf(Class<T> clazz) {
        return clazz.cast(typeDependencies.get(clazz));
    }

    public <T> T getNamedInstance(String name, Class<T> clazz) {
        return clazz.cast(namedDependencies.get(name));
    }

    public void injectDependencies(Object target) {
        Class<?> clazz = target.getClass(); // Obtener la clase del objeto

        for (Field field : clazz.getDeclaredFields()) { // Recorrer todos los atributos de la clase
            if (field.isAnnotationPresent(InjectDependency.class)) { // Buscar los que tienen @InjectDependency
                InjectDependency annotation = field.getAnnotation(InjectDependency.class);
                String name = annotation.name(); // Obtener el nombre si está presente

                Class<?> fieldType = field.getType(); // Obtener el tipo del atributo
                Object instance;

                // Si se especificó un nombre, buscar la dependencia en namedDependencies
                if (!name.isEmpty()) {
                    instance = namedDependencies.get(name);
                } else {
                    // Si no se especificó un nombre, buscar por tipo en typeDependencies
                    instance = typeDependencies.get(fieldType);
                }

                if (instance != null) {
                    try {
                        field.setAccessible(true); // Hacer el campo accesible si es privado
                        field.set(target, instance); // Inyectar la instancia
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("It could not be injected:"+ field.getName(), e);
                    }
                } else {
                    System.err.println("No instance found to inject into: " + field.getName());
                }
            }
        }
    }
}
