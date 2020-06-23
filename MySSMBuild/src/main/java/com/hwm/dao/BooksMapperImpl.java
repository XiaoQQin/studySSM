package com.hwm.dao;

import com.hwm.pojo.Books;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class BooksMapperImpl extends SqlSessionDaoSupport implements BooksMapper {
    public int addBook(Books book) {
        BooksMapper mapper = getSqlSession().getMapper(BooksMapper.class);

        int i = mapper.addBook(book);
        return i;
    }

    public int deleteBookById(int bookId) {
        return getSqlSession().getMapper(BooksMapper.class).deleteBookById(bookId);
    }

    public Books selectBookById(int bookId) {
        return getSqlSession().getMapper(BooksMapper.class).selectBookById(bookId);
    }

    public List<Books> selectAllBooks() {
        return getSqlSession().getMapper(BooksMapper.class).selectAllBooks();
    }

    public int updateBook(Books book) {
        return getSqlSession().getMapper(BooksMapper.class).updateBook(book);
    }

    public List<Books> queryBookByName(String name) {
        return null;
    }
}
