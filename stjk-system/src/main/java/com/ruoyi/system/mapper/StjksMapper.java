package com.ruoyi.system.mapper;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.system.domain.ecg.PeisPatient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
@DataSource(value = DataSourceType.SLAVE)
public interface StjksMapper {

    @Select("select count(*) from  PeisPatient")
    public int count();

   /* @Select("select top 1 ID_Patient as idPatient, PatientCode as patientcode, PatientCardNo as patientcardno, PatientBizNo as patientbizno, IDCardNo as idcardno, PatientName as patientname from PeisPatient where PatientBizNo is not null")
    public List<PeisPatient> peisList();

    @Select("select top 1 ID_Patient , PatientCode, PatientCardNo, PatientBizNo, IDCardNo, PatientName, Input_Code, Org_Name, Org_Depart, Org_DepartSubA, Org_DepartSubB, Org_DepartSubC, Org_DepartSubD, Sex, BirthDate, Age, Marriage, Nation, Address, Email, Phone, Education, Occupation, ResArea, DateCreated, F_Registered, DateRegister, Position_Code, GuidanceNote, WorkNo, DoctorReg, ExamSuite_Name, F_Paused, DoctorFinal_Name_R, DateFinalExamed, DateReportPrinted, Note, Conclusion, ConclusionSummary, Suggestion, PositiveSummary from WarePatient where PatientBizNo is not null")
    public List<PeisPatient> warePeisList();*/



}
