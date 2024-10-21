package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("configurationSection02")
public class ContextConfiguration {

    // 메소드의 반환값을 빈으로 등록
    @Bean(name = "member") // @Bean("member")도 가능 // 이름 지정안하면 메소드 이름을 빈 이름으로 가짐
    public MemberDTO getMember(){
        return new MemberDTO(1, "user02", "pass", "홍길동");
    }
}
