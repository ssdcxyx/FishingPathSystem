package edu.jxufe.tiamo.fishingpathsys.service;

import edu.jxufe.tiamo.fishingpathsys.domain.Staff;

import java.util.List;

public interface StaffService {

    // 根据企业id获得所有员工信息
    List<Staff> getAllStaffsByEnterpriseId(Short enterpriseId);

}
