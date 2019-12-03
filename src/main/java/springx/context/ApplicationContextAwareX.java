package springx.context;

import springx.context.support.AbstractApplicationContextX;

/**
 * 通过解藕方式获取IOC容器的顶层设计
 * 观察者模式：通过监听器去扫描所有类，只要实现了此接口，
 * 将自动调用 setApplicationContext 方法，从而将ioc容器注入到目标类中
 */
public interface ApplicationContextAwareX {
    void setApplicationContext(AbstractApplicationContextX applicationContext);
}
