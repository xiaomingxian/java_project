package gu_pao.p1_design_parrent.code.p6_strategy.mvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyDispatchServlet extends HttpServlet {


    private List<Handler> handlerMapping = new ArrayList<>();

    class Handler {
        String url;
        Method method;

        public Handler(String url, Method method) {
            this.url = url;
            this.method = method;
        }

        public Handler setUrl(String url) {
            this.url = url;
            return this;
        }

        public Handler setMethod(Method method) {
            this.method = method;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Method getMethod() {
            return method;
        }
    }

    @Override
    public void init() throws ServletException {

        //初始映射关系[地址<-->类+方法]
        try {
            //此处在框架中应该是先扫描 @Controller @RequestMapping 等rest相关的注解
            Class<?> memberControllerClass = MemberController.class;
            handlerMapping.add(new Handler("/member", memberControllerClass.getMethod("memberQuery", null)));


        } catch (Exception e) {
            e.printStackTrace();
        }

        //super.init();

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        mydispatch(req, resp);
    }

    private void mydispatch(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();

        Handler h = null;
        //进行url匹配
        for (Handler handler : handlerMapping) {
            if (requestURI.equals(handler.getUrl())) {
                h = handler;
                break;
            }
        }
        //执行相关方法
        try {
            Object rest = h.method.invoke(h, null);
            resp.getWriter().write(rest.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}