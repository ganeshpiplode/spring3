package com.studentmanagement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwagggerConfig{
    private static final String SCHEME_NAME = "bearerAuth";
    private static final String SCHEME = "bearer";

    @Bean
    public OpenAPI customOpenAPI() {
        var openApi = new OpenAPI()
                .info(getInfo());
        addSecurity(openApi);
        return openApi;
    }

    private Info getInfo() {
        return new Info()
                .title("Student management API Documentation")
                .description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
                .version("V1.0.0")
                .contact(getcontact())
                .license(getLicense());
    }
    private Contact getcontact() {
        return new Contact()
                .name("Student Management").url("#").email("ganeshpiplode@calsoft.com");
    }
    private License getLicense() {
        return new License()
                .name("Apache License Version 2.0")
                .url("#");
    }
    private void addSecurity(OpenAPI openApi) {
        var components = createComponents();
        var securityItem = new SecurityRequirement().addList(SCHEME_NAME);
        openApi.components(components)
                .addSecurityItem(securityItem);//.paths(new Paths().)
    }

    private Components createComponents() {
        var components = new Components();
        components.addSecuritySchemes(SCHEME_NAME, createSecurityScheme())
                .addParameters("Authorization", new Parameter().in("header").schema(new StringSchema()).name("Authorization"));
        return components;
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme(SCHEME);//.bearerFormat("JWT")
    }


}
