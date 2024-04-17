package com.kmini.encryptdemo;

import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static java.nio.charset.StandardCharsets.UTF_8;

class AES256Test {

    // aes256은 비밀키의 길이가 32bytes 이여야 한다.
    private String privateKey = "01234567890123456789012345678901";
    // IV(Initial Vector)는 암호화의 시작 부분에 사용되는 값으로 16 bytes 여야 한다.
    private String AES_IV = "initialValue123456789";

    // aes256 테스트
    @Test
    void aes256Encode() throws Exception {
        // 암호화 할 문자열
        String plainText = "this is plainText";

        SecretKey secretKey = new SecretKeySpec(privateKey.getBytes(UTF_8), "AES");
        IvParameterSpec IV = new IvParameterSpec(AES_IV.substring(0,16).getBytes(UTF_8));

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");

        c.init(Cipher.ENCRYPT_MODE, secretKey, IV);

        byte[] encryptedByte =  c.doFinal(plainText.getBytes(UTF_8));

        System.out.println(new String(encryptedByte, UTF_8));
    }

    @Test
    void aes256Decode() throws Exception {

        // 암호화 할 문자열
        String plainText = "this is plainText222";

        SecretKey encryptSecretKey = new SecretKeySpec(privateKey.getBytes(UTF_8), "AES");
        IvParameterSpec encryptIV = new IvParameterSpec(AES_IV.substring(0,16).getBytes(UTF_8));

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");

        c.init(Cipher.ENCRYPT_MODE, encryptSecretKey, encryptIV);

        byte[] encryptedByte =  c.doFinal(plainText.getBytes(UTF_8));

        // =================================================================

        SecretKey secretKey = new SecretKeySpec(privateKey.getBytes(UTF_8), "AES");
        IvParameterSpec IV = new IvParameterSpec(AES_IV.substring(0,16).getBytes(UTF_8));

        Cipher c2 = Cipher.getInstance("AES/CBC/PKCS5Padding");

        c2.init(Cipher.DECRYPT_MODE, secretKey, IV);

        byte[] decryptedBytes = c2.doFinal(encryptedByte);

        System.out.println(new String(decryptedBytes, UTF_8));
    }

}
