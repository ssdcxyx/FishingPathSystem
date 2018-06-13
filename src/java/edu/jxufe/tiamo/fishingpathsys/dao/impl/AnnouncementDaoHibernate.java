package edu.jxufe.tiamo.fishingpathsys.dao.impl;

import edu.jxufe.tiamo.common.dao.impl.BaseDaoHibernate;
import edu.jxufe.tiamo.fishingpathsys.dao.AnnouncementDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Announcement;

import java.util.List;

public class AnnouncementDaoHibernate extends BaseDaoHibernate<Announcement> implements AnnouncementDao {

    @Override
    public List<Announcement> getAnnouncementsByEnterpriseIdAndDepartmentId(Short enterpriseId,Short departmentId) {
        String hql = null;
        if(departmentId!=null){
            hql = "FROM " + Announcement.class.getSimpleName() +" where enterprise_id=?0 and department_id=?1";
            return find(hql,enterpriseId,departmentId);
        }else{
            hql = "FROM " + Announcement.class.getSimpleName() +" where enterprise_id=?0 and department_id is null";
            return find(hql,enterpriseId);
        }

    }

    @Override
    public List<Announcement> getAnnouncementsByAdminId(Short adminId) {
        String hql = "FROM " + Announcement.class.getSimpleName() +" where admin_id=?0";
        return find(hql,adminId);
    }

    @Override
    public List<Announcement> getAnnouncementsForAdmin() {
        String hql = "FROM " + Announcement.class.getSimpleName() +" where admin_id is not null";
        return find(hql);
    }

    @Override
    public List<Announcement> getAnnouncementsByEnterpriseId(Short enterpriseId) {
        String hql = "FROM " + Announcement.class.getSimpleName() +" where enterprise_id=?0";
        return find(hql,enterpriseId);
    }
}
