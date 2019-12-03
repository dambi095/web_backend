package com.exdiary.web_backend.security;

import com.exdiary.web_backend.service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 토큰 관련 메소드
 **/
@Component
public class JwtTokenProvider {

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.secretkey}")
    private String secretKey;

    @Autowired
    private UserService userService;

    // token 생성
    public String createToken(String email) {

        System.out.println("createToken current date:: " + new Date(System.currentTimeMillis()));
        System.out.println("*********** expiration duration:: " + expiration);
        System.out.println("*********** expiration:: " + new Date(System.currentTimeMillis() + expiration));
        return Jwts.builder()//
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS512)
                //.signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .setHeaderParam("typ", "JWT")
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    // token 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    //token 유효성 체크
    public boolean validateToken(String token) {
        try {
            System.out.println("validatetoken token:: " + token);
            Jws<Claims> jws = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
            System.out.println("validatetoken expiration:: " + jws.getBody().getExpiration());
            if (jws.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Expired or invalid JWT token");
            System.out.println(e);
            return false;
        }
    }

    //사용자 인증
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //token 에서 사용자 추출
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }

}
