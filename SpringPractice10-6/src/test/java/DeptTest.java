import com.mh.entity.Dept;
import com.mh.service.Cal;
import com.mh.service.DeptService;
import com.mh.service.impl.CalImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.AliasFor;

import java.util.List;

public class DeptTest {
    @Test
    public void deptTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext("com.mh.entity");
        Dept bean = context.getBean(Dept.class);
        System.out.println(bean);
    }
    @Test
    public void aopTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Cal bean = context.getBean(Cal.class);
        System.out.println(bean.add(5, 10));
        System.out.println(bean.add(6, 8));
    }

    @Test
    public void mybatisTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        DeptService deptService = context.getBean("deptServiceImpl",DeptService.class);
        List<Dept> list = deptService.queryAll();
        for (Dept dept : list) {
            System.out.println(dept);
        }
    }
}
