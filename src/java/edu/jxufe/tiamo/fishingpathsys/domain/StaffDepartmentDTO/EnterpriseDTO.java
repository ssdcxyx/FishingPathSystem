package edu.jxufe.tiamo.fishingpathsys.domain.StaffDepartmentDTO;

import edu.jxufe.tiamo.fishingpathsys.domain.Department;
import edu.jxufe.tiamo.fishingpathsys.domain.Enterprise;

import java.util.List;

public class EnterpriseDTO {

    private Enterprise enterprise;

    private List<DepartmentDTO> departmentList;

    public EnterpriseDTO() {
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public List<DepartmentDTO> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<DepartmentDTO> departmentList) {
        this.departmentList = departmentList;
    }


}
