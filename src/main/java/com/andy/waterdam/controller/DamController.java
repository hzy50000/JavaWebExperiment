package com.andy.waterdam.controller;

import com.andy.waterdam.pojo.Dam;
import com.andy.waterdam.service.DamService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/dam/")
public class DamController {
    @Autowired
    private DamService damService;

    @GetMapping("/list")
    public List<Dam> listDam() {
        return damService.getDamList();
    }

    @GetMapping("/add")
    public void addDam(HttpServletRequest request) {
        try {
            Dam dam = new Dam();
            dam.setName(request.getParameter("name"));
            dam.setRiver(request.getParameter("river"));
            dam.setCapacity(Long.parseLong(request.getParameter("capacity")));
            dam.setCreateTime(new Timestamp(Long.parseLong(request.getParameter("createTime"))));
            damService.insertWaterDam(dam);
        } catch (Exception e) {
            System.out.printf("添加失败" + e.getMessage());
        }
    }

    @GetMapping("/update")
    public void updateDam(HttpServletRequest request) {
        try {
            Dam dam = new Dam();
            dam.setId(Long.parseLong(request.getParameter("id")));
            dam.setName(request.getParameter("name"));
            dam.setRiver(request.getParameter("river"));
            dam.setCapacity(Long.parseLong(request.getParameter("capacity")));
            dam.setCreateTime(new Timestamp(Long.parseLong(request.getParameter("createTime"))));
            damService.updateWaterDam(dam);
        } catch (Exception e) {
            System.out.printf("更新失败" + e.getMessage());
        }
    }

    @GetMapping("/delete")
    public void deleteDam(@RequestParam Long id) {
        damService.deleteWaterDamById(id);
    }


}
