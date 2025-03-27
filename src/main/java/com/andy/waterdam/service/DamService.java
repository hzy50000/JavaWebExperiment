package com.andy.waterdam.service;

import com.andy.waterdam.pojo.Dam;

import java.util.List;


public interface DamService {
    public List<Dam> getWaterDamList();
    public List<Dam> getDamList();
    public Dam getWaterDamById(Long id);
    public void insertWaterDam(Dam dam);
    public void updateWaterDam(Dam dam);
    public void deleteWaterDamById(Long id);
}
