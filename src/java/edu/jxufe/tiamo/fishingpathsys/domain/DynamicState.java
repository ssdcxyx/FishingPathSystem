package edu.jxufe.tiamo.fishingpathsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dynamic_state", schema = "FishingPathSystem")
public class DynamicState {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Basic
    @Column(name = "content")
    private String content;

    @Basic
    @Column(name = "time")
    private String time;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    private User user;

    @OneToMany(targetEntity = DynamicStateLike.class,mappedBy = "dynamicState",fetch = FetchType.LAZY)
    private List<DynamicStateLike> dynamicStateLikeList;

    @OneToMany(targetEntity = DynamicStateComment.class,mappedBy = "dynamicState",fetch = FetchType.LAZY)
    private List<DynamicStateComment> dynamicStateCommentList;


    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<DynamicStateLike> getDynamicStateLikeList() {
        return dynamicStateLikeList;
    }

    public void setDynamicStateLikeList(List<DynamicStateLike> dynamicStateLikeList) {
        this.dynamicStateLikeList = dynamicStateLikeList;
    }

    public List<DynamicStateComment> getDynamicStateCommentList() {
        return dynamicStateCommentList;
    }

    public void setDynamicStateCommentList(List<DynamicStateComment> dynamicStateCommentList) {
        this.dynamicStateCommentList = dynamicStateCommentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicState that = (DynamicState) o;
        return id == that.id &&
                Objects.equals(content, that.content) &&
                Objects.equals(time, that.time) &&
                Objects.equals(user, that.user) &&
                Objects.equals(dynamicStateCommentList, that.dynamicStateCommentList) &&
                Objects.equals(dynamicStateLikeList, that.dynamicStateLikeList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, content, time, user, dynamicStateCommentList, dynamicStateLikeList);
    }
}
