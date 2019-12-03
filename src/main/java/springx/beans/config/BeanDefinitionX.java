package springx.beans.config;


import lombok.Data;

@Data
public class BeanDefinitionX {
    private String beanClassName;
    private boolean lazyInit;
    private String factoryBeanName;
}
