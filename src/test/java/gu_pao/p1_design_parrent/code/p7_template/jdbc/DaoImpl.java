package gu_pao.p1_design_parrent.code.p7_template.jdbc;

import pojo.Person;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class DaoImpl extends JdbcTemplate {

    public DaoImpl(DataSource dataSource) {
        super(dataSource);
    }


    public List<?> selectAll() {
        String sql = "select * from user";
        return super.executeQueryAll(sql, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet resultSet, int index) throws Exception {
                Person person = new Person();
                person.setName(resultSet.getString("user_name"));
                person.setSex(resultSet.getString("sex"));
                return person;
            }
        }, null);
    }

}

