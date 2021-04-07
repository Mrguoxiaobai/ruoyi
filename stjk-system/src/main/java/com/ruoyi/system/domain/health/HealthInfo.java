package com.ruoyi.system.domain.health;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 重大阳性信息对象 health_info
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
public class HealthInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long infoId;

    /** 体检号 */
    @Excel(name = "体检号")
    private String infoCode;

    /** 身份号 */
    @Excel(name = "身份号")
    private String infoCardno;

    /** 姓名 */
    @Excel(name = "姓名")
    private String infoName;

    /** 用户性别（0男 1女 2未知） */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String infoSex;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long infoAge;

    /** 科室名称 */
    @Excel(name = "科室名称")
    private String deptName;

    private Long deptId;


    /** 项目名称 */
    @Excel(name = "项目名称")
    private String healthItemName;

    private Long healthItemId;

    /** 手机号 */
    @Excel(name = "手机号")
    private String infoPhone;

    /** 是否通知本人(0未通知 1已通知) */
    @Excel(name = "是否通知本人")
    private String infoInform;

    /** 重大阳性结果 */
    @Excel(name = "重大阳性结果")
    private String infoResult;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String infoDept;

    /** 诊治经过 */
    @Excel(name = "诊治经过")
    private String infoHospital;

    /** 诊治结果 */
    @Excel(name = "诊治结果")
    private String infoConsequence;

    /** 体检医师 */
    @Excel(name = "体检医师")
    private String infoDoctor;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getHealthItemName() {
        return healthItemName;
    }

    public void setHealthItemName(String healthItemName) {
        this.healthItemName = healthItemName;
    }

    public void setInfoId(Long infoId) 
    {
        this.infoId = infoId;
    }

    public Long getInfoId() 
    {
        return infoId;
    }
    public void setInfoCode(String infoCode) 
    {
        this.infoCode = infoCode;
    }

    public String getInfoCode() 
    {
        return infoCode;
    }
    public void setInfoCardno(String infoCardno) 
    {
        this.infoCardno = infoCardno;
    }

    public String getInfoCardno() 
    {
        return infoCardno;
    }
    public void setInfoName(String infoName) 
    {
        this.infoName = infoName;
    }

    public String getInfoName() 
    {
        return infoName;
    }
    public void setInfoSex(String infoSex) 
    {
        this.infoSex = infoSex;
    }

    public String getInfoSex() 
    {
        return infoSex;
    }
    public void setInfoAge(Long infoAge) 
    {
        this.infoAge = infoAge;
    }

    public Long getInfoAge() 
    {
        return infoAge;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setHealthItemId(Long healthItemId) 
    {
        this.healthItemId = healthItemId;
    }

    public Long getHealthItemId() 
    {
        return healthItemId;
    }
    public void setInfoPhone(String infoPhone) 
    {
        this.infoPhone = infoPhone;
    }

    public String getInfoPhone() 
    {
        return infoPhone;
    }
    public void setInfoInform(String infoInform) 
    {
        this.infoInform = infoInform;
    }

    public String getInfoInform() 
    {
        return infoInform;
    }
    public void setInfoResult(String infoResult) 
    {
        this.infoResult = infoResult;
    }

    public String getInfoResult() 
    {
        return infoResult;
    }
    public void setInfoDept(String infoDept) 
    {
        this.infoDept = infoDept;
    }

    public String getInfoDept() 
    {
        return infoDept;
    }
    public void setInfoHospital(String infoHospital) 
    {
        this.infoHospital = infoHospital;
    }

    public String getInfoHospital() 
    {
        return infoHospital;
    }
    public void setInfoConsequence(String infoConsequence) 
    {
        this.infoConsequence = infoConsequence;
    }

    public String getInfoConsequence() 
    {
        return infoConsequence;
    }
    public void setInfoDoctor(String infoDoctor) 
    {
        this.infoDoctor = infoDoctor;
    }

    public String getInfoDoctor() 
    {
        return infoDoctor;
    }

    @Override
    public String toString() {
        return "HealthInfo{" +
                "infoId=" + infoId +
                ", infoCode='" + infoCode + '\'' +
                ", infoCardno='" + infoCardno + '\'' +
                ", infoName='" + infoName + '\'' +
                ", infoSex='" + infoSex + '\'' +
                ", infoAge=" + infoAge +
                ", deptName='" + deptName + '\'' +
                ", deptId=" + deptId +
                ", healthItemName='" + healthItemName + '\'' +
                ", healthItemId=" + healthItemId +
                ", infoPhone='" + infoPhone + '\'' +
                ", infoInform='" + infoInform + '\'' +
                ", infoResult='" + infoResult + '\'' +
                ", infoDept='" + infoDept + '\'' +
                ", infoHospital='" + infoHospital + '\'' +
                ", infoConsequence='" + infoConsequence + '\'' +
                ", infoDoctor='" + infoDoctor + '\'' +
                '}';
    }
}
