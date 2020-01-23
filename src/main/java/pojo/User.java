package pojo;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "test")
public class User {
    private String id;

    private String userName;

    private String password;

    private String phone;

    private String email;

    private Date createTime;

    private Date updateTima;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", updateTima=" + updateTima +
                '}';
    }
}