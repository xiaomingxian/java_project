package pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DatePojoTest {

    private String name;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "DatePojoTest{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                '}';
    }
}
