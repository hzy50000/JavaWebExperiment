package com.andy.waterdam.service.impl;

import com.andy.waterdam.mapper.DamMapper;
import com.andy.waterdam.pojo.Dam;
import com.andy.waterdam.service.DamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamServiceImpl implements DamService {
    @Autowired
    private DamMapper damMapper;

    @Override
    public List<Dam> getDamList() {
        System.out.println(damMapper.getWaterDamList() + "\n\n\n\n\n");
        return damMapper.getWaterDamList();
    }

    @Override
    public Dam getWaterDamById(Long id) {
        return damMapper.getWaterDamById(id);
    }

    @Override
    public void insertWaterDam(Dam dam) {
        damMapper.insertWaterDam(dam);
    }

    @Override
    public void updateWaterDam(Dam dam) {
        damMapper.updateWaterDam(dam);
    }

    @Override
    public void deleteWaterDamById(Long id) {
        damMapper.deleteWaterDamById(id);
    }
}
