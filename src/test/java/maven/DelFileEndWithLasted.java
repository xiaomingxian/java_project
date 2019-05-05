package maven;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.File;

public class DelFileEndWithLasted {
    /**
     * spring-boot-starter-activemq-2.1.4.RELEASE.pom.lastUpdated存在类似与这样的文件说明下载失败-需要删除
     */



    @Test
    public void del() {

        String mac = "/Users/xxm/develop/maven/repository";
        String win = "E:\\develop\\maven\\repository";
        String work = "C:\\xxm\\dev\\maven\\MavenRepository";
        File file = new File(work);
        //        System.out.println(file.isDirectory());
        //需要使用递归的方法
        deleteFile(file);
    }

    //删除repository下的已lastUpdated结尾的文件，解决pom.xml文件报错的问题
    public void deleteFile(File file) {
        if (file.isDirectory()) {
            //是目录就遍历下面的文件
            File[] files = file.listFiles();
            for (File file2 : files) {
                deleteFile(file2);
            }
        } else {
            //不是目录就判断文件是否是以lastUpdated结尾,就删除该文件
            if (file.getName().endsWith(".lastUpdated")) {
                file.delete();
                System.out.println("删除文件：" + file.getName());
            }
        }
    }
}
