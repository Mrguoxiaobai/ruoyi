package com.ruoyi.system.domain.ecg;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 历年体检数据对象 ecg_year_count
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public class EcgYearCount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long yearCountId;

    /** 年份 */
    @Excel(name = "年份")
    private Long year;

    /** 体检人数 */
    @Excel(name = "体检人数")
    private Long pCount;

    /** 体检率 */
    @Excel(name = "体检率")
    private String pCentage;

    public void setYearCountId(Long yearCountId) 
    {
        this.yearCountId = yearCountId;
    }

    public Long getYearCountId() 
    {
        return yearCountId;
    }
    public void setYear(Long year) 
    {
        this.year = year;
    }

    public Long getYear() 
    {
        return year;
    }
    public void setpCount(Long pCount) 
    {
        this.pCount = pCount;
    }

    public Long getpCount() 
    {
        return pCount;
    }
    public void setpCentage(String pCentage) 
    {
        this.pCentage = pCentage;
    }

    public String getpCentage() 
    {
        return pCentage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("yearCountId", getYearCountId())
            .append("year", getYear())
            .append("pCount", getpCount())
            .append("pCentage", getpCentage())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
