import com.hwm.pojo.People;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void testPeoPle(){

        ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");


        People people = context.getBean("people", People.class);

        people.getCat().shout();
        people.getDog().shout();
    }

    @Test
    public void testAutoWired(){
        ApplicationContext context=new ClassPathXmlApplicationContext("beans_autowire.xml");

        People people = context.getBean("people", People.class);

        people.getDog().shout();
        people.getCat().shout();
    }
}
