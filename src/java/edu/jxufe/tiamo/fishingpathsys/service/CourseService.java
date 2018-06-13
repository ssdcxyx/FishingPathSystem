package edu.jxufe.tiamo.fishingpathsys.service;

import edu.jxufe.tiamo.fishingpathsys.domain.Course;
import edu.jxufe.tiamo.fishingpathsys.domain.CourseSection;

import java.util.List;

public interface CourseService {

    // 获得所有课程信息
    List<Course> getAllCourses();

    // 分页获得课程信息
    List<Course> findCoursesByPage(int pageNo, int pageSize);

    // 获得所有课程的总数
    long findCoursesLength();

    // 根据课程id获得课程信息
    Course findCourseByCourseId(Short courseId);

    // 根据课程节id获得课程节信息
    CourseSection findCourseSectionByCourseSectionId(Short courseSectionId);
}
