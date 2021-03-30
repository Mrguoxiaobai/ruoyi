package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.HealthItem;

/**
 * 重大阳性项目Service接口
 * 
 * @author Mrguo
 * @date 2021-03-30
 */
public interface IHealthItemService 
{
    /**
     * 查询重大阳性项目
     * 
     * @param healthItemId 重大阳性项目ID
     * @return 重大阳性项目
     */
    public HealthItem selectHealthItemById(Long healthItemId);

    /**
     * 查询重大阳性项目列表
     * 
     * @param healthItem 重大阳性项目
     * @return 重大阳性项目集合
     */
    public List<HealthItem> selectHealthItemList(HealthItem healthItem);

    /**
     * 新增重大阳性项目
     * 
     * @param healthItem 重大阳性项目
     * @return 结果
     */
    public int insertHealthItem(HealthItem healthItem);

    /**
     * 修改重大阳性项目
     * 
     * @param healthItem 重大阳性项目
     * @return 结果
     */
    public int updateHealthItem(HealthItem healthItem);

    /**
     * 批量删除重大阳性项目
     * 
     * @param healthItemIds 需要删除的重大阳性项目ID
     * @return 结果
     */
    public int deleteHealthItemByIds(Long[] healthItemIds);

    /**
     * 删除重大阳性项目信息
     * 
     * @param healthItemId 重大阳性项目ID
     * @return 结果
     */
    public int deleteHealthItemById(Long healthItemId);
}
