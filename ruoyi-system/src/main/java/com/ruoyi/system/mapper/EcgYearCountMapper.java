package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.EcgYearCount;
/**
 * 历年体检数据Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public interface EcgYearCountMapper 
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
     * 删除历年体检数据
     * 
     * @param yearCountId 历年体检数据ID
     * @return 结果
     */
    public int deleteEcgYearCountById(Long yearCountId);

    /**
     * 批量删除历年体检数据
     * 
     * @param yearCountIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteEcgYearCountByIds(Long[] yearCountIds);
}
