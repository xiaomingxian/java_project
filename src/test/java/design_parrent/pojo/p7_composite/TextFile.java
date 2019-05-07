package design_parrent.pojo.p7_composite;

import lombok.Data;

@Data
public class TextFile implements AbstractFile {
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    @Override
    public void add(AbstractFile abstractFile) {
        System.out.println("----此功能属于文件夹操作");
    }

    @Override
    public void del(AbstractFile abstractFile) {
        System.out.println("----此功能属于文件夹操作");
    }

    @Override
    public AbstractFile get(int index) {
        System.out.println("----此功能属于文件夹操作");
        return null;
    }

    @Override
    public void killDu() {
        System.out.println("---->文本文件执行杀毒操作");
    }
}
