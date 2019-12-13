package springx.webmvc.servlet;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HandlerAdapter
 */
public class HandlerAdapterX {
    public final boolean support() {
        return true;
    }

    /**
     * @param request
     * @param response
     * @param hadler   其实就是方法(被HandlerMapping包装的方法)
     * @return
     * @throws Exception
     */

    ModelAndViewX handle(HttpServletRequest request, HttpServletResponse response, Object hadler)  {//throws Exception


        return null;

    }




}
