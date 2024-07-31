package com.user.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 이 부분은 Spring security PasswordEncoder를 사용하게 되면 삭제할 계획입니다.
 * 암호화는 Argon2를 사용할 계획입니다. (종류는 Argon2, Bcrypt, PBKDF2, Scrypt)
 * 안정성과 검증된 보안성을 가진 Bcryt도 많이 사용하지만,
 * 비교적 최신 비밀번호 알고리즘으로 알려진 보안 공격에 대해 높은 보안성을 제공하는 점에서
 * Argon2 를 선택했습니다.
 */

public class SHA256 {

    public static String encrypt(String str)  {

        String SHA;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());

            byte[] bytes = md.digest();
            StringBuilder builder = new StringBuilder();

            for (byte b : bytes) {
                builder.append(String.format("%02x", b));
            }
            SHA = builder.toString();
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException("암호화 에러", e);
        }
        return SHA;
    }
}