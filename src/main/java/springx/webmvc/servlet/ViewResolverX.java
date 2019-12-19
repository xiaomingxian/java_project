package springx.webmvc.servlet;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Locale;

public class ViewResolverX {


    /**
     * 默认后缀
     */
    private String DEFAULT_SUFFIX=".html";

    private File templateRootDir;

    public ViewResolverX() {
    }

    public ViewResolverX(String templateRoot) {

        String file = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        templateRootDir = new File(file);
    }

    public ViewX resolverViewName(String viewName, Locale locale) {//locale国际化

        if (StringUtils.isBlank(viewName)) return null;

        viewName=viewName.endsWith(DEFAULT_SUFFIX)?viewName:viewName+DEFAULT_SUFFIX;
        //
        return null;
    }
}
