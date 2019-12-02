package springx.context.support;


/**
 * IOC容器实现的顶层设计
 * AbstractApplicationContext：模版模式 所有的ApplicationContext都得遵循这一套
 */
public abstract class AbstractApplicationContextX {

    /**
     * protected 最少知道原则
     */
    protected abstract void refresh();

}
