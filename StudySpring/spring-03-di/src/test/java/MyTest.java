import com.hwm.pojo.Student;
import com.hwm.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Student student = (Student) context.getBean("student");
        //Student{
        // name='hwm',
        // address=Address{adress='地址'},
        // books=[book1, book2, book3],
        // hobbys=[hobbys1, hobbys2, hobbys3],
        // card={username=123, pwd=123},
        // games=[set1, set2], wifi='null',
        // info={key3=value3, key2=value2, key1=value1}, ads=[Address{adress='地址'}, Address{adress='地址1'}]}
        System.out.println(student);
    }


    @Test
    public void testP(){
        ApplicationContext context = new ClassPathXmlApplicationContext("UserBean.xml");

        User user2 = context.getBean("user2", User.class);

        System.out.println(user2);
    }
}
