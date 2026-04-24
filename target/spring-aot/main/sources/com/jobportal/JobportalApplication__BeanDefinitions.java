package com.jobportal;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link JobportalApplication}.
 */
@Generated
public class JobportalApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'jobportalApplication'.
   */
  public static BeanDefinition getJobportalApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JobportalApplication.class);
    beanDefinition.setInstanceSupplier(JobportalApplication::new);
    return beanDefinition;
  }
}
