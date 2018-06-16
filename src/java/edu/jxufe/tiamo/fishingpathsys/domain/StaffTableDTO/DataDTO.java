package edu.jxufe.tiamo.fishingpathsys.domain.StaffTableDTO;

import edu.jxufe.tiamo.fishingpathsys.domain.Enterprise;

import java.util.Map;

public class DataDTO {

    private String jobNumber;
    private DepartmentDTO department;
    private PostTypeDTO postType;
    private UserDTO user;
    private EnterpriseDTO enterprise;

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
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
