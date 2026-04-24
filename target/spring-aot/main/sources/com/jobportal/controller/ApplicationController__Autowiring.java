package com.jobportal.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link ApplicationController}.
 */
@Generated
public class ApplicationController__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static ApplicationController apply(RegisteredBean registeredBean,
      ApplicationController instance) {
    AutowiredFieldValueResolver.forRequiredField("applicationServiceImpl").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
