package springx.webmvc.servlet;

import org.python.antlr.ast.Str;
import springx.annotation.ControllerX;
import springx.annotation.RequestMappingX;
import springx.context.ApplicationContextX;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class DispatchServletX extends HttpServlet {


    private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    ApplicationContextX applicationContextX;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        doDispatcher(req, resp);
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) {


    }

    @Override
    public void init(ServletConfig config) {
        //super.init();
        //1 初始化ApplicationContext
        String initParameter = config.getInitParameter(CONTEXT_CONFIG_LOCATION);
        applicationContextX = new ApplicationContextX(initParameter);

        //2 初始化springmvc9大组件
        initStrategies(applicationContextX);

    }

    /**
     * 初始化策略
     *
     * @param applicationContextX
     */
    private void initStrategies(ApplicationContextX applicationContextX) {
        //1 多文件上传组件
        initMultipartResolver(applicationContextX);
        //2 初始化本地语言环境
        initLocalResolver(applicationContextX);
        //3 初始化模版处理器
        initThemeResolver(applicationContextX);
        //4 handlerMapping
        initHanderMapping(applicationContextX);
        //5 初始化参数适配器
        initHanderAdapters(applicationContextX);
        //6 初始化异常拦截器
        initHanderExceptionResolvers(applicationContextX);
        //7 初始化视图预处理器
        initRequestToViewNameTranslator(applicationContextX);
        //8 初始化视图转换器
        initViewResolver(applicationContextX);
        //9
        initFlashMapManager(applicationContextX);


    }

    private void initFlashMapManager(ApplicationContextX applicationContextX) {

    }

    private void initViewResolver(ApplicationContextX applicationContextX) {
    }

    private void initRequestToViewNameTranslator(ApplicationContextX applicationContextX) {
    }

    private void initHanderExceptionResolvers(ApplicationContextX applicationContextX) {
    }

    private void initHanderAdapters(ApplicationContextX applicationContextX) {
    }

    private void initHanderMapping(ApplicationContextX applicationContextX) {
        String[] beanDefinitionNames = applicationContextX.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            //建立类于URL的映射关系
            try {
                Object controller = applicationContextX.getBean(beanDefinitionName);
                //判断是不是Controller
                Class<?> clazz = controller.getClass();
                if (!clazz.isAnnotationPresent(ControllerX.class)) continue;
                //1 获取Controller 的url配置
                String baseurl = "";
                if (clazz.isAnnotationPresent(RequestMappingX.class)) {
                    RequestMappingX requestMappingX = clazz.getAnnotation(RequestMappingX.class);
                    baseurl = requestMappingX.value();
                }
                //2 获取method的url配置
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    //忽略没有加注解的方法[此处简化处理]
                    if (method.isAnnotationPresent(RequestMappingX.class)) continue;
                    RequestMappingX requestMappingX = method.getAnnotation(RequestMappingX.class);
                    String methodUrl= requestMappingX.value();
                    String url=("/"+baseurl+"/"+methodUrl).replace("/+","/");



                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    private void initThemeResolver(ApplicationContextX applicationContextX) {
    }

    private void initLocalResolver(ApplicationContextX applicationContextX) {

    }

    private void initMultipartResolver(ApplicationContextX applicationContextX) {

    }
}
