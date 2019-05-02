package spring_source_code.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import spring_source_code.pojo.MyAware;
import spring_source_code.pojo.Proper;

@Configuration
@Import({Proper.class, MyAware.class})
@PropertySource({"classpath:properties/properties"})
public class MainConfigProperty {


}
