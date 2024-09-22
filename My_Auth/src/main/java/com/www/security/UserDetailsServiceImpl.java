package com.www.security;

import cn.hutool.core.net.URLEncoder;
import cn.hutool.http.HttpStatus;
import com.www.pojo.MbUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @author ceshi
 * @version 1.0.0
 * @date 2021/3/9 14:03
 * @description 用户登录处理
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 查询数据库，验证用户名信息
        MbUserInfo mbUserInfo = MbUserInfo.builder().userCode("123456").userName("zhangsan").password("123456").build();
        if (null == mbUserInfo) {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.setStatus(HttpStatus.HTTP_FORBIDDEN);
        }
        String[] authorities = new String[]{"a" , "b"};
        List<String> roles = Arrays.asList("x" , "y");
        List<GrantedAuthority> authoritiesList = AuthorityUtils.createAuthorityList(authorities);
        // 查询用户所属二级管理单位的代码
        String manageDepCode = "000000";

        SecurityUserDetails userDetails = new SecurityUserDetails(userName, passwordEncoder.encode(mbUserInfo.getPassword()), authoritiesList);
        userDetails.setManageDepCode(manageDepCode);
        userDetails.setMbUserInfo(mbUserInfo);
        userDetails.setRoles(roles);

        // 将用户名进行UTF-8编码，不然前端将其放入token中时，后端节收到的姓名乱码
        userDetails.getMbUserInfo().setUserName(URLEncoder.createAll().encode(userDetails.getMbUserInfo().getUserName(), StandardCharsets.UTF_8));

        // 返回user实体
        return userDetails;
    }
}
