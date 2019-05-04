package spring_source_code.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"spring_source_code.tx"})
@EnableTransactionManagement
public class MainConfigTx {


    @Bean("txDataSource")
    public DataSource txDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("abc");
        dataSource.setUrl("jdbc:mysql://localhost:3306/learn?characterEncoding=utf-8");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(txDataSource());
    }


    @Bean
    public JdbcTemplate jdbcTemplate() {
        //在配置类中调用@Bean bean是从容器中获取
        return new JdbcTemplate(txDataSource());
    }


}
