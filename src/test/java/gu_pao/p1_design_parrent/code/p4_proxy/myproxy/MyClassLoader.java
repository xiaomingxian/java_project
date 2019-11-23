package gu_pao.p1_design_parrent.code.p4_proxy.myproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader {


    private File file;

    public MyClassLoader() {
        //此处将本包作为加载类的文件包
        String path = MyClassLoader.class.getResource("").getPath();
        file = new File(path);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //类的全限定名
        String className = MyClassLoader.class.getPackage().getName() + "." + name;
        try {

            //文件已经提前写入.java格式并编译成.class
            File classFile = new File(this.file, name + ".class");
            if (classFile.exists()) {
                FileInputStream inputStream = null;
                ByteArrayOutputStream byteArrayOutputStream = null;
                try {
                    //
                    inputStream = new FileInputStream(classFile);
                    byteArrayOutputStream = new ByteArrayOutputStream();

                    byte[] bytes = new byte[1024];
                    int len;
                    while ((len = inputStream.read(bytes)) != -1) {
                        byteArrayOutputStream.write(bytes, 0, len);
                    }

                    return defineClass(className, byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    inputStream.close();
                    byteArrayOutputStream.close();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
