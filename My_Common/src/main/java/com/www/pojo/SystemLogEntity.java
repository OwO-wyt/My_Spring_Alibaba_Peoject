package com.www.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 夕颜
 * @since 2024-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("system_log")
public class SystemLogEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "log_id")
    private Long logId;

    private String userCode;

    private String userName;

    private String depCode;

    private String depName;

    private String moduleName;

    private String createTime;


}
