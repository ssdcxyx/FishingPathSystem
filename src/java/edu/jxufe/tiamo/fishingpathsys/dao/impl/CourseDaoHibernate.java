package edu.jxufe.tiamo.fishingpathsys.dao.impl;

import edu.jxufe.tiamo.common.dao.impl.BaseDaoHibernate;
import edu.jxufe.tiamo.fishingpathsys.dao.CourseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Course;

import java.util.List;

public class CourseDaoHibernate extends BaseDaoHibernate<Course> implements CourseDao {

    @Override
    public List<Course> findCoursesByPage(int pageNo, int pageSize) {
        String hql = "FROM "+Course.class.getSimpleName();
        List<Course> courseList = findByPage(hql,pageNo,pageSize);
        return courseList;
    }

    @Override
    public List<Course> findCourseByCourseId(Short courseId) {
        String hql = "FROM " + Course.class.getSimpleName() +" where id=?0";
        return find(hql,courseId);
    }
}
