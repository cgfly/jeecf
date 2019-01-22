package org.jeecf.common.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 密钥加密工具类
 * 
 * @author jianyiming
 *
 */
public class DesEncryptUtils {

    private final static String DES = "DES";

    private final static String ENCODE = "UTF-8";

    /**
     * 加密 base64编码 UTF-8
     * 
     * @param data 加密数据
     * @param key  密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String encryptWithBase64(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(ENCODE), key.getBytes(ENCODE));
        return EncodeUtils.encodeBase64(bt);
    }

    /**
     * 解密 base64解码 UTF-8
     * 
     * @param data 解密数据
     * @param key  密钥
     * @return 解密结果
     * @throws Exception
     */
    public static String decryptWithBase64(String data, String key) throws Exception {
        byte[] dataBuf = EncodeUtils.decodeBase64(data);
        byte[] result = decrypt(dataBuf, key.getBytes(ENCODE));
        return new String(result, ENCODE);
    }

    /**
     * 加密
     * 
     * @param data 原数据
     * @param key  密钥
     * @return 加密后数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * 解密
     * 
     * @param data 原数据
     * @param key  密钥
     * @return 解密后数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

}
