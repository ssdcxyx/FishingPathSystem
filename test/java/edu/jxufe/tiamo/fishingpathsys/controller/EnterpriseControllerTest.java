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

public class EnterpriseControllerTest extends BaseControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext contextConfigLocation;

    @Before()
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(contextConfigLocation).build();
    }

    @Test
    public void enterpriseRegister() {
        Logger.Log.info(this.getClass().getName()+": enterpriseRegister: "+"18379190862"+" 1234567789");
        try{
            String responseString = mockMvc.perform(
                    post("/enterpriseRegister")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("telephone","18379190862")
                            .param("password","1234567789")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"enterpriseRegister: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"enterpriseRegister:"+ex);
        }
    }

    @Test
    public void getAllEnterpriseTypes() {
        Logger.Log.info(this.getClass().getName()+": getAllEnterpriseTypes: ");
        try{
            String responseString = mockMvc.perform(
                    get("/getAllEnterpriseTypes")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"getAllEnterpriseTypes: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"getAllEnterpriseTypes:"+ex);
        }
    }

    @Test
    public void getAllDepartments() {
        Logger.Log.info(this.getClass().getName()+": getAllDepartments: ");
        try{
            String responseString = mockMvc.perform(
                    get("/getAllDepartments")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"getAllDepartments: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"getAllDepartments:"+ex);
        }
    }

    @Test
    public void getAllPostTypes() {
        Logger.Log.info(this.getClass().getName()+": getAllPostTypes: ");
        try{
            String responseString = mockMvc.perform(
                    get("/getAllPostTypes")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"getAllPostTypes: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"getAllPostTypes:"+ex);
        }
    }

    @Test
    public void updateEnterpriseInfo() {
        Logger.Log.info(this.getClass().getName()+": updateEnterpriseInfo: "+ "1 "+ "1 "+ "1 "+ "1 "+ "1 "+ "1 ");
        try{
            String responseString = mockMvc.perform(
                    post("/updateEnterpriseInfo")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("id","1")
                            .param("name","1")
                            .param("enterpriseTypeId","1")
                            .param("description","1")
                            .param("linkMan","1")
                            .param("telephone","1")

            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"updateEnterpriseInfo: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"updateEnterpriseInfo:"+ex);
        }
    }

    @Test
    public void publishAnnouncement() {
        Logger.Log.info(this.getClass().getName()+": publishAnnouncement: "+"1 "+"1 "+"1 "+"1 ");
        try{
            String responseString = mockMvc.perform(
                    post("/publishAnnouncement")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("title","1")
                            .param("information","1")
                            .param("enterpriseId","1")
                            .param("departmentId","1")

            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"publishAnnouncement: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"publishAnnouncement:"+ex);
        }
    }

    @Test
    public void getAnnouncementsByEnterpriseId() {
        Logger.Log.info(this.getClass().getName()+": getAnnouncementsByEnterpriseId: "+"1 ");
        try{
            String responseString = mockMvc.perform(
                    get("/getAnnouncementsByEnterpriseId")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("enterpriseId","1")

            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"getAnnouncementsByEnterpriseId: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"getAnnouncementsByEnterpriseId:"+ex);
        }
    }

    @Test
    public void getEnterpriseDTOByEnterpriseId() {
        Logger.Log.info(this.getClass().getName()+": getEnterpriseDTOByEnterpriseId: "+"1 ");
        try{
            String responseString = mockMvc.perform(
                    get("/getEnterpriseDTOByEnterpriseId")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("enterpriseId","1")

            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"getEnterpriseDTOByEnterpriseId: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"getEnterpriseDTOByEnterpriseId:"+ex);
        }
    }

    @Test
    public void getEnterpriseByEnterpriseId() {
        Logger.Log.info(this.getClass().getName()+": getEnterpriseByEnterpriseId: "+"1 ");
        try{
            String responseString = mockMvc.perform(
                    get("/getEnterpriseByEnterpriseId")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("enterpriseId","1")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"getEnterpriseByEnterpriseId: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"getEnterpriseByEnterpriseId:"+ex);
        }
    }
}