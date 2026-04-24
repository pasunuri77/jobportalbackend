package com.jobportal.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ApplicationController}.
 */
@Generated
public class ApplicationController__BeanDefinitions {
  /**
   * Get the bean definition for 'applicationController'.
   */
  public static BeanDefinition getApplicationControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ApplicationController.class);
    InstanceSupplier<ApplicationController> instanceSupplier = InstanceSupplier.using(ApplicationController::new);
    instanceSupplier = instanceSupplier.andThen(ApplicationController__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
