package gu_pao.p1_design_parrent.code.p7_template.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<?> executeQueryAll(String sql, RowMapper<?> rowMapper, Object[] params) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //1 获取连接
            connection = getConnection();
            //2 获取语句集
            statement = createQueryStatement(connection, sql);
            //3 执行查询语句获取结果集
            resultSet = executeQuery(statement, params);
            //4 解析结果
            return parseresult(resultSet, rowMapper);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet);
            this.close(statement);
            this.close(connection);
        }
        return null;
    }

    private void close(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void close(PreparedStatement statement) {
        try {
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void close(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<?> parseresult(ResultSet resultSet, RowMapper<?> rowMapper) {
        ArrayList<Object> list = new ArrayList<>();
        int i = 0;
        try {

            while (resultSet.next()) {
                list.add(rowMapper.mapRow(resultSet, i++));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private PreparedStatement createQueryStatement(Connection connection, String sql) {

        try {
            return connection.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    private Connection getConnection() {
        try {
            return this.dataSource.getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //查询
    public ResultSet executeQuery(PreparedStatement preparedStatement, Object[] params) {

        try {

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i, params[i]);

            }
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }


}
