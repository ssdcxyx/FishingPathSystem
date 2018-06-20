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

public class StaffControllerTest extends BaseControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext contextConfigLocation;

    @Before()
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(contextConfigLocation).build();
    }


    @Test
    public void getStaffsByEnterpriseId() {
        Logger.Log.info(this.getClass().getName()+": getStaffsByEnterpriseId "+"1");
        try{
            String responseString = mockMvc.perform(
                    get("/getStaffsByEnterpriseId")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("enterpriseId","1")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+": getStaffsByEnterpriseId "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"getStaffsByEnterpriseId:"+ex);
        }
    }

    @Test
    public void getAnnouncementsByEnterpriseIdAndDepartmentId() {
        Logger.Log.info(this.getClass().getName()+": getAnnouncementsByEnterpriseIdAndDepartmentId "+"1 " +"1 ");
        try{
            String responseString = mockMvc.perform(
                    get("/getAnnouncementsByEnterpriseIdAndDepartmentId")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("enterpriseId","1")
                            .param("departmentId","1")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+": getAnnouncementsByEnterpriseIdAndDepartmentId "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"getAnnouncementsByEnterpriseIdAndDepartmentId:"+ex);
        }
    }

    @Test
    public void storeLearningRecord() {
        Logger.Log.info(this.getClass().getName()+": storeLearningRecord "+"1 " +"1 " + "1");
        try{
            String responseString = mockMvc.perform(
                    post("/storeLearningRecord")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("staffId","1")
                            .param("courseId","1")
                            .param("courseSectionId","1")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+": storeLearningRecord "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"storeLearningRecord:"+ex);
        }
    }

    @Test
    public void getLearningPathByStaffId() {
        Logger.Log.info(this.getClass().getName()+": getLearningPathByStaffId "+"1 " );
        try{
            String responseString = mockMvc.perform(
                    get("/getLearningPathByStaffId")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("staffId","1")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+": getLearningPathByStaffId "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"getLearningPathByStaffId:"+ex);
        }
    }

    @Test
    public void getAllStaffsByEnterpriseIdAndDepartmentId() {
        Logger.Log.info(this.getClass().getName()+": getAllStaffsByEnterpriseIdAndDepartmentId "+"1 "+"1 "+" 1" );
        try{
            String responseString = mockMvc.perform(
                    get("/getAllStaffsByEnterpriseIdAndDepartmentId")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("staffId","1")
                            .param("departmentId","1")
                            .param("postTypeId","1")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+": getAllStaffsByEnterpriseIdAndDepartmentId "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"getAllStaffsByEnterpriseIdAndDepartmentId:"+ex);
        }
    }

    @Test
    public void getAllStaffsByEnterpriseIdAndDepartmentId1() {
    }
}