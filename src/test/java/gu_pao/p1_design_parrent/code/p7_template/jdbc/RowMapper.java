package gu_pao.p1_design_parrent.code.p7_template.jdbc;

import java.sql.ResultSet;

public interface RowMapper<T> {


    public T mapRow(ResultSet resultSet,int index)throws Exception;
}
