package edu.jxufe.tiamo.fishingpathsys.service.impl;

import edu.jxufe.tiamo.fishingpathsys.dao.CourseDao;
import edu.jxufe.tiamo.fishingpathsys.dao.CourseSectionDao;
import edu.jxufe.tiamo.fishingpathsys.dao.StaffDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Course;
import edu.jxufe.tiamo.fishingpathsys.domain.CourseLearningRecordDTO.CourseDTO;
import edu.jxufe.tiamo.fishingpathsys.domain.CourseLearningRecordDTO.LearningRecordDTO;
import edu.jxufe.tiamo.fishingpathsys.domain.CourseSection;
import edu.jxufe.tiamo.fishingpathsys.domain.LearningRecord;
import edu.jxufe.tiamo.fishingpathsys.domain.Staff;
import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import edu.jxufe.tiamo.fishingpathsys.service.CourseService;
import edu.jxufe.tiamo.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;
    private CourseSectionDao courseSectionDao;
    private StaffDao staffDao;

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void setCourseSectionDao(CourseSectionDao courseSectionDao) {
        this.courseSectionDao = courseSectionDao;
    }

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
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

    @Override
    public List<CourseDTO> getAllCourseDTOs() {
        try{
            List<Course> courseList = courseDao.findAll(Course.class);
            return getCourseDTOsForCourses(courseList);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("获取课程信息时出现异常，请通知管理员！");
        }
    }

    @Override
    public List<CourseDTO> findCourseDTOsByPage(int pageNo, int pageSize) {
        List<Course> courseList = courseDao.findCoursesByPage(pageNo, pageSize);
        return getCourseDTOsForCourses(courseList);
    }

    private List<CourseDTO> getCourseDTOsForCourses(List<Course> courseList){
        List<CourseDTO> courseDTOList = new ArrayList<>();
        long staffsLength = staffDao.findCount(Staff.class);
        for (Course course : courseList) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setCourse(course);
            List<LearningRecordDTO> learningRecordDTOList = new ArrayList<>();
            for (LearningRecord learningRecord : course.getLearningRecordList()) {
                LearningRecordDTO learningRecordDTO = new LearningRecordDTO();
                learningRecordDTO.setLearningRecord(learningRecord);
                learningRecordDTO.setLearningPath(learningRecord.getLearningPath());
                learningRecordDTOList.add(learningRecordDTO);
            }
            courseDTO.setCompleteness((learningRecordDTOList.size()*100)/(int)staffsLength);
            courseDTO.setLearningRecordDTOList(learningRecordDTOList);
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }
}
