package com.andy.waterdam.pojo;

import java.util.Date;

public class Dam {
    private Integer id;
    private String name;
    private String river;
    private Integer capacity;
    private Date createTime;

    public Dam(Integer id, String name, String river, Integer capacity, Date createTime) {
        this.id = id;
        this.name = name;
        this.river = river;
        this.capacity = capacity;
        this.createTime = createTime;
    }

    public Dam() {
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
