package com.study.kang.bootsecu.web.service;

import com.study.kang.bootsecu.exception.NotAuthorizationException;
import com.study.kang.bootsecu.web.vo.MemberVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private static final String secureKey = "luvookSecret";

    public String getToken(MemberVO memberVO) {
        String jwt = Jwts.builder().setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setSubject(memberVO.getEmail())
                .claim("password", memberVO.getPassword())
                .setExpiration(new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 60 * 24)))
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact();
        return jwt;
    }

    private byte[] generateKey() {
        byte[] key = null;
        try {
            key = secureKey.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public boolean isUsable(String token) {
        try {
            // parser 도중 체크하는 에러 5가지.
            /*
             * 1. ExpiredJwtException : JWT를 생성할 때 지정한 유효기간 초과할 때.
             * 2. UnsupportedJwtException : 예상하는 형식과 일치하지 않는 특정 형식이나 구성의 JWT일 때
             * 3. MalformedJwtException : JWT가 올바르게 구성되지 않았을 때
             * 4. SignatureException :  JWT의 기존 서명을 확인하지 못했을 때
             * 5. IllegalArgumentException
             */
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.generateKey())
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            throw new NotAuthorizationException();
        }
    }
}
