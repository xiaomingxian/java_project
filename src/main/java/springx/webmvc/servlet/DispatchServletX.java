package springx.webmvc.servlet;

import springx.annotation.ControllerX;
import springx.annotation.RequestMappingX;
import springx.context.ApplicationContextX;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DispatchServletX extends HttpServlet {


    private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    ApplicationContextX applicationContextX;

    public DispatchServletX() {
    }

    //HandlerMapping容器
    List<HandlerMappingX> handlerMappingXES = new ArrayList<>();

    //HandlerMapping与HandlerAdapter的关系
    Map<HandlerMappingX, HandlerAdapterX> handlerAdapterXMap = new HashMap<HandlerMappingX, HandlerAdapterX>();

    //ViewResolver容器
    List<ViewResolverX> viewResolverXES = new ArrayList<ViewResolverX>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        try {
            this.doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception,Details:\r\n" + e.toString());
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        //1 通过从request中拿到URL去匹配一个HandlerMapping
        HandlerMappingX handler = getHandlerMapping(req);
        if (handler == null) {
            //ModelAndView 404
            processDispatchResult(req,resp,new ModelAndViewX("404"));
            return;
        }
        //2 准备调用前的参数
        HandlerAdapterX handlerAdapterX = getHandlerAdapter(handler);

        //3 真正的调用方法  返回modelAndView[存储了要传到页面的值，和页面模版的名称]
        ModelAndViewX modelAndViewx = null;
        try {
            modelAndViewx = handlerAdapterX.handle(req, resp, handlerAdapterX);//第三个参数是具体执行方法(此处做简化处理)
        } catch (Exception e) {
            e.printStackTrace();
        }

        //解析ModelAndView去真正的返回数据渲染页面(真正的输出)
        processDispatchResult(req, resp, modelAndViewx);
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, ModelAndViewX modelAndViewx) {
        //把ModelAndView变成一个Html,OutputStream,Json,Freemark,veolcity
        //由ContextType决定
        if (null == modelAndViewx) return;
        if (this.viewResolverXES.isEmpty()) return;

        for (ViewResolverX viewResolverX : this.viewResolverXES) {

            ViewX viewX = viewResolverX.resolverViewName(modelAndViewx.getViewName(), null);
            viewX.render(modelAndViewx.getModel(), req, resp);
            return;//????
        }



    }

    private HandlerAdapterX getHandlerAdapter(HandlerMappingX handler) {
        if (this.handlerAdapterXMap.isEmpty()) {
            return null;
        }
        HandlerAdapterX handlerAdapterX = this.handlerAdapterXMap.get(handler);
        //判断是否支持
        if (handlerAdapterX.support()) {
            return handlerAdapterX;
        }


        return null;
    }

    /**
     * 通过request中的URL获取到RequestMapping
     */
    private HandlerMappingX getHandlerMapping(HttpServletRequest req) {
        if (this.handlerMappingXES.isEmpty()) return null;
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (HandlerMappingX handlerMappingX : this.handlerMappingXES) {

            try {
                Matcher matcher = handlerMappingX.getPattern().matcher(url);
                if (!matcher.matches()) continue;
                return handlerMappingX;
            } catch (Exception e) {
                log(e.toString());
            }

        }

        return null;
    }


    @Override
    public void init(ServletConfig config) {
        //super.init();
        //1 初始化ApplicationContext(读取配置文件/初始化容器)
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
        //1 多文件上传组件(略:未实现)
        initMultipartResolver(applicationContextX);
        //2 初始化本地语言环境(略:未实现)
        initLocalResolver(applicationContextX);
        //3 初始化模版处理器(略:未实现)
        initThemeResolver(applicationContextX);
        //4 handlerMapping  ok
        initHanderMapping(applicationContextX);
        //5 初始化参数适配器   ok
        initHanderAdapters(applicationContextX);
        //6 初始化异常拦截器
        initHanderExceptionResolvers(applicationContextX);
        //7 初始化视图预处理器
        initRequestToViewNameTranslator(applicationContextX);
        //8 初始化视图转换器
        initViewResolver(applicationContextX);
        //9 参数缓存器
        initFlashMapManager(applicationContextX);


    }

    private void initFlashMapManager(ApplicationContextX applicationContextX) {

    }

    private void initViewResolver(ApplicationContextX applicationContextX) {
        //拿到存放模版的跟目录
        String templateRoot = applicationContextX.getConfig().getProperty("templateRoot");
        //获取资源
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File file = new File(templateRootPath);
        for (File file1 : file.listFiles()) {

            //this.viewResolverXES.add(new ViewResolverX(templateRootPath));
            this.viewResolverXES.add(new ViewResolverX(file1.getAbsolutePath()));

        }
    }

    private void initRequestToViewNameTranslator(ApplicationContextX applicationContextX) {
    }

    private void initHanderExceptionResolvers(ApplicationContextX applicationContextX) {
    }

    private void initHanderAdapters(ApplicationContextX applicationContextX) {

        System.out.println("----------------------->>>9大组件初始5：handlerAdapter与HandlerMapping建立关系[一知半解](已实现：handlerAdapterXMap)");

        //https://www.jianshu.com/p/1ccd4b326cff
        //handlerAdapter与HandlerMapping建立关系
        for (HandlerMappingX handlerMappingX : this.handlerMappingXES) {
            this.handlerAdapterXMap.put(handlerMappingX, new HandlerAdapterX());
        }

    }

    private void initHanderMapping(ApplicationContextX applicationContextX) {

        System.out.println("----------------------->>>9大组件初始4：建立类与url映射(已实现：handlerMappingXES)");

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
                    if (!method.isAnnotationPresent(RequestMappingX.class)) continue;
                    RequestMappingX requestMappingX = method.getAnnotation(RequestMappingX.class);
                    String methodUrl = requestMappingX.value();
                    //replaceAll("\\*",".*")   //url的正则不必写的太严格 严格方式xxx.*  修改后xxx*
                    String url = ("/" + baseurl + "/" + methodUrl.replaceAll("\\*", ".*")).replace("/+", "/");
                    //缓存进容器{地址:方法}
                    handlerMappingXES.add(new HandlerMappingX(controller, method, Pattern.compile(url)));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    private void initThemeResolver(ApplicationContextX applicationContextX) {
        System.out.println("----------------------->>>9大组件初始3：初始化模版处理器(略)");
    }

    private void initLocalResolver(ApplicationContextX applicationContextX) {
        System.out.println("----------------------->>>9大组件初始2：初始化本地语言环境(略)");

    }

    private void initMultipartResolver(ApplicationContextX applicationContextX) {
        System.out.println("----------------------->>>9大组件初始1：多文件上传组件(略)");
    }
}
