package mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectTest {

    private static String driver = "com.mysql.jdbc.Driver";//mysql8.0驱动
    private static String url = "jdbc:mysql://localhost:3306/springboot_learn?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
    private static String userName = "root";
    private static String passWd = "abc";

    //加载驱动
    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("··········"+e);
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(url, userName, passWd);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println(resultSet);

        } catch (Exception e) {
            System.out.println("··········"+e);
        }

    }
}
