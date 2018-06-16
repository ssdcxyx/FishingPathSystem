package edu.jxufe.tiamo.fishingpathsys.service;

import edu.jxufe.tiamo.fishingpathsys.domain.*;
import edu.jxufe.tiamo.fishingpathsys.domain.CourseLearningRecordDTO.CourseDTO;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffDepartmentDTO.EnterpriseDTO;

import java.util.List;

public interface EnterpriseService {

    // 企业用户注册
    Enterprise enterpriseRegister(String telephone, String password);

    // 获得企业类型种类
    List<EnterpriseType> getAllEnterpriseTypes();

    // 获得部门种类
    List<Department> getAllDepartment();

    // 获得所有职位种类
    List<PostType> getAllPostType();

    // 更新企业信息
    Enterprise updateEnterpriseInfo(Short id, String name, Short enterpriseTypeId,String description,String linkMan,String telephone);

    // 添加员工账号
    Staff addStaff(String jobNumber,String name,String sex,short departmentId,short postTypeId,String telephone,String account,String password,short enterpriseId);

    // 更新员工账号
    Staff updateStaff(short id,String jobNumber,String name,String sex,short departmentId,short postTypeId,String telephone,String account,String password,short enterpriseId);

    // 删除员工账号
    Staff deleteStaff(short id);

    // 发布公告
    Announcement publishAnnouncement(String title,String information,short enterpriseId,short departmentId);

    // 根据企业id获取公告
    List<Announcement> getAnnouncementsByEnterpriseId(Short enterpriseId);

    // 根据企业id获取DTO企业信息
    EnterpriseDTO getEnterpriseDTOByEnterpriseId(Short enterpriseId);

    // 根据企业id获取企业信息
    Enterprise getEnterpriseByEnterpriseId(Short enterpriseId);

    // 更新营业执照
    Enterprise updateBusinessLicensePath(Short enterpriseId,String businessLicensePath);

}
