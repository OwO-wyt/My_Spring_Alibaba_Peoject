package com.www.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @CreateBy ytwang
 * @UpdateBY xxx
 * @Date 2022/3/4/15:01
 * @Version 1.0
 * 用户信息表
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MbUserInfo {

    /**
     * 自增id
     */
    private Integer id;
    /**
     * 人员id，唯一标识一个人，后续根据实际情况再确定填充规则
     */
    private String userId;
    /**
     * 证件号码
     */
    private String userCode;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 警号
     */
    private String policeNo;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 性别 1:男，2:女
     */
    private Integer sex;
    /**
     * 职级
     */
    private String prof;
    /**
     * 是否红名单用户0：否1：是
     */
    private Integer isRedList;
    /**
     * 部门编码
     */
    private String depCode;
    /**
     * 部门名称
     */
    private String depName;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;
    /**
     * 删除标识0:未删除1:已删除
     */
    private Integer deleteFlag;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 添加、删除红名单操作人身份证
     */
    private String redListOperatorCode;
    /**
     * 添加、删除红名单操作人姓名
     */
    private String redListOperatorName;

    /**
     * 添加、删除红名单操作时间
     */
    private Long redListOperateTime;

    /**
     * 角色Id
     */
    @TableField(exist = false)
    private List<Integer> roleIds;
    /**
     * 角色名称
     */
    @TableField(exist = false)
    private String roleName;
    /**
     * 创建时间字符串
     */
    @TableField(exist = false)
    private String createTimeStr;
    /**
     * 创建时间字符串
     */
    @TableField(exist = false)
    private String updateTimeStr;

    /**
     * 是否不可以修改
     */
    @TableField(exist = false)
    private Boolean cantChange = false;
}
