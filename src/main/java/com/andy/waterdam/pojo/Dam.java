package com.andy.waterdam.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class Dam {
    private Long id;
    private String name;
    private String river;
    private Long capacity;
    private Timestamp createTime;

    public Dam(Long id, String name, String river, Long capacity, Timestamp createTime) {
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

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
