package edu.jxufe.tiamo.fishingpathsys.domain.StaffTableDTO;

import java.util.Map;

public class DeleteResponseDao {

    private String action;
    private Map<String,Object> data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
