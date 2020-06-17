import com.hwm.config.MyConfig;
import com.hwm.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class myTest {

    public static void main(String[] args) {
        //使用java配置的话，需要使用AnnotationConfigApplicationContext生成ApplicationContext对象
        ApplicationContext context=new AnnotationConfigApplicationContext(MyConfig.class);

        User getUser = context.getBean("getUser", User.class);

        System.out.println(getUser);
    }
}
