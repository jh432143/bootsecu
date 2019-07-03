package com.study.kang.bootsecu.interceptor;

import com.study.kang.bootsecu.exception.NotAuthorizationException;
import com.study.kang.bootsecu.util.CookieUtil;
import com.study.kang.bootsecu.web.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Value("${jwt.token.cookieName}")
    private String jwtTokenCookieName;

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 헤더정보에서 토큰을 추출
        //String token = request.getHeader("Authorization");

        // 쿠키정보에서 토큰을 추출
        String token = CookieUtil.getValue(request, jwtTokenCookieName);

        if (token != null && jwtService.isUsable(token)) {
            return true;
        } else {
            throw new NotAuthorizationException();
        }
    }
}
