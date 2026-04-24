package com.jobportal.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link CompanyServiceImpl}.
 */
@Generated
public class CompanyServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static CompanyServiceImpl apply(RegisteredBean registeredBean,
      CompanyServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("companyRepo").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
