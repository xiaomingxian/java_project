package springx.beans;

/**
 * 单例工厂的顶层设计
 * Bean工厂
 */
public interface BeanFactoryX {


    /**
     * 从IOC容器中获取对象
     *
     * @param className
     * @return
     */
    Object getBean(String className);
}
