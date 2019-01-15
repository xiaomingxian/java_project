package controller.tincymce;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("tincymce")
public class TincymceController {


    @RequestMapping("insert")
    public String insert(String value) {

        return value;
    }

    @RequestMapping("report")
    public String report() {

        return "/page/vue_test/report";
    }


}
