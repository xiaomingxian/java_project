package spring_source_code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import spring_source_code.pojo.Advice;
import spring_source_code.pojo.Math;

@EnableAspectJAutoProxy//开启切面自动代理
@Configuration
public class MainConfigAop {

    @Bean
    public Advice advice() {
        return new Advice();
    }

    @Bean
    public Math math() {
        return new Math();
    }

}
