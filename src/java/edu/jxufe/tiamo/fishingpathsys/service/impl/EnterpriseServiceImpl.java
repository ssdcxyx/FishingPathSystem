package edu.jxufe.tiamo.fishingpathsys.service.impl;

import edu.jxufe.tiamo.fishingpathsys.dao.EnterpriseDao;
import edu.jxufe.tiamo.fishingpathsys.dao.EnterpriseTypeDao;
import edu.jxufe.tiamo.fishingpathsys.dao.UserDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Enterprise;
import edu.jxufe.tiamo.fishingpathsys.domain.EnterpriseType;
import edu.jxufe.tiamo.fishingpathsys.domain.Staff;
import edu.jxufe.tiamo.fishingpathsys.domain.User;
import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import edu.jxufe.tiamo.fishingpathsys.service.EnterpriseService;
import edu.jxufe.tiamo.util.Code;
import edu.jxufe.tiamo.util.ListUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class EnterpriseServiceImpl implements EnterpriseService{

    private EnterpriseDao enterpriseDao;
    private EnterpriseTypeDao enterpriseTypeDao;
    private UserDao userDao;

    public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
        this.enterpriseDao = enterpriseDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setEnterpriseTypeDao(EnterpriseTypeDao enterpriseTypeDao) {
        this.enterpriseTypeDao = enterpriseTypeDao;
    }

    @Override
    public Enterprise enterpriseRegister(String telephone, String password) {
        try{
            Enterprise enterprise = new Enterprise();
            enterprise.setTelephone(telephone);
            enterprise.setName("yj"+telephone);
            User user = new User();
            user.setAccount(telephone);
            user.setPassword(password);
            user.setRole(Code.role.ENTERPRISE.getIndex());
            enterprise.setUser(user);
            enterprise.setId((Short) enterpriseDao.save(enterprise));
            return enterprise;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("企业用户注册时出现异常，请通知管理员！");
        }
    }

    @Override
    public List<EnterpriseType> getAllEnterpriseTypes() {
        try{
            return enterpriseTypeDao.findAll(EnterpriseType.class);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("获得所有企业类型时出现问题，请通知管理员！");
        }
    }

    @Override
    public Enterprise updateEnterpriseInfo(Short id, String name, Short enterpriseTypeId, String description, String linkMan, String telephone) {
        try{
            Enterprise enterprise = enterpriseDao.get(Enterprise.class,id);
            enterprise.setName(name);
            enterprise.setEnterpriseType(enterpriseTypeDao.get(EnterpriseType.class,enterpriseTypeId));
            enterprise.setDescription(description);
            enterprise.setLinkMan(linkMan);
            enterprise.setTelephone(telephone);
            enterpriseDao.update(enterprise);
            return new ListUtil<Enterprise>().getFirst(enterpriseDao.findEnterpriseByUserId(enterprise.getUser().getId()));
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("更新用户信息时出现异常。请通知管理员！");
        }
    }

}
