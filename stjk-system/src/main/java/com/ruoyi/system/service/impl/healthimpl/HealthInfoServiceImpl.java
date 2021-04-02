package com.ruoyi.system.service.impl.healthimpl;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.health.HealthInfoMapper;
import com.ruoyi.system.health.domain.HealthInfo;
import com.ruoyi.system.health.service.IHealthInfoService;

import javax.annotation.Resource;

/**
 * 重大阳性信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
@Service
public class HealthInfoServiceImpl implements IHealthInfoService 
{
    @Resource
    private HealthInfoMapper healthInfoMapper;

    /**
     * 查询重大阳性信息
     * 
     * @param infoId 重大阳性信息ID
     * @return 重大阳性信息
     */
    @Override
    public HealthInfo selectHealthInfoById(Long infoId)
    {
        return healthInfoMapper.selectHealthInfoById(infoId);
    }

    /**
     * 查询重大阳性信息列表
     * 
     * @param healthInfo 重大阳性信息
     * @return 重大阳性信息
     */
    @Override
    public List<HealthInfo> selectHealthInfoList(HealthInfo healthInfo)
    {
        return healthInfoMapper.selectHealthInfoList(healthInfo);
    }

    /**
     * 新增重大阳性信息
     * 
     * @param healthInfo 重大阳性信息
     * @return 结果
     */
    @Override
    public int insertHealthInfo(HealthInfo healthInfo)
    {
        healthInfo.setCreateTime(DateUtils.getNowDate());
        return healthInfoMapper.insertHealthInfo(healthInfo);
    }

    /**
     * 修改重大阳性信息
     * 
     * @param healthInfo 重大阳性信息
     * @return 结果
     */
    @Override
    public int updateHealthInfo(HealthInfo healthInfo)
    {
        healthInfo.setUpdateTime(DateUtils.getNowDate());
        return healthInfoMapper.updateHealthInfo(healthInfo);
    }

    /**
     * 批量删除重大阳性信息
     * 
     * @param infoIds 需要删除的重大阳性信息ID
     * @return 结果
     */
    @Override
    public int deleteHealthInfoByIds(Long[] infoIds)
    {
        return healthInfoMapper.deleteHealthInfoByIds(infoIds);
    }

    /**
     * 删除重大阳性信息信息
     * 
     * @param infoId 重大阳性信息ID
     * @return 结果
     */
    @Override
    public int deleteHealthInfoById(Long infoId)
    {
        return healthInfoMapper.deleteHealthInfoById(infoId);
    }
}
