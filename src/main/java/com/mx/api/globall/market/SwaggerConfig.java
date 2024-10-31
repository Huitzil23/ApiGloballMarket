package com.mx.api.globall.market;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket apiDocket() {
		Docket docket;
		docket = new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mx.api.globall.market"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
		
		docket.globalOperationParameters(
                Arrays.asList(
                new ParameterBuilder().name("X-API-KEY")
                        .description("Authorization ApiKey Globall for security ")
                        .modelRef(new ModelRef("String")).parameterType("header").required(true).build()));  

		return docket;
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"GloballTeConecta Service API Integraciones",
				"GloballTeConecta Descripción API",
				"1.0",
				"",//url pagina
				null,//new Contact("Globall", "http://www.globall.com", "info@globall.com"),
				null,//"LICENSE",//licencia
				null,//"http://www.globall.com",//url licencia
				Collections.emptyList()
				);
	}
	
}