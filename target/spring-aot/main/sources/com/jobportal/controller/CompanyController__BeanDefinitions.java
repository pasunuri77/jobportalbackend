package com.jobportal.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CompanyController}.
 */
@Generated
public class CompanyController__BeanDefinitions {
  /**
   * Get the bean definition for 'companyController'.
   */
  public static BeanDefinition getCompanyControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CompanyController.class);
    InstanceSupplier<CompanyController> instanceSupplier = InstanceSupplier.using(CompanyController::new);
    instanceSupplier = instanceSupplier.andThen(CompanyController__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
