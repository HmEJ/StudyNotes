package com.mh.springbootenable;

import com.mh.config.EnableUser;
import com.mh.config.MyImportBeanDefinitionRegistrar;
import com.mh.config.MyImportSelector;
import com.mh.config.UserConfig;
import com.mh.domain.Role;
import com.mh.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@ComponentScan("com.mh")
//@Import(UserConfig.class)
//@EnableUser
//@Import(MyImportSelector.class)
@Import(MyImportBeanDefinitionRegistrar.class)
public class SpringbootEnableApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootEnableApplication.class, args);

        User user = context.getBean(User.class);
        System.out.println(user);

        Role role = context.getBean(Role.class);
        System.out.println(role);
    }

}
