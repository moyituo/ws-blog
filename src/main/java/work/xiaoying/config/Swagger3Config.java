package work.xiaoying.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * swagger3配置
 * application中还配置了mvc，因为springboot版本与swagger3不兼容
 *
 * @author 小樱
 * @date 2022/11/22
 */
@Configuration
@EnableOpenApi
public class Swagger3Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                // ture 启用Swagger3.0， false 禁用（生产环境要禁用）
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                // 扫描的路径使用@Api的controller
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("博客接口文档")
                .description("接口文档")
                .contact(new Contact("挚爱小v脸", "https://github.com/ytt521", "3047893225@qq.com"))
                .version("1.0")
                .build();
    }
}

