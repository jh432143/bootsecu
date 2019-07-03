package com.study.kang.bootsecu.web.service;

import com.study.kang.bootsecu.web.vo.MemberVO;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public MemberVO memberSignin(String email, String password) {
        //MemberVO memberVO = selectMemberSignin(String email, String password)
        MemberVO memberVO = new MemberVO();
        memberVO.setEmail("test@gmail.com");
        memberVO.setPassword("123456");
        return memberVO;
    }
}
