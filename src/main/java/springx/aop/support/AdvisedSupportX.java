package springx.aop.support;

import lombok.Data;
import springx.aop.config.AopConfigX;

import java.util.List;


@Data
public class AdvisedSupportX {


    AopConfigX aopConfigX;
    private Class<?> targetClass;

    private Object target;


    public AdvisedSupportX() {
    }

    public AdvisedSupportX(AopConfigX aopConfigX) {
        this.aopConfigX = aopConfigX;
    }


    public List<Object> getInterceptorsAndDynamicMethodMatchers(){
        return null;
    }


    public boolean pointCutMatch() {

        return false;
    }
}
