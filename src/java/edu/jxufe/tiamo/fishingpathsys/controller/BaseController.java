package edu.jxufe.tiamo.fishingpathsys.controller;

import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Object exp(Exception ex){
        Map<String,String> map = new HashMap<>();
        map.put("exception",ex.getMessage());
        return map;
    }
}
