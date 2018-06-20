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

public class CourseControllerTest extends BaseControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext contextConfigLocation;

    @Before()
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(contextConfigLocation).build();
    }


    @Test
    public void getAllCourses() {
        Logger.Log.info(this.getClass().getName()+": getAllCourses: ");
        try{
            String responseString = mockMvc.perform(
                    get("/getAllCourses")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"getAllCourses: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"getAllCourses:"+ex);
        }
    }

    @Test
    public void findCoursesByPage() {
        Logger.Log.info(this.getClass().getName()+": findCoursesByPage: "+ "1 " + "12");
        try{
            String responseString = mockMvc.perform(
                    get("/findCoursesByPage")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("pageNo","1")
                            .param("pageSize","12")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"findCoursesByPage: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"findCoursesByPage:"+ex);
        }
    }

    @Test
    public void findCoursesLength() {
        Logger.Log.info(this.getClass().getName()+": findCoursesLength: ");
        try{
            String responseString = mockMvc.perform(
                    get("/findCoursesLength")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"findCoursesLength: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"findCoursesLength:"+ex);
        }
    }

    @Test
    public void findCourseByCourseId() {
        Logger.Log.info(this.getClass().getName()+": findCourseByCourseId: " + 1);
        try{
            String responseString = mockMvc.perform(
                    get("/findCourseByCourseId")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("courseId","1")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"findCourseByCourseId: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"findCourseByCourseId:"+ex);
        }
    }

    @Test
    public void findCourseSectionByCourseSectionId() {
        Logger.Log.info(this.getClass().getName()+": findCourseSectionByCourseSectionId: " + 1);
        try{
            String responseString = mockMvc.perform(
                    get("/findCourseSectionByCourseSectionId")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("courseSectionId","1")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"courseSectionId: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"courseSectionId:"+ex);
        }
    }

    @Test
    public void getAllCourseDTOs() {
        Logger.Log.info(this.getClass().getName()+": getAllCourseDTOs: ");
        try{
            String responseString = mockMvc.perform(
                    get("/getAllCourseDTOs")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"getAllCourseDTOs: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"getAllCourseDTOs:"+ex);
        }
    }

    @Test
    public void findCourseDTOsByPage() {
        Logger.Log.info(this.getClass().getName()+": findCourseDTOsByPage: "+"1 " + "12 ");
        try{
            String responseString = mockMvc.perform(
                    get("/findCourseDTOsByPage")   // 请求方式
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)   // 数据格式
                            .param("pageNo","1")
                            .param("pageSize","12")
            )
                    .andExpect(MockMvcResultMatchers.status().isOk())   // 返回状态为200
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();   //将相应数据转化为字符串
            Logger.Log.info(this.getClass().getName()+"findCourseDTOsByPage: "+responseString);
        }catch (Exception ex){
            Logger.Log.error(this.getClass().getName()+"findCourseDTOsByPage:"+ex);
        }
    }
}