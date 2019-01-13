package controller.tincymce;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tincymce")
public class TincymceController {
    @RequestMapping("insert")
    public String insert(String value) {

        return value;
    }


}
