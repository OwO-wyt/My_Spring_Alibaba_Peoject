package com.www.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.www.config.DataSourceConfig;
import com.www.pojo.SystemLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 夕颜
 * @since 2024-05-12
 */
@DS(DataSourceConfig.SHARDING_DATA_SOURCE_NAME)
@Mapper
public interface SystemLogMapper extends BaseMapper<SystemLogEntity> {



    void saveLog(@Param("systemLog") SystemLogEntity systemLogEntity);


}
