package com.mfq.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTraceLogger {

	private Logger logger;
	
	private static InheritableThreadLocal<String> traceIdTls = new InheritableThreadLocal<String>();

	public static UserTraceLogger getLogger(Class<?> clz) {
		return new UserTraceLogger(clz.getName());
	}

	public static UserTraceLogger getLogger(String name) {
		return new UserTraceLogger(name);
	}

	public static void setTraceId(String traceId) {
		traceIdTls.set(traceId);
	}

	private UserTraceLogger(String name) {
		logger = LoggerFactory.getLogger("UserTrace." + name);
	}

	public void debug(Object message) {
		logger.debug(getPrefix() + message);
	}

	private String getPrefix() {
		return traceIdTls.get() + " ";
	}

	public void debug(Object message, Throwable t) {
		logger.debug(getPrefix() + message, t);
	}

	public void error(Object message) {
		logger.error(getPrefix() + message);
	}

	public void error(Object message, Throwable t) {
		logger.error(getPrefix() + message, t);
	}

	public void info(Object message) {
		logger.info(getPrefix() + message);
	}
	
	public void info(Object message, Throwable t) {
		logger.info(getPrefix() + message, t);
	}
	
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
	
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}
	
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}
	
	public void trace(Object message) {
		logger.trace(getPrefix() + message);
	}

	public void trace(Object message, Throwable t) {
		logger.trace(getPrefix() + message, t);
	}
	public void warn(Object message) {
		logger.warn(getPrefix() + message);
	}
	
	public void warn(Object message, Throwable t) {
		logger.warn(getPrefix() + message, t);
	}
	
	public static String getTraceId() {
		return traceIdTls.get();
	}
}
