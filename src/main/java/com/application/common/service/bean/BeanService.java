package com.application.common.service.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * The type Bean service.
 */
@Service
public class BeanService implements ApplicationContextAware {

  /**
   * The constant context.
   */
  private static ApplicationContext context;

  /**
   * Gets bean.
   *
   * @param <T>       the type parameter
   * @param beanClass the bean class
   * @return the bean
   */
  public static <T> T getBean(Class<T> beanClass) {
    return context.getBean(beanClass);
  }

  /**
   * Sets application context.
   *
   * @param applicationContext the application context
   * @throws BeansException the beans exception
   */
  @Override
  public void setApplicationContext(
      final ApplicationContext applicationContext)
      throws BeansException {
    context = applicationContext;
  }

}
