package springx.webmvc.servlet;

import lombok.Data;

import java.util.Map;

@Data
public class ModelAndViewX {


    private String viewName;

    private Map<String, ?> model;


    public ModelAndViewX(String viewName) {
        this.viewName = viewName;
    }

    public ModelAndViewX(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public ModelAndViewX() {
    }
}
