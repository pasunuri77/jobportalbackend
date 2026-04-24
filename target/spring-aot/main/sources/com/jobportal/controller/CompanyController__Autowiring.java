package com.jobportal.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link CompanyController}.
 */
@Generated
public class CompanyController__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static CompanyController apply(RegisteredBean registeredBean, CompanyController instance) {
    AutowiredFieldValueResolver.forRequiredField("compayRepo").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("companyService").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
