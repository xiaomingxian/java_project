package design_parrent.pojo.p7_composite;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Folder implements AbstractFile {

    List<AbstractFile> list = new ArrayList<AbstractFile>();

    private String name;

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public void add(AbstractFile abstractFile) {
        list.add(abstractFile);
        System.out.println("--元素添加成功");
    }

    @Override
    public void del(AbstractFile abstractFile) {
        boolean remove = list.remove(abstractFile);
        //递归查找
        if (remove) System.out.println("--元素移除成功");
        else System.out.println("---元素移除失败");
    }

    @Override
    public AbstractFile get(int index) {
        return list.get(index);
    }

    @Override
    public void killDu() {
        //文件夹杀毒
        list.stream().forEach((obj) -> {
            if (obj instanceof Folder) {//或者用类型进行判断
                //    递归
                System.out.println("========对文件夹：" + ((Folder) obj).getName() + " 进行杀毒");
                obj.killDu();
            } else {
                if (obj instanceof ImgFile) System.out.println("--------对文件：" + ((ImgFile) obj).getName() + " 进行杀毒");
                else System.out.println("--------对文件：" + ((TextFile) obj).getName() + " 进行杀毒");

            }
        });


    }
}
