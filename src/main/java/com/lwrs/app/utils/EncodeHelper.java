package com.lwrs.app.utils;

import sun.misc.BASE64Encoder;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeHelper {

    /**利用MD5进行加密
 　　* @param str  待加密的字符串
 　　* @return  加密后的字符串
 　　* @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
 　　 * @throws UnsupportedEncodingException
 　　*/
    public static String md5Encode(String str) throws NoSuchAlgorithmException{
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes(Charset.forName("UTF-8"))));
        return newstr;
    }
}
