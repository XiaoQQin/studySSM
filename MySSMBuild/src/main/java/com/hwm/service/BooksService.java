package com.hwm.service;

import com.hwm.pojo.Books;

import java.util.List;

public interface BooksService {

    int addBook(Books book);
    //删
    int deleteBookById(int bookId);
    //查
    Books selectBookById(int bookId);
    List<Books> selectAllBooks();
    //改
    int updateBook(Books book);

    List<Books> queryBookByName(String queryBookByName);
}
