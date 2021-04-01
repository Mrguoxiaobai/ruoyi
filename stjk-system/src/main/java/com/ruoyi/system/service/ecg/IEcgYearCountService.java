package com.ruoyi.system.service.ecg;

import com.ruoyi.system.domain.ecg.EcgYearCount;

import java.util.List;

/**
 * 历年体检数据Service接口
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public interface IEcgYearCountService 
{
    /**
     * 查询历年体检数据
     * 
     * @param yearCountId 历年体检数据ID
     * @return 历年体检数据
     */
    public EcgYearCount selectEcgYearCountById(Long yearCountId);

    /**
     * 查询历年体检数据列表
     * 
     * @param ecgYearCount 历年体检数据
     * @return 历年体检数据集合
     */
    public List<EcgYearCount> selectEcgYearCountList(EcgYearCount ecgYearCount);

    /**
     * 新增历年体检数据
     * 
     * @param ecgYearCount 历年体检数据
     * @return 结果
     */
    public int insertEcgYearCount(EcgYearCount ecgYearCount);

    /**
     * 修改历年体检数据
     * 
     * @param ecgYearCount 历年体检数据
     * @return 结果
     */
    public int updateEcgYearCount(EcgYearCount ecgYearCount);

    /**
     * 批量删除历年体检数据
     * 
     * @param yearCountIds 需要删除的历年体检数据ID
     * @return 结果
     */
    public int deleteEcgYearCountByIds(Long[] yearCountIds);

    /**
     * 删除历年体检数据信息
     * 
     * @param yearCountId 历年体检数据ID
     * @return 结果
     */
    public int deleteEcgYearCountById(Long yearCountId);
}
