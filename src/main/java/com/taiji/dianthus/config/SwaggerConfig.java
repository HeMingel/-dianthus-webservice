package com.taiji.dianthus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Description
 * @Author H.M
 * @Date 2019/10/15
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
        /**
         * 创建一个Docket对象
         * 调用select()方法，
         * 生成ApiSelectorBuilder对象实例，该对象负责定义外漏的API入口
         * 通过使用RequestHandlerSelectors和PathSelectors来提供Predicate，在此我们使用any()方法，将所有API都通过Swagger进行文档管理
         * @return
         */
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    //如果不想将所有的接口都通过swagger管理的话，可以将RequestHandlerSelectors.any()
                    // 修改为RequestHandlerSelectors.basePackage()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build();
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    //标题
                    .title("项目管理接口文档")
                    //简介
                    .description("")
                    //服务条款
                    .termsOfServiceUrl("")
                    //作者个人信息
                    .contact(new Contact("H.M","","angelhm@sina.cn"))
                    //版本
                    .version("1.0")
                    .build();
        }

}
