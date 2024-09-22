package com.www.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.www.config.DataSourceConfig;
import com.www.pojo.TestEntity;
import org.apache.ibatis.annotations.Mapper;

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
public interface TestMapper extends BaseMapper<TestEntity> {

}
