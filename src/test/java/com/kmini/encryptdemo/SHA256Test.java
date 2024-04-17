package com.kmini.encryptdemo;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SHA256Test {

    @Test
    void sha256Encrypt() throws Exception {
        String plainText = "123456";

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(plainText.getBytes(UTF_8));
        byte[] encryptedBytes = md.digest();

        System.out.println(bytesToHex(encryptedBytes));
//        byte a = 0;
//        String demo = String.format("%02X", a);
//        System.out.println(demo);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            // 2자릿수채우면서 16진수로 출력
            String format = String.format("%02X", b);
//            System.out.println(format);
            builder.append(format);
        }
        return builder.toString();
    }
}
