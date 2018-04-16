package edu.jxufe.tiamo.fishingpathsys.dao;

import edu.jxufe.tiamo.common.dao.BaseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Enterprise;

import java.util.List;

public interface EnterpriseDao extends BaseDao<Enterprise> {

    List<Enterprise> findEnterpriseByUserId(short userId);
}
