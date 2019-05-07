package design_parrent.pojo.p7_composite;


import lombok.Data;

/**
 * 为用户提供，忽略了层级
 */
public interface AbstractFile {


    //private int type;//可以定义类型--文件/文件夹

    void add(AbstractFile abstractFile);

    void del(AbstractFile abstractFile);

    AbstractFile get(int index);

    //杀毒
    void killDu();
}
