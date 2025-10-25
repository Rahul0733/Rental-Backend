package com.cts.auth_service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class JwtKeyGenerator {

    public static void main(String[] args) {
        String secretKey = Encoders.
        		BASE64.
        		encode(Keys.secretKeyFor(SignatureAlgorithm.HS256)
        		.getEncoded());
        System.out.println("Generated JWT Secret Key:");
        System.out.println(secretKey);
    }
}
