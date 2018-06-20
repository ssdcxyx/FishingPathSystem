package edu.jxufe.tiamo.fishingpathsys.controller;

import edu.jxufe.tiamo.fishingpathsys.domain.Staff;
import edu.jxufe.tiamo.fishingpathsys.service.StaffService;
import edu.jxufe.tiamo.util.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class StaffController extends BaseController {

    @Resource
    private StaffService staffService;


    @ResponseBody
    @GetMapping(value = "/getStaffsByEnterpriseId")
    public Object getStaffsByEnterpriseId(Short enterpriseId){
        Logger.Log.info(this.getClass().getName()+": getStaffsByEnterpriseId" + enterpriseId);
        return staffService.getAllStaffsByEnterpriseId(enterpriseId);
    }

    @ResponseBody
    @GetMapping(value = "/getAnnouncementsByEnterpriseIdAndDepartmentId")
    public Object getAnnouncementsByEnterpriseIdAndDepartmentId(Short enterpriseId,Short departmentId){
        Logger.Log.info(this.getClass().getName()+": getAnnouncementsByEnterpriseIdAndDepartmentId" + enterpriseId  + " "+ departmentId);
        return staffService.getAnnouncementsByEnterpriseIdAndDepartmentId(enterpriseId,departmentId);
    }

    @ResponseBody
    @PostMapping(value = "/storeLearningRecord")
    public Object storeLearningRecord(Short staffId,Short courseId,Short courseSectionId){
        Logger.Log.info(this.getClass().getName()+": storeLearningRecord" + staffId  + " "+ courseId  + " "+ courseSectionId);
        return staffService.storeLearningRecord(staffId,courseId,courseSectionId);
    }

    @ResponseBody
    @GetMapping(value = "/getLearningPathByStaffId")
    public Object getLearningPathByStaffId(Short staffId){
        Logger.Log.info(this.getClass().getName()+": getLearningPathByStaffId" + staffId);
        return staffService.getLearningPathByStaffId(staffId);
    }

    @ResponseBody
    @GetMapping(value = "/getAllStaffsByEnterpriseIdAndDepartmentIdAndPostTypeId")
    public Object getAllStaffsByEnterpriseIdAndDepartmentId(Short enterpriseId,Short departmentId,Short postTypeId){
        Logger.Log.info(this.getClass().getName()+": getAllStaffsByEnterpriseIdAndDepartmentId" + enterpriseId + " " + departmentId +" " +postTypeId);
        return staffService.getAllStaffsByEnterpriseIdAndDepartmentIdAndPostTypeId(enterpriseId,departmentId,postTypeId);
    }

    @ResponseBody
    @GetMapping(value = "/getAllStaffsByEnterpriseIdAndDepartmentId")
    public Object getAllStaffsByEnterpriseIdAndDepartmentId(Short enterpriseId,Short departmentId){
        Logger.Log.info(this.getClass().getName()+": getAllStaffsByEnterpriseIdAndDepartmentId" + enterpriseId + " " + departmentId);
        return staffService.getAllStaffsByEnterpriseIdAndDepartmentId(enterpriseId,departmentId);
    }
}
