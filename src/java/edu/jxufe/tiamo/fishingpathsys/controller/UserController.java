package edu.jxufe.tiamo.fishingpathsys.controller;


import com.sun.corba.se.spi.ior.ObjectKey;
import edu.jxufe.tiamo.fishingpathsys.controller.BaseController;
import edu.jxufe.tiamo.fishingpathsys.domain.User;
import edu.jxufe.tiamo.fishingpathsys.service.UserService;
import edu.jxufe.tiamo.util.Code;
import edu.jxufe.tiamo.util.Logger;
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
    @GetMapping(value = "/sendVerificationCode")
    public String sendVerificationCode(String telephone){
        Logger.Log.info(this.getClass().getName()+": verifyVerificationCode "+telephone);
        if(userService.getUserByTelephone(telephone)!=null){
            return Code.isExist;
        }
        return userService.sendVerificationCode(telephone);
    }

    @ResponseBody
    @GetMapping(value = "/verifyVerificationCode")
    public Code.jiguangReturnResult verifyVerificationCode(String msgId, String authCode){
        Logger.Log.info(this.getClass().getName()+": verifyVerificationCode "+msgId);
        return userService.verifyVerificationCode(msgId,authCode);
    }

    @ResponseBody
    @PostMapping(value = "/userLogin")
    public Object userLogin(String telephone,String password){
        Logger.Log.info(this.getClass().getName()+": userLogin "+telephone+" "+password);
        return userService.userLogin(telephone,password);
    }

    @ResponseBody
    @GetMapping(value = "/getAllDynamicStatesByEnterpriseId")
    public Object getAllDynamicStatesByEnterpriseId(Short enterpriseId){
        Logger.Log.info(this.getClass().getName()+": getAllDynamicStatesByEnterpriseId "+enterpriseId);
        return userService.getAllDynamicStatesByEnterpriseId(enterpriseId);
    }
}
