package edu.jxufe.tiamo.fishingpathsys.dao.impl;

import edu.jxufe.tiamo.common.dao.impl.BaseDaoHibernate;
import edu.jxufe.tiamo.fishingpathsys.dao.StaffDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Staff;
import edu.jxufe.tiamo.fishingpathsys.domain.User;

import java.util.List;

public class StaffDaoHibernate extends BaseDaoHibernate<Staff> implements StaffDao {

    @Override
    public List<Staff> findStaffByUserId(short userId) {
        String hql = "FROM "+Staff.class.getSimpleName()+" where user_id=?0";
        List<Staff> staffList = find(hql,userId);
        return staffList;
    }

    @Override
    public List<Staff> findStaffsByEnterpriseId(short enterpriseId) {
        String hql = "FROM "+Staff.class.getSimpleName()+" where enterprise_id=?0";
        List<Staff> staffList = find(hql,enterpriseId);
        return staffList;
    }

    @Override
    public List<Staff> findStaffsByEnterpriseIdAndDepartmentId(Short enterpriseId, Short departmentId) {
        String hql = "FROM "+Staff.class.getSimpleName()+" where enterprise_id=?0 and department_id=?1";
        List<Staff> staffList = find(hql,enterpriseId,departmentId);
        return staffList;
    }

    @Override
    public List<Staff> findStaffsByEnterpriseIdAndPostTypeId(Short enterpriseId, Short postTypeId) {
        String hql = "FROM "+Staff.class.getSimpleName()+" where enterprise_id=?0 and post_type_id=?1";
        List<Staff> staffList = find(hql,enterpriseId,postTypeId);
        return staffList;
    }

    @Override
    public List<Staff> findStaffsByEnterpriseIdAndPostTypeIdNotDepartmentId(Short enterpriseId, Short postTypeId, Short departmentId) {
        String hql = "FROM "+Staff.class.getSimpleName()+" where enterprise_id=?0 and post_type_id=?1 and department_id!=?2";
        List<Staff> staffList = find(hql,enterpriseId,postTypeId,departmentId);
        return staffList;
    }
}
