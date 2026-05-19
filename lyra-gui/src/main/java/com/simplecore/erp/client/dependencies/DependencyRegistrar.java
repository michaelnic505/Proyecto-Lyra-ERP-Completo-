
package com.simplecore.erp.client.dependencies;

/**
 *
 * @author user
 */
public interface DependencyRegistrar {
  void registerDependencies();   
  ContainerDependencies container();
}
