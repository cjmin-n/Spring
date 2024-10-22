package com.ohgiraffers.section01.aop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MemberService {

    private final MemberDAO memberDAO;

    @Autowired
    public MemberService() {
        this.memberDAO = new MemberDAO();
    }

    public Map<Integer, MemberDTO> selectMembers(){
        System.out.println("selectMembers 메소드 실행");
        return memberDAO.selectMembers();
    }

    public MemberDTO selectMember(int id){
        System.out.println("selectMember 메소드 실행");
        return memberDAO.selectMember(id);
    }

    @Override
    public String toString() {
        return "MemberService Bean";
    }
}
