package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.common.Account;
import com.ohgiraffers.common.MemberDTO;
import com.ohgiraffers.common.PersonalAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {

    @Bean
    public Account accountGenerator(){
        return new PersonalAccount(20, "110-233-2222");
    }
    // accountGenerator() 는 Bean 이 아니어도 쓸 수 있지만
    // memberGenerator() 가 호출될 때, 계속 새롭게 만들기 때문에 (accountGenerator() 도) Bean 으로 만듦

    @Bean
    public MemberDTO memberGenerator(){
        MemberDTO member = new MemberDTO(30, "홍석천", "010-2222-3333", "hong@gmail.com", accountGenerator());

        return member;
    }
}
