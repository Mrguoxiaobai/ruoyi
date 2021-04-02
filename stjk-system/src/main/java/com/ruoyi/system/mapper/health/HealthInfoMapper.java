package com.ruoyi.system.mapper.health;

import java.util.List;
import com.ruoyi.system.health.domain.HealthInfo;

/**
 * 重大阳性信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
public interface HealthInfoMapper 
{
    /**
     * 查询重大阳性信息
     * 
     * @param infoId 重大阳性信息ID
     * @return 重大阳性信息
     */
    public HealthInfo selectHealthInfoById(Long infoId);

    /**
     * 查询重大阳性信息列表
     * 
     * @param healthInfo 重大阳性信息
     * @return 重大阳性信息集合
     */
    public List<HealthInfo> selectHealthInfoList(HealthInfo healthInfo);

    /**
     * 新增重大阳性信息
     * 
     * @param healthInfo 重大阳性信息
     * @return 结果
     */
    public int insertHealthInfo(HealthInfo healthInfo);

    /**
     * 修改重大阳性信息
     * 
     * @param healthInfo 重大阳性信息
     * @return 结果
     */
    public int updateHealthInfo(HealthInfo healthInfo);

    /**
     * 删除重大阳性信息
     * 
     * @param infoId 重大阳性信息ID
     * @return 结果
     */
    public int deleteHealthInfoById(Long infoId);

    /**
     * 批量删除重大阳性信息
     * 
     * @param infoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteHealthInfoByIds(Long[] infoIds);
}
