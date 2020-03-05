package com.nowcoder.community;


import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes=TestApplication.class)
class TestApplicationTests implements ApplicationContextAware {

   
private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
    @Autowired
    @Qualifier("ssss")
    private TestApplication testApplication;

    @Test
    public void testApplicationContext(){
        System.out.println(applicationContext);
    }
}
