package edu.jxufe.tiamo.fishingpathsys.dao;

import edu.jxufe.tiamo.common.dao.BaseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Staff;

import java.util.List;

public interface StaffDao extends BaseDao<Staff> {

    List<Staff> findStaffByUserId(short userId);

    List<Staff> findStaffsByEnterpriseId(short enterpriseId);
}
