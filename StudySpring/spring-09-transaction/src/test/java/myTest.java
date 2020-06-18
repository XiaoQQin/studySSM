import com.hwm.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class myTest {

    @Test
    public void testtransaction(){

        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");


        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);

        userMapper.selectUsers();
    }
}
