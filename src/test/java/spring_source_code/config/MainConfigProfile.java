package spring_source_code.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;


//@Profile("test")
@Configuration
@PropertySource({"classpath:properties/db.properties"})
public class MainConfigProfile implements EmbeddedValueResolverAware {


    @Value("${jdbc.username}")
    private String name;

    private String url;

    //@Profile("default")//默认是这样


    @Profile("test")
    @Bean("dataSourcetest")
    public DataSource activitiDataSource(@Value("${jdbc.password}") String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(name);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

    private String driver;

    @Profile("dev")
    @Bean("dataSourceDev")
    public DataSource dev(@Value("${jdbc.password}") String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(name);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

    @Profile("pro")
    @Bean("dataSourcePro")
    public DataSource pro(@Value("${jdbc.password}") String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(name);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }


    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        String s = stringValueResolver.resolveStringValue("jdbc.url");
        String driver = stringValueResolver.resolveStringValue("jdbc.driver");
        this.url = s;
        this.driver = driver;
    }
}
