package org.jeecf.common.utils;

import org.jeecf.common.security.Sha1Digests;
/**
 * 加密工具
 * @author jianyiming
 *
 */
public class SysEntrypt {
	
	public static final int HASH_INTERATIONS = 1024;
	/**
	 * 密盐位数
	 */
	public static final int SALT_SIZE = 8;
	
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Sha1Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Sha1Digests.encrpt1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return EncodeUtils.encodeHex(salt) + EncodeUtils.encodeHex(hashPassword);
	}

	/**
	 * 验证密码
	 * 
	 * @param plainPassword
	 *            明文密码
	 * @param password
	 *            密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = EncodeUtils.decodeHex(password.substring(0, 16));
		byte[] hashPassword = Sha1Digests.encrpt1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(EncodeUtils.encodeHex(salt) + EncodeUtils.encodeHex(hashPassword));
	}
}
