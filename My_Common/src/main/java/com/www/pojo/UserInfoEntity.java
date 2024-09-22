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
@TableName("user_info")
public class UserInfoEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id")
    private Integer id;

    /**
     * 身份证
     */
    private String userCode;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 部门编码
     */
    private String depCode;

    /**
     * 部门名称
     */
    private String depName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 密码
     */
    private String password;


}
