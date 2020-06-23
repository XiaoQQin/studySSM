package com.hwm.service;

import com.hwm.dao.BooksMapper;
import com.hwm.pojo.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("booksService")
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksMapper booksMapper;

    public BooksServiceImpl(BooksMapper booksMapper) {
        this.booksMapper = booksMapper;
    }

    public void setBooksMapper(BooksMapper booksMapper) {
        this.booksMapper = booksMapper;
    }

    public int addBook(Books book) {

        return booksMapper.addBook(book);
    }

    public int deleteBookById(int bookId) {

        return booksMapper.deleteBookById(bookId);
    }

    public Books selectBookById(int bookId) {

        return booksMapper.selectBookById(bookId);
    }

    public List<Books> selectAllBooks() {

        return booksMapper.selectAllBooks();
    }

    public int updateBook(Books book) {

        return booksMapper.updateBook(book);
    }

    public List<Books> queryBookByName(String queryBookByName) {
        return booksMapper.queryBookByName(queryBookByName);
    }
}
