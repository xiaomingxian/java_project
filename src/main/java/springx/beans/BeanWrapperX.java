package springx.beans;

/**
 *BeanWrapper是spring提供的一个用来操作JavaBean属性的工具，使用它可以直接修改一个对象的属性
 */
public class BeanWrapperX {


    private Object wrapperInstance;
    private Class<?> wrapperClass;

    public BeanWrapperX() {
    }

    public BeanWrapperX(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }


    /**
     * 如果是单例直接获取对象
     * @return
     */
    public Object getWrapperInstance(){
        return this.wrapperInstance;
    }

    /**
     * 如果不是单例就获取类信息
     * @return
     */
    public  Class<?> getWrapperClass(){
        return this.wrapperInstance.getClass();
    }

}
