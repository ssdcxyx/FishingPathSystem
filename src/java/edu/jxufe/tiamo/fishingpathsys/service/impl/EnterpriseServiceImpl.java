package edu.jxufe.tiamo.fishingpathsys.service.impl;

import com.sun.tools.javac.comp.Enter;
import edu.jxufe.tiamo.fishingpathsys.dao.*;
import edu.jxufe.tiamo.fishingpathsys.domain.*;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffDepartmentDTO.DepartmentDTO;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffDepartmentDTO.EnterpriseDTO;
import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import edu.jxufe.tiamo.fishingpathsys.service.EnterpriseService;
import edu.jxufe.tiamo.util.Code;
import edu.jxufe.tiamo.util.ListUtil;
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
            enterprise.setTelephone(telephone);
            enterprise.setName("yj"+telephone);
            User user = new User();
            user.setAccount(telephone);
            user.setPassword(password);
            user.setRole(Code.role.ENTERPRISE.getIndex());
            user.setLogoPath("/img/user_logo/3.png");
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
            throw new CustomException("获取所有企业类型时出现问题，请通知管理员！");
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

    @Override
    public List<Department> getAllDepartment() {
        try{
            List<Department> departments = departmentDao.findAll(Department.class);
            Department department_all = new Department();
            department_all.setName("所有部门");
            departments.add(0,department_all);
            return departments;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("获取所有部门种类时出现异常。请通知管理员！");
        }
    }

    @Override
    public List<PostType> getAllPostType() {
        try{
            return postTypeDao.findAll(PostType.class);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("获取所有职位种类时出现异常。请通知管理员！");
        }
    }

    @Override
    public Staff addStaff(String jobNumber, String name, String sex, short departmentId, short postTypeId, String telephone, String account, String password, short enterpriseId) {
        try{
            Staff staff = new Staff();
            staff.setJobNumber(jobNumber);
            staff.setName(name);
            staff.setSex(sex);
            staff.setTelephone(telephone );
            Department department = departmentDao.get(Department.class,departmentId);
            staff.setDepartment(department);
            PostType postType = postTypeDao.get(PostType.class,postTypeId);
            staff.setPostType(postType);
            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setRole(Code.role.STAFF.getIndex());
            userDao.save(user);
            staff.setUser(user);
            Enterprise enterprise = enterpriseDao.get(Enterprise.class,enterpriseId);
            staff.setEnterprise(enterprise);
            staffDao.save(staff);
            return staff;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("添加员工账号时出现异常，请通知管理员！");
        }
    }

    @Override
    public Staff updateStaff(short id, String jobNumber, String name, String sex, short departmentId, short postTypeId, String telephone, String account, String password, short enterpriseId) {
        try{
            Staff staff = staffDao.get(Staff.class,id);
            staff.setJobNumber(jobNumber);
            staff.setName(name);
            staff.setSex(sex);
            staff.setTelephone(telephone );
            Department department = departmentDao.get(Department.class,departmentId);
            staff.setDepartment(department);
            PostType postType = postTypeDao.get(PostType.class,postTypeId);
            staff.setPostType(postType);
            User user = staff.getUser();
            user.setAccount(account);
            user.setPassword(password);
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
            ex.printStackTrace();
            throw new CustomException("删除员工账号时出现异常，请通知管理员！");
        }
    }

    @Override
    public Announcement publishAnnouncement(String title, String information, short enterpriseId, short departmentId) {
        try{
            Department department = null;
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
            DepartmentDTO departmentDTO = new DepartmentDTO();
            for (Staff staff : staffList) {
                departmentDTO.setDepartment(staff.getDepartment());
                for (DepartmentDTO dto : departmentDTOList) {
                    if(departmentDTO.getDepartment().getId()==dto.getDepartment().getId()){
                        if(departmentDTOList.indexOf(dto)==departmentDTOList.size()){
                            if(departmentDTO.getStaffList()==null){
                                List<Staff> staffs = new ArrayList<>();
                                staffs.add(staff);
                                departmentDTO.setStaffList(new ArrayList<>());
                            }else{
                                departmentDTO.getStaffList().add(staff);
                            }
                            departmentDTOList.add(departmentDTO);
                        }
                        break;
                    }
                }

            }
            EnterpriseDTO enterpriseDTO = new EnterpriseDTO();
            enterpriseDTO.setEnterprise(enterprise);
            enterpriseDTO.setDepartmentList(departmentDTOList);
            return enterpriseDTO;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomException("获取企业员工信息失败，请通知管理员！");
        }
    }
}
