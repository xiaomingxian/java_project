package springx.aop;

import springx.aop.support.AdvisedSupportX;

public class CglibDynamicProxyX implements AopProxyX {

    AdvisedSupportX config;

    public CglibDynamicProxyX() {
    }

    public CglibDynamicProxyX(AdvisedSupportX config) {
        this.config = config;
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }
}
