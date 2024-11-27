package io.github.citronix.config;

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

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("io.github.citronix.controller")).paths(PathSelectors.any())
				.build().apiInfo(apiInfoMetaData());
	}

	private ApiInfo apiInfoMetaData() {

		return new ApiInfoBuilder().title("Citronix API Documentation").description(
				"The Citronix project is a farm management application designed to help lemon farmers track production, harvest, and sales. It aims to streamline the management of farms, fields, trees, harvests, and sales while optimizing tree productivity based on their age.")
				.contact(new Contact("Anass Benfill", "https://www.linkedin.com/in/benfill/", "benfillanass@gmail.com"))
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0")
				.build();
	}
}
