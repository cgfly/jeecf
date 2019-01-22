package org.jeecf.common.utils;

import org.jeecf.common.security.Md5Digests;
import org.jeecf.common.security.Sha1Digests;

/**
 * 基于MD5与SHA-1 加密工具类
 * 
 * @author GloryJ
 *
 */
public class DigestsEncryptUtils {
    /**
     * MD5 密钥
     */
    private static final Md5Digests MD5 = Md5Digests.getInstance();
    /**
     * SHA1 密钥
     */
    private static final Sha1Digests SHA1 = Sha1Digests.getInstance();

    /**
     * 默认 加密迭代次数
     */
    private static final int DEFAULT_ITERATIONS = 10;

    public static String hexWithMd5(byte[] input, byte[] salt) {
        return hexWithMd5(input, salt, DEFAULT_ITERATIONS);
    }

    /**
     * Md5 加密 hex编码返回
     */
    public static String hexWithMd5(byte[] input, byte[] salt, int iterations) {
        byte[] result = MD5.encrpt(input, salt, iterations);
        return EncodeUtils.encodeHex(result);
    }

    public static String hexWithSha1(byte[] input, byte[] salt) {
        return hexWithSha1(input, salt, DEFAULT_ITERATIONS);
    }

    /**
     * Sha1 加密 hex编码返回
     */
    public static String hexWithSha1(byte[] input, byte[] salt, int iterations) {
        byte[] result = SHA1.encrpt(input, salt, iterations);
        return EncodeUtils.encodeHex(result);
    }

    public static String base64WithMd5(byte[] input, byte[] salt) {
        return base64WithMd5(input, salt, DEFAULT_ITERATIONS);
    }

    /**
     * Md5 加密 base64编码返回
     */
    public static String base64WithMd5(byte[] input, byte[] salt, int iterations) {
        byte[] result = MD5.encrpt(input, salt, iterations);
        return EncodeUtils.encodeBase64(result);
    }

    public static String base64WithSha1(byte[] input, byte[] salt) {
        return base64WithSha1(input, salt, DEFAULT_ITERATIONS);
    }

    /**
     * Sha1 加密 base64编码返回
     */
    public static String base64WithSha1(byte[] input, byte[] salt, int iterations) {
        byte[] result = SHA1.encrpt(input, salt, iterations);
        return EncodeUtils.encodeBase64(result);
    }

    /**
     * 获取Md5摘要
     */
    public Md5Digests getMd5() {
        return MD5;
    }

    /**
     * 获取Sha1摘要
     */
    public Sha1Digests getSha1() {
        return SHA1;
    }

}
