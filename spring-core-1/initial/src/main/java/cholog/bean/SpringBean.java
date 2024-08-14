package cholog.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SpringBean {
    public String hello() {
        return "Hello";
    }
}

//@Configuration
//public class SpringBean {
//    @Bean
//    public String hello() {
//        return "Hello";
//    }
//}
