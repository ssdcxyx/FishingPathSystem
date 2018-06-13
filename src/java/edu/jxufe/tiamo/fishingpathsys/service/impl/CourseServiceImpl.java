package edu.jxufe.tiamo.fishingpathsys.service.impl;

import edu.jxufe.tiamo.fishingpathsys.dao.CourseDao;
import edu.jxufe.tiamo.fishingpathsys.dao.CourseSectionDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Course;
import edu.jxufe.tiamo.fishingpathsys.domain.CourseSection;
import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import edu.jxufe.tiamo.fishingpathsys.service.CourseService;
import edu.jxufe.tiamo.util.ListUtil;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;
    private CourseSectionDao courseSectionDao;

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void setCourseSectionDao(CourseSectionDao courseSectionDao) {
        this.courseSectionDao = courseSectionDao;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.findAll(Course.class);
    }

    @Override
    public List<Course> findCoursesByPage(int pageNo, int pageSize) {
        try{
            return courseDao.findCoursesByPage(pageNo, pageSize);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("获取课程信息时出现异常，请通知管理员！");
        }
    }

    @Override
    public long findCoursesLength() {
        try{
            return courseDao.findCount(Course.class);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("获取课程信息时出现异常，请通知管理员！");
        }
    }

    @Override
    public Course findCourseByCourseId(Short courseId) {
        try{
            return new ListUtil<Course>().getFirst(courseDao.findCourseByCourseId(courseId));
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("获取课程信息时出现异常，请通知管理员！");
        }
    }

    @Override
    public CourseSection findCourseSectionByCourseSectionId(Short courseSectionId) {
        try{
            return courseSectionDao.get(CourseSection.class,courseSectionId);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("获取课程信息时出现异常，请通知管理员！");
        }
    }
}
