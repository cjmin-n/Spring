package com.ohgiraffers.section03.annotationconfig.subsection01;

import com.ohgiraffers.common.MemberDAO;
import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        /*
        * ComponentScan
        * basePackages 로 설정 된 하위 경로에 특정 어노테이션을 가지고 있는
        * 클래스를 bean 으로 등록하는 기능이다.
        * @Component 어노테이션이 작성된 클래스를 인식하여 bean 으로 등록한다.
        * 특수 목적에 따라 @Controller, @Service 등을 사용한다.
        * */

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        
        String[] beanNames = context.getBeanDefinitionNames();
        for(String bean : beanNames) {
            System.out.println("bean = " + bean);
            /*
            // springFramework 가 내부적으로 실행하는 필수 bean
                bean = org.springframework.context.annotation.internalConfigurationAnnotationProcessor
                bean = org.springframework.context.annotation.internalAutowiredAnnotationProcessor
                bean = org.springframework.context.event.internalEventListenerProcessor
                bean = org.springframework.context.event.internalEventListenerFactory
            // 작성한 bean
                bean = configurationSection03 // bean 을 가져오는 역할을 하는 bean
                bean = memberDAO
            * */
        }

        MemberDAO memberDAO = context.getBean(MemberDAO.class);

        System.out.println(memberDAO.selectMember(1)); // MemberDTO{sequence=1, id='user01', pwd='pass01', name='홍길동'}

        System.out.println(memberDAO.insertMember(new MemberDTO(3, "user03", "pass03", "홍석삼"))); // true
        System.out.println(memberDAO.selectMember(3)); // MemberDTO{sequence=3, id='user03', pwd='pass03', name='홍석삼'}

    }
}
