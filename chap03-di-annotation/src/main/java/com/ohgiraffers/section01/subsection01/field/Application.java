package com.ohgiraffers.section01.subsection01.field;

import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.ohgiraffers.section01");
        
        BookService bookService = context.getBean(BookService.class);
        
        // 전체 책 조회
        List<BookDTO> bookDTOList  = bookService.selectAllBook();
        
        for(BookDTO book : bookDTOList){
            System.out.println("book = " + book);
            /*
            book = BookDTO{seq=1, isbn=123, title='자바의정석', author='남궁성', publisher='도우출판', createDate=Mon Oct 21 14:49:49 KST 2024}
            book = BookDTO{seq=2, isbn=456, title='컨테이너', author='문성주', publisher='길벗', createDate=Mon Oct 21 14:49:49 KST 2024}
            * */
        }
        
        // 책 하나 찾기 
        BookDTO book = bookService.selectOneBook(1);
        System.out.println("book = " + book);
        
    }
}
