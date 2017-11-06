package com.xyw.datacollectsystem.zencryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by 31429 on 2017/10/12.
 */

public class AES {
    private static byte IV[] = { 18, 28, -28, -54, -11, -91, -71, 65 ,8, 38, -48, -64, -21, -71, -81, 85};

    // 加密
    public static String Encrypt(String sSrc) throws Exception {

        byte RC2Key[] = Rsa.getRc2Key();
        SecretKeySpec skeySpec = new SecretKeySpec(RC2Key, "AES");
        IvParameterSpec iv = new IvParameterSpec(IV);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");// "算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec,iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));

        return org.kobjects.base64.Base64.encode(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String Decrypt(String sSrc) throws Exception  {
        byte RC2Key[] = Rsa.getRc2Key();
        SecretKeySpec skeySpec = new SecretKeySpec(RC2Key, "AES");
        IvParameterSpec iv = new IvParameterSpec(IV);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encrypted1 = org.kobjects.base64.Base64.decode(sSrc);// 先用base64解密
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original, "UTF-8");
        return originalString;

    }
}
