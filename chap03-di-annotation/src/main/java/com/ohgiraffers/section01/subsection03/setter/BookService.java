package com.ohgiraffers.section01.subsection03.setter;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookServiceSetter")
public class BookService {

    private BookDAO bookDAO;

    // 3. setter 주입방식 - 시점이 느려서(생성되고 좀 후에) 필드에 final 없이 만들어야함
    @Autowired
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<BookDTO> selectAllBooks(){
        return bookDAO.selectBookList();
    }

    public BookDTO selectOneBook(int seq){
        return bookDAO.selectOneBook(seq);
    }
}
