package com.jobportal.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ApplicationServiceImpl}.
 */
@Generated
public class ApplicationServiceImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'applicationServiceImpl'.
   */
  public static BeanDefinition getApplicationServiceImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ApplicationServiceImpl.class);
    InstanceSupplier<ApplicationServiceImpl> instanceSupplier = InstanceSupplier.using(ApplicationServiceImpl::new);
    instanceSupplier = instanceSupplier.andThen(ApplicationServiceImpl__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
