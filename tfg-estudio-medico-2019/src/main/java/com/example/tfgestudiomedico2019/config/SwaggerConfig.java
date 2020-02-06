package com.example.tfgestudiomedico2019.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket UserDataApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.tfgestudiomedico2019.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiData());
	}
	
	private ApiInfo apiData() {
		return new ApiInfoBuilder().title("API REST TFG Estudio Médico 2019")
				.description("TFG developed by Eduardo Gonzalo and Sergio Pacheco")
				.version("1.0.0")
				.build();
	}
}
