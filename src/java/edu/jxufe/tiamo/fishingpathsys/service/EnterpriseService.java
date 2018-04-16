package edu.jxufe.tiamo.fishingpathsys.service;

import edu.jxufe.tiamo.fishingpathsys.domain.Enterprise;
import edu.jxufe.tiamo.fishingpathsys.domain.EnterpriseType;
import edu.jxufe.tiamo.fishingpathsys.domain.Staff;

import java.util.List;

public interface EnterpriseService {

    // 企业用户注册
    Enterprise enterpriseRegister(String telephone, String password);

    // 获得企业类型种类
    List<EnterpriseType> getAllEnterpriseTypes();

    // 更新企业信息
    Enterprise updateEnterpriseInfo(Short id, String name, Short enterpriseTypeId,String description,String linkMan,String telephone);
}
