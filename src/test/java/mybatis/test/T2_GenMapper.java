package mybatis.test;

import com.google.common.collect.HashBasedTable;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.util.Hashtable;
import java.util.Vector;

public class T2_GenMapper {


    @Test
    public void t1() throws Exception {
        String pattern = "mybatis/test/*.class";
        ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
        Resource[] source = resourceLoader.getResources(pattern);
        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourceLoader);

        for (Resource resource : source) {
            if (resource.isReadable()) {
                MetadataReader reader = readerFactory.getMetadataReader(resource);
                String className = reader.getClassMetadata().getClassName();
                System.out.println("---readable:" + className);
            }
        }

        Vector v=null;
        Hashtable t=null;

    }

}
