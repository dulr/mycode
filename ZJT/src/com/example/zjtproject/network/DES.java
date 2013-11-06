package com.example.zjtproject.network;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import android.inputmethodservice.Keyboard.Key;

public class DES {
    private static byte[] iv = { 18, 52, 86, 120, (byte) 144, (byte) 171,
            (byte) 205, (byte) 239 };
    public static String KEY = "sSfnJiYo";

    private final static String secretKey = "liuyunqiang@lx100$#365#$";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    // 加密方法
    public static String encryptDES(String encryptString, String encryptKey)
            throws Exception {
        // IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());

        return Base64.encode(encryptedData);
    }

    // 解密方法
    public static String decryptDES(String decryptString, String decryptKey)
            throws Exception {
        byte[] byteMi = new Base64().decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        // IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
//        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
        
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);

        return new String(decryptedData);
    }

    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {

      // DES算法要求有一个可信任的随机数源

      SecureRandom sr = new SecureRandom();

      // 从原始密匙数据创建一个DESKeySpec对象

     DESKeySpec dks = new DESKeySpec(key);

      // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成

      // 一个SecretKey对象

      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( "DES");

      SecretKey securekey = keyFactory.generateSecret(dks);

      // Cipher对象实际完成解密操作

      Cipher cipher = Cipher.getInstance( "DES");

      // 用密匙初始化Cipher对象

      cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

      // 现在，获取数据并解密

      // 正式执行解密操作

      return cipher.doFinal(src);
 }


    
    public static String encode(String plainText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = (Key) keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, (java.security.Key) deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return new String(Base64.encode(encryptData));
    }

    /**
     * byte[]转换成字符串
     * 
     * @param b
     * @return
     */
    public static String byte2HexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        int length = b.length;
        for (int i = 0; i < b.length; i++) {
            String stmp = Integer.toHexString(b[i] & 0xff);
            if (stmp.length() == 1)
                sb.append("0" + stmp);
            else
                sb.append(stmp);
        }
        return sb.toString();
    }

    /**
     * 16进制转换成byte[]
     * 
     * @param hexString
     * @return
     */
    public static byte[] String2Byte(String hexString) {
        if (hexString.length() % 2 == 1)
            return null;
        byte[] ret = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            ret[i / 2] = Integer.decode("0x" + hexString.substring(i, i + 2))
                    .byteValue();
        }
        return ret;
    }
}