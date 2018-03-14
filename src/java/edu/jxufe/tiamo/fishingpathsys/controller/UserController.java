package edu.jxufe.tiamo.fishingpathsys.controller;


import edu.jxufe.tiamo.fishingpathsys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @ResponseBody
    @GetMapping(value = "userLogin")
    public Object userLogin(String account,String password){
        return userService.userLogin(account,password);
    }

    @ResponseBody
    @GetMapping(value = "sendVerificationCode")
    public void sendVerificationCode(String telephone,String verificationCode) throws Exception{
        userService.sendVerificationCode(telephone,verificationCode);
    }
}
