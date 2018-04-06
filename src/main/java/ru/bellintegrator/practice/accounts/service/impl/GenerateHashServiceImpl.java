package ru.bellintegrator.practice.accounts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.accounts.service.GenerateHashService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class GenerateHashServiceImpl implements GenerateHashService {
    private String uuid;
    private final Logger log = LoggerFactory.getLogger(GenerateHashServiceImpl.class);
    @Override
    public String decode(String code){

        String encoded = getHashFromString(code);
        byte[] decodedPsw = Base64.getDecoder().decode(encoded);
        return new String(decodedPsw);
    }

    @Override
    public String encode(String password){
        String encoded = getHashFromString(password);
        String encodedPsw = Base64.getEncoder().encodeToString(encoded.getBytes());
        return encodedPsw;
    }
    @Override
    public String generateCode(){
        setUuid(UUID.randomUUID().toString().replace("-",""));
        log.info(uuid);
        byte[] encoded = encode(uuid).getBytes();
        String code = new String(encoded);
        return code;
    }
    @Override
    public String getHashFromString(String str){
        MessageDigest digest = null;
        byte[] encoded=null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            encoded = digest.digest(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new String(encoded);
    }
    @Override
    public String getUuid() {
        return uuid;
    }
    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
