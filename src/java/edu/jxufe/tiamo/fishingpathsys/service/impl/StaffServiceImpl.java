package edu.jxufe.tiamo.fishingpathsys.service.impl;

import edu.jxufe.tiamo.fishingpathsys.dao.StaffDao;
import edu.jxufe.tiamo.fishingpathsys.domain.Staff;
import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import edu.jxufe.tiamo.fishingpathsys.service.StaffService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class StaffServiceImpl implements StaffService {

    private StaffDao staffDao;

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    @Override
    public List<Staff> getAllStaffsByEnterpriseId(Short enterpriseId) {
        try{
            return staffDao.findStaffsByEnterpriseId(enterpriseId);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("企业用户注册时出现异常，请通知管理员！");
        }
    }
}
