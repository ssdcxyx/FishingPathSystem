package edu.jxufe.tiamo.fishingpathsys.service.impl;

import com.sun.tools.javac.comp.Enter;
import edu.jxufe.tiamo.fishingpathsys.dao.*;
import edu.jxufe.tiamo.fishingpathsys.domain.*;
import edu.jxufe.tiamo.fishingpathsys.domain.CourseLearningRecordDTO.CourseDTO;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffDepartmentDTO.DepartmentDTO;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffDepartmentDTO.EnterpriseDTO;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffDepartmentDTO.StaffDTO;
import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import edu.jxufe.tiamo.fishingpathsys.service.EnterpriseService;
import edu.jxufe.tiamo.util.Code;
import edu.jxufe.tiamo.util.ListUtil;
import edu.jxufe.tiamo.util.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class EnterpriseServiceImpl implements EnterpriseService{

    private EnterpriseDao enterpriseDao;
    private EnterpriseTypeDao enterpriseTypeDao;
    private UserDao userDao;
    private PostTypeDao postTypeDao;
    private DepartmentDao departmentDao;
    private StaffDao staffDao;
    private AnnouncementDao announcementDao;

    public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
        this.enterpriseDao = enterpriseDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setEnterpriseTypeDao(EnterpriseTypeDao enterpriseTypeDao) {
        this.enterpriseTypeDao = enterpriseTypeDao;
    }

    public void setPostTypeDao(PostTypeDao postTypeDao) {
        this.postTypeDao = postTypeDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    public void setAnnouncementDao(AnnouncementDao announcementDao) {
        this.announcementDao = announcementDao;
    }

    @Override
    public Enterprise enterpriseRegister(String telephone, String password) {
        try{
            Enterprise enterprise = new Enterprise();
            User user = new User();
            user.setAccount(telephone);
            user.setPassword(password);
            user.setTelephone(telephone);
            user.setName("yj"+telephone);
            user.setRole(Code.role.ENTERPRISE.getIndex());
            user.setLogoPath("/img/user_logo/3.png");
            enterprise.setUser(user);
            enterprise.setId((Short) enterpriseDao.save(enterprise));
            return enterprise;
        }catch (Exception ex){
            Logger.Log.error("enterpriseRegister:"+ex);
            ex.printStackTrace();
            throw new CustomException("企业用户注册时出现异常，请通知管理员！");
        }
    }

    @Override
    public List<EnterpriseType> getAllEnterpriseTypes() {
        try{
            return enterpriseTypeDao.findAll(EnterpriseType.class);
        }catch (Exception ex){
            Logger.Log.error("getAllEnterpriseTypes:"+ex);
            ex.printStackTrace();
            throw new CustomException("获取所有企业类型时出现问题，请通知管理员！");
        }
    }

    @Override
    public Enterprise updateEnterpriseInfo(Short id, String name, Short enterpriseTypeId, String description, String linkMan, String telephone) {
        try{
            Enterprise enterprise = enterpriseDao.get(Enterprise.class,id);
            enterprise.getUser().setName(name);
            enterprise.setEnterpriseType(enterpriseTypeDao.get(EnterpriseType.class,enterpriseTypeId));
            enterprise.getUser().setDescription(description);
            enterprise.setLinkMan(linkMan);
            enterprise.getUser().setTelephone(telephone);
            enterpriseDao.update(enterprise);
            return new ListUtil<Enterprise>().getFirst(enterpriseDao.findEnterpriseByUserId(enterprise.getUser().getId()));
        }catch (Exception ex){
            Logger.Log.error("updateEnterpriseInfo:"+ex);
            ex.printStackTrace();
            throw new CustomException("更新用户信息时出现异常。请通知管理员！");
        }
    }

    @Override
    public List<Department> getAllDepartment() {
        try{
            List<Department> departments = departmentDao.findAll(Department.class);
            Department department_all = new Department();
            department_all.setName("所有部门");
            departments.add(0,department_all);
            return departments;
        }catch (Exception ex){
            Logger.Log.error("getAllDepartment:"+ex);
            ex.printStackTrace();
            throw new CustomException("获取所有部门种类时出现异常。请通知管理员！");
        }
    }

    @Override
    public List<PostType> getAllPostType() {
        try{
            return postTypeDao.findAll(PostType.class);
        }catch (Exception ex){
            Logger.Log.error("getAllPostType:"+ex);
            ex.printStackTrace();
            throw new CustomException("获取所有职位种类时出现异常。请通知管理员！");
        }
    }

    @Override
    public Staff addStaff(String jobNumber, String name, String sex, short departmentId, short postTypeId, String telephone, String account, String password, short enterpriseId) {
        try{
            Staff staff = new Staff();
            staff.setJobNumber(jobNumber);
            Department department = departmentDao.get(Department.class,departmentId);
            staff.setDepartment(department);
            PostType postType = postTypeDao.get(PostType.class,postTypeId);
            staff.setPostType(postType);
            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setName(name);
            user.setSex(sex);
            user.setTelephone(telephone );
            user.setRole(Code.role.STAFF.getIndex());
            if(sex.equals('男')){
                user.setLogoPath("/img/user_logo/1.png");
            }else{
                user.setLogoPath("/img/user_logo/4.png");
            }
            userDao.save(user);
            staff.setUser(user);
            Enterprise enterprise = enterpriseDao.get(Enterprise.class,enterpriseId);
            staff.setEnterprise(enterprise);
            staffDao.save(staff);
            return staff;
        }catch (Exception ex){
            Logger.Log.error("addStaff:"+ex);
            ex.printStackTrace();
            throw new CustomException("添加员工账号时出现异常，请通知管理员！");
        }
    }

    @Override
    public Staff updateStaff(short id, String jobNumber, String name, String sex, short departmentId, short postTypeId, String telephone, String account, String password, short enterpriseId) {
        try{
            Staff staff = staffDao.get(Staff.class,id);
            staff.setJobNumber(jobNumber);
            Department department = departmentDao.get(Department.class,departmentId);
            staff.setDepartment(department);
            PostType postType = postTypeDao.get(PostType.class,postTypeId);
            staff.setPostType(postType);
            User user = staff.getUser();
            user.setAccount(account);
            user.setPassword(password);
            user.setName(name);
            user.setSex(sex);
            user.setTelephone(telephone );
            if(sex.equals('男')){
                user.setLogoPath("/img/user_logo/1.png");
            }else{
                user.setLogoPath("/img/user_logo/4.png");
            }
            userDao.save(user);
            staff.setUser(user);
            Enterprise enterprise = enterpriseDao.get(Enterprise.class,enterpriseId);
            staff.setEnterprise(enterprise);
            staffDao.update(staff);
            return staff;
        }catch (Exception ex){
            Logger.Log.error("updateStaff:"+ex);
            ex.printStackTrace();
            throw new CustomException("更新员工账号时出现异常，请通知管理员！");
        }
    }

    @Override
    public Staff deleteStaff(short id) {
        try{
            Staff staff = staffDao.get(Staff.class,id);
            userDao.delete(staff.getUser());
            staffDao.delete(staff);
            return staff;
        }catch (Exception ex){
            Logger.Log.error("deleteStaff:"+ex);
            ex.printStackTrace();
            throw new CustomException("删除员工账号时出现异常，请通知管理员！");
        }
    }

    @Override
    public Announcement publishAnnouncement(String title, String information, short enterpriseId, short departmentId) {
        try{
            Department department;
            if(departmentId!=0){
                department = departmentDao.get(Department.class,departmentId);
            }else{
                department = null;
            }
            Enterprise enterprise = enterpriseDao.get(Enterprise.class,enterpriseId);
            Announcement announcement = new Announcement();
            announcement.setTitle(title);
            announcement.setInformation(information);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            announcement.setTime(simpleDateFormat.format(new Date()));
            announcement.setEnterprise(enterprise);
            announcement.setDepartment(department);
            short announcementId = (Short)announcementDao.save(announcement);
            return announcementDao.get(Announcement.class,announcementId);
        }catch (Exception ex){
            Logger.Log.error("publishAnnouncement:"+ex);
            ex.printStackTrace();
            throw new CustomException("发布公告时出现异常，请通知管理员！");
        }
    }

    @Override
    public List<Announcement> getAnnouncementsByEnterpriseId(Short enterpriseId) {
        try{
            List<Announcement> announcements = new ArrayList<>();
            announcements.addAll(announcementDao.getAnnouncementsByEnterpriseId(enterpriseId));
            announcements.addAll(announcementDao.getAnnouncementsForAdmin());
            return announcements;
        }catch (Exception ex){
            Logger.Log.error("getAnnouncementsByEnterpriseId:"+ex);
            ex.printStackTrace();
            throw new CustomException("获取公告信息时出现异常，请通知管理员！");
        }
    }

    @Override
    public EnterpriseDTO getEnterpriseDTOByEnterpriseId(Short enterpriseId) {
        try{
            Enterprise enterprise = enterpriseDao.get(Enterprise.class,enterpriseId);
            List<Staff> staffList = staffDao.findStaffsByEnterpriseId(enterpriseId);
            List<DepartmentDTO> departmentDTOList = new ArrayList<>();
            DepartmentDTO departmentDTO;
            for (Staff staff : staffList) {
                if(staffList.indexOf(staff)==0){
                    departmentDTO = new DepartmentDTO();
                    departmentDTO.setDepartment(staff.getDepartment());
                    departmentDTO.setStaffDTOList(new ArrayList<>());
                    StaffDTO staffDTO = new StaffDTO();
                    staffDTO.setStaff(staff);
                    staffDTO.setLearningPathList(staff.getLearningPathList());
                    departmentDTO.getStaffDTOList().add(staffDTO);
                    departmentDTOList.add(departmentDTO);
                    continue;
                }
                for (int i = 0; i < departmentDTOList.size(); i++) {
                    if(staff.getDepartment().getId()==departmentDTOList.get(i).getDepartment().getId()){
                        StaffDTO staffDTO = new StaffDTO();
                        staffDTO.setStaff(staff);
                        staffDTO.setLearningPathList(staff.getLearningPathList());
                        departmentDTOList.get(i).getStaffDTOList().add(staffDTO);
                    }else{
                        if(i==departmentDTOList.size()-1){
                            departmentDTO = new DepartmentDTO();
                            departmentDTO.setDepartment(staff.getDepartment());
                            List<StaffDTO> staffDTOList = new ArrayList<>();
                            StaffDTO staffDTO = new StaffDTO();
                            staffDTO.setStaff(staff);
                            staffDTO.setLearningPathList(staff.getLearningPathList());
                            staffDTOList.add(staffDTO);
                            departmentDTO.setStaffDTOList(staffDTOList);
                            departmentDTOList.add(departmentDTO);
                            break;
                        }
                    }
                }
            }
            EnterpriseDTO enterpriseDTO = new EnterpriseDTO();
            enterpriseDTO.setEnterprise(enterprise);
            enterpriseDTO.setDepartmentDTOList(departmentDTOList);
            return enterpriseDTO;
        }catch (Exception ex){
            Logger.Log.error("getEnterpriseDTOByEnterpriseId:"+ex);
            ex.printStackTrace();
            throw new CustomException("获取企业员工信息失败，请通知管理员！");
        }
    }

    @Override
    public Enterprise getEnterpriseByEnterpriseId(Short enterpriseId) {
        try{
            return enterpriseDao.get(Enterprise.class,enterpriseId);
        }catch (Exception ex){
            Logger.Log.error("getEnterpriseByEnterpriseId:"+ex);
            ex.printStackTrace();
            throw new CustomException("获取企业信息失败，请通知管理员！");
        }
    }

    @Override
    public Enterprise updateBusinessLicensePath(Short enterpriseId,String businessLicensePath) {
        try{
            Enterprise enterprise = enterpriseDao.get(Enterprise.class,enterpriseId);
            enterprise.setBusinessLicensePath(businessLicensePath);
            return enterpriseDao.get(Enterprise.class,enterpriseDao.save(enterprise));
        }catch (Exception ex){
            Logger.Log.error("updateBusinessLicensePath:"+ex);
            ex.printStackTrace();
            throw new CustomException("获取企业信息失败，请通知管理员！");
        }
    }
}
