package com.ohgiraffers.section01.xmlconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context = new GenericXmlApplicationContext("section01/xmlconfig/spring-context.xml");

        MemberDTO member = (MemberDTO) context.getBean("member");

        System.out.println("member = " + member); // member = MemberDTO{seq=1, name='홍길동', phone='010-2222-3333', email='hong@gmail.com', personalAccount=PersonalAccount{bankCode=20, accNo='110-223-423321', balance=0}}
        System.out.println(member.getPersonalAccount()); // PersonalAccount{bankCode=20, accNo='110-223-423321', balance=0}

        // 입금
        System.out.println(member.getPersonalAccount().deposit(10000)); // 10000원이 입금 되었습니다.
        // 잔액 출력
        System.out.println(member.getPersonalAccount().getBalance()); // 110-223-423321 계좌의 현재 잔액 10000원 입니다.
        // 출금
        System.out.println(member.getPersonalAccount().withDraw(5000)); // 5000원이 출금 되었습니다.
        // 잔액 출력
        System.out.println(member.getPersonalAccount().getBalance()); // 110-223-423321 계좌의 현재 잔액 5000원 입니다.
        // 출금
        System.out.println(member.getPersonalAccount().withDraw(10000)); // 현재 잔액은 5000원 입니다. 10000원 보다 적습니다.
    }
}
