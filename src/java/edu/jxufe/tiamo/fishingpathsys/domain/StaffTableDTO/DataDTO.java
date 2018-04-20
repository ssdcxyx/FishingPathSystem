package edu.jxufe.tiamo.fishingpathsys.domain.StaffTableDTO;

import edu.jxufe.tiamo.fishingpathsys.domain.Enterprise;

import java.util.Map;

public class DataDTO {

    private String jobNumber;
    private String name;
    private String sex;
    private DepartmentDTO department;
    private PostTypeDTO postType;
    private String telephone;
    private UserDTO user;
    private EnterpriseDTO enterprise;

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }

    public PostTypeDTO getPostType() {
        return postType;
    }

    public void setPostType(PostTypeDTO postType) {
        this.postType = postType;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setEnterprise(EnterpriseDTO enterprise) {
        this.enterprise = enterprise;
    }

    public EnterpriseDTO getEnterprise() {
        return enterprise;
    }
}
