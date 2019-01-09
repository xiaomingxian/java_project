package pojo.excelpojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import jnr.ffi.annotations.In;

import java.util.Date;

public class Teacher extends BaseRowModel {

    @ExcelProperty(value = {"基本信息", "工号"}, index = 0)
    private Integer id;
    @ExcelProperty(value = {"基本信息", "姓名"}, index = 1)
    private String name;
    @ExcelProperty(value = {"基本信息", "年龄"}, index = 2)
    private Integer age;
    @ExcelProperty(value = {"基本信息", "住址"}, index = 3)
    private String address;

    @ExcelProperty(value = {"基本信息", "生日"}, index = 4, format = "yyyy-MM-dd")
    private Date birthday;

    @ExcelProperty(value = {"工作", "教室"}, index = 5)
    private String className;


    @ExcelProperty(value = {"工作", "班主任"}, index = 6)
    private String isBos;

    @ExcelProperty(value = "级别", index = 7)
    private Integer jiBie;


    public String getIsBos() {
        return isBos;
    }

    public void setIsBos(String isBos) {
        this.isBos = isBos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    public Integer getJiBie() {
        return jiBie;
    }

    public void setJiBie(Integer jiBie) {
        this.jiBie = jiBie;
    }
}
