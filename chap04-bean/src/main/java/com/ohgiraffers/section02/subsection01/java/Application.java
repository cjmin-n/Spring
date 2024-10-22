package com.ohgiraffers.section02.subsection01.java;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {


        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        // context 만 만들어도 @Bean(initMethod = "openShop")이 실행된다.

        Product carpBread = context.getBean("carpBread", Bread.class);
        Product milk = context.getBean("milk", Beverage.class);
        Product water = context.getBean("water", Beverage.class);

        // 첫 번째 손님이 쇼핑 카트를 꺼낸다.
        ShoppingCart cart = context.getBean("cart", ShoppingCart.class);
        cart.addItem(carpBread);
        cart.addItem(milk);
        cart.addItem(water);
        System.out.println("cart 에 담긴 물품 : " + cart.getItems());
        // cart 에 담긴 물품 : [붕어빵 1000 Tue Oct 22 10:08:54 KST 2024, 딸기우유 1500 500, 지리산암반수 1000 500]

        // 두 번째 손님이 카트를 꺼낸다.
        ShoppingCart cart2 = context.getBean("cart", ShoppingCart.class);
        cart2.addItem(water);
        System.out.println("cart2 에 담긴 물품 : " + cart2.getItems());
        // cart2 에 담긴 물품 : [지리산암반수 1000 500]

        
        // context 강제종료

        // ApplicationContext 이 interface 이기 때문에
        // (다운캐스팅) AnnotationConfigApplicationContext 으로 형변환 시켜줘야 close 할 수 있음.
        ((AnnotationConfigApplicationContext)context).close();
        // 사장님이 가게 문을 닫았습니다. 이제 쇼핑을 하실 수 없습니다.

    }
}
