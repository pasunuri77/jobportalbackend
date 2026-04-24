package com.jobportal.service;

import com.jobportal.controller.UserController;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CompanyServiceImpl}.
 */
@Generated
public class CompanyServiceImpl__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'companyServiceImpl'.
   */
  private static BeanInstanceSupplier<CompanyServiceImpl> getCompanyServiceImplInstanceSupplier() {
    return BeanInstanceSupplier.<CompanyServiceImpl>forConstructor(UserController.class)
            .withGenerator((registeredBean, args) -> new CompanyServiceImpl(args.get(0)));
  }

  /**
   * Get the bean definition for 'companyServiceImpl'.
   */
  public static BeanDefinition getCompanyServiceImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CompanyServiceImpl.class);
    InstanceSupplier<CompanyServiceImpl> instanceSupplier = getCompanyServiceImplInstanceSupplier();
    instanceSupplier = instanceSupplier.andThen(CompanyServiceImpl__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
