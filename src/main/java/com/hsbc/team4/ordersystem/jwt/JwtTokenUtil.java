package com.hsbc.team4.ordersystem.jwt;

import com.hsbc.team4.ordersystem.users.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chenRenXun
 * @date 2018/4/27 0027
 *  create token
 */
@Component
public class JwtTokenUtil implements Serializable {
    /**
     *
     { "iss": "Online JWT Builder",  issuer；
     "iat": 1416797419,   iss create time；
     "exp": 1448333419,    expiration time；
     "aud": "www.example.com",   accept people；
     "sub": "jrocket@example.com", having people；
     "GivenName": "Johnny",
     "Surname": "Rocket",
     "Email": "jrocket@example.com",
     "Role": [ "Manager", "Project Administrator" ]
     }
     */

    private static final long serialVersionUID = -3301605591108950415L;
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 通过token获取用户信息
     * @param token user token
     * @return username
     */
    String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            // 拿用户名
            username = (String) claims.get("sub");
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 通过token获取创建时间
     * @param token user Token
     * @return createTime
     */
    private Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 通过token获取有效时间
     * @param token user Token
     * @return ExpirationDate
     */
    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * getClaimsFromToken
     * @param token user Token
     * @return Claims
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            // 解析 Token
            claims = Jwts.parser()
                    // 验签
                    .setSigningKey(secret)
                    // 去掉 Bearer
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * getClaimsFromToken
     * @return Date
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration );
    }

    /**
     * token是否过期
     * @param token username
     * @return Boolean
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * isCreatedBeforeLastPasswordReset
     * @param created create time
     * @param lastPasswordReset lastPasswordReset time
     * @return Boolean
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * token生成器
     * @param userDetails the user Details information
     * @return token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 生成token
     * @param claims 权限
     * @return token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                // 保存权限（角色）
                .setClaims(claims)
                // 有效期设置
                .setExpiration(generateExpirationDate())
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * canTokenBeRefreshed
     * @param token user token
     * @param lastPasswordReset lastPasswordReset time
     * @return boolean
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token user old token
     * @return token
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证token
     * @param token user token
     * @param userDetails the user Details
     * @return boolean
     * @throws ParseException the parseException
     */
    Boolean validateToken(String token, UserDetails userDetails) throws ParseException {
        User user = (User) userDetails;
        //获取用户名
        final String username = getUsernameFromToken(token);
        //获取创建时间
        final Date created = getCreatedDateFromToken(token);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        return (
                username.equals(user.getUsername())
                        && !isTokenExpired(token)
                        && !isCreatedBeforeLastPasswordReset(created, format.parse(format.format(user.getLastLoginTime()))));
    }

}

