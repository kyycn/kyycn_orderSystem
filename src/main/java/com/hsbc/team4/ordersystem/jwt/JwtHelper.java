package com.hsbc.team4.ordersystem.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * jwt helper
 * @author Kevin
 */
public class JwtHelper {
    /**
     * 解析jwt
     * @param jsonWebToken token
     * @param base64Security security
     * @return Claims
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 创建jwt
     * @param name username
     * @param role role
     * @param audience audience
     * @param issuer issuer
     * @param ttlmillis time
     * @param base64Security security
     * @return String jwt
     */
    private static String createJWT(String name, String role,
                                    String audience, String issuer, long ttlmillis, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //create secret key
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //add jwt params
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("role", role)
                .claim("unique_name", name)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //create token expiration time
        if (ttlmillis >= 0) {
            long expMillis = nowMillis + ttlmillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //create JWT
        return builder.compact();
    }

}
