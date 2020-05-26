package com.example.demo.util;

import com.example.demo.constant.CrowdConstant;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CrowdUtil {

    public static String md5(String source) {

        //不是有效的就抛异常
        if (source == null || source.length() == 0) {
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        String algorithm = "md5";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] input = source.getBytes();
            // 5 执行加密
            byte[] output = messageDigest.digest(input);
            // 6.创建BigInteger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            // 7. 按照16进制将bigIntteger 的值转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix);
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
