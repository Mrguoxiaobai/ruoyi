package com.ruoyi.web.controller.ecg;

import com.ruoyi.system.mapper.ecg.StjksMapper;
import com.ruoyi.system.mapper.ecg.PeisPatientMapper;

import javax.annotation.Resource;

public class Peis {

    public static void main(String[] args) {

        new test().te();

    }
}
class test{
    @Resource
    private PeisPatientMapper peisPatientMapper;
    @Resource
    private StjksMapper stjksMapper;

    public void te(){
        //System.out.println(stjksMapper.count());
     /*   List<PeisPatient> peisPatients = stjksMapper.peisList();
        List<PeisPatient> peisPatients1 = stjksMapper.warePeisList();
        for (PeisPatient  p:peisPatients) {
            peisPatientMapper.insertPeisPatient(p);
        }
        for (PeisPatient p1:peisPatients1) {
            peisPatientMapper.insertPeisPatient(p1);
        }*/
    }

}