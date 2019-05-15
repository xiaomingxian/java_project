package mybatis.test;

import mybatis.config.MyBatisConfig;
import mybatis.pojo.TableMsg;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class T2_GenMapper {
    /**
     * 将类名小写作为key   将类信息与mapper信息封装起来
     * <p>
     * <p>
     * <p>
     * 增    解析request参数，删选出实体有的字段   封装成键值对格式  反射进行调用
     * 删    通过主键[参数传入]  反射进行调用
     * 改    通过主键[参数传入]   解析request参数，删选出实体有的字段-- 反射进行调用
     * 查    解析request参数，删选出实体有的字段，封装成equals条件   解析特定参数封装成分页条件
     */

    //初始化容器
    private static ApplicationContext applicationContextAll = new AnnotationConfigApplicationContext(MyBatisConfig.class);
    //mapeer所在位置描述
    private static String pattern = "classpath:mybatis/mapper/*.class";
    //类集合
    private static Set<Class<?>> classes = new LinkedHashSet<Class<?>>();

    Map map = new HashMap();

    /**
     * 包扫描
     *
     * @throws Exception
     */
    @Test
    public void addClasses() throws Exception {

        //用于解析资源文件的策略接口，其特殊的地方在于，它应该提供带有*号这种通配符的资源路径。
        ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
        Resource[] source = resourceLoader.getResources(pattern);
        //此处通过asm将class文件读取成元数据模型
        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourceLoader);

        for (Resource resource : source) {
            if (resource.isReadable()) {
                MetadataReader reader = readerFactory.getMetadataReader(resource);
                String className = reader.getClassMetadata().getClassName();
                //System.out.println("----->类信息: " + className);
                classes.add(Class.forName(className));
            }
        }
        System.out.println("------->添加类信息成功");

        //判断类是否是某个类或者某个类/接口的子类或者实现类
        //以Type的形式返回本类直接实现的接口.这样就包含了泛型参数信息
        classes.stream().filter(Mapper.class::isAssignableFrom).forEach(cl -> {
            Type[] type = cl.getGenericInterfaces();
            //利用ParameterizedType获取java泛型的参数类型   type[0] 一个接口上只有一个泛型
            if (type.length > 0 && type[0] instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType) type[0]).getActualTypeArguments();//泛型
                if (p.length > 0) {
                    Class<?> t = (Class<?>) p[0];
                    String request_url = t.getSimpleName().toLowerCase();//实体类名小写
                    //获取表明---获取注解的值
                    String tableName = getTableName(t);
                    List<String> columnNames = getColums(t);
                    //从容器中获取生成的代理类mapper信息
                    Mapper mapper = (Mapper) MyBatisConfig.getApplicationContext().getBean(cl);

                    //封装表明(作为请求参数)--表对应的实体类类信息--表明(注解)--属性信息--mapper类

                    map.put(request_url, new TableMsg(request_url, t, tableName, columnNames, mapper));
                }
            }
        });

        System.out.println("------>初始化完毕");
        System.out.println(map);


    }

    //获取实体类的所有属性---@Transient忽略----加了@Colum以他为准
    private List<String> getColums(Class<?> clazz) {
        //字段集合
        ArrayList<String> list = new ArrayList<>();

        Field[] declaredFields = clazz.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field fileld = declaredFields[i];
            Column column = fileld.getAnnotation(Column.class);
            Transient trans = fileld.getAnnotation(Transient.class);

            if (trans != null) continue;//在循环
            if (column == null) list.add(fileld.getName());//没加注解就以去属性名
            else list.add(column.name());//加了注解就取注解名

        }
        return list;
    }


    //获取注解标注的表明----如果没有注解就获取实体类的类名小写
    private String getTableName(Class<?> t) {
        Table annotation = t.getAnnotation(Table.class);
        if (annotation == null) return t.getSimpleName().toLowerCase();
        String name = annotation.name();
        return name;
    }


}
