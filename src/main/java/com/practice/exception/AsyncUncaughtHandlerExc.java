package com.practice.exception;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class AsyncUncaughtHandlerExc implements AsyncUncaughtExceptionHandler {
	
	private static Logger LOGGER = LoggerFactory.getLogger(AsyncUncaughtHandlerExc.class);

	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) {
		LOGGER.warn("Unhandle method while executing a method : "+ method.getName(), ex);
	}

}
