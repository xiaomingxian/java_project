package controller.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.User;
import service.UserService;
import utils.MD5Util;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("pre")
public class ShiroLogin {

    @Autowired
    private UserService userService;


    @RequestMapping("index")
    public String index() {
        return "/index";
    }


    @RequestMapping("login")
    public String loginpage() {

        return "/page/pre_authorcation/login";
    }


    @RequestMapping("check")
    public String login(String name, String password) {

        System.out.println("----------login-------------");

        Subject subject = SecurityUtils.getSubject();
        //AuthenticationToken token = new UsernamePasswordToken(name, MD5Util.getPwd(password));
        AuthenticationToken token = new UsernamePasswordToken(name, (password));

        try {

            subject.login(token);
            Session session = subject.getSession();
            session.setTimeout(-1000L);
            //登录成功
            return "index";

        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidSessionException e) {
            e.printStackTrace();
        }
        //登录失败
        return "/page/pre_authorcation/login";

    }

    @RequestMapping("register")
    public String register() {


        return "/page/pre_authorcation/register";
    }

    @RequestMapping("insert")
    public String insert(String name, String password, String phone, String email) {

        User user = new User();
        user.setUserName(name);
        user.setId(UUID.randomUUID().toString().replace("-", ""));
        user.setPassword(MD5Util.getPwd(password));
        user.setPhone(phone);
        user.setEmail(email);
        user.setCreateTime(new Date());
        user.setUpdateTima(new Date());

        userService.register(user);


        return "/index";
    }

    @RequestMapping("isPermission")
    @ResponseBody
    public String isPermission() {
        Subject subject = SecurityUtils.getSubject();


        if (subject.isPermitted("ss")) {
            return "yi shou quan";
        } else {

            System.out.println("==============");
            return "weishouquan";
        }


    }


}
