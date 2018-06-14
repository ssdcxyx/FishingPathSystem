package edu.jxufe.tiamo.fishingpathsys.service.impl;

import edu.jxufe.tiamo.fishingpathsys.dao.*;
import edu.jxufe.tiamo.fishingpathsys.domain.*;
import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import edu.jxufe.tiamo.fishingpathsys.service.StaffService;
import edu.jxufe.tiamo.util.ListUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class StaffServiceImpl implements StaffService {

    private StaffDao staffDao;
    private AnnouncementDao announcementDao;
    private LearningPathDao learningPathDao;
    private LearningRecordDao learningRecordDao;
    private CourseDao courseDao;
    private CourseSectionDao courseSectionDao;

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    public void setAnnouncementDao(AnnouncementDao announcementDao) {
        this.announcementDao = announcementDao;
    }

    public void setLearningPathDao(LearningPathDao learningPathDao) {
        this.learningPathDao = learningPathDao;
    }

    public void setLearningRecordDao(LearningRecordDao learningRecordDao) {
        this.learningRecordDao = learningRecordDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void setCourseSectionDao(CourseSectionDao courseSectionDao) {
        this.courseSectionDao = courseSectionDao;
    }

    @Override
    public List<Staff> getAllStaffsByEnterpriseId(Short enterpriseId) {
        try{
            return staffDao.findStaffsByEnterpriseId(enterpriseId);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("获取员工信息时出现异常，请通知管理员！");
        }
    }

    @Override
    public List<Announcement> getAnnouncementsByEnterpriseIdAndDepartmentId(Short enterpriseId,Short departmentId) {
        try{
            List<Announcement> announcements = announcementDao.getAnnouncementsByEnterpriseIdAndDepartmentId(enterpriseId,departmentId);
            announcements.addAll(announcementDao.getAnnouncementsByEnterpriseIdAndDepartmentId(enterpriseId,null));
            announcements.addAll(announcementDao.getAnnouncementsForAdmin());
            return announcements;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("获取公告信息时出现异常，请通知管理员！");
        }
    }

    @Override
    public LearningRecord storeLearningRecord(Short staffId, Short courseId, Short courseSectionId) {
        try{
            Course course = courseDao.get(Course.class,courseId);
            List<LearningPath> learningPaths = new ArrayList<>();
            learningPaths.addAll(learningPathDao.getLearningPathByStaffIdAndCourseCategoryId(staffId,course.getCourseType().getCourseCategory().getId()));
            LearningPath learningPath;
            if(learningPaths.size()<=0){
                learningPath = new LearningPath();
                learningPath.setName(course.getCourseType().getCourseCategory().getName());
                learningPath.setCourseCategory(course.getCourseType().getCourseCategory());
                learningPath.setStaff(staffDao.get(Staff.class,staffId));
                learningPath = learningPathDao.get(LearningPath.class,learningPathDao.save(learningPath));
            }else{
                learningPath = new ListUtil<LearningPath>().getFirst(learningPaths);
            }
            List<LearningRecord> learningRecordList = learningRecordDao.getLearningRecordByCourseIdAndLearningPathId(courseId,learningPath.getId());
            LearningRecord learningRecord = new ListUtil<LearningRecord>().getFirst(learningRecordList);
            if(learningRecord!=null){
                if(learningRecord.getCourseChapter().getId()<courseSectionId){
                    learningRecord.setCourseSection(courseSectionDao.get(CourseSection.class,courseSectionId));
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                learningRecord.setLastLearningTime(simpleDateFormat.format(new Date()));
                learningRecordDao.update(learningRecord);
            }else{
                learningRecord = new LearningRecord();
                learningRecord.setCourse(course);
                learningRecord.setLearningPath(learningPath);
                learningRecord.setCourseSection(courseSectionDao.get(CourseSection.class,courseSectionId));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                learningRecord.setStartTime(simpleDateFormat.format(new Date()));
                learningRecord.setLastLearningTime(simpleDateFormat.format(new Date()));
                learningRecordDao.save(learningRecord);
            }
            return learningRecord;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("存储员工学习记录时出现异常，请通知管理员！");
        }
    }

    @Override
    public List<LearningPath> getLearningPathByStaffId(Short staffId) {
        try{
            List<LearningPath> learningPaths = new ArrayList<>();
            learningPaths.addAll(learningPathDao.getLearningPathsByStaffId(staffId));
            return learningPaths;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("获取员工学习记录时出现异常，请通知管理员！");
        }
    }

    @Override
    public List<Staff> getAllStaffsByEnterpriseIdAndDepartmentIdAndPostTypeId(Short enterpriseId, Short departmentId,Short postTypeId) {
        try{
            List<Staff> staffList = new ArrayList<>();
            if(departmentId!=null){
                if(departmentId==1){
                    staffList.addAll(staffDao.findStaffsByEnterpriseIdAndDepartmentId(enterpriseId,departmentId));
                }
                // 如果员工为总监或CEO则获取其他部门总监以及CEO的信息
                if(postTypeId==1){
                    staffList.addAll(staffDao.findStaffsByEnterpriseIdAndPostTypeIdNotDepartmentId(enterpriseId,(short)2,departmentId));
                }else if (postTypeId==2){
                    if(departmentId!=1){
                        staffList.addAll(staffDao.findStaffsByEnterpriseIdAndPostTypeIdNotDepartmentId(enterpriseId,(short)1,departmentId));
                    }else{
                        staffList.addAll(staffDao.findStaffsByEnterpriseIdAndPostTypeIdNotDepartmentId(enterpriseId,(short)2,departmentId));
                    }
                }
                if(departmentId!=1){
                    staffList.addAll(staffDao.findStaffsByEnterpriseIdAndDepartmentId(enterpriseId,departmentId));
                }
            }else{
                staffList.addAll(staffDao.findStaffsByEnterpriseId(enterpriseId));
            }
            return staffList;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("获取员工信息时时出现异常，请通知管理员！");
        }
    }


}
