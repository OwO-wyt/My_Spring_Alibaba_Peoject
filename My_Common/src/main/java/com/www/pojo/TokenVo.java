package com.www.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
public class TokenVo {

    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("refreshToken")
    private String refreshToken;
    @ApiModelProperty("用户信息")
    private TokenInfo info;

    @Data
    public static class TokenInfo {
        @ApiModelProperty("身份证")
        private String idno;
        @ApiModelProperty("手机号")
        private String mobile;
        @ApiModelProperty("姓名")
        private String name;
        @ApiModelProperty("权限")
        private Set<String> authorities;
        @ApiModelProperty("角色")
        private Set<String> roles;
        @ApiModelProperty("部门编码")
        private String depCode;
        @ApiModelProperty("部门名称")
        private String depName;
        @ApiModelProperty("管理部门代码")
        private String manageDepCode;
    }

}
