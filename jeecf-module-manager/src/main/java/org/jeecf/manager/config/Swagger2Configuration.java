package org.jeecf.manager.config;

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
 * swagger2 配置
 * 
 * @author jianyiming
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("org.jeecf.manager.module")).paths(PathSelectors.any()).build();
    }

    /**
     * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     * 
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("JEECF RESTful API")
                // 创建人
                .contact(new Contact("jianyiming", "", ""))
                // 版本号
                .version("1.0")
                // 描述
                .description("JEECF API 描述").build();
    }

}
