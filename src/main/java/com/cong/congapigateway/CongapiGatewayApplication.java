package com.cong.congapigateway;

import com.cong.project.provider.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@EnableDubbo
public class CongapiGatewayApplication {
    @DubboReference
    private DemoService demoService;
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CongapiGatewayApplication.class, args);
        CongapiGatewayApplication application = context.getBean(CongapiGatewayApplication.class);
        String result =  application.doSayHello("world");
        System.out.println(result);
    }


    public String doSayHello(String name){
        return demoService.sayHello(name);
    }
}
