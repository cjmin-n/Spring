package com.ohgiraffers.section02.subsection01.java;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Date;

@Configuration
public class ContextConfiguration {

    @Bean
    public Product carpBread(){
        return new Bread("붕어빵", 1000, new Date());
    }

    @Bean
    public Product milk(){
        return new Beverage("딸기우유", 1500, 500);
    }

    @Bean
    public Product water(){
        return new Beverage("지리산암반수", 1000, 500);
    }

    @Bean
    @Scope("prototype") // 요청마다 새로운 객체 생성
    public ShoppingCart cart(){
        return new ShoppingCart();
    }

    // initMethod : 빈이 등록될 때 제일 먼저 실행 (빈이 실행되기 전에 동작해야할 것)
    // destroyMethod : context 가 종료될 때 실행될 메소드 (ex. 외부 API 연결 끊어줄 때)
    @Bean(initMethod = "openShop", destroyMethod = "closeShop")
    public Owner owner(){
        return new Owner();
    }
}
