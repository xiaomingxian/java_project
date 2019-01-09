package pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Test {

    private Integer id;
    private String info;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
