package com.hlsofttech.swagger2;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.service.Parameter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;

/**
 * @Package com.hailian.swagger2.SwaggerConfig
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/19 10:45
 * version V1.0.0
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    //http://localhost:8080/swagger-ui.html
   /**
     * 使用enableMVC注解的话,该配置必须,否则无法映射资源
     * @Api：用在类上，说明该类的作用
		@ApiOperation：用在方法上，说明方法的作用
		@ApiImplicitParams：用在方法上包含一组参数说明
		@ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
		paramType：参数放在哪个地方
		header-->请求参数的获取：@RequestHeader
		query-->请求参数的获取：@RequestParam
		path（用于restful接口）-->请求参数的获取：@PathVariable
		body（不常用）
		form（不常用）
		name：参数名
		dataType：参数类型
		required：参数是否必须传
		value：参数的意思
		defaultValue：参数的默认值
		@ApiResponses：用于表示一组响应
		@ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
		code：数字，例如400
		message：信息，例如"请求参数没填好"
		response：抛出异常的类
		@ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
		@ApiModelProperty：描述一个model的属性
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");

    }

    @Bean
    public Docket createRestApi() {
    	return new Docket(DocumentationType.SWAGGER_2)
		.apiInfo(apiInfo())
		.groupName("api")
		.select()
		//此包路径下的类，才生成接口文档
		.apis(RequestHandlerSelectors.basePackage("com.hlsofttech.controller"))
		//加了ApiOperation注解的类，才生成接口文档
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
		.paths(PathSelectors.any())
		.build()
		.globalOperationParameters(setHeaderToken());
    }
    /**
	 * JWT token
	 * @return
	 */
	private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

	/**
	 * api文档的详细信息函数,注意这里的注解引用的是哪个
	 *
	 * @return
	 */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("zuoqb","http://hlsofttech.com","qingbei.zuo@hlsofttech.com");
        return new ApiInfoBuilder()
        		//大标题
                .title("青岛海联软件科技有限公司")
                .description("国信在线购药平台")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
