package com.andy.waterdam.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

public class Dam {
    private Long id;
    private String name;
    private String river;
    private Long capacity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp completeDate; // 改为与数据库字段名匹配的驼峰命名

    public Dam(Long id, String name, String river, Long capacity, Timestamp completeDate) {
        this.id = id;
        this.name = name;
        this.river = river;
        this.capacity = capacity;
        this.completeDate = completeDate;
    }

    public Dam() {
    }

    public Date getCompleteDate() { // 更改getter和setter方法名
        return completeDate;
    }

    public void setCompleteDate(Timestamp completeDate) { // 更改getter和setter方法名
        this.completeDate = completeDate;
    }

    // 为了兼容现有代码，保留原有的getter和setter
    public Date getCreateTime() {
        return completeDate;
    }

    public void setCreateTime(Timestamp createTime) {
        this.completeDate = createTime;
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
