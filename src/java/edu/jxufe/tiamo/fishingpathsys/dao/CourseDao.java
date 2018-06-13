package edu.jxufe.tiamo.fishingpathsys.dao;

import edu.jxufe.tiamo.common.dao.BaseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Course;

import java.util.List;

public interface CourseDao extends BaseDao<Course>{

    List<Course> findCoursesByPage(int pageNo,int pageSize);

    List<Course> findCourseByCourseId(Short courseId);

}
