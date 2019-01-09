package utils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import pojo.Person;

public class SerializeUtil {

    public static void main(String[] args) throws IOException {

        Person p = new Person();
        p.setName("tom");
        p.setSex("1");

        //byte[] serialize = serialize(p);
        //Object unserialize = unserialize(serialize);
        //
        //System.out.println(serialize);
        //System.out.println(unserialize);


        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("a.txt"));
            objectOutputStream.writeObject(p);
            objectOutputStream.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("a.txt"));
            Object o = objectInputStream.readObject();
            System.out.println(o);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //ObjectInputStream objectInputStream = new ObjectInputStream();

    }


    // 序列化
    public static byte[] serialize(Object object) {

        ObjectOutputStream oos = null;

        ByteArrayOutputStream baos = null;

        try {

            // 序列化

            baos = new ByteArrayOutputStream();

            //对象流输出到字节数组中返回
            oos = new ObjectOutputStream(baos);

            oos.writeObject(object);

            byte[] bytes = baos.toByteArray();

            return bytes;

        } catch (Exception e) {

        }

        return null;

    }

    // 反序列化
    public static Object unserialize(byte[] bytes) {

        ByteArrayInputStream bais = null;

        try {

            // 反序列化

            bais = new ByteArrayInputStream(bytes);

            //读取字节数组-对象流读取字节数组
            ObjectInputStream ois = new ObjectInputStream(bais);

            return ois.readObject();

        } catch (Exception e) {

        }

        return null;

    }


    private static List<Class> registerList = new ArrayList<Class>();

    public static ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {

        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.setReferences(false);
            for (int i = 0; i < registerList.size(); i++) {
                kryo.register(registerList.get(i));
            }
            return kryo;
        }
    };


    public static void init(Class class1) {
        registerList.add(class1);
    }

    public static void writeObjects(OutputStream os, Object[] objects) {
        Output op = new Output(os);
        try {
            kryos.get().writeClassAndObject(op, objects.length);
            for (Object object : objects) {
                kryos.get().writeClassAndObject(op, object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            op.close();
        }
    }

    public static Object[] readObjects(InputStream is) {
        try {
            Input input = new Input(is);
            int length = (Integer) kryos.get().readClassAndObject(input);
            Object[] objects = new Object[length];
            for (int i = 0; i < objects.length; i++) {
                objects[i] = kryos.get().readClassAndObject(input);
            }
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
