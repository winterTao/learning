package com.tao;


import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author DongTao
 * @since 2019-10-15
 */
public class EncryptUtil {

    private static final String AES = "AES";
    private static final String DES = "DES";

    public static void main(String[] args) {

        String aesKey = "0123456789abcdef";
        String desKey = "01234567";

        String password = "nice aes!!!good des!!!hello base64!!!";

        String aesEncrypt = aesEncrypt(password, aesKey);
        System.out.println(aesEncrypt);
        System.out.println(aesDecrypt(aesEncrypt, aesKey));

        String desEncrypt = desEncrypt(password, desKey);
        System.out.println(desEncrypt);
        System.out.println(desDecrypt(desEncrypt, desKey));

        System.out.println("----------------------");

        String base64Encode = base64Encode(password.getBytes(StandardCharsets.UTF_8));
        System.out.println(base64Encode);
        System.out.println(new String(base64Decode(base64Encode), StandardCharsets.UTF_8));

        System.out.println("----------------------");
        System.out.println(messageDigest(password, "MD5"));
        System.out.println(messageDigest(password, "SHA"));
        System.out.println(messageDigest(password, "SHA1"));
        System.out.println(messageDigest(password, "SHA-256"));
        System.out.println(messageDigest(password, "SHA-512"));

    }

    /**
     * 消息摘要
     *
     * @param content 内容
     * @param algorithm 算法 md5,sha-a
     */
    public static String messageDigest(String content, String algorithm) {

        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(content.getBytes(StandardCharsets.UTF_8));
            result = byte2HexStr(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * AES加密
     */
    public static String aesEncrypt(String content, String key) {
        return encrypt(content, key, AES);
    }

    /**
     * AES解密
     */
    public static String aesDecrypt(String content, String key) {
        return decrypt(content, key, AES);
    }

    /**
     * DES加密
     */
    public static String desEncrypt(String content, String key) {
        return encrypt(content, key, DES);
    }

    /**
     * DES解密
     */
    public static String desDecrypt(String content, String key) {
        return decrypt(content, key, DES);
    }

    /**
     * 加密
     */
    public static String encrypt(String content, String key, String algorithm) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                    key.getBytes(StandardCharsets.UTF_8), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return byte2HexStr(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     */
    public static String decrypt(String content, String key, String algorithm) {
        try {

            SecretKeySpec secretKeySpec = new SecretKeySpec(
                    key.getBytes(StandardCharsets.UTF_8), algorithm);

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(hexStr2Byte(content));
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * BASE64 加密
     */
    public static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * BASE64 解密
     */
    public static byte[] base64Decode(String base64Code) {
        return Base64.getDecoder().decode(base64Code);
    }

    /**
     * 二进制转十六进制
     */
    private static String byte2HexStr(byte[] buf) {
        if (buf == null || buf.length < 1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 十六进制转二进制
     */
    private static byte[] hexStr2Byte(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }
        ByteBuffer bf = ByteBuffer.allocate(hex.length() / 2);
        for (int i = 0; i < hex.length(); i++) {
            String hexStr = hex.charAt(i) + "";
            i++;
            hexStr += hex.charAt(i);
            byte b = (byte) Integer.parseInt(hexStr, 16);
            bf.put(b);
        }
        return bf.array();
    }

}
