package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "post_type", schema = "FishingPathSystem")
public class PostType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_type_id")
    @JsonBackReference
    private List<Staff> staffList;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostType postType = (PostType) o;
        return id == postType.id &&
                Objects.equals(name, postType.name) &&
                Objects.equals(description, postType.description) &&
                Objects.equals(staffList, postType.staffList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, staffList);
    }
}
