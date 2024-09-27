package com.sensetime.manual.model.base;

import com.baomidou.mybatisplus.annotation.*;
import com.sensetime.manual.common.enums.StatusEnum;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhucheng1
 * @since 2024.02.23
 */
@Data
@ToString
public class BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建人
     */
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField(value = "last_updated_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastUpdatedTime;

    /**
     * 更新人
     */
    @TableField(value = "last_updated_by", fill = FieldFill.INSERT_UPDATE)
    private String lastUpdatedBy;

    /**
     * 删除标识，0-未删除，1-已删除
     */
    @TableLogic
    @TableField(value = "status", fill = FieldFill.INSERT)
    private Integer status;


    public void initCreate() {
        initCreate(null, null);
    }

    public void initCreate(String userName) {
        initCreate(userName, null);
    }

    public void initCreate(String userName, LocalDateTime date) {
        if (StringUtils.isBlank(userName)) {
            userName = "system";
        }
        if (date == null) {
            date = LocalDateTime.now();
        }
        this.createdBy = userName;
        this.createdTime = date;
        this.lastUpdatedBy = userName;
        this.lastUpdatedTime = date;
        this.status = StatusEnum.ACTIVE.getValue();
    }

    public void initUpdate() {
        initUpdate(null, null);
    }

    public void initUpdate(String userName) {
        initUpdate(userName, null);
    }

    public void initUpdate(String userName, LocalDateTime date) {
        if (date == null) {
            date = LocalDateTime.now();
        }
        if (StringUtils.isBlank(userName)) {
            userName = "system";
        }
        this.lastUpdatedBy = userName;
        this.lastUpdatedTime = date;
    }
}
