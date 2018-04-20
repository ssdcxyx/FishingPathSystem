package edu.jxufe.tiamo.fishingpathsys.domain.StaffTableDTO;

import java.util.Map;

public class ResponseDTO {

    private String action;
    private Map<String,DataDTO> data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, DataDTO> getData() {
        return data;
    }

    public void setData(Map<String, DataDTO> data) {
        this.data = data;
    }

}
