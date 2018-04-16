package edu.jxufe.tiamo.fishingpathsys.dao;

import edu.jxufe.tiamo.common.dao.BaseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Admin;

import java.util.List;

public interface AdminDao extends BaseDao<Admin> {

    List<Admin> findAdminByUserId(short userId);
}
