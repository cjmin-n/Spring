package com.ohgiraffers.section02.annotation.subsection01.primary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.ohgiraffers.section02");

        String[] beanNames = context.getBeanDefinitionNames();

        for (String bean : beanNames) {
            System.out.println("bean = " + bean);
            /*
            bean = org.springframework.context.annotation.internalConfigurationAnnotationProcessor
            bean = org.springframework.context.annotation.internalAutowiredAnnotationProcessor
            bean = org.springframework.context.event.internalEventListenerProcessor
            bean = org.springframework.context.event.internalEventListenerFactory
            bean = pokemonServicePrimary
            bean = charmander
            bean = pikachu
            bean = squirtle
            * */
        }

        PokemonService pokemonService = context.getBean("pokemonServicePrimary", PokemonService.class);

        pokemonService.pokemonAttack(); // 파이리 불꽃
    }
}
