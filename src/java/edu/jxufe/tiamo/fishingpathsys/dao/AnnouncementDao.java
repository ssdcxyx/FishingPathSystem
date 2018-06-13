package edu.jxufe.tiamo.fishingpathsys.dao;

import edu.jxufe.tiamo.common.dao.BaseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Announcement;

import java.util.List;

public interface AnnouncementDao extends BaseDao<Announcement> {

    List<Announcement> getAnnouncementsByEnterpriseIdAndDepartmentId(Short enterpriseId,Short departmentId);

    List<Announcement> getAnnouncementsByAdminId(Short adminId);

    List<Announcement> getAnnouncementsForAdmin();

    List<Announcement> getAnnouncementsByEnterpriseId(Short enterpriseId);
}
