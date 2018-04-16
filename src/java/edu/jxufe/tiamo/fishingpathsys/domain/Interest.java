package edu.jxufe.tiamo.fishingpathsys.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "interest", schema = "FishingPathSystem")
public class Interest {
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

    @ManyToOne(targetEntity = InterestType.class)
    @JoinColumn(name = "interest_type_id", referencedColumnName = "id", nullable = false)
    private InterestType interestType;
    @ManyToOne(targetEntity = Staff.class)
    @JoinColumn(name = "staff_id", referencedColumnName = "id", nullable = false)
    private Staff staff;

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

    public InterestType getInterestType() {
        return interestType;
    }

    public void setInterestType(InterestType interestType) {
        this.interestType = interestType;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interest interest = (Interest) o;
        return id == interest.id &&
                Objects.equals(name, interest.name) &&
                Objects.equals(description, interest.description) &&
                Objects.equals(interestType, interest.interestType) &&
                Objects.equals(staff, interest.staff);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, interestType, staff);
    }
}
