package com.ohgiraffers.section03.annotationconfig.subsection01;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("configurationSection03")
@ComponentScan(basePackages = "com.ohgiraffers.common") // basePackages 하위에 빈이 있다면 가져올 수 있도록
public class ContextConfiguration {


}
