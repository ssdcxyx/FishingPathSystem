package edu.jxufe.tiamo.fishingpathsys.controller;

import edu.jxufe.tiamo.fishingpathsys.controller.BaseController;
import edu.jxufe.tiamo.fishingpathsys.domain.Enterprise;
import edu.jxufe.tiamo.fishingpathsys.domain.EnterpriseType;
import edu.jxufe.tiamo.fishingpathsys.service.EnterpriseService;
import edu.jxufe.tiamo.fishingpathsys.service.UserService;
import edu.jxufe.tiamo.util.Code;
import edu.jxufe.tiamo.util.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class EnterpriseController extends BaseController {

    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping(value = "/enterpriseRegister")
    public Object enterpriseRegister(String telephone,String password){
        Logger.Log.info(this.getClass().getName()+": enterpriseRegister "+telephone);
        if(userService.getUserByTelephone(telephone)!=null){
            return Code.jiguangReturnResult.REPEAT;
        }
        return enterpriseService.enterpriseRegister(telephone, password);
    }

    @ResponseBody
    @GetMapping(value = "/getAllEnterpriseTypes")
    public Object getAllEnterpriseTypes(){
        return enterpriseService.getAllEnterpriseTypes();
    }

    @ResponseBody
    @PostMapping(value = "/updateEnterpriseInfo")
    public Object updateEnterpriseInfo(Short id, String name, Short enterpriseTypeId,String description,String linkMan,String telephone){
        return enterpriseService.updateEnterpriseInfo(id, name, enterpriseTypeId, description, linkMan, telephone);
    }
}
