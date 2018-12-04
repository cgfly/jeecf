package org.jeecf.common.utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类
 * 
 * @author GloryJ
 * 
 */
public class IdGenUtils {

	private static SecureRandom random = new SecureRandom();

	private static char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u' ,'v','w','x','y','z'};

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String randomUUID(int max) {
		String uuid = uuid();
		Random r = new Random();
		for(int i = 0; i < max; i++) {
			uuid+=String.valueOf(chars[r.nextInt(26)]);
		}
		return uuid;
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base64编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase64(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return EncodeUtils.encodeBase64(randomBytes);
	}

	/**
	 * 基于Hex编码的SecureRandom随机生成bytes.
	 */
	public static String randomHex(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return EncodeUtils.encodeHex(randomBytes);
	}

}
