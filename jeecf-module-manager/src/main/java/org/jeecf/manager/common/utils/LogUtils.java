package org.jeecf.manager.common.utils;

import org.jeecf.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志打印工具类
 * 
 * @author GloryJian
 *
 */
public class LogUtils {

	public static void print(BusinessException e) {
		printError(e.getErrorMsg(), e.getClazz(), e.getIsPrint());
	}

	public static void print(Throwable e) {
		print(e, e.getClass(), null);
	}

	public static void print(Throwable e, Class<?> clazz, Boolean isPrint) {
		printError(e.getMessage(), clazz, isPrint);
	}

	public static void printInfo(String message, Class<?> clazz) {
		printInfo(message, clazz, null);
	}

	public static void printWarn(String message, Class<?> clazz) {
		printWarn(message, clazz, null);
	}

	public static void printError(String message, Class<?> clazz) {
		printError(message, clazz, null);
	}

	public static void printDebug(String message, Class<?> clazz) {
		printDebug(message, clazz, null);
	}

	public static void printInfo(String message, Class<?> clazz, Boolean isPrint) {
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.info(message);
	}

	public static void printWarn(String message, Class<?> clazz, Boolean isPrint) {
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.warn(message);
	}

	public static void printError(String message, Class<?> clazz, Boolean isPrint) {
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.error(message);
	}

	public static void printDebug(String message, Class<?> clazz, Boolean isPrint) {
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.debug(message);
	}
}
