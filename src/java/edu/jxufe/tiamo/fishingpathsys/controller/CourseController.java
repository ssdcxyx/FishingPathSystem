package edu.jxufe.tiamo.fishingpathsys.controller;

import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import edu.jxufe.tiamo.fishingpathsys.service.CourseService;
import edu.jxufe.tiamo.util.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class CourseController extends BaseController {

    @Resource
    private CourseService courseService;


    @ResponseBody
    @GetMapping(value = "/getAllCourses")
    public Object getAllCourses(){
        Logger.Log.info(this.getClass().getName()+"：getAllCourses ");
        return courseService.getAllCourses();
    }

    @ResponseBody
    @GetMapping(value = "/findCoursesByPage")
    public Object findCoursesByPage(Integer pageNo,Integer pageSize){
        Logger.Log.info(this.getClass().getName()+": findCoursesByPage "+pageNo+" "+pageSize);
        return courseService.findCoursesByPage(pageNo, pageSize);
    }

    @ResponseBody
    @GetMapping(value = "/findCoursesLength")
    public Object findCoursesLength(){
        Logger.Log.info(this.getClass().getName()+": findCoursesLength ");
        return courseService.findCoursesLength();
    }

    @ResponseBody
    @GetMapping(value = "/findCourseByCourseId")
    public Object findCourseByCourseId(Short courseId){
        Logger.Log.info(this.getClass().getName()+": findCourseByCourseId " + courseId);
        return courseService.findCourseByCourseId(courseId);
    }

    @ResponseBody
    @GetMapping(value = "/findCourseSectionByCourseSectionId")
    public Object findCourseSectionByCourseSectionId(Short courseSectionId){
        Logger.Log.info(this.getClass().getName()+": findCourseSectionByCourseSectionId " + courseSectionId);
        return courseService.findCourseSectionByCourseSectionId(courseSectionId);
    }
}
