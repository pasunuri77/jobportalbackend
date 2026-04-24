package com.jobportal.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link JobServiceImpl}.
 */
@Generated
public class JobServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static JobServiceImpl apply(RegisteredBean registeredBean, JobServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("jobsRepo").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
