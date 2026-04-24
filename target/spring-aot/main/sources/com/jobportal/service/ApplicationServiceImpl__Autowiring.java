package com.jobportal.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link ApplicationServiceImpl}.
 */
@Generated
public class ApplicationServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static ApplicationServiceImpl apply(RegisteredBean registeredBean,
      ApplicationServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("applicationRepo").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("userRepo").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("jobRepo").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
