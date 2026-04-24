package com.jobportal.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link ServiceImplementation}.
 */
@Generated
public class ServiceImplementation__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static ServiceImplementation apply(RegisteredBean registeredBean,
      ServiceImplementation instance) {
    AutowiredFieldValueResolver.forRequiredField("userRepo").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
