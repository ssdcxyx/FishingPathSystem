package edu.jxufe.tiamo.fishingpathsys.controller;

import edu.jxufe.tiamo.fishingpathsys.service.AssociateService;
import edu.jxufe.tiamo.util.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class AssociateController extends BaseController{

    @Resource
    private AssociateService associateService;

    @ResponseBody
    @PostMapping(value = "/publishDynamicState")
    public Object publishDynamicState(Short userId, String content){
        Logger.Log.info(this.getClass().getName()+": publishDynamicState "+userId+ " " +content);
        return associateService.publishDynamicState(userId, content);
    }

    @ResponseBody
    @PostMapping(value = "/publishDynamicStateComment")
    public Object publishDynamicStateComment(Short userId, String content, Short dynamicStateId){
        Logger.Log.info(this.getClass().getName()+": publishDynamicStateComment "+userId + " " + content + " " +dynamicStateId);
        return associateService.publishDynamicStateComment(userId, content,dynamicStateId);
    }

    @ResponseBody
    @PostMapping(value = "/publishDynamicStateLike")
    public Object publishDynamicStateLike(Short userId, Short dynamicStateId){
        Logger.Log.info(this.getClass().getName()+": publishDynamicStateLike "+userId + " " +dynamicStateId);
        return associateService.publishDynamicStateLike(userId, dynamicStateId);
    }
}
