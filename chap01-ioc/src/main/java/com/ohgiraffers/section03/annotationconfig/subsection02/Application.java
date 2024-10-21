package com.ohgiraffers.section03.annotationconfig.subsection02;

import com.ohgiraffers.common.MemberDAO;
import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context = new GenericXmlApplicationContext("section03/annotationconfig/subsection02/xml/spring-context.xml");
        
        String[] beanNames = context.getBeanDefinitionNames();
        for (String bean : beanNames) {
            System.out.println("bean = " + bean);
            /*
            bean = memberDAO
            bean = configurationSection03
            bean = org.springframework.context.annotation.internalConfigurationAnnotationProcessor
            bean = org.springframework.context.annotation.internalAutowiredAnnotationProcessor
            bean = org.springframework.context.event.internalEventListenerProcessor
            bean = org.springframework.context.event.internalEventListenerFactory
            * */
        }

        MemberDAO memberDAO = context.getBean(MemberDAO.class);

        System.out.println(memberDAO.selectMember(1)); // MemberDTO{sequence=1, id='user01', pwd='pass01', name='홍길동'}

        System.out.println(memberDAO.insertMember(new MemberDTO(3, "user03", "pass03", "홍석삼"))); // true
        System.out.println(memberDAO.selectMember(3)); // MemberDTO{sequence=3, id='user03', pwd='pass03', name='홍석삼'}
    }
}
