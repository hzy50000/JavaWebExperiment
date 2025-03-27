package com.andy.waterdam.mapper;

import com.andy.waterdam.pojo.Dam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DamMapper {
    public List<Dam> getWaterDamList();
    public Dam getWaterDamById(Long id);
    public void insertWaterDam(Dam dam);
    public void updateWaterDam(Dam dam);
    public void deleteWaterDamById(Long id);
}
