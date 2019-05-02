package spring_source_code.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import spring_source_code.pojo.Car;

@Configuration
@ComponentScan("spring_source_code.pojo")
public class MainConfigLifecyle {

    @Bean(value = "car1", initMethod = "init", destroyMethod = "destory")
    //@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Car car() {
        return new Car();
    }


}
