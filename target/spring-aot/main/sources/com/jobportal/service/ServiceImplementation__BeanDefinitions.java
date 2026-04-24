package com.jobportal.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ServiceImplementation}.
 */
@Generated
public class ServiceImplementation__BeanDefinitions {
  /**
   * Get the bean definition for 'serviceImplementation'.
   */
  public static BeanDefinition getServiceImplementationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ServiceImplementation.class);
    InstanceSupplier<ServiceImplementation> instanceSupplier = InstanceSupplier.using(ServiceImplementation::new);
    instanceSupplier = instanceSupplier.andThen(ServiceImplementation__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
