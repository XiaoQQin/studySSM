import com.hwm.pojo.Books;
import com.hwm.service.BooksService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class myTest {

    @Test
    public void testDao(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        BooksService booksService = context.getBean("booksService", BooksService.class);
        List<Books> books = booksService.queryBookByName("测试");
        for (Books book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testController(){

    }
}
