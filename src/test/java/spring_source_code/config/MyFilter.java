package spring_source_code.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyFilter implements TypeFilter {
    /**
     * @param metadataReader        当前类信息
     * @param metadataReaderFactory 类工厂可以获得其他类信息
     * @return true 表示匹配成功
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        Resource resource = metadataReader.getResource();


        System.out.println("====>annotationMetadata:" + annotationMetadata);
        System.out.println("====>classMetadata:" + classMetadata.getClassName());
        System.out.println("====>resource:" + resource);

        if (classMetadata.getClassName().contains("ao")) {
            return true;
        }
        return false;
    }
}
