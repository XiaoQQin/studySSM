package com.hwm.controller;

import com.hwm.pojo.Books;
import com.hwm.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    //controller层调用service层

    @Autowired
    @Qualifier("booksService")
    private BooksService booksService;

    //查询全部书籍
    @RequestMapping("/allBooks")
    public String listBooks(Model model){
        List<Books> books = booksService.selectAllBooks();
        model.addAttribute("list",books);
        return "allBooks";
    }

    //跳转到增加书籍页面
    @RequestMapping("/toaddBooks")
    public String roAddPaper(){
        return "addBooks";
    }
    @RequestMapping("/addBooks")
    public String addBook(Books books){
        booksService.addBook(books);
        System.out.println(books);
        //重定向@RequestMapping("/allBooks")
        return "redirect:/books/allBooks";
    }

    @RequestMapping("/toupdateBooks/{bookId}")
    //使用restful风格
    public String toupdateBook(@PathVariable int bookId,Model model){
        Books books = booksService.selectBookById(bookId);
        //这个传到页面
        model.addAttribute("book",books);
        return "updateBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Books books){
        booksService.updateBook(books);
        System.out.println("修改书籍"+books.getBookId());
        return "redirect:/books/allBooks";
    }

    @RequestMapping("/deleteBookById/{bookId}")
    public String deleteBook(@PathVariable int bookId){
        booksService.deleteBookById(bookId);
        //重定向
        return "redirect:/books/allBooks";
    }

    @RequestMapping("/queryBooks")
    public String queryBooks(String queryBookName,Model model){
        List<Books> books = booksService.queryBookByName(queryBookName);
        System.out.println("books是空吗"+books==null);
        if(books.size()==0){
            books=booksService.selectAllBooks();
            model.addAttribute("error","未查到相关书籍");
        }
        model.addAttribute("list",books);
        return "allBooks";
    }


}
