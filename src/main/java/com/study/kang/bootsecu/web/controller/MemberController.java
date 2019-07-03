package com.study.kang.bootsecu.web.controller;

import com.study.kang.bootsecu.util.CookieUtil;
import com.study.kang.bootsecu.web.service.JwtService;
import com.study.kang.bootsecu.web.service.MemberService;
import com.study.kang.bootsecu.web.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MemberController {
    @Value("${jwt.token.cookieName}")
    private String jwtTokenCookieName;

    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtService jwtService;

    /**
     *  로그인페이지
     */

    /**
     * 로그인처리
     */
    @RequestMapping(value = "/member/signin")
    public String memberSignin(HttpServletResponse response) {
        MemberVO memberVO = memberService.memberSignin("test@gamil.com", "123456");
        String token = jwtService.getToken(memberVO);
        //토큰을 헤더에 세팅
        //response.setHeader("Authorization", token);

        // 토큰을 쿠키에 세팅 60은 60초 즉 1분
        CookieUtil.create(response, jwtTokenCookieName, token, false, 60 * 60 * 24, "");

        return "index";
    }

    /**
     * 로그아웃처리
     */
    @RequestMapping(value = "/member/logout")
    public String memberLogout(HttpServletResponse response) {
        // 쿠키제거
        CookieUtil.clear(response, jwtTokenCookieName);

        return "index";
    }
}
