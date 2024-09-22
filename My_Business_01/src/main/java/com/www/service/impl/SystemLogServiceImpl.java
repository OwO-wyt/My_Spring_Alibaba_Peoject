package com.www.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.www.mapper.SystemLogMapper;
import com.www.pojo.SystemLogEntity;
import com.www.service.SystemLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 夕颜
 * @since 2024-05-12
 */
@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLogEntity> implements SystemLogService {

    @Resource
    private SystemLogMapper systemLogMapper;


    @Override
    public void saveLog(SystemLogEntity systemLogEntity) {
        systemLogMapper.saveLog(systemLogEntity);
    }
}
