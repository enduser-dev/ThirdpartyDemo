package com.quec.thirdparty.demo.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;

public class Utils {
    /**
     * 生成安全随机字符串
     * @param len 要生成的字符串长度
     * @return 安全随机字符串
     */
    public static String generateRandomString(final Integer len){
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] bytes = new byte[len];
            random.nextBytes(bytes);
            return Base58.encode(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String SHA256(String str) throws Exception {
        MessageDigest messageDigest;
        String encdeStr = "";
        messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
        encdeStr = Hex.encodeHexString(hash);
        return encdeStr;
    }

    /**
     * 检查签名
     * 签名计算公式：sign=SHA256(AppKey+AppSecret+AuthCode)
     * @param appKey AppKey
     * @param appSecret AppSecret
     * @param authCode AuthCode
     * @param sign 要验证的签名
     * @return false-签名验证失败  true-签名验证通过
     */
    public static Boolean checkSign(String appKey,String appSecret, String authCode,String sign){
        try{
            String generateSign = SHA256(appKey + appSecret + authCode);
            sign = sign.toLowerCase();
            return sign.equals(generateSign);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 脱敏昵称
     * @param nickname 昵称
     * @return 脱敏后的昵称
     */
    public static String desensitizedNickname(String nickname){
        if(nickname == null){
            return "";
        }
        if(nickname.length()<3){
            return nickname;
        }
        StringBuffer newNickname = new StringBuffer();
        newNickname.append(nickname.charAt(0));
        for(int i=0;i<nickname.length()-2;i++){
            newNickname.append("*");
        }
        newNickname.append(nickname.charAt(nickname.length() - 1));
        return newNickname.toString();
    }
}
