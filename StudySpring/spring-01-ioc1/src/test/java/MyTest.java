import com.hwm.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// TODO: 2020/6/16
public class MyTest {
    public static void main(String[] args) {
        //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //对象都在spring中进行管理，我们要使用取出来即可
        Hello hello = (Hello) context.getBean("hello");

        System.out.println(hello);
    }
}
