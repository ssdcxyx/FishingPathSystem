package edu.jxufe.tiamo.fishingpathsys.controller;

import edu.jxufe.tiamo.fishingpathsys.domain.Staff;
import edu.jxufe.tiamo.fishingpathsys.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        return staffService.getAllStaffsByEnterpriseId(enterpriseId);
    }
}
