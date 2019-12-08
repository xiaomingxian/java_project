package springx.beans;

/**
 *
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
