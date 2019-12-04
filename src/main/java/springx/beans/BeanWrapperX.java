package springx.beans;

/**
 *
 */
public class BeanWrapperX {

    /**
     * 如果是单例直接获取对象
     * @return
     */
    public Object getWrapperInstance(){
        return null;
    }

    /**
     * 如果不是单例就获取类信息
     * @return
     */
    public  Class<?> getWrapperClass(){
        return null;
    }

}
