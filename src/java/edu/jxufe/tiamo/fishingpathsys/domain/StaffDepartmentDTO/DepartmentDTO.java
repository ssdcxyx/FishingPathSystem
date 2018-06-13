package edu.jxufe.tiamo.fishingpathsys.domain.StaffDepartmentDTO;

import edu.jxufe.tiamo.fishingpathsys.domain.Department;
import edu.jxufe.tiamo.fishingpathsys.domain.Staff;

import java.util.List;

public class DepartmentDTO {

    private Department department;

    private List<Staff> staffList;

    public DepartmentDTO() {
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
}
