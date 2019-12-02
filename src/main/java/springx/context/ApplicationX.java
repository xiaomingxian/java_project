package springx.context;

import springx.beans.BeanFactoryX;
import springx.beans.support.DefaultListableBeanFactoryX;

/**
 * AnnotationContext...
 * XmlContext...等等
 */
public class ApplicationX extends DefaultListableBeanFactoryX implements BeanFactoryX {
    @Override
    public Object getBean(String className) {
        return null;
    }
}
