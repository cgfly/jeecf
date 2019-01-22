package org.jeecf.common.security;

import java.io.IOException;
import java.io.InputStream;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;

/**
 * 基于MD5 加密
 * 
 * @author GloryJ
 *
 */
public class Md5Digests extends AbstractDigests {

    private static Md5Digests MD5 = null;

    private static final String ALGORITHM = "MD5";

    private Md5Digests() {
    }

    public synchronized static final Md5Digests getInstance() {
        if (MD5 == null) {
            MD5 = new Md5Digests();
        }
        return MD5;
    }

    @Override
    public byte[] encrpt(byte[] input, byte[] salt, int iterations) {
        return digest(input, salt, ALGORITHM, iterations);
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
