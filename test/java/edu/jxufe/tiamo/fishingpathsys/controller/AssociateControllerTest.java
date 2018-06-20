package edu.jxufe.tiamo.fishingpathsys.controller;

import base.BaseControllerTest;
import edu.jxufe.tiamo.util.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AssociateControllerTest extends BaseControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext contextConfigLocation;

    @Before()
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(contextConfigLocation).build();
    }


    @Test
    public void publishDynamicState() {
        Logger.Log.info(this.getClass().getName()+": publishDynamicState: "+"1"+" Junit测试");
        try{
            String responseString = mockMvc.perform(
                    post("/publishDynamicState")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("userId","1")
                            .param("content","Junit测试")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"publishDynamicState: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"publishDynamicStateTest:"+ex);
        }
    }

    @Test
    public void publishDynamicStateComment() {
        Logger.Log.info(this.getClass().getName()+": publishDynamicStateComment: "+"1 "+" Junit测试 " +" 6");
        try{
            String responseString = mockMvc.perform(
                    post("/publishDynamicStateComment")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("userId","1")
                            .param("content","Junit测试")
                            .param("dynamicStateId","6")

            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"publishDynamicStateComment: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"publishDynamicStateComment:"+ex);
        }
    }

    @Test
    public void publishDynamicStateLike() {
        Logger.Log.info(this.getClass().getName()+": publishDynamicStateLike "+"1 " +" 1");
        try{
            String responseString = mockMvc.perform(
                    post("/publishDynamicStateLike")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("userId","1")
                            .param("dynamicStateId","6")

            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"publishDynamicStateLike: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"publishDynamicStateLike:"+ex);
        }
    }
}