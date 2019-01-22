/**
 * Copyright (c) 2005-2012 springside.org.cn
 */
package org.jeecf.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;

/**
 * 用于 Hex,Base64,Url 编解码
 * 
 * @author GloryJ
 *
 */
public class EncodeUtils {

    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    /**
     * Hex编码.
     */
    public static String encodeHex(byte[] input) {
        return new String(Hex.encodeHex(input));
    }

    /**
     * Hex解码.
     * 
     * @throws DecoderException
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new BusinessException(SysErrorEnum.ENCODE_ERROR);
        }
    }

    /**
     * Base64编码.
     */
    public static String encodeBase64(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }

    /**
     * Base64编码.
     * 
     * @throws UnsupportedEncodingException
     */
    public static String encodeBase64(String input) {
        return encodeBase64(input, DEFAULT_URL_ENCODING);
    }

    /**
     * Base64编码.
     * 
     * @throws UnsupportedEncodingException
     */
    public static String encodeBase64(String input, String encode) {
        try {
            return new String(Base64.encodeBase64(input.getBytes(encode)));
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(SysErrorEnum.ENCODE_ERROR);
        }
    }

    /**
     * Base64解码.
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input.getBytes());
    }

    /**
     * Base64解码.
     * 
     * @throws UnsupportedEncodingException
     */
    public static String decodeBase64String(String input) {
        return decodeBase64String(input, DEFAULT_URL_ENCODING);
    }

    /**
     * Base64解码.
     * 
     * @throws UnsupportedEncodingException
     */
    public static String decodeBase64String(String input, String decode) {
        try {
            return new String(Base64.decodeBase64(input.getBytes()), decode);
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(SysErrorEnum.ENCODE_ERROR);
        }
    }

    /**
     * URL 编码, Encode默认为UTF-8.
     * 
     * @throws UnsupportedEncodingException
     */
    public static String urlEncode(String part) throws UnsupportedEncodingException {
        return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
    }

    /**
     * URL 编码
     * 
     * @throws UnsupportedEncodingException
     */
    public static String urlEncode(String part, String encode) {
        try {
            return URLEncoder.encode(part, encode);
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(SysErrorEnum.ENCODE_ERROR);
        }
    }

    /**
     * URL 解码, Encode默认为UTF-8.
     * 
     * @throws UnsupportedEncodingException
     */
    public static String urlDecode(String part) {
        try {
            return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(SysErrorEnum.ENCODE_ERROR);
        }
    }

    /**
     * URL 解码
     * 
     * @throws UnsupportedEncodingException
     */
    public static String urlDecode(String part, String decode) {
        try {
            return URLDecoder.decode(part, decode);
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(SysErrorEnum.ENCODE_ERROR);
        }
    }

    /**
     * Html 转码.
     */
    /*
     * public static String escapeHtml(String html) { return
     * StringEscapeUtils.escapeHtml4(html); }
     * 
     *//**
        * Html 解码.
        */
    /*
     * public static String unescapeHtml(String htmlEscaped) { return
     * StringEscapeUtils.unescapeHtml4(htmlEscaped); }
     * 
     *//**
        * Xml 转码.
        */
    /*
     * public static String escapeXml(String xml) { return
     * StringEscapeUtils.escapeXml10(xml); }
     * 
     *//**
        * Xml 解码.
        *//*
           * public static String unescapeXml(String xmlEscaped) { return
           * StringEscapeUtils.unescapeXml(xmlEscaped); }
           */
}
