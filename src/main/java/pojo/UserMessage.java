package pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserMessage implements Serializable {


    private String id;
    private String name;
    private String telphone;
    private String mobile;
    private String outEmail;
    private String inEmail;
    private String faxcode;
    private String sex;
    private String remark;
    private List<Map> authorities;
    private String username;

    public List<Map> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Map> authorities) {
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOutEmail() {
        return outEmail;
    }

    public void setOutEmail(String outEmail) {
        this.outEmail = outEmail;
    }

    public String getInEmail() {
        return inEmail;
    }

    public void setInEmail(String inEmail) {
        this.inEmail = inEmail;
    }

    public String getFaxcode() {
        return faxcode;
    }

    public void setFaxcode(String faxcode) {
        this.faxcode = faxcode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
       return  name;
    }
}
