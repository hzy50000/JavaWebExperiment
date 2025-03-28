package com.andy.waterdam.controller;

import com.andy.waterdam.pojo.Dam;
import com.andy.waterdam.pojo.DamRequest;
import com.andy.waterdam.service.DamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

    @PostMapping("/add")
    @ResponseBody
    public String addDam(@RequestBody DamRequest damRequest) {
        try {
            Dam dam = new Dam();
            dam.setName(damRequest.getName());
            dam.setRiver(damRequest.getRiver());
            dam.setCapacity(Long.parseLong(damRequest.getCapacity()));
            dam.setCompleteDate(new Timestamp(Long.parseLong(damRequest.getComplete())));
            damService.insertWaterDam(dam);
            return "添加成功";
        } catch (Exception e) {
            System.out.printf("添加失败" + e.getMessage());
            return "添加失败";
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateDam(@RequestBody DamRequest damRequest) {
        try {
            Dam dam = new Dam();
            dam.setId(Long.parseLong(damRequest.getId()));
            dam.setName(damRequest.getName());
            dam.setRiver(damRequest.getRiver());
            dam.setCapacity(Long.parseLong(damRequest.getCapacity()));
            dam.setCompleteDate(new Timestamp(Long.parseLong(damRequest.getComplete())));
            damService.updateWaterDam(dam);
            return "更新成功";
        } catch (Exception e) {
            System.out.printf("更新失败" + e.getMessage());
            return "更新失败";
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteDam(@RequestBody DamRequest damRequest) {
        try {
            damService.deleteWaterDamById(Long.parseLong(damRequest.getId()));
            return "删除成功";
        } catch (Exception e) {
            System.out.printf("删除失败" + e.getMessage());
            return "删除失败";
        }
    }
}
