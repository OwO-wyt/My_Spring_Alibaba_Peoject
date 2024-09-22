package com.www.security;

import com.www.pojo.MbUserInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ShiLei
 * @version 1.0.0
 * @date 2021/3/10 13:15
 * @description 自定义用户信息，可以添加额外属性
 */
@Setter
@Getter
public class SecurityUserDetails extends User implements Serializable {
    private static final long serialVersionUID = -4672579300442474315L;
    // 用户信息
    private MbUserInfo mbUserInfo = new MbUserInfo();
    // 管理单位代码
    private String manageDepCode;
    // 用户角色
    private List<String> roles = new ArrayList<>();

    public SecurityUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}
