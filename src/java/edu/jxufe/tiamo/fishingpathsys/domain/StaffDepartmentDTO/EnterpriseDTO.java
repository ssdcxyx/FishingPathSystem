package edu.jxufe.tiamo.fishingpathsys.domain.StaffDepartmentDTO;

import edu.jxufe.tiamo.fishingpathsys.domain.Department;
import edu.jxufe.tiamo.fishingpathsys.domain.Enterprise;

import java.util.List;

public class EnterpriseDTO {

    private Enterprise enterprise;

    private List<DepartmentDTO> departmentDTOList;

    public EnterpriseDTO() {
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public List<DepartmentDTO> getDepartmentDTOList() {
        return departmentDTOList;
    }

    public void setDepartmentDTOList(List<DepartmentDTO> departmentDTOList) {
        this.departmentDTOList = departmentDTOList;
    }
}
