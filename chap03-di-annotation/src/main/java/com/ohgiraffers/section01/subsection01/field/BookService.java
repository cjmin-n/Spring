package com.ohgiraffers.section01.subsection01.field;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookServiceField")
public class BookService {

    // DI 주입하는 방식
    // 1. 필드 주입 (Autowired 는 인터페이스인지 확인해서 상속받은 클래스를 가져옴 - 여러개일 경우 순서를 정해줘야한다.)
    @Autowired
    private BookDAO bookDAO;

    public List<BookDTO> selectAllBook(){
        return bookDAO.selectBookList();
    }

    public BookDTO selectOneBook(int seq){
        return bookDAO.selectOneBook(seq);
    }


}
