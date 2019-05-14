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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTima() {
        return updateTima;
    }

    public void setUpdateTima(Date updateTima) {
        this.updateTima = updateTima;
    }
}