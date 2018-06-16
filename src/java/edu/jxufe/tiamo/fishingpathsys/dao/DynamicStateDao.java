package edu.jxufe.tiamo.fishingpathsys.dao;

import edu.jxufe.tiamo.common.dao.BaseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.DynamicState;

import java.util.List;

public interface DynamicStateDao extends BaseDao<DynamicState> {

    List<DynamicState> getDynamicStatesByUserId(Short userId);
}
