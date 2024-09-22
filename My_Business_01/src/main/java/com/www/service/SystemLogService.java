package com.www.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.www.pojo.SystemLogEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 夕颜
 * @since 2024-05-12
 */
public interface SystemLogService extends IService<SystemLogEntity> {


    void saveLog(SystemLogEntity systemLogEntity);

}
