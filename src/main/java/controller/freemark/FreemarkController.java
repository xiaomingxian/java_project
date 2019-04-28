package controller.freemark;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("freemark")
public class FreemarkController {


    @RequestMapping("getPage")
    public ModelAndView getPage() {
        ModelAndView mv = new ModelAndView("getPage");
        return mv;
    }

}
