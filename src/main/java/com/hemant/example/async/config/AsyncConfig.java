package com.hemant.example.async.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

  private final AsyncExceptionHandler asyncExceptionHandler;

  public AsyncConfig(AsyncExceptionHandler asyncExceptionHandler) {
    this.asyncExceptionHandler = asyncExceptionHandler;
  }


  //Application level overridden method
  @Override
  @Bean(name = "asyncExecutor")
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setMaxPoolSize(50);
    taskExecutor.setCorePoolSize(5);
    taskExecutor.setThreadNamePrefix("thread-Async-");
    taskExecutor.setQueueCapacity(500);
    taskExecutor.initialize();
    return taskExecutor;
  }


  @Bean
  public RestTemplate getBean(){
    return new RestTemplate();
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return asyncExceptionHandler;
  }
}
