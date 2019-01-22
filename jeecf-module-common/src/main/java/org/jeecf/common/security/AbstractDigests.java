package org.jeecf.common.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.lang3.Validate;
import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;

/**
 * 加密摘要抽象类
 * 
 * @author GloryJ
 */
public abstract class AbstractDigests {

    protected static SecureRandom RANDOM = new SecureRandom();

    /**
     * 获取盐
     * 
     * @param numBytes
     * @return
     */
    public static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

        byte[] bytes = new byte[numBytes];
        RANDOM.nextBytes(bytes);
        return bytes;
    }

    public byte[] encrpt(byte[] input) {
        return encrpt(input, null);
    }

    public byte[] encrpt(byte[] input, byte[] salt) {
        return encrpt(input, salt, 1);
    }

    /**
     * 对字符串加密
     * 
     * @param input
     * @param salt
     * @param iterations
     * @return
     */
    public abstract byte[] encrpt(byte[] input, byte[] salt, int iterations);

    /**
     * 对文件加密
     * 
     * @param input
     * @return
     */
    public abstract byte[] encrpt(InputStream input);

    /**
     * 字符串加密算法
     * 
     * @param input
     * @param salt
     * @param algorithm
     * @param iterations
     * @return
     */
    protected static byte[] digest(byte[] input, byte[] salt, String algorithm, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw new BusinessException(SysErrorEnum.SECURITY_ERROR);
        }
    }

    /**
     * 文件加密算法
     * 
     * @param input
     * @param algorithm
     * @return
     * @throws IOException
     */
    protected static byte[] digest(InputStream input, String algorithm) throws IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            int bufferLength = 8 * 1024;
            byte[] buffer = new byte[bufferLength];
            int read = input.read(buffer, 0, bufferLength);

            while (read > -1) {
                messageDigest.update(buffer, 0, read);
                read = input.read(buffer, 0, bufferLength);
            }

            return messageDigest.digest();
        } catch (GeneralSecurityException e) {
            throw new BusinessException(SysErrorEnum.SECURITY_ERROR);
        }
    }

}
