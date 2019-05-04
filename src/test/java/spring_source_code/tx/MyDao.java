package spring_source_code.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MyDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert() {
        String sql = "insert into test (name,age) values (?,?)";
        jdbcTemplate.update(sql, "tom", 20);
        int i = 1 / 0;

    }
}
