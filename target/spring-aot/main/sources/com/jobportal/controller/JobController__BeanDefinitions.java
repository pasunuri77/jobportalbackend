package com.jobportal.controller;

import com.jobportal.service.JobServiceImpl;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link JobController}.
 */
@Generated
public class JobController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'jobController'.
   */
  private static BeanInstanceSupplier<JobController> getJobControllerInstanceSupplier() {
    return BeanInstanceSupplier.<JobController>forConstructor(JobServiceImpl.class)
            .withGenerator((registeredBean, args) -> new JobController(args.get(0)));
  }

  /**
   * Get the bean definition for 'jobController'.
   */
  public static BeanDefinition getJobControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JobController.class);
    InstanceSupplier<JobController> instanceSupplier = getJobControllerInstanceSupplier();
    instanceSupplier = instanceSupplier.andThen(JobController__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
