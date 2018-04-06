package ru.bellintegrator.practice.accounts.service.impl;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.junit.Assert.*;

public class AccountServiceImplTest {

    @Test
    public void decodePassword() {
        String password = "123456";//hash
        byte[] decoded = Base64.getDecoder().decode(password.getBytes());
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(decoded, 0, decoded.length);
        String decodedPsw = new String(decoded);

        System.out.println(decodedPsw);
    }

    @Test
    public void encodePassword() {
        String password =  "123456";
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] encoded = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encodedPsw = Base64.getEncoder().encodeToString(encoded);

        System.out.println(encodedPsw);
    }
}