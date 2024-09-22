package com.www.service.impl;

import com.www.pojo.TestEntity;
import com.www.mapper.TestMapper;
import com.www.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 夕颜
 * @since 2024-05-12
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TestEntity> implements TestService {

}
