package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.HealthItemMapper;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.HealthItem;
import com.ruoyi.system.service.IHealthItemService;

import javax.annotation.Resource;

/**
 * 重大阳性项目Service业务层处理
 * 
 * @author Mrguo
 * @date 2021-03-30
 */
@Service
public class HealthItemServiceImpl implements IHealthItemService 
{
    @Resource
    private HealthItemMapper healthItemMapper;

    /**
     * 查询重大阳性项目
     * 
     * @param healthItemId 重大阳性项目ID
     * @return 重大阳性项目
     */
    @Override
    public HealthItem selectHealthItemById(Long healthItemId)
    {
        return healthItemMapper.selectHealthItemById(healthItemId);
    }

    /**
     * 查询重大阳性项目列表
     * 
     * @param healthItem 重大阳性项目
     * @return 重大阳性项目
     */
    @Override
    public List<HealthItem> selectHealthItemList(HealthItem healthItem)
    {
        return healthItemMapper.selectHealthItemList(healthItem);
    }

    /**
     * 新增重大阳性项目
     * 
     * @param healthItem 重大阳性项目
     * @return 结果
     */
    @Override
    public int insertHealthItem(HealthItem healthItem)
    {
        healthItem.setCreateTime(DateUtils.getNowDate());
        return healthItemMapper.insertHealthItem(healthItem);
    }

    /**
     * 修改重大阳性项目
     * 
     * @param healthItem 重大阳性项目
     * @return 结果
     */
    @Override
    public int updateHealthItem(HealthItem healthItem)
    {
        healthItem.setUpdateTime(DateUtils.getNowDate());
        return healthItemMapper.updateHealthItem(healthItem);
    }

    /**
     * 批量删除重大阳性项目
     * 
     * @param healthItemIds 需要删除的重大阳性项目ID
     * @return 结果
     */
    @Override
    public int deleteHealthItemByIds(Long[] healthItemIds)
    {
        return healthItemMapper.deleteHealthItemByIds(healthItemIds);
    }

    /**
     * 删除重大阳性项目信息
     * 
     * @param healthItemId 重大阳性项目ID
     * @return 结果
     */
    @Override
    public int deleteHealthItemById(Long healthItemId)
    {
        return healthItemMapper.deleteHealthItemById(healthItemId);
    }
}
