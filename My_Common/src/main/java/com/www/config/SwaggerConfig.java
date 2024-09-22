package com.www.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @CreateBy ytwang
 * @UpdateBY xxx
 * @Date 2022/1/18/14:27
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@ConditionalOnClass(javax.servlet.ServletException.class)
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.www.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 访问地址： http://localhost:8081/auth/doc.html
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfo("myProject接口服务", "接口", "1.0", "",
                new Contact("王运涛", "", "724429027@qq.com"), "", "",
                new ArrayList<VendorExtension>());
    }
}
