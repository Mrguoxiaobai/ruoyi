package com.ruoyi.system.service.impl.ecgimpl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ecg.EcgYearCount;
import com.ruoyi.system.mapper.ecg.EcgYearCountMapper;
import com.ruoyi.system.service.ecg.IEcgYearCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 历年体检数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
@Service
public class EcgYearCountServiceImpl implements IEcgYearCountService
{
    @Resource
    private EcgYearCountMapper ecgYearCountMapper;

    /**
     * 查询历年体检数据
     * 
     * @param yearCountId 历年体检数据ID
     * @return 历年体检数据
     */
    @Override
    public EcgYearCount selectEcgYearCountById(Long yearCountId)
    {
        return ecgYearCountMapper.selectEcgYearCountById(yearCountId);
    }

    /**
     * 查询历年体检数据列表
     * 
     * @param ecgYearCount 历年体检数据
     * @return 历年体检数据
     */
    @Override
    public List<EcgYearCount> selectEcgYearCountList(EcgYearCount ecgYearCount)
    {
        return ecgYearCountMapper.selectEcgYearCountList(ecgYearCount);
    }

    /**
     * 新增历年体检数据
     * 
     * @param ecgYearCount 历年体检数据
     * @return 结果
     */
    @Override
    public int insertEcgYearCount(EcgYearCount ecgYearCount)
    {
        ecgYearCount.setCreateTime(DateUtils.getNowDate());
        return ecgYearCountMapper.insertEcgYearCount(ecgYearCount);
    }

    /**
     * 修改历年体检数据
     * 
     * @param ecgYearCount 历年体检数据
     * @return 结果
     */
    @Override
    public int updateEcgYearCount(EcgYearCount ecgYearCount)
    {
        ecgYearCount.setUpdateTime(DateUtils.getNowDate());
        return ecgYearCountMapper.updateEcgYearCount(ecgYearCount);
    }

    /**
     * 批量删除历年体检数据
     * 
     * @param yearCountIds 需要删除的历年体检数据ID
     * @return 结果
     */
    @Override
    public int deleteEcgYearCountByIds(Long[] yearCountIds)
    {
        return ecgYearCountMapper.deleteEcgYearCountByIds(yearCountIds);
    }

    /**
     * 删除历年体检数据信息
     * 
     * @param yearCountId 历年体检数据ID
     * @return 结果
     */
    @Override
    public int deleteEcgYearCountById(Long yearCountId)
    {
        return ecgYearCountMapper.deleteEcgYearCountById(yearCountId);
    }
}
