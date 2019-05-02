package spring_source_code.pojo;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Data
@Component
public class Cat implements InitializingBean, DisposableBean {
    public Cat() {
        System.out.println("----------->cat 构造方法创建");
    }

    private String name;
    private String color;

    /**
     * 在容器关闭的时候进行调用
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("---------->cat 销毁");

    }

    /**
     * 在属性注入完毕后执行
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("---------->cat 属性设置完毕后执行");
    }
}
