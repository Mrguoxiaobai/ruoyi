package com.ruoyi.system.domain.health;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 重大阳性项目对象 health_item
 * 
 * @author Mrguo
 * @date 2021-03-30
 */
public class HealthItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long healthItemId;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String healthItemName;

    /** 科室名称 */
    @Excel(name = "科室名称")
    private String deptName;

    @Override
    public String toString() {
        return "HealthItem{" +
                "healthItemId=" + healthItemId +
                ", deptId=" + deptId +
                ", healthItemName='" + healthItemName + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setHealthItemId(Long healthItemId)
    {
        this.healthItemId = healthItemId;
    }

    public Long getHealthItemId() 
    {
        return healthItemId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setHealthItemName(String healthItemName) 
    {
        this.healthItemName = healthItemName;
    }

    public String getHealthItemName() 
    {
        return healthItemName;
    }

}
