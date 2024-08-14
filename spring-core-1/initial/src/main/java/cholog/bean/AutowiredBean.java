package cholog.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class AutowiredBean {

    @Autowired
    private SpringBean springBean;
//    private SpringBean springBean = new SpringBean();

//    가장 추천
//    public AutowiredBean(SpringBean springBean) {
//        this.springBean = springBean;
//    }

    public String sayHello() {
        return springBean.hello();
    }
}
