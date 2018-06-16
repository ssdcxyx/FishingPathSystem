package edu.jxufe.tiamo.fishingpathsys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.jxufe.tiamo.fishingpathsys.domain.Enterprise;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffTableDTO.DataDTO;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffTableDTO.DeleteResponseDao;
import edu.jxufe.tiamo.fishingpathsys.domain.StaffTableDTO.ResponseDTO;
import edu.jxufe.tiamo.fishingpathsys.service.EnterpriseService;
import edu.jxufe.tiamo.fishingpathsys.service.StaffService;
import edu.jxufe.tiamo.fishingpathsys.service.UserService;
import edu.jxufe.tiamo.util.Code;
import edu.jxufe.tiamo.util.Logger;
import edu.jxufe.tiamo.util.UrlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
        Logger.Log.info(this.getClass().getName()+": getAllEnterpriseTypes ");
        return enterpriseService.getAllEnterpriseTypes();
    }

    @ResponseBody
    @GetMapping(value = "/getAllDepartments")
    public Object getAllDepartments(){
        Logger.Log.info(this.getClass().getName()+": getAllDepartments ");
        return enterpriseService.getAllDepartment();
    }

    @ResponseBody
    @GetMapping(value = "/getAllPostTypes")
    public Object getAllPostTypes(){
        Logger.Log.info(this.getClass().getName()+": getAllPostTypes ");
        return enterpriseService.getAllPostType();
    }

    @ResponseBody
    @PostMapping(value = "/updateEnterpriseInfo")
    public Object updateEnterpriseInfo(Short id, String name, Short enterpriseTypeId,String description,String linkMan,String telephone){
        Logger.Log.info(this.getClass().getName()+": updateEnterpriseInfo" + id  + " "+ name);
        return enterpriseService.updateEnterpriseInfo(id, name, enterpriseTypeId, description, linkMan, telephone);
    }

    @ResponseBody
    @PostMapping(value = "/addStaff")
    public Object addStaff(@RequestBody String param) throws Exception{
        Logger.Log.info(this.getClass().getName()+": addStaff" + param);
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
            enterpriseService.addStaff(dataDTO.getJobNumber(),dataDTO.getUser().getName(),dataDTO.getUser().getSex(),
                    dataDTO.getDepartment().getId(),dataDTO.getPostType().getId(),dataDTO.getUser().getTelephone(),
                    dataDTO.getUser().getAccount(),dataDTO.getUser().getPassword(),dataDTO.getEnterprise().getId());
        }
        return responseDTO;
    }

    @ResponseBody
    @PostMapping(value = "/editStaff")
    public Object editStaff(@RequestBody String param) throws Exception{
        Logger.Log.info(this.getClass().getName()+": editStaff" + param);
        // TODO: 2018/4/18 多行编辑存在问题
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDTO responseDTO = objectMapper.readValue(param,ResponseDTO.class);
        Map<String,DataDTO> dataDTOMap = responseDTO.getData();
        List<DataDTO> dataDTOS = new ArrayList<>();
        Short staffId = 0;
        for (String key : dataDTOMap.keySet()) {
            staffId = Short.parseShort(key);
            DataDTO dataDTO = dataDTOMap.get(key);
            enterpriseService.updateStaff(staffId,dataDTO.getJobNumber(),dataDTO.getUser().getName(),dataDTO.getUser().getSex(),
                    dataDTO.getDepartment().getId(),dataDTO.getPostType().getId(),dataDTO.getUser().getTelephone(),
                    dataDTO.getUser().getAccount(),dataDTO.getUser().getPassword(),dataDTO.getEnterprise().getId());
        }
        return param;
    }

    @ResponseBody
    @PostMapping(value = "/deleteStaff")
    public Object deleteStaff(@RequestBody String param) throws Exception{
        Logger.Log.info(this.getClass().getName()+": deleteStaff" + param);
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

    @ResponseBody
    @PostMapping(value="/publishAnnouncement")
    public Object publishAnnouncement(String title,String information,Short enterpriseId,Short departmentId){
        Logger.Log.info(this.getClass().getName()+": publishAnnouncement" + title + " " + information + " " + enterpriseId  + " "+departmentId);
        return enterpriseService.publishAnnouncement(title, information, enterpriseId, departmentId);
    }

    @ResponseBody
    @GetMapping(value = "/getAnnouncementsByEnterpriseId")
    public Object getAnnouncementsByEnterpriseId(Short enterpriseId){
        Logger.Log.info(this.getClass().getName()+": getAnnouncementsByEnterpriseId" + enterpriseId);
        return enterpriseService.getAnnouncementsByEnterpriseId(enterpriseId);
    }

    @ResponseBody
    @GetMapping(value = "/getEnterpriseDTOByEnterpriseId")
    public Object getEnterpriseDTOByEnterpriseId(Short enterpriseId){
        Logger.Log.info(this.getClass().getName()+": getEnterpriseDTOByEnterpriseId" + enterpriseId);
        return enterpriseService.getEnterpriseDTOByEnterpriseId(enterpriseId);
    }

    @ResponseBody
    @RequestMapping("/uploadBusinessLicense")
    public void upload(HttpServletRequest request, HttpServletResponse response,Short enterpriseId){
        Enterprise enterprise =  enterpriseService.getEnterpriseByEnterpriseId(enterpriseId);
        String preFilePath ="";
        if(enterprise!=null){
            if(enterprise.getBusinessLicensePath()!=null&&!enterprise.getBusinessLicensePath().equals("")){
                int index = enterprise.getBusinessLicensePath().indexOf("http://localhost:8080");
                String preFileRelativePath = "";
                if(index>=0){
                    preFileRelativePath = enterprise.getBusinessLicensePath().substring(index+22);
                }
                preFilePath = request.getServletContext().getRealPath("/") + preFileRelativePath ;
            }
        }
        MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
        String uploadUrl = request.getServletContext().getRealPath("/") +"img/enterprise/";//得到当前工程路径拼接上文件名
        File dir = new File(uploadUrl);
        if(!dir.exists())//目录不存在则创建
            dir.mkdirs();
        for(MultipartFile file :files.values()){
            String fileSrc=file.getOriginalFilename();
            String fileName = "";
            if(fileSrc!=null&&!fileSrc.equals("")){
                if(fileSrc.indexOf(".jpg")>=0){
                    fileName = enterpriseId + ".jpg";
                }else if(fileSrc.indexOf(".png")>=0){
                    fileName = enterpriseId + ".png";
                }else if(fileSrc.indexOf(".jpeg")>=0){
                    fileName = enterpriseId + ".jpeg";
                }else if(fileSrc.indexOf(".bmp")>=0){
                    fileName = enterpriseId + ".bmp";
                }else if(fileSrc.indexOf(".gif")>=0){
                    fileName = enterpriseId + ".gif";
                }
            }
            Logger.Log.info(this.getClass().getName()+": uploadBusinessLicense" + uploadUrl+fileName);
            File targetFile = new File(uploadUrl+fileName);//创建文件对象
            File preFile = new File(preFilePath);
            try {
                if(preFile.exists()){
                    preFile.delete();
                }
                if(targetFile.exists()){
                    targetFile.delete();
                }
                targetFile.createNewFile();
                enterpriseService.updateBusinessLicensePath(enterpriseId,"http://localhost:8080" +"/img/enterprise/" +fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                file.transferTo(targetFile);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @ResponseBody
    @GetMapping(value = "/getEnterpriseByEnterpriseId")
    public Object getEnterpriseByEnterpriseId(Short enterpriseId){
        Logger.Log.info(this.getClass().getName()+": getEnterpriseByEnterpriseId" + enterpriseId);
        return enterpriseService.getEnterpriseByEnterpriseId(enterpriseId);
    }
}
