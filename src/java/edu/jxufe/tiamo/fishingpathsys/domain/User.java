package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user",schema = "FishingPathSystem")
@JsonIgnoreProperties(value={"staff","enterprise","admin"})
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Basic
    @Column(name = "account")
    private String account;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "role")
    private short role;

    public short getId() {
        return id;
    }

    public void setId(short id) {
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

    public short getRole() {
        return role;
    }

    public void setRole(short role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(account, user.account) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role,user.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, account, password, role);
    }
}
