package com.ohgiraffers.section01.subsection01.scope;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        /*
        * bean scope 란 ? 빈이 생성될 때 생성되는 인스턴스의 범위를 의미한다.
        * 스프링에서는 다양한 스코프를 제공한다.
        *
        * 1. singleton  하나의 인스턴스만 생성하고, 모든 빈이 해당 인스턴스를 공유한다.
        * 2. prototype  매번 새로운 인스턴스를 생성한다.
        * 3. request    http 요청을 처리할 때 마다 새로운 인스턴스를 생성하고, 요청 처리가 끝나면 인스턴스를 폐기한다.
        * 4. session    http 세션 당 하나의 인스턴스를 생성하고, 세션이 종료되면 인스턴스를 폐기한다.
        * */

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        
        String[] beanNames = context.getBeanDefinitionNames();
        for (String bean : beanNames) {
            System.out.println("bean = " + bean);
            /*
            bean = org.springframework.context.annotation.internalConfigurationAnnotationProcessor
            bean = org.springframework.context.annotation.internalAutowiredAnnotationProcessor
            bean = org.springframework.context.event.internalEventListenerProcessor
            bean = org.springframework.context.event.internalEventListenerFactory
            bean = contextConfiguration
            bean = carpBread
            bean = milk
            bean = water
            bean = cart
            * */
        }

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
        // cart2 에 담긴 물품 : [붕어빵 1000 Tue Oct 22 10:13:37 KST 2024, 딸기우유 1500 500, 지리산암반수 1000 500, 지리산암반수 1000 500]

        // 해쉬코드가 같아 동일객체이다.
        System.out.println("cart = " + cart.hashCode()); // cart = 1546908073
        System.out.println("cart2 = " + cart2.hashCode()); // cart2 = 1546908073

        /*
        cart 객체에 @Scope("prototype") 해주면 매번 새로운 인스턴스를 생성하여, 원하는 값을 만들 수 있다.

        cart 에 담긴 물품 : [붕어빵 1000 Tue Oct 22 10:16:20 KST 2024, 딸기우유 1500 500, 지리산암반수 1000 500]
        cart2 에 담긴 물품 : [지리산암반수 1000 500]
        cart = 440938038
        cart2 = 904058452
        * */

    }
}
