package com.hemant.example.async.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Log4j2
public class AsyncExceptionHandler implements
    AsyncUncaughtExceptionHandler {

  @Override
  public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
    log.error("Method name:"+method.getName()+"()--"+Arrays.toString(objects)+" Exception mgs:"+throwable.getMessage());
  }
}
