package com.practice.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.practice.exception.AsyncUncaughtHandlerExc;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer{
	
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.initialize();
		executor.setCorePoolSize(2);
		return executor;
	}

	public AsyncUncaughtExceptionHandler getExc() {
		return new AsyncUncaughtHandlerExc();
	}
}
