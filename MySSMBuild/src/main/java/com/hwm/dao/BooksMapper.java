package com.hwm.dao;

import com.hwm.pojo.Books;

import java.util.List;

public interface BooksMapper {
    //增
    int addBook(Books book);
    //删
    int deleteBookById(int bookId);
    //查
    Books selectBookById(int bookId);
    List<Books> selectAllBooks();
    //改
    int updateBook(Books book);
    //根据书名查询
    List<Books> queryBookByName(String queryBookByName);
}
