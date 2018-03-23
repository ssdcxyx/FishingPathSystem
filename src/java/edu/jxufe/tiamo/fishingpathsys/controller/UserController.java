package edu.jxufe.tiamo.fishingpathsys.controller;


import edu.jxufe.tiamo.fishingpathsys.service.UserService;
import edu.jxufe.tiamo.util.Code;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping(value = "userLogin")
    public Object userLogin(String username,String password){
        return userService.userLogin(username,password);
    }

    @ResponseBody
    @GetMapping(value = "sendVerificationCode")
    public String sendVerificationCode(String telephone){
        if(userService.getUserByTelephone(telephone)!=null){
            return Code.isExist;
        }
        return userService.sendVerificationCode(telephone);
    }

    @ResponseBody
    @GetMapping(value = "verifyVerificationCode")
    public Code.jiguangReturnResult verifyVerificationCode(String msgId, String authCode){
        return userService.verifyVerificationCode(msgId,authCode);
    }

    @ResponseBody
    @PostMapping(value = "userRegister")
    public Object userRegister(String userName,String telephone,String password,String type){
        if(userService.getUserByTelephone(telephone)!=null){
            return Code.jiguangReturnResult.REPEAT;
        }
        return userService.userRegister(userName, telephone, password, type);
    }

    @ResponseBody
    @GetMapping(value = "getUserByTelephone")
    public Object getUserByTelephone(String telephone){
        return userService.getUserByTelephone(telephone);
    }
}
