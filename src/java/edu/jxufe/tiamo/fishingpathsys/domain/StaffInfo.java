package edu.jxufe.tiamo.fishingpathsys.domain;

import javax.persistence.*;

@Entity
@Table(name = "staff_info")
public class StaffInfo {

    @Id
    @Column(name = "staff_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "sex")
    private Integer sex;

    public StaffInfo() {
    }

    public StaffInfo(String name, Integer sex) {
        this.name = name;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
