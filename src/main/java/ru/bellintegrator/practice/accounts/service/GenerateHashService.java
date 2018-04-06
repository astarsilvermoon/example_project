package ru.bellintegrator.practice.accounts.service;


public interface GenerateHashService {
     String encode(String password);
     String generateCode();
     String getHashFromString(String str);
     String decode(String code);

     String getUuid();
     void setUuid(String uuid);
}
