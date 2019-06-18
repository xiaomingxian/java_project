package jvm.test;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.Arrays;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * 解析类
 * 类：cafe baby 版本  常量池  方法  属性
 * 可以访问每一个节点[cafe baby 版本  常量池  方法  属性]
 */
public class T13_ParseClass extends ClassVisitor {

    public T13_ParseClass() {
        super(ASM4);
    }

    @Override
    public void visit(int version, int access, String name, String sinature, String superName, String[] interfaces) {

        System.out.println("版本: " + version + "，access:" + access + ",名称：" + name + ",sinature:" + sinature + ",superName:" + superName);
        System.out.println("接口：" + Arrays.toString(interfaces));
    }

    /**
     * 访问字段
     */
    @Override
    public FieldVisitor visitField(int i, String s, String s1, String s2, Object o) {

        System.out.println("访问字段：" + i + " " + s + " " + s1 + " " + s2 + " " + o);
        return null;
    }

    /**
     * 访问方法
     */
    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        System.out.println("访问字段：" + i + " " + s + " " + s1 + " " + s2 + " " + strings);

        return null;
    }

    /**
     *
     */
    @Override
    public void visitEnd() {
        System.out.println("访问结束");
    }

    public static void main(String[] args) throws Exception {
        T13_ParseClass t13_parseClass = new T13_ParseClass();

        ClassReader classReader = new ClassReader("java.lang.Runnable");

        classReader.accept(t13_parseClass, 0);
    }

}


