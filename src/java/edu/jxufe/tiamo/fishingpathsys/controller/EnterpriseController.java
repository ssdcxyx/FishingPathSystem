package edu.jxufe.tiamo.fishingpathsys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffTableDTO.DataDTO;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffTableDTO.DeleteResponseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffTableDTO.ResponseDTO;
import edu.jxufe.tiamo.fishingpathsys.service.EnterpriseService;
import edu.jxufe.tiamo.fishingpathsys.service.StaffService;
import edu.jxufe.tiamo.fishingpathsys.service.UserService;
import edu.jxufe.tiamo.util.Code;
import edu.jxufe.tiamo.util.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class EnterpriseController extends BaseController {

    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private UserService userService;
    @Resource
    private StaffService staffService;

    @ResponseBody
    @PostMapping(value = "/enterpriseRegister")
    public Object enterpriseRegister(String telephone,String password){
        Logger.Log.info(this.getClass().getName()+": enterpriseRegister "+telephone);
        if(userService.getUserByTelephone(telephone)!=null){
            return Code.jiguangReturnResult.REPEAT;
        }
        return enterpriseService.enterpriseRegister(telephone, password);
    }

    @ResponseBody
    @GetMapping(value = "/getAllEnterpriseTypes")
    public Object getAllEnterpriseTypes(){
        return enterpriseService.getAllEnterpriseTypes();
    }

    @ResponseBody
    @GetMapping(value = "/getAllDepartments")
    public Object getAllDepartments(){
        return enterpriseService.getAllDepartment();
    }

    @ResponseBody
    @GetMapping(value = "/getAllPostTypes")
    public Object getAllPostTypes(){
        return enterpriseService.getAllPostType();
    }

    @ResponseBody
    @PostMapping(value = "/updateEnterpriseInfo")
    public Object updateEnterpriseInfo(Short id, String name, Short enterpriseTypeId,String description,String linkMan,String telephone){
        return enterpriseService.updateEnterpriseInfo(id, name, enterpriseTypeId, description, linkMan, telephone);
    }

    @ResponseBody
    @PostMapping(value = "/addStaff")
    public Object addStaff(@RequestBody String param) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDTO responseDTO = objectMapper.readValue(param,ResponseDTO.class);
        Map<String,DataDTO> dataDTOMap = responseDTO.getData();
        List<DataDTO> dataDTOS = new ArrayList<>();
        for (String key : dataDTOMap.keySet()) {
            dataDTOS.add(dataDTOMap.get(key));
        }
        DataDTO dataDTO;
        if(dataDTOS.size()>0){
            dataDTO = dataDTOS.get(0);
            enterpriseService.addStaff(dataDTO.getJobNumber(),dataDTO.getName(),dataDTO.getSex(),
                    dataDTO.getDepartment().getId(),dataDTO.getPostType().getId(),dataDTO.getTelephone(),
                    dataDTO.getUser().getAccount(),dataDTO.getUser().getPassword(),dataDTO.getEnterprise().getId());
        }
        return responseDTO;
    }

    @ResponseBody
    @PostMapping(value = "/editStaff")
    public Object editStaff(@RequestBody String param) throws Exception{
        // TODO: 2018/4/18 多行编辑存在问题
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDTO responseDTO = objectMapper.readValue(param,ResponseDTO.class);
        Map<String,DataDTO> dataDTOMap = responseDTO.getData();
        List<DataDTO> dataDTOS = new ArrayList<>();
        Short staffId = 0;
        for (String key : dataDTOMap.keySet()) {
            staffId = Short.parseShort(key);
            DataDTO dataDTO = dataDTOMap.get(key);
            enterpriseService.updateStaff(staffId,dataDTO.getJobNumber(),dataDTO.getName(),dataDTO.getSex(),
                    dataDTO.getDepartment().getId(),dataDTO.getPostType().getId(),dataDTO.getTelephone(),
                    dataDTO.getUser().getAccount(),dataDTO.getUser().getPassword(),dataDTO.getEnterprise().getId());
        }
        return param;
    }

    @ResponseBody
    @PostMapping(value = "/deleteStaff")
    public Object deleteStaff(@RequestBody String param) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        DeleteResponseDao deleteResponseDao = objectMapper.readValue(param,DeleteResponseDao.class);
        Map<String,Object> dataDTOMap = deleteResponseDao.getData();
        Short staffId = 0;
        for (String key : dataDTOMap.keySet()) {
            staffId = Short.parseShort(key);
            enterpriseService.deleteStaff(staffId);
        }
        return param;
    }

}
