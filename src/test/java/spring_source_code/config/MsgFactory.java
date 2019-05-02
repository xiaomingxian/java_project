package spring_source_code.config;

import org.springframework.beans.factory.FactoryBean;
import pojo.redis.Msg;

public class MsgFactory implements FactoryBean<Msg> {
    @Override
    public Msg getObject() throws Exception {
        return new Msg("1", "factoryBean-redisMsg", "msg....");
    }

    @Override
    public Class<?> getObjectType() {
        return Msg.class;
    }

    /**
     * true 单实例
     *
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
