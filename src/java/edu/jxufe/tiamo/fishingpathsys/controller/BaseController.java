package edu.jxufe.tiamo.fishingpathsys.controller;

import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.HashMap;
import java.util.Map;

public class BaseController extends DispatcherServlet{

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Object exp(Exception ex){
        Map<String,String> map = new HashMap<>();
        map.put("exception",ex.getMessage());
        return map;
    }

    @Override
    protected void noHandlerFound(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        response.sendRedirect(request.getContextPath() + "/notFound");
    }

}
