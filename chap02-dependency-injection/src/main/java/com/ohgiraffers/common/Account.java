package com.ohgiraffers.common;

public interface Account {

    // 구현 책임 의무화
    String getBalance(); // 잔액

    String deposit(int money); // 입금
    
    String withDraw(int money); // 출금
}
