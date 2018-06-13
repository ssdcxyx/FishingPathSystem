package edu.jxufe.tiamo.fishingpathsys.service;

import edu.jxufe.tiamo.fishingpathsys.domain.*;

import java.util.List;

public interface StaffService {

    // 根据企业id获得所有员工信息
    List<Staff> getAllStaffsByEnterpriseId(Short enterpriseId);

    // 根据企业id获得所有公告信息
    List<Announcement> getAnnouncementsByEnterpriseIdAndDepartmentId(Short enterpriseId,Short departmentId);

    // 存储员工学习信息
    LearningRecord storeLearningRecord(Short staffId, Short courseId, Short courseSectionId);

    // 根据员工id获取学习路径
    List<LearningPath> getLearningPathByStaffId(Short staffId);

    // 根据企业id、部门id、职位id获取员工信息
    List<Staff> getAllStaffsByEnterpriseIdAndDepartmentIdAndPostTypeId(Short enterpriseId,Short departmentId,Short postTypeId);

    // 根据企业id获取员工信息并根据部门分类
    List<Department> findStaffsByEnterpriseIdClassifyByDepartment(Short enterpriseId);
}
