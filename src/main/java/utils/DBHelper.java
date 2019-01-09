package utils;

import java.sql.*;

public class DBHelper {
    public static final String url = "jdbc:teradata://xxx.xxx.x.xxx/CLIENT_CHARSET=GBK,TMODE=TERA,CHARSET=ASCII,database=etlsim1,LOB_Support=OFF";
    public static final String name = "com.teradata.jdbc.TeraDriver";
    public static final String user = "etlsim1";
    public static final String password = "etlsim1";

    public Connection conn = null;
    public PreparedStatement pst = null;

    public DBHelper(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sql = "select *from ETL_Server";//SQL语句
        DBHelper db1 = new DBHelper(sql);//创建DBHelper对象

        try {
            ResultSet ret = db1.pst.executeQuery();//执行语句，得到结果集
            while (ret.next()) {
                String uid = ret.getString(1);
                String ufname = ret.getString(2);

            }
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
