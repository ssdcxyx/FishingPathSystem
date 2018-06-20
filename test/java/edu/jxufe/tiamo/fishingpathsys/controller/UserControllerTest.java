package edu.jxufe.tiamo.fishingpathsys.controller;

import base.BaseControllerTest;
import edu.jxufe.tiamo.fishingpathsys.service.UserService;
import edu.jxufe.tiamo.util.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UserControllerTest extends BaseControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext contextConfigLocation;

    @Before()
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(contextConfigLocation).build();
    }

    @Test
    public void sendVerificationCode() {
        Logger.Log.info(this.getClass().getName()+": sendVerificationCode "+" 13807066846");
        try{
            String responseString = mockMvc.perform(
                    get("/sendVerificationCode")   // 请求方式
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                    .param("telephone","18379190862")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+": sendVerificationCode "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"sendVerificationCode:"+ex);
        }
    }

    @Test
    public void verifyVerificationCode() {
        Logger.Log.info(this.getClass().getName()+": verifyVerificationCode "+"123456"+" 123456");
        try{
            String responseString = mockMvc.perform(
                    get("/verifyVerificationCode")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("msgId",null)
                            .param("authCode","123456")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+": verifyVerificationCode "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"verifyVerificationCode:"+ex);
        }
    }

    @Test
    public void userLogin() {
        Logger.Log.info(this.getClass().getName()+": userLogin "+"13807066846"+" 1234567789");
        try{
            String responseString = mockMvc.perform(
                    post("/userLogin")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("telephone","13807066846")
                            .param("password","1234567789")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+": userLogin "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"userLogin:"+ex);
        }
    }

    @Test
    public void getAllDynamicStatesByEnterpriseId() {
        Logger.Log.info(this.getClass().getName()+": getAllDynamicStatesByEnterpriseId "+"1");
        try{
            String responseString = mockMvc.perform(
                    post("/userLogin")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("enterpriseId","1")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+": getAllDynamicStatesByEnterpriseId "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"getAllDynamicStatesByEnterpriseId:"+ex);
        }

    }
}