package com.ohgiraffers.section02.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // 인터페이스를 상속받고 있는 동일한 타입의 클래스 중에 제일 우선적으로 연결된다. // 지우면 에러
public class Charmander implements Pokemon{
    
    @Override
    public void attack() {
        System.out.println("파이리 불꽃");
    }
}
