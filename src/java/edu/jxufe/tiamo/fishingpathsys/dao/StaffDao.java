package edu.jxufe.tiamo.fishingpathsys.dao;

import edu.jxufe.tiamo.common.dao.BaseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Staff;

import java.util.List;

public interface StaffDao extends BaseDao<Staff> {

    List<Staff> findStaffByUserId(short userId);

    List<Staff> findStaffsByEnterpriseId(short enterpriseId);

    List<Staff> findStaffsByEnterpriseIdAndDepartmentId(Short enterpriseId,Short departmentId);

    List<Staff> findStaffsByEnterpriseIdAndPostTypeId(Short enterpriseId,Short postTypeId);

    List<Staff> findStaffsByEnterpriseIdAndPostTypeIdNotDepartmentId(Short enterpriseId,Short postTypeId,Short departmentId);

    List<Staff> findStaffsByEnterpriseIdAndDepartmentID(Short enterpriseId,Short departmentId);
}
