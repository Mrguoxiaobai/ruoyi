package com.ruoyi.system.mapper;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
@DataSource(value = DataSourceType.SLAVE)
public interface TestMapper {

    @Select("select count(*) from  PeisPatient")
    public int count();

}
