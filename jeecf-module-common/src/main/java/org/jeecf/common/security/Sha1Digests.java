package org.jeecf.common.security;

import java.io.IOException;
import java.io.InputStream;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;

/**
 * 基于SHA-1 加密
 * 
 * @author GloryJ
 *
 */
public class Sha1Digests extends AbstractDigests{
	
	private static Sha1Digests sha1 = null;
	
	private static final String ALGORITHM = "SHA-1";

	private Sha1Digests() {
	}

	public synchronized static final Sha1Digests getInstance() {
		if (sha1 == null) {
			sha1 = new Sha1Digests();
		}
		return sha1;
	}
	
	@Override
	public byte[] encrpt(byte[] input, byte[] salt, int iterations) {
		return digest(input, salt, ALGORITHM, iterations);
	}
	
	
	public static byte[] encrpt1(byte[] input) {
		return getInstance().encrpt(input);
	}
	
	public static byte[] encrpt1(byte[] input, byte[] salt) {
		return getInstance().encrpt(input,salt);
	}
	
	public static byte[] encrpt1(byte[] input, byte[] salt, int iterations) {
		return getInstance().encrpt(input,salt,iterations);
	}

	@Override
	public byte[] encrpt(InputStream input) {
		try {
			return digest(input, ALGORITHM);
		} catch (IOException e) {
			throw new BusinessException(SysErrorEnum.IO_ERROR);
		}
	}
}
