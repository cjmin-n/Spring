package com.ohgiraffers.section04.properties;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.util.Date;

@Configuration
@PropertySource("section04/properties/product-info.properties")
public class ContextConfiguration {

    /*
     * 치환자(placeholder) 문법을 이용하여 properties 에 저장된 key 를 입력하면 value 에 해당하는 값을 꺼내온다.
     * 공백을 사용하면 값을 읽어오지 못하니 주의한다.
     * : 을 사용하면 값을 읽어오지 못하는 경우 사용할 대체 값을 작성할 수 있다.
     *
     * */

    @Value("${bread.carpbread.name:팥붕어빵}")                           // 리소스에 있는 값을 가져올 때 씀
    private String carpBreadName;

    @Value("${bread.carpbread.price:0}")
    private int carpBreadPrice;

    @Value("${beverage.milk.name:}")
    private String milkName;

    @Value("${beverage.milk.price:0}")
    private int milkPrice;

    @Value("${beverage.milk.capacity:0}")
    private int milkCapacity;

    @Bean
    public Product carpBread(){
        return new Bread(carpBreadName, carpBreadPrice, new Date());
    }

    @Bean
    public Product milk(){
        return new Beverage(milkName,milkPrice,milkCapacity);
    }

    @Bean
    public Product water(@Value("${beverage.water.name:}")String waterName,
                         @Value("${beverage.water.price:0}")int waterPrice,
                         @Value("${beverage.water.capacity:0}")int waterCapacity
    ){
        return new Beverage(waterName,waterPrice,waterCapacity);
    }

    @Bean
    @Scope("prototype")
    public ShoppingCart cart(){
        return new ShoppingCart();
    }
}
