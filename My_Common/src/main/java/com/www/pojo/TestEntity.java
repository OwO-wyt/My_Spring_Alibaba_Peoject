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
@TableName("test")
public class TestEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id")
    private Integer id;

    private String name;

    private Integer age;


}
