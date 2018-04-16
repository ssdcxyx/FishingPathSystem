package edu.jxufe.tiamo.fishingpathsys.dao.impl;

import edu.jxufe.tiamo.common.dao.impl.BaseDaoHibernate;
import edu.jxufe.tiamo.fishingpathsys.dao.StaffDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Staff;
import edu.jxufe.tiamo.fishingpathsys.domain.User;

import java.util.List;

public class StaffDaoHibernate extends BaseDaoHibernate<Staff> implements StaffDao {

    @Override
    public List<Staff> findStaffByUserId(short userId) {
        String hql = "FROM "+User.class.getSimpleName()+" where user_id=?0";
        List<Staff> staffList = find(hql,userId);
        return staffList;
    }

    @Override
    public List<Staff> findStaffsByEnterpriseId(short enterpriseId) {
        String hql = "FROM "+Staff.class.getSimpleName()+" where enterprise_id=?0";
        List<Staff> staffList = find(hql,enterpriseId);
        return staffList;
    }
}
