package edu.jxufe.tiamo.fishingpathsys.domain;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    //标识属性
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "account")
    private String account;
    @Column(name = "password")
    private String password;
    @Column(name = "type")
    private String type;
    @OneToOne(targetEntity = StaffInfo.class)
    @JoinColumn(name = "staff_info_id",referencedColumnName = "staff_info_id")
    private StaffInfo staffInfo;

    public User() {
    }

    public User(String account, String password, String type, StaffInfo staffInfo) {
        this.account = account;
        this.password = password;
        this.type = type;
        this.staffInfo = staffInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public StaffInfo getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(StaffInfo staffInfo) {
        this.staffInfo = staffInfo;
    }
}
