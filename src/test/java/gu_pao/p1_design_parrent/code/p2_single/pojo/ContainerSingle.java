package gu_pao.p1_design_parrent.code.p2_single.pojo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerSingle {
    private ContainerSingle() {
    }


    //ConcurrentHashMap 线程安全：保证了put安全
    private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>();


    //getBean创建对象不安全
    public static Object getBean(String className) {

        synchronized (ioc) {

            if (!ioc.containsKey(className)) {
                Object obj = null;
                try {
                    obj = Class.forName(className).newInstance();
                    ioc.put(className, obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            } else {
                return ioc.get(className);
            }
        }


    }

}
