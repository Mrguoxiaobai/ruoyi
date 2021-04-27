package com.ruoyi.system.mapper.ecg;


import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.system.domain.ecg.PeisPatient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@DataSource(value = DataSourceType.SLAVE)
public interface StjksMapper {
/*
    @Select("select count(*) from PeisPatient")
    public int count();*/
    @Select("select  ID_Patient as  idPatient, PatientCode as patientcode, PatientCardNo as patientcardno, PatientBizNo as patientbizno, IDCardNo as idcardno, PatientName as patientname, Input_Code as inputCode, Org_Name as orgName, Org_Depart as orgDepart, Org_DepartSubA as orgDepartsuba, Org_DepartSubB as orgDepartsubb, Org_DepartSubC as orgDepartsubc, Org_DepartSubD as orgDepartsubd, Sex as sex, BirthDate as birthdate, Age as age, Marriage as marriage, Nation as nation, Address as address, Email as email, Phone as phone, Education as education, Occupation as occupation, ResArea as resarea, DateCreated as datecreated, F_Registered as fRegistered, DateRegister as dateregister, Position_Code as positionCode, GuidanceNote as guidancenote, WorkNo as workno, DoctorReg as doctorreg, ExamSuite_Name as examsuiteName, F_Paused as fPaused, DoctorFinal_Name_R doctorfinalNameR, DateFinalExamed as datefinalexamed, DateReportPrinted as datereportprinted, Note as note, Conclusion, ConclusionSummary, Suggestion, PositiveSummary from PeisPatient where PatientBizNo is not null")
    public List<PeisPatient> peisList();

    @Select("select top 1  ID_Patient as  idPatient, PatientCode as patientcode, PatientCardNo as patientcardno, PatientBizNo as patientbizno, IDCardNo as idcardno, PatientName as patientname, Input_Code as inputCode, Org_Name as orgName, Org_Depart as orgDepart, Org_DepartSubA as orgDepartsuba, Org_DepartSubB as orgDepartsubb, Org_DepartSubC as orgDepartsubc, Org_DepartSubD as orgDepartsubd, Sex as sex, BirthDate as birthdate, Age as age, Marriage as marriage, Nation as nation, Address as address, Email as email, Phone as phone, Education as education, Occupation as occupation, ResArea as resarea, DateCreated as datecreated, F_Registered as fRegistered, DateRegister as dateregister, Position_Code as positionCode, GuidanceNote as guidancenote, WorkNo as workno, DoctorReg as doctorreg, ExamSuite_Name as examsuiteName, F_Paused as fPaused, DoctorFinal_Name_R doctorfinalNameR, DateFinalExamed as datefinalexamed, DateReportPrinted as datereportprinted, Note as note, Conclusion, ConclusionSummary, Suggestion, PositiveSummary from PeisPatient where IDCardNo =#{IDCardNo}")
    public PeisPatient peisListById(@Param(value = "IDCardNo") String IDCardNo);

    @Select("select  ID_Patient as  idPatient, PatientCode as patientcode, PatientCardNo as patientcardno, PatientBizNo as patientbizno, IDCardNo as idcardno, PatientName as patientname, Input_Code as inputCode, Org_Name as orgName, Org_Depart as orgDepart, Org_DepartSubA as orgDepartsuba, Org_DepartSubB as orgDepartsubb, Org_DepartSubC as orgDepartsubc, Org_DepartSubD as orgDepartsubd, Sex as sex, BirthDate as birthdate, Age as age, Marriage as marriage, Nation as nation, Address as address, Email as email, Phone as phone, Education as education, Occupation as occupation, ResArea as resarea, DateCreated as datecreated, F_Registered as fRegistered, DateRegister as dateregister, Position_Code as positionCode, GuidanceNote as guidancenote, WorkNo as workno, DoctorReg as doctorreg, ExamSuite_Name as examsuiteName, F_Paused as fPaused, DoctorFinal_Name_R doctorfinalNameR, DateFinalExamed as datefinalexamed, DateReportPrinted as datereportprinted, Note as note, Conclusion, ConclusionSummary, Suggestion, PositiveSummary from WarePatient where PatientBizNo is not null")
    public List<PeisPatient> warePeisList();



}
