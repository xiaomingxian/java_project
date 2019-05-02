package spring_source_code.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //annotationMetadata  可以获取有@import修饰的类上的其他注解信息
        return new String[]{"pojo.UserExample", "pojo.UserMessage"};
    }
}
